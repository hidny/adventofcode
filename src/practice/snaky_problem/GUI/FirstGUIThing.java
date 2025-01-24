package practice.snaky_problem.GUI;

import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class FirstGUIThing {

	public static int GRID_WIDTH = 20;
	public static int NUM_CELLS_VERT = 40;
	public static int NUM_CELLS_HORI = 40;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JFrame myFrame = new JFrame("Player1 Snaky Game");
		
		JPanel mainPanel = new DemoPanel(null);
		myFrame.getContentPane().add(mainPanel);
		
		
		myFrame.setSize(new Dimension(500, 500));
	
		
		//myFrame.setLocation(new Point(100, 100));


		//myFrame.pack();
		myFrame.setVisible(true);
		
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
