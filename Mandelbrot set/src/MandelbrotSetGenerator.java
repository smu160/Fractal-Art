import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
 
public class MandelbrotSetGenerator extends JFrame {
 
    private final int MAX_NUM_OF_ITERATIONS = 1000;
    private final int HEIGHT = 600;
    private final int WIDTH = 600;
    
    DrawingPanel drawingPanel;
    BufferedImage Image;
    
    public MandelbrotSetGenerator() {
    	setGUI();
    	setDrawingPanel();
    }
    
    /**
     *     z = x + iy 
     * ==> z^2 = (x + iy)(x + iy)
     * ==> z^2 = x^2 + 2ixy + (-y^2)
     * ==> z^2 = x^2 + i2xy - y^2
     * 
     *     c = x_0 + iy_0
     * 
     *     x = Re(z^2 + c) = x^2 - y^2 + x_0 
     *     y = Im(z^2 + c) = 2xy + y_0
     */
	private int computeIterations(double complexNumRealPart, double complexNumImagPart) {
    	
    	double x = 0.0;
    	double y = 0.0;
    	int iterationCounter = 0;
    	while(x * x + y * y < 4 && iterationCounter < MAX_NUM_OF_ITERATIONS) {
    		double xtemp = x * x - y * y + x_0;
    		y = 2 * x * y + y_0;
    		x = xtemp;

    		// TODO Add Comment
    		if(iterationCounter >= MAX_NUM_OF_ITERATIONS) {
    			return MAX_NUM_OF_ITERATIONS;
    		}
    		iterationCounter++;
    	}
    	return iterationCounter;
    } 
    
    // Initialize the drawing panel and the Image and display them
    private void setDrawingPanel() {
    	drawingPanel = new DrawingPanel();
		Image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		drawingPanel.setVisible(true);
		this.add(drawingPanel, BorderLayout.CENTER);
	}

	// Creates a Frame and sets its initial properties
    private void setGUI() {
    	this.setTitle("Fractal Generator");
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.setSize(WIDTH, HEIGHT);
    	this.setResizable(false);
    	this.setLocationRelativeTo(null);
    	this.setVisible(true);
	}

    // Creates a component that you can actually draw on
    private class DrawingPanel extends JPanel{
      
    	@Override public Dimension getPreferredSize() {
    		return new Dimension(WIDTH, HEIGHT);
    	}
      
        // Draws the actual fractal 
    	@Override public void paint(Graphics drawObject) {
    	  	drawObject.drawImage(Image, 0, 0, null);
        }
    }
    
	public static void main(String[] args) {
		new MandelbrotSetGenerator();
    } // end main
	
} // end class
