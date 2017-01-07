package probs;

import java.util.ArrayList;

import aStar.AstarNode;

public class prob22Cluster implements AstarNode {

	public static prob22comp array[][] = new prob22comp[100][100];
	
	private int goalX = -1;
	private int goalY = -1;
	
	private int endX = 0;
	private int endY = 0;
	public prob22comp[][] getArray() {
		return array;
	}
	
	public void setXYComp(int x, int y, int size, int used, int avail) {
		array[y][x] = new prob22comp(x, y, size, used, avail);
	}
	
	@Override
	public long getAdmissibleHeuristic() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<AstarNode> getNodeNeighbours() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getCostOfMove(AstarNode nextPos) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	public static void printCluster() {
		boolean compExistsOnRow;
		for(int i=0; i<array.length; i++) {
			compExistsOnRow = false;
			for(int j=0; j<array[0].length; j++) {
				if(array[i][j] != null) {
					System.out.print(array[i][j]);
					compExistsOnRow = true;
				}
			}
			if(compExistsOnRow) {
				System.out.println();
			}
		}
		System.out.println();
	}

	public static void printMoves() {
		for(int i=0; i<array.length; i++) {
			for(int j=0; j<array[0].length; j++) {
				if(array[i][j] == null) {
					continue;
				}
				if(array[i][j].isUsedZero()) {
					//Can't move 0 data around
					continue;
				}
				if(i > 0) {
					if(array[i][j].fitsInside(array[i-1][j])) {
						System.out.println("x" + j + "-y" + i + " fits inside " + "x" + j + "-y" + (i-1) + ")");
					}
				}
				if( j > 0 ) {
					if(array[i][j].fitsInside(array[i][j-1])) {
						System.out.println("x" + j + "-y" + i + " fits inside " + "x" + (j-1) + "-y" + i + ")");
					}
				}
				if(i + 1 < array.length && array[i + 1][j] != null) {
					if(array[i][j].fitsInside(array[i+1][j])) {
						System.out.println("x" + j + "-y" + i + " fits inside " + "x" + j + "-y" + (i+1) + ")");
					}
				}
				if( j + 1 < array[0].length && array[i][j + 1] != null) {
					if(array[i][j].fitsInside(array[i][j+1])) {
						System.out.println("x" + j + "-y" + i + " fits inside " + "x" + (j+1) + "-y" + i + ")");
					}
				}
			}
			
		}
		System.out.println();
	}
	
	

	//Not yet useful function:
	public static void printNodesThatCantMove() {
		for(int i=0; i<array.length; i++) {
			for(int j=0; j<array[0].length; j++) {
				if(array[i][j] == null) {
					continue;
				}
				if(i > 0) {
					if(array[i][j].couldFitInside(array[i-1][j])) {
						//System.out.println("x" + j + "-y" + i + " fits inside " + "x" + j + "-y" + (i-1) + ")");
						continue;
					}
				}
				if( j > 0 ) {
					if(array[i][j].couldFitInside(array[i][j-1])) {
						//System.out.println("x" + j + "-y" + i + " fits inside " + "x" + (j-1) + "-y" + i + ")");
						continue;
					}
				}
				if(i + 1 < array.length && array[i + 1][j] != null) {
					if(array[i][j].couldFitInside(array[i+1][j])) {
						//System.out.println("x" + j + "-y" + i + " fits inside " + "x" + j + "-y" + (i+1) + ")");
						continue;
					}
				}
				if(j + 1 < array[0].length && array[i][j + 1] != null) {
					if(array[i][j].couldFitInside(array[i][j+1])) {
						//System.out.println("x" + j + "-y" + i + " fits inside " + "x" + (j+1) + "-y" + i + ")");
						continue;
					}
				}
				
				System.out.println("Can\'t ever move " + "x" + j + "-y" + i);
			}
			
		}
		System.out.println();
	}
	
	public ArrayList<prob22comp> getArrayList() {
		ArrayList<prob22comp> ret = new ArrayList<prob22comp>();
		
		for(int i=0; i<array.length; i++) {
			for(int j=0; j<array[i].length; j++) {
				if(array[i][j] != null) {
					ret.add(array[i][j]);
				}
			}
		}
		
		return ret;
	}
	
	

	public ArrayList<prob22comp> getVoidsCouldBeFilled() {

		//TODO: don't search every time...
		ArrayList<prob22comp> ret = new ArrayList<prob22comp>();
		for(int i=0; i<array.length; i++) {
			
			NEXTCELL:
			for(int j=0; j<array[0].length; j++) {
				if(array[i][j] == null) {
					continue;
				}
				ArrayList<prob22comp> neighbours = getNeighbours(i, j);
				
				for(int k=0; k<neighbours.size(); k++)  {
					if(neighbours.get(k).isUsedZero() == false && neighbours.get(k).fitsInside(array[i][j])) {
						ret.add(array[i][j]);
						continue NEXTCELL;
					}
				}
			}
		}
		return ret;
	}
	
	
	public ArrayList<prob22comp> getGoalNeighbours() {
		return getNeighbours(goalY, goalX);
	}
	
	private ArrayList<prob22comp> getNeighbours(int i, int j) {
		ArrayList<prob22comp> ret = new ArrayList<prob22comp>();
		if(i > 0) {
			ret.add(array[i-1][j]);
		}
		if( j > 0 ) {
			ret.add(array[i][j-1]);
		}
		if(i + 1 < array.length && array[i + 1][j] != null) {
			ret.add(array[i+1][j]);
		}
		if(j + 1 < array[0].length && array[i][j + 1] != null) {
			ret.add(array[i][j+1]);
		}
		
		return ret;
	}
	
	
	public void removeNullComps() {
		prob22comp arrayNew[][];
		
		int width =0;
		int length =0;
		while( width < array[0].length && array[0][width] != null ) {
			width++;
		}
		
		while( length < array.length && array[length][0] != null) {
			length++;
		}
		
		arrayNew = new prob22comp[length][width];
		
		for(int i=0; i<arrayNew.length; i++) {
			for(int j=0; j<arrayNew[0].length; j++) {
				arrayNew[i][j] = array[i][j];
			}
		}
		
		array = arrayNew;
	}
	
	public void setOrigGoalProb2() {

		goalX = 0;
		goalY = 0;
		
		while( goalX < array[0].length && array[0][goalX] != null ) {
			goalX++;
		}
		goalX--;
		
		System.out.println("Goal X: " + goalX + "   Goal Y " + goalY);
	}
	
	
	
}
