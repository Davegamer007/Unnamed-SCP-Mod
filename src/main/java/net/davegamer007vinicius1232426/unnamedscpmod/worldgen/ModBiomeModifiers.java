package net.davegamer007vinicius1232426.unnamedscpmod.worldgen;

import net.davegamer007vinicius1232426.unnamedscpmod.UnnamedSCPMod;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBiomeModifiers {
    public static final ResourceKey<BiomeModifier> ADD_TELEKILL_ORE = registerKey("add_saphire_ore");



    public static void bootstrap(BootstapContext<BiomeModifier> pContext){
        var placedFeatures = pContext.lookup(Registries.PLACED_FEATURE);

        var biomes = pContext.lookup(Registries.BIOME);

        pContext.register(ADD_TELEKILL_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.SPAWNS_COLD_VARIANT_FROGS),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.TELEKILL_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_DECORATION));
    }

    private static ResourceKey<BiomeModifier> registerKey(String name){
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(UnnamedSCPMod.MOD_ID, name));
    }
}
