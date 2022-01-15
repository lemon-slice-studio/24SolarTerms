package cloud.lemonslice.solarterms.data.provider;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

import static cloud.lemonslice.solarterms.SolarTerms.MODID;
import static cloud.lemonslice.solarterms.data.tag.NormalTags.Items.*;
import static net.minecraftforge.common.Tags.Items.*;

public final class CropInfoProvider extends ItemTagsProvider
{
    public CropInfoProvider(DataGenerator dataGenerator, BlockTagsProvider blockTagProvider, @Nullable ExistingFileHelper existingFileHelper)
    {
        super(dataGenerator, blockTagProvider, MODID, existingFileHelper);
    }

    @Override
    public void addTags()
    {
        tag(ARID).addTag(SEEDS_NETHER_WART).addTag(SEEDS_NETHER_VINE);
        tag(ARID_DRY).addOptionalTag(SEEDS_AGAVE.getName()).addOptionalTag(SEEDS_CACTUS_FRUIT.getName()).addTag(SEEDS_CACTUS);
        tag(ARID_AVERAGE).addOptionalTag(SEEDS_CORN.getName()).addOptionalTag(SEEDS_MILLET.getName()).addOptionalTag(SEEDS_PARSNIP.getName());
        tag(ARID_MOIST).addOptionalTag(SEEDS_SORGHUM.getName());
        tag(ARID_HUMID).addOptionalTag(SEEDS_CANTALOUPE.getName()).addOptionalTag(SEEDS_SISAL.getName()).addOptionalTag(SEEDS_SUNFLOWER.getName());
        tag(DRY).addOptionalTag(SEEDS_LENTIL.getName()).addOptionalTag(SEEDS_OAT.getName()).addOptionalTag(SEEDS_QUINOA.getName());
        tag(DRY_AVERAGE).addOptionalTag(SEEDS_CRANBERRY.getName()).addOptionalTag(SEEDS_HUCKLEBERRY.getName()).addOptionalTag(SEEDS_JUNIPER_BERRY.getName()).addOptionalTag(SEEDS_KALE.getName()).addOptionalTag(SEEDS_LEEK.getName()).addOptionalTag(SEEDS_RYE.getName()).addOptionalTag(SEEDS_YAM.getName());
        tag(DRY_MOIST).addOptionalTag(SEEDS_AMARANTH.getName()).addOptionalTag(SEEDS_BARLEY.getName()).addOptionalTag(SEEDS_BEAN.getName()).addOptionalTag(SEEDS_CANDLE_BERRY.getName()).addOptionalTag(SEEDS_CHICKPEA.getName()).addOptionalTag(SEEDS_COTTON.getName()).addOptionalTag(SEEDS_CUMIN.getName()).addOptionalTag(SEEDS_ELDERBERRY.getName()).addOptionalTag(SEEDS_KENAF.getName()).addOptionalTag(SEEDS_KOHLRABI.getName()).addOptionalTag(SEEDS_MULBERRY.getName()).addOptionalTag(SEEDS_MUSTARD_SEEDS.getName()).addOptionalTag(SEEDS_ONION.getName()).addOptionalTag(SEEDS_POTATO.getName()).addOptionalTag(SEEDS_RADISH.getName()).addOptionalTag(SEEDS_RUTABAGA.getName()).addOptionalTag(SEEDS_SOYBEAN.getName()).addOptionalTag(SEEDS_SWEET_POTATO.getName()).addOptionalTag(SEEDS_WINTER_SQUASH.getName()).addTag(SEEDS_WHEAT);
        tag(DRY_HUMID).addOptionalTag(SEEDS_ASPARAGUS.getName()).addOptionalTag(SEEDS_JICAMA.getName()).addOptionalTag(SEEDS_RASPBERRY.getName()).addOptionalTag(SEEDS_TOMATILLO.getName()).addOptionalTag(SEEDS_TOMATO.getName()).addTag(SEEDS_PUMPKIN);
        tag(AVERAGE).addOptionalTag(SEEDS_BELL_PEPPER.getName()).addOptionalTag(SEEDS_BLACKBERRY.getName()).addOptionalTag(SEEDS_CAULIFLOWER.getName()).addOptionalTag(SEEDS_CHILI_PEPPER.getName()).addOptionalTag(SEEDS_PEA.getName()).addOptionalTag(SEEDS_PEPPER.getName()).addTag(SEEDS_CHORUS_FLOWER).addTag(SEEDS_KELP);
        tag(AVERAGE_MOIST).addOptionalTag(SEEDS_ARTICHOKE.getName()).addOptionalTag(SEEDS_BRUSSELS_SPROUT.getName()).addOptionalTag(SEEDS_CELERY.getName()).addOptionalTag(SEEDS_CUCUMBER.getName()).addOptionalTag(SEEDS_EGGPLANT.getName()).addOptionalTag(SEEDS_FLAX.getName()).addOptionalTag(SEEDS_GARLIC.getName()).addOptionalTag(SEEDS_KIWI.getName()).addOptionalTag(SEEDS_PEANUT.getName()).addOptionalTag(SEEDS_RHUBARB.getName()).addOptionalTag(SEEDS_SCALLION.getName()).addOptionalTag(SEEDS_SESAME_SEEDS.getName()).addOptionalTag(SEEDS_SPINACH.getName()).addOptionalTag(SEEDS_STRAWBERRY.getName()).addOptionalTag(SEEDS_TURNIP.getName()).addOptionalTag(SEEDS_ZUCCHINI.getName()).addTag(SEEDS_GLOW_BERRY);
        tag(AVERAGE_HUMID).addOptionalTag(SEEDS_ARROWROOT.getName()).addOptionalTag(SEEDS_BROCCOLI.getName()).addOptionalTag(SEEDS_CABBAGE.getName()).addOptionalTag(SEEDS_CARROT.getName()).addOptionalTag(SEEDS_COFFEE_BEAN.getName()).addOptionalTag(SEEDS_GINGER.getName()).addOptionalTag(SEEDS_HONEYDEW.getName()).addOptionalTag(SEEDS_JUTE.getName()).addOptionalTag(SEEDS_OKRA.getName()).addOptionalTag(SEEDS_PINEAPPLE.getName()).addTag(SEEDS_BEETROOT);
        tag(MOIST).addOptionalTag(SEEDS_BLUEBERRY.getName()).addOptionalTag(SEEDS_LETTUCE.getName());
        tag(MOIST_HUMID).addOptionalTag(SEEDS_CASSAVA.getName()).addOptionalTag(SEEDS_GRAPE.getName()).addOptionalTag(SEEDS_GREEN_GRAPE.getName()).addOptionalTag(SEEDS_RICE.getName()).addOptionalTag(SEEDS_TARO.getName()).addTag(SEEDS_MELON).addOptionalTag(SEEDS_WATER_CHESTNUT.getName()).addOptionalTag(SEEDS_TEA_LEAF.getName()).addOptionalTag(SEEDS_WHITE_MUSHROOM.getName()).addTag(SEEDS_BAMBOO).addTag(SEEDS_SUGAR_CANE);
        tag(HUMID).addTag(SEEDS_COCOA_BEAN);

        tag(SPRING).addOptionalTag(SEEDS_WHITE_MUSHROOM.getName()).addOptionalTag(SEEDS_BRUSSELS_SPROUT.getName());
        tag(SUMMER).addTag(SEEDS_MELON).addOptionalTag(SEEDS_QUINOA.getName()).addOptionalTag(SEEDS_CANDLE_BERRY.getName()).addOptionalTag(SEEDS_ARTICHOKE.getName()).addOptionalTag(SEEDS_COFFEE_BEAN.getName()).addOptionalTag(SEEDS_HONEYDEW.getName()).addOptionalTag(SEEDS_PINEAPPLE.getName()).addTag(SEEDS_KELP);
        tag(AUTUMN).addOptionalTag(SEEDS_JICAMA.getName());
//        tag(WINTER).addOptionalTag(.getName());
        tag(SP_SU).addOptionalTag(SEEDS_LETTUCE.getName()).addOptionalTag(SEEDS_BLUEBERRY.getName()).addOptionalTag(SEEDS_BROCCOLI.getName()).addOptionalTag(SEEDS_CUCUMBER.getName()).addOptionalTag(SEEDS_CAULIFLOWER.getName()).addOptionalTag(SEEDS_BLACKBERRY.getName()).addOptionalTag(SEEDS_RUTABAGA.getName()).addOptionalTag(SEEDS_KOHLRABI.getName()).addOptionalTag(SEEDS_CUMIN.getName()).addOptionalTag(SEEDS_KOHLRABI.getName()).addOptionalTag(SEEDS_MULBERRY.getName()).addOptionalTag(SEEDS_MUSTARD_SEEDS.getName()).addOptionalTag(SEEDS_TURNIP.getName()).addOptionalTag(SEEDS_WINTER_SQUASH.getName()).addOptionalTag(SEEDS_GRAPE.getName()).addOptionalTag(SEEDS_GREEN_GRAPE.getName()).addOptionalTag(SEEDS_CANTALOUPE.getName()).addOptionalTag(SEEDS_CRANBERRY.getName()).addOptionalTag(SEEDS_HUCKLEBERRY.getName()).addOptionalTag(SEEDS_JUNIPER_BERRY.getName());
//        tag(SP_AU).addOptionalTag(.getName());
//        tag(SP_WI).addOptionalTag(.getName());
        tag(SU_AU).addOptionalTag(SEEDS_CASSAVA.getName()).addOptionalTag(SEEDS_OKRA.getName()).addOptionalTag(SEEDS_ARROWROOT.getName()).addOptionalTag(SEEDS_CHILI_PEPPER.getName()).addOptionalTag(SEEDS_ASPARAGUS.getName()).addOptionalTag(SEEDS_ELDERBERRY.getName()).addOptionalTag(SEEDS_CHICKPEA.getName()).addOptionalTag(SEEDS_AMARANTH.getName()).addOptionalTag(SEEDS_LEEK.getName()).addOptionalTag(SEEDS_ONION.getName()).addOptionalTag(SEEDS_PARSNIP.getName()).addOptionalTag(SEEDS_PEANUT.getName()).addOptionalTag(SEEDS_PEPPER.getName()).addTag(SEEDS_PUMPKIN).addOptionalTag(SEEDS_RASPBERRY.getName()).addOptionalTag(SEEDS_RHUBARB.getName()).addOptionalTag(SEEDS_SESAME_SEEDS.getName()).addOptionalTag(SEEDS_STRAWBERRY.getName()).addOptionalTag(SEEDS_WATER_CHESTNUT.getName());
//        tag(SU_WI).addOptionalTag(.getName());
//        tag(AU_WI).addOptionalTag(.getName());
        tag(SP_SU_AU).addOptionalTag(SEEDS_JUTE.getName()).addTag(SEEDS_CARROT).addOptionalTag(SEEDS_CABBAGE.getName()).addOptionalTag(SEEDS_FLAX.getName()).addOptionalTag(SEEDS_EGGPLANT.getName()).addOptionalTag(SEEDS_BELL_PEPPER.getName()).addOptionalTag(SEEDS_KENAF.getName()).addOptionalTag(SEEDS_COTTON.getName()).addOptionalTag(SEEDS_BEAN.getName()).addOptionalTag(SEEDS_KALE.getName()).addOptionalTag(SEEDS_CORN.getName()).addOptionalTag(SEEDS_KIWI.getName()).addOptionalTag(SEEDS_LENTIL.getName()).addOptionalTag(SEEDS_MILLET.getName()).addOptionalTag(SEEDS_OAT.getName()).addOptionalTag(SEEDS_PEA.getName()).addOptionalTag(SEEDS_PINEAPPLE.getName()).addOptionalTag(SEEDS_POTATO.getName()).addOptionalTag(SEEDS_RICE.getName()).addOptionalTag(SEEDS_SISAL.getName()).addOptionalTag(SEEDS_SORGHUM.getName()).addOptionalTag(SEEDS_SOYBEAN.getName()).addOptionalTag(SEEDS_SUNFLOWER.getName()).addOptionalTag(SEEDS_SWEET_POTATO.getName()).addOptionalTag(SEEDS_TARO.getName()).addOptionalTag(SEEDS_TEA_LEAF.getName()).addOptionalTag(SEEDS_TOMATILLO.getName()).addOptionalTag(SEEDS_TOMATO.getName()).addOptionalTag(SEEDS_ZUCCHINI.getName()).addTag(SEEDS_GLOW_BERRY);
//        tag(SP_SU_WI).addOptionalTag(.getName());
        tag(SP_AU_WI).addTag(SEEDS_BEETROOT).addTag(SEEDS_SUGAR_CANE);
//        tag(SU_AU_WI).addOptionalTag(.getName());
        tag(ALL_SEASONS).addOptionalTag(SEEDS_GINGER.getName()).addOptionalTag(SEEDS_GARLIC.getName()).addOptionalTag(SEEDS_CELERY.getName()).addOptionalTag(SEEDS_BARLEY.getName()).addOptionalTag(SEEDS_AGAVE.getName()).addOptionalTag(SEEDS_CACTUS_FRUIT.getName()).addOptionalTag(SEEDS_RADISH.getName()).addOptionalTag(SEEDS_RYE.getName()).addOptionalTag(SEEDS_SCALLION.getName()).addOptionalTag(SEEDS_SPINACH.getName()).addTag(SEEDS_WHEAT).addOptionalTag(SEEDS_YAM.getName()).addTag(SEEDS_BAMBOO).addTag(SEEDS_CACTUS).addTag(SEEDS_CHORUS_FLOWER).addTag(SEEDS_COCOA_BEAN).addTag(SEEDS_NETHER_WART).addTag(SEEDS_NETHER_VINE);
    }

    @Override
    public String getName()
    {
        return "24 Solar Terms Crop Info";
    }
}
