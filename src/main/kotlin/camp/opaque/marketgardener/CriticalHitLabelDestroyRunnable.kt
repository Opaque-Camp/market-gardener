package camp.opaque.marketgardener

import org.bukkit.scheduler.BukkitRunnable

class CriticalHitLabelDestroyRunnable(private val label: CriticalHitLabelImpl) : BukkitRunnable() {
    override fun run() = label.detach()
}