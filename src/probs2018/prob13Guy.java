package probs2018;

import java.io.File;

import number.IsNumber;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import utils.Mapping;

public class prob13Guy {

	public int direction;
	
	public int i;
	public int j;
	private int numMovesDebug = 0;
	
	public int turnNumber = 1;
	boolean moved = false;
	
	public static ArrayList <prob13Guy>guys = new ArrayList<prob13Guy>();
	
	public prob13Guy(int i, int j, int direction) {
		this.i = i;
		this.j = j;
		this.direction = direction;
	}
	
	public static boolean hasCollision() {
		if(getCollision()[0] != -1) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void resetItereation() {
		for(int k=0; k<guys.size(); k++) {
			guys.get(k).moved = false;
		}
	}
	
	public static int guyDirection(int i, int j) {
		for(int k=0; k<guys.size(); k++) {
			if(guys.get(k).i == i && guys.get(k).j == j && guys.get(k).moved == false) {
				return guys.get(k).direction;
			}
		}
		return -100;
	}
	
	public static int guyDirectionIgnoreIteration(int i, int j) {
		for(int k=0; k<guys.size(); k++) {
			if(guys.get(k).i == i && guys.get(k).j == j ) {
				return guys.get(k).direction;
			}
		}
		return -100;
	}
	
	public static void sortGuys() {
		ArrayList <prob13Guy>guysSorted = new ArrayList<prob13Guy>();
		
		//sop(guys.size());
		int bestIndex;
		while(guys.size() > 0) {
			bestIndex = 0;
			
			for(int m=1; m<guys.size(); m++) {
				if(guys.get(bestIndex).getValue() > guys.get(m).getValue()) {
					bestIndex = m;
				}
			}
			
			guysSorted.add(guys.get(bestIndex));
			
			guys.remove(bestIndex);
		}
	
		guys = guysSorted;
		//sop(guys.size());
	}

	public int getValue() {
		return 100000*i + j;
	}
	
	public static int removeAndGetNextIndex(int i, int j, int gi) {
		int numRm = 0;
		int numBack = 0;
	
		for(int k=0; k<guys.size(); k++) {
			if(guys.get(k).i == i && guys.get(k).j == j) {
				guys.remove(k);
				if(k<=gi) {
					gi--;
					numBack++;
				}
				k--;
				numRm++;
				
			}
		}
		if(numRm != 2) {
			sop("bad collision");
			exit(1);
		}
		if(numBack == 0) {
			sop("bad index adjust");
			exit(0);
		}
		return gi;
	}
	
	public static int[] getCollision() {
		for(int i=0; i<guys.size(); i++) {
			for(int j=i+1; j<guys.size(); j++) {
				if(guys.get(i).i == guys.get(j).i && guys.get(i).j == guys.get(j).j) {
					return new int[] { guys.get(i).i, guys.get(i).j};
				}
			}
		}
		
		return new int[] {-1};
	}
	
	public void setNextTurnNumber() {
		if(turnNumber >=1) {
			turnNumber = -1;
		} else {
			turnNumber++;
		}

		direction = (direction + turnNumber + 4) % 4;
	}
	
	//pre set turn number first
	public void moveOneStraight() {
		numMovesDebug++;
		//sop("before");
		//sop("i=" + this.i);
		//sop("j=" + this.j);
		
		if(direction == 0) {
			this.i--;
		} else if(direction == 1) {
			this.j++;
		} else if(direction == 2) {
			this.i++;
		} else if(direction == 3) {
			this.j--;
		} else {
			sop("Unknown direction: " + direction);
			exit(1);
		}
		if(i<0 || j <0) {
			sop("aah");
		}
		//sop("i=" + this.i);
		//sop("j=" + this.j);
		//sop("moved");
		moved = true;
	}
	
	
	
	public static void sop(Object a) {
		System.out.println(a.toString());
	}
	public static void sopl(Object a) {
		System.out.println(a.toString());
	}
	
	public static int pint(String s) {
		if (IsNumber.isNumber(s)) {
			return Integer.parseInt(s);
		} else {
			sop("Error: (" + s + " is not a number");
			return -1;
		}
	}
	
	public static void exit() {
		exit(0);
	}
	public static void exit(int code) {
		sop("Exit with code " + code);
		
		System.exit(code);
	}
}
