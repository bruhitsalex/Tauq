package net.bruhitsalex.tauq.graphics.events;

import net.bruhitsalex.tauq.graphics.events.events.AfterInitialisedEvent;
import net.bruhitsalex.tauq.graphics.events.events.KeyPressEvent;

public class EventHandlers {

    public static void onKeyPress(int key) {
        for (KeyPressEvent keyPressListener : Listeners.KEY_PRESS_LISTENERS) {
            keyPressListener.onKeyPress(key);
        }
    }

    public static void afterInitialised() {
        for (AfterInitialisedEvent afterInitialisedEvent : Listeners.AFTER_INITIALISED_LISTENERS) {
            afterInitialisedEvent.afterInitialized();
        }
    }

}
