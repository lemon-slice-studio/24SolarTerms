package cloud.lemonslice.solarterms.common.environment.solar;

import cloud.lemonslice.solarterms.common.capability.CapabilityRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public enum Season
{
    SPRING(ChatFormatting.DARK_GREEN, true),
    SUMMER(ChatFormatting.RED, false),
    AUTUMN(ChatFormatting.GOLD, false),
    WINTER(ChatFormatting.BLUE, true),
    NONE(ChatFormatting.DARK_AQUA, true);

    private final ChatFormatting color;
    private final boolean isDry;

    Season(ChatFormatting color, boolean isDry)
    {
        this.color = color;
        this.isDry = isDry;
    }

    public String getName()
    {
        return this.toString().toLowerCase();
    }

    public Component getTranslation()
    {
        return new TranslatableComponent("info.solarterms.environment.season." + getName()).withStyle(color);
    }

    public boolean isDry()
    {
        return isDry;
    }

    public ChatFormatting getColor()
    {
        return color;
    }

    public static Season getSeason(ICapabilityProvider world)
    {
        return world.getCapability(CapabilityRegistry.WORLD_SOLAR_TERMS).map(data -> data.getSolarTerm().getSeason()).orElse(Season.NONE);
    }
}
