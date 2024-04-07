package com.c446.ars_trinkets.capabilities;

import com.c446.ars_trinkets.LotmCraft;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BeyonderCapabilityAttacher{

    public static class BeyonderCapabilityProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
        public static final ResourceLocation IDENTIFIER = new  ResourceLocation(LotmCraft.MOD_ID,"levels");
        public static Capability<BeyonderCapability > BEYONDER = CapabilityManager.get(new CapabilityToken<BeyonderCapability>() {});
        private BeyonderCapability beyonder_cap= null;
        private final LazyOptional<BeyonderCapability> optional = LazyOptional.of(this::createBeyonderCapability);

        private BeyonderCapability createBeyonderCapability() {
            if (this.beyonder_cap== null) {
                this.beyonder_cap = new BeyonderCapability();
            }
            return this.beyonder_cap;
        }

        @Override
        public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
            if (cap == BEYONDER) {
                return optional.cast();
            }
            return LazyOptional.empty();
        }

        @Override
        public CompoundTag serializeNBT() {
            CompoundTag nbt = new CompoundTag();
            createBeyonderCapability().saveNBTData(nbt);
            return nbt;
        }

        @Override
        public void deserializeNBT(CompoundTag nbt) {
            createBeyonderCapability().loadNBTData(nbt);
        }


    }
    public static void attach(final AttachCapabilitiesEvent<Entity> event) {
        final BeyonderCapabilityProvider provider = new BeyonderCapabilityProvider ();
        event.addCapability(BeyonderCapabilityProvider.IDENTIFIER, provider);
    }
}
