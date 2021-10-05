package org.venterok.advancedwhitelist.features

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.venterok.advancedwhitelist.AdvancedWhitelist.Companion.formatColor
import org.venterok.advancedwhitelist.utils.CommandAction.Companion.playerAdd
import org.venterok.advancedwhitelist.utils.CommandAction.Companion.playerRemove
import org.venterok.advancedwhitelist.utils.CommandAction.Companion.whitelistOff
import org.venterok.advancedwhitelist.utils.CommandAction.Companion.whitelistOn
import org.venterok.advancedwhitelist.utils.CommandAction.Companion.whitelistReload
import org.venterok.advancedwhitelist.utils.CommandAction.Companion.whitelistSize
import org.venterok.advancedwhitelist.utils.Variables.Companion.config

class WhitelistCommand() : CommandExecutor, TabCompleter {
        override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {

            val dontHavePermission = config.getString("message.whitelist-add-player")!!
    
            if (!sender.hasPermission("AdvancedWhitelist.admin") || !sender.isOp) { sender.sendMessage(formatColor(dontHavePermission))
                return true }

            when (args[0]) {
                 "add" -> playerAdd(args[1], sender)
                 "remove" -> playerRemove(args[1], sender)
                 "on" -> whitelistOn(sender)
                 "off" -> whitelistOff(sender)
                 "reload" -> whitelistReload(sender)
                 "size" -> whitelistSize(sender)
            }
            return true
        }
    
        override fun onTabComplete(
            sender: CommandSender,
            command: Command,
            alias: String,
            args: Array<out String>
        ): MutableList<String>? {
            if(args.size < 2){
                val list = mutableListOf<String>()
                list.addAll(listOf("add", "remove", "on", "off", "reload", "size"))
                return list
            }
            return null
        }
    }