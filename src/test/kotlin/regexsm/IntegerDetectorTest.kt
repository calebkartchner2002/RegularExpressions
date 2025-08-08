package regexsm

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class IntegerDetectorTest {
    private val d = Detectors.integer

    @Test
    fun valid() {
        assertTrue(d.matches("1"))
        assertTrue(d.matches("123"))
        assertTrue(d.matches("3452342352434534524346"))
    }

    @Test
    fun invalid() {
        assertFalse(d.matches(""))
        assertFalse(d.matches("0123"))
        assertFalse(d.matches("132a"))
        assertFalse(d.matches("0"))
    }
}
