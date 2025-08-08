package regexsm

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PasswordDetectorTest {
    private val d = Detectors.password

    @Test
    fun valid() {
        assertTrue(d.matches("aaaaH!aa"))
        assertTrue(d.matches("1234567*9J"))
        assertTrue(d.matches("asdpoihj;loikjasdf;ijp;lij2309jasd;lfkm20ij@aH"))
    }

    @Test
    fun invalid() {
        assertFalse(d.matches("a"))
        assertFalse(d.matches("aaaaaaa!"))
        assertFalse(d.matches("aaaHaaaaa"))
        assertFalse(d.matches("Abbbbbbb!"))
        assertFalse(d.matches("AAAAAAA*"))
        assertFalse(d.matches("aaaaaaa*"))
        assertFalse(d.matches("AAAAAAAa"))
    }
}
