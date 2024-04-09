package com.c446.lotm_craft.capabilities;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;

import java.util.ArrayList;

public class BeyonderCapability implements IBeyonderCapability {
    int sequence = 10;
    String pathway = "";
    int current_spirituality = 0;
    int max_spirituality = 0;
    int madness_tolerance = 100;
    int madness_buildup = 0;
    boolean concealed = false;
    boolean alternatePlane = false;
    boolean isMarionette = false;
    int selectedSpell = 0;
    ArrayList<String> spellList = new ArrayList<>();

    public void nextSpell() {
        selectedSpell++;
    }

    public void prevSpell() {
        selectedSpell--;
    }

    /*public IBeyonderSpell getCurrentSpell() {
        return(

                spellList.get(selectedSpell%spellList.size())

        );




        return null;
    }
*/

    public void calculateSpirituality(LivingEntity livingEntity) {


    }

    public void playerDeath() {
        if (sequence++ != 11) {
            sequence++;
        }
    }

    @Override
    public void CopyFrom(BeyonderCapability source) {
        sequence = source.sequence;
        pathway = source.pathway;
        current_spirituality = source.current_spirituality;
        max_spirituality = source.max_spirituality;
        madness_buildup = source.madness_buildup;
        madness_tolerance = source.madness_tolerance;
        concealed = source.concealed;
        alternatePlane = source.alternatePlane;
        isMarionette = source.isMarionette;
    }

    @Override
    public void saveNBTData(CompoundTag nbt) {
        // this function's purpose is to save the player's stats as well as other things in a NBT file so that it can easily be saved.
        nbt.putInt("sequence", this.sequence);
        nbt.putInt("cur_spirit", this.current_spirituality);
        nbt.putInt("max_spirit", this.max_spirituality);
        nbt.putInt("madness", this.madness_buildup);
        nbt.putInt("tolerance", this.madness_tolerance);
        nbt.putBoolean("isMarionette", isMarionette);
        nbt.putString("pathway", pathway);
    }

    @Override
    public void loadNBTData(CompoundTag nbt) {
        // this function's goal is to obtain the player's stats from an NBT file.
        sequence = nbt.getInt("sequence");
        pathway = nbt.getString("pathway");
        current_spirituality = nbt.getInt("cur_spirit");
        max_spirituality = nbt.getInt("max_spirit");
        madness_tolerance = nbt.getInt("tolerance");
        madness_buildup = nbt.getInt("madness");
        concealed = false;
        alternatePlane = false;
        isMarionette = nbt.getBoolean("isMarionette");
    }

    public int getSequence() {
        return sequence;
    }
}