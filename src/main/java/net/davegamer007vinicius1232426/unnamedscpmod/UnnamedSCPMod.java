package net.davegamer007vinicius1232426.unnamedscpmod;

import com.mojang.logging.LogUtils;
import net.davegamer007vinicius1232426.unnamedscpmod.block.ModBlocks;
import net.davegamer007vinicius1232426.unnamedscpmod.effect.ModEffects;
import net.davegamer007vinicius1232426.unnamedscpmod.entity.ModEntities;
import net.davegamer007vinicius1232426.unnamedscpmod.events.ModEvents;
import net.davegamer007vinicius1232426.unnamedscpmod.item.ModCreativeTabs;
import net.davegamer007vinicius1232426.unnamedscpmod.item.ModItems;
import net.davegamer007vinicius1232426.unnamedscpmod.particle.ModParticles;
import net.davegamer007vinicius1232426.unnamedscpmod.util.BottleToMetal;
import net.davegamer007vinicius1232426.unnamedscpmod.util.ModItemProperties;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.CreativeModeTabRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(UnnamedSCPMod.MOD_ID)
public class UnnamedSCPMod
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "unnamedscpmod";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public UnnamedSCPMod(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModEffects.register(modEventBus);
        ModCreativeTabs.register(modEventBus);
        ModParticles.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        modEventBus.addListener(this::addCreative);
        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS){
            event.accept(ModItems.BOTTLE_OF_COAL);
            event.accept(ModItems.BOTTLE_OF_COPPER);
            event.accept(ModItems.BOTTLE_OF_DIAMONDS);
            event.accept(ModItems.BOTTLE_OF_GOLD);
            event.accept(ModItems.BOTTLE_OF_IRON);
            event.accept(ModItems.BOTTLE_OF_LAPISLAZULI);
            event.accept(ModItems.BOTTLE_OF_QUARTZ);
            event.accept(ModItems.BOTTLE_OF_EMERALDS);
            event.accept(ModItems.BOTTLE_OF_NETHERITE);
        }

        if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS){
            event.accept(ModItems.BOTTLE_OF_GORE);
        }

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.ETERNAL_FLAME_BLOCK.get(), RenderType.cutout());
            ModItemProperties.setup(event);
        }
    }
}
