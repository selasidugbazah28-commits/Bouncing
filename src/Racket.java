import java.awt.*;

public class Racket {
    String name;
    Image image;
    int xpos;
    int ypos;
    int speed;
    double dx;
    double dy;
    int width;
    int height;
    Rectangle hitbox;

    public Racket(){
        hitbox = new Rectangle(xpos, ypos, width, height); // convention of making a rectangle
    }

    public Racket(int xposInput, int yposInput, double dxInput, double dyInput, int widthInput, int heightInput){
        xpos = xposInput;
        ypos = yposInput;
        dx = dxInput;
        dy = dyInput;
        width = widthInput;
        height = heightInput;

        hitbox = new Rectangle(xpos, ypos, width, height);

    }
    public void move() {
        xpos = xpos + (int) dx; // This Is tested on January exam, know this
        ypos = ypos + (int) dy;
        if (ypos >= 700) {
            //dy = -dy;
            ypos = 0;
        }
        else if (ypos <= 0) {
            //dy = -dy;
            ypos = 700;
        }

        if (xpos >= 1000) {
            //dx = -dx;
            xpos = 0;
        }
        else if (xpos <= 0) {
            //dx = -dx;
            xpos = 1000;
        }

        hitbox = new Rectangle(xpos, ypos ,width, height);
    /*
    public void move(char key){
        if (key == 'w');
        ypos = ypos + 2;
        speed = speed + 4;
        if (key == 'a');
        xpos = xpos -2;
        speed = speed + 2;
        if (key == 's');
        ypos = ypos -2;
        speed = speed + 2;
        if (key == 'd');
        xpos = xpos + 2;
        speed = speed + 4;
    }

     */
        //public static void main(String[] args) {

    }
}
