package practice.klotskiBlockThing;

public class ShapePosRecycler {

	private ShapePos grid[][];
	
	public static int OUT_OF_BOUNDS_ALLOWANCE = 1;
	
	public ShapePosRecycler(ShapePos orig, int height, int width) {
		grid = new ShapePos[height + 2 * OUT_OF_BOUNDS_ALLOWANCE][width + 2 * OUT_OF_BOUNDS_ALLOWANCE];
		
		for(int i=0; i<grid.length; i++) {
			for(int j=0; j<grid[0].length; j++) {
				grid[i][j] = orig.hardCopyReposition(i - OUT_OF_BOUNDS_ALLOWANCE, j - OUT_OF_BOUNDS_ALLOWANCE, this);
			}
		}
	}
	
	public ShapePos getShapeThatIsMovedInDir(ShapePos current, int dir) {
		int currentGridI = current.getI() + OUT_OF_BOUNDS_ALLOWANCE;
		int currentGridJ = current.getJ() + OUT_OF_BOUNDS_ALLOWANCE;
		
		if(dir == 0) {
			currentGridI--;

		} else if(dir == 1) {
			currentGridJ++;
			
		} else if(dir == 2) {
			currentGridI++;
			
		} else if(dir == 3){
			currentGridJ--;
			
		} else {
			System.out.println("ERROR");
			System.exit(1);
		}
		
		return grid[currentGridI][currentGridJ];
	}
}
