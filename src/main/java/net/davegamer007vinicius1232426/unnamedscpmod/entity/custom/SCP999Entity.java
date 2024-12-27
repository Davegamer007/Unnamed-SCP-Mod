package net.davegamer007vinicius1232426.unnamedscpmod.entity.custom;

import net.davegamer007vinicius1232426.unnamedscpmod.UnnamedSCPMod;
import net.davegamer007vinicius1232426.unnamedscpmod.effect.ModEffects;
import net.davegamer007vinicius1232426.unnamedscpmod.entity.custom.abstracts.AbstractSCPEntity;
import net.davegamer007vinicius1232426.unnamedscpmod.entity.goals.chaseSweetGoal;
import net.davegamer007vinicius1232426.unnamedscpmod.item.ModItems;
import net.davegamer007vinicius1232426.unnamedscpmod.util.SweetList;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Predicate;

public class SCP999Entity extends AbstractSCPEntity {
    public SCP999Entity(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setCanPickUpLoot(true);
        this.isInvulnerableTo(this.damageSources().fall());
        this.isInvulnerableTo(this.damageSources().hotFloor());
        this.isInvulnerableTo(this.damageSources().lightningBolt());
        this.isInvulnerableTo(this.damageSources().cramming());
        this.isInvulnerableTo(this.damageSources().onFire());
        this.isInvulnerableTo(this.damageSources().drown());
        this.isInvulnerableTo(this.damageSources().cactus());
        this.isInvulnerableTo(this.damageSources().lava());
    }
    public static final Predicate<ItemEntity> ALLOWED_ITEMS;

    public static final EntityDataAccessor<Boolean> IDDLE =
            SynchedEntityData.defineId(SCP999Entity.class, EntityDataSerializers.BOOLEAN);

    public static final EntityDataAccessor<Boolean> SPINNING =
            SynchedEntityData.defineId(SCP999Entity.class, EntityDataSerializers.BOOLEAN);

    public static final EntityDataAccessor<Boolean> DANCING =
            SynchedEntityData.defineId(SCP999Entity.class, EntityDataSerializers.BOOLEAN);

    public static final EntityDataAccessor<Boolean> SQUASHED =
            SynchedEntityData.defineId(SCP999Entity.class, EntityDataSerializers.BOOLEAN);

    public static final EntityDataAccessor<Boolean> PONG =
            SynchedEntityData.defineId(SCP999Entity.class, EntityDataSerializers.BOOLEAN);

    public static final EntityDataAccessor<Boolean> IN_AND_OUT =
            SynchedEntityData.defineId(SCP999Entity.class, EntityDataSerializers.BOOLEAN);

    public static final EntityDataAccessor<Boolean> SLEEP =
            SynchedEntityData.defineId(SCP999Entity.class, EntityDataSerializers.BOOLEAN);

    public static final EntityDataAccessor<Boolean> BLINK =
            SynchedEntityData.defineId(SCP999Entity.class, EntityDataSerializers.BOOLEAN);



    private BlockPos jukebox;

    public static final AnimationState idleState = new AnimationState();
    private int idleStateTimeOut = 0;

    public static final AnimationState spinState = new AnimationState();
    private int spinStateTimeout = 0;

    public static final AnimationState danceState = new AnimationState();
    private int danceStateTimeout = 0;

    public static final AnimationState squashedState = new AnimationState();
    private int squashedStateTimeout = 0;

    public static final AnimationState pongState = new AnimationState();
    private int pongStateTimeout = 0;

    public static final AnimationState inAndOutState = new AnimationState();
    private int inAndOutTimeout = 0;

    public static final AnimationState sleepState = new AnimationState();
    private int sleepStateTimeout = 0;

