package net.bruhitsalex.tauq.graphics;

import net.bruhitsalex.tauq.misc.Logger;
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

public class GLFWHandler {

    private static final int INIT_DISPLAY_WIDTH = 1280;
    private static final int INIT_DISPLAY_HEIGHT = 720;
    private static final String INIT_DISPLAY_TITLE = "Tauq";

    private static long window;

    public static void run() {
        Logger.log(LoggerType.GLFW, "Running LWJGL version " + Version.getVersion());
        if (init()) {
            OpenGLHandler.loop();
        }
    }

    private static boolean init() {
        Logger.log(LoggerType.GLFW, "Initialising window...");
        GLFWErrorCallback.createPrint(System.err).set();

        if (!GLFW.glfwInit()) {
            Logger.fatal(LoggerType.GLFW, "Unable to initialise GLFW...");
            return false;
        }

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

        window = glfwCreateWindow(INIT_DISPLAY_WIDTH, INIT_DISPLAY_HEIGHT, INIT_DISPLAY_TITLE, NULL, NULL);

        if (window == 0) {
            Logger.fatal(LoggerType.GLFW, "Unable to create window object...");
            return false;
        }

        // register events, do number priority system n shit (sort list so it knows what to call first)

        try (MemoryStack stack = stackPush()) {
            IntBuffer pWidth = stack.mallocInt(1);
            IntBuffer pHeight = stack.mallocInt(1);

            glfwGetWindowSize(window, pWidth, pHeight);

            GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            glfwSetWindowPos(
                    window,
                    (vidMode.width() - pWidth.get(0)) / 2,
                    (vidMode.height() - pHeight.get(0)) / 2
            );
        }

        glfwMakeContextCurrent(window);
        glfwSwapInterval(1); // vsync
        glfwShowWindow(window);

        Logger.log(LoggerType.GLFW, "GLFW initialised, showing window.");
        return true;
    }

    public static long getWindow() {
        return window;
    }

}
