package net.davegamer007vinicius1232426.unnamedscpmod.util;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class SweetList {
    public static List<Item> sweetList = new ArrayList<>();


    public static Ingredient sweetItems(){
        innitSweetList();
        for(int i=0;i<sweetList.size();i++){
            return Ingredient.of(sweetList.get(i));
        }
        return null;
    }

    public static void addSweetItem(Item newItem){
        sweetList.add(newItem);
    }

    public static void innitSweetList(){
        addSweetItem(Items.SUGAR);
        addSweetItem(Items.SUGAR_CANE);
        addSweetItem(Items.COOKIE);
        addSweetItem(Items.CAKE);
        addSweetItem(Items.HONEY_BOTTLE);
        addSweetItem(Items.HONEY_BLOCK);
        addSweetItem(Items.HONEYCOMB);
        addSweetItem(Items.HONEYCOMB_BLOCK);
        addSweetItem(Items.SWEET_BERRIES);
        addSweetItem(Items.NETHER_STAR);
    }
}
