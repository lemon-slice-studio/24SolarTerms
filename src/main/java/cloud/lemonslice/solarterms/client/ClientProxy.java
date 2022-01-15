package cloud.lemonslice.solarterms.client;

import cloud.lemonslice.solarterms.SolarTerms;
import cloud.lemonslice.solarterms.client.color.block.BlockColorsRegistry;
import cloud.lemonslice.solarterms.common.CommonProxy;
import cloud.lemonslice.solarterms.common.capability.CapabilityRegistry;
import cloud.lemonslice.solarterms.common.handler.TemperatureHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;


public class ClientProxy extends CommonProxy
{
    @Override
    public Level getClientWorld()
    {
        return Minecraft.getInstance().level;
    }

    @Override
    public Player getClientPlayer()
    {
        return Minecraft.getInstance().player;
    }

    public static float getClientTemperatureChange(Biome biome)
    {
        return SolarTerms.PROXY.getClientWorld().getCapability(CapabilityRegistry.WORLD_SOLAR_TERMS).map(data ->
                TemperatureHandler.getSuitableChange(biome, data.getSolarTerm()) + TemperatureHandler.getRandomDeviation(SolarTerms.PROXY.getClientWorld(), data.getSolarTermIndex())).orElse(0.0F);
    }

    public static Biome.Precipitation getClientPrecipitation(Biome biome)
    {
        return SolarTerms.PROXY.getClientWorld().getCapability(CapabilityRegistry.WORLD_SOLAR_TERMS).map(data ->
                data.getSolarTerm().getSeason().isDry() ? Biome.Precipitation.NONE : Biome.Precipitation.RAIN).orElse(null);
    }

    public static void initBlockColors()
    {
        BlockColorsRegistry.init();
    }
}
