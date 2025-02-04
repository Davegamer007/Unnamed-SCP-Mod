package net.davegamer007vinicius1232426.unnamedscpmod.events;


import net.davegamer007vinicius1232426.unnamedscpmod.UnnamedSCPMod;
import net.davegamer007vinicius1232426.unnamedscpmod.entity.ModEntities;
import net.davegamer007vinicius1232426.unnamedscpmod.entity.custom.SCP999Entity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = UnnamedSCPMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBusEvents {

   @SubscribeEvent
   public static void registerEntityAtributes(EntityAttributeCreationEvent pEvent){
        pEvent.put(ModEntities.SCP999.get(),SCP999Entity.createAtributes().build());
   }
}
