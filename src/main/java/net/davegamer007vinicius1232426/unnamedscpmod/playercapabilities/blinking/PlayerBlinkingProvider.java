package net.davegamer007vinicius1232426.unnamedscpmod.playercapabilities.blinking;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerBlinkingProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static Capability<PlayerBlinking> PLAYER_BLINKING = CapabilityManager.get(new CapabilityToken<PlayerBlinking>() {});

    private PlayerBlinking blinking = null;
    private final LazyOptional<PlayerBlinking> optional = LazyOptional.of(this::createPlayerBlinking);

    private PlayerBlinking createPlayerBlinking() {
        if (this.blinking == null){
            this.blinking = new PlayerBlinking();
        }

        return this.blinking;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction direction) {
        if (capability == PLAYER_BLINKING){
            return optional.cast();
        }

        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbtData = new CompoundTag();
        createPlayerBlinking().saveNBTData(nbtData);
        return nbtData;
    }

    @Override
    public void deserializeNBT(CompoundTag nbtData) {
        createPlayerBlinking().loadNBTData(nbtData);
    }
}
