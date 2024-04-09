package com.c446.lotm_craft.beyonder.pathways;

import com.c446.lotm_craft.beyonder.beyonder_spells.IBeyonderSpell;
import com.c446.lotm_craft.beyonder.pathways.DoorPathway.DoorPathway;

import java.util.ArrayList;
import java.util.HashMap;

public class IPathway {
    public static IPathway instance = new IPathway("test_pathway", 0, 0, 0);
    public String name = "";
    static int r = 0;
    static int g = 0;
    static int b = 0;
    static private final HashMap<String, IPathway> PathwayIdentifiers = new HashMap<>();
    static public HashMap<Integer, IBeyonderSpell> spellIdentifiers = new HashMap<>();
    public static HashMap<Integer, Integer> spiritualityForSeq = new HashMap<Integer, Integer>();
    ArrayList<IBeyonderSpell> spells = new ArrayList<>();

    public IPathway(String name, int red, int green, int blue) {
        name = name;
        r = red;
        g = green;
        b = blue;
    }

    static {
        PathwayIdentifiers.put(DoorPathway.name, DoorPathway.Instance);
    }

    public static IPathway getPathway(String name) {
        if (PathwayIdentifiers.get(name) == null) {
            return IPathway.instance;
        } else {
            return PathwayIdentifiers.get(name);
        }
    }

    public static int[] getRGBColor() {
        return new int[]{r, g, b};
    }
}