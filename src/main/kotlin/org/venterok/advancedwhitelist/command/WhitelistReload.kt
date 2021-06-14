package org.venterok.advancedwhitelist.command

import org.bukkit.command.CommandSender
import org.venterok.advancedwhitelist.AdvancedWhitelist

class WhitelistReload {
    fun whReload (sender: CommandSender) {

        val config = AdvancedWhitelist.inst?.config

        val messageReload = config?.getString("message.whitelist-reload")

        AdvancedWhitelist.inst?.reloadConfig()
        sender.sendMessage(AdvancedWhitelist.formatColor(messageReload!!))
    }
}