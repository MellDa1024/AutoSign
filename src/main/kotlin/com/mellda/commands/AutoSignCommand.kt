package com.mellda.commands

import com.lambda.client.command.ClientCommand
import com.lambda.client.util.text.MessageSendHelper
import com.mellda.managers.AutoSignManager

object AutoSignCommand : ClientCommand(
    name = "autosign",
    description = "modifies autosign"
) {
    init {
        literal("get") {
            int("signoffset") { signoffset ->
                execute("print line's message to chat.") {
                    if ((0 < signoffset.value) && (signoffset.value < 5)) {
                        MessageSendHelper.sendChatMessage("line ${signoffset.value} : ${AutoSignManager.getSign(signoffset.value)}")
                    }
                    else {
                        MessageSendHelper.sendWarningMessage("Set your signoffset value to 1~4.")
                    }
                }
            }
        }
        literal("set") {
            int("signoffset") { signoffset ->
                string("text") { text ->
                    execute("set line to message.") {
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
        literal("add") {
            int("signoffset") { signoffset ->
                string("text") { text ->
                    execute("add message to line") {
                        if ((0 < signoffset.value) && (signoffset.value < 5)) {
                            AutoSignManager.addSign(signoffset.value, text.value)
                            MessageSendHelper.sendChatMessage("Add ${text.value} in line ${signoffset.value}")
                        }
                        else {
                            MessageSendHelper.sendWarningMessage("Set your signoffset value to 1~4.")
                        }
                    }
                }
            }
        }
        literal("slice") {
            int("signoffset") { signoffset ->
                int("startoffset") { startoffset ->
                    int("endoffset") { endoffset ->
                        execute("slices selected line's message.") {
                            if ((0 < signoffset.value) && (signoffset.value < 5)) {
                                if ((startoffset.value > 0) && (signoffset.value <= AutoSignManager.getSign(signoffset.value).length)) {
                                    if ((endoffset.value > 0) && (endoffset.value <= AutoSignManager.getSign(signoffset.value).length) && (endoffset.value >= startoffset.value)){
                                        AutoSignManager.sliceSign(signoffset.value, startoffset.value, endoffset.value)
                                        MessageSendHelper.sendChatMessage("Sliced ${startoffset.value}:${endoffset.value} in line ${signoffset.value}, Text : ${AutoSignManager.getSign(signoffset.value)}")
                                    }
                                    else {
                                        MessageSendHelper.sendWarningMessage("endoffset's value is out of range.")
                                    }
                                }
                                else {
                                    MessageSendHelper.sendWarningMessage("startoffset's value is out of range.")
                                }
                            }
                            else {
                                MessageSendHelper.sendWarningMessage("Set your signoffset value to 1~4.")
                            }
                        }
                    }
                }
            }
        }
        literal("move") {
            int("signoffset") { signoffset ->
                int("moveoffset") { moveoffset ->
                    execute("moves the msg to next line.") {
                        if ((0 < signoffset.value) && (signoffset.value < 4)) {
                            if (AutoSignManager.getSign(signoffset.value) != ""){
                                if ((moveoffset.value > 1) && (moveoffset.value < AutoSignManager.getSign(signoffset.value).length)) {
                                    AutoSignManager.moveSign(signoffset.value, moveoffset.value)
                                    MessageSendHelper.sendChatMessage("Message moved Successfully.")
                                } else {
                                    MessageSendHelper.sendWarningMessage("moveoffset's value is out of range.")
                                }
                            } else {
                                MessageSendHelper.sendWarningMessage("line ${signoffset.value} doesn't have msg.")
                            }
                        }
                        else {
                            MessageSendHelper.sendWarningMessage("Set your signoffset value to 1~3.")
                        }
                    }
                }
            }
        }
        literal("steal") {
            int("signoffset") { signoffset ->
                executeSafe("Steals the name of the item that you're holding.") {
                    if ((0 < signoffset.value) && (signoffset.value < 5)) {
                        if (!player.inventory.getCurrentItem().isEmpty) {
                            val text = player.inventory.getCurrentItem().displayName
                            AutoSignManager.changeSign(signoffset.value, text)
                            MessageSendHelper.sendChatMessage("Set line ${signoffset.value} to $text")
                        }
                        else {
                            MessageSendHelper.sendWarningMessage("Hold item that you want to steal the name.")
                        }
                    }
                    else {
                        MessageSendHelper.sendWarningMessage("Set your signoffset value to 1~4.")
                    }
                }
            }
        }
        literal("reset") {
            string("confirm") { confirmmsg ->
                execute("Resets saved sign msg") {
                    if (confirmmsg.value == "confirm"){
                        AutoSignManager.changeSign(1, "")
                        AutoSignManager.changeSign(2, "")
                        AutoSignManager.changeSign(3, "")
                        AutoSignManager.changeSign(4, "")
                        MessageSendHelper.sendChatMessage("Saved message has been reseted.")
                    } else {
                        MessageSendHelper.sendWarningMessage("please type 'confirm' in args.")
                    }
                }
            }
        }
    }
}