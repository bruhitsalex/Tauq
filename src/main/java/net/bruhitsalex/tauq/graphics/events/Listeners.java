package net.bruhitsalex.tauq.graphics.events;

import net.bruhitsalex.tauq.graphics.events.init.AfterInitialisedEvent;
import net.bruhitsalex.tauq.graphics.events.io.*;

import java.util.ArrayList;
import java.util.List;

public class Listeners {

    public List<KeyPressEvent> KEY_PRESS_LISTENERS = new ArrayList<>();
    public List<KeyReleasedEvent> KEY_RELEASED_LISTENERS = new ArrayList<>();

    public List<MouseClickEvent> MOUSE_CLICK_LISTENERS = new ArrayList<>();
    public List<MouseReleaseEvent> MOUSE_RELEASE_LISTENERS = new ArrayList<>();
    public List<MouseScrollEvent> MOUSE_SCROLL_LISTENERS = new ArrayList<>();

    public List<AfterInitialisedEvent> AFTER_INITIALISED_LISTENERS = new ArrayList<>();

}
