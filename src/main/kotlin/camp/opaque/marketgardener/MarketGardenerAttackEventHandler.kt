package camp.opaque.marketgardener

import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.Plugin

class MarketGardenerAttackEventHandler(private val plugin: Plugin) : Listener {
    @EventHandler
    fun onAttack(event: EntityDamageByEntityEvent) {
        if (!isDamagerAFastFlyingPlayerWithMarketGardener(event))
            return
        event.damage *= 3
        repeat(3) {
            event.entity.world.playSound(event.entity, Sound.BLOCK_BELL_USE, 3f, 3f)
        }
        CriticalHitLabel(plugin).spawnAt(event.entity)
    }

    private fun isDamagerAFastFlyingPlayerWithMarketGardener(event: EntityDamageByEntityEvent): Boolean {
        val damager = event.damager
        if (damager !is Player || damager.velocity.length() < MIN_MARKET_GARDENING_VELOCITY) {
            return false
        }
        return damager.inventory.itemInMainHand.isMarketGardener
    }

    private companion object {
        const val MIN_MARKET_GARDENING_VELOCITY = 0.75
    }
}

val ItemStack.isMarketGardener: Boolean
    get() {
        val weaponName = if (itemMeta.hasDisplayName()) itemMeta.asString else ""
        return weaponName.lowercase().contains("\"market gardener\"")
    }
