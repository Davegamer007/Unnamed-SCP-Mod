package net.davegamer007vinicius1232426.unnamedscpmod.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.davegamer007vinicius1232426.unnamedscpmod.UnnamedSCPMod;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class ModKeyBindings {

    private static String keyCategory(String pInput){
        return "key.category." + UnnamedSCPMod.MOD_ID + "." + pInput;
    }

    private static String keyInput(String pInput){
        return "key." + UnnamedSCPMod.MOD_ID + "." + pInput;
    }


    public static final String BLINKING_KEY_CATEGORY = keyCategory("blinking");
    public static final String HOLD_EYE_OPEN_KEY = keyInput("hold_eye");
    public static final String CLOSE_EYE_KEY = keyInput("close_eye");

    public static final KeyMapping CLOSE_EYE_BUTTON = new KeyMapping(CLOSE_EYE_KEY, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_B, BLINKING_KEY_CATEGORY);
    public static final KeyMapping HOLD_EYE_BUTTON = new KeyMapping(HOLD_EYE_OPEN_KEY, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_H, BLINKING_KEY_CATEGORY);
}
