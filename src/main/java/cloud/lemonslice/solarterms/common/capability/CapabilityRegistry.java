package cloud.lemonslice.solarterms.common.capability;

import cloud.lemonslice.solarterms.common.network.SimpleNetworkHandler;
import cloud.lemonslice.solarterms.common.network.SolarTermsMessage;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;

import static cloud.lemonslice.solarterms.SolarTerms.MODID;

@Mod.EventBusSubscriber
public final class CapabilityRegistry
{
    public static final Capability<SolarTermIndexStorage> WORLD_SOLAR_TERMS = CapabilityManager.get(new CapabilityToken<>()
    {
    });

    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event)
    {
        event.register(SolarTermIndexStorage.class);
    }

    @SubscribeEvent
    public static void onAttachCapabilitiesWorld(AttachCapabilitiesEvent<Level> event)
    {
        if (event.getObject().dimension() == Level.OVERWORLD)
        {
            event.addCapability(new ResourceLocation(MODID, "solar_terms"), new SolarTermIndexStorage.Provider());
        }
    }

    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event)
    {
        if (!(event.getPlayer() instanceof FakePlayer))
        {
            event.getPlayer().getLevel().getCapability(CapabilityRegistry.WORLD_SOLAR_TERMS).ifPresent(data ->
                    SimpleNetworkHandler.CHANNEL.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) event.getPlayer()), new SolarTermsMessage(data.getSolarTermsDay())));
        }
    }
}
