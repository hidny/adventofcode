package practice;

import java.util.ArrayList;

import aStar.AstarNode;

public class HexMaze implements AstarNode {

	private boolean firstRowShifted = false;
	
	//Pre: this is a square
	public boolean wall[][];
	
	private int i;
	private int j;
	
	public HexMaze(boolean wall[][], int i, int j, boolean firstRowShifted) {
		this.wall = wall;
		this.i = i;
		this.j = j;
		this.firstRowShifted = firstRowShifted;
	}
	
	@Override
	public long getAdmissibleHeuristic(AstarNode goal) {
		//TODO: improve it
		HexMaze temp = (HexMaze)goal;
		
		return Math.abs(temp.i - this.i);
	}

	//TODO: 2d maze has a fancier way of getting neighbours
	@Override
	public ArrayList<AstarNode> getNodeNeighbours() {
		ArrayList<AstarNode> ret = new ArrayList<AstarNode>();
		
		if(this.j > 0 && wall[i][j-1] == false) {
			ret.add(new HexMaze(wall, i, j-1, firstRowShifted));
		}
		
		if(this.j  + 1 < wall[0].length && wall[i][j+1] == false) {
			ret.add(new HexMaze(wall, i, j+1, firstRowShifted));
		}
		
		if(rowToTheRight(this.i)) {
			//get Neighbours above
			if(this.i > 0) {
				if(wall[i-1][j] == false) {
					ret.add(new HexMaze(wall, i-1, j, firstRowShifted));
				}
				
				if(this.j + 1 < wall[0].length && wall[i-1][j+1] == false) {
					ret.add(new HexMaze(wall, i-1, j+1, firstRowShifted));
				}
			}
			
			//get Neighbours below
			if(this.i + 1 < wall.length) {
				if(wall[i+1][j] == false) {
					ret.add(new HexMaze(wall, i+1, j, firstRowShifted));
				}
				
				if(this.j + 1 < wall[0].length && wall[i+1][j+1] == false) {
					ret.add(new HexMaze(wall, i+1, j+1, firstRowShifted));
				}
			}
		} else {
			if(this.i > 0) {
				if(j > 0 && wall[i-1][j-1] == false) {
					ret.add(new HexMaze(wall, i-1, j-1, firstRowShifted));
				}
				
				if(wall[i-1][j] == false) {
					ret.add(new HexMaze(wall, i-1, j, firstRowShifted));
				}
			}
			
			//get Neighbours below
			if(this.i + 1 < wall.length) {
				if(j > 0 && wall[i+1][j-1] == false) {
					ret.add(new HexMaze(wall, i+1, j-1, firstRowShifted));
				}
				
				if(wall[i+1][j] == false) {
					ret.add(new HexMaze(wall, i+1, j, firstRowShifted));
				}
			}
		}
		return ret;
	}

	@Override
	public long getCostOfMove(AstarNode nextPos) {
		// TODO Auto-generated method stub
		return 1;
	}
	//To override the hash contains function, we need to override both toString and hashCode. Isn't Java great?
	public String toString() {
		return "(" + i + ", " + j + ")" + hashCode();
	}
	public int hashCode() {
		return 2*i*wall[0].length + j;
	}
	
	public boolean  equals (Object object) {
		if(this.toString().equals(object.toString())) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean rowToTheRight(int i) {
		if( (this.firstRowShifted && i % 2 == 0) ||  (this.firstRowShifted  == false && i % 2 == 1)) {
			return true;
		} else {
			return false;
		}
	}

	public void print(int goali, int goalj) {
		for(int i=0; i<wall.length; i++) {
			if( rowToTheRight(i)) {
				System.out.print(" ");
			}
			
			for(int j=0; j<wall[0].length; j++) {
				
				if(wall[i][j]) {
					System.out.print("W ");
				} else {
					if(this.i == i && this.j == j) {
						System.out.print("O ");
					} else if(goali == i && goalj == j) {
						System.out.print("E ");
					} else {
						System.out.print("  ");
					}
				}
			}
			System.out.println();
		}
	}
}
