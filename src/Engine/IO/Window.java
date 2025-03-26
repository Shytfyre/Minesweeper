package Engine.IO;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11.*;

import static org.lwjgl.opengl.GL11.*;


public class Window {
    private int width, height;
    private String title;
    private long window;
    public int frames;
    public static long time;
    public Input input;
    private float backgroundR, backgroundG, backgroundB;
    private Grid grid;



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

        input = new Input();
        window = GLFW.glfwCreateWindow(width, height, title, 0, 0);

        if (window == 0) {
            System.err.println("ERROR 2: Window wasn't initialized");
            return;
        }

        GLFWVidMode videoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
        GLFW.glfwSetWindowPos(window, (videoMode.width() - width) / 2, (videoMode.height() - height) / 2);
        GLFW.glfwMakeContextCurrent(window);
        GL.createCapabilities();

        initGraphics();
        grid = new Grid(30,16,50);



        GLFW.glfwSetKeyCallback(window, input.getKeyboardCallback());
        GLFW.glfwSetCursorPosCallback(window, input.getMousePosCallback());
        GLFW.glfwSetMouseButtonCallback(window, input.getMouseButtonCallback());


        GLFW.glfwShowWindow(window);

        GLFW.glfwSwapInterval(1);

        time = System.currentTimeMillis();
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

    public void initGraphics(){
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0,width, height, 0, -1, 1);
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



    public void setBackgroundColor(float r, float b, float g){
        backgroundR = r;
        backgroundB = b;
        backgroundG = g;
    }

}