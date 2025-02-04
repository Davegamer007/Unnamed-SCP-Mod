package net.davegamer007vinicius1232426.unnamedscpmod.events;

import net.davegamer007vinicius1232426.unnamedscpmod.UnnamedSCPMod;
import net.davegamer007vinicius1232426.unnamedscpmod.client.ModKeyBindings;
import net.davegamer007vinicius1232426.unnamedscpmod.networking.ModMessages;
import net.davegamer007vinicius1232426.unnamedscpmod.networking.packets.C2S.ChangeEyeStateC2SPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
@Mod.EventBusSubscriber(modid = UnnamedSCPMod.MOD_ID, value = Dist.CLIENT)
public class ModClientEvents {
    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key pEvent){
        if (ModKeyBindings.CLOSE_EYE_BUTTON.consumeClick()){
            ModMessages.sendToServer(new ChangeEyeStateC2SPacket());
        }
    }
}

