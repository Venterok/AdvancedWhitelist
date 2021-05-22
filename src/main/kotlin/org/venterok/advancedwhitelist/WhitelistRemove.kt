package org.venterok.advancedwhitelist

import org.bukkit.command.CommandSender

class WhitelistRemove {
    fun PlayerRemove (player: String, sender: CommandSender) {
        val whitelistedPlayers = AdvancedWhitelist.inst?.config?.getStringList("in-whitelist")
        var playerNotFoundMessage = AdvancedWhitelist.inst?.config?.getString("message.player-not-found")
        var playerRemoved = AdvancedWhitelist.inst?.config?.getString("message.whitelist-remove-player")
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