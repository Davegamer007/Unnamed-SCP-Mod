package net.davegamer007vinicius1232426.unnamedscpmod.events;

import net.davegamer007vinicius1232426.unnamedscpmod.UnnamedSCPMod;
import net.davegamer007vinicius1232426.unnamedscpmod.client.ModKeyBindings;
import net.davegamer007vinicius1232426.unnamedscpmod.effect.ModEffects;
import net.davegamer007vinicius1232426.unnamedscpmod.item.custom.abstracts.SCPFuelItem;
import net.davegamer007vinicius1232426.unnamedscpmod.item.custom.abstracts.SCPItem;
import net.davegamer007vinicius1232426.unnamedscpmod.networking.ModMessages;
import net.davegamer007vinicius1232426.unnamedscpmod.networking.packets.S2C.SyncEyeStateS2CPacket;
import net.davegamer007vinicius1232426.unnamedscpmod.playercapabilities.blinking.PlayerBlinking;
import net.davegamer007vinicius1232426.unnamedscpmod.playercapabilities.blinking.PlayerBlinkingProvider;
import net.davegamer007vinicius1232426.unnamedscpmod.util.BottleToMetal;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.animal.SnowGolem;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.item.ItemExpireEvent;
import net.minecraftforge.event.entity.living.LivingChangeTargetEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.server.ServerLifecycleEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

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

    @SubscribeEvent
    public static void scpItemVoidEvent(ItemExpireEvent pEvent){
        if (!(pEvent.getEntity() instanceof ItemEntity)){return;}
        ItemEntity pItemEntity = (ItemEntity) pEvent.getEntity();
        if (pItemEntity.getItem().getItem() instanceof SCPItem
                || pItemEntity.getItem().getItem() instanceof SCPFuelItem){
            pEvent.isCanceled();
        }
    }




    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if(event.getObject() instanceof Player) {
            if(!event.getObject().getCapability(PlayerBlinkingProvider.PLAYER_BLINKING).isPresent()) {
                event.addCapability(new ResourceLocation(UnnamedSCPMod.MOD_ID, "properties"), new PlayerBlinkingProvider());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone pEvent){
        if (pEvent.isWasDeath()){
            pEvent.getOriginal().getCapability(PlayerBlinkingProvider.PLAYER_BLINKING).ifPresent(
                    oldStore -> pEvent.getOriginal().getCapability(PlayerBlinkingProvider.PLAYER_BLINKING).ifPresent(
                            newStore -> newStore.copyFrom(oldStore)));

        }
    }

    @SubscribeEvent
    public static void onRegisterCapabilitiesEvent(RegisterCapabilitiesEvent pEvent){
        pEvent.register(PlayerBlinking.class);
    }

    @SubscribeEvent
    public static void onPlayerJoinWorld(EntityJoinLevelEvent event) {
        if(!event.getLevel().isClientSide()) {
            if(event.getEntity() instanceof ServerPlayer player) {
                player.getCapability(PlayerBlinkingProvider.PLAYER_BLINKING).ifPresent(blink -> {
                    ModMessages.sendToPlayer(new SyncEyeStateS2CPacket(blink.getBlinkSex(), true), player);
                    player.sendSystemMessage(Component.literal("Press " + ModKeyBindings.CLOSE_EYE_BUTTON.getKey().getDisplayName() + " to open your Eyes").withStyle(ChatFormatting.YELLOW));
                });
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent pEvent){
        if (pEvent.side != LogicalSide.SERVER){
            return;
        }
        pEvent.player.getCapability(PlayerBlinkingProvider.PLAYER_BLINKING).ifPresent(blink ->{
            ServerPlayer sPlayer = (ServerPlayer) pEvent.player;
            if (blink.areEyesOpen()){
                blink.clockTickDown();
            } else {
                blink.clockTickDown();
                blink.resetBlinkSex();
                blink.resetClock();
            }
            ModMessages.sendToPlayer(new SyncEyeStateS2CPacket(blink.getBlinkSex(), blink.areEyesOpen()), sPlayer);
        });
    }

    @SubscribeEvent
    public static void scpSpawnEvent(TickEvent.ServerTickEvent pEvent){
    }
}

