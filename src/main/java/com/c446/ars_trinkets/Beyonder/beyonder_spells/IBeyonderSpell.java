package com.c446.ars_trinkets.Beyonder.beyonder_spells;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.HitResult;

import java.util.logging.Level;

public interface IBeyonderSpell {
    int cost = 0;
    int madness_buildup = 0;
    int minimum_seq =-1;

    static SpellContext BuildSpellContext(LivingEntity player, HitResult target, Level level) {
        return new SpellContext();
    }

    public default void runSpellOnEntity(Entity rayTraceResult, LivingEntity caster, SpellContext context, Level world) {
    }

    public default void runSpellOnBlock(BlockPos rayTraceResult, LivingEntity caster, SpellContext context, Level world) {
    }

    public default void runSpellOnAny(HitResult rayTraceResult, LivingEntity caster, SpellContext context, Level world) {
    }
}
