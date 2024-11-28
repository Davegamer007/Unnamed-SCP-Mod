package net.davegamer007vinicius1232426.unnamedscpmod.item;

import net.davegamer007vinicius1232426.unnamedscpmod.UnnamedSCPMod;
import net.davegamer007vinicius1232426.unnamedscpmod.item.custom.SCP268;
import net.davegamer007vinicius1232426.unnamedscpmod.item.custom.SCP310Item;
import net.davegamer007vinicius1232426.unnamedscpmod.item.custom.SCP458;
import net.davegamer007vinicius1232426.unnamedscpmod.item.custom.SCP622;
import net.davegamer007vinicius1232426.unnamedscpmod.item.custom.abstracts.ModArmorMaterials;
import net.davegamer007vinicius1232426.unnamedscpmod.item.custom.easterEggs.ThalassoRex;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, UnnamedSCPMod.MOD_ID);

    public static final RegistryObject<Item> TELEKILL = ITEMS.register("telekill",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TELEKILL_UPGRADE_TEMPLATE = ITEMS.register("telekill_upgrade_template",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_TELEKILL = ITEMS.register("raw_telekill",
            ()-> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SCP310 = ITEMS.register("scp-310_eternal_flame",
            ()-> new SCP310Item(new Item.Properties().fireResistant().rarity(Rarity.EPIC).stacksTo(1)));
    public static final RegistryObject<Item> SCP622 = ITEMS.register("scp622",
            ()-> new SCP622(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> SCP458 = ITEMS.register("scp458",
            ()-> new SCP458(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> SCP268 = ITEMS.register("scp268",
            ()-> new SCP268(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC)));


    public static final RegistryObject<Item> CLASS_A_AMNESTIC = ITEMS.register("class-a_amnestic",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SPC939SPIKE = ITEMS.register("scp939_spike",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SCP999GELLO = ITEMS.register("scp999_gello",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SALT = ITEMS.register("salt",
            ()-> new Item(new Item.Properties()));











    public static final RegistryObject<Item> BOTTLE_OF_Y9O9 = ITEMS.register("scp3000_y9o9",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BOTTLE_OF_IRON = ITEMS.register("bottle_of_iron",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BOTTLE_OF_DIAMONDS = ITEMS.register("bottle_of_diamonds",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BOTTLE_OF_COPPER = ITEMS.register("bottle_of_copper",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BOTTLE_OF_LAPISLAZULI = ITEMS.register("bottle_of_lapislazuli",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BOTTLE_OF_QUARTZ = ITEMS.register("bottle_of_quartz",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BOTTLE_OF_GOLD = ITEMS.register("bottle_of_gold",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BOTTLE_OF_COAL = ITEMS.register("bottle_of_coal",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BOTTLE_OF_NETHERITE = ITEMS.register("bottle_of_netherite",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BOTTLE_OF_EMERALDS = ITEMS.register("bottle_of_emeralds",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BOTTLE_OF_TELEKILL = ITEMS.register("bottle_of_telekill",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BOTTLE_OF_GORE = ITEMS.register("bottle_of_gore",
            ()-> new Item(new Item.Properties()));


    public static final RegistryObject<Item> THALASSOREX = ITEMS.register("thalassorex",
            ()-> new ThalassoRex(new Item.Properties().stacksTo(1)
                    .defaultDurability(-1).rarity(Rarity.EPIC)));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }


}
