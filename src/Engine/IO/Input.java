package Engine.IO;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;

public class Input {
    private static boolean[] keys = new boolean[GLFW.GLFW_KEY_LAST];
    private static boolean[] buttons = new boolean[GLFW.GLFW_MOUSE_BUTTON_LAST];
    private static double mouseX, mouseY;

    private GLFWKeyCallback keyboard;
    private GLFWMouseButtonCallback mouseButton;
    private GLFWCursorPosCallback mousePos;


    public Input() {
        keyboard = new GLFWKeyCallback() {
            public void invoke(long window, int key, int scancode, int action, int mods) {
                keys[key] = (action != GLFW.GLFW_RELEASE);
            }
        };

        mouseButton = new GLFWMouseButtonCallback() {
            public void invoke(long window, int button, int action, int mods) {
                buttons[button] = (action != GLFW.GLFW_RELEASE);
            }
        };

        mousePos = new GLFWCursorPosCallback() {
            public void invoke(long window, double xpos, double ypos) {
                mouseX = xpos;
                mouseY = ypos;

            }
        };

    };

    public static boolean isKeyDown (int key){
        return keys[key];
    }

    public static boolean isButtonDown (int button){
        return buttons[button];
    }

    public void destroy() {
        keyboard.free();
        mouseButton.free();
        mousePos.free();
    }



    public static double getMouseX(){
        return mouseX;
    }

    public static double getMouseY(){
        return mouseY;
    }





    public GLFWKeyCallback getKeyboardCallback() {
        return keyboard;
    }

    public GLFWMouseButtonCallback getMouseButtonCallback() {
        return mouseButton;
    }

    public GLFWCursorPosCallback getMousePosCallback() {
        return mousePos;
    }

    public double getMouseXCallback() {
        return mouseX;
    }

    public double getMouseYCallback() {
        return mouseY;
    }

}