package net.bruhitsalex.tauq.graphics.events;

import net.bruhitsalex.tauq.graphics.events.events.AfterInitialisedEvent;
import net.bruhitsalex.tauq.graphics.events.events.KeyPressEvent;

import java.util.ArrayList;
import java.util.List;

public class Listeners {

    public static List<KeyPressEvent> KEY_PRESS_LISTENERS = new ArrayList<>();
    public static List<AfterInitialisedEvent> AFTER_INITIALISED_LISTENERS = new ArrayList<>();

}
