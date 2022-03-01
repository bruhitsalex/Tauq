package net.bruhitsalex.tauq.graphics;

import net.bruhitsalex.tauq.misc.Logger;
import net.bruhitsalex.tauq.misc.LoggerType;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

public class OpenGLHandler {

    public static void loop() {
        Logger.log(LoggerType.OPENGL, "Starting loop");

        GL.createCapabilities();
        GL11.glClearColor(1f, 0f, 0f, 0f);

        while (!GLFW.glfwWindowShouldClose(GLFWHandler.getWindow())) {
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
            GLFW.glfwSwapBuffers(GLFWHandler.getWindow());
            GLFW.glfwPollEvents();
        }
    }

}
