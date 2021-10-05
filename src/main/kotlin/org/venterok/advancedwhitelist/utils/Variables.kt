package org.venterok.advancedwhitelist.utils

import org.venterok.advancedwhitelist.AdvancedWhitelist

class Variables {
    companion object {
        val config = AdvancedWhitelist.inst?.config!!
    }
}