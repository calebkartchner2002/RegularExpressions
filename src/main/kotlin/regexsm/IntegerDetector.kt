package regexsm

class IntegerDetector : Detector {
    private interface State {
        fun next(c: String, ctx: Ctx): State
        fun accepting(ctx: Ctx): Boolean = false
    }
    private class Start : State {
        override fun next(c: String, ctx: Ctx): State {
            return if (c in Chars.nonZeroDigit) InNumber() else Reject
        }
    }
    private class InNumber : State {
        override fun next(c: String, ctx: Ctx): State {
            return if (c in Chars.digit) this else Reject
        }
        override fun accepting(ctx: Ctx): Boolean = true
    }
    private object Reject : State {
        override fun next(c: String, ctx: Ctx): State = this
        override fun accepting(ctx: Ctx): Boolean = false
    }
    private class Ctx

    override fun matches(input: String): Boolean {
        if (input.isEmpty()) return false
        var state: State = Start()
        val ctx = Ctx()
        for (c in input.chunked(1)) {
            state = state.next(c, ctx)
            if (state === Reject) return false
        }
        return state.accepting(ctx)
    }
}
