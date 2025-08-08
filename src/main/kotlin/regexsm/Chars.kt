package regexsm

object Chars {
    val digit = setOf("0","1","2","3","4","5","6","7","8","9")
    val nonZeroDigit = setOf("1","2","3","4","5","6","7","8","9")
    val bin = setOf("0","1")
    val specials = setOf("!","@","#","$","%","&","*")
    val capitals = (65..90).map { String(byteArrayOf(it.toByte())) }.toSet()
}
