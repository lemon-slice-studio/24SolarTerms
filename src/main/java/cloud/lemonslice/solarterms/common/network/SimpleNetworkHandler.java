package cloud.lemonslice.solarterms.common.network;

import cloud.lemonslice.silveroak.network.INormalMessage;
import cloud.lemonslice.solarterms.SolarTerms;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.function.Function;

import static cloud.lemonslice.solarterms.SolarTerms.MODID;

public final class SimpleNetworkHandler
{
    public static final SimpleChannel CHANNEL = NetworkRegistry.ChannelBuilder.named(new ResourceLocation(MODID, "main")).networkProtocolVersion(() -> SolarTerms.NETWORK_VERSION).serverAcceptedVersions(SolarTerms.NETWORK_VERSION::equals).clientAcceptedVersions(SolarTerms.NETWORK_VERSION::equals).simpleChannel();

    public static void init()
    {
        int id = 0;
        registerMessage(id++, SolarTermsMessage.class, SolarTermsMessage::new);
    }

    private static <T extends INormalMessage> void registerMessage(int index, Class<T> messageType, Function<FriendlyByteBuf, T> decoder)
    {
        CHANNEL.registerMessage(index, messageType, INormalMessage::toBytes, decoder, (message, context) ->
        {
            message.process(context);
            context.get().setPacketHandled(true);
        });
    }
}
