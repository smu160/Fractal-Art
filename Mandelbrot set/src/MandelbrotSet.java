import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MandelbrotSet extends Canvas{

    public final int WIDTH = 800;
    public final int HEIGHT = 600;
    public final double SCALE = 200.0;
    public final int MAX_ITERATIONS = 5000;
    // public int[] histogram = new int[WIDTH * HEIGHT];

    private BufferedImage image;

    public MandelbrotSet() {
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        render();
        JFrame frame = new JFrame("Mandelbrot Set");
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.getContentPane().add(this);
        frame.setVisible(true);

    }

    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, null);
    }

    /*
        (0 - 800)
     */

    public void render() {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                int color = escape((x - (WIDTH / 2.0)) / SCALE, (y - (HEIGHT / 2.0)) / SCALE);
                image.setRGB(x, y, color);
            }
        }
    }

    public int escape(double x, double y) {
        double x0 = x;
        double y0 = y;
        int iteration = 0;
        while(x * x + y * y < 4 && iteration < MAX_ITERATIONS){
            double xTemp = x * x - y * y + x0;
            double yTemp = 2 * x * y + y0;
            x = xTemp;
            y = yTemp;
            iteration++;
        }

        // If the point is within the Mandelbrot set, then color it black
        if(iteration == MAX_ITERATIONS) {
            return 0x000000;
        }

        // If the max number of iterations was not reached, then the point is
        // not in the Mandelbrot set; color it white.
        return 0xFFFFFF;
    }

    public static void main(String[] args) {

        new MandelbrotSet();

    } // end main
} // end class
