package net.davegamer007vinicius1232426.unnamedscpmod.worldgen;

import net.davegamer007vinicius1232426.unnamedscpmod.UnnamedSCPMod;
import net.davegamer007vinicius1232426.unnamedscpmod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;

import java.util.List;

public class ModConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?,?>> TELEKILL_ORE = registerKey("telekill_ore");


    public static void bootstrap(BootstapContext<ConfiguredFeature<?,?>> context){
        RuleTest diamondOreReplaceable = new BlockMatchTest(Blocks.DIAMOND_ORE);
        RuleTest deepDiamondOreReplaceable = new BlockMatchTest(Blocks.DEEPSLATE_DIAMOND_ORE);


        List<OreConfiguration.TargetBlockState> overworldOres = List.of(
                OreConfiguration.target(diamondOreReplaceable, ModBlocks.TELEKILL_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepDiamondOreReplaceable, ModBlocks.DEEPSLATE_TELEKILL_ORE.get().defaultBlockState()));

        register(context, TELEKILL_ORE, Feature.SCATTERED_ORE, new OreConfiguration(overworldOres, 1));
    }



    public static ResourceKey<ConfiguredFeature<?,?>> registerKey(String name){
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(UnnamedSCPMod.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?,?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?,?>> key,
                                                                                          F feature, FC configuration){
        context.register(key, new ConfiguredFeature<>(feature, configuration));

    }
}
