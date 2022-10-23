package camp.opaque.marketgardener

import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.inventory.meta.ItemMeta

class MarketGardenerAttackEventHandler(
    private val soundPlayer: SoundPlayer,
    private val criticalHitLabelFactory: CriticalHitLabelFactory
) : Listener {
    @EventHandler
    fun onAttack(event: EntityDamageByEntityEvent) {
        if (!isValidMarketGardenerHit(event))
            return
        event.damage *= 3
        repeat(3) {
            soundPlayer.playSound(event.entity, Sound.BLOCK_BELL_USE, CRIT_SOUND_VOLUME, CRIT_SOUND_PITCH)
        }
        criticalHitLabelFactory.create().spawnAt(event.entity)
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
        const val CRIT_SOUND_VOLUME = 3f
        const val CRIT_SOUND_PITCH = 3f
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
