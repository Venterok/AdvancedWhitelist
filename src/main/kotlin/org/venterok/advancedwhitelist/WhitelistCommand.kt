package org.venterok.advancedwhitelist

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter

class WhitelistCommand() : CommandExecutor, TabCompleter {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        val dontHavePermission = AdvancedWhitelist.inst?.config?.getString("message.whitelist-add-player")
        if (!sender.hasPermission("AdvancedWhitelist.admin")) sender.sendMessage(AdvancedWhitelist.formatColor(dontHavePermission!!))
        val funArg = args[0]
        when (funArg) {
             "add" -> org.venterok.advancedwhitelist.WhitelistAdd().PlayerAdd(args[1], sender)
             "remove" -> org.venterok.advancedwhitelist.WhitelistRemove().PlayerRemove(args[1], sender)
             "on" -> org.venterok.advancedwhitelist.WhitelistOn().whOn(sender)
             "off" -> org.venterok.advancedwhitelist.WhitelistOff().whOff(sender)
             "reload" -> org.venterok.advancedwhitelist.WhitelistReload().whReload(sender)
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
            list.addAll(listOf("add", "remove", "on", "off", "reload"))
            return list
        }
        return null
    }
}