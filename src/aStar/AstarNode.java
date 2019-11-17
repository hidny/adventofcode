package aStar;

import java.util.ArrayList;

//WARNING: AUTO-IMPLEMENT DOESN'T GET YOU ALL NECESSARY FUNCTIONS IN THIS INTERFACE!
//DO A COPY PASTE
public interface AstarNode {

	
	public long getAdmissibleHeuristic(AstarNode goal);
	
	public ArrayList<AstarNode> getNodeNeighbours();
	
	//pos: nextPos is attainable by one move:
	public long getCostOfMove(AstarNode nextPos);
	
	
	//
	//To do the right thing and override the hash's 'contains' function, we need to override both toString and hashCode. Isn't Java great?
	public String toString();
	public int hashCode();
	public boolean  equals (Object object);
	
}
