package com.c446.lotm_craft.beyonder.pathways.DoorPathway.DoorSpells;

import com.c446.lotm_craft.beyonder.beyonder_spells.IBeyonderSpell;
import com.c446.lotm_craft.beyonder.beyonder_spells.SpellContext;
import com.c446.lotm_craft.beyonder.pathways.IPathway;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;

import javax.swing.text.html.parser.Entity;
import java.util.logging.Level;

public class SpiritVision implements IBeyonderSpell {
    int cost = 0;
    int madness_buildup = 0;
    int minimum_seq = -1;
    boolean canceled = false;
    public static String spellName = "";
    public static IPathway pathway = IPathway.instance;


    @Override
    public void runSpell(Entity rayTraceResult, LivingEntity caster, SpellContext context, Level world) {
        double range = caster.getAttributeValue(Attributes.LUCK);


    }





}





