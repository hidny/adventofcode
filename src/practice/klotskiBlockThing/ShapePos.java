package practice.klotskiBlockThing;

public class ShapePos {

	
	public int i=-1;
	public int j=-1;
	
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
	
	public ShapePos hardCopy() {
		ShapePos newShapePos = new ShapePos(i, j, table, isGoalShape, goali, goalj);
		
		newShapePos.isMovable = this.isMovable;
		
		
		return newShapePos;
	}
	
}
