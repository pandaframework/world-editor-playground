package org.pandaframework.editor.glfw

import org.lwjgl.glfw.Callbacks
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.glfw.GLFWKeyCallback
import org.lwjgl.system.MemoryUtil.NULL

/**
 * @author Ranie Jade Ramiso
 */
abstract class GLFWApplication {
    private val errorCallback = GLFWErrorCallback.createPrint(System.err)

    private val keyCallback: GLFWKeyCallback = GLFWKeyCallback.create { window, key, scanCode, action, mods ->

    }

    private var window: Long = 0

    fun run() {
        try {
            init()
            loop()

            Callbacks.glfwFreeCallbacks(window);
            glfwDestroyWindow(window)

        } finally {
            destroy()
            glfwTerminate()
            glfwSetErrorCallback(null).free()
        }
    }

    protected open fun setup() { }

    protected abstract fun update(delta: Double)

    protected open fun destroy() { }

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
    }

    private fun loop() {
        setup()

        var lastUpdate = 0.0

        while (!glfwWindowShouldClose(window)) {
            lastUpdate = glfwGetTime() - lastUpdate

            update(lastUpdate)

            glfwSwapBuffers(window)
            glfwPollEvents()
        }
    }

    companion object {
        const val WIDTH = 800
        const val HEIGHT = 600
    }
}
