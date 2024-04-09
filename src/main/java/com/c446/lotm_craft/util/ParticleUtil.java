package com.c446.lotm_craft.util;

import com.hollingsworth.arsnouveau.client.particle.ColorParticleTypeData;
import com.hollingsworth.arsnouveau.client.particle.ParticleColor;
import com.hollingsworth.arsnouveau.client.registry.ModParticles;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.world.phys.Vec3;

public class ParticleUtil {
    public static DustParticleOptions CreateDustParticle(int red, int green, int blue) {
        int color = ((red << 16) | (green << 8) | blue);
        return new DustParticleOptions(Vec3.fromRGB24(color).toVector3f(), 1.0F);
    }
}
