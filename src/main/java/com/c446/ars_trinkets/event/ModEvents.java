package com.c446.ars_trinkets.event;

import com.c446.ars_trinkets.LotmCraft;
import com.c446.ars_trinkets.capabilities.BeyonderCapabilityAttacher;
import com.c446.ars_trinkets.commands.CommandResetArcaneProgression;
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
        CommandResetArcaneProgression.register(event.getDispatcher());
        SetArcaneProgression.register(event.getDispatcher());
    }

    @SubscribeEvent
    public static void attachCapabilities(final AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            BeyonderCapabilityAttacher.attach(event);
        }
    }

}
