package cloud.lemonslice.solarterms.common.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static cloud.lemonslice.solarterms.SolarTerms.MODID;

public class ItemRegistry
{
    public static final DeferredRegister<Item> ITEM_REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final RegistryObject<Item> FARMING_NOTE = ITEM_REGISTER.register("farming_note", FarmingNoteItem::new);
}
