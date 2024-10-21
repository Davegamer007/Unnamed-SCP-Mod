package net.davegamer007vinicius1232426.unnamedscpmod.util;

import net.davegamer007vinicius1232426.unnamedscpmod.UnnamedSCPMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> IS_ORE = tag("is_ore");

        private static TagKey<Block> tag(String name){
            return BlockTags.create(new ResourceLocation(UnnamedSCPMod.MOD_ID, name));
        }
    }

    public static class Items {

        public static final TagKey<Item> IS_LIQUID = tag("is_liquid");

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(UnnamedSCPMod.MOD_ID, name));
        }
    }
}
