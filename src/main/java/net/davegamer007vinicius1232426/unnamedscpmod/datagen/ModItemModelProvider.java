package net.davegamer007vinicius1232426.unnamedscpmod.datagen;

import net.davegamer007vinicius1232426.unnamedscpmod.UnnamedSCPMod;
import net.davegamer007vinicius1232426.unnamedscpmod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, UnnamedSCPMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.CLASS_A_AMNESTIC);
        simpleItem(ModItems.RAW_TELEKILL);
        simpleItem(ModItems.SCP310);
        simpleItem(ModItems.SCP999GELLO);
        simpleItem(ModItems.SPC939SPIKE);
        simpleItem(ModItems.TELEKILL);
        simpleItem(ModItems.TELEKILL_UPGRADE_TEMPLATE);
        simpleItem(ModItems.SALT);



        //Bottles from SCP 294
        simpleItem(ModItems.BOTTLE_OF_Y9O9);
        simpleItem(ModItems.BOTTLE_OF_IRON);
        simpleItem(ModItems.BOTTLE_OF_COAL);
        simpleItem(ModItems.BOTTLE_OF_DIAMONDS);
        simpleItem(ModItems.BOTTLE_OF_COPPER);
        simpleItem(ModItems.BOTTLE_OF_GOLD);
        simpleItem(ModItems.BOTTLE_OF_EMERALDS);
        simpleItem(ModItems.BOTTLE_OF_GORE);
        simpleItem(ModItems.BOTTLE_OF_LAPISLAZULI);
        simpleItem(ModItems.BOTTLE_OF_NETHERITE);
        simpleItem(ModItems.BOTTLE_OF_QUARTZ);
        simpleItem(ModItems.BOTTLE_OF_TELEKILL);
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item){
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(UnnamedSCPMod.MOD_ID, "item/" + item.getId().getPath()));
    }
}
