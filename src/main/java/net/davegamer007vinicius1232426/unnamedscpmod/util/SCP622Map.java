package net.davegamer007vinicius1232426.unnamedscpmod.util;

import net.davegamer007vinicius1232426.unnamedscpmod.block.ModBlocks;
import net.davegamer007vinicius1232426.unnamedscpmod.item.ModItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import javax.swing.text.html.parser.Entity;
import java.util.HashMap;
import java.util.Map;

public class SCP622Map {

    public static BlockState dryMap(BlockState pInput){
        Map<BlockState, BlockState> mapSCP622 = new HashMap<>();

        mapSCP622.put(Blocks.GRASS_BLOCK.defaultBlockState(), Blocks.DIRT.defaultBlockState());
        mapSCP622.put(Blocks.DIRT.defaultBlockState(), Blocks.COARSE_DIRT.defaultBlockState());
        mapSCP622.put(Blocks.COARSE_DIRT.defaultBlockState(), Blocks.GRAVEL.defaultBlockState());
        mapSCP622.put(Blocks.GRAVEL.defaultBlockState(), Blocks.SAND.defaultBlockState());

        mapSCP622.put(Blocks.BLUE_ICE.defaultBlockState(), Blocks.PACKED_ICE.defaultBlockState());
        mapSCP622.put(Blocks.PACKED_ICE.defaultBlockState(), Blocks.ICE.defaultBlockState());

        mapSCP622.put(Blocks.MUD.defaultBlockState(), Blocks.CLAY.defaultBlockState());
        mapSCP622.put(Blocks.CLAY.defaultBlockState(), Blocks.TERRACOTTA.defaultBlockState());
        mapSCP622.put(Blocks.TERRACOTTA.defaultBlockState(), Blocks.RED_SAND.defaultBlockState());

        mapSCP622.put(ModBlocks.FLESH_BLOCK.get().defaultBlockState(), Blocks.AIR.defaultBlockState());
        mapSCP622.put(ModBlocks.FLESH_SPONGE_BLOCK.get().defaultBlockState(), Blocks.AIR.defaultBlockState());
        mapSCP622.put(Blocks.SNOW.defaultBlockState(), Blocks.AIR.defaultBlockState());
        mapSCP622.put(Blocks.SNOW_BLOCK.defaultBlockState(), Blocks.AIR.defaultBlockState());
        mapSCP622.put(Blocks.ICE.defaultBlockState(), Blocks.AIR.defaultBlockState());

        return mapSCP622.get(pInput);
    }
}
