package cloud.lemonslice.solarterms.data.provider;

import cloud.lemonslice.silveroak.common.item.SilveroakItemsRegistry;
import cloud.lemonslice.solarterms.common.item.ItemRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

public final class RecipeProvider extends net.minecraft.data.recipes.RecipeProvider
{
    public RecipeProvider(DataGenerator generatorIn)
    {
        super(generatorIn);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer)
    {
        ShapelessRecipeBuilder.shapeless(ItemRegistry.FARMING_NOTE.get()).requires(Items.WRITABLE_BOOK).requires(SilveroakItemsRegistry.HYGROMETER.get()).unlockedBy("hygrometer", has(SilveroakItemsRegistry.HYGROMETER.get())).group("farming_note").save(pFinishedRecipeConsumer);
    }

    @Override
    public String getName()
    {
        return "24 Solar Terms Recipes";
    }
}
