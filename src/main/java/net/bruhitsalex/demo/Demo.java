package net.bruhitsalex.demo;

import net.bruhitsalex.tauq.graphics.TauqWindow;
import net.bruhitsalex.tauq.graphics.events.io.MouseClickEvent;
import net.bruhitsalex.tauq.misc.Log;

public class Demo implements MouseClickEvent {

    private TauqWindow window;

    public void start() {
        this.window = new TauqWindow();
        window.start();
        window.getEventHandler().getListeners().MOUSE_CLICK_LISTENERS.add(this);
    }

    @Override
    public void onMouseClick(int button) {
        Log.debug("FPS: " + window.getRenderer().getFPS());
    }

}
