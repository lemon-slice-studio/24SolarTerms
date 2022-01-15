package cloud.lemonslice.solarterms.data;

import cloud.lemonslice.solarterms.data.provider.CropInfoProvider;
import cloud.lemonslice.solarterms.data.provider.NormalItemTagProvider;
import cloud.lemonslice.solarterms.data.provider.RecipeProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeBlockTagsProvider;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

import static cloud.lemonslice.solarterms.SolarTerms.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class DataGatherHandler
{
    @SubscribeEvent
    public static void onDataGather(GatherDataEvent event)
    {
        DataGenerator gen = event.getGenerator();
        if (event.includeServer())
        {
            ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
            ForgeBlockTagsProvider blockTags = new ForgeBlockTagsProvider(gen, existingFileHelper);
            gen.addProvider(new NormalItemTagProvider(gen, blockTags, existingFileHelper));
            gen.addProvider(new CropInfoProvider(gen, blockTags, existingFileHelper));
            gen.addProvider(new RecipeProvider(gen));
        }
    }
}
