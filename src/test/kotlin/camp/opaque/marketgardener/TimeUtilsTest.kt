package camp.opaque.marketgardener

import org.junit.Test
import kotlin.test.assertEquals

internal class TimeUtilsTest {
    @Test
    fun getSeconds() {
        val fortyTicks = 2.seconds
        assertEquals(40, fortyTicks)
    }
}