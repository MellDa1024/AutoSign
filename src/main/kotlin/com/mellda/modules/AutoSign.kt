package com.mellda.modules

import com.lambda.client.module.Category
import com.lambda.client.plugin.api.PluginModule
import com.lambda.client.util.threads.safeListener
import com.mellda.AutoSignPlugin
import com.mellda.managers.AutoSignManager
import com.lambda.mixin.accessor.gui.AccessorGuiEditSign
import net.minecraft.client.gui.inventory.GuiEditSign
import net.minecraft.util.text.TextComponentString
import net.minecraft.util.text.ITextComponent
import net.minecraft.tileentity.TileEntitySign
import net.minecraftforge.fml.common.gameevent.TickEvent

/**
 * This is a module. First set properties then settings then add listener.
 * **/
internal object AutoSign : PluginModule(
    name = "AutoSign",
    category = Category.MISC,
    description = "Made it to bypass unicode characters.",
    pluginMain = AutoSignPlugin
) {

    val GuiEditSign.tileSign: TileEntitySign get() = (this as AccessorGuiEditSign).tileSign

    init {
        safeListener<TickEvent.ClientTickEvent> {
             val guiScreen = mc.currentScreen
             if (guiScreen != null) {
                 if (guiScreen is GuiEditSign) {
                     val sign = mc.currentScreen as GuiEditSign
                     sign.tileSign.signText[0] = TextComponentString(AutoSignManager.getSign(0)) as ITextComponent
                     sign.tileSign.signText[1] = TextComponentString(AutoSignManager.getSign(1)) as ITextComponent
                     sign.tileSign.signText[2] = TextComponentString(AutoSignManager.getSign(2)) as ITextComponent
                     sign.tileSign.signText[3] = TextComponentString(AutoSignManager.getSign(3)) as ITextComponent
                 }
            }
        }
    }
}