package com.c446.lotm_craft.beyonder.pathways.DoorPathway.DoorSpells;

import com.c446.lotm_craft.beyonder.beyonder_spells.IBeyonderSpell;
import com.c446.lotm_craft.beyonder.beyonder_spells.SpellContext;
import com.c446.lotm_craft.beyonder.pathways.DoorPathway.DoorPathway;
import com.c446.lotm_craft.beyonder.pathways.IPathway;
import com.c446.lotm_craft.event.spell_events.SpellCastEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.MinecraftForge;

public class SpiritVision implements IBeyonderSpell {
    public static final SpiritVision INSTANCE = new SpiritVision(30, 5, 9, "SPIRITUAL_VISION", DoorPathway.INSTANCE);


    public SpiritVision(int cost, int madness_buildup, int minimum_seq, String name, IPathway pathway) {
        this.madness_buildup = madness_buildup;
        this.minimum_seq = minimum_seq;
        this.spellName = name;
        this.pathway = pathway;
        this.cost = cost;
    }

    int madness_buildup;
    int minimum_seq;
    String spellName;
    IPathway pathway;
    int cost;


    @Override
    public void runSpell(LivingEntity caster, ServerLevel level) {
        SpellContext context = new SpellContext(caster, level, INSTANCE);
        MinecraftForge.EVENT_BUS.post(new SpellCastEvent(context));
    }

    @Override
    public void spellEffect(SpellContext context) {
        LivingEntity caster = context.caster;
        ServerLevel level = (ServerLevel) context.world;
        Vec3 pos = caster.position();
        double range = caster.getAttributeValue(Attributes.LUCK);
        for (Entity e : level.getEntities(caster, new AABB(
                pos.add(range, range, range), pos.subtract(range, range, range)))) {
            if ((!e.equals(caster) || e instanceof LivingEntity l && l.hasEffect(MobEffects.INVISIBILITY))) {
                LivingEntity l = (LivingEntity) e;
                l.addEffect(new MobEffectInstance(MobEffects.GLOWING, 200));
            }
        }
        MinecraftForge.EVENT_BUS.post(new SpellCastEvent(new SpellContext(caster, level, INSTANCE)));
    }

    @Override
    public int getSpellCost() {
        return cost;
    }

    @Override
    public String getSpellIdentifier() {
        return IBeyonderSpell.super.getSpellIdentifier(INSTANCE);
    }
}