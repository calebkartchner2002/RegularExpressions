package regexsm

class PasswordDetector : Detector {
    private class Ctx {
        var sawCapital = false
        var sawSpecial = false
        var lastWasSpecial = false
        var len = 0
    }
    private interface State {
        fun next(c: String, ctx: Ctx): State
        fun accepting(ctx: Ctx): Boolean = false
    }
    private class Scan : State {
        override fun next(c: String, ctx: Ctx): State {
            ctx.len += 1
            ctx.lastWasSpecial = c in Chars.specials
            if (c in Chars.capitals) ctx.sawCapital = true
            if (ctx.lastWasSpecial) ctx.sawSpecial = true
            return this
        }
        override fun accepting(ctx: Ctx): Boolean {
            return ctx.len >= 8 && ctx.sawCapital && ctx.sawSpecial && !ctx.lastWasSpecial
        }
    }

    override fun matches(input: String): Boolean {
        val ctx = Ctx()
        var state: State = Scan()
        for (c in input.chunked(1)) {
            state = state.next(c, ctx)
        }
        return state.accepting(ctx)
    }
}
