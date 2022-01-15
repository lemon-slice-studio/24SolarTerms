package cloud.lemonslice.solarterms.common.command;

import cloud.lemonslice.solarterms.common.capability.CapabilityRegistry;
import cloud.lemonslice.solarterms.common.capability.SolarTermIndexStorage;
import cloud.lemonslice.solarterms.common.config.ServerConfig;
import cloud.lemonslice.solarterms.common.environment.solar.SolarTerm;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;

import java.util.Arrays;

public class SolarCommand
{
    private static final SuggestionProvider<CommandSourceStack> SUGGEST_SOLAR_TERMS = (context, builder) ->
            SharedSuggestionProvider.suggest(Arrays.stream(SolarTerm.values()).map(Enum::toString), builder);

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher)
    {
        dispatcher.register(Commands.literal("solar").requires((source) -> source.hasPermission(2))
                .then(Commands.literal("set")
                        .then(Commands.argument("day", IntegerArgumentType.integer())
                                .executes(commandContext -> setDay(commandContext.getSource(), IntegerArgumentType.getInteger(commandContext, "day")))
                        )
                        .then(Commands.argument("solar_term", StringArgumentType.word())
                                .suggests(SUGGEST_SOLAR_TERMS)
                                .executes(commandContext -> setDay(commandContext.getSource(), SolarTerm.valueOf(StringArgumentType.getString(commandContext, "solar_term")).ordinal() * ServerConfig.Season.lastingDaysOfEachTerm.get()))
                        )
                )
                .then(Commands.literal("add")
                        .then(Commands.argument("day", IntegerArgumentType.integer())
                                .executes(commandContext -> addDay(commandContext.getSource(), IntegerArgumentType.getInteger(commandContext, "day")))
                        )
                )
        );
    }

    private static int getDay(ServerLevel worldIn)
    {
        return worldIn.getCapability(CapabilityRegistry.WORLD_SOLAR_TERMS).map(SolarTermIndexStorage::getSolarTermsDay).orElse(0);
    }

    public static int setDay(CommandSourceStack source, int day)
    {

        source.getLevel().getCapability(CapabilityRegistry.WORLD_SOLAR_TERMS).ifPresent(data ->
        {
            data.setSolarTermsDay(day);
            data.sendUpdateMessage(source.getLevel());
        });

        source.sendSuccess(new TranslatableComponent("commands.solarterms.solar.set", day), true);
        return getDay(source.getLevel());
    }

    public static int addDay(CommandSourceStack source, int add)
    {
        source.getLevel().getCapability(CapabilityRegistry.WORLD_SOLAR_TERMS).ifPresent(data ->
        {
            data.setSolarTermsDay(data.getSolarTermsDay() + add);
            data.sendUpdateMessage(source.getLevel());
            source.sendSuccess(new TranslatableComponent("commands.solarterms.solar.set", data.getSolarTermsDay()), true);
        });
        return getDay(source.getLevel());
    }
}
