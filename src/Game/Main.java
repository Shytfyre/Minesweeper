package Game;


import Engine.IO.*;
import org.lwjgl.glfw.GLFW;

import javax.swing.text.AbstractDocument;

import static org.lwjgl.opengl.GL11.*;


public class Main implements Runnable {
    public Thread game;
    public Window window;
    public final int WIDTH = 1500, HEIGHT = 800;
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
            render();
            update();

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



        window.render();
    }

    public static void main(String[] args) {
        new Main().start();
    }
}
