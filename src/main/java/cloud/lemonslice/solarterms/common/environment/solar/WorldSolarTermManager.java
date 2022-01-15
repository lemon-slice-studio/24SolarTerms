package cloud.lemonslice.solarterms.common.environment.solar;

import cloud.lemonslice.solarterms.common.capability.CapabilityRegistry;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static cloud.lemonslice.solarterms.SolarTerms.MODID;

@Mod.EventBusSubscriber(modid = MODID)
public final class WorldSolarTermManager
{
    @SubscribeEvent
    public static void onWorldTick(TickEvent.WorldTickEvent event)
    {
        if (event.phase.equals(TickEvent.Phase.END) && !event.world.isClientSide() && event.world.dimension() == Level.OVERWORLD)
        {
            event.world.getCapability(CapabilityRegistry.WORLD_SOLAR_TERMS).ifPresent(data ->
            {
                if (!event.world.players().isEmpty())
                {
                    data.updateTicks((ServerLevel) event.world);
                }
            });
        }
    }
}
