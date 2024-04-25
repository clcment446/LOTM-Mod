package com.c446.lotm_craft.beyonder.pathways.DoorPathway.DoorSpells;

import com.c446.lotm_craft.beyonder.beyonder_spells.IBeyonderSpell;
import com.c446.lotm_craft.beyonder.beyonder_spells.SpellContext;
import com.c446.lotm_craft.beyonder.pathways.DoorPathway.DoorPathway;
import com.c446.lotm_craft.beyonder.pathways.IPathway;
import com.c446.lotm_craft.event.spell_events.SpellCastEvent;
import com.c446.lotm_craft.util.Util;
import com.electronwill.nightconfig.core.io.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.property.Properties;

import java.util.ArrayList;

public class Flash implements IBeyonderSpell {
    int madness_buildup;
    int minimum_seq;
    String spellName;
    IPathway pathway;
    int cost;
    int spellSequence = 0;

    public static Flash INSTANCE = new Flash(15, 1, 8, "flash", DoorPathway.INSTANCE);

    public Flash(int cost, int madness_buildup, int minimum_seq, String name, IPathway pathway) {
        this.madness_buildup = madness_buildup;
        this.minimum_seq = minimum_seq;
        this.spellName = name;
        this.pathway = pathway;
        this.cost = cost;
    }

    @Override
    public String getName() {
        return IBeyonderSpell.super.buildSpellName(INSTANCE);
    }

    @Override
    public int getMinimumSeq() {
        return IBeyonderSpell.super.getMinimumSeq();
    }

    @Override
    public int getCost() {
        return IBeyonderSpell.super.getCost();
    }

    @Override
    public int getMadnessBuildup() {
        return IBeyonderSpell.super.getMadnessBuildup();
    }

    public static ArrayList<Entity> rayTrace(Vec3 source, int targetLimit, ServerLevel level) {
        /**
         * @Param targetLimit, an integer number, greater than 0.
         * @Param source, the source of the beam.
         * When @Param targetLimit = 0, only one entity will be given.
         * */

        for (int i = 0; i<120; i++){


            /*
            * let S, a vector
            * we need to find D_s, a line passing through the player's eye position, which follows the player's eye direction.
            *
            *
            * */
            level.getBlockEntity(new BlockPos((int)source.x, (int)source.y, (int)source.z));
        }

        return null;
    }




    @Override
    public void runSpell(LivingEntity caster, ServerLevel level) {
        HitResult target = Util.entityRayCast(level, caster, 20.0F,false);




        SpellContext context = new SpellContext(caster,target, level, INSTANCE);
        MinecraftForge.EVENT_BUS.post(new SpellCastEvent(context));
    }

    @Override
    public void spellEffect(SpellContext context) {
        ServerLevel level = (ServerLevel) context.world;
        Vec3 targetLocation = context.target.getLocation();
        level.sendParticles(ParticleTypes.FLASH, targetLocation.x, targetLocation.y, targetLocation.z, 0, 0, 0, 0, 0);


        for (Entity e : level.getEntities(context.caster, new AABB(context.target.getLocation().add(4,4,4), context.target.getLocation().subtract(4,4,4)))){
            if (e instanceof LivingEntity){
                ((LivingEntity) e).addEffect(new MobEffectInstance(MobEffects.BLINDNESS,10,0,false,false));

            }


        }

    }

    @Override
    public int getSpellCost() {
        return this.cost;
    }

    @Override
    public String getSpellIdentifier() {
        return IBeyonderSpell.super.getSpellIdentifier(INSTANCE);
    }
}
