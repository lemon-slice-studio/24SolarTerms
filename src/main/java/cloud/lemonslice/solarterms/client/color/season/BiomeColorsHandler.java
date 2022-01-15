package cloud.lemonslice.solarterms.client.color.season;

import cloud.lemonslice.silveroak.helper.ColorHelper;
import cloud.lemonslice.solarterms.common.capability.CapabilityRegistry;
import cloud.lemonslice.solarterms.common.environment.solar.SolarTerm;
import cloud.lemonslice.solarterms.common.handler.TemperatureHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.world.level.biome.Biome;

public class BiomeColorsHandler
{
    public static int getSeasonGrassColor(Biome biome, double posX, double posZ)
    {
        return mixColor(biome, biome.getGrassColor(posX, posZ));
    }

    public static int getSeasonFoliageColor(Biome biome, double posX, double posZ)
    {
        return mixColor(biome, biome.getFoliageColor());
    }

    private static int mixColor(Biome biome, int originColor)
    {
        if (Minecraft.getInstance().level != null)
        {
            return Minecraft.getInstance().level.getCapability(CapabilityRegistry.WORLD_SOLAR_TERMS).map(data ->
            {
                SolarTerm solarTerm = data.getSolarTerm();
                if (TemperatureHandler.canTemperatureChange(biome))
                {
                    if (biome.getBiomeCategory() == Biome.BiomeCategory.SAVANNA)
                    {
                        if (solarTerm.getColorInfo().getRainyMix() != 0)
                        {
                            return ColorHelper.simplyMixColor(solarTerm.getColorInfo().getRainyColor(), solarTerm.getColorInfo().getRainyMix(), originColor, 1.0F - solarTerm.getColorInfo().getRainyMix());
                        }
                        else return originColor;
                    }
                    else if (biome.getBiomeCategory() != Biome.BiomeCategory.JUNGLE && biome.getBiomeCategory() != Biome.BiomeCategory.DESERT)
                    {
                        return ColorHelper.simplyMixColor(solarTerm.getColorInfo().getTemperateColor(), solarTerm.getColorInfo().getTemperateMix(), originColor, 1.0F - solarTerm.getColorInfo().getTemperateMix());
                    }
                    else return originColor;
                }
                else return originColor;
            }).orElse(originColor);
        }
        else return originColor;
    }
}
