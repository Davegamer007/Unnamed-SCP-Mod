package net.davegamer007vinicius1232426.unnamedscpmod.datagen;

import net.davegamer007vinicius1232426.unnamedscpmod.UnnamedSCPMod;
import net.davegamer007vinicius1232426.unnamedscpmod.block.ModBlocks;
import net.davegamer007vinicius1232426.unnamedscpmod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        //Recipes go in here

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.TELEKILL_BLOCK.get())
                .pattern("III")
                .pattern("III")
                .pattern("III")
                .define('I', ModItems.TELEKILL.get())
                .unlockedBy(getHasName(ModItems.TELEKILL.get()), has(ModItems.TELEKILL.get()))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.RAW_TELEKILL_BLOCK.get())
                .pattern("III")
                .pattern("III")
                .pattern("III")
                .define('I', ModItems.RAW_TELEKILL.get())
                .unlockedBy(getHasName(ModItems.RAW_TELEKILL.get()), has(ModItems.RAW_TELEKILL.get()))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SALT_BLOCK.get())
                .pattern("III")
                .pattern("III")
                .pattern("III")
                .define('I', ModItems.SALT.get())
                .unlockedBy(getHasName(ModItems.SALT.get()), has(ModItems.SALT.get()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.TELEKILL.get(), 9)
                .requires(ModBlocks.TELEKILL_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.TELEKILL_BLOCK.get()), has(ModBlocks.TELEKILL_BLOCK.get()))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.RAW_TELEKILL.get(), 9)
                .requires(ModBlocks.RAW_TELEKILL_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.RAW_TELEKILL_BLOCK.get()), has(ModBlocks.RAW_TELEKILL_BLOCK.get()))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.CLASS_A_AMNESTIC.get(), 1)
                .requires(ModItems.BOTTLE_OF_Y9O9.get())
                .requires(ModItems.SPC939SPIKE.get(), 2)
                .unlockedBy(getHasName(ModItems.BOTTLE_OF_Y9O9.get()), has(ModItems.BOTTLE_OF_Y9O9.get()))
                .save(consumer);

        oreBlasting(consumer, TELLEKILL_SMELTABLES,RecipeCategory.MISC,ModItems.TELEKILL.get(),2,200, "telekill");
        oreSmelting(consumer, TELLEKILL_SMELTABLES,RecipeCategory.MISC,ModItems.TELEKILL.get(),2,400, "telekill");

    }

    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        Iterator var9 = pIngredients.iterator();

        while (var9.hasNext()) {
            ItemLike itemlike = (ItemLike) var9.next();
            SimpleCookingRecipeBuilder.generic(Ingredient.of(new ItemLike[]{itemlike}), pCategory, pResult,
                            pExperience, pCookingTime, pCookingSerializer)
                    .group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pFinishedRecipeConsumer, UnnamedSCPMod.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }

    }

    private static final List<ItemLike> TELLEKILL_SMELTABLES = List.of(ModItems.RAW_TELEKILL.get(), ModBlocks.TELEKILL_ORE.get());
}