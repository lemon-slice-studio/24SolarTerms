package cloud.lemonslice.solarterms.common.handler;

import cloud.lemonslice.solarterms.common.capability.CapabilityRegistry;
import cloud.lemonslice.solarterms.common.environment.solar.SolarTerm;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelTimeAccess;

public final class SolarAngleHandler
{
    public static float getSeasonCelestialAngle(LevelTimeAccess world)
    {
        return getCelestialAngle(getSolarAngelTime(world));
    }

    public static float getSolarAngelTime(LevelTimeAccess world)
    {
        long worldTime = world.dayTime();
        if (world instanceof Level)
        {
            return ((Level) world).getCapability(CapabilityRegistry.WORLD_SOLAR_TERMS).map(data ->
            {
                int dayTime = SolarTerm.get(data.getSolarTermIndex()).getDayTime();
                int sunrise = 24000 - dayTime / 2;
                int sunset = dayTime / 2;
                int dayWorldTime = Math.toIntExact((worldTime + 18000) % 24000); // 0 for noon; 6000 for sunset; 18000 for sunrise.
                int solarAngelTime;
                if (0 <= dayWorldTime && dayWorldTime <= sunset)
                {
                    solarAngelTime = 6000 + dayWorldTime * 6000 / sunset;
                }
                else if (dayWorldTime > sunset && dayWorldTime <= sunrise)
                {
                    solarAngelTime = 12000 + (dayWorldTime - sunset) * 12000 / (24000 - dayTime);
                }
                else
                {
                    solarAngelTime = (dayWorldTime - sunrise) * 6000 / (24000 - sunrise);
                }
                return solarAngelTime;
            }).orElse(Math.toIntExact(worldTime % 24000));
        }
        else return worldTime;
    }

    public static float getCelestialAngle(float worldTime)
    {
        double d0 = Mth.frac(worldTime / 24000.0D - 0.25D);
        double d1 = 0.5D - Math.cos(d0 * Math.PI) / 2.0D;
        return (float) (d0 * 2.0D + d1) / 3.0F;
    }
}
