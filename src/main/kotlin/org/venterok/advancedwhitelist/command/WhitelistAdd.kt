package org.venterok.advancedwhitelist.command

import org.bukkit.command.CommandSender
import org.venterok.advancedwhitelist.AdvancedWhitelist


class WhitelistAdd {
    fun PlayerAdd (player: String, sender: CommandSender) {

        val config = AdvancedWhitelist.inst?.config

        val whitelistedPlayers = config?.getStringList("in-whitelist")

        var playerAlreadyWhitelisted = config?.getString("message.player-already-whitelisted")

        var playerAdded = config?.getString("message.whitelist-add-player")

        playerAdded = playerAdded.toString().replace("%player%", player, true)

        playerAlreadyWhitelisted = playerAlreadyWhitelisted.toString().replace("%player%", player, true)

        if (whitelistedPlayers != null) {
            if (whitelistedPlayers.contains(player)) {
                sender.sendMessage(AdvancedWhitelist.formatColor(playerAlreadyWhitelisted!!))
            }
            else {
                sender.sendMessage(AdvancedWhitelist.formatColor(playerAdded!!))
                whitelistedPlayers.add(player)
                AdvancedWhitelist.inst?.config?.set("in-whitelist", whitelistedPlayers);

                AdvancedWhitelist.configFile?.let { config.save(it) }
                AdvancedWhitelist.inst?.reloadConfig()

            }

        }
        else
            return
    }
}