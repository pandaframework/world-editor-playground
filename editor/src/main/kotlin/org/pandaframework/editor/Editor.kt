package org.pandaframework.editor

import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11
import org.pandaframework.editor.glfw.GLFWApplication

/**
 * @author Ranie Jade Ramiso
 */
class Editor(val args: Array<String>): GLFWApplication() {
    override fun setup() {
        super.setup()

        GL.createCapabilities()

        GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f)

    }

    override fun update(delta: Double) {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT or GL11.GL_DEPTH_BUFFER_BIT)
    }

}
