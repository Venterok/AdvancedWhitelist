package org.venterok.advancedwhitelist.features

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.venterok.advancedwhitelist.AdvancedWhitelist
import org.venterok.advancedwhitelist.AdvancedWhitelist.Companion.formatColor
import org.venterok.advancedwhitelist.utils.WhitelistManager.Companion.config

class WhitelistCommand(st: AdvancedWhitelist) : CommandExecutor, TabCompleter {

    private var st: AdvancedWhitelist

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        val dontHavePermission = config.getString(formatColor("message.dont-have-permission"))!!

        if (!sender.hasPermission("AdvancedWhitelist.admin") || !sender.isOp) { sender.sendMessage(formatColor(dontHavePermission))
            return true }
        commandAction(sender, args)
        return true
    }
    private fun commandAction(sender: CommandSender, args: Array<out String>) {
        val storage = st.getStorage()
        if (storage == null) { println("storage null"); return }

        val checkMessage = config.getStringList("message.player-check")
        val whitelistEnabledMessage = config.getStringList("message.whitelist-enabled")
        val whitelistActionMessage = config.getStringList("message.whitelist-action")


        when (args[0]) {
            "add" -> {
                if(!storage.isWhitelisted(args[1])) {
                    storage.addWhitelist(args[1])
                    sender.sendMessage(formatColor(whitelistActionMessage[0].replace("%player%", args[1])))
                    return
                }
                sender.sendMessage(formatColor(config.getString("message.player-already-whitelisted")!!.replace("%player%", args[1])))
                return
            }
            "remove" -> {
                if(storage.isWhitelisted(args[1])) {
                    storage.removeWhitelist(args[1])
                    sender.sendMessage(formatColor(whitelistActionMessage[1].replace("%player%", args[1])))
                    return
                }
                sender.sendMessage(formatColor(config.getString("message.player-not-found")!!.replace("%player%", args[1])))
                return
            }
            "on" -> {
                storage.setWhitelist(true)
                sender.sendMessage(formatColor(whitelistEnabledMessage[0]))
                return
            }
            "off" -> {
                storage.setWhitelist(false)
                sender.sendMessage(formatColor(whitelistEnabledMessage[1]))
                return
            }
            "reload" -> {
                storage.reload()
                sender.sendMessage(formatColor(config.getString("message.whitelist-reload")!!))
                return
            }
            "size" -> {
                val size = storage.whitelist.size
                sender.sendMessage(formatColor(config.getString("message.whitelist-size")!!.replace("%whsize%", "$size")))
                return
            }
            "check" -> {
                if(storage.isWhitelisted(args[1])) {
                    sender.sendMessage(formatColor(checkMessage[0].replace("%player", args[1])))
                    return
                }
                sender.sendMessage(formatColor(checkMessage[1].replace("%player", args[1])))
                return
            }

        }

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
    init {
        this.st = st
    }
}