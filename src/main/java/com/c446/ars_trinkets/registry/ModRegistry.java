package com.c446.ars_trinkets.registry;

import com.c446.ars_trinkets.LotmCraft;
import com.c446.ars_trinkets.perks.PerkAttributes;
import com.hollingsworth.arsnouveau.api.perk.IPerk;
import com.hollingsworth.arsnouveau.api.registry.PerkRegistry;
import com.hollingsworth.arsnouveau.api.sound.SpellSound;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


public class ModRegistry {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, LotmCraft.MOD_ID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, LotmCraft.MOD_ID);
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, LotmCraft.MOD_ID);

    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, LotmCraft.MOD_ID);
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, LotmCraft.MOD_ID);


    public static void registerRegistries(IEventBus bus) {
        BLOCKS.register(bus);
        ITEMS.register(bus);
        SOUNDS.register(bus);
        ENTITIES.register(bus);
        EFFECTS.register(bus);
        PerkAttributes.ATTRIBUTES.register(bus);

    }

    //    public static final RegistryObject<Item> EXAMPLE;




    public static void registerPerk(IPerk perk) {
        PerkRegistry.registerPerk(perk);
    }

    public static SpellSound EXAMPLE_SPELL_SOUND;

    static {

// PUT ITEMS HERE
    }
}
