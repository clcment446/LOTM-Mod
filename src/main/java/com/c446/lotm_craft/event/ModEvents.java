package com.c446.lotm_craft.event;

import com.c446.lotm_craft.LotmCraft;
import com.c446.lotm_craft.capabilities.BeyonderCapabilityAttacher;
import com.c446.lotm_craft.event.spell_events.SpellCastEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import static com.c446.lotm_craft.beyonder.beyonder_spells.IBeyonderSpell.consumeSpirituality;

@Mod.EventBusSubscriber(modid = LotmCraft.MOD_ID)
public class ModEvents {
    @SubscribeEvent
    public static void commandRegister(RegisterCommandsEvent event) {

    }

    @SubscribeEvent
    public static void attachCapabilities(final AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            BeyonderCapabilityAttacher.attach(event);
        }
    }

    @SubscribeEvent
    public static void spellCasted(SpellCastEvent event) {
        AtomicInteger k = new AtomicInteger();
        AtomicInteger seq = new AtomicInteger();

        event.getContext().caster.getCapability(BeyonderCapabilityAttacher.BeyonderCapabilityProvider.BEYONDER).ifPresent(a -> {
            seq.set(a.getSequence());
            k.set(a.getSpiritualityForSeq(seq.get()));
        });

        if (event.getSpell().getSpellCost() <= k.get() && event.getSpell().getMinimumSeq() <= seq.get()) {
            event.getSpell().spellEffect(event.getContext());
            consumeSpirituality(event.getContext());
        }
    }
}
