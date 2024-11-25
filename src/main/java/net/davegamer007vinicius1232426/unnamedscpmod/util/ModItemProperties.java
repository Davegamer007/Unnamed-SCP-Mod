package net.davegamer007vinicius1232426.unnamedscpmod.util;

import net.davegamer007vinicius1232426.unnamedscpmod.UnnamedSCPMod;
import net.davegamer007vinicius1232426.unnamedscpmod.item.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ModItemProperties {
    public static void setup(FMLClientSetupEvent event){

        event.enqueueWork(() ->
        {
            net.minecraft.client.renderer.item.ItemProperties.register(ModItems.SCP458.get(),
                    new ResourceLocation(UnnamedSCPMod.MOD_ID, "number_of_slices"), (stack, level, living, id) -> {
                        return stack.getOrCreateTag().getInt("number_of_slices");
                    });
        });

        event.enqueueWork(() ->
        {
            net.minecraft.client.renderer.item.ItemProperties.register(ModItems.THALASSOREX.get(),
                    new ResourceLocation(UnnamedSCPMod.MOD_ID, "use_mode"), (stack, level, living, id) -> {
                        return stack.getOrCreateTag().getInt("use_mode");
                    });
        });
    }
}
