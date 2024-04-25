package com.c446.lotm_craft.beyonder.beyonder_spells;

import com.c446.lotm_craft.beyonder.pathways.DoorPathway.DoorSpells.SpiritVision;

import java.util.ArrayList;

public class SpellRegistry {
    public static final ArrayList<IBeyonderSpell> spellRegistry = new ArrayList<>();


    static {
        registerSpell(SpiritVision.INSTANCE);
    }

    public static IBeyonderSpell getSpellFromName(String name) {
        for (IBeyonderSpell iBeyonderSpell : spellRegistry) {
            if (iBeyonderSpell.getName().equals(name)) {
                return iBeyonderSpell;
            }
        }
        return null;
    }

    public static void registerSpell(IBeyonderSpell spell){
        spellRegistry.add(spell);
    }

}
