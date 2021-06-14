package org.venterok.advancedwhitelist.command

import org.bukkit.command.CommandSender
import org.venterok.advancedwhitelist.AdvancedWhitelist

class WhitelistOff {
    fun whOff (sender: CommandSender) {

        val config = AdvancedWhitelist.inst?.config

        val playerAlreadyWhitelisted = config?.getBoolean("whitelist.enabled")

        val whitelistAlreadyOff = config?.getString("message.whitelist-already-off")

        val whitelistOff = config?.getString("message.whitelist-off")

        if (playerAlreadyWhitelisted == false) {
            sender.sendMessage(AdvancedWhitelist.formatColor(whitelistAlreadyOff!!))
        }
        else {
            sender.sendMessage(AdvancedWhitelist.formatColor(whitelistOff!!))
            AdvancedWhitelist.inst?.config?.set("whitelist.enabled", false)
            AdvancedWhitelist.inst?.saveConfig()
            AdvancedWhitelist.inst?.reloadConfig()
        }
    }
}