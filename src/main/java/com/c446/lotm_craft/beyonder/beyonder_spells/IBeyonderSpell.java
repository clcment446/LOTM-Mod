package com.c446.lotm_craft.beyonder.beyonder_spells;

import com.c446.lotm_craft.LotmCraft;
import com.c446.lotm_craft.beyonder.pathways.IPathway;
import com.c446.lotm_craft.capabilities.BeyonderCapabilityAttacher;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;

public interface IBeyonderSpell {
    int cost = 0;
    int madness_buildup = 0;
    int minimum_seq = -1;
    String spellName = "";
    IPathway pathway = IPathway.INSTANCE;
    boolean isCanceled = false;

    default String buildSpellName(IBeyonderSpell spell) {
        return (LotmCraft.MOD_ID + ".spells." + pathway.name + spellName);
    }

    default String getName() {
        return spellName;
    }

    default String getSpellIdentifier(IBeyonderSpell INSTANCE) {
        return this.buildSpellName(INSTANCE);
    }

    default int getMinimumSeq() {
        return minimum_seq;
    }

    default int getCost() {
        return cost;
    }

    default int getMadnessBuildup() {
        return madness_buildup;
    }


    default void runSpell() {
        /**
         * This is the starting point of the spells, and events will be launched from here.
         */
    }


    default void spellEffect(LivingEntity caster, ServerLevel level) {
        /**
         * This is where the "main" part of the spell will go.
         This is triggered after the Bus gets the spellEvent, and will therefore only trigger after the event gets validated.
         */
    }

    void runSpell(LivingEntity caster, ServerLevel level);

    void spellEffect(SpellContext context);

    int getSpellCost();

    static void consumeSpirituality(SpellContext context) {
        context.caster.getCapability(BeyonderCapabilityAttacher.BeyonderCapabilityProvider.BEYONDER).ifPresent(a -> {
            a.setCurrentSpirituality(a.getCurrentSpirituality() - context.spell.getSpellCost());
        });
    }

    String getSpellIdentifier();
}
