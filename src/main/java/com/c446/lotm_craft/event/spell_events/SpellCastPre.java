package com.c446.lotm_craft.event.spell_events;

import com.c446.lotm_craft.beyonder.beyonder_spells.IBeyonderSpell;
import com.c446.lotm_craft.beyonder.beyonder_spells.SpellContext;
import net.minecraftforge.event.entity.living.LivingEvent;

public class SpellCastPre extends LivingEvent {
    private final IBeyonderSpell spell;
    private final SpellContext context;

    public SpellCastPre(IBeyonderSpell castedSpell, SpellContext castContext) {
        super(castContext.caster);
        this.spell = castedSpell;
        this.context = castContext;
    }

    @Override
    public boolean isCancelable() {
        return super.isCancelable();
    }

    public IBeyonderSpell getSpell() {
        return this.spell;
    }

    public SpellContext getContext() {
        return this.context;
    }

    public boolean getCanceled() {
        return this.isCanceled();
    }


}
