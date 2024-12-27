package net.davegamer007vinicius1232426.unnamedscpmod.events;

import net.davegamer007vinicius1232426.unnamedscpmod.UnnamedSCPMod;
import net.davegamer007vinicius1232426.unnamedscpmod.client.ModKeyBindings;
import net.davegamer007vinicius1232426.unnamedscpmod.client_hud.BlinkHudOverlay;
import net.davegamer007vinicius1232426.unnamedscpmod.entity.client.ModModelLayers;
import net.davegamer007vinicius1232426.unnamedscpmod.entity.client.modelsAnimationsEtc.scp999.SCP999_Model;
import net.davegamer007vinicius1232426.unnamedscpmod.particle.ModParticles;
import net.davegamer007vinicius1232426.unnamedscpmod.particle.custom.EternalEmbers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = UnnamedSCPMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {
    @SubscribeEvent
    public static void registerParticleFactories(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ModParticles.ETERNAL_EMBERS.get(), EternalEmbers.Provider:: new);
    }

    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions pEvent){
        pEvent.registerLayerDefinition(ModModelLayers.SCP_999_LAYER, SCP999_Model::createBodyLayer);
    }

    @SubscribeEvent
    public static void onKeyRegister(RegisterKeyMappingsEvent pEvent){
        pEvent.register(ModKeyBindings.CLOSE_EYE_BUTTON);
        pEvent.register(ModKeyBindings.HOLD_EYE_BUTTON);
    }

    @SubscribeEvent
    public static void hudRegister(RegisterGuiOverlaysEvent pEvent){
        pEvent.registerAboveAll("blink", BlinkHudOverlay.BLINK_HUD);
    }
}
