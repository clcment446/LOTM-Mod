package com.c446.lotm_craft.event.spell_events;

import com.c446.lotm_craft.beyonder.beyonder_spells.CancelReason;
import com.c446.lotm_craft.beyonder.beyonder_spells.IBeyonderSpell;
import com.c446.lotm_craft.beyonder.beyonder_spells.SpellContext;
import net.minecraftforge.eventbus.api.Event;

public class SpellCanceledEvent extends Event {
    IBeyonderSpell spell = null;
    SpellContext context = null;
    CancelReason reason = null;
    public SpellCanceledEvent(IBeyonderSpell spell, SpellContext context, CancelReason reason){
        this.spell = spell;
        this.context = context;
        this.reason = reason;
    }

}
