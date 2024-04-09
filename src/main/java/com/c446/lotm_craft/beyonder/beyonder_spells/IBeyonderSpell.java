package com.c446.lotm_craft.beyonder.beyonder_spells;

import com.c446.lotm_craft.LotmCraft;
import com.c446.lotm_craft.beyonder.pathways.IPathway;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.HitResult;

import javax.swing.text.html.parser.Entity;
import java.util.logging.Level;

public interface IBeyonderSpell {
    int cost = 0;
    int madness_buildup = 0;
    int minimum_seq = -1;
    boolean canceled = false;
    String spellName = "";
    IPathway pathway = IPathway.instance;



    default String buildSpellName(IBeyonderSpell spell) {
        return (
                LotmCraft.MOD_ID
                        + ".spells."
                        + pathway.name
                        + spellName
        );
    }

    static SpellContext BuildSpellContext(LivingEntity player, HitResult target, Level level) {
        return new SpellContext();
    }


    void runSpell(Entity rayTraceResult, LivingEntity caster, SpellContext context, Level world);

/*    public void runSpellOnEntity(Entity rayTraceResult, LivingEntity caster, SpellContext context, Level world) {
    }

    public void runSpellOnBlock(BlockPos rayTraceResult, LivingEntity caster, SpellContext context, Level world) {
    }

    public final void runSpell(HitResult rayTraceResult, LivingEntity caster, SpellContext context, Level world) {

    }
*/
}
