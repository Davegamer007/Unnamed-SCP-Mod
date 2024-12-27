package net.davegamer007vinicius1232426.unnamedscpmod.networking.packets.S2C;

import net.davegamer007vinicius1232426.unnamedscpmod.client_hud.BlinkHudData;
import net.davegamer007vinicius1232426.unnamedscpmod.playercapabilities.blinking.PlayerBlinkingProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SyncEyeStateS2CPacket {
    private final int playerBlinkSex;
    private final boolean playerIsOpen;

    public SyncEyeStateS2CPacket(int playerBlinkSex, boolean playerIsOpen){
        this.playerBlinkSex = playerBlinkSex;
        this.playerIsOpen = playerIsOpen;

    }

    public SyncEyeStateS2CPacket(FriendlyByteBuf buf){
        this.playerIsOpen = buf.readBoolean();
        this.playerBlinkSex = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf){
        buf.writeInt(playerBlinkSex);
        buf.writeBoolean(playerIsOpen);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context pContext = supplier.get();
        pContext.enqueueWork(()-> {
            BlinkHudData.setPlayerBlinkSex(playerBlinkSex);
            BlinkHudData.setPlayerIsOpen(playerIsOpen);
        });
            //HERE BE CLIENT
            //every tick set ClientEyes Open except if key is being pressed
            //when key is pressed set ClientEyes to false and send packet to Server
        return true;
    }
}
