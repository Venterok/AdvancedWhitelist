package org.venterok.advancedwhitelist.command

import org.bukkit.command.CommandSender
import org.venterok.advancedwhitelist.AdvancedWhitelist

class WhitelistRemove {
    fun PlayerRemove (player: String, sender: CommandSender) {

        val config = AdvancedWhitelist.inst?.config

        val whitelistedPlayers = config?.getStringList("in-whitelist")

        var playerNotFoundMessage = config?.getString("message.player-not-found")

        var playerRemoved = config?.getString("message.whitelist-remove-player")

        playerNotFoundMessage = playerNotFoundMessage.toString().replace("%player%", player, true)

        playerRemoved = playerRemoved.toString().replace("%player%", player, true)

        if (whitelistedPlayers != null) {
            if (whitelistedPlayers.contains(player)) {
                sender.sendMessage(AdvancedWhitelist.formatColor(playerRemoved!!))
                whitelistedPlayers.remove(player)
                AdvancedWhitelist.inst?.config?.set("in-whitelist", whitelistedPlayers);
                AdvancedWhitelist.inst?.saveConfig()
                AdvancedWhitelist.inst?.reloadConfig()
            }
            else
                sender.sendMessage(AdvancedWhitelist.formatColor(playerNotFoundMessage!!))
        }
        else
            return

    }
}