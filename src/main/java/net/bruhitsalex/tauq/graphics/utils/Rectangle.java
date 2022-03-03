package net.bruhitsalex.tauq.graphics.utils;

import org.lwjgl.opengl.GL11;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;

@SuppressWarnings("Duplicates")
public class Rectangle {

    public static void drawRect(int left, int top, int right, int bottom, Color color) {
        if (color.getAlpha() != 255) {
            glEnable(GL11.GL_BLEND);
        }

        ColorUtils.setColor(color);
        glBegin(GL_QUADS);
        glVertex2f(left, top);
        glVertex2f(right, top);
        glVertex2f(right, bottom);
        glVertex2f(left, bottom);
        glEnd();

        if (color.getAlpha() != 255) {
            glDisable(GL11.GL_BLEND);
        }
    }

    public static void drawRectBorders(int left, int top, int right, int bottom, int width, Color color) {
        if (color.getAlpha() != 255) {
            glEnable(GL11.GL_BLEND);
        }

        ColorUtils.setColor(color);
        glLineWidth(width);
        glBegin(GL_LINE_LOOP);
        glVertex2f(left, top);
        glVertex2f(right, top);
        glVertex2f(right, bottom);
        glVertex2f(left, bottom);
        glEnd();

        if (color.getAlpha() != 255) {
            glDisable(GL11.GL_BLEND);
        }
    }

}
