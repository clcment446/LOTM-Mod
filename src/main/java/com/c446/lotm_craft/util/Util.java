package com.c446.lotm_craft.util;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.*;
import net.minecraftforge.entity.PartEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;


public class Util {
    ArrayList<Item> focus = new ArrayList<>();

    public static BlockHitResult getTargetOld(ServerLevel level, Player player, ClipContext.Fluid clipContext, double reach) {
        float f = player.getXRot();
        float f1 = player.getYRot();
        Vec3 vec3 = player.getEyePosition();
        float f2 = Mth.cos(-f1 * ((float) Math.PI / 180F) - (float) Math.PI);
        float f3 = Mth.sin(-f1 * ((float) Math.PI / 180F) - (float) Math.PI);
        float f4 = -Mth.cos(-f * ((float) Math.PI / 180F));
        float f5 = Mth.sin(-f * ((float) Math.PI / 180F));
        float f6 = f3 * f4;
        float f7 = f2 * f4;
        Vec3 vec31 = vec3.add((double) f6 * reach, (double) f5 * reach, (double) f7 * reach);
        return level.clip(new ClipContext(vec3, vec31, ClipContext.Block.OUTLINE, clipContext, player));
    }

    public static BlockHitResult getTargetBlock(ServerLevel level, LivingEntity entity, ClipContext.Fluid clipContext, double reach) {
        var rotation = entity.getLookAngle().normalize().scale(reach);
        var pos = entity.getEyePosition();
        var dest = rotation.add(pos);
        return level.clip(new ClipContext(pos, dest, ClipContext.Block.COLLIDER, clipContext, entity));
    }
    public static void CreateParticleBeam(Vec3 start, Vec3 end, ServerLevel level, SimpleParticleType particle) {
        /**
         * Creates a beam of particles between two points.
         * @param start: Vec3; the vector that represents the start of the line.
         * @param end: Vec3;  the vector that represents the end of the line.
         * @param color: ParticleColor; the particle color of the beam.
         * @param increment: double; how many blocks to increment the beam by.
         * This function was copied and modified from the Too Many Glyphs project. Github Repo here : https://github.com/DerringersMods/TooManyGlyphs/blob/1.19.x/src/main/java/io/github/derringersmods/toomanyglyphs/common/network/PacketRayEffect.java#L29.
         * */

        double distance = start.distanceTo(end);
        double traceStart = 0.0, increment = 1.0 / 16;
        for (double d = traceStart; d < distance; d += increment) {
            double fractionalDistance = d / distance;
            level.sendParticles(particle,
                    Mth.lerp(fractionalDistance, start.x, end.x),
                    Mth.lerp(fractionalDistance, start.y, end.y),
                    Mth.lerp(fractionalDistance, start.z, end.z)
                    , 100, 0, 0, 0, 1);
        }
    }

    public static void CreateParticleBeam(Vec3 start, Vec3 end, ServerLevel level, SimpleParticleType particle, double increment) {
        /**
         * Creates a beam of particles between two points.
         * @param start: Vec3; the vector that represents the start of the line.
         * @param end: Vec3;  the vector that represents the end of the line.
         * @param color: ParticleColor; the particle color of the beam.
         * @param increment: double; how many blocks to increment the beam by.
         * This function was copied and modified from the Too Many Glyphs project. Github Repo here : https://github.com/DerringersMods/TooManyGlyphs/blob/1.19.x/src/main/java/io/github/derringersmods/toomanyglyphs/common/network/PacketRayEffect.java#L29.
         * */


        double distance = start.distanceTo(end);
        double traceStart = 0.0;
        for (double d = traceStart; d < distance; d += increment) {
            double fractionalDistance = d / distance;
            level.sendParticles(particle,
                    Mth.lerp(fractionalDistance, start.x, end.x),
                    Mth.lerp(fractionalDistance, start.y, end.y),
                    Mth.lerp(fractionalDistance, start.z, end.z)
                    , 100, 0, 0, 0, 1);
        }
    }

    public static void CreateParticleBeam(Vec3 start, Vec3 end, ServerLevel level, SimpleParticleType particle, double increment, int count) {
        /**
         * Creates a beam of particles between two points.
         * @param start: Vec3; the vector that represents the start of the line.
         * @param end: Vec3;  the vector that represents the end of the line.
         * @param color: ParticleColor; the particle color of the beam.
         * @param increment: double; how many blocks to increment the beam by.
         * This function was copied and modified from the Too Many Glyphs project. Github Repo here : https://github.com/DerringersMods/TooManyGlyphs/blob/1.19.x/src/main/java/io/github/derringersmods/toomanyglyphs/common/network/PacketRayEffect.java#L29.
         * */


        double distance = start.distanceTo(end);
        double traceStart = 0.0;
        for (double d = traceStart; d < distance; d += increment) {
            double fractionalDistance = d / distance;
            level.sendParticles(particle,
                    Mth.lerp(fractionalDistance, start.x, end.x),
                    Mth.lerp(fractionalDistance, start.y, end.y),
                    Mth.lerp(fractionalDistance, start.z, end.z)
                    , count, 0, 0, 0, 1);
        }
    }

    public static long randomLongFromRange(int lower, int upper) {
        return (lower + (long) (Math.random() * (upper - lower)));
    }




