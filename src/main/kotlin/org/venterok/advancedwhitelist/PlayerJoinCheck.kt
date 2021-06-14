package org.venterok.advancedwhitelist

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerPreLoginEvent

class PlayerJoinCheck : Listener {
    @EventHandler
    fun JoinCheck (e: AsyncPlayerPreLoginEvent) {

        val config = AdvancedWhitelist.inst?.config

        val whitelistEnabled = config?.getBoolean("whitelist.enabled")

        if (whitelistEnabled == false) return

        val joinedPlayer = e.name

        val whitelistedPlayers = config?.getList("in-whitelist")

        val noWhitelistMessage = config?.getString("message.join-no-whitelist") ?: return

        if (whitelistedPlayers != null) {
            if (whitelistedPlayers.contains(joinedPlayer)) return
            e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, noWhitelistMessage)
        }
        else
            return

    }
}