package net.davegamer007vinicius1232426.unnamedscpmod.entity.goals;

import net.davegamer007vinicius1232426.unnamedscpmod.util.SweetList;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.EnumSet;

public class chaseSweetGoal extends Goal {
    private static final TargetingConditions TEMP_TARGETING = TargetingConditions.forNonCombat().range(10.0).ignoreLineOfSight();
    private final TargetingConditions targetingConditions;
    protected final PathfinderMob mob;
    private double px;
    private double py;
    private double pz;
    private double pRotX;
    private double pRotY;
    private final double speedModifier;

    @Nullable
    protected Player player;
    private int calmDown;
    private boolean isRunning;

    public chaseSweetGoal(PathfinderMob pMob, double pSpeedModifier) {
        this.targetingConditions = TEMP_TARGETING.copy().selector(this::shouldFollow);
        this.speedModifier = pSpeedModifier;
        this.mob = pMob;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }


    private boolean shouldFollow(LivingEntity pTarget) {
        if (pTarget instanceof Player){this.player = (Player) pTarget;}

        if (this.player == null){
            return false;
        } else {
            Item pHandItem = this.player.getMainHandItem().getItem();
            Item pOffHandItem = this.player.getOffhandItem().getItem();

            return SweetList.sweetList.contains(pHandItem) || SweetList.sweetList.contains(pOffHandItem);
        }
    }

    @Override
    public boolean canUse() {
        if (this.calmDown > 0) {
            --this.calmDown;
            return false;
        } else {
            this.player = this.mob.level().getNearestPlayer(this.targetingConditions, this.mob);
            return this.player != null;
        }
    }

    @Override
    public void start() {
        this.px = this.player.getX();
        this.py = this.player.getY();
        this.pz = this.player.getZ();
        this.isRunning = true;
    }

    @Override
    public void stop() {
        this.player = null;
        this.mob.getNavigation().stop();
        this.calmDown = reducedTickDelay(100);
        this.isRunning = false;
    }


    @Override
    public void tick() {
        this.mob.getLookControl().setLookAt(this.player, (float)(this.mob.getMaxHeadYRot() + 20), (float)this.mob.getMaxHeadXRot());
        if (this.mob.distanceToSqr(this.player) < 6.25) {
            this.mob.getNavigation().stop();
        } else {
            this.mob.getNavigation().moveTo(this.player, this.speedModifier);
        }

    }
}
