package net.davegamer007vinicius1232426.unnamedscpmod.util;

import net.davegamer007vinicius1232426.unnamedscpmod.UnnamedSCPMod;
import net.davegamer007vinicius1232426.unnamedscpmod.item.ModItems;
import net.davegamer007vinicius1232426.unnamedscpmod.item.custom.SCP458;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.renderer.item.ItemPropertyFunction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ModItemProperties {
    public static void setup(FMLClientSetupEvent event) {
            ItemProperties.register(ModItems.SCP458.get(),
                    new ResourceLocation(UnnamedSCPMod.MOD_ID, "number_of_slices"), (stack, level, living, id) -> {
                        if (!(stack.getItem() instanceof SCP458 pPizza)){
                            return 0;
                        } else {
                            return (float) pPizza.SLICES /10;
                        }
                    });
    }
}