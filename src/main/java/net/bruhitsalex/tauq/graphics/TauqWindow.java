package net.bruhitsalex.tauq.graphics;

import net.bruhitsalex.tauq.components.panels.TPanel;
import net.bruhitsalex.tauq.graphics.events.EventHandler;
import net.bruhitsalex.tauq.graphics.events.window.PixelCoords;
import net.bruhitsalex.tauq.misc.Log;
import net.bruhitsalex.tauq.misc.LoggerType;
import net.bruhitsalex.tauq.theme.DebugTheme;
import net.bruhitsalex.tauq.theme.Theme;
import org.lwjgl.BufferUtils;
import org.lwjgl.Version;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.system.MemoryStack;

import java.nio.DoubleBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class TauqWindow {

    private static boolean glfwInitialised = false;
    private static volatile boolean initialisingGlfw = false;

    private static final int INIT_DISPLAY_WIDTH = 1280;
    private static final int INIT_DISPLAY_HEIGHT = 720;
    private static final String INIT_DISPLAY_TITLE = "Tauq";

    private long window;
    private OpenGLHandler renderer;
    private EventHandler eventHandlers;

    private Thread renderThread;

    private int displayWidth;
    private int displayHeight;

    private final TPanel mainTPanel;
    private Theme theme;

    public TauqWindow() {
        this.displayWidth = INIT_DISPLAY_WIDTH;
        this.displayHeight = INIT_DISPLAY_HEIGHT;
        this.theme = new DebugTheme();
        this.mainTPanel = new TPanel(0, 0, displayWidth, displayHeight, this);
    }

    public void start() {
        this.renderThread = new Thread(this::run);
        this.eventHandlers = new EventHandler();
        renderThread.start();
    }

    private void run() {
        Log.log(LoggerType.GLFW, "Running LWJGL version " + Version.getVersion());
        if (init()) {
            this.eventHandlers.afterInitialised();
            this.renderer = new OpenGLHandler();
            this.renderer.loop(getInstance());
        }
    }

    private boolean init() {
        Log.log(LoggerType.GLFW, "Initialising window...");
        GLFWErrorCallback.createPrint(System.err).set();

        while (initialisingGlfw) {
            Thread.onSpinWait();
        }

        if (!glfwInitialised && !initialisingGlfw) {
            initialisingGlfw = true;
            if (!GLFW.glfwInit()) {
                Log.error(LoggerType.GLFW, "Unable to initialise GLFW...");
                return false;
            }
            glfwInitialised = true;
            initialisingGlfw = false;
        }

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_TRANSPARENT_FRAMEBUFFER, GLFW_TRUE);

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

        glfwSetWindowSizeLimits(window, 200, 200, GLFW_DONT_CARE, GLFW_DONT_CARE);
        glfwMakeContextCurrent(window);
        glfwSwapInterval(1); // vsync
        glfwShowWindow(window);

        Log.log(LoggerType.GLFW, "GLFW initialised, showing window.");
        return true;
    }

    private void setupCallbacks() {
        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            switch (action) {
                case GLFW_PRESS:
                    eventHandlers.onKeyPress(key);
                    return;
                case GLFW_RELEASE:
                    eventHandlers.onKeyRelease(key);
            }
        });

        glfwSetScrollCallback(window, (l, v, dir) -> {
            eventHandlers.onMouseScroll(dir == 1.0);
        });

        glfwSetMouseButtonCallback(window, (l, mouseButton, action, i2) -> {
            switch (action) {
                case GLFW_PRESS:
                    eventHandlers.onMousePress(mouseButton);
                    return;
                case GLFW_RELEASE:
                    eventHandlers.onMouseRelease(mouseButton);
            }
        });

        glfwSetWindowSizeCallback(window, (l, width, height) -> {
            this.displayWidth = width;
            this.displayHeight = height;
        });
    }

    private TauqWindow getInstance() {
        return this;
    }

    public long getWindow() {
        return window;
    }

    public OpenGLHandler getRenderer() {
        return renderer;
    }

    public PixelCoords getMouseXY() {
        DoubleBuffer coords = BufferUtils.createDoubleBuffer(2);
        glfwGetCursorPos(window, coords, coords);
        return new PixelCoords(coords.get(0), coords.get(1));
    }

    public EventHandler getEventHandler() {
        return eventHandlers;
    }

    public Thread getRenderThread() {
        return renderThread;
    }

    public int getDisplayWidth() {
        return displayWidth;
    }

    public int getDisplayHeight() {
        return displayHeight;
    }

    public TPanel getMainPanel() {
        return mainTPanel;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

}
