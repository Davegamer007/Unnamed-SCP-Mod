package net.davegamer007vinicius1232426.unnamedscpmod.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

public class ThalassorexProjectile extends ThrownTrident {
    public ThalassorexProjectile(Level pLevel, LivingEntity pShooter, ItemStack pStack) {
        super(pLevel, pShooter, pStack);
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        Entity $$4 = this.getOwner();
        Entity hitEntity = pResult.getEntity();
        BlockPos hitBlock = hitEntity.blockPosition();
        float damage = 10.0F;
        Entity owner = this.getOwner();
        DamageSource $$5 = this.damageSources().trident(this, (Entity)(owner == null ? this : owner));
        SoundEvent $$6 = SoundEvents.TRIDENT_HIT;

        if (hitEntity.hurt($$5, damage)) {
            if (hitEntity.getType() == EntityType.ENDERMAN) {
                this.setDeltaMovement(this.getDeltaMovement().multiply(-0.01, -0.1, -0.01));
                return;
            } else {this.kill();}
        }
        float $$8 = 1.0F;
        if (this.level() instanceof ServerLevel && this.level().canSeeSky(hitBlock)) {
                LightningBolt lightningBolt = (LightningBolt)EntityType.LIGHTNING_BOLT.create(this.level());
                if (lightningBolt != null) {
                    lightningBolt.moveTo(Vec3.atBottomCenterOf(hitBlock));
                    lightningBolt.setCause(owner instanceof ServerPlayer ? (ServerPlayer)owner : null);
                    this.level().addFreshEntity(lightningBolt);
                    $$6 = SoundEvents.TRIDENT_THUNDER;
                    $$8 = 5.0F;
            }
            this.playSound($$6, $$8, 1.0F);
        }
        super.onHitEntity(pResult);
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        if (this.level() instanceof ServerLevel && this.level().canSeeSky(this.getOnPos())) {
            LightningBolt lightningBolt = (LightningBolt)EntityType.LIGHTNING_BOLT.create(this.level());
            if (lightningBolt != null) {
                lightningBolt.moveTo(Vec3.atBottomCenterOf(this.getOnPos()));
                lightningBolt.setCause(this.getOwner() instanceof ServerPlayer ? (ServerPlayer)this.getOwner() : null);
                this.level().addFreshEntity(lightningBolt);
                this.playSound(SoundEvents.TRIDENT_THUNDER,5.0F, 1.0F);
            }
            this.kill();
        }
    }
}
