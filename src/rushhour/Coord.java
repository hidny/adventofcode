package rushhour;

public class Coord {

	
	public Coord(int i, int j) {
		this.i = i;
		this.j = j;
	}
	
	private int i;
	private int j;
	
	public int getI() {
		return i;
	}

	public int getJ() {
		return j;
	}

	public boolean inBound(int map[][]) {
		
		return i >=0 && j>=0 && i<map.length && j<map[0].length;
		
	}

	public Coord moveInDir(int indexDir) {
		Coord newCoord = new Coord(i, j);
		
		if(indexDir == 0) {
			newCoord.i--;
		} else if(indexDir == 1) {
			newCoord.j++;
		} else if(indexDir == 2) {
			newCoord.i++;
		} else if(indexDir == 3) {
			newCoord.j--;
		} else {
			System.out.println("Invalid coord");
			System.exit(1);
		}
		
		return newCoord;
	}
	
	public String toString() {
		return "(" + i +", " + j + ")";
	}
	
	public static Coord getTopLeftCoord(Coord startCoord, int dirIndexFromEmptySpace, int carLength) {
		
		if(dirIndexFromEmptySpace == 0 || dirIndexFromEmptySpace == 3) {
			
			Coord ret = startCoord;
			for(int i=0; i<carLength -1; i++) {
				ret = ret.moveInDir(dirIndexFromEmptySpace);
			}
			
			return ret;
			
		
		} else if(dirIndexFromEmptySpace == 1 || dirIndexFromEmptySpace == 2) {
			return startCoord;
		} else {
			System.out.println("Invalid coord");
			System.exit(1);
			return null;
		}
	}
	
	public static int getOppositeDir(int indexDir) {
		if(indexDir == 0) {
			return 2;
		} else if(indexDir == 1) {
			return 3;
		} else if(indexDir == 2) {
			return 0;
		} else if(indexDir == 3) {
			return 1;
		} else {
			System.out.println("Invalid coord");
			System.exit(1);
			return -1;
		}
	}
	
	public static String convertIndexDirToString(int indexDir) {
		if(indexDir == 0) {
			return "up";
		} else if(indexDir == 1) {
			return "right";
		} else if(indexDir == 2) {
			return "down";
		} else if(indexDir == 3) {
			return "left";
		} else {
			System.out.println("Invalid coord");
			System.exit(1);
			return "";
		}
	}
}
