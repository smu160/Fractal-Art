import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MandelbrotSetGenerator {

	//static int[] histogram = new int[n];
	
    // return number of iterations to check if c = a + ib is in Mandelbrot set
    public static int mand(Complex z0, int max) {
        Complex z = z0;
        for (int t = 0; t < max; t++) {
            if (z.abs() > 2.0) return t;
            z = z.times(z).plus(z0);
        }
        return max;
    }

    public static void main(String[] args)  {
        
    	int total = 0;
    	double xc   = 0.0;
        double yc   = 0.0;
        double size = 5;

        int n = 1440;   // create an n * n sized window
        int max = 256;  // maximum number of iterations
        
        int[] histogram = new int[n];
        
        // Read in color map
        File file = new File("mandel.txt");
        Color[] colors = new Color[max];
		Scanner in;
		try {
			in = new Scanner(file);
			while(in.hasNext()) { 
				for (int row = 0; row < max; row++) {
					int r = in.nextInt();
		            int g = in.nextInt();
		            int b = in.nextInt();
		            colors[row] = new Color(r, g, b);
                }
            }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

        Picture picture = new Picture(n, n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                double x = xc - size / 2 + size * i / n;
                double y = yc - size / 2 + size * j / n;
                Complex z0 = new Complex(x, y);
                
                for (i = 0; i < max; i++) {
                	total += histogram[i];
                }
                double hue = 0.0;
                for (i = 0; i < mand(z0, max - 1); i++) {
                  hue += histogram[i] / (double)total; 	// Must be floating-point division.
                }
                
               // int colorIndex = mand(z0, max - 1);
                picture.set(i, (n - 1 - j), colors[(int) hue]);
            }
        }
        picture.show();

    } // end main
} // end class
