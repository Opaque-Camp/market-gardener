package camp.opaque.marketgardener

import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.plugin.Plugin

class MarketGardenerAttackEventHandler(private val plugin: Plugin) : Listener {
    @EventHandler
    fun onAttack(event: EntityDamageByEntityEvent) {
        if (!isValidMarketGardenerHit(event))
            return
        event.damage *= 3
        repeat(3) {
            event.entity.world.playSound(event.entity, Sound.BLOCK_BELL_USE, 3f, 3f)
        }
        CriticalHitLabel(plugin).spawnAt(event.entity)
    }

    private fun isValidMarketGardenerHit(event: EntityDamageByEntityEvent): Boolean {
        val damager = event.damager as? Player
        if (damager == null || !isAttackerFastEnough(damager)) {
            return false
        }
        return damager.inventory.itemInMainHand.itemMeta.isMarketGardener
    }

    private fun isAttackerFastEnough(damager: Player) = damager.velocity.length() >= MIN_MARKET_GARDENING_VELOCITY

    private companion object {
        const val MIN_MARKET_GARDENING_VELOCITY = 0.75
    }
}

val ItemMeta?.isMarketGardener: Boolean
    get() {
        if (this == null) {
            return false
        }
        val weaponName = if (hasDisplayName()) asString else ""
        return weaponName.lowercase().contains("\"market gardener\"")
    }
