package org.venterok.advancedwhitelist.features

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.venterok.advancedwhitelist.AdvancedWhitelist
import org.venterok.advancedwhitelist.AdvancedWhitelist.Companion.formatColor
import org.venterok.advancedwhitelist.utils.WhitelistManager.Companion.config

class WhitelistCommand : CommandExecutor, TabCompleter {
    private val st: AdvancedWhitelist? = null
        override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {

            val dontHavePermission = config.getString(formatColor("message.whitelist-add-player"))!!
    
            if (!sender.hasPermission("AdvancedWhitelist.admin") || !sender.isOp) { sender.sendMessage(formatColor(dontHavePermission))
                return true }

            val checkMessage = config.getStringList("message.player-check")
            val whitelistEnabledMessage = config.getStringList("message.whitelist-enabled")
            val whitelistActionMessage = config.getStringList("message.whitelist-action")

            when (args[0]) {
                 "add" -> {
                     if(!st!!.getStorage()!!.isWhitelisted(args[1])) {
                         st.getStorage()!!.addWhitelist(args[1])
                         sender.sendMessage(formatColor(whitelistActionMessage[0]))
                     }
                     sender.sendMessage(formatColor(whitelistActionMessage[1]))
                 }
                 "remove" -> {
                     if(st!!.getStorage()!!.isWhitelisted(args[1])) {
                         st.getStorage()!!.removeWhitelist(args[1])
                         sender.sendMessage(formatColor(config.getString("message.whitelist-remove-player")!!.replace("%player%", args[1])))
                     }
                     sender.sendMessage(formatColor(config.getString("message.player-not-found")!!.replace("%player%", args[1])))
                 }
                 "on" -> {
                     st!!.getStorage()!!.setWhitelist(true)
                     sender.sendMessage(formatColor(whitelistEnabledMessage[0]))
                 }
                 "off" -> {
                     st!!.getStorage()!!.setWhitelist(false)
                     sender.sendMessage(formatColor(whitelistEnabledMessage[1]))
                 }
                 "reload" -> {
                     st!!.getStorage()!!.reload()
                     sender.sendMessage(formatColor(config.getString("message.whitelist-reload")!!))
                 }
                 "size" -> {
                     val size = st!!.getStorage()!!.whitelist.size
                     sender.sendMessage(formatColor(config.getString("message.whitelist-size")!!.replace("%whsize%", "$size")))
                 }
                 "check" -> {
                    if(st!!.getStorage()!!.isWhitelisted(args[1])) {
                        sender.sendMessage(formatColor(checkMessage[0].replace("%player", args[1])))
                    }
                    sender.sendMessage(formatColor(checkMessage[1].replace("%player", args[1])))
                 }

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
                list.addAll(listOf("add", "remove", "on", "off", "reload", "size", "check"))
                return list
            }
            return null
        }
    }