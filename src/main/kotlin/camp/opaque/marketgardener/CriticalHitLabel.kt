package camp.opaque.marketgardener

import org.bukkit.entity.Entity

interface CriticalHitLabel {
    fun spawnAt(entity: Entity)
    fun float()
    fun detach()
}