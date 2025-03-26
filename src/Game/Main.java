package Game;

import Engine.IO.Input;
import org.lwjgl.glfw.GLFW;


import Engine.IO.Window;

public class Main implements Runnable {
    public Thread game;
    public Window window;
    public final int WIDTH = 1280, HEIGHT = 760;
    public final String TITLE = "BOOM";

    public void start() {
        game = new Thread(this, TITLE);
        game.start();
    }

    public void init() {
        window = new Window(WIDTH, HEIGHT, TITLE);
        window.create();
    }

    public void run() {
        init();
        while (!window.shouldClose()) {
            update();
            render();
            if (Input.isKeyDown(GLFW.GLFW_KEY_ESCAPE)) {
                System.out.println("Game has been closed.");
                return;
            }
        }
        window.destroy();
    }

    private void update() {
        window.update();
        if (Input.isButtonDown(GLFW.GLFW_MOUSE_BUTTON_LEFT)) {
            System.out.println("X: " + Input.getMouseX() + "| Y: " + Input.getMouseY());
        }
    }

    private void render() {
        window.swapBuffers();
    }

    public static void main(String[] args) {
        new Main().start();
    }
}
