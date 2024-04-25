package com.c446.lotm_craft.event;

import com.c446.lotm_craft.LotmCraft;
import com.c446.lotm_craft.capabilities.BeyonderCapability;
import com.c446.lotm_craft.capabilities.BeyonderCapabilityAttacher;
import com.c446.lotm_craft.key_binds.KeyBindings;
import net.minecraft.client.Minecraft;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = LotmCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class KeyBindEvents {
    @SubscribeEvent
    public static void clientTickingEvent(TickEvent.ClientTickEvent event){
        if(KeyBindings.INSTANCE.prevSpell.isDown())
        {
            KeyBindings.INSTANCE.prevSpell.consumeClick();
            if ((Minecraft.getInstance().player != null)) {
                Minecraft.getInstance().player.getCapability(BeyonderCapabilityAttacher.BeyonderCapabilityProvider.BEYONDER).ifPresent(BeyonderCapability::prevSpell);
            } else {
                System.out.println("PLAYER IS NULL. IGNORING KEYPRESS.");
            }
        }
        if(KeyBindings.INSTANCE.nextSpell.isDown())
        {
            KeyBindings.INSTANCE.nextSpell.consumeClick();
            if ((Minecraft.getInstance().player != null)) {
                Minecraft.getInstance().player.getCapability(BeyonderCapabilityAttacher.BeyonderCapabilityProvider.BEYONDER).ifPresent(BeyonderCapability::nextSpell);
            } else {
                System.out.println("PLAYER IS NULL. IGNORING KEYPRESS.");
            }
        }
        if(KeyBindings.INSTANCE.useSpell.isDown())
        {
            KeyBindings.INSTANCE.useSpell.consumeClick();
            if ((Minecraft.getInstance().player != null)) {
                Minecraft.getInstance().player.getCapability(BeyonderCapabilityAttacher.BeyonderCapabilityProvider.BEYONDER).ifPresent(BeyonderCapability::castSpell);
            } else {
                System.out.println("PLAYER IS NULL. IGNORING KEYPRESS.");
            }
        }

    }
}
