package cloud.lemonslice.solarterms.client.color.block;

import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.world.level.block.Blocks;

public final class BlockColorsRegistry
{
    public static final BlockColor BIRCH_LEAVES_COLOR = new BirchLeavesColor();

    public static void init()
    {
        Minecraft.getInstance().getBlockColors().register(BIRCH_LEAVES_COLOR, Blocks.BIRCH_LEAVES);
    }
}
