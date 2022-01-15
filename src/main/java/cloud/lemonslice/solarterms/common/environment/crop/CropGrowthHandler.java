package cloud.lemonslice.solarterms.common.environment.crop;

import cloud.lemonslice.silveroak.common.environment.Humidity;
import cloud.lemonslice.solarterms.common.config.ServerConfig;
import cloud.lemonslice.solarterms.common.environment.solar.Season;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static cloud.lemonslice.solarterms.SolarTerms.MODID;

@Mod.EventBusSubscriber(modid = MODID)
public final class CropGrowthHandler
{
    @SubscribeEvent
    public static void canCropGrowUp(BlockEvent.CropGrowEvent.Pre event)
    {
        Block block = event.getState().getBlock();
        LevelAccessor world = event.getWorld();
        BlockPos pos = event.getPos();
        CropSeasonInfo seasonInfo = CropInfoManager.getSeasonInfo(block);
        if (seasonInfo != null)
        {
            if (seasonInfo.isSuitable(Season.getSeason((ICapabilityProvider) world)))
            {
                Humidity env = Humidity.getHumid(world.getBiome(pos), pos);
                CropHumidityInfo humidityInfo = CropInfoManager.getHumidityInfo(block);
                checkHumidity(event, world, humidityInfo, env);
            }
            else if (world.getRandom().nextInt(100) < 25)
            {
                Humidity env = Humidity.getHumid(world.getBiome(pos), pos);
                CropHumidityInfo humidityInfo = CropInfoManager.getHumidityInfo(block);
                checkHumidity(event, world, humidityInfo, env);
            }
            else
            {
                event.setResult(Event.Result.DENY);
            }
        }
        else
        {
            Humidity env = Humidity.getHumid(world.getBiome(pos), pos);
            CropHumidityInfo humidityInfo = CropInfoManager.getHumidityInfo(block);
            checkHumidity(event, world, humidityInfo, env);
        }
    }

    @SubscribeEvent
    public static void boneMealUsingLimit(BonemealEvent event)
    {
        if (!ServerConfig.Agriculture.canUseBoneMeal.get())
        {
            if (event.getBlock().getBlock() instanceof IPlantable && ((IPlantable) event.getBlock().getBlock()).getPlantType(event.getWorld(), event.getPos()) == PlantType.CROP)
            {
                event.setCanceled(true);
            }
            else
                event.setResult(Event.Result.DEFAULT);
        }
    }

    public static void checkHumidity(BlockEvent.CropGrowEvent.Pre event, LevelAccessor world, CropHumidityInfo humidityInfo, Humidity env)
    {
        if (humidityInfo != null)
        {
            float f = humidityInfo.getGrowChance(env);
            if (f == 0)
            {
                event.setResult(Event.Result.DENY);
            }
            else if (f > 1.0F)
            {
                event.setResult(Event.Result.ALLOW);
            }
            else
            {
                if (world.getRandom().nextInt(1000) < 1000 * f)
                {
                    event.setResult(Event.Result.DEFAULT);
                }
                else
                {
                    event.setResult(Event.Result.DENY);
                }
            }
        }
    }
}
