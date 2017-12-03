package practice;

import java.util.ArrayList;

import aStarPrio.AstarNode;

public class HexMaze implements AstarNode {

	private boolean FirstRowSlanted = false;
	
	public static boolean wall[][];
	
	//TODO: this should be gone:
		public static  int goali;
		public static  int goalj;
	
	private int i;
	private int j;
	
	//TODO: this should take in the goal node
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
		return 1;
	}
	
	

}
