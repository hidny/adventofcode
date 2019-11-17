package practice.klotskiBlockThing;

public class ShapePos {

	
	private int i;
	private int j;
	
	public int getI() {
		return i;
	}

	public int getJ() {
		return j;
	}


	public final boolean table[][];
	
	boolean isGoalShape = false;

	public int goali = -1;
	public int goalj = -1;

	//extra params you need to insert:
	public boolean isMovable = true;
	
	//TODO: for complex ones...
	public void setImmobile() {
		isMovable = false;
	}
	
	public ShapePos(int i, int j, boolean table[][]) {
		this(i, j, table, false, -1, -1);
	}
	
	public ShapePos(int i, int j, boolean table[][], boolean isGoalShape, int goali, int goalj) {
		this.i = i;
		this.j = j;
		this.table = table;
		
		this.isGoalShape = isGoalShape;
		this.goali = goali;
		this.goalj = goalj;
		
	}
	
	public ShapePos hardCopyReposition(int inew, int jnew, ShapePosRecycler recycleCopy) {
		ShapePos newShapePos = new ShapePos(inew, jnew, table, isGoalShape, goali, goalj);
		
		newShapePos.isMovable = this.isMovable;
		newShapePos.recycle = recycleCopy;
		
		return newShapePos;
	}
	
	//Make moving just mean getting another copy of shape that is somewhere else:
	private ShapePosRecycler recycle = null;

	//pre: done before ever moving the shapes:
	public void setupShapeRecyclingProgram(int height, int width) {
		recycle = new ShapePosRecycler(this, height, width);
	}
	
	//pre: recycling program is setup
	public ShapePos getShapeThatIsMovedInDir(int dir) {
		if(recycle == null) {
			System.out.println("Recycle is null");
		}
		
		return recycle.getShapeThatIsMovedInDir(this, dir);
	}
	
	
	
	
}
