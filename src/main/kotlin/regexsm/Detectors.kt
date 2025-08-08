package regexsm

object Detectors {
    val integer = IntegerDetector()
    val floating = FloatDetector()
    val binaryStartsEndsWithOne = BinaryStartsEndsWithOneDetector()
    val email = EmailDetector()
    val password = PasswordDetector()
}
