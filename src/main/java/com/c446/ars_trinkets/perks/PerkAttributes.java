package com.c446.ars_trinkets.perks;

import com.c446.ars_trinkets.LotmCraft;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.UUID;
import java.util.function.Function;

@EventBusSubscriber(modid = LotmCraft.MOD_ID, bus = EventBusSubscriber.Bus.MOD)

public class PerkAttributes {
    public static final HashMap<RegistryObject<Attribute>, UUID> UUIDS = new HashMap();
    public static final DeferredRegister<Attribute> ATTRIBUTES;
    public static final RegistryObject<Attribute> BEYONDER_POWER;
    public PerkAttributes() {
    }

    public static RegistryObject<Attribute> registerAttribute(String name, Function<String, Attribute> attribute, String uuid) {
        return registerAttribute(name, attribute, UUID.fromString(uuid));
    }

    public static RegistryObject<Attribute> registerAttribute(String name, Function<String, Attribute> attribute, UUID uuid) {
        RegistryObject<Attribute> registryObject = ATTRIBUTES.register(name, () -> attribute.apply(name));
        UUIDS.put(registryObject, uuid);
        return registryObject;
    }

    @SubscribeEvent
    public static void modifyEntityAttributes(EntityAttributeModificationEvent event) {
        event.getTypes().stream().filter((e) -> e == EntityType.PLAYER).forEach((e) -> {
            ATTRIBUTES.getEntries().forEach((v) -> {
                event.add(e, v.get());
            });
        });
    }

    static {
        ATTRIBUTES = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, LotmCraft.MOD_ID);
        BEYONDER_POWER = registerAttribute("lotm_craft.perks.spell_power", (id) ->
                {
                    return (new RangedAttribute(id, 1.0, 0.0, 1024.0)).setSyncable(true);
                },
                "7777076e-83bd-4e5c-957e-c065c7027cdb");


    }
}
