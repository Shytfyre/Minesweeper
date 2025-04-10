package Engine.IO;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import static org.lwjgl.opengl.GL11.*;


public class Window {
    //see Main- comment
    private final int width, height;
    private final String title;
    private long window;
    public int frames;
    public static long time;
    public Input input;

    private Grid grid;


    // see Main- comment
    public Window(int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;
    }

    public void create() {
        if (!GLFW.glfwInit()) {
            System.err.println("ERROR 1: GLFW wasn't initialized");
            return;
        }
        // this is why height/width is useless - I'm grabbing the native screen stats directly
        GLFWVidMode videoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());

        int numRows = 9;  //can and should be changed using flags/cases or something depending on wanted grid size
                          //or even both using cases for grid scaling (9x9-30-16) and flags for windowed/fullscreen
        int maxWidth = videoMode.width();
        int numCols = numRows; //super redundant as the grid is a square, for visibility only
        int tileSize = (int) ((maxWidth/2) / (numRows*1.2));


        input = new Input();
        window = GLFW.glfwCreateWindow(videoMode.width(), videoMode.height(), title, GLFW.glfwGetPrimaryMonitor(), 0);

        if (window == 0) {
            System.err.println("ERROR 2: Window wasn't initialized");
            return;
        }
        //This line centers the window for windowed mode:
        //GLFW.glfwSetWindowPos(window, (videoMode.width() - width / 2), (videoMode.height() - height / 2));
        GLFW.glfwMakeContextCurrent(window);
        GL.createCapabilities();

        initGraphics(videoMode.width(), videoMode.height());

        int gridWidth = numCols * tileSize;
        int gridHeight = numRows * tileSize;


        //Centering the Grid inside the window, added 10p offset vertically for aesthetics - but not needed
        int offsetX = (videoMode.width() - gridWidth) / 2;
        int offsetY = videoMode.height() - gridHeight - 10;

        grid = new Grid(numRows, numCols, tileSize, offsetX, offsetY);



        GLFW.glfwSetKeyCallback(window, input.getKeyboardCallback());
        GLFW.glfwSetCursorPosCallback(window, input.getMousePosCallback());
        GLFW.glfwSetMouseButtonCallback(window, input.getMouseButtonCallback());


        GLFW.glfwShowWindow(window);

        GLFW.glfwSwapInterval(1);

        time = System.currentTimeMillis();
    }

    public Grid getGrid() {
        return grid;
    }

    public void update() {

        GLFW.glfwPollEvents();
        frames++;
        if (System.currentTimeMillis() > time + 1000) {
            GLFW.glfwSetWindowTitle(window, title + " | FPS: " + frames);
            time = System.currentTimeMillis();
            frames = 0;
        }
    }

    public void swapBuffers() {

        GLFW.glfwSwapBuffers(window);
    }

    public boolean shouldClose() {

        return GLFW.glfwWindowShouldClose(window);
    }

    public void destroy() {
        input.destroy();
        GLFW.glfwWindowShouldClose(window);
        GLFW.glfwDestroyWindow(window);
        GLFW.glfwTerminate();
    }

    public void initGraphics(int videoModeWidth, int videoModeHeight){
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();

        glOrtho(0, videoModeWidth, videoModeHeight, 0, -1, 1);
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();

        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

    }

    public void render(){
        glClear(GL_COLOR_BUFFER_BIT);
        grid.render();
        swapBuffers();

    }
}