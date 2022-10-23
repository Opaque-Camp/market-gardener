package camp.opaque.marketgardener

import org.bukkit.scheduler.BukkitRunnable

class CriticalHitLabelFloatRunnable(private val label: CriticalHitLabelImpl) : BukkitRunnable() {
    override fun run() {
        label.float()
    }
}