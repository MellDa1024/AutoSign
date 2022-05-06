package com.mellda.commands

import com.lambda.client.command.ClientCommand
import com.lambda.client.util.text.MessageSendHelper
import com.mellda.managers.AutoSignManager

object AutoSignCommand : ClientCommand(
    name = "autosign",
    description = "modifies autosign"
) {
    init {
        literal("set") {
            int("signoffset") { signoffset ->
                string("text") { text ->
                    execute("modify autosign") {
                        if ((0 < signoffset.value) && (signoffset.value < 5)) {
                            AutoSignManager.changeSign(signoffset.value, text.value)
                            MessageSendHelper.sendChatMessage("Set line ${signoffset.value} to ${text.value}")
                        }
                        else {
                            MessageSendHelper.sendWarningMessage("Set your signoffset value to 1~4.")
                        }
                    }
                }
            }
        }
    }
}