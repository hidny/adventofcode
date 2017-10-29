package aStarPrio;

import java.util.ArrayList;

public interface AstarNode {

	
	public long getAdmissibleHeuristic();
	
	public ArrayList<AstarNode> getNodeNeighbours();
	
	//pos: nextPos is attainable by one move:
	public long getCostOfMove(AstarNode nextPos);
	
	public int hashCode();
}
