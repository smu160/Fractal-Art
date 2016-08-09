import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MandelbrotSet extends Canvas{

    public final int SCALE = 200;
    public final int WIDTH = 800;
    public final int HEIGHT = 800;
    public final int MAX_ITERATIONS = 1000;
    public int[] pixels = new int[WIDTH * HEIGHT];

    public MandelbrotSet() {
        JFrame frame = new JFrame("Mandelbrot Set");
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.getContentPane().add(this);
        frame.setVisible(true);
    }

    BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, null);
    }

    //
    public void render() {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                double x0 = 400 - SCALE/2 + SCALE*i/n;
                double y0 = 400 - SCALE/2 + SCALE*j/n;
                Complex z0 = new Complex(x0, y0);
                int color = bailout(z0, MAX_ITERATIONS)

            }
        }
    }


    // Test a complex number, z_0, to see if it is a part of the Mandelbrot set
    public boolean bailout(Complex z0, int MAX_ITERATIONS) {
        Complex z = z0;
        for(int i = 0; i < MAX_ITERATIONS; i++) {
            if (z.abs() > 2.0) {
                return false;
            }
            z = z.times(z).plus(z0);
        }
        return true;
    }

    public static void main(String[] args) {

        MandelbrotSet generator = new MandelbrotSet();

    } // end main
} // end class
