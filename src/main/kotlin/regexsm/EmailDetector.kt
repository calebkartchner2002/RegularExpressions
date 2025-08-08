package regexsm

class EmailDetector : Detector {
    private class Ctx {
        var part1Len = 0
        var part2Len = 0
        var part3Len = 0
        var sawAt = false
        var sawDotAfterAt = false
    }
    private interface State {
        fun next(c: String, ctx: Ctx): State
        fun accepting(ctx: Ctx): Boolean = false
    }
    private class Part1 : State {
        override fun next(c: String, ctx: Ctx): State {
            return when {
                c == " " || c == "@" -> if (c == "@") {
                    if (ctx.part1Len >= 1 && !ctx.sawAt) { ctx.sawAt = true; Part2() } else Reject
                } else Reject
                else -> { ctx.part1Len += 1; this }
            }
        }
    }
    private class Part2 : State {
        override fun next(c: String, ctx: Ctx): State {
            return when {
                c == " " || c == "@" -> Reject
                c == "." -> if (ctx.part2Len >= 1 && !ctx.sawDotAfterAt) { ctx.sawDotAfterAt = true; Part3() } else Reject
                else -> { ctx.part2Len += 1; this }
            }
        }
    }
    private class Part3 : State {
        override fun next(c: String, ctx: Ctx): State {
            return when {
                c == " " || c == "@" || c == "." -> Reject
                else -> { ctx.part3Len += 1; this }
            }
        }
        override fun accepting(ctx: Ctx): Boolean {
            return ctx.sawAt && ctx.sawDotAfterAt && ctx.part1Len >= 1 && ctx.part2Len >= 1 && ctx.part3Len >= 1
        }
    }
    private object Reject : State {
        override fun next(c: String, ctx: Ctx): State = this
        override fun accepting(ctx: Ctx): Boolean = false
    }

    override fun matches(input: String): Boolean {
        if (input.isEmpty()) return false
        var state: State = Part1()
        val ctx = Ctx()
        for (c in input.chunked(1)) {
            state = state.next(c, ctx)
            if (state === Reject) return false
        }
        return state.accepting(ctx)
    }
}
