package org.venterok.advancedwhitelist

import org.bukkit.command.CommandSender

class WhitelistOn {
    fun whOn (sender: CommandSender) {
        val playerAlreadyWhitelisted = AdvancedWhitelist.inst?.config?.getBoolean("whitelist.enabled")
        val whitelistAlreadyOn = AdvancedWhitelist.inst?.config?.getString("message.whitelist-already-on")
        val whitelistOn = AdvancedWhitelist.inst?.config?.getString("message.whitelist-on")
        if (playerAlreadyWhitelisted == true) {
            sender.sendMessage(AdvancedWhitelist.formatColor(whitelistAlreadyOn!!))
        }
        else {
            sender.sendMessage(AdvancedWhitelist.formatColor(whitelistOn!!))
            AdvancedWhitelist.inst?.config?.set("whitelist.enabled", true)
            AdvancedWhitelist.inst?.saveConfig()
            AdvancedWhitelist.inst?.reloadConfig()
        }
    }
}