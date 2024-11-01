package net.davegamer007vinicius1232426.unnamedscpmod.entity;

import net.davegamer007vinicius1232426.unnamedscpmod.UnnamedSCPMod;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, UnnamedSCPMod.MOD_ID);

    // public static final RegistryObject<EntityType<SCP622Projectile>> SCP622PROJECTILE =
    //        ENTITY_TYPES.register("dice_projectile", () -> EntityType.Builder.<SCP622Projectile>of(SCP622Projectile::new, MobCategory.MISC)
    //                .sized(0.1f, 0.1f).build("scp622_projectile"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
