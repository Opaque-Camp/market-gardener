package camp.opaque.marketgardener

import net.kyori.adventure.text.Component
import org.bukkit.entity.Entity
import org.bukkit.plugin.Plugin
import org.bukkit.scheduler.BukkitTask

class CriticalHitLabel(private val plugin: Plugin) {
    private var label: Label? = null
    private var floatTask: BukkitTask? = null

    fun spawnAt(entity: Entity) {
        label = Label(Component.text("CRITICAL HIT!!!")).apply { spawnAt(entity.world, entity.location) }
        CriticalHitLabelDestroyRunnable(this).runTaskLater(plugin, LABEL_LIFE_TIME)
        floatTask = CriticalHitLabelFloatRunnable(this).runTaskTimer(plugin, 0, 10)
    }

    fun float() {
        label?.float()
    }

    fun detach() {
        label?.remove()
        floatTask?.cancel()
    }

    private companion object {
        val LABEL_LIFE_TIME = 2.seconds
    }
}