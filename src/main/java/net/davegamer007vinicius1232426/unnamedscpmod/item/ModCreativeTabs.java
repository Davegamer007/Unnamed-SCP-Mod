package net.davegamer007vinicius1232426.unnamedscpmod.item;

import net.davegamer007vinicius1232426.unnamedscpmod.UnnamedSCPMod;
import net.davegamer007vinicius1232426.unnamedscpmod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, UnnamedSCPMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> MOD_ITEMS = CREATIVE_MODE_TABS.register("mod_items",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.TELEKILL.get()))
                    .title(Component.translatable("creativetab.scp_items_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.SCP310.get());
                        output.accept(ModItems.SCP622.get());
                        output.accept(ModItems.RAW_TELEKILL.get());
                        output.accept(ModItems.TELEKILL.get());
                        output.accept(ModItems.BOTTLE_OF_TELEKILL.get());
                        output.accept(ModItems.TELEKILL_UPGRADE_TEMPLATE.get());
                        output.accept(ModItems.CLASS_A_AMNESTIC.get());
                        output.accept(ModItems.SPC939SPIKE.get());
                        output.accept(ModItems.BOTTLE_OF_Y9O9.get());
                        output.accept(ModItems.SALT.get());
                        output.accept(ModItems.SCP458.get());
                        output.accept(ModItems.SCP268.get());
                        output.accept(ModItems.SCP999GELLO.get());



                        // Mod Blocks
                        output.accept(ModBlocks.TELEKILL_BLOCK.get());
                        output.accept(ModBlocks.RAW_TELEKILL_BLOCK.get());
                        output.accept(ModBlocks.SALT_BLOCK.get());
                    }).build());

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
