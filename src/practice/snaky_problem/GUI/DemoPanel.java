package practice.snaky_problem.GUI;

import javax.swing.JPanel;

import practice.snaky_problem.logic.BoardStateStatic;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DemoPanel extends JPanel {

	
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
	
	
	 public static int gridXPress = -1;
     public static int gridYPress = -1;
	
	private void setupListeners() {
		
		addMouseListener(new MouseAdapter() {
            private Color background;

            @Override
            public void mousePressed(MouseEvent e) {
                background = getBackground();
                setBackground(Color.RED);
                repaint();
                
                int x = e.getX();
                int y = e.getY();
                
                gridXPress = (x - Constants.TOP_LEFT_X) / Constants.GRID_WIDTH;
                gridYPress = (y - Constants.TOP_LEFT_Y) / Constants.GRID_WIDTH;
                
                if(x >=Constants.TOP_LEFT_X
                		&& y >=Constants.TOP_LEFT_Y
                		&& gridXPress < Constants.NUM_CELLS_HORI 
                		&& gridYPress < Constants.NUM_CELLS_VERT 
                		&& gridXPress >= 0 
                		&& gridYPress >=0) {
                	//pass
                } else {
                	gridXPress = -1;
                	gridYPress = -1;
                }
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                setBackground(background);
                
                int x = e.getX();
                int y = e.getY();
                
                int gridXRelease = (x - Constants.TOP_LEFT_X) / Constants.GRID_WIDTH;
                int gridYRelease = (y - Constants.TOP_LEFT_Y) / Constants.GRID_WIDTH;
                
                if(x >=Constants.TOP_LEFT_X
                		&& y >=Constants.TOP_LEFT_Y
                		&& gridXRelease < Constants.NUM_CELLS_HORI 
                		&& gridYRelease < Constants.NUM_CELLS_VERT 
                		&& gridXRelease >= 0 
                		&& gridYRelease >=0) {
                	
                	if(gridXRelease == gridXPress && gridYPress == gridYRelease) {
                		app.insertGUIMove(gridXRelease, gridYRelease);
                	}
                	//pass
                } else {
                	gridXRelease = -1;
                	gridYRelease = -1;
                }
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
         
         
         for(int i=0; i<=Constants.NUM_CELLS_HORI; i++) {
        	 
        	 int xLocation = Constants.TOP_LEFT_X + i*Constants.GRID_WIDTH;
        	 g.drawLine(
        			 xLocation, 
        			 Constants.TOP_LEFT_Y, 
        			 xLocation, 
        			 Constants.TOP_LEFT_Y + Constants.NUM_CELLS_VERT*Constants.GRID_WIDTH
        	);
         }

         g.setColor(Color.BLACK);
         
         for(int i=0; i<=Constants.NUM_CELLS_VERT; i++) {
        	 
        	 int yLocation = Constants.TOP_LEFT_Y + i*Constants.GRID_WIDTH;
        	 g.drawLine(
        			 Constants.TOP_LEFT_X, 
        			 yLocation, 
        			 Constants.TOP_LEFT_X + Constants.NUM_CELLS_HORI*Constants.GRID_WIDTH,
        			 yLocation
        	);
         }
         
         for(int i=0; i<Constants.NUM_CELLS_VERT; i++) {
        	 for(int j=0; j<Constants.NUM_CELLS_HORI; j++) {
            	 
        		 if(BoardStateStatic.get(i, j) == Constants.PLAYER_1) {

        	         g.setColor(Color.YELLOW);
        			 g.fillOval(
                			 Constants.TOP_LEFT_X + j*Constants.GRID_WIDTH + Constants.GRID_WIDTH/2 - Constants.PIECE_WIDTH /2,
                			 Constants.TOP_LEFT_Y + i*Constants.GRID_WIDTH + Constants.GRID_WIDTH/2 - Constants.PIECE_WIDTH /2,
                			 Constants.PIECE_WIDTH,
                			 Constants.PIECE_WIDTH
                	);
        			 
        		 } else if(BoardStateStatic.get(i, j) == Constants.PLAYER_2) {

        	         g.setColor(Color.BLACK);
        			 g.fillOval(
                			 Constants.TOP_LEFT_X + j*Constants.GRID_WIDTH + Constants.GRID_WIDTH/2 - Constants.PIECE_WIDTH /2,
                			 Constants.TOP_LEFT_Y + i*Constants.GRID_WIDTH + Constants.GRID_WIDTH/2 - Constants.PIECE_WIDTH /2,
                			 Constants.PIECE_WIDTH,
                			 Constants.PIECE_WIDTH
                	);
        		 }
        		 
             }
         }
         
         g2.setStroke(oldStroke);
         
     }
	 
}
