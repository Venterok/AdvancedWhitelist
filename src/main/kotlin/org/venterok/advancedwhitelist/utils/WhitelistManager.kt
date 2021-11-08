package org.venterok.advancedwhitelist.utils

import org.bukkit.configuration.file.FileConfiguration
import org.venterok.advancedwhitelist.AdvancedWhitelist
import org.venterok.advancedwhitelist.AdvancedWhitelist.Companion.formatColor


class WhitelistManager(st: AdvancedWhitelist) {

    companion object {
        val config = AdvancedWhitelist.inst!!.config
    }

    private val st: AdvancedWhitelist

    var whitelist = ArrayList<String>()
        private set
    var whitelistEnabled = true
        private set
    var noWhitelistMessage = ""
        private set

    fun reload() {
        st.reloadConfig()
        val config: FileConfiguration = st.config
        whitelist = ArrayList(config.getStringList("in-whitelist"))
        whitelistEnabled = config.getBoolean(formatColor("whitelist.enabled"))
        noWhitelistMessage = config.getString(formatColor("message.join-no-whitelist"))!!
    }

    fun saveWhitelists() {
        val config: FileConfiguration = st.config
        config["in-whitelist"] = whitelist
        config["whitelist.enabled"] = java.lang.Boolean.valueOf(whitelistEnabled)
        st.saveConfig()
    }

    fun isWhitelisted(name: String): Boolean {
        return whitelist.contains(name)
    }

    fun addWhitelist(name: String) {
        if (whitelist.contains(name)) return
        whitelist.add(name)
        saveWhitelists()
    }

    fun removeWhitelist(name: String) {
        if (!whitelist.contains(name)) return
        whitelist.remove(name)
        saveWhitelists()
    }

    fun setWhitelist(enabled: Boolean) {
        whitelistEnabled = enabled
        saveWhitelists()
    }

    fun whitelistEnabled(): Boolean {
        return this.whitelistEnabled
    }

    fun noWhitelistMessage(): String {
        return this.noWhitelistMessage
    }

    init {
        this.st = st
        reload()
    }
}