package net.davegamer007vinicius1232426.unnamedscpmod.events;

import net.davegamer007vinicius1232426.unnamedscpmod.UnnamedSCPMod;
import net.davegamer007vinicius1232426.unnamedscpmod.effect.ModEffects;
import net.davegamer007vinicius1232426.unnamedscpmod.item.custom.abstracts.SCPItem;
import net.davegamer007vinicius1232426.unnamedscpmod.util.BottleToMetal;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.animal.SnowGolem;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.entity.item.ItemExpireEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingChangeTargetEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
@Mod.EventBusSubscriber(modid = UnnamedSCPMod.MOD_ID)
public class ModEvents{

    @SubscribeEvent
    public static void snowGolemCraft(PlayerInteractEvent.EntityInteract pEvent){
      Entity pTarget = pEvent.getTarget();
      Player pPlayer = pEvent.getEntity();
      ItemStack pStack = pPlayer.getItemInHand(pEvent.getHand());
      Item pOutput = BottleToMetal.map(pStack.getItem());
      Item pReturn = BottleToMetal.returnMap(pStack.getItem());
    if (pTarget instanceof SnowGolem pSnowGolem && pOutput != null && !pPlayer.isShiftKeyDown()){
        pStack.setCount(pStack.getCount()-1);
        pSnowGolem.spawnAtLocation(pOutput);
        if (pReturn == null){
            pSnowGolem.playSound(SoundEvents.GLASS_BREAK);
        }
    }
    if (pTarget instanceof SnowGolem pSnowGolem && pReturn != null && !pPlayer.isShiftKeyDown()){
        pStack.setCount(pStack.getCount()-1);
        pSnowGolem.spawnAtLocation(pReturn);
    }
    if (pTarget instanceof SnowGolem pSnowGolem && pOutput != null && pPlayer.isShiftKeyDown()){
        for (int i = pStack.getCount(); i > 0; i--){
            pStack.setCount(pStack.getCount()-1);
            pSnowGolem.spawnAtLocation(pOutput);
            if (pReturn == null){
                pSnowGolem.playSound(SoundEvents.GLASS_BREAK);
            }
        }
    }
    if (pTarget instanceof SnowGolem pSnowGolem && pReturn != null && pPlayer.isShiftKeyDown()){
        for (int i = pStack.getCount(); i > 0; i--){
            pStack.setCount(pStack.getCount()-1);
            pSnowGolem.spawnAtLocation(pReturn);
        }
    }}

    @SubscribeEvent
    public static void incognitoVisibilityHandler(LivingEvent.LivingVisibilityEvent pEvent){
        if (pEvent.getEntity().hasEffect(ModEffects.INCOGNITO_EFFECT.get())){
            pEvent.modifyVisibility(0.1);
        }
    }
    @SubscribeEvent
    public static void incognitoAgroHandlerEvent(LivingChangeTargetEvent pEvent){
        LivingEntity pNewTarget = pEvent.getNewTarget();
        LivingEntity pOldTarget = pEvent.getOriginalTarget();
        LivingEntity pPeeper = pEvent.getEntity();
        if (pNewTarget == null){
            return;
        }

        if(pOldTarget.hasEffect(ModEffects.INCOGNITO_EFFECT.get()) && !pPeeper.hasLineOfSight(pOldTarget)){
               pEvent.setNewTarget(null);
               pPeeper.getBrain().eraseMemory(MemoryModuleType.HURT_BY_ENTITY);
               pPeeper.getBrain().eraseMemory(MemoryModuleType.NEAREST_VISIBLE_PLAYER);
               pPeeper.getBrain().eraseMemory(MemoryModuleType.ANGRY_AT);
        }
    }
}

