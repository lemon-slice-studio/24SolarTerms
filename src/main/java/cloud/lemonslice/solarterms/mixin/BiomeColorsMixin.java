package cloud.lemonslice.solarterms.mixin;

import cloud.lemonslice.solarterms.client.color.season.BiomeColorsHandler;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.world.level.ColorResolver;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(BiomeColors.class)
public abstract class BiomeColorsMixin
{
    @Final
    @Mutable
    @Shadow
    private static ColorResolver GRASS_COLOR_RESOLVER = BiomeColorsHandler::getSeasonGrassColor;

    @Final
    @Mutable
    @Shadow
    private static ColorResolver FOLIAGE_COLOR_RESOLVER = BiomeColorsHandler::getSeasonFoliageColor;
}
