package camp.opaque.marketgardener

import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.PlayerInventory
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.util.Vector
import org.junit.Test
import org.mockito.kotlin.*
import java.lang.AssertionError

internal class MarketGardenerAttackEventHandlerTest {
    @Test
    fun allConditionsMet() {
        val itemMeta = mock<ItemMeta> {
            on { hasDisplayName() } doReturn true
            on { asString } doReturn "\"Market Gardener\""
        }
        val itemInMainHand = mock<ItemStack> {
            on { getItemMeta() } doReturn itemMeta
        }
        val inventory = mock<PlayerInventory> {
            on { getItemInMainHand() } doReturn itemInMainHand
        }
        val damager = mock<Player> {
            on { getInventory() } doReturn inventory
            on { velocity } doReturn Vector(999, 999, 999)
        }
        val victim = mock<Entity>()
        val event = mock<EntityDamageByEntityEvent> {
            on { getDamager() } doReturn damager
            on { damage } doReturn 10.0
            on { entity } doReturn victim
        }
        val soundPlayer = mock<SoundPlayer>()
        val critLabel = mock<CriticalHitLabel>()
        val critLabelFactory = mock<CriticalHitLabelFactory> {
            on { create() } doReturn critLabel
        }

        MarketGardenerAttackEventHandler(soundPlayer, critLabelFactory).onAttack(event)

        verify(event).damage = 30.0
        verify(soundPlayer, times(3)).playSound(eq(victim), any(), any(), any())
        verify(critLabel).spawnAt(eq(victim))
    }

    @Test
    fun ignoresNonPlayerCausedEvents() {
        val event = mock<EntityDamageByEntityEvent> {
            on { damager } doReturn mock {}
            on {
                damage = any()
            } doThrow AssertionError("Should not try to modify damage since event conditions are not met")
        }

        val handler = MarketGardenerAttackEventHandler(mock(), mock())

        handler.onAttack(event)
    }

    @Test
    fun ignoresSlowPlayerCausedEvents() {
        val damager = mock<Player> {
            on { velocity } doReturn Vector(0, 0, 0)
        }
        val event = mock<EntityDamageByEntityEvent> {
            on { getDamager() } doReturn damager
            on {
                damage = any()
            } doThrow AssertionError("Should not try to modify damage since event conditions are not met")
        }

        val handler = MarketGardenerAttackEventHandler(mock(), mock())

        handler.onAttack(event)
    }

    @Test
    fun ignoresNonMarketGardenerCausedEvents() {
        val itemMeta = mock<ItemMeta> {
            on { hasDisplayName() } doReturn true
            on { asString } doReturn "Generic shovel"
        }
        val itemInMainHand = mock<ItemStack> {
            on { getItemMeta() } doReturn itemMeta
        }
        val inventory = mock<PlayerInventory> {
            on { getItemInMainHand() } doReturn itemInMainHand
        }
        val damager = mock<Player> {
            on { getInventory() } doReturn inventory
            on { velocity } doReturn Vector(999, 999, 999)
        }
        val event = mock<EntityDamageByEntityEvent> {
            on { getDamager() } doReturn damager
            on {
                damage = any()
            } doThrow AssertionError("Should not try to modify damage since event conditions are not met")
        }

        val handler = MarketGardenerAttackEventHandler(mock(), mock())

        handler.onAttack(event)
    }

    @Test
    fun handlesNullItemMeta() {
        val itemInMainHand = mock<ItemStack> {
            on { itemMeta } doReturn null
        }
        val inventory = mock<PlayerInventory> {
            on { getItemInMainHand() } doReturn itemInMainHand
        }
        val damager = mock<Player> {
            on { getInventory() } doReturn inventory
            on { velocity } doReturn Vector(999, 999, 999)
        }
        val event = mock<EntityDamageByEntityEvent> {
            on { getDamager() } doReturn damager
            on {
                damage = any()
            } doThrow AssertionError("Should not try to modify damage since event conditions are not met")
        }

        val handler = MarketGardenerAttackEventHandler(mock(), mock())

        handler.onAttack(event)
    }
}
