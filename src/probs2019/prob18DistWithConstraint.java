package probs2019;

import java.util.ArrayList;
import aStar.AstarNode;

//Much simpler algo that just gets distance from A to B:

public class prob18DistWithConstraint {

	public int dist;
	public int x1;
	public int y1;
	public int x2;
	public int y2;
	
	public ArrayList <String> contraints = new ArrayList <String>();

	public prob18DistWithConstraint(int dist, int x1, int y1, int x2, int y2, ArrayList<String> contraints) {
		this.dist = dist;
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.contraints = contraints;
	}
	
	public String toKey() {
		String constraintsStr = "";
		for(int i=0; i<contraints.size(); i++) {
			constraintsStr += contraints.get(i);
		}
		return x1 + "," + y1 + "," + x2 + "," + y2 + "," + constraintsStr;
	}
	
	public String toString() {
		return toKey();
	}
}
