package com.c446.lotm_craft.capabilities;

import com.c446.lotm_craft.beyonder.beyonder_spells.IBeyonderSpell;
import com.c446.lotm_craft.beyonder.beyonder_spells.SpellRegistry;
import com.c446.lotm_craft.util.ParticleUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.server.ServerLifecycleHooks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class BeyonderCapability implements IBeyonderCapability {

    /*
     * "normal" classification
     * SEQ 9-8 : low level
     * SEQ 7-5 : middle level
     * SEQ 4-3 : saints
     * SEQ 2-1 : angels
     * SEQ 0 : true deity (will never be implemented)
     * SEQ -1 : great old one
     * SEQ -1.5 : pillar
     *
     * the classification that will be used when dealing with sequence will be the following :
     * 10-seq
     * that is to say that a seq 0 will be represented as a 10-0, while a seq 9 as 10-9.
     * this is to make it easier to do logical operations, such as comparing if the player's level is sufficient.
     * */
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

    public void selectedSpellUpdated() {
        this.selectedSpell = selectedSpell % spellList.size();
    }

    public void nextSpell() {
        selectedSpellUpdated();
        selectedSpell++;
    }

    public void prevSpell() {
        selectedSpellUpdated();
        selectedSpell--;
    }

    public IBeyonderSpell getCurrentSpell() {
        /**
         * THIS FUNCTION SHOULD WORK, PLEASE, PLEASE DON'T TOUCH IT...
         * */
        if ((SpellRegistry.getSpellFromName(spellList.get(spellList.size() % selectedSpell))) != null) {
            return (SpellRegistry.getSpellFromName(spellList.get(spellList.size() % selectedSpell)));
        }
        return null;
    }

    public void appendSpellToList(IBeyonderSpell spell) {
        if (spell.pathway.name.equals(this.pathway)) {
            spellList.add(spell.getName());
        } else {
            System.out.println("ERROR, ATTEMPTED TO ADD A SPELL FROM AN INVALID PATHWAY. CAUSE SPELL:\n" + spell.getName() + " != " + this.pathway);
        }
    }

    private static final HashMap<Integer, Integer> BEYONDER_SPIRITUALITY = new HashMap<>();

    static {
        BEYONDER_SPIRITUALITY.put(10, 0);
        BEYONDER_SPIRITUALITY.put(9, 100);
        BEYONDER_SPIRITUALITY.put(8, 200);
        BEYONDER_SPIRITUALITY.put(7, 400);
        BEYONDER_SPIRITUALITY.put(6, 600);
        BEYONDER_SPIRITUALITY.put(5, 800);
        BEYONDER_SPIRITUALITY.put(4, 1000);
        BEYONDER_SPIRITUALITY.put(3, 1500);
        BEYONDER_SPIRITUALITY.put(2, 3000);
        BEYONDER_SPIRITUALITY.put(1, 5000);
    }

    public int getSpiritualityForSeq(Integer sequence) {
        return BEYONDER_SPIRITUALITY.get(sequence);
    }

    public int getCurrentSpirituality() {
        return current_spirituality;
    }
    public void setCurrentSpirituality(int k){
        current_spirituality = k;
    }

    public void calculateSpiritualityRegen() {

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

    public void castSpell() {
        ServerLevel level;
        Player player = Minecraft.getInstance().player;
        level = (ServerLevel) ServerLifecycleHooks.getCurrentServer().getLevel(Objects.requireNonNull(Objects.requireNonNull(Minecraft.getInstance().player).level().dimension()));
        Objects.requireNonNull(level).sendParticles(ParticleUtil.CreateDustParticle(20, 100, 150), 1, 1, 1, 30, 0, 0, 0, 0);



        this.getCurrentSpell().spellEffect(player, level);



    }
}