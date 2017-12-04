package practice;

import java.util.ArrayList;

import aStar.AstarNode;

public class HexMaze implements AstarNode {

	private boolean FirstRowShifted = false;
	
	public static boolean wall[][];
	
	private int i;
	private int j;
	
	//TODO: this should take in the goal node
	@Override
	public long getAdmissibleHeuristic(AstarNode goal) {
		
		
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
	//To override the hash contains function, we need to override both toString and hashCode. Isn't Java great?
	public String toString() {
		return "(" + i + ", " + j + ")";
	}
	public int hashCode() {
		return 0;
	}
	

}
