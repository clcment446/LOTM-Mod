package com.c446.lotm_craft.beyonder.beyonder_spells;

import com.c446.lotm_craft.event.spell_events.SpellCastEvent;
import com.c446.lotm_craft.perks.PerkAttributes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.common.MinecraftForge;

import java.util.Objects;

public class SpellContext {
    public SpellContext(LivingEntity caster, HitResult target, ServerLevel world, IBeyonderSpell spell) {
        this.caster = caster;
        this.target = target;
        this.world = world;
        this.spell = spell;
        this.triggerEvent();
    }

    public SpellContext(LivingEntity caster, ServerLevel world, IBeyonderSpell spell) {
        this.caster = caster;
        this.is_self_targeting = true;
        this.world = world;
        this.spell = spell;
        this.triggerEvent();
    }

    public SpellContext(LivingEntity caster, HitResult target, ServerLevel world, IBeyonderSpell spell, float spellPower) {
        this.caster = caster;
        this.target = target;
        this.world = world;
        this.spell = spell;
        this.triggerEvent();
        this.spellPower = spellPower;
    }

    public SpellContext(LivingEntity caster, ServerLevel world, IBeyonderSpell spell, float spellPower) {
        this.caster = caster;
        this.is_self_targeting = true;
        this.world = world;
        this.spell = spell;
        this.triggerEvent();
        this.spellPower = spellPower;
    }

    public IBeyonderSpell spell = null;
    public LivingEntity caster = null;
    public HitResult target = null;
    public Level world = null;
    public boolean is_self_targeting = false;
    public float spellPower = 1;

    public void setSpell(IBeyonderSpell spell){
        this.spell = spell;
    }

    public void setCaster(LivingEntity caster){
        this.caster = caster;
    }

    public void setTarget(HitResult target){
        this.target = target;
    }

    public void setWorld(Level lvl){
        this.world = lvl;
    }

    public void setIsSelfTargeting(boolean targeting){
        this.is_self_targeting = targeting;
    }

    public void setSpellPower(float spellPower){
        this.spellPower = spellPower;
    }

    public int calcSpellPower(LivingEntity entity){
        return (int) Objects.requireNonNull(entity.getAttribute(PerkAttributes.BEYONDER_SPELL_POWER.get())).getValue();
    }


    public void triggerEvent() {
        if (is_self_targeting) {
            MinecraftForge.EVENT_BUS.post(new SpellCastEvent(new SpellContext(caster, (ServerLevel) world, spell)));
        } else {
            MinecraftForge.EVENT_BUS.post(new SpellCastEvent(new SpellContext(caster, target, (ServerLevel) world, spell)));
        }
    }
}
