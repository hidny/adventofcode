package practice.snaky_problem.GUI;

import javax.swing.JPanel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DemoPanel extends JPanel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	private Controller app;
	
	public DemoPanel(Controller app) {
		super();
		
		this.app = app;
		
		setupPanel();
		setupListeners();
		setupLayout();
	}

	private void setupPanel() {
		this.setBackground(Color.MAGENTA);
	}
	
	private void setupListeners() {
		
		addMouseListener(new MouseAdapter() {
            private Color background;

            @Override
            public void mousePressed(MouseEvent e) {
                background = getBackground();
                setBackground(Color.RED);
                System.out.println(app);
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                setBackground(background);
            }
        });
	}
	private void setupLayout() {
		
	}
	
	 @Override
     public void paintComponent(Graphics g) {
         super.paintComponent(g);

         Graphics2D g2 = (Graphics2D) g;

         double thickness = 2;
         Stroke oldStroke = g2.getStroke();
         g2.setStroke(new BasicStroke((float) thickness));
         int x = 0;
         int y =0;
         int width = 200;
         int height = 200;
         
         //g.drawString("BLAH", 20, 20);
         //g.drawRect(200, 200, 200, 200);
         
         for(int i=0; i<=Constants.NUM_CELLS_HORI; i++) {
        	 
        	 int xLocation = Constants.TOP_LEFT_X + i*Constants.GRID_WIDTH;
        	 g.drawLine(
        			 xLocation, 
        			 Constants.TOP_LEFT_Y, 
        			 xLocation, 
        			 Constants.TOP_LEFT_Y + Constants.NUM_CELLS_VERT*Constants.GRID_WIDTH
        	);
         }
         
         for(int i=0; i<=Constants.NUM_CELLS_VERT; i++) {
        	 
        	 int yLocation = Constants.TOP_LEFT_Y + i*Constants.GRID_WIDTH;
        	 g.drawLine(
        			 Constants.TOP_LEFT_X, 
        			 yLocation, 
        			 Constants.TOP_LEFT_X + Constants.NUM_CELLS_HORI*Constants.GRID_WIDTH,
        			 yLocation
        	);
         }
         

         //g2.drawRect(x, y, width, height);
         g2.setStroke(oldStroke);
         
     }
	 
	 /*
	  * import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel{

    private BufferedImage image;

    public ImagePanel() {
       try {                
          image = ImageIO.read(new File("image name and path"));
       } catch (IOException ex) {
            // handle exception...
       }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters            
    }

}
*/
}
