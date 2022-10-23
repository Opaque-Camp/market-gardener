package camp.opaque.marketgardener

import net.kyori.adventure.text.Component
import org.bukkit.entity.Entity
import org.bukkit.plugin.Plugin
import org.bukkit.scheduler.BukkitTask

class CriticalHitLabelImpl(private val plugin: Plugin) : CriticalHitLabel {
    private var label: Label? = null
    private var floatTask: BukkitTask? = null

    override fun spawnAt(entity: Entity) {
        label = Label(Component.text("CRITICAL HIT!!!")).apply { spawnAt(entity.world, entity.location) }
        CriticalHitLabelDestroyRunnable(this).runTaskLater(plugin, LABEL_LIFE_TIME)
        floatTask = CriticalHitLabelFloatRunnable(this).runTaskTimer(plugin, 0, 10)
    }

    override fun float() {
        label?.float()
    }

    override fun detach() {
        label?.remove()
        floatTask?.cancel()
    }

    private companion object {
        val LABEL_LIFE_TIME = 2.seconds
    }
}