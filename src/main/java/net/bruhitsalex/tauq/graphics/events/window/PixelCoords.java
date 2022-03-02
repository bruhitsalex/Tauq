package net.bruhitsalex.tauq.graphics.events.window;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PixelCoords {

    private double x;
    private double y;

    public int getXInt() {
        return (int) x;
    }

    public int getYInt() {
        return (int) y;
    }

}
