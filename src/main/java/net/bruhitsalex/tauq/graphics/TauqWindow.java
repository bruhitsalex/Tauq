package net.bruhitsalex.tauq.graphics;

import net.bruhitsalex.tauq.graphics.events.EventHandlers;
import net.bruhitsalex.tauq.misc.Log;
import net.bruhitsalex.tauq.misc.LoggerType;
import org.lwjgl.Version;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class TauqWindow {

    private static final int INIT_DISPLAY_WIDTH = 1280;
    private static final int INIT_DISPLAY_HEIGHT = 720;
    private static final String INIT_DISPLAY_TITLE = "Tauq";

    private long window;
    private OpenGLHandler openGLHandler;

    public void run() {
        Log.log(LoggerType.GLFW, "Running LWJGL version " + Version.getVersion());
        if (init()) {
            EventHandlers.afterInitialised();
            this.openGLHandler = new OpenGLHandler();
            openGLHandler.loop(this);
        }
    }

    private boolean init() {
        Log.log(LoggerType.GLFW, "Initialising window...");
        GLFWErrorCallback.createPrint(System.err).set();

        if (!GLFW.glfwInit()) {
            Log.error(LoggerType.GLFW, "Unable to initialise GLFW...");
            return false;
        }

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

        window = glfwCreateWindow(INIT_DISPLAY_WIDTH, INIT_DISPLAY_HEIGHT, INIT_DISPLAY_TITLE, NULL, NULL);

        if (window == 0) {
            Log.error(LoggerType.GLFW, "Unable to create window object...");
            return false;
        }

        setupCallbacks();

        try (MemoryStack stack = stackPush()) {
            IntBuffer pWidth = stack.mallocInt(1);
            IntBuffer pHeight = stack.mallocInt(1);

            glfwGetWindowSize(window, pWidth, pHeight);

            GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            if (vidMode == null) {
                Log.error(LoggerType.GLFW, "Unable to get monitor context...");
                return false;
            }

            glfwSetWindowPos(
                    window,
                    (vidMode.width() - pWidth.get(0)) / 2,
                    (vidMode.height() - pHeight.get(0)) / 2
            );
        }

        glfwMakeContextCurrent(window);
        glfwSwapInterval(1); // vsync
        glfwShowWindow(window);

        Log.log(LoggerType.GLFW, "GLFW initialised, showing window.");
        return true;
    }

    private void setupCallbacks() {
        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if (action == GLFW_PRESS) {
                EventHandlers.onKeyPress(key);
            }
        });
    }

    public long getWindow() {
        return window;
    }

}
