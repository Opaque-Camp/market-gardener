package camp.opaque.marketgardener

import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.plugin.Plugin
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.doThrow
import org.mockito.kotlin.mock
import java.lang.AssertionError

internal class MarketGardenerAttackEventHandlerTest {
    @Test
    fun ignoresNonPlayerCausedEvents() {
        val event = mock<EntityDamageByEntityEvent> {
            on { damager } doReturn mock {}
            on { damage = any() } doThrow AssertionError()
        }
        val plugin = mock<Plugin> {}

        val handler = MarketGardenerAttackEventHandler(plugin)

        handler.onAttack(event)
    }
}
