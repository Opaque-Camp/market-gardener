package camp.opaque.marketgardener

import org.bukkit.scheduler.BukkitRunnable

class CriticalHitLabelDestroyRunnable(private val label: CriticalHitLabel) : BukkitRunnable() {
    override fun run() = label.detach()
}