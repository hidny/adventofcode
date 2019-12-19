package probs2019;

import java.util.ArrayList;
import aStar.AstarNode;

//Much simpler algo that just gets distance from A to B:

public class prob18stateAtoB implements aStar.AstarNode {

	public int coordX = -1;
	public int coordY = -1;
	

	private int coordGoalX = -1;
	private int coordGoalY = -1;
	
	public char map[][];
	
	private ArrayList<String> ClosedDoorsOrKeyBlocks;
	
	public prob18stateAtoB(char map[][], int coordX, int coordY, int coordGoalX, int coordGoalY) {
		this(map, coordX, coordY, coordGoalX, coordGoalY, new ArrayList<String>());

	}
	
	public prob18stateAtoB(char map[][], int coordX, int coordY, int coordGoalX, int coordGoalY, ArrayList<String> ClosedDoorsOrKeyBlocks2) {
		this.map = map;
		this.coordY = coordY;
		this.coordX = coordX;
		
		this.coordGoalX = coordGoalX;
		this.coordGoalY = coordGoalY;

		this.ClosedDoorsOrKeyBlocks = ClosedDoorsOrKeyBlocks2;
		
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
		ret.add(new prob18stateAtoB(map, coordX, coordY-1, coordGoalX, coordGoalY, ClosedDoorsOrKeyBlocks));
		ret.add(new prob18stateAtoB(map, coordX-1, coordY, coordGoalX, coordGoalY, ClosedDoorsOrKeyBlocks));
		ret.add(new prob18stateAtoB(map, coordX+1, coordY, coordGoalX, coordGoalY, ClosedDoorsOrKeyBlocks));
		ret.add(new prob18stateAtoB(map, coordX, coordY+1, coordGoalX, coordGoalY, ClosedDoorsOrKeyBlocks));
		
		for(int i=0; i<ret.size(); i++) {
			int x = ((prob18stateAtoB)ret.get(i)).coordX;
			int y = ((prob18stateAtoB)ret.get(i)).coordY;
			if(isWallorClosedDoor( x, y ) ) {
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
	
	
	
	public boolean isWallorClosedDoor(int coordX, int coordY) {

		//negative values are invalid, as they represent a location outside the building.
		if(coordX < 0 || coordY < 0 || coordX >= map[0].length || coordY >= map.length) {
			return true;
		}
		
		//Check if there's a closed door:
		if((map[coordY][coordX] >= 'A' && map[coordY][coordX] <= 'Z') || (map[coordY][coordX] >= 'a' && map[coordY][coordX] <= 'z')) {
			for(int i=0; i<ClosedDoorsOrKeyBlocks.size(); i++) {
				if(map[coordY][coordX] == ClosedDoorsOrKeyBlocks.get(i).charAt(0)) {
					return true;
				}
			}
		}
		
		if(map[coordY][coordX] == '#') {
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
}
