package net.bruhitsalex.tauq.graphics;

import net.bruhitsalex.tauq.misc.Logger;
import net.bruhitsalex.tauq.misc.LoggerType;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

public class OpenGLHandler {

    private static int frameCount = 0;
    private static double previousFrameTime = 0;
    private static int fps;

    public static void loop() {
        Logger.log(LoggerType.OPENGL, "Starting loop");

        GL.createCapabilities();
        GL11.glClearColor(0f, 0f, 0f, 0f);

        while (!GLFW.glfwWindowShouldClose(GLFWHandler.getWindow())) {
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
            GLFW.glfwSwapBuffers(GLFWHandler.getWindow());
            GLFW.glfwPollEvents();
            calculateFPS();
            render();
        }
    }

    private static void calculateFPS() {

    }

    private static void render() {
        double currentTime = GLFW.glfwGetTime();
        frameCount++;
        if (currentTime - previousFrameTime >= 1.0) {
            fps = frameCount;
            frameCount = 0;
            previousFrameTime = currentTime;
        }
    }

    public static int getFps() {
        return fps;
    }

}
