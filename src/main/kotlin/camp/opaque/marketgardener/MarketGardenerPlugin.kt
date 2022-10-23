package camp.opaque.marketgardener

import org.bukkit.plugin.java.JavaPlugin

@Suppress("unused")
class MarketGardenerPlugin : JavaPlugin() {
    private val attackEventHandler = MarketGardenerAttackEventHandler(this)

    override fun onEnable() {
        logger.info("Market Gardener: Starting up.")
        registerAttackEventHandler()
    }

    private fun registerAttackEventHandler() {
        server.pluginManager.registerEvents(attackEventHandler, this)
        logger.info("Market Gardener hit event handler has been registered.")
    }

    override fun onDisable() {
    }
}