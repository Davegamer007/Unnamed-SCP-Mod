package net.davegamer007vinicius1232426.unnamedscpmod.events;

import net.davegamer007vinicius1232426.unnamedscpmod.UnnamedSCPMod;
import net.davegamer007vinicius1232426.unnamedscpmod.item.custom.abstracts.SCPItem;
import net.davegamer007vinicius1232426.unnamedscpmod.util.BottleToMetal;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.SnowGolem;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.item.ItemExpireEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
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
    if (pTarget instanceof SnowGolem pSnowGolem && BottleToMetal.map(pStack.getItem()) != null && !pPlayer.isShiftKeyDown()){
        pStack.setCount(pStack.getCount()-1);
        pSnowGolem.spawnAtLocation(pOutput);
        pSnowGolem.playSound(SoundEvents.GLASS_BREAK);
        }
        if (pTarget instanceof SnowGolem pSnowGolem && BottleToMetal.map(pStack.getItem()) != null && pPlayer.isShiftKeyDown()){
            for (int i = pStack.getCount(); i > 0; i--){
                pStack.setCount(pStack.getCount()-1);
                pSnowGolem.spawnAtLocation(pOutput);
                pSnowGolem.playSound(SoundEvents.GLASS_BREAK);
            }
        }
    }

    @SubscribeEvent
    public static void transformsBottle(ItemExpireEvent pEvent){
        ItemEntity pItemEntity = pEvent.getEntity();
        ItemStack pStack = ((pEvent.getEntity()).getItem());
        Item pOutput = BottleToMetal.map(pStack.getItem());
        if (pItemEntity.isInPowderSnow && pOutput != null){
            for (int i = (int)pStack.getCount(); i > 0 ; i--){
                (pOutput.getDefaultInstance()).setCount(pStack.getCount());
                pItemEntity.spawnAtLocation(pOutput.getDefaultInstance());
            }
        }
    }
}
