package com.c446.lotm_craft.event.spell_events;

import com.c446.lotm_craft.beyonder.beyonder_spells.IBeyonderSpell;
import com.c446.lotm_craft.beyonder.beyonder_spells.SpellContext;
import net.minecraftforge.event.entity.living.LivingEvent;

public class SpellCastEvent extends LivingEvent {
    private final SpellContext context;

    public SpellCastEvent(SpellContext castContext) {
        super(castContext.caster);
        this.context = castContext;
    }

    @Override
    public boolean isCancelable() {
        return super.isCancelable();
    }

    public IBeyonderSpell getSpell() {
        return context.spell;
    }

    public SpellContext getContext() {
        return this.context;
    }

    public boolean getCanceled() {
        return this.isCanceled();
    }


}
