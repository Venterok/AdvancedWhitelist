package org.venterok.advancedwhitelist

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerPreLoginEvent
import kotlin.random.Random

class PlayerJoinCheck : Listener {
    @EventHandler
    fun JoinCheck (e: AsyncPlayerPreLoginEvent) {
        val whitelistEnabled = AdvancedWhitelist.inst?.config?.getBoolean("whitelist.enabled")
        if (whitelistEnabled == false) return
        val joinedPlayer = e.name
        val whitelistedPlayers = AdvancedWhitelist.inst?.config?.getStringList("in-whitelist")
        val noWhitelistMessage = AdvancedWhitelist.inst?.config?.getString("message.join-no-whitelist") as String
        if (whitelistedPlayers != null) {
            if (whitelistedPlayers.contains(joinedPlayer)) return
            e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, noWhitelistMessage)
        }
        else
            return

    }
}