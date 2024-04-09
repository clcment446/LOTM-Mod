package com.c446.lotm_craft.commands;


import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class CommandResetArcaneProgression {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("dummy_command").
                requires(sender -> sender.hasPermission(2))
                .executes(context -> resetsProgression(context.getSource())));
    }

    private static int resetsProgression(CommandSourceStack source) {
        return 0;
    }
}