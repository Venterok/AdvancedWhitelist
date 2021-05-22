package org.venterok.advancedwhitelist

import org.bukkit.command.CommandSender

class WhitelistOff {
    fun whOff (sender: CommandSender) {
        val playerAlreadyWhitelisted = AdvancedWhitelist.inst?.config?.getBoolean("whitelist.enabled")
        val whitelistAlreadyOff = AdvancedWhitelist.inst?.config?.getString("message.whitelist-already-off")
        val whitelistOff = AdvancedWhitelist.inst?.config?.getString("message.whitelist-off")
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