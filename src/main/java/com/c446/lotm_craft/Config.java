package com.c446.lotm_craft;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = LotmCraft.MOD_ID)
public class Config {
    public static ForgeConfigSpec.BooleanValue DUMMY_VAL_BOOL;
    public static ForgeConfigSpec SERVER_CONFIG;
    static {
        ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();

        SERVER_BUILDER.comment("SERVER CONFIGURATION");
        DUMMY_VAL_BOOL = SERVER_BUILDER.comment("this is a dummy value used for debugging").define("DUMMY_CONFIG_BOOL", true);

        SERVER_CONFIG = SERVER_BUILDER.build();
    }
}
