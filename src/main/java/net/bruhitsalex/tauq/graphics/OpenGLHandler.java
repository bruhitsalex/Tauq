package net.bruhitsalex.tauq.graphics;

import net.bruhitsalex.tauq.misc.Log;
import net.bruhitsalex.tauq.misc.LoggerType;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

public class OpenGLHandler {

    private int frameCount = 0;
    private double previousFrameTime = 0;
    private int fps;

    public void loop(TauqWindow parent) {
        GL.createCapabilities();
        Log.log(LoggerType.OPENGL, "Running OpenGL version " + GL11.glGetString(GL11.GL_VERSION));
        Log.log(LoggerType.OPENGL, "Starting loop");

        GL.createCapabilities();
        GL11.glClearColor(0f, 0f, 0f, 0f);

        while (!GLFW.glfwWindowShouldClose(parent.getWindow())) {
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
            GLFW.glfwSwapBuffers(parent.getWindow());
            GLFW.glfwPollEvents();
            calculateFPS();
            render();
        }
    }

    private void calculateFPS() {
        double currentTime = GLFW.glfwGetTime();
        frameCount++;
        if (currentTime - previousFrameTime >= 1.0) {
            fps = frameCount;
            frameCount = 0;
            previousFrameTime = currentTime;
        }
    }

    private void render() {

    }

    public int getFPS() {
        return fps;
    }

}
