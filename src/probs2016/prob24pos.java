package probs2016;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import aStar.AstarNode;

public class prob24pos implements aStar.AstarNode {

	private int coordX;
	private int coordY;
	
	public static long puzzleInput = 0;
	
	public static char map[][];
	
	public static void main(String args[]) {
		
	}
	
	public prob24pos(int coordX, int coordY) {
		this.coordX = coordX;
		this.coordY = coordY;
	}
	
	@Override
	public long getAdmissibleHeuristic(aStar.AstarNode goal) {
		prob24pos goalPos = (prob24pos)goal;
		return Math.abs(goalPos.coordX - coordX) + Math.abs(goalPos.coordY - coordY);
	}

	@Override
	public ArrayList<AstarNode> getNodeNeighbours() {
		ArrayList<AstarNode> ret = new ArrayList<AstarNode>();
		
		ret.add(new prob24pos(coordX+1, coordY));
		ret.add(new prob24pos(coordX-1, coordY));
		ret.add(new prob24pos(coordX, coordY+1));
		ret.add(new prob24pos(coordX, coordY-1));
		
		for(int i=0; i<ret.size(); i++) {
			int x = ((prob24pos)ret.get(i)).coordX;
			int y = ((prob24pos)ret.get(i)).coordY;
			if(isWall( x, y ) ) {
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
	
	public static boolean isWall(int coordX, int coordY) {

		//negative values are invalid, as they represent a location outside the building.
		if(coordX < 0 || coordY < 0 || coordX >= map[0].length || coordY >= map.length) {
			return true;
		}
		
		if(map[coordY][coordX] == '#') {
			return true;
		} else {
			return false;
		}
	}
	
	public static void setPuzzleInput(char mapOfDucts[][]) {
		map = mapOfDucts;
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
		return "( " + this.coordX + ", " + this.coordY + ")";
	}
}
