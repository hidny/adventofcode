package practice.snaky_problem.GUI;

public class Controller {

	private DemoFrame frame;
	
	public Controller() {
		this.frame = new DemoFrame(this);
	}
	
	public void start() {
		
	}
	
	public static void main(String args[]) {
		Controller c = new Controller();
	}

}
