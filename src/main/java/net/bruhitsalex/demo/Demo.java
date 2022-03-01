package net.bruhitsalex.demo;

import net.bruhitsalex.tauq.graphics.TauqWindow;
import net.bruhitsalex.tauq.graphics.events.Listeners;
import net.bruhitsalex.tauq.graphics.events.events.AfterInitialisedEvent;

public class Demo implements AfterInitialisedEvent {

    private TauqWindow window;

    public void start() {
        Listeners.AFTER_INITIALISED_LISTENERS.add(this);
        this.window = new TauqWindow();
        window.run();
    }

    @Override
    public void afterInitialized() {

    }

}
