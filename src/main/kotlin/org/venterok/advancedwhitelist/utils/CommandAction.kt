package org.venterok.advancedwhitelist.utils

import org.bukkit.command.CommandSender
import org.venterok.advancedwhitelist.AdvancedWhitelist
import org.venterok.advancedwhitelist.AdvancedWhitelist.Companion.formatColor
import org.venterok.advancedwhitelist.utils.Variables.Companion.config

class CommandAction {
    companion object {
        fun playerAdd (player: String, sender: CommandSender) {

            val whitelistedPlayers = config.getStringList("in-whitelist")
            var playerAlreadyWhitelisted = config.getString("message.player-already-whitelisted")
            var playerAdded = config.getString("message.whitelist-add-player")

            playerAdded = playerAdded.toString().replace("%player%", player, true)
            playerAlreadyWhitelisted = playerAlreadyWhitelisted.toString().replace("%player%", player, true)

            if (whitelistedPlayers.contains(player)) {
                sender.sendMessage(formatColor(playerAlreadyWhitelisted))
            }
            else {
                sender.sendMessage(formatColor(playerAdded))
                whitelistedPlayers.add(player)
                AdvancedWhitelist.inst?.config?.set("in-whitelist", whitelistedPlayers)

                AdvancedWhitelist.configFile?.let { config.save(it) }
                AdvancedWhitelist.inst?.reloadConfig()

            }
        }
        fun whitelistOff (sender: CommandSender) {


            val playerAlreadyWhitelisted = config.getBoolean("whitelist.enabled")
            val whitelistAlreadyOff = config.getString("message.whitelist-already-off")
            val whitelistOff = config.getString("message.whitelist-off")

            if (!playerAlreadyWhitelisted) {
                sender.sendMessage(formatColor(whitelistAlreadyOff!!))
            }
            else {
                sender.sendMessage(formatColor(whitelistOff!!))
                AdvancedWhitelist.inst?.config?.set("whitelist.enabled", false)
                AdvancedWhitelist.inst?.saveConfig()
                AdvancedWhitelist.inst?.reloadConfig()
            }
        }
        fun whitelistOn (sender: CommandSender) {

            val playerAlreadyWhitelisted = config.getBoolean("whitelist.enabled")
            val whitelistAlreadyOn = config.getString("message.whitelist-already-on")
            val whitelistOn = config.getString("message.whitelist-on")

            if (playerAlreadyWhitelisted) {
                sender.sendMessage(formatColor(whitelistAlreadyOn!!))
            }
            else {
                sender.sendMessage(formatColor(whitelistOn!!))
                AdvancedWhitelist.inst?.config?.set("whitelist.enabled", true)
                AdvancedWhitelist.inst?.saveConfig()
                AdvancedWhitelist.inst?.reloadConfig()
            }
        }
        fun whitelistReload (sender: CommandSender) {

            val messageReload = config.getString("message.whitelist-reload")

            AdvancedWhitelist.inst?.reloadConfig()
            sender.sendMessage(formatColor(messageReload!!))
        }
        fun playerRemove (player: String, sender: CommandSender) {

            val whitelistedPlayers = config.getStringList("in-whitelist")
            var playerNotFoundMessage = config.getString("message.player-not-found")
            var playerRemoved = config.getString("message.whitelist-remove-player")

            playerNotFoundMessage = playerNotFoundMessage.toString().replace("%player%", player, true)

            playerRemoved = playerRemoved.toString().replace("%player%", player, true)

            if (whitelistedPlayers.contains(player)) {
                sender.sendMessage(formatColor(playerRemoved))
                whitelistedPlayers.remove(player)
                AdvancedWhitelist.inst?.config?.set("in-whitelist", whitelistedPlayers)
                AdvancedWhitelist.inst?.saveConfig()
                AdvancedWhitelist.inst?.reloadConfig()
            }
            else
                sender.sendMessage(formatColor(playerNotFoundMessage))

        }
        fun whitelistSize (sender: CommandSender) {

            val whitelistedPlayers = config.getStringList("in-whitelist")

            var whitelistSizeMessage = config.getString("message.whitelist-size")

            whitelistSizeMessage = whitelistSizeMessage.toString().replace("%whsize%", whitelistedPlayers.size.toString(), true)
            sender.sendMessage(formatColor(whitelistSizeMessage))
        }
    }
}