    //The code bellow comes from the Iron's Spells and Spellbooks mod.



    public static HitResult checkEntityIntersecting(Entity entity, Vec3 start, Vec3 end, float bbInflation) {
        Vec3 hitPos = null;
        if (entity.isMultipartEntity()) {
            for (PartEntity p : entity.getParts()) {
                if (p != null) {
                    var hit = p.getBoundingBox().inflate(bbInflation).clip(start, end).orElse(null);
                    if (hit != null) {
                        hitPos = hit;
                        break;
                    }
                }
            }
        } else {
            hitPos = entity.getBoundingBox().inflate(bbInflation).clip(start, end).orElse(null);
        }
        if (hitPos != null)
            return new EntityHitResult(entity, hitPos);
        else
            return BlockHitResult.miss(end, Direction.UP, new BlockPos((int) end.x, (int) end.y, (int) end.z));

    }


    private static HitResult rayCast(ServerLevel level, Entity startEntity, Vec3 start, Vec3 end, boolean affectBlocks, float inflateArea, Predicate<? super Entity> entityFilter) {
        BlockHitResult blockRes = null;
        if (affectBlocks) {
            blockRes = level.clip(new ClipContext(start, end, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, startEntity));
            end = blockRes.getLocation();
        }
        AABB inflRange = startEntity.getBoundingBox().expandTowards(end.subtract(start));

        List<HitResult> hits = new ArrayList<>();
        List<? extends Entity> resultEntities = level.getEntities(startEntity, inflRange, entityFilter);
        for (Entity e : resultEntities) {
            HitResult hit = checkEntityIntersecting(e, start, end, inflateArea);
            if (hit.getType() != HitResult.Type.MISS)
                hits.add(hit);
        }

        if (!hits.isEmpty()) {
            hits.sort((o1, o2) -> o1.getLocation().distanceToSqr(start) < o2.getLocation().distanceToSqr(start) ? -1 : 1);
            return hits.get(0);
        } else if (affectBlocks) {
            return blockRes;
        }
        return BlockHitResult.miss(end, Direction.UP, new BlockPos((int) end.x, (int) end.y, (int) end.z));
    }





    public static HitResult entityRayCast(ServerLevel l, Entity startEntity, float dist, boolean checkForBlocks) {
        Vec3 start = startEntity.getEyePosition();
        Vec3 end = startEntity.getLookAngle().normalize().scale(dist).add(start);

        return entityRayCast(l, startEntity, start, end, checkForBlocks);
    }

    public static HitResult entityRayCast(ServerLevel l, Entity startEntity, float dist, boolean affectBlocks, float infl) {
        Vec3 start = startEntity.getEyePosition();
        Vec3 end = startEntity.getLookAngle().normalize().scale(dist).add(start);

        return rayCast(l, startEntity, start, end, affectBlocks, infl, Util::rayCastCanAffectEntityE);
    }

    public static HitResult entityRayCast(ServerLevel l, Entity startEntity, Vec3 start, Vec3 end, boolean affectBlocks) {
        return rayCast(l, startEntity, start, end, affectBlocks, 0, Util::rayCastCanAffectEntityE);
    }

    private static boolean rayCastCanAffectEntityE(Entity e) {
        // check if the entity is rayCast-able
        return e.isPickable() && e.isAlive();
    }


    public static HitResult entityRayCast(ServerLevel level, Entity originEntity, Vec3 start, Vec3 end, boolean checkForBlocks, float bbInflation, Predicate<? super Entity> filter) {
        return rayCast(level, originEntity, start, end, checkForBlocks, bbInflation, filter);
    }

    public static HitResult entityRayCastForClass(ServerLevel level, Entity originEntity, Vec3 start, Vec3 end, boolean checkForBlocks, Class<? extends Entity> c) {
        return rayCast(level, originEntity, start, end, checkForBlocks, 0, (entity) -> entity.getClass() == c);
    }

    public static void CreateParticleBeam(Vec3 start, Vec3 end, ServerLevel level, int r, int g, int b) {
        /**
         * Creates a beam of particles between two points.
         * @param start: Vec3; the vector that represents the start of the line.
         * @param end: Vec3;  the vector that represents the end of the line.
         * @param color: ParticleColor; the particle color of the beam.
         * This function was copied and modified from the Too Many Glyphs project. Github Repo here : https://github.com/DerringersMods/TooManyGlyphs/blob/1.19.x/src/main/java/io/github/derringersmods/toomanyglyphs/common/network/PacketRayEffect.java#L29.
         * */

        double distance = start.distanceTo(end);
        double traceStart = 0.0, increment = 1.0 / 16;
        for (double d = traceStart; d < distance; d += increment) {
            double fractionalDistance = d / distance;
            double speedCoefficient = Mth.lerp(fractionalDistance, 0.2, 0.001);
            level.addParticle(
                    ParticleUtil.CreateDustParticle(r, g, b),
                    Mth.lerp(fractionalDistance, start.x, end.x),
                    Mth.lerp(fractionalDistance, start.y, end.y),
                    Mth.lerp(fractionalDistance, start.z, end.z),
                    (level.random.nextFloat() - 0.5) * speedCoefficient,
                    (level.random.nextFloat() - 0.5) * speedCoefficient,
                    (level.random.nextFloat() - 0.5) * speedCoefficient);
        }
    }



}