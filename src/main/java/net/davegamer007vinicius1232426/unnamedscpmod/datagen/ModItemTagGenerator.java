package net.davegamer007vinicius1232426.unnamedscpmod.datagen;

import net.davegamer007vinicius1232426.unnamedscpmod.UnnamedSCPMod;
import net.davegamer007vinicius1232426.unnamedscpmod.item.ModItems;
import net.davegamer007vinicius1232426.unnamedscpmod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {
    public ModItemTagGenerator(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_, CompletableFuture<TagLookup<Block>> p_275322_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_275343_, p_275729_, p_275322_, UnnamedSCPMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(ModTags.Items.IS_LIQUID)
                .add(
                        ModItems.BOTTLE_OF_COAL.get(),
                        ModItems.BOTTLE_OF_COPPER.get(),
                        ModItems.BOTTLE_OF_DIAMONDS.get(),
                        ModItems.BOTTLE_OF_EMERALDS.get(),
                        ModItems.BOTTLE_OF_Y9O9.get(),
                        ModItems.BOTTLE_OF_IRON.get(),
                        ModItems.BOTTLE_OF_COAL.get(),
                        ModItems.BOTTLE_OF_GORE.get(),
                        ModItems.BOTTLE_OF_LAPISLAZULI.get(),
                        ModItems.BOTTLE_OF_NETHERITE.get(),
                        ModItems.BOTTLE_OF_GOLD.get(),
                        ModItems.BOTTLE_OF_TELEKILL.get()
                );

        this.tag(ModTags.Items.SWEET).add(
                Items.SUGAR,
                Items.CAKE,
                Items.COOKIE,
                Items.SUGAR_CANE

        );
    }
}

