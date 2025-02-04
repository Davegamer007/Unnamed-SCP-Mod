package net.davegamer007vinicius1232426.unnamedscpmod.networking;

import net.davegamer007vinicius1232426.unnamedscpmod.UnnamedSCPMod;
import net.davegamer007vinicius1232426.unnamedscpmod.networking.packets.C2S.ChangeEyeStateC2SPacket;
import net.davegamer007vinicius1232426.unnamedscpmod.networking.packets.S2C.SyncEyeStateS2CPacket;
import net.davegamer007vinicius1232426.unnamedscpmod.playercapabilities.blinking.PlayerBlinking;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModMessages {
    private static SimpleChannel INSTANCE;
    private PlayerBlinking blinking = null;
    private static int packetId = 0;
    private static int id(){
        return packetId++;
    }

    public static void register(){
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(UnnamedSCPMod.MOD_ID, "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s-> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        //C2S
        net.messageBuilder(ChangeEyeStateC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(ChangeEyeStateC2SPacket::new)
                .encoder(ChangeEyeStateC2SPacket::toBytes)
                .consumerMainThread(ChangeEyeStateC2SPacket::handle)
                .add();


        //S2C
        net.messageBuilder(SyncEyeStateS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(SyncEyeStateS2CPacket::new)
                .encoder(SyncEyeStateS2CPacket::toBytes)
                .consumerMainThread(SyncEyeStateS2CPacket::handle)
                .add();

        INSTANCE = net;
    }

    public static <MSG> void sendToServer(MSG message){
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player){
        INSTANCE.send(PacketDistributor.PLAYER.with(()-> player), message);
    }
}
