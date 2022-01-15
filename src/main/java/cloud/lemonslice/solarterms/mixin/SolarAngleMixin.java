package cloud.lemonslice.solarterms.mixin;

import cloud.lemonslice.solarterms.common.handler.SolarAngleHandler;
import net.minecraft.world.level.LevelTimeAccess;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LevelTimeAccess.class)
public interface SolarAngleMixin extends LevelTimeAccess
{
    @Override
    default float getTimeOfDay(float pPartialTicks)
    {
        return SolarAngleHandler.getSeasonCelestialAngle(this);
    }
}
