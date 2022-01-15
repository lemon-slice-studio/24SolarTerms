package cloud.lemonslice.solarterms.common.handler;

import cloud.lemonslice.solarterms.client.ClientProxy;
import cloud.lemonslice.solarterms.common.capability.CapabilityRegistry;
import cloud.lemonslice.solarterms.common.environment.solar.SolarTerm;
import com.google.common.collect.Lists;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.storage.LevelData;
import net.minecraftforge.server.ServerLifecycleHooks;

import java.util.List;

import static net.minecraft.world.level.biome.Biome.BiomeCategory.*;

public final class TemperatureHandler
{
    private static final List<Biome.BiomeCategory> SEASONAL_BIOME = Lists.newArrayList(TAIGA, EXTREME_HILLS, JUNGLE, MESA, PLAINS, SAVANNA, FOREST, DESERT, RIVER, SWAMP, MOUNTAIN);

    public static float getCurrentTemperatureChange(Biome biome)
    {
        if (canTemperatureChange(biome))
        {
            MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
            if (server != null)
            {
                ServerLevel level = server.getLevel(Level.OVERWORLD);
                if (level != null)
                {
                    return level.getCapability(CapabilityRegistry.WORLD_SOLAR_TERMS).map(data ->
                            getSuitableChange(biome, data.getSolarTerm()) + getRandomDeviation(level, data.getSolarTermIndex())).orElse(0.0F);
                }
            }
            else return ClientProxy.getClientTemperatureChange(biome);
        }
        return 0.0F;
    }

    public static float getRandomDeviation(Level level, int solarIndex)
    {
        LevelData data = level.getLevelData();
        int seed = data.getXSpawn() + data.getYSpawn() * 7 + data.getZSpawn() * 13 + Math.toIntExact(level.getGameTime() / 168000) + 101;
        return Mth.cos(seed * (solarIndex + 1)) * 0.05F;
    }

    public static boolean canTemperatureChange(Biome biome)
    {
        Biome.BiomeCategory category = biome.getBiomeCategory();
        return SEASONAL_BIOME.contains(category);
    }

    public static float getSuitableChange(Biome biome, SolarTerm solarTerm)
    {
        if (biome.getBaseTemperature() < 0.3F)
        {
            return solarTerm.getFrigidChange();
        }
        else if (biome.getBaseTemperature() < 0.8F)
        {
            return solarTerm.getTemperateChange();
        }
        else
        {
            return solarTerm.getTropicChange();
        }
    }

    public static Biome.Precipitation getCurrentPrecipitation(Biome biome)
    {
        if (biome.getBiomeCategory() == SAVANNA)
        {
            MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
            if (server != null)
            {
                ServerLevel level = server.getLevel(Level.OVERWORLD);
                if (level != null)
                {
                    return level.getCapability(CapabilityRegistry.WORLD_SOLAR_TERMS).map(data ->
                            data.getSolarTerm().getSeason().isDry() ? Biome.Precipitation.NONE : Biome.Precipitation.RAIN).orElse(null);
                }
            }
            else return ClientProxy.getClientPrecipitation(biome);
        }
        return null;
    }
}