    public static final AnimationState blinkState = new AnimationState();
    private int blinkStateTimeout = 0;







    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SPINNING, false);
        this.entityData.define(DANCING, false);
        this.entityData.define(SQUASHED, false);
        this.entityData.define(PONG, false);
        this.entityData.define(SLEEP, false);
        this.entityData.define(BLINK, false);
        this.entityData.define(IN_AND_OUT, false);
        this.entityData.define(IDDLE, false);
    }




    @Override
    public void setRecordPlayingNearby(BlockPos pPos, boolean pIsPartying) {
        this.jukebox = pPos;
        setDancing(pIsPartying);
    }

    public boolean isDancing(){
        return this.entityData.get(DANCING);
    }
    public void setDancing(boolean pDancing){this.entityData.set(DANCING, pDancing);}

    public boolean isSpinning(){
        return this.entityData.get(SPINNING);
    }
    public void setSpin(boolean isSpinning){
        this.entityData.set(SPINNING, isSpinning);
    }

    public boolean isSquashed() {return this.entityData.get(SQUASHED);}
    public void setSquashed(boolean isSquashed){this.entityData.set(SQUASHED, isSquashed);}

    public boolean isPong() {return this.entityData.get(PONG);}
    public void setPong(boolean isPong){this.entityData.set(PONG, isPong);}

    public boolean isInAndOut(){return this.entityData.get(IN_AND_OUT);}
    public void setInAndOut(boolean isInAndOut){this.entityData.set(IN_AND_OUT, isInAndOut);}

    public boolean isSleep(){return this.entityData.get(SLEEP);}
    public void setSleep(boolean isSleep){this.entityData.set(SLEEP, isSleep);}

    public boolean isBlink(){return this.entityData.get(SLEEP);}
    public void setBlink(boolean isSleep){this.entityData.set(SLEEP, isSleep);}

    public boolean isIddle(){return this.entityData.get(IDDLE);}
    public void setIddle(boolean isSleep){this.entityData.set(IDDLE, isSleep);}

    @Override
    public void tick() {
        super.tick();
        if (this.getHealth() < getMaxHealth()){
            this.heal(20f);
        }
        setIddle(!(isSleep()||isInAndOut()||isBlink()||isPong()||isDancing()));
        if (this.level().isClientSide){
            setAnimationStates();
        }
    }

    private void setAnimationStates() {
        if (this.isIddle() && this.idleStateTimeOut <= 0){
            this.idleStateTimeOut = this.random.nextInt(40) + 80;
            this.idleState.start(this.tickCount);}
        else {--this.idleStateTimeOut;}
        if (!this.isIddle()){
            idleState.stop();
        }


        if (this.isSpinning() && this.spinStateTimeout <= 0){
            this.spinStateTimeout = 80;
            spinState.start(this.tickCount);}
        else {--this.spinStateTimeout;}
        if (!this.isSpinning()){
            spinState.stop();
        }


        if (this.isDancing() && this.danceStateTimeout <= 0){
            this.danceStateTimeout = 40;
            danceState.start(this.tickCount);}
        else {--this.spinStateTimeout;}
        if (!this.isDancing()){
            danceState.stop();
        }


        if (this.isSquashed() && this.squashedStateTimeout <= 0){
            this.squashedStateTimeout = 40;
            squashedState.start(this.tickCount);
        } else {--this.squashedStateTimeout;}
        if (!this.isSquashed()){
            squashedState.stop();
        }


        if (this.isPong() && this.pongStateTimeout <= 0){
            this.pongStateTimeout = 100;
            pongState.start(this.tickCount);
        } else {-- this.squashedStateTimeout;}
        if (!this.isPong()){
            pongState.stop();
        }


        if (this.isInAndOut() && this.pongStateTimeout <= 0){
            this.inAndOutTimeout = 40;
            inAndOutState.start(this.tickCount);
        } else {-- this.inAndOutTimeout;}
        if (!this.isInAndOut()){
            inAndOutState.stop();
        }

        if (this.isSleep() && this.sleepStateTimeout <= 0){
            this.sleepStateTimeout = 210;
            sleepState.start(this.tickCount);
        } else {-- this.sleepStateTimeout;}
        if (!this.isSleep()){
            sleepState.stop();
        }

        if (this.isBlink() && this.blinkStateTimeout <= 0){
            this.blinkStateTimeout = 30;
            blinkState.start(this.tickCount);
            idleState.stop();
        } else {-- this.blinkStateTimeout;}
        if (!this.isBlink()){
            blinkState.stop();
        }
    }





    @Override
    protected void updateWalkAnimation(float pPartialTick) {
        float f;
        if(this.getPose() == Pose.STANDING) {
            f = Math.min(pPartialTick * 6F, 1f);
        } else {
            f = 0f;
        }

        this.walkAnimation.update(f, 0.2f);
    }








    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SquashGoal());
        this.goalSelector.addGoal(0, new FloatGoal(this));

        this.goalSelector.addGoal(1, new SleepGoal());

        this.goalSelector.addGoal(2, new chaseSweetGoal(this, 1.2f));
        this.goalSelector.addGoal(2, new SerotoninGoal());

        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Villager.class, 4));
        this.goalSelector.addGoal(3, new PlayWithItemsGoal());
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class,6,100));

        this.goalSelector.addGoal(4, new OpenDoorGoal(this, false));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this,1.0));

        this.goalSelector.addGoal(5, new InOutGoal());
        this.goalSelector.addGoal(5, new SpinGoal());
        this.goalSelector.addGoal(5, new BlinkGoal());

        this.goalSelector.addGoal(6, new PongGoal());

        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(7, new FollowMobGoal(this, 1.0, 2, 6));
    }

    public static AttributeSupplier.Builder createAtributes(){
        SweetList.innitSweetList();
        return AbstractSCPEntity.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 20f)
                .add(Attributes.MOVEMENT_SPEED, 0.4f)
                .add(Attributes.FOLLOW_RANGE, 5D)
                .add(Attributes.ARMOR, 500)
                .add(Attributes.ARMOR_TOUGHNESS, 500);
    }





    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return null;
    }





    @Override
    public boolean canTakeItem(ItemStack pItemstack) {
        EquipmentSlot pHand = Mob.getEquipmentSlotForItem(pItemstack);
        if (!this.getItemBySlot(pHand).isEmpty()) {
            return false;
        } else {
            return pHand == EquipmentSlot.MAINHAND && super.canTakeItem(pItemstack);
        }
    }



    @Override
    protected void pickUpItem(ItemEntity pItemEntity) {
        if (this.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty()) {
            ItemStack pItem = pItemEntity.getItem();
            if (this.canHoldItem(pItem)) {
                this.onItemPickup(pItemEntity);
                this.setItemSlot(EquipmentSlot.MAINHAND, pItem);
                this.setGuaranteedDrop(EquipmentSlot.MAINHAND);
                this.take(pItemEntity, pItem.getCount());
                pItemEntity.discard();
            }
        }
    }

    static {
        ALLOWED_ITEMS = (itemEntity) -> {
            return !itemEntity.hasPickUpDelay();
        };
    }

    @Override
    public boolean canBeLeashed(Player pPlayer) {
        return false;
    }

    @Override
    public void aiStep() {
        if (this.jukebox == null || !this.jukebox.closerToCenterThan(this.position(), 5) || !this.level().getBlockState(this.jukebox).is(Blocks.JUKEBOX)) {
            setDancing(false);
            this.jukebox = null;
        }
        super.aiStep();
    }

    @Override
    public InteractionResult interactAt(Player pPlayer, Vec3 pVec, InteractionHand pHand) {
        pPlayer.addEffect(new MobEffectInstance(ModEffects.SEROTONIN_EFFECT.get(), 1200));
        return super.interactAt(pPlayer, pVec, pHand);
    }

    class PlayWithItemsGoal extends Goal{
        private int cooldown = 80;
        @Override
        public boolean canUse() {
            if (this.cooldown > SCP999Entity.this.tickCount) {
                return false;
            } else {
                //pItem entity is detecting the items near the Entity, the bounding box is the detection range
                List<ItemEntity> pDetectedItem = SCP999Entity.this.level().getEntitiesOfClass(ItemEntity.class, SCP999Entity.this.getBoundingBox().inflate(8.0,8.0,8.0), SCP999Entity.ALLOWED_ITEMS);
                return !pDetectedItem.isEmpty() || !SCP999Entity.this.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty();
            }
        }

        public void start() {
            List<ItemEntity> pDetectedItems = SCP999Entity.this.level().getEntitiesOfClass(ItemEntity.class, SCP999Entity.this.getBoundingBox().inflate(8.0, 8.0, 8.0), SCP999Entity.ALLOWED_ITEMS);
            if (!pDetectedItems.isEmpty()) {
                SCP999Entity.this.getNavigation().moveTo((Entity)pDetectedItems.get(0), 1);
            }
                this.cooldown = 0;
            }

            public void stop() {
            ItemStack pMainHand = SCP999Entity.this.getItemBySlot(EquipmentSlot.MAINHAND);
            if (!pMainHand.isEmpty()) {
                this.drop(pMainHand);
                SCP999Entity.this.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
                this.cooldown = SCP999Entity.this.tickCount + SCP999Entity.this.random.nextInt(100);
            }
        }

        public void tick() {
            List<ItemEntity> pDetectedItems = SCP999Entity.this.level().getEntitiesOfClass(ItemEntity.class, SCP999Entity.this.getBoundingBox().inflate(8.0, 8.0, 8.0), SCP999Entity.ALLOWED_ITEMS);
            ItemStack pHandItem = SCP999Entity.this.getItemBySlot(EquipmentSlot.MAINHAND);
            if (!pHandItem.isEmpty()) {
                this.drop(pHandItem);
                SCP999Entity.this.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
            } else if (!pDetectedItems.isEmpty()) {
                SCP999Entity.this.getNavigation().moveTo((Entity)pDetectedItems.get(0), 1);
            }
        }


        private void drop(ItemStack pStack) {
            if (!pStack.isEmpty()) {
                double yEye = SCP999Entity.this.getEyeY() - 0.30000001192092896;
                ItemEntity droppedItem = new ItemEntity(SCP999Entity.this.level(), SCP999Entity.this.getX(), yEye, SCP999Entity.this.getZ(), pStack);
                droppedItem.setPickUpDelay(40);
                droppedItem.setThrower(SCP999Entity.this.getUUID());
                droppedItem.setDeltaMovement(0,0.5f,0);
                if (!SweetList.sweetList.contains(pStack.getItem())){
                    SCP999Entity.this.level().addFreshEntity(droppedItem);
                } else {
                    for (int i = pStack.getCount(); i > 0; i--){
                        ItemEntity droppedGooItem = new ItemEntity(SCP999Entity.this.level(), SCP999Entity.this.getX(), yEye, SCP999Entity.this.getZ(), ModItems.SCP999GELLO.get().getDefaultInstance());
                        SCP999Entity.this.level().playLocalSound(SCP999Entity.this.getX(),SCP999Entity.this.getY(),SCP999Entity.this.getZ(),SoundEvents.DOLPHIN_EAT, SoundSource.VOICE, 5.0F,5.0F,false);
                        SCP999Entity.this.level().addFreshEntity(droppedGooItem);
                    }
                }
            }
        }
    }









    class SerotoninGoal extends Goal{

        @Override
        public boolean canUse() {
            MobEffect pEffect = ModEffects.SEROTONIN_EFFECT.get();
            List<Villager> pDetectedVillagers = SCP999Entity.this.level().getEntitiesOfClass(Villager.class, SCP999Entity.this.getBoundingBox().inflate(8.0,8.0,8.0));
            return !pDetectedVillagers.isEmpty() && pDetectedVillagers.get(0) != null && !pDetectedVillagers.get(0).hasEffect(pEffect);
        }


        @Override public void start(){
            List<Villager> pDetectedVillagers = SCP999Entity.this.level().getEntitiesOfClass(Villager.class, SCP999Entity.this.getBoundingBox().inflate(8.0,8.0,8.0));
            List<Player> pDetectedPlayers = SCP999Entity.this.level().getEntitiesOfClass(Player.class, SCP999Entity.this.getBoundingBox().inflate(8.0,8.0,8.0));
            if (!(pDetectedVillagers.get(0).hasEffect(ModEffects.SEROTONIN_EFFECT.get()))){
                SCP999Entity.this.getNavigation().moveTo((Entity) pDetectedVillagers.get(0), 1);
                SCP999Entity.this.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 20, 5));
            }
            if (!(pDetectedPlayers.get(0).hasEffect(ModEffects.SEROTONIN_EFFECT.get()))){
                SCP999Entity.this.getNavigation().moveTo((Entity) pDetectedVillagers.get(0), 1);
                SCP999Entity.this.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 20, 5));
            }
        }

        @Override public void tick(){
            happyTime();
        }

        public void happyTime(){
            List<Villager> pDetectedVillagers = SCP999Entity.this.level().getEntitiesOfClass(Villager.class, SCP999Entity.this.getBoundingBox().inflate(8.0,8.0,8.0));
            pDetectedVillagers.get(0).addEffect(new MobEffectInstance(ModEffects.SEROTONIN_EFFECT.get(),1200));
            if (pDetectedVillagers.get(0).hasEffect(ModEffects.SEROTONIN_EFFECT.get())){
                pDetectedVillagers.remove(pDetectedVillagers.get(0));
            }
            List<Player> pDetectedPlayers = SCP999Entity.this.level().getEntitiesOfClass(Player.class, SCP999Entity.this.getBoundingBox().inflate(8.0,8.0,8.0));
            pDetectedPlayers.get(0).addEffect(new MobEffectInstance(ModEffects.SEROTONIN_EFFECT.get(),1200));
            if (pDetectedPlayers.get(0).hasEffect(ModEffects.SEROTONIN_EFFECT.get())){
                pDetectedPlayers.remove(pDetectedPlayers.get(0));
            }
        }
    }

    class SquashGoal extends Goal{
        @Override
        public boolean canUse() {
            return (SCP999Entity.this.getLastDamageSource() != null &&
                    (SCP999Entity.this.getLastDamageSource() == SCP999Entity.this.damageSources().fall() ||
                    SCP999Entity.this.getLastDamageSource() == SCP999Entity.this.damageSources().anvil(SCP999Entity.this)));
        }

        @Override public void start(){
            SCP999Entity.this.setSquashed(true);
        }

        @Override public void stop(){
            SCP999Entity.this.setSquashed(false);
        }
    }


    class PongGoal extends Goal{
        @Override
        public boolean canUse() {
            return SCP999Entity.this.random.nextInt() == 0;
        }
        @Override public void start(){
            SCP999Entity.this.setPong(true);
        }

        @Override public void stop(){
            SCP999Entity.this.setPong(false);
        }
    }


    class InOutGoal extends Goal{
        @Override
        public boolean canUse() {
            return SCP999Entity.this.random.nextInt() == 1;
        }
        @Override public void start(){
            SCP999Entity.this.setInAndOut(true);
        }

        @Override public void stop(){
            SCP999Entity.this.setInAndOut(false);
        }
    }


    class SpinGoal extends Goal{
        @Override
        public boolean canUse() {
            return SCP999Entity.this.random.nextInt() == 2;
        }
        @Override public void start(){
            SCP999Entity.this.setSpin(true);
        }

        @Override public void stop(){
            SCP999Entity.this.setSpin(false);
        }
    }

    class BlinkGoal extends Goal{
        @Override
        public boolean canUse() {
            return SCP999Entity.this.random.nextInt() == 3;
        }
        @Override
        public void start(){
            SCP999Entity.this.setBlink(true);
        }

        @Override
        public void stop(){
            SCP999Entity.this.setBlink(false);
        }
    }

    class SleepGoal extends Goal{
        @Override
        public boolean canUse() {
            return SCP999Entity.this.level().isNight();
        }
        @Override
        public void start(){
            SCP999Entity.this.setSleep(true);
        }

        @Override
        public void tick(){
            SCP999Entity.this.navigation.stop();
            SCP999Entity.this.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 200, 10));
        }

        @Override
        public void stop(){
            SCP999Entity.this.setSleep(false);
            SCP999Entity.this.removeAllEffects();
        }
    }
}


