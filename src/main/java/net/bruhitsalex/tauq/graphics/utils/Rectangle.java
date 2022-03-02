package net.bruhitsalex.tauq.graphics.utils;

import org.lwjgl.opengl.GL11;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;

public class Rectangle {

    public static void drawRect(int left, int top, int right, int bottom, Color color) {
        ColorUtils.setColor(color);
        glBegin(GL_QUADS);
        glVertex2f(left, top);
        glVertex2f(right, top);
        glVertex2f(right, bottom);
        glVertex2f(left, bottom);
        glEnd();
    }

    public static void drawBlendedRect(int left, int top, int right, int bottom, Color color) {
        glEnable(GL11.GL_BLEND);
        drawRect(left, top, right, bottom, color);
        glDisable(GL11.GL_BLEND);
    }

}
