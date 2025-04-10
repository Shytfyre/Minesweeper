package Engine.IO;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glVertex2f;


public class Grid {

private final int rows,cols;
private final int tileSize;
private final int offsetX;
private final int offsetY;

    public int getTileSize() {
        return tileSize;
    }

    public int getOffsetX() {
        return offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }

public Grid (int rows, int cols, int tileSize, int offsetX, int offsetY) {
    this.rows = rows;
    this.cols = cols;
    this.tileSize = tileSize;
    this.offsetX = offsetX;
    this.offsetY = offsetY;

}

public void render() {
    for (int row = 0; row <rows; row++){
        for (int col = 0; col < cols; col++){
            int x = offsetX + col * tileSize;
            int y = offsetY + row * tileSize;
            drawCell(x, y, tileSize);
        }
    }

}

public void drawCell(int x, int y, int size){
    glColor3f(0.8f, 0.8f, 0.8f);
    glBegin(GL_QUADS);
    //glVertex2i possible, but less precise in case of animation or scaling
    glVertex2f(x, y);
    glVertex2f(x + size, y);
    glVertex2f(x + size, y + size);
    glVertex2f(x, y + size );
    glEnd();

    glColor3f(0.2f, 0.2f, 0.2f);
    glBegin(GL_LINE_LOOP);
    //same shit, just depends on grid scaling
    glVertex2f(x, y);
    glVertex2f(x + size, y);
    glVertex2f(x + size, y + size);
    glVertex2f(x, y + size );
    glEnd();




}

}





