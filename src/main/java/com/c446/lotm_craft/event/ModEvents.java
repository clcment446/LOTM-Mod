package com.c446.lotm_craft.event;

import com.c446.lotm_craft.LotmCraft;
import com.c446.lotm_craft.capabilities.BeyonderCapabilityAttacher;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

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

}
