package net.bruhitsalex.tauq.components;

import lombok.Getter;
import lombok.Setter;
import net.bruhitsalex.tauq.graphics.TauqWindow;
import net.bruhitsalex.tauq.graphics.utils.Rectangle;
import net.bruhitsalex.tauq.theme.Theme;

import java.awt.*;

@Getter
@Setter
public abstract class TComponent {

    private TauqWindow window;

    private int x;
    private int y;
    private int width;
    private int height;

    public TComponent(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void draw() {
        if (backgroundColor() != null) {
            Rectangle.drawRect(x, y, x + width, x + height, backgroundColor());
        }

        if (borderColor() != null) {
            Rectangle.drawRectBorders(x, y, x + width, x + height, 2, borderColor());
        }

        onDraw();
    }

    protected abstract Color backgroundColor();

    protected abstract Color borderColor();

    public abstract void onDraw();

    public Theme getTheme() {
        return getWindow().getTheme();
    }

}
