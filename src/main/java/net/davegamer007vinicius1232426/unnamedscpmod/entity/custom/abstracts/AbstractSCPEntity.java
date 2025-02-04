package net.davegamer007vinicius1232426.unnamedscpmod.entity.custom.abstracts;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;

public abstract class AbstractSCPEntity extends Animal {


    protected AbstractSCPEntity(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    public void tick() {
        super.tick();
        float YSpeed = (this.fallDistance + 5) / 50f;
        if (this.getY() <= -64) {
            this.setDeltaMovement(0, YSpeed, 0);
            this.unRide();
        }
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }
}
