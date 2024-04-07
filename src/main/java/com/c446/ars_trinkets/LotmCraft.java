package com.c446.ars_trinkets;

import com.c446.ars_trinkets.capabilities.BeyonderCapability;
import com.c446.ars_trinkets.registry.ModRegistry;
import com.c446.ars_trinkets.util.SetInterval;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


// The value here should match an entry in the META-INF/mods.toml file
@Mod(LotmCraft.MOD_ID)
public class LotmCraft {
    public static final String MOD_ID = "lotm_craft";

    private static final Logger LOGGER = LogManager.getLogger();

    public LotmCraft() {
        IEventBus modbus = FMLJavaModLoadingContext.get().getModEventBus();
        ModRegistry.registerRegistries(modbus);
        modbus.addListener(this::doClientStuff);
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.SERVER_CONFIG);
        
        MinecraftForge.EVENT_BUS.register(this);
    }

    public static ResourceLocation prefix(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

    private void doClientStuff(final FMLClientSetupEvent event) {

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

//    @SubscribeEvent
//    public void doTabThings(BuildCreativeModeTabContentsEvent event) {
//        if (event.getTab() == CreativeTabRegistry.BLOCKS.get()) {
//            for (var item : ModRegistry.ITEMS.getEntries()) {
//                event.accept(item::get);
//            }
//        }
//    }
    @SubscribeEvent
    public void doCapabilities(RegisterCapabilitiesEvent event){
        event.register(BeyonderCapability.class);
    }
    public static void setInterval(Runnable method, int tickInterval, int timeToLive){
        MinecraftForge.EVENT_BUS.register(new SetInterval(method, tickInterval, timeToLive));
    }
}

