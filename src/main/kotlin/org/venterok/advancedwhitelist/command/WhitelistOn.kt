package org.venterok.advancedwhitelist.command

import org.bukkit.command.CommandSender
import org.venterok.advancedwhitelist.AdvancedWhitelist

class WhitelistOn {
    fun whOn (sender: CommandSender) {

        val config = AdvancedWhitelist.inst?.config

        val playerAlreadyWhitelisted = config?.getBoolean("whitelist.enabled")

        val whitelistAlreadyOn = config?.getString("message.whitelist-already-on")

        val whitelistOn = config?.getString("message.whitelist-on")

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