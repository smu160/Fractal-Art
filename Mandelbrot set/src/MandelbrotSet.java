import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MandelbrotSet extends Canvas{

    public final int WIDTH = 1920;
    public final int HEIGHT = 1080;
    public final double SCALE = 450.0;
    public final int MAX_ITERATIONS = 5000;
    public int[] histogram = new int[MAX_ITERATIONS + 1];

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

    public void render() {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                int color = escape((x - (WIDTH / 2.0)) / SCALE, (y - (HEIGHT / 2.0)) / SCALE);
                image.setRGB(x, y, color);
            }
        }
    }

    /**
     * Complex data type is not used. Complex-number operations are manually simulated
     * by using two real numbers.
     * The parts of z_{n+1} = z²_n + c are substituted with:
     *
     * z = x + iy
     * z² = x² + i2xy - y²
     * c = x_0 + iy_0
     *
     * so, x = Re(z² + c) = x² - y² + x_0 and y = Im(z² + c) = 2xy + y_0
     *
     * @param x simulated real part to be tested
     * @param y simulated complex part to be tested
     * @return the integer form of the color black or the color white
     */
    public int escape(double x, double y) {
        double x0 = x;
        double y0 = y;
        int iteration = 0;
        while(x * x + y * y < 4 && iteration < MAX_ITERATIONS){
            double xTemp = x * x - y * y + x0;
            double yTemp = 2 * x * y + y0;

            // Periodicity checking
            if (x == xTemp && y == yTemp) {
                iteration = MAX_ITERATIONS;
                break;
            }

            x = xTemp;
            y = yTemp;
            iteration++;
        }

        histogram[iteration]++;
        long total = 0;
        for (int i = 0; i < MAX_ITERATIONS; i++) {
            total += histogram[i];
        }
        float hue = 0.0f;
        for (int i = 0; i < iteration; i++) {

            // Must be floating-point division.
            hue += (float)histogram[i] / total;
        }

        // If the point is within the Mandelbrot set, then color it black
        if(iteration == MAX_ITERATIONS) {
            return 0x000000;
        }

        // The max number of iterations was not reached. Color the point in.
        return Color.HSBtoRGB(hue, 1.0f, 1.0f);
    }

    public static void main(String[] args) {
        double startTime = System.currentTimeMillis();
        new MandelbrotSet();
        double endTime = System.currentTimeMillis();
        System.out.println("It took " + (endTime - startTime) / 1000 + " seconds to render");
    } // end main
} // end class
