package cloud.lemonslice.solarterms.client;

import cloud.lemonslice.solarterms.common.config.ServerConfig;
import cloud.lemonslice.solarterms.common.environment.crop.CropHumidityInfo;
import cloud.lemonslice.solarterms.common.environment.crop.CropInfoManager;
import cloud.lemonslice.solarterms.common.environment.crop.CropSeasonInfo;
import net.minecraft.world.item.BlockItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static cloud.lemonslice.solarterms.SolarTerms.MODID;

@Mod.EventBusSubscriber(modid = MODID, value = Dist.CLIENT)
public final class ClientEventHandler
{
    @SubscribeEvent
    public static void addTooltips(ItemTooltipEvent event)
    {
        if (event.getItemStack().getItem() instanceof BlockItem)
        {
            if (ServerConfig.Agriculture.hasHumidityLimitation.get() && CropInfoManager.getHumidityCrops().contains(((BlockItem) event.getItemStack().getItem()).getBlock()))
            {
                CropHumidityInfo info = CropInfoManager.getHumidityInfo(((BlockItem) event.getItemStack().getItem()).getBlock());
                event.getToolTip().addAll(info.getTooltip());
            }
            if (ServerConfig.Agriculture.hasSeasonLimitation.get() && CropInfoManager.getSeasonCrops().contains(((BlockItem) event.getItemStack().getItem()).getBlock()))
            {
                CropSeasonInfo info = CropInfoManager.getSeasonInfo(((BlockItem) event.getItemStack().getItem()).getBlock());
                event.getToolTip().addAll(info.getTooltip());
            }
        }

    }
}
