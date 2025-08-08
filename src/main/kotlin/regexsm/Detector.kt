package regexsm

interface Detector {
    fun matches(input: String): Boolean
}
