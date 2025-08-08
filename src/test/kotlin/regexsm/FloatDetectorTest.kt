package regexsm

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class FloatDetectorTest {
    private val d = Detectors.floating

    @Test
    fun valid() {
        assertTrue(d.matches("1.0"))
        assertTrue(d.matches("123.34"))
        assertTrue(d.matches("0.20000"))
        assertTrue(d.matches("12349871234.12340981234098"))
        assertTrue(d.matches(".123"))
    }

    @Test
    fun invalid() {
        assertFalse(d.matches("123"))
        assertFalse(d.matches("123.123."))
        assertFalse(d.matches("123.02a"))
        assertFalse(d.matches("123."))
        assertFalse(d.matches("012.4"))
        assertFalse(d.matches(""))
        assertFalse(d.matches("."))
        assertFalse(d.matches("0"))
        assertFalse(d.matches("00.1"))
    }
}
