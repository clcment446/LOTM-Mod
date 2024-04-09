package com.c446.lotm_craft.event;

import com.c446.lotm_craft.LotmCraft;
import com.c446.lotm_craft.key_binds.KeyBindings;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = LotmCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientHandler {
    @SubscribeEvent
    public static void registerKeyMapping(RegisterKeyMappingsEvent event){
            event.register(KeyBindings.INSTANCE.nextSpell);
            event.register(KeyBindings.INSTANCE.prevSpell);
            event.register(KeyBindings.INSTANCE.useSpell);
    }
}
