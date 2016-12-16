package probs;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import aStar.AstarNode;

public class prob13pos implements aStar.AstarNode {

	private int coordX;
	private int coordY;
	
	public static int goalX;
	public static int goalY;
	public static long puzzleInput = 0;
	
	public static void main(String args[]) {
		for(int i=0; i<20; i++) {
			System.out.print(countNumberOfBinaryOnes(i) % 2);
		}
		System.out.println();
		
		setGoalAndPuzzleInput(31, 39, 1362);
		
		int MOVE_DIAG = 20;
		
		for(int i=0; i<80; i++) {
			for(int j=0; j<80; j++) {
				if(isWall(j - MOVE_DIAG, i - MOVE_DIAG)) {
					System.out.print("#");
				} else {
					if(j - MOVE_DIAG == 1 && i - MOVE_DIAG == 1) {
						System.out.print("S");
					} else if(j - MOVE_DIAG  == 31 && i - MOVE_DIAG == 39) {
						System.out.print("E");
						
					} else {
						System.out.print(new prob13pos(j - MOVE_DIAG, i - MOVE_DIAG).getNodeNeighbours().size());
						
						
					}
				}
			}
			System.out.println();
		}
		
	}
	
	public prob13pos(int coordX, int coordY) {
		this.coordX = coordX;
		this.coordY = coordY;
	}
	
	@Override
	public long getAdmissibleHeuristic() {
		return Math.abs(goalX - coordX) + Math.abs(goalY - coordY);
	}

	@Override
	public ArrayList<AstarNode> getNodeNeighbours() {
		ArrayList<AstarNode> ret = new ArrayList<AstarNode>();
		
		ret.add(new prob13pos(coordX+1, coordY));
		ret.add(new prob13pos(coordX-1, coordY));
		ret.add(new prob13pos(coordX, coordY+1));
		ret.add(new prob13pos(coordX, coordY-1));
		
		for(int i=0; i<ret.size(); i++) {
			int x = ((prob13pos)ret.get(i)).coordX;
			int y = ((prob13pos)ret.get(i)).coordY;
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
		if(coordX < 0 || coordY < 0) {
			return true;
		}
		
		long number = coordX*coordX + 3*coordX + 2*coordX*coordY + coordY + coordY*coordY;
		number += puzzleInput;
		
		int numOnes = countNumberOfBinaryOnes(number);
		
		if(numOnes % 2 == 0) {
			return false;
		} else {
			return true;
		}
	}
	
	public static void setGoalAndPuzzleInput(int goalxin, int goalyin, long input) {
		goalX = goalxin;
		goalY = goalyin;
		puzzleInput = input;
	}
	
	public static int countNumberOfBinaryOnes(long number) {
		if(number < 0) {
			System.out.println("ERROR: how to do negative numbers?");
			System.exit(1);
		}
		int ret = 0;
		long current = number;
		while(current > 0) {
			if(current % 2 == 1) {
				ret++;
			}
			
			current /= 2;
		}
		
		return ret;
	}
	
	//AHA:
	//This helps. I guess some types use hashCode, and some types use equals...
	//That's soooo annoying.
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
