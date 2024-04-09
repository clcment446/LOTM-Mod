package com.c446.lotm_craft.beyonder.beyonder_spells;

import java.util.ArrayList;

public class SpellRegistry {
    public static final ArrayList<IBeyonderSpell> spellRegistry = new ArrayList<>();


    static {
        registerSpell(com.c446.lotm_craft.beyonder.pathways.DoorPathway.DoorSpells.);


    }


    public static void registerSpell(IBeyonderSpell spell){
        spellRegistry.add(spell);
    }

}
