package net.davegamer007vinicius1232426.unnamedscpmod.networking.packets.C2S;

import net.davegamer007vinicius1232426.unnamedscpmod.networking.ModMessages;
import net.davegamer007vinicius1232426.unnamedscpmod.networking.packets.S2C.SyncEyeStateS2CPacket;
import net.davegamer007vinicius1232426.unnamedscpmod.playercapabilities.blinking.PlayerBlinkingProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ChangeEyeStateC2SPacket {
    public ChangeEyeStateC2SPacket(){

    }

    public ChangeEyeStateC2SPacket(FriendlyByteBuf buf){

    }

    public void toBytes(FriendlyByteBuf buf){

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context pContext = supplier.get();
        pContext.enqueueWork(()-> {
            ServerPlayer player = pContext.getSender();
            player.getCapability(PlayerBlinkingProvider.PLAYER_BLINKING).ifPresent(playerBlinking -> {
                playerBlinking.switchEyeState();
                ModMessages.sendToPlayer(new SyncEyeStateS2CPacket(playerBlinking.getBlinkSex(), playerBlinking.areEyesOpen()), player);



            });
            //HERE BE SERVER
            //if player eyes are open {
            //Make the server stop and reset the blink timer
            //Set player eyes to closed
            // } else {
            //Start Blink timer
            //Set Player eyes to open
            //}

            //HERE BE CLIENT
            //every tick set ClientEyes Open except if key is being pressed
            //when key is pressed set ClientEyes to false and send packet to Server

        });
        return true;
    }
}
