package net.bruhitsalex.demo;

import net.bruhitsalex.tauq.components.panels.TPanel;
import net.bruhitsalex.tauq.graphics.TauqWindow;
import net.bruhitsalex.tauq.graphics.events.io.MouseClickEvent;
import net.bruhitsalex.tauq.misc.Log;

public class Demo implements MouseClickEvent {

    private TauqWindow window;

    public void start() {
        this.window = new TauqWindow();
        window.start();
        window.getMainPanel().add(new TPanel(10, 20, 26, 82));
        window.getEventHandler().getListeners().MOUSE_CLICK_LISTENERS.add(this);
    }

    @Override
    public void onMouseClick(int button) {
        Log.debug("FPS: " + window.getRenderer().getFPS());
    }

}
