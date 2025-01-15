package net.davegamer007vinicius1232426.unnamedscpmod.playercapabilities.blinking;

import net.davegamer007vinicius1232426.unnamedscpmod.networking.ModMessages;
import net.davegamer007vinicius1232426.unnamedscpmod.networking.packets.S2C.SyncEyeStateS2CPacket;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;

public class PlayerBlinking {

    private int blinkSex = 8;
    private int clock = 20;
    private int blinkTick = 0;
    private boolean areHeldOpen = false;
    private boolean areOpen = true;
    private final String BLINK_TAG = "blinking";
    private final String EYE_TAG = "eye";
    private final String HOLD_TAG = "hold_eye";
    private final String CLOCK = "clock";
    private final String BLINK_TICK_TAG = "clock2";

    public void clockTickDown(ServerPlayer pPlayer){
        if (this.clock == 0){
            this.clock = 40;
            if (this.blinkSex > 0){
                this.blinkSex = this.blinkSex-1;
                ModMessages.sendToPlayer(new SyncEyeStateS2CPacket(this.blinkSex, this.areOpen), pPlayer);
            } else {
                this.blinkSex = 8;
                this.blinkTick = 2;
                switchEyeState();
                ModMessages.sendToPlayer(new SyncEyeStateS2CPacket(this.blinkSex, this.areOpen), pPlayer);
            }
        } else {
            --this.clock;
        }
        if (this.blinkTick > 0){
            --this.blinkTick;
        } else if (this.blinkTick == 0){
            switchEyeState();
            ModMessages.sendToPlayer(new SyncEyeStateS2CPacket(this.blinkSex, this.areOpen), pPlayer);
            this.blinkTick = -1;
        }
    }

    public void resetClock(){
        this.clock = 40;
    }

    public void resetBlinkSex(ServerPlayer pPlayer){
        this.blinkSex = 8;
        ModMessages.sendToPlayer(new SyncEyeStateS2CPacket(this.blinkSex, this.areOpen), pPlayer);
    }


    public void switchEyeState(){
        this.areOpen = !this.areOpen;
    }

    public void switchHeldEyeState(){
        this.areHeldOpen = !this.areHeldOpen;
    }

    public int getClock(){
        return this.clock;
    }

    public int getBlinkSex(){
        return this.blinkSex;
    }

    public boolean areEyesOpen(){
        return this.areOpen;
    }

    public boolean areHeldOpen(){
        return this.areHeldOpen;
    }



    public void copyFrom(PlayerBlinking source){
        this.blinkSex = source.blinkSex;
        this.areOpen = source.areOpen;
        this.areHeldOpen = source.areHeldOpen;
        this.clock = source.clock;
        this.blinkTick = source.blinkTick;
    }


    public void saveNBTData(CompoundTag nbtData){
        nbtData.putInt(BLINK_TAG, blinkSex);
        nbtData.putInt(CLOCK, clock);
        nbtData.putInt(BLINK_TICK_TAG, blinkTick);
        nbtData.putBoolean(EYE_TAG, areOpen);
        nbtData.putBoolean(HOLD_TAG, areHeldOpen);
    }


    public void loadNBTData(CompoundTag nbtData){
        nbtData.getInt(BLINK_TAG);
        nbtData.getInt(CLOCK);
        nbtData.getInt(BLINK_TICK_TAG);
        nbtData.getBoolean(EYE_TAG);
        nbtData.getBoolean(HOLD_TAG);
    }
}
