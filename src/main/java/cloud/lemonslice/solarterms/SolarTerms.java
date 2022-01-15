package cloud.lemonslice.solarterms;

import cloud.lemonslice.solarterms.client.ClientProxy;
import cloud.lemonslice.solarterms.common.CommonProxy;
import cloud.lemonslice.solarterms.common.command.SolarCommand;
import cloud.lemonslice.solarterms.common.config.NormalConfigs;
import cloud.lemonslice.solarterms.common.item.ItemRegistry;
import cloud.lemonslice.solarterms.common.network.SimpleNetworkHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("solarterms")
public final class SolarTerms
{
    public static final String MODID = "solarterms";
    public static final CommonProxy PROXY = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> CommonProxy::new);
    public static final String NETWORK_VERSION = "1.0";

    public SolarTerms()
    {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        MinecraftForge.EVENT_BUS.addListener(this::onCommandRegister);
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, NormalConfigs.SERVER_CONFIG);
        ItemRegistry.ITEM_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        SimpleNetworkHandler.init();
    }

    private void doClientStuff(final FMLClientSetupEvent event)
    {
        ClientProxy.initBlockColors();
    }

    public void onCommandRegister(RegisterCommandsEvent event)
    {
        SolarCommand.register(event.getDispatcher());
    }
}
