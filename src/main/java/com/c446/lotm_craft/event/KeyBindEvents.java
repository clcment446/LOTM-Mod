package com.c446.lotm_craft.event;

import com.c446.lotm_craft.LotmCraft;
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
            Minecraft.getInstance().player.getCapability(BeyonderCapabilityAttacher.BeyonderCapabilityProvider.BEYONDER).ifPresent(c->{
                c.prevSpell

            });
        }




    }
}
