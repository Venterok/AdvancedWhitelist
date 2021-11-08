package org.venterok.advancedwhitelist

import net.md_5.bungee.api.ChatColor
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import org.venterok.advancedwhitelist.features.PlayerJoinCheck
import org.venterok.advancedwhitelist.features.WhitelistCommand
import org.venterok.advancedwhitelist.utils.WhitelistManager
import java.io.File
import java.util.regex.Matcher
import java.util.regex.Pattern

class AdvancedWhitelist : JavaPlugin() {
    private var storage: WhitelistManager? = null

    override fun onEnable() {

        inst = this
        config.options().copyDefaults(true)
        saveConfig()
        configFile = setUpConfig()
        storage = WhitelistManager(this)

        Bukkit.getPluginManager().registerEvents(PlayerJoinCheck(this),this )
        getCommand("advwhitelist")!!.setExecutor(WhitelistCommand(this))

    }

    override fun onDisable() {
        this.storage?.saveWhitelists()
    }
    companion object {
        var configFile : File? = null
        private val pattern: Pattern = Pattern.compile("#[a-fA-F0-9]{6}")
        fun formatColor(msg: String): String {
            var msg = msg
            var matcher: Matcher = pattern.matcher(msg)
            while (matcher.find()) {
                val color = msg.substring(matcher.start(), matcher.end())
                msg = msg.replace(color, ChatColor.of(color).toString() + "")
                matcher = pattern.matcher(msg)
            }
            return ChatColor.translateAlternateColorCodes('&', msg)
        }
        var inst: AdvancedWhitelist? = null
    }
    private fun setUpConfig(): File {
        val config = File(dataFolder.toString() + File.separator + "config.yml")
        if (!config.exists()) {
            logger.info("Creating config file...")
            getConfig().options().copyDefaults(true)
            saveDefaultConfig()
        }
        return config
    }

    fun getStorage(): WhitelistManager? {
        return storage
    }
}
