package cloud.lemonslice.solarterms.common.item;

import cloud.lemonslice.silveroak.common.item.NormalItem;
import cloud.lemonslice.solarterms.common.capability.CapabilityRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FarmingNoteItem extends NormalItem
{
    public FarmingNoteItem()
    {
        super(new Properties().tab(CreativeModeTab.TAB_TOOLS).stacksTo(1));
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext)
    {
        return pContext.getLevel().getCapability(CapabilityRegistry.WORLD_SOLAR_TERMS).map(data ->
        {
            if (pContext.getPlayer() != null)
            {
                pContext.getPlayer().displayClientMessage(new TranslatableComponent("info.solarterms.environment.solar_term", data.getSolarTerm().getTranslation()), true);
            }
            return InteractionResult.SUCCESS;
        }).orElse(InteractionResult.PASS);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced)
    {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        if (pLevel != null)
        {
            pLevel.getCapability(CapabilityRegistry.WORLD_SOLAR_TERMS).ifPresent(data ->
            {
                pTooltipComponents.add(new TranslatableComponent("info.solarterms.environment.solar_term", data.getSolarTerm().getTranslation()));
                pTooltipComponents.add(data.getSolarTerm().getAlternationText());
            });
        }
    }
}
