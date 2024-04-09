package com.c446.lotm_craft.capabilities;

import net.minecraft.nbt.CompoundTag;

public interface IBeyonderCapability{
    int sequence = 10;
    int pathway = -1;
    int current_spirituality = 100;
    int max_spirituality = 100;
    int madness_tolerance = 100;
    int madness_buildup = 0;
    boolean concealed = false;
    boolean alternatePlane = false;
    boolean isMarionette = false;

    public default void CopyFrom(BeyonderCapability source) {
    }

    public default void saveNBTData(CompoundTag nbt) {
    }

    public default void loadNBTData(CompoundTag nbt) {
    }
}
