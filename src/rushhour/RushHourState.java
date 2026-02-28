package rushhour;

import java.util.ArrayList;

public class RushHourState {

	public int map[][];
	
	public String lastMoveDetails = "";
	
	
	public static void main(String[] args) {

		RushHourState test = new RushHourState(6);
		
		//Features:
		//Cars are auto-labeled
		//Defaults to car length 2.
		//There's a warning there's an overlap
		
		test.insertCar(0, 0, 3, true);
		test.insertCar(2, 2, true, true);
		test.insertCar(4, 3, true);

		test.insertCar(0, 3, false);
		test.insertCar(0, 4, 3, false);
		test.insertCar(1, 1, false);
		test.insertCar(3, 2, false);
		test.insertCar(4, 5, false);
		
		System.out.println(test);
		
		ArrayList<RushHourState> options = test.getOptions();
		
		for(int i=0; i<options.size(); i++) {
			System.out.println(options.get(i).lastMoveDetails);
			System.out.println(options.get(i));
		}
		
		System.out.println("Number of moves: " + options.size());
	}
	
	//Goal 1 to the right
	
	public RushHourState(int size) {
		this.map = new int[size][size];
		
	}
	public RushHourState(int map[][], int label, Coord start, Coord end, int dirUsed,
			RushHourState prev) {
		this.map = map;
		lastMoveDetails = "Moved " + label + " from " +start + " to " + end + ". (" + Coord.convertIndexDirToString(dirUsed) + ")";
		this.goalI = prev.goalI;
		this.goalJ = prev.goalJ;
		this.depth = prev.depth + 1;
		this.prev = prev;
		
	}
	private int curLabelIndex = 2;
	public void insertCar(int i, int j, boolean isHori) {
		insertCar(i, j, 2, isHori);
	}
	public void insertCar(int i, int j, boolean isHori, boolean isMain) {
		insertCar(i, j, 2, isHori, isMain);
	}
	
	public void insertCar(int i, int j, int length, boolean isHori) {
		insertCar(i, j, length, isHori, false);
	}
	
	
	public void insertCar(int i, int j, int length, boolean isHori, boolean isMain) {

		int labelToUse = -1;
		if(isMain) {
			labelToUse = 1;
		} else {
			labelToUse = curLabelIndex;
		}
		
		if(isHori) {
			for(int j1=j; j1<j+length; j1++) {
				insertLabel(labelToUse, i, j1);
			}
		} else {

			for(int i1=i; i1<i+length; i1++) {
				insertLabel(labelToUse, i1, j);
			}
		}
		
		curLabelIndex++;
	}
	
	private void insertLabel(int label, int i, int j) {

		if(this.map[i][j] != 0) {
			System.out.println("ERROR: overlap at i = " + i + " and j = " + j);
			System.exit(1);
		}
		this.map[i][j] = label;
	}
	
	public String toString() {
		String ret = "";
		for(int i=0; i<this.map.length; i++) {
			for(int j=0; j<this.map[0].length; j++) {
				if(this.map[i][j] == 0) {
					ret += "_";
				} else {
					if(this.map[i][j] < 10) {
						ret += this.map[i][j];
					} else {
						ret += (char)((this.map[i][j] - 10) + 'A');
					}
				}
			}
			ret += "\n";
		}
		return ret;
	}

	public ArrayList<RushHourState> getOptions() {
		ArrayList<RushHourState> ret = new ArrayList<RushHourState>();
		
		for(int i=0; i<this.map.length; i++) {
			for(int j=0; j<this.map[0].length; j++) {
				
				if(this.map[i][j] == 0) {
					
					for(int indexDirFromEmptySpace = 0; indexDirFromEmptySpace < 4; indexDirFromEmptySpace++) {
						
						RushHourState tmp = findCarToFillSpaceInDir(indexDirFromEmptySpace, new Coord(i, j), map);
						
						if(tmp != null) {
							ret.add(tmp);
						}
					}
					
				}
			}
		}
		
		
		return ret;
	}
	
	
	public RushHourState findCarToFillSpaceInDir(int dirIndexFromEmpty, Coord emptySpace, int map[][]) {

		RushHourState ret = null;
		
		Coord cur = emptySpace.moveInDir(dirIndexFromEmpty);

		for(;cur.moveInDir(dirIndexFromEmpty).inBound(map); cur = cur.moveInDir(dirIndexFromEmpty)) {
			
			Coord next = cur.moveInDir(dirIndexFromEmpty);

			if(this.map[cur.getI()][cur.getJ()] != 0 && this.map[cur.getI()][cur.getJ()] == this.map[next.getI()][next.getJ()]) {
				
				//FOUND CAR TO MOVE:
				Coord next2 = next.moveInDir(dirIndexFromEmpty);
				
				int carLength = 2;
				if(next2.inBound(map) && this.map[next2.getI()][next2.getJ()] == this.map[next.getI()][next.getJ()]) {
					carLength = 3;
				}
				int label = this.map[cur.getI()][cur.getJ()];
				
				int newmap[][] = hardCopyMap(this.map);
				
				
				Coord removeCoords = cur;
				for(int k=0; k<carLength; k++) {
					newmap[removeCoords.getI()][removeCoords.getJ()] = 0;
					removeCoords = removeCoords.moveInDir(dirIndexFromEmpty);
				}
				
				Coord addCoords = emptySpace;
				for(int k=0; k<carLength; k++) {
					newmap[addCoords.getI()][addCoords.getJ()] = label;
					addCoords = addCoords.moveInDir(dirIndexFromEmpty);
				}
				
				ret = new RushHourState(newmap, label,
						Coord.getTopLeftCoord(cur, dirIndexFromEmpty, carLength),
						Coord.getTopLeftCoord(emptySpace, dirIndexFromEmpty, carLength),
						Coord.getOppositeDir(dirIndexFromEmpty),
						this
					);
				break;
				
				//END FOUND CAR TO MOVE
	
			} else if(this.map[cur.getI()][cur.getJ()] != 0 && this.map[cur.getI()][cur.getJ()] != this.map[next.getI()][next.getJ()]) {
				break;
			}
		}
		
		return ret;
	}
	
	
	public static int[][] hardCopyMap(int map[][]) {
		int ret[][] = new int[map.length][map[0].length];
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[0].length; j++) {
				ret[i][j]=map[i][j];
			}
		}
		return ret;
	}

	public String getLastMoveDetails() {
		return lastMoveDetails;
	}
	
	

	public int goalI = -1;
	public int goalJ = -1;
	public int depth = 0;
	RushHourState prev = null;

	public boolean hitGoal() {
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[0].length; j++) {
				if(map[i][j] == 1) {
					return hitGoal(new Coord(i, j));
				}
			}
		}
		System.out.println("ERROR: could not find car 1!");
		System.exit(1);
		return false;
	}
	public boolean hitGoal(Coord x) {
		if((x.getI() == goalI || goalI == -1) && (x.getJ() == goalJ || goalJ == -1)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void printFullSolutionGivenEndNode(RushHourState end) {
		
		RushHourState curFromEnd = end;
		
		ArrayList<RushHourState> list = new ArrayList<RushHourState>();
		
		while(curFromEnd != null) {
			list.add(curFromEnd);
			curFromEnd = curFromEnd.prev;
		}
		
		System.out.println("Printing path:");
		
		
		
		for(int i=list.size() - 1; i>=0; i--) {
			RushHourState cur = list.get(i);
			
			if(i < list.size() - 1) {
				System.out.println(cur.lastMoveDetails);
				System.out.println();
			}
			System.out.println(cur);
			System.out.println();
			
		}
		
	}

	
}

