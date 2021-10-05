package org.venterok.advancedwhitelist.features

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerPreLoginEvent
import org.venterok.advancedwhitelist.utils.Variables.Companion.config

class PlayerJoinCheck : Listener {
    @EventHandler
    fun joinCheck (e: AsyncPlayerPreLoginEvent) {

        if (!config.getBoolean("whitelist.enabled")) return

        val joinedPlayer = e.name
        val whitelistedPlayers = config.getList("in-whitelist") ?: return
        val noWhitelistMessage = config.getString("message.join-no-whitelist")!!

        if (whitelistedPlayers.contains(joinedPlayer)) return
        e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, noWhitelistMessage)

    }
}