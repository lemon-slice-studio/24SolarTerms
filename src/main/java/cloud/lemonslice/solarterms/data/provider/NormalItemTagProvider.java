package cloud.lemonslice.solarterms.data.provider;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

import static cloud.lemonslice.solarterms.SolarTerms.MODID;
import static cloud.lemonslice.solarterms.data.tag.NormalTags.Items.*;

public final class NormalItemTagProvider extends ItemTagsProvider
{
    public NormalItemTagProvider(DataGenerator dataGenerator, BlockTagsProvider blockTagProvider, @Nullable ExistingFileHelper existingFileHelper)
    {
        super(dataGenerator, blockTagProvider, MODID, existingFileHelper);
    }

    @Override
    protected void addTags()
    {
        tag(SEEDS_BAMBOO).add(Items.BAMBOO);
        tag(SEEDS_CACTUS).add(Items.CACTUS);
        tag(SEEDS_CARROT).add(Items.CARROT);
        tag(SEEDS_GLOW_BERRY).add(Items.GLOW_BERRIES);
        tag(SEEDS_CHORUS_FLOWER).add(Items.CHORUS_FLOWER);
        tag(SEEDS_COCOA_BEAN).add(Items.COCOA_BEANS);
        tag(SEEDS_CRANBERRY).add(Items.SWEET_BERRIES);
        tag(SEEDS_KELP).add(Items.KELP);
        tag(SEEDS_NETHER_VINE).add(Items.TWISTING_VINES, Items.WEEPING_VINES);
        tag(SEEDS_NETHER_WART).add(Items.NETHER_WART);
        tag(SEEDS_POTATO).add(Items.POTATO);
        tag(SEEDS_SUGAR_CANE).add(Items.SUGAR_CANE);

    }

    @Override
    public String getName()
    {
        return "24 Solar Terms Item Tags";
    }
}
