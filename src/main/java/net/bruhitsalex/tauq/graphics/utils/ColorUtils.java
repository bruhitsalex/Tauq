package net.bruhitsalex.tauq.graphics.utils;

import java.awt.*;

import static org.lwjgl.opengl.GL11.glColor4f;

public class ColorUtils {

    public static void setColor(Color color) {
        glColor4f(color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f, color.getAlpha() / 255f);
    }

}
