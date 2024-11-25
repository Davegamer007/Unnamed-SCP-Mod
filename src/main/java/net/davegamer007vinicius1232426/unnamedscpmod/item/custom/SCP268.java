package net.davegamer007vinicius1232426.unnamedscpmod.item.custom;

import net.davegamer007vinicius1232426.unnamedscpmod.UnnamedSCPMod;
import net.davegamer007vinicius1232426.unnamedscpmod.effect.ModEffects;
import net.davegamer007vinicius1232426.unnamedscpmod.item.ModItems;
import net.davegamer007vinicius1232426.unnamedscpmod.item.custom.abstracts.SCPArmorItem;
import net.davegamer007vinicius1232426.unnamedscpmod.item.custom.abstracts.SCPItem;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Equipable;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class SCP268 extends SCPItem implements Equipable {

    public SCP268(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void onInventoryTick(ItemStack stack, Level level, Player player, int slotIndex, int selectedIndex) {
        incognitoHandler(player);
        super.onInventoryTick(stack, level, player, slotIndex, selectedIndex);
    }

    public static void incognitoHandler(LivingEntity pLivingEntity){
        if (pLivingEntity.getItemBySlot(EquipmentSlot.HEAD).is(ModItems.SCP268.get())
                && !pLivingEntity.hasEffect(ModEffects.INCOGNITO_EFFECT.get())){
            pLivingEntity.addEffect(new MobEffectInstance(
                    ModEffects.INCOGNITO_EFFECT.get(), 60, 1, false, false, true));
        }
    }

    @Override
    public EquipmentSlot getEquipmentSlot() {
        return EquipmentSlot.HEAD;
    }
}
