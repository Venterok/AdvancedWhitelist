package org.venterok.advancedwhitelist.command

import org.bukkit.command.CommandSender
import org.venterok.advancedwhitelist.AdvancedWhitelist

class WhitelistSize  {
    fun WhSize (sender: CommandSender) {

        val config = AdvancedWhitelist.inst?.config

        val whitelistedPlayers = config?.getStringList("in-whitelist")

        val whitelistSize = whitelistedPlayers?.size?.toString()

        var whitelistSizeMessage = config?.getString("message.whitelist-size")

        whitelistSizeMessage = whitelistSize?.let { whitelistSizeMessage.toString().replace("%whsize%", it, true) }
        sender.sendMessage(AdvancedWhitelist.formatColor(whitelistSizeMessage!!))
    }
}