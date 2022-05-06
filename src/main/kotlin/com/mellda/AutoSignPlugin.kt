package com.mellda

import com.lambda.client.plugin.api.Plugin
import com.mellda.commands.AutoSignCommand
import com.mellda.modules.AutoSign
import com.mellda.managers.AutoSignManager

internal object AutoSignPlugin : Plugin() {

    override fun onLoad() {
        // Load any modules, commands, or HUD elements here
        modules.add(AutoSign)
        commands.add(AutoSignCommand)
        managers.add(AutoSignManager)
    }

    override fun onUnload() {
        // Here you can unregister threads etc...
    }
}