package camp.opaque.marketgardener

import org.bukkit.Sound
import org.bukkit.entity.Entity

class SoundPlayerImpl : SoundPlayer {
    override fun playSound(entity: Entity, sound: Sound, volume: Float, pitch: Float) {
        entity.world.playSound(entity, sound, volume, pitch)
    }
}