package net.bruhitsalex.tauq.graphics.events;

import lombok.Getter;
import net.bruhitsalex.tauq.graphics.events.init.AfterInitialisedEvent;

@Getter
public class EventHandler {

    private final Listeners listeners;
    public EventHandler() {
        this.listeners = new Listeners();
    }

    public void onKeyPress(int key) {
        listeners.KEY_PRESS_LISTENERS.forEach(event -> event.onKeyPress(key));
    }

    public void afterInitialised() {
        listeners.AFTER_INITIALISED_LISTENERS.forEach(AfterInitialisedEvent::afterInitialized);
    }

    public void onKeyRelease(int key) {
        listeners.KEY_RELEASED_LISTENERS.forEach(event -> event.onKeyRelease(key));
    }

    public void onMouseScroll(boolean up) {
        listeners.MOUSE_SCROLL_LISTENERS.forEach(event -> event.onScrollEvent(up));
    }

    public void onMousePress(int mouseButton) {
        listeners.MOUSE_CLICK_LISTENERS.forEach(event -> event.onMouseClick(mouseButton));
    }

    public void onMouseRelease(int mouseButton) {
        listeners.MOUSE_RELEASE_LISTENERS.forEach(event -> event.onMouseRelease(mouseButton));
    }

}
