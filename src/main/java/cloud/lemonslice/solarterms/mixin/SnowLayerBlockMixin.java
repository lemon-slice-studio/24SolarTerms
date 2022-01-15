package cloud.lemonslice.solarterms.mixin;

import cloud.lemonslice.solarterms.common.config.ServerConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(SnowLayerBlock.class)
public abstract class SnowLayerBlockMixin
{
    @Inject(method = "randomTick",
            at = @At("HEAD"))
    private void injectRandomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, Random pRandom, CallbackInfo ci)
    {
        Biome biome = pLevel.getBiome(pPos);
        if (ServerConfig.Nature.snowMelt.get() && biome.warmEnoughToRain(pPos))
        {
            int layers = pState.getValue(SnowLayerBlock.LAYERS);
            if (layers > 1)
            {
                if (layers != 8 || !(pLevel.getBlockState(pPos.above()).getBlock() instanceof SnowLayerBlock))
                {
                    pLevel.setBlockAndUpdate(pPos, Blocks.SNOW.defaultBlockState().setValue(SnowLayerBlock.LAYERS, layers - 1));
                }
            }
            else
            {
                pLevel.removeBlock(pPos, false);
            }
        }
    }
}
