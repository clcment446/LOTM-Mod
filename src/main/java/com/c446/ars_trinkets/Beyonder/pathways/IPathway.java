package com.c446.ars_trinkets.Beyonder.pathways;

import com.c446.ars_trinkets.Beyonder.beyonder_spells.IBeyonderSpell;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentContents;

import java.util.ArrayList;
import java.util.HashMap;

public interface IPathway {
    String name = "";
    HashMap<Integer, Integer> spiritualityForSeq = new HashMap<Integer, Integer>();
    ArrayList<IBeyonderSpell> spells = new ArrayList<IBeyonderSpell>();



}
