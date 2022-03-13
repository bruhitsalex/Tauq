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

        GL11.glClearColor(0f, 0f, 0f, 0f);

        while (!GLFW.glfwWindowShouldClose(parent.getWindow())) {
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
            calculateFPS();

            GL11.glMatrixMode(GL11.GL_PROJECTION);
            GL11.glLoadIdentity();
            GL11.glOrtho(0f, parent.getDisplayWidth(), parent.getDisplayHeight(), 0f, 0f, 1f);
            GL11.glViewport(0, 0, parent.getScaledDisplayWidth(), parent.getScaledDisplayHeight());

            render(parent);

            GLFW.glfwSwapBuffers(parent.getWindow());
            GLFW.glfwPollEvents();
        }

        Log.log(LoggerType.GLFW, "Terminating GLFW");
        GLFW.glfwTerminate();
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

    private void render(TauqWindow parent) {
        parent.getMainPanel().draw();
    }

    public int getFPS() {
        return fps;
    }

}
