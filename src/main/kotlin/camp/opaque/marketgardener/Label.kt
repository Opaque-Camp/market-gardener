package camp.opaque.marketgardener

import net.kyori.adventure.text.Component
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.entity.ArmorStand
import org.bukkit.util.Vector

class Label(private val component: Component) {
    private var stand: ArmorStand? = null

    fun spawnAt(world: World, location: Location) {
        stand = world.spawn(location, ArmorStand::class.java)
        stand?.run {
            customName(component)
            isCustomNameVisible = true
            isVisible = false
        }
    }

    fun float() {
        stand?.location?.add(Vector(0.0, 1.0, 0.0))
    }

    fun remove() {
        stand?.remove()
    }
}