package com.c446.ars_trinkets.Beyonder.beyonder_spells;

import com.c446.ars_trinkets.capabilities.BeyonderCapability;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;

public class SpellContext {
    LivingEntity caster = null;
    HitResult target = null;
    Level world = null;

    public void SpellContext(LivingEntity caster, HitResult target, Level world){
        this.caster = caster;
        this.target = target;
        this.world = world;
    }

}
