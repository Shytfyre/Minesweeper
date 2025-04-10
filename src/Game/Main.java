package Game;


import Engine.IO.*;
import org.lwjgl.glfw.GLFW;


//could superclass my loop for now but might need the slot for something else in the future so using an interface saves me that option
public class Main implements Runnable {
    public Thread game;
    public Window window;
    //could comment the screen settings out but cba currently - they are useless tho
    public final int WIDTH = 1500, HEIGHT = 800;
    public final String TITLE = "Minesweeper";
    public int tileSize;
    public int offsetX;
    public int offsetY;


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
        //Coordinate detection (obsolete in grid-based tracking), probably useful for BOOM
            // if (Input.isButtonDown(GLFW.GLFW_MOUSE_BUTTON_LEFT)) {
               // System.out.println("X: " + Input.getMouseX() + "| Y: " + Input.getMouseY());}

        if (Input.isButtonDown(GLFW.GLFW_MOUSE_BUTTON_LEFT)) {

            double mouseX = Input.getMouseX();
            double mouseY = Input.getMouseY();

            Grid grid = window.getGrid();
            tileSize = grid.getTileSize();
            offsetX = grid.getOffsetX();
            offsetY = grid.getOffsetY();


            int gridX = (int) ((mouseX - offsetX) / tileSize);
            int gridY = (int) ((mouseY - offsetY) / tileSize);

            System.out.println("Selected tile: " + gridX + "/ " + gridY);


        }


    }

    private void render() {



        window.render();
    }

    public static void main(String[] args) {
        new Main().start();
    }
}


