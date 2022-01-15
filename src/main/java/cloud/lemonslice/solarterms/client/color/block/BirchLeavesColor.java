package cloud.lemonslice.solarterms.client.color.block;

import cloud.lemonslice.solarterms.common.capability.CapabilityRegistry;
import cloud.lemonslice.solarterms.common.environment.solar.SolarTerm;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class BirchLeavesColor implements BlockColor
{
    @Override
    public int getColor(BlockState state, @Nullable BlockAndTintGetter reader, @Nullable BlockPos pos, int index)
    {
        if (Minecraft.getInstance().level != null)
        {
            return Minecraft.getInstance().level.getCapability(CapabilityRegistry.WORLD_SOLAR_TERMS).map(data ->
                    SolarTerm.get(data.getSolarTermIndex()).getColorInfo().getBirchColor()).orElse(FoliageColor.getBirchColor());
        }
        return FoliageColor.getBirchColor();
    }
}
