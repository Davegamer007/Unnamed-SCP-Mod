package net.davegamer007vinicius1232426.unnamedscpmod.client_hud;

public class BlinkHudData {
    private static int playerBlinkSex;
    private static boolean playerIsOpen;

    public static void setPlayerBlinkSex(int pInput){
        BlinkHudData.playerBlinkSex = pInput;
    }

    public static void setPlayerIsOpen(boolean pInput){
        BlinkHudData.playerIsOpen = pInput;
    }

    public static int getPlayerBlinkSex(){
        return BlinkHudData.playerBlinkSex;
    }

    public static boolean getPlayerisOpen(){
        return BlinkHudData.playerIsOpen;
    }
}
