package net.davegamer007vinicius1232426.unnamedscpmod.particle.custom;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class EternalEmbers extends TextureSheetParticle {
    protected EternalEmbers(ClientLevel pLevel, double pX, double pY, double pZ,
                            SpriteSet pSprites, double xd, double yd, double zd) {
        super(pLevel, pX, pY, pZ, xd, yd, zd);

        this.friction = 1;
        this.xd = xd;
        this.yd = yd;
        this.zd = yd;
        this.quadSize *= 0.8f;
        this.lifetime = 32;
        this.setSpriteFromAge(pSprites);

        this.rCol = 1.0f;
        this.bCol = 1.0f;
        this.gCol = 1.0f;

    }

    @Override
    public void tick(){
        super.tick();
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_LIT;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet spriteSet) {
            this.sprites = spriteSet;
        }

        public Particle createParticle(SimpleParticleType particleType, ClientLevel level,
                                       double x, double y, double z,
                                       double dx, double dy, double dz) {
            return new EternalEmbers(level, x, y, z, this.sprites, dx, dy, dz);
        }
    }
}
