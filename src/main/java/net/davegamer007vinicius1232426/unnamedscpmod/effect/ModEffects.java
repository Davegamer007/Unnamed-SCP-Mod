package net.davegamer007vinicius1232426.unnamedscpmod.effect;

import net.davegamer007vinicius1232426.unnamedscpmod.UnnamedSCPMod;
import net.davegamer007vinicius1232426.unnamedscpmod.effect.custom.EternalFlame;
import net.davegamer007vinicius1232426.unnamedscpmod.effect.custom.IncognitoEffect;
import net.davegamer007vinicius1232426.unnamedscpmod.effect.custom.SerotoninEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, UnnamedSCPMod.MOD_ID);

    public static final RegistryObject<MobEffect> ETERNAL_FLAME = MOB_EFFECTS.register("eternal_flame",
            ()-> new EternalFlame(MobEffectCategory.HARMFUL, 0));

    public static final RegistryObject<MobEffect> INCOGNITO_EFFECT = MOB_EFFECTS.register("incognito_effect",
            ()-> new IncognitoEffect(MobEffectCategory.NEUTRAL, 0));

    public static final RegistryObject<MobEffect> SEROTONIN_EFFECT = MOB_EFFECTS.register("serotonin_effect",
            ()-> new IncognitoEffect(MobEffectCategory.BENEFICIAL, 0xfff300));



    public static void register(IEventBus eventBus){
        MOB_EFFECTS.register(eventBus);
    }
}
