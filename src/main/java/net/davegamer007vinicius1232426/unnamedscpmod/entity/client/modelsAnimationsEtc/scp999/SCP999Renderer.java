package net.davegamer007vinicius1232426.unnamedscpmod.entity.client.modelsAnimationsEtc.scp999;

import net.davegamer007vinicius1232426.unnamedscpmod.UnnamedSCPMod;
import net.davegamer007vinicius1232426.unnamedscpmod.entity.client.ModModelLayers;
import net.davegamer007vinicius1232426.unnamedscpmod.entity.custom.SCP999Entity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

public class SCP999Renderer extends MobRenderer<SCP999Entity, SCP999_Model<SCP999Entity>> {
    public SCP999Renderer(EntityRendererProvider.Context pContext) {
        super(pContext, new SCP999_Model<>(pContext.bakeLayer(ModModelLayers.SCP_999_LAYER)), 0.8f);
    }

    @Override
    public ResourceLocation getTextureLocation(SCP999Entity scp999Entity) {
        return new ResourceLocation(UnnamedSCPMod.MOD_ID, "textures/entity/scp999.png");
    }

    @Override
    protected @Nullable RenderType getRenderType(SCP999Entity pLivingEntity, boolean pBodyVisible, boolean pTranslucent, boolean pGlowing) {
        return super.getRenderType(pLivingEntity, pBodyVisible, true, pGlowing);
    }
}
