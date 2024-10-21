package net.davegamer007vinicius1232426.unnamedscpmod.datagen;

import net.davegamer007vinicius1232426.unnamedscpmod.UnnamedSCPMod;
import net.davegamer007vinicius1232426.unnamedscpmod.block.ModBlocks;
import net.davegamer007vinicius1232426.unnamedscpmod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, UnnamedSCPMod.MOD_ID , existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
            this.tag(BlockTags.NEEDS_IRON_TOOL)
                    .add(
                            ModBlocks.TELEKILL_BLOCK.get(),
                            ModBlocks.RAW_TELEKILL_BLOCK.get(),
                            ModBlocks.TELEKILL_ORE.get()
                    );
            this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                    .add(
                            ModBlocks.TELEKILL_BLOCK.get(),
                            ModBlocks.RAW_TELEKILL_BLOCK.get(),
                            ModBlocks.TELEKILL_ORE.get(),
                            ModBlocks.DEEPSLATE_TELEKILL_ORE.get(),
                            ModBlocks.FLOOR_TILES.get()
                    );
            this.tag(ModTags.Blocks.IS_ORE)
                    .add(
                            ModBlocks.TELEKILL_ORE.get(),
                            ModBlocks.DEEPSLATE_TELEKILL_ORE.get()
                    ).addTag(Tags.Blocks.ORES);


        }
}
