package org.pandaframework.editor

import org.lwjgl.glfw.Callbacks.glfwFreeCallbacks
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.glfw.GLFWKeyCallback
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11
import org.lwjgl.system.MemoryUtil.NULL

/**
 * @author Ranie Jade Ramiso
 */
class Application(val args: Array<String>) {
    private val errorCallback = GLFWErrorCallback.createPrint(System.err)

    private val keyCallback: GLFWKeyCallback = GLFWKeyCallback.create { window, key, scanCode, action, mods ->

    }

    private var window: Long = 0

    fun run() {
        try {
            init()
            loop()

            glfwFreeCallbacks(window);
            glfwDestroyWindow(window)

        } finally {
            glfwTerminate()
            glfwSetErrorCallback(null).free()
        }
    }

    private fun init() {
        glfwSetErrorCallback(errorCallback)

        if (!glfwInit()) {
            throw IllegalStateException()
        }

        window = glfwCreateWindow(WIDTH, HEIGHT, "World Editor Playground", NULL, NULL)

        if (window == NULL) {
            glfwTerminate()
            throw RuntimeException("Failed to create window")
        }

        glfwSetKeyCallback(window, keyCallback)

        // Get the resolution of the primary monitor
        val videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor())

        // Center our window
        glfwSetWindowPos(
            window,
            (videoMode.width() - WIDTH) / 2,
            (videoMode.height() - HEIGHT) / 2
        )

        glfwMakeContextCurrent(window)
        glfwSwapInterval(1)
        glfwSwapInterval(1)
    }

    private fun loop() {
        GL.createCapabilities()

        GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f)


        while (!glfwWindowShouldClose(window)) {
            val time = glfwGetTime()

            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT or GL11.GL_DEPTH_BUFFER_BIT)
            glfwSwapBuffers(window)
            glfwPollEvents()
        }
    }

    companion object {
        const val WIDTH = 800
        const val HEIGHT = 600
    }
}
