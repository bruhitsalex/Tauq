package net.bruhitsalex.tauq.components.panels;

import lombok.*;
import net.bruhitsalex.tauq.components.TComponent;
import net.bruhitsalex.tauq.graphics.TauqWindow;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TPanel extends TComponent {

    private List<TComponent> children = new ArrayList<>();

    public TPanel(int x, int y, int displayWidth, int displayHeight, TauqWindow window) {
        super(x, y, displayWidth, displayHeight);
        setWindow(window);
    }

    public TPanel(int x, int y, int displayWidth, int displayHeight) {
        super(x, y, displayWidth, displayHeight);
    }

    @Override
    protected Color backgroundColor() {
        return getTheme().getTPanelBackgroundColor();
    }

    @Override
    protected Color borderColor() {
        return getTheme().getTPanelBorderColor();
    }

    @Override
    public void onDraw() {
        children.forEach(TComponent::draw);
    }

    public void add(TPanel panel) {
        panel.setWindow(getWindow());
        children.add(panel);
    }

}
