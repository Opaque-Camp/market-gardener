package camp.opaque.marketgardener

import org.bukkit.plugin.Plugin

class CriticalHitLabelFactoryImpl(private val plugin: Plugin) : CriticalHitLabelFactory {
    override fun create() = CriticalHitLabelImpl(plugin)
}