package net.davegamer007vinicius1232426.unnamedscpmod.datagen;

import net.davegamer007vinicius1232426.unnamedscpmod.UnnamedSCPMod;
import net.davegamer007vinicius1232426.unnamedscpmod.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;


public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, UnnamedSCPMod.MOD_ID ,exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.TELEKILL_BLOCK);
        blockWithItem(ModBlocks.RAW_TELEKILL_BLOCK);
        blockWithItem(ModBlocks.TELEKILL_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_TELEKILL_ORE);
        blockWithItem(ModBlocks.FLOOR_TILES);
        blockWithItem(ModBlocks.FLESH_BLOCK);
        blockWithItem(ModBlocks.FLESH_SPONGE_BLOCK);

        simpleBlock(ModBlocks.ETERNAL_FLAME_BLOCK.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/eternal_flame_block")));


    }
    private void blockWithItem(RegistryObject<Block> blockRegistryObject){
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
