package net.davegamer007vinicius1232426.unnamedscpmod.events;


import net.davegamer007vinicius1232426.unnamedscpmod.UnnamedSCPMod;
import net.davegamer007vinicius1232426.unnamedscpmod.particle.ModParticles;
import net.davegamer007vinicius1232426.unnamedscpmod.particle.custom.EternalEmbers;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = UnnamedSCPMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBusEvents {
    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void registerParticleFactories(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ModParticles.ETERNAL_EMBERS.get(), EternalEmbers.Provider:: new);
    }
}
