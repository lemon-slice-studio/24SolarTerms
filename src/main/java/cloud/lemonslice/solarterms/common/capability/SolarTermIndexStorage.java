package cloud.lemonslice.solarterms.common.capability;

import cloud.lemonslice.solarterms.common.config.ServerConfig;
import cloud.lemonslice.solarterms.common.environment.solar.SolarTerm;
import cloud.lemonslice.solarterms.common.network.SimpleNetworkHandler;
import cloud.lemonslice.solarterms.common.network.SolarTermsMessage;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.network.PacketDistributor;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

import static cloud.lemonslice.solarterms.common.capability.CapabilityRegistry.WORLD_SOLAR_TERMS;

public class SolarTermIndexStorage implements INBTSerializable<Tag>
{
    private int solarTermsDay = (ServerConfig.Season.initialSolarTermIndex.get() - 1) * ServerConfig.Season.lastingDaysOfEachTerm.get();
    private int solarTermsTicks = 0;

    public void updateTicks(ServerLevel world)
    {
        solarTermsTicks++;
        int dayTime = Math.toIntExact(world.getDayTime() % 24000);
        if (solarTermsTicks > dayTime + 100)
        {
            solarTermsDay++;
            solarTermsDay %= 24 * ServerConfig.Season.lastingDaysOfEachTerm.get();
            sendUpdateMessage(world);
        }
        solarTermsTicks = dayTime;
    }

    public int getSolarTermIndex()
    {
        return solarTermsDay / ServerConfig.Season.lastingDaysOfEachTerm.get();
    }

    public SolarTerm getSolarTerm()
    {
        return SolarTerm.get(this.getSolarTermIndex());
    }

    public int getSolarTermsDay()
    {
        return solarTermsDay;
    }

    public int getSolarTermsTicks()
    {
        return solarTermsTicks;
    }

    public void setSolarTermsDay(int solarTermsDay)
    {
        this.solarTermsDay = Math.max(solarTermsDay, 0) % (24 * ServerConfig.Season.lastingDaysOfEachTerm.get());
    }

    public void setSolarTermsTicks(int solarTermsTicks)
    {
        this.solarTermsTicks = solarTermsTicks;
    }

    public void sendUpdateMessage(ServerLevel world)
    {
        for (ServerPlayer player : world.getServer().getPlayerList().getPlayers())
        {
            SimpleNetworkHandler.CHANNEL.send(PacketDistributor.PLAYER.with(() -> player), new SolarTermsMessage(solarTermsDay));
        }
    }

    @Override
    public Tag serializeNBT()
    {
        CompoundTag compound = new CompoundTag();
        compound.putInt("SolarTermsDay", this.getSolarTermsDay());
        compound.putInt("SolarTermsTicks", this.getSolarTermsTicks());
        return compound;
    }

    @Override
    public void deserializeNBT(Tag nbt)
    {
        this.setSolarTermsDay(((CompoundTag) nbt).getInt("SolarTermsDay"));
        this.setSolarTermsTicks(((CompoundTag) nbt).getInt("SolarTermsTicks"));
    }

    public static class Provider implements ICapabilitySerializable<Tag>
    {
        private final LazyOptional<SolarTermIndexStorage> data = LazyOptional.of(SolarTermIndexStorage::new);

        @Nonnull
        @Override
        public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side)
        {
            if (Objects.equals(cap, WORLD_SOLAR_TERMS))
                return data.cast();
            else
                return LazyOptional.empty();
        }

        @Override
        public Tag serializeNBT()
        {
            return data.orElseGet(SolarTermIndexStorage::new).serializeNBT();
        }

        @Override
        public void deserializeNBT(Tag nbt)
        {
            data.orElseGet(SolarTermIndexStorage::new).deserializeNBT(nbt);
        }
    }
}
