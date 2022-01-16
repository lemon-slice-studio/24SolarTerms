package cloud.lemonslice.solarterms.common.environment.solar;

import cloud.lemonslice.solarterms.client.color.season.SolarTermColors;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public enum SolarTerm
{
    // Spring Solar Terms
    BEGINNING_OF_SPRING(-0.6F, 10500),
    RAIN_WATER(-0.45F, 11000),
    INSECTS_AWAKENING(-0.35F, 11500),
    SPRING_EQUINOX(-0.25F, 12000),
    FRESH_GREEN(-0.1F, 12500),
    GRAIN_RAIN(0, 13000),

    // Summer Solar Terms
    BEGINNING_OF_SUMMER(0.025F, 13500),
    LESSER_FULLNESS(0.05F, 14000),
    GRAIN_IN_EAR(0.01F, 14500),
    SUMMER_SOLSTICE(0.15F, 15000),
    LESSER_HEAT(0.2F, 14500),
    GREATER_HEAT(0.25F, 14000),

    // Autumn Solar Terms
    BEGINNING_OF_AUTUMN(0.2F, 13500),
    END_OF_HEAT(0.1F, 13000),
    WHITE_DEW(0.05F, 12500),
    AUTUMNAL_EQUINOX(0, 12000),
    COLD_DEW(-0.125F, 11500),
    FIRST_FROST(-0.3F, 11000),

    // Winter Solar Terms
    BEGINNING_OF_WINTER(-0.5F, 10500),
    LIGHT_SNOW(-0.75F, 10000),
    HEAVY_SNOW(-0.8F, 9500),
    WINTER_SOLSTICE(-0.95F, 9000),
    LESSER_COLD(-1F, 9500),
    GREATER_COLD(-0.9F, 10000);

    private final float temperate;
    private final int dayTime;

    SolarTerm(float temperate, int dayTime)
    {
        this.temperate = temperate;
        this.dayTime = dayTime;
    }

    public String getName()
    {
        return this.toString().toLowerCase();
    }

    public Component getTranslation()
    {
        return new TranslatableComponent("info.solarterms.environment.solar_term." + getName()).withStyle(getSeason().getColor());
    }

    public Component getAlternationText()
    {
        return new TranslatableComponent("info.solarterms.environment.solar_term.alternation." + getName()).withStyle(getSeason().getColor());
    }

    public static SolarTerm get(int index)
    {
        return values()[index];
    }

    @OnlyIn(Dist.CLIENT)
    public SolarTermColors getColorInfo()
    {
        return SolarTermColors.values()[this.ordinal()];
    }

    public float getTemperateChange()
    {
        return temperate;
    }

    public int getDayTime()
    {
        return dayTime;
    }

    public Season getSeason()
    {
        return Season.values()[this.ordinal() / 6];
    }
}
