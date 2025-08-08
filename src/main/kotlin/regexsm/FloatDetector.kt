package regexsm

class FloatDetector : Detector {
    private class Ctx {
        var dotCount = 0
        var digitsAfterDot = 0
        var sawAnyDigit = false
        var pos = 0
    }
    private interface State {
        fun next(c: String, ctx: Ctx): State
        fun accepting(ctx: Ctx): Boolean = false
    }
    private class Start : State {
        override fun next(c: String, ctx: Ctx): State {
            return when {
                c == "." -> { ctx.dotCount = 1; AfterDotExpectDigit() }
                c == "0" -> ZeroStart()
                c in Chars.nonZeroDigit -> { ctx.sawAnyDigit = true; IntPart() }
                else -> Reject
            }
        }
    }
    private class ZeroStart : State {
        override fun next(c: String, ctx: Ctx): State {
            return if (c == ".") { ctx.dotCount = 1; AfterDotExpectDigit() } else Reject
        }
    }
    private class IntPart : State {
        override fun next(c: String, ctx: Ctx): State {
            return when {
                c in Chars.digit -> this
                c == "." && ctx.dotCount == 0 -> { ctx.dotCount = 1; AfterDotExpectDigit() }
                else -> Reject
            }
        }
    }
    private class AfterDotExpectDigit : State {
        override fun next(c: String, ctx: Ctx): State {
            return if (c in Chars.digit) { ctx.digitsAfterDot += 1; FractionPart() } else Reject
        }
    }
    private class FractionPart : State {
        override fun next(c: String, ctx: Ctx): State {
            return if (c in Chars.digit) { ctx.digitsAfterDot += 1; this } else Reject
        }
        override fun accepting(ctx: Ctx): Boolean = ctx.dotCount == 1 && ctx.digitsAfterDot >= 1
    }
    private object Reject : State {
        override fun next(c: String, ctx: Ctx): State = this
        override fun accepting(ctx: Ctx): Boolean = false
    }

    override fun matches(input: String): Boolean {
        if (input.isEmpty()) return false
        val ctx = Ctx()
        var state: State = Start()
        for (c in input.chunked(1)) {
            ctx.pos += 1
            state = state.next(c, ctx)
            if (state === Reject) return false
        }
        return state.accepting(ctx)
    }
}
