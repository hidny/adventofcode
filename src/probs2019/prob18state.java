package probs2019;

import java.util.ArrayList;
import aStar.AstarNode;

public class prob18state implements Comparable, aStar.AstarNode {

	public int coordX = -1;
	public int coordY = -1;
	

	private int coordGoalX = -1;
	private int coordGoalY = -1;
	
	public char map[][];
	
	
	
	public int numMovesToGetToStartOfGoal = -1;
	
	public prob18state(char map[][]) {
		this(map, 0);
	}
	public prob18state(char map[][], int numMovesFromStartOfGoal) {
		
		this.map = new char[map.length][map[0].length];
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[0].length; j++) {
				if(map[i][j] == '@') {
					this.coordX = j;
					//TODO: y starts from top?
					this.coordY = i;
				}
				this.map[i][j] = map[i][j];
				
			}
		}
		
		this.numMovesToGetToStartOfGoal = numMovesFromStartOfGoal;
	}
	
	//pre: got goal
	public char[][] getStateMapAfterGoal() {
		char newMap[][] = new char[map.length][map[0].length];

		char goalObtained = map[coordGoalY][coordGoalX];
		
		char DoorOpened = (char)(goalObtained +'A' - 'a');
		//System.out.println("key obtained: " + goalObtained);
		//System.out.println("Door opened: " + DoorOpened);
		
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[0].length; j++) {
				if(map[i][j] == '@') {
					newMap[i][j] = '.';
				} else {
					newMap[i][j] = map[i][j];
				}
				
				if(map[i][j] == DoorOpened) {
					newMap[i][j] = '.';
				}
			}
		}
		
		
		newMap[coordGoalY][coordGoalX] = '@';
		
		return newMap;
	}
	
	public prob18state(char map[][], int coordX, int coordY, int coordGoalX, int coordGoalY) {
		this.map = map;
		this.coordY = coordY;
		this.coordX = coordX;
		
		this.coordGoalX = coordGoalX;
		this.coordGoalY = coordGoalY;

	}
	
	
	public void setGoal(int coordGoalX, int coordGoalY) {
		this.coordGoalY = coordGoalY;
		this.coordGoalX = coordGoalX;
	}
	
	@Override
	public long getAdmissibleHeuristic(aStar.AstarNode goal) {
		if(coordGoalX == -1) {
			System.out.println("ERROR: no goal set");
			System.exit(1);
		}
		
		if(Math.abs(this.coordGoalX - coordX) + Math.abs(this.coordGoalY - coordY) == 0) {
			return -1; //GOAL_FOUND TOOD CONST
		}
		
		return Math.abs(this.coordGoalX - coordX) + Math.abs(this.coordGoalY - coordY);
	}

	@Override
	public ArrayList<AstarNode> getNodeNeighbours() {
		ArrayList<AstarNode> ret = new ArrayList<AstarNode>();

		//Order it for prob 15:
		ret.add(new prob18state(map, coordX, coordY-1, coordGoalX, coordGoalY));
		ret.add(new prob18state(map, coordX-1, coordY, coordGoalX, coordGoalY));
		ret.add(new prob18state(map, coordX+1, coordY, coordGoalX, coordGoalY));
		ret.add(new prob18state(map, coordX, coordY+1, coordGoalX, coordGoalY));
		
		for(int i=0; i<ret.size(); i++) {
			int x = ((prob18state)ret.get(i)).coordX;
			int y = ((prob18state)ret.get(i)).coordY;
			if(isWallorLockedDoorOrWrongKey( x, y ) ) {
				ret.remove(i);
				i--;
			}
		}
		
		return ret;
	}

	@Override
	public long getCostOfMove(AstarNode nextPos) {
		return 1;
	}
	
	
	//TODO
	
	public static char friendsMap[][];
	
	public boolean isWallorLockedDoorOrWrongKey(int coordX, int coordY) {

		//negative values are invalid, as they represent a location outside the building.
		if(coordX < 0 || coordY < 0 || coordX >= map[0].length || coordY >= map.length) {
			return true;
		}
		
		if((map[coordY][coordX] >= 'a' && map[coordY][coordX] <= 'z') && (coordGoalX != coordX || coordGoalY != coordY)) {
			return true;
		}
		
		if(map[coordY][coordX] == '#' || (map[coordY][coordX] >= 'A' && map[coordY][coordX] <= 'Z')) {
			return true;
		} else {
			return false;
		}
	}
	
	
	
	
	//AHA:
	//This helps. I have to override equals so the hash will use hashCode... :(
	public boolean  equals (Object object) {
		if(this.toString().equals(object.toString())) {
			return true;
		} else {
			return false;
		}
	}
	
	public int hashCode() {
		return this.coordX * ((int)Math.pow(2, 16)) + this.coordY;
		
	}
	
	public String toString() {
		
		/*String mapDEBUG = "\n";
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[0].length; j++) {
				mapDEBUG += map[i][j];
			}
			mapDEBUG += "\n";
		}*/

		
		return /*mapDEBUG + */"( " + this.coordX + ", " + this.coordY + ")";
	}
	@Override
	public int compareTo(Object o) {
		if(((prob18state)o).numMovesToGetToStartOfGoal < this.numMovesToGetToStartOfGoal) {
			return 1;
		} else if(((prob18state)o).numMovesToGetToStartOfGoal > this.numMovesToGetToStartOfGoal) {
			return -1;
		} else {
			return 0;
		}
	}
}
