package camp.opaque.marketgardener

import org.bukkit.inventory.meta.ItemMeta
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import kotlin.test.assertTrue
import kotlin.test.assertFalse
import org.junit.Test

internal class IsMarketGardenerTest {
    @Test
    fun itemMetaIsValid() {
        val itemMeta = mock<ItemMeta> {
            on { hasDisplayName() } doReturn true
            on { asString } doReturn """something "market GaRdEnEr" something"""
        }

        assertTrue(itemMeta.isMarketGardener)
    }

    @Test
    fun itemMetaIsNull() {
        val itemMeta: ItemMeta? = null

        assertFalse(itemMeta.isMarketGardener)
    }
}