package net.davegamer007vinicius1232426.unnamedscpmod.datagen.loot;

import net.davegamer007vinicius1232426.unnamedscpmod.block.ModBlocks;
import net.davegamer007vinicius1232426.unnamedscpmod.item.ModItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.TELEKILL_BLOCK.get());
        this.dropSelf(ModBlocks.RAW_TELEKILL_BLOCK.get());
        this.dropSelf(ModBlocks.FLOOR_TILES.get());
        this.dropSelf(ModBlocks.FLESH_BLOCK.get());
        this.dropSelf(ModBlocks.FLESH_SPONGE_BLOCK.get());

        this.add(ModBlocks.TELEKILL_ORE.get(),
                block -> createOreDrop(ModBlocks.TELEKILL_ORE.get(), ModItems.RAW_TELEKILL.get()));
        this.add(ModBlocks.DEEPSLATE_TELEKILL_ORE.get(),
                block -> createOreDrop(ModBlocks.DEEPSLATE_TELEKILL_ORE.get(), ModItems.RAW_TELEKILL.get()));

        this.add(ModBlocks.SALT_BLOCK.get(),
                block -> createMushroomBlockDrop(ModBlocks.SALT_BLOCK.get(), ModItems.SALT.get()));

        this.add(ModBlocks.SCP458_BLOCK.get(),
                block -> createSingleItemTable(ModItems.SCP458.get()));
    }


    @Override
    protected Iterable<Block> getKnownBlocks(){
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
