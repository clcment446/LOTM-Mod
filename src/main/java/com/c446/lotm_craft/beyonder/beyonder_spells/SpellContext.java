package com.c446.lotm_craft.beyonder.beyonder_spells;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;

public class SpellContext {
    public IBeyonderSpell spell = null;
    public LivingEntity caster = null;
    public HitResult target = null;
    public Level world = null;

    public SpellContext(LivingEntity caster, HitResult target, Level world, IBeyonderSpell spell){
        this.caster = caster;
        this.target = target;
        this.world = world;
        this.spell = spell;
    }
}
