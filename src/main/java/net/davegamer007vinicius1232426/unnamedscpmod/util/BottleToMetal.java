package net.davegamer007vinicius1232426.unnamedscpmod.util;

import net.davegamer007vinicius1232426.unnamedscpmod.item.ModItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class BottleToMetal {

    public static Item map(Item pInput) {
        Map<Item, Item> bottleMap = new HashMap<>();

        bottleMap.put(ModItems.BOTTLE_OF_COAL.get(), Items.COAL);
        bottleMap.put(ModItems.BOTTLE_OF_COPPER.get(), Items.COPPER_INGOT);
        bottleMap.put(ModItems.BOTTLE_OF_DIAMONDS.get(), Items.DIAMOND);
        bottleMap.put(ModItems.BOTTLE_OF_IRON.get(), Items.IRON_INGOT);
        bottleMap.put(ModItems.BOTTLE_OF_LAPISLAZULI.get(), Items.LAPIS_LAZULI);
        bottleMap.put(ModItems.BOTTLE_OF_GOLD.get(), Items.GOLD_INGOT);
        bottleMap.put(ModItems.BOTTLE_OF_NETHERITE.get(), Items.NETHERITE_INGOT);
        bottleMap.put(ModItems.BOTTLE_OF_EMERALDS.get(), Items.EMERALD);
        bottleMap.put(ModItems.BOTTLE_OF_QUARTZ.get(), Items.QUARTZ);
        bottleMap.put(ModItems.BOTTLE_OF_TELEKILL.get(), ModItems.TELEKILL.get());
        bottleMap.put(Items.LAVA_BUCKET, Items.OBSIDIAN);
        bottleMap.put(Items.WATER_BUCKET, Items.ICE);

            return bottleMap.get(pInput);
    }

    public static Item returnMap(Item pInput) {
        Map<Item, Item> returnMap = new HashMap<>();

        returnMap.put(Items.LAVA_BUCKET, Items.BUCKET);
        returnMap.put(Items.WATER_BUCKET, Items.BUCKET);

        return returnMap.get(pInput);
    }
}

