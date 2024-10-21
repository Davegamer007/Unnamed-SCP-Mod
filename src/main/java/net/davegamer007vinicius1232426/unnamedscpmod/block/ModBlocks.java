package net.davegamer007vinicius1232426.unnamedscpmod.block;

import net.davegamer007vinicius1232426.unnamedscpmod.UnnamedSCPMod;
import net.davegamer007vinicius1232426.unnamedscpmod.block.custom.Eternal_Flame_Block;
import net.davegamer007vinicius1232426.unnamedscpmod.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, UnnamedSCPMod.MOD_ID);

    public static final RegistryObject<Block> TELEKILL_BLOCK = registerBlock("telekill_block",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.COPPER)));


    public static final RegistryObject<Block> RAW_TELEKILL_BLOCK = registerBlock("raw_telekill_block",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.COPPER)));
    public static final RegistryObject<Block> TELEKILL_ORE = registerBlock("telekill_ore",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_ORE).sound(SoundType.STONE)));
    public static final RegistryObject<Block> DEEPSLATE_TELEKILL_ORE = registerBlock("deepslate_telekill_ore",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_DIAMOND_ORE).sound(SoundType.STONE)));
    public static final RegistryObject<Block> FLOOR_TILES = registerBlock("floor_tiles",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.QUARTZ_BLOCK).sound(SoundType.STONE)));
    public static final RegistryObject<Block> FLESH_BLOCK = registerBlock("flesh_block",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.HONEY_BLOCK).sound(SoundType.HONEY_BLOCK).destroyTime(200)));
    public static final RegistryObject<Block> FLESH_SPONGE_BLOCK = registerBlock("flesh_sponge_block",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.HONEY_BLOCK).sound(SoundType.HONEY_BLOCK).destroyTime(200)));

    public static final RegistryObject<Block> ETERNAL_FLAME_BLOCK = registerBlock("flesh_sponge_block",
            ()-> new Eternal_Flame_Block(BlockBehaviour.Properties.copy(Blocks.FIRE).strength(-1).pushReaction(PushReaction.DESTROY)));


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends  Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block){
        return ModItems.ITEMS.register(name, ()-> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }

}