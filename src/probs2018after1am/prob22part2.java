package probs2018after1am;

import java.io.File;

import number.IsNumber;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import aStar.AstarAlgo;
import aStar.AstarNode;
import utils.Mapping;

public class prob22part2 {

	static int LIMIT = 10000;
	
	public static int ROCKY =0;
	public static int NARROW = 1;
	public static int WET = 2;
	
	
	//myinput:
	
	public static int y = 731;
	public static int x = 9;
	public static int DEPTH = 11109;
	
	
	//Random
	/*
	public static int y = 796;
	public static int x = 9;
	public static int DEPTH = 6969;
	//expected: 1087
	//got: 1087
	*/

	/*public static int y = 10;
	public static int x = 10;
	public static int DEPTH = 510;
	*/
	
	public static void main(String[] args) {
	
		for(int i=0; i<=y; i++) {
			for(int j=0; j<=x; j++) {
				int temp = typePART2(i, j);
				if(i==y && x == j) {
					temp = ROCKY;
				}
				if(temp == WET) {
					sop("=");
				} else if(temp == NARROW) {
					sop("|");
				}else {
					sop(".");
				}
			}
			sopl();
		}
		sopl();
		
		
		int answer = 0;
		
		prob22pos start = new prob22pos();
		prob22pos goal = new prob22pos(x, y, true, false);
		
		ArrayList<AstarNode> path;
		
		path = AstarAlgo.astar(start, goal);
		
		
		for(int i=1; i<path.size(); i++) {
			sopl("index" + i);
			if( ((prob22pos)path.get(i)).coordX == ((prob22pos)path.get(i-1)).coordX 
					&& ((prob22pos)path.get(i)).coordY == ((prob22pos)path.get(i-1)).coordY ){
				answer += 7;
			} else {
				
				answer += 1;
			}
			if(((prob22pos)path.get(i)).coordX - ((prob22pos)path.get(i-1)).coordX > 0) {
				sopl("right");
			} else if(((prob22pos)path.get(i)).coordX - ((prob22pos)path.get(i-1)).coordX < 0) {
				sopl("left");
			} else if(((prob22pos)path.get(i)).coordY - ((prob22pos)path.get(i-1)).coordY > 0) {
				sopl("down");
			} else if(((prob22pos)path.get(i)).coordY - ((prob22pos)path.get(i-1)).coordY < 0) {
				sopl("up");
			} else {
				if(((prob22pos)path.get(i)).torch == true) {
					sopl("switch torch on");
				} else {
					sopl("switch torch off");
				}
				
				if(((prob22pos)path.get(i)).climbingGear == true) {
					sopl("switch climb on");
				} else {
					sopl("switch climb off");
				}
			}
			sopl(path.get(i));
			int temp = typePART2(((prob22pos)path.get(i)).coordY, ((prob22pos)path.get(i)).coordX);
			
			if(temp == WET) {
				sopl("WET");
			} else if(temp == NARROW) {
				sopl("NARROW");
			}else {
				sopl("ROCKY");
			}
			sopl("---");
		}
		
		sopl("Answer: " + answer);
		
		
	
	}

	
	//I called the wrong class earlier... causing a bug.
	public static int typePART2(int i, int j) {
		long temp = erosion2PART2(i, j);
		if(temp  % 3 == 0) {
			return ROCKY;
		} else if(temp % 3 == 1) {
			return WET;
		} else if(temp % 3 == 2) {
			return NARROW;
		} else {
			sopl("what??");
			exit(1);
			return -1;
		}
	}
	
	public static long erosion2PART2(int i, int j) {
		return (indexPART2(i,j) + DEPTH) % 20183;
		
	}
	static boolean foundIndex[][] = new boolean[LIMIT][LIMIT];
	static long ref[][] = new long[LIMIT][LIMIT];
	
	public static long indexPART2(int i, int j) {
		if(foundIndex[i][j]) {
			return ref[i][j];
		}
		if(i==y && j ==x) {
			return 0;
		}
		if(i==0 && j ==0) {
			
			return 0;
		} else if(i==0) {
			return 16807*j;
		} else if(j==0) {
			return 48271*i;
		} else {
			foundIndex[i][j] = true;
			long temp = (erosion2PART2(i-1, j) * erosion2PART2(i, j-1))  %20183;
			ref[i][j] = temp;
			if(temp < 0) {
				sopl(temp);
			}
			return temp;
		}
	}

	public static void sop(Object a) {
		System.out.print(a.toString());
	}
	public static void sopl(Object a) {
		System.out.println(a.toString());
	}

	public static void sopl() {
		System.out.println();
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
