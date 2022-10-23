package camp.opaque.marketgardener

import org.bukkit.Sound
import org.bukkit.entity.Entity

interface SoundPlayer {
    fun playSound(entity: Entity, sound: Sound, volume: Float, pitch: Float)
}