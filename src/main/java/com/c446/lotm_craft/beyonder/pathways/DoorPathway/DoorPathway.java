package com.c446.lotm_craft.beyonder.pathways.DoorPathway;

import com.c446.lotm_craft.beyonder.pathways.IPathway;
import net.minecraft.network.chat.Component;

import java.util.HashMap;

public class DoorPathway extends IPathway {
    public static final HashMap<Integer, Integer> spiritualityMap = new HashMap<>();
    static{
        spiritualityMap.put(10,0);
        spiritualityMap.put(9,0);
        spiritualityMap.put(8,0);
        spiritualityMap.put(6,0);
        spiritualityMap.put(5,0);
        spiritualityMap.put(4,0);
        spiritualityMap.put(3,0);
        spiritualityMap.put(2,0);
        spiritualityMap.put(1,0);
        spiritualityMap.put(0,0);
    }

    public static DoorPathway Instance = new DoorPathway(Component.translatable("lotm_craft.pathways.door.NAME").toString(),0,0,0,spiritualityMap);
    public static String name = "DOOR";
    public static Component TranslatedName = Component.translatable("lotm_craft.pathways.door.NAME");

    public DoorPathway(String name, int r, int g, int b, HashMap<Integer, Integer> spiritMap) {



        super(name, r, g, b, spiritMap);
    }
}
