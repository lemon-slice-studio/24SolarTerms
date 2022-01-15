package cloud.lemonslice.solarterms.common.network;

import cloud.lemonslice.silveroak.network.INormalMessage;
import cloud.lemonslice.solarterms.SolarTerms;
import cloud.lemonslice.solarterms.common.capability.CapabilityRegistry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SolarTermsMessage implements INormalMessage
{
    int solarDay;

    public SolarTermsMessage(int solarDay)
    {
        this.solarDay = solarDay;
    }

    public SolarTermsMessage(FriendlyByteBuf buf)
    {
        solarDay = buf.readInt();
    }

    @Override
    public void toBytes(FriendlyByteBuf buf)
    {
        buf.writeInt(solarDay);
    }

    @Override
    public void process(Supplier<NetworkEvent.Context> context)
    {
        context.get().enqueueWork(() ->
        {
            if (context.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
            {
                SolarTerms.PROXY.getClientWorld().getCapability(CapabilityRegistry.WORLD_SOLAR_TERMS).ifPresent(data ->
                        data.setSolarTermsDay(solarDay));
            }
        });
    }
}
