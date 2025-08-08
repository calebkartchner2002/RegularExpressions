package regexsm

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class BinaryStartsEndsWithOneDetectorTest {
    private val d = Detectors.binaryStartsEndsWithOne

    @Test
    fun valid() {
        assertTrue(d.matches("1"))
        assertTrue(d.matches("11"))
        assertTrue(d.matches("101"))
        assertTrue(d.matches("111111"))
        assertTrue(d.matches("10011010001"))
    }

    @Test
    fun invalid() {
        assertFalse(d.matches(""))
        assertFalse(d.matches("01"))
        assertFalse(d.matches("10"))
        assertFalse(d.matches("1000010"))
        assertFalse(d.matches("100a01"))
        assertFalse(d.matches("0"))
    }
}
