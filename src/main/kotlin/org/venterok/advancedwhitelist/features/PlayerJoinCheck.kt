package org.venterok.advancedwhitelist.features

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerLoginEvent
import org.venterok.advancedwhitelist.AdvancedWhitelist


class PlayerJoinCheck(m: AdvancedWhitelist) : Listener {

    private val st: AdvancedWhitelist

    @EventHandler
    fun onConnect(e: PlayerLoginEvent) {
        val pl = e.player

        if (!st.getStorage()!!.whitelistEnabled()) return
        if (st.getStorage()!!.isWhitelisted(pl.name)) return

        e.disallow(PlayerLoginEvent.Result.KICK_WHITELIST, st.getStorage()!!.noWhitelistMessage())
    }

    init {
        this.st = m
    }
}