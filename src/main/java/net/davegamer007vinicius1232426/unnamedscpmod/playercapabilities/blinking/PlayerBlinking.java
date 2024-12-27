package net.davegamer007vinicius1232426.unnamedscpmod.playercapabilities.blinking;

import net.minecraft.nbt.CompoundTag;

public class PlayerBlinking {

    private int secsUntilNextBlink = 8;
    private int clock = 20;
    private int blinkTick = 0;
    private boolean areHeldOpen = false;
    private boolean areOpen = true;
    private final String BLINK_TAG = "blinking";
    private final String EYE_TAG = "eye";
    private final String HOLD_TAG = "hold_eye";
    private final String CLOCK = "clock";
    private final String BLINK_TICK_TAG = "clock2";

    public int getSecsUntilNextBlink(){
        return this.secsUntilNextBlink;
    }

    public void removeBlinkSecs(int pInput){
        this.secsUntilNextBlink = Math.max(secsUntilNextBlink - pInput,  0);
    }

    public void clockTickDown() {
        this.clock = Math.max(clock - 1, 0);
    }

    public void blinkTickDown(){
        this.blinkTick = Math.max(blinkTick - 1, 0);
    }

    public int getClock() {
        return this.clock;
    }

    public int getBlinkTick(){
        return this.blinkTick;
    }

    public void setBlinkTick(int pInput){
        this.blinkTick = pInput;
    }

    public void setClock(int pInput){
        this.clock = pInput;
    }

    public void setBlinkSecs(int pInput){
        this.secsUntilNextBlink = pInput;
    }

    public void switchEyeState(){
        this.areOpen = !this.areOpen;
    }

    public boolean getAreOpen(){
        return areOpen;
    }

    public void switchHeldEyeState() {
        this.areHeldOpen = !this.areHeldOpen;
    }

    public void copyFrom(PlayerBlinking source){
        this.secsUntilNextBlink = source.secsUntilNextBlink;
        this.areOpen = source.areOpen;
        this.areHeldOpen = source.areHeldOpen;
        this.clock = source.clock;
        this.blinkTick = source.blinkTick;
    }

    public void saveNBTData(CompoundTag nbtData){
        nbtData.putInt(BLINK_TAG, secsUntilNextBlink);
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
