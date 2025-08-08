package regexsm

class BinaryStartsEndsWithOneDetector : Detector {
    private class Ctx { var last = "" }
    private interface State {
        fun next(c: String, ctx: Ctx): State
        fun accepting(ctx: Ctx): Boolean = false
    }
    private class Start : State {
        override fun next(c: String, ctx: Ctx): State {
            return if (c == "1") { ctx.last = "1"; InBin() } else Reject
        }
    }
    private class InBin : State {
        override fun next(c: String, ctx: Ctx): State {
            return if (c in Chars.bin) { ctx.last = c; this } else Reject
        }
        override fun accepting(ctx: Ctx): Boolean = ctx.last == "1"
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
            state = state.next(c, ctx)
            if (state === Reject) return false
        }
        return state.accepting(ctx)
    }
}
