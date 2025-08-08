package regexsm

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class EmailDetectorTest {
    private val d = Detectors.email

    @Test
    fun valid() {
        assertTrue(d.matches("a@b.c"))
        assertTrue(d.matches("joseph.ditton@usu.edu"))
        assertTrue(d.matches("{}*$.&$*(@*$%&.*&*"))
    }

    @Test
    fun invalid() {
        assertFalse(d.matches("@b.c"))
        assertFalse(d.matches("a@b@c.com"))
        assertFalse(d.matches("a.b@b.b.c"))
        assertFalse(d.matches("joseph ditton@usu.edu"))
        assertFalse(d.matches("a@.c"))
        assertFalse(d.matches("a@b."))
        assertFalse(d.matches("a@b c"))
        assertFalse(d.matches("a@b.c.d"))
    }
}
