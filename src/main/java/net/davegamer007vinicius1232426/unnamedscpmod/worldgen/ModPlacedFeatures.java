package net.davegamer007vinicius1232426.unnamedscpmod.worldgen;

import net.davegamer007vinicius1232426.unnamedscpmod.UnnamedSCPMod;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class ModPlacedFeatures {

    public static final ResourceKey<PlacedFeature> TELEKILL_ORE_PLACED_KEY = registerKey("telekill_ore_placed");



    public static void bootstrap(BootstapContext<PlacedFeature> pContext){
        HolderGetter<ConfiguredFeature<?,?>> configuredFeatures = pContext.lookup(Registries.CONFIGURED_FEATURE);


        register(pContext, TELEKILL_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.TELEKILL_ORE),
                ModOrePlacement.commonOrePlacement(1, HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(30))));
    }



    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(UnnamedSCPMod.MOD_ID, name));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
