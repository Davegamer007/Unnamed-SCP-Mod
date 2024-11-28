package net.davegamer007vinicius1232426.unnamedscpmod.item.custom.easterEggs;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.davegamer007vinicius1232426.unnamedscpmod.item.custom.abstracts.SCPItem;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.Vanishable;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class ThalassoRex extends SCPItem implements Vanishable {
    public static final int THROW_THRESHOLD_TIME = 10;
    public static final float BASE_DAMAGE = 8.0F;
    public static final float SHOOT_POWER = 2.5F;
    private final Multimap<Attribute, AttributeModifier> defaultModifiers;

    public ThalassoRex(Properties pProperties) {
        super(pProperties);
        ImmutableMultimap.Builder<Attribute, AttributeModifier> Map = ImmutableMultimap.builder();
        Map.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Tool modifier", 8.0, AttributeModifier.Operation.ADDITION));
        Map.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Tool modifier", -2.9000000953674316, AttributeModifier.Operation.ADDITION));
        this.defaultModifiers = Map.build();
    }

    private final String MODE_TAG = "use_mode";

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack pStack = pPlayer.getItemInHand(pUsedHand);
        CompoundTag nbtData =  pStack.getOrCreateTag();
        int mode = nbtData.getInt(MODE_TAG);

        if (pPlayer.isShiftKeyDown() && mode == 2){
            nbtData.putInt(MODE_TAG, 0);
            pStack.enchant(Enchantments.LOYALTY, 0);
            return InteractionResultHolder.success(pStack);
        }  else
        if (!pPlayer.isShiftKeyDown() && mode == 2){
            return InteractionResultHolder.fail(pStack);
        }  else
        if (pPlayer.isShiftKeyDown() && mode < 2){
            nbtData.putInt(MODE_TAG, mode + 1);
            pStack.enchant(Enchantments.LOYALTY, 0);
            return InteractionResultHolder.success(pStack);
        } else {pPlayer.startUsingItem(pUsedHand);}
        return super.use(pLevel, pPlayer, pUsedHand);
    }

    @Override
    public boolean canAttackBlock(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer) {
        return !pPlayer.isCreative();
    }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.SPEAR;
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        return 72000;
    }

    @Override
    public void releaseUsing(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving, int pTimeLeft) {
        if (pEntityLiving instanceof Player pPlayer) {
            int chargeTime = this.getUseDuration(pStack) - pTimeLeft;
            if (chargeTime >= 10) {
                int pMode = pStack.getOrCreateTag().getInt(MODE_TAG);
                if (pMode == 0) {
                    pPlayer.awardStat(Stats.ITEM_USED.get(this));
                    float pPlayerYRot = pPlayer.getYRot();
                    float pPlayerXRot = pPlayer.getXRot();
                    float $$10 = -Mth.sin(pPlayerYRot * 0.017453292F) * Mth.cos(pPlayerXRot * 0.017453292F);
                    float $$11 = -Mth.sin(pPlayerXRot * 0.017453292F);
                    float $$12 = Mth.cos(pPlayerYRot * 0.017453292F) * Mth.cos(pPlayerXRot * 0.017453292F);
                    float $$13 = Mth.sqrt($$10 * $$10 + $$11 * $$11 + $$12 * $$12);
                    float $$14 = 3.0F * (5 / 4.0F);
                    $$10 *= $$14 / $$13;
                    $$11 *= $$14 / $$13;
                    $$12 *= $$14 / $$13;
                    pPlayer.push((double)$$10, (double)$$11, (double)$$12);
                    pPlayer.startAutoSpinAttack(20);
                    if (pPlayer.onGround()) {
                        float $$15 = 1.1999999F;
                        pPlayer.move(MoverType.SELF, new Vec3(0.0, 1.1999999284744263, 0.0));
                    }
                    SoundEvent riptideSound = SoundEvents.TRIDENT_RIPTIDE_3;

                    pLevel.playSound((Player)null, pPlayer, riptideSound, SoundSource.PLAYERS, 1.0F, 1.0F);

                }
                if (!pLevel.isClientSide) {
                    if (pMode == 1) {
                        ThrownTrident tridentProjectile = new ThrownTrident(pLevel, pPlayer, pStack);
                        tridentProjectile.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0F, 2.5F + 4 * 0.5F, 1.0F);
                        if (pPlayer.getAbilities().instabuild) {
                            tridentProjectile.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                        }

                        pLevel.addFreshEntity(tridentProjectile);
                        pLevel.playSound((Player)null, tridentProjectile, SoundEvents.TRIDENT_THROW, SoundSource.PLAYERS, 1.0F, 1.0F);
                        if (!pPlayer.getAbilities().instabuild) {
                            pPlayer.getInventory().removeItem(pStack);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onInventoryTick(ItemStack stack, Level level, Player player, int slotIndex, int selectedIndex) {
        ItemStack pHandItem = player.getItemInHand(player.getUsedItemHand());
        Level pLevel = player.level();
        if (pHandItem.getOrCreateTag().getInt(MODE_TAG) == 2){
            player.isInvulnerableTo(level.damageSources().fall());
            player.addEffect(new MobEffectInstance(MobEffects.REGENERATION,60, 2));
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE,60, 3));
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN,60, 8));
            player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING,60, 1));
        }
        super.onInventoryTick(stack, level, player, slotIndex, selectedIndex);
    }

    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot pEquipmentSlot) {
        return pEquipmentSlot == EquipmentSlot.MAINHAND ? this.defaultModifiers : super.getDefaultAttributeModifiers(pEquipmentSlot);
    }
}

