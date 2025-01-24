package practice.snaky_problem.GUI;

import javax.swing.JFrame;

public class DemoFrame extends JFrame {

	private Controller cont;
	private DemoPanel panel;
	
	public DemoFrame(Controller cont) {
		super();
		
		this.cont = cont;
		
		this.panel = new DemoPanel(this.cont);
		
		setupFrame();
	}
	
	public void setupFrame() {
		this.setContentPane(panel);
		this.setSize(
				3 * Constants.TOP_LEFT_X + (Constants.NUM_CELLS_HORI + 1) * Constants.GRID_WIDTH,
				3 * Constants.TOP_LEFT_Y + (Constants.NUM_CELLS_VERT + 1) * Constants.GRID_WIDTH
		);
		this.setResizable(true);
		this.setTitle("lets go");
		this.setVisible(true);
		
	}

}
