package cloud.lemonslice.solarterms.common.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ServerConfig
{
    protected ServerConfig(ForgeConfigSpec.Builder builder)
    {
        Agriculture.load(builder);
        Nature.load(builder);
        Season.load(builder);
    }

    public static class Nature
    {
        public static ForgeConfigSpec.BooleanValue iceMelt;
        public static ForgeConfigSpec.BooleanValue snowMelt;

        private static void load(ForgeConfigSpec.Builder builder)
        {
            builder.comment("Configuration about environment settings").push("Nature");
            iceMelt = builder.comment("Ice will melt in warm place..")
                    .define("IceMelt", true);
            snowMelt = builder.comment("Snow will melt in warm place..")
                    .define("SnowMelt", true);
            builder.pop();
        }
    }

    public static class Season
    {
        public static ForgeConfigSpec.IntValue lastingDaysOfEachTerm;
        public static ForgeConfigSpec.IntValue initialSolarTermIndex;

        private static void load(ForgeConfigSpec.Builder builder)
        {
            builder.comment("Configuration about solar terms.").push("Season");
            lastingDaysOfEachTerm = builder.comment("The lasting days of each term (24 in total).")
                    .defineInRange("LastingDaysOfEachTerm", 7, 1, 180);
            initialSolarTermIndex = builder.comment("The index of the initial solar term.")
                    .defineInRange("InitialSolarTermIndex", 4, 1, 24);
            builder.pop();
        }
    }

    public static class Agriculture
    {
        public static ForgeConfigSpec.BooleanValue canUseBoneMeal;
        public static ForgeConfigSpec.BooleanValue hasHumidityLimitation;
        public static ForgeConfigSpec.BooleanValue hasSeasonLimitation;

        private static void load(ForgeConfigSpec.Builder builder)
        {
            builder.comment("Configuration about cultivation limitations.").push("Agriculture");
            canUseBoneMeal = builder.comment("Can bone meal be used to grow crops?")
                    .define("BoneMeal", true);
            hasHumidityLimitation = builder.comment("Is there humidity limitation?")
                    .define("HumidityLimitation", true);
            hasSeasonLimitation = builder.comment("Is there season limitation?")
                    .define("SeasonLimitation", true);
            builder.pop();
        }
    }
}

