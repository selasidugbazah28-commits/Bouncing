//vvvvvvvvvvvvvvvvvvvvvvvvvvvvvv DON'T CHANGE! vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
// Graphics Libraries
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
public class BasicGameApp implements Runnable {

    //Sets the width and height of the program window
    final int WIDTH = 1000;
    final int HEIGHT = 700;


    //Variable Definition Section
    //You can set their initial values too
    // Like Mario mario = new Mario(); //
    Tenis tenis;
    Racket racket;
    Image background;







    // Initialize your variables and construct your program objects here.
    public BasicGameApp() { // BasicGameApp constructor
        setUpGraphics();

        //variable and objects
        //create (construct) the objects needed for the game
        tenis = new Tenis(500,350,4,4,70,70);
        tenis.name = "Tenis Tenis";
        tenis.aliveimage = Toolkit.getDefaultToolkit().getImage("Tenis.jpg");
        tenis.deadimage = Toolkit.getDefaultToolkit().getImage("HitBall.jpeg");

        racket = new Racket (300, 400, 1.5, 1.5, 130, 130 );
        racket.image = Toolkit.getDefaultToolkit().getImage("Racket.jpg");
        background = Toolkit.getDefaultToolkit().getImage("TenisCourt.png");



    }
    // end BasicGameApp constructor

    public void moveThings() {
        //call the move() code for each object  -
        tenis.move();
        racket.move();

    }

    public void checkCollisions(){
        //System.out.println(tenis.hitbox);
        //System.out.println(racket.hitbox);
        if (tenis.hitbox.intersects(racket.hitbox) ){
            System.out.println("You have hit the ball");
            tenis.isAlive = false;
            tenis.dx = -tenis.dx;
            racket.dx = -racket.dx;
            tenis.dy = -tenis.dy;
            racket.dy= -racket.dy;
        }
        else {
            tenis.isAlive = true;
        }

    }

    //Paints things on the screen using bufferStrategy
    private void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);

        //draw the images
        // Signature: drawImage(Image img, int x, int y, int width, int height, ImageObserver observer)
        g.drawImage(background, 0, 0, WIDTH, HEIGHT, null);
        if (tenis.isAlive) {
            g.drawImage(tenis.aliveimage, tenis.xpos, tenis.ypos, tenis.width, tenis.height, null);
        }
        else {
            g.drawImage(tenis.deadimage,tenis.xpos, tenis.ypos, tenis.width, tenis.height, null );
        }
        g.drawImage(racket.image, racket.xpos, racket.ypos, racket.width, racket.height, null);



        // Keep the code below at the end of render()
        g.dispose();
        bufferStrategy.show();
    }














    //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
//vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv DON'T CHANGE! vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
    //Declare the variables needed for the graphics
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;
    public BufferStrategy bufferStrategy;

    // PSVM: This is the code that runs first and automatically
    public static void main(String[] args) {
        BasicGameApp ex = new BasicGameApp();   //creates a new instance of the game
        new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method
    }

    // main thread
    // this is the code that plays the game after you set things up
    public void run() {
        //for the moment we will loop things forever.
        while (true) {
            moveThings();  //move all the game objects
            checkCollisions();
            render();  // paint the graphics
            pause(10); // sleep for 10 ms
        }
    }

    //Pauses or sleeps the computer for the amount specified in milliseconds
    public void pause(int time ) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
        }
    }

    private Image getImage(String filename){
        return Toolkit.getDefaultToolkit().getImage(filename);
    }

    //Graphics setup method
    private void setUpGraphics() {
        frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.

        panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
        panel.setLayout(null);   //set the layout

        // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
        // and trap input events (Mouse and Keyboard events)
        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);

        panel.add(canvas);  // adds the canvas to the panel.

        // frame operations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
        frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
        frame.setResizable(false);   //makes it so the frame cannot be resized
        frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

        // sets up things so the screen displays images nicely.
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        System.out.println("DONE graphic setup");
    }
//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
}
