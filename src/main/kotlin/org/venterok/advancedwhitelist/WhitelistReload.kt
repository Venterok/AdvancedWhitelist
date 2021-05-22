package org.venterok.advancedwhitelist

import org.bukkit.command.CommandSender

class WhitelistReload {
    fun whReload (sender: CommandSender) {
        val messageReload = AdvancedWhitelist.inst?.config?.getString("message.whitelist-reload")
        AdvancedWhitelist.inst?.reloadConfig()
        sender.sendMessage(AdvancedWhitelist.formatColor(messageReload!!))
    }
}