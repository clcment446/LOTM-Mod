package com.c446.lotm_craft.beyonder.beyonder_spells;

import com.c446.lotm_craft.event.spell_events.SpellCanceledEvent;
import net.minecraft.network.chat.Component;

public class CancelReason {
    public static CancelReason NO_SPIRITUALITY = new CancelReason("cancel_reasons.not_enough_spirituality");
    public static CancelReason FOCUS_BROKEN = new CancelReason("cancel_reasons.focus_broken");
    public static CancelReason ALREADY_CASTING = new CancelReason("cancel_reasons.player_already_casting");

    String description = null;
    public CancelReason(String reason){
        this.description = reason;
    }
    public Component getTranslatedDescription(){
        return Component.translatable("lotm_craft.spells." + description);
    }
}
