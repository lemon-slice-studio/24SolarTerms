package cloud.lemonslice.solarterms.mixin;

import cloud.lemonslice.solarterms.common.config.ServerConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.IceBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(IceBlock.class)
public abstract class IceBlockMixin
{
    @Inject(method = "randomTick",
            at = @At("HEAD"))
    private void injectRandomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, Random pRandom, CallbackInfo ci)
    {
        Biome biome = pLevel.getBiome(pPos);
        if (ServerConfig.Nature.iceMelt.get() && biome.warmEnoughToRain(pPos))
        {
            this.melt(pState, pLevel, pPos);
        }
    }

    @Shadow
    protected abstract void melt(BlockState pState, Level pLevel, BlockPos pPos);
}
