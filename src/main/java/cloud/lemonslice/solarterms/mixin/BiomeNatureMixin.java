package cloud.lemonslice.solarterms.mixin;

import cloud.lemonslice.solarterms.common.handler.TemperatureHandler;
import it.unimi.dsi.fastutil.longs.Long2FloatLinkedOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.biome.Biome;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Biome.class)
public abstract class BiomeNatureMixin
{
    @Final
    @Shadow
    private ThreadLocal<Long2FloatLinkedOpenHashMap> temperatureCache;

    @Inject(method = "getTemperature",
            at = @At("HEAD"),
            cancellable = true)
    private void injectGetTemperature(BlockPos pPos, CallbackInfoReturnable<Float> cir)
    {
        long i = pPos.asLong();
        Long2FloatLinkedOpenHashMap long2floatlinkedopenhashmap = this.temperatureCache.get();
        float f = long2floatlinkedopenhashmap.get(i);
        if (!Float.isNaN(f))
        {
            cir.setReturnValue(f + TemperatureHandler.getCurrentTemperatureChange((Biome) (Object) this));
        }
        else
        {
            float f1 = this.getHeightAdjustedTemperature(pPos);
            if (long2floatlinkedopenhashmap.size() == 1024)
            {
                long2floatlinkedopenhashmap.removeFirstFloat();
            }

            long2floatlinkedopenhashmap.put(i, f1);
            cir.setReturnValue(f1 + TemperatureHandler.getCurrentTemperatureChange((Biome) (Object) this));
        }
    }

    @Inject(method = "getPrecipitation",
            at = @At("HEAD"),
            cancellable = true)
    private void injectGetPrecipitation(CallbackInfoReturnable<Biome.Precipitation> cir)
    {
        Biome.Precipitation precipitation = TemperatureHandler.getCurrentPrecipitation((Biome) (Object) this);
        if (precipitation != null)
        {
            cir.setReturnValue(precipitation);
        }
    }

    @Inject(method = "getDownfall",
            at = @At("HEAD"),
            cancellable = true)
    private void injectGetDownfall(CallbackInfoReturnable<Float> cir)
    {
        if (this.getBiomeCategory() == Biome.BiomeCategory.SAVANNA)
        {
            cir.setReturnValue(0.2F);
        }
    }

    @Shadow
    protected abstract float getHeightAdjustedTemperature(BlockPos pPos);

    @Shadow
    protected abstract Biome.BiomeCategory getBiomeCategory();
}
