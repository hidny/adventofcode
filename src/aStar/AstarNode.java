package aStar;

import java.util.ArrayList;

public interface AstarNode {

	
	public long getAdmissibleHeuristic(AstarNode goal);
	
	public ArrayList<AstarNode> getNodeNeighbours();
	
	//pos: nextPos is attainable by one move:
	public long getCostOfMove(AstarNode nextPos);
	
	
	//To override the hash contains function, we need to override both toString and hashCode. Isn't Java great?
	public String toString();
	public int hashCode();
	
}
