package probs2024;
import java.io.File;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

import number.IsNumber;
import utils.Mapping;
import utils.Sort;

public class prob21state3 {

	//day1 part 1
	//2:38.01
	
	
	public int robotsLocations[][];
	public String curInput;
	public String curOutput;
	public boolean badState = false;
	
	prob21state3(int depth) {

		this.curInput = "";
		this.curOutput = "";
		
		this.robotsLocations = new int[depth][2];
		
		for(int i=0; i<robotsLocations.length; i++) {
			for(int j=0; j<robotsLocations[0].length; j++) {
				
					this.robotsLocations[i][0] = 0;
					this.robotsLocations[i][1] = 2;
				
			}
		}
		
	}
	
	
	prob21state3(int robotsLocationsIn[][], String curInput, String curOutput, boolean badState) {
		
		this.robotsLocations = new int[robotsLocationsIn.length][2];
		
		for(int i=0; i<robotsLocations.length; i++) {
			for(int j=0; j<robotsLocations[0].length; j++) {
				this.robotsLocations[i][j] = robotsLocationsIn[i][j];
			}
		}
		
		this.badState = badState;
		this.curInput = curInput;
		this.curOutput = curOutput;
	}
	
	public prob21state3 makeMove(int move) {
		
		if(this.badState) {
			return null;
		}
		
		prob21state3 newState = new prob21state3(robotsLocations, this.curInput + prob21b2.nextChar[move], this.curOutput, this.badState);
		
		makeMove(newState, move, 0);
		
		return newState;
	}
	
	public prob21state3 makeMove(prob21state3 newState, int move, int index) {
		

		if(move == 0) {
			newState.robotsLocations[index][0]--;
		} else if(move==1) {
			newState.robotsLocations[index][1]++;
		} else if(move==2) {
			newState.robotsLocations[index][0]++;
		} else if(move==3) {
			newState.robotsLocations[index][1]--;
		} else if(move == 4) {
			
			if(index < this.robotsLocations.length - 1) {
				char nextRobotMove = mapDir[newState.robotsLocations[index][0]][newState.robotsLocations[index][1]];
				
				int innerMove = -1;
				if(nextRobotMove == '^') {
					innerMove = 0;
				} else if(nextRobotMove == '>') {
					innerMove = 1;
				} else if(nextRobotMove == 'v') {
					innerMove = 2;
				} else if(nextRobotMove == '<') {
					innerMove = 3;
				} else if(nextRobotMove == 'A') {
					innerMove = 4;
				}
				newState = makeMove(newState, innerMove, index + 1);
			
			} else {
				

				if(newState.robotsLocations[index][0] >= mapDir.length || newState.robotsLocations[index][0] < 0) {
					newState.badState = true;
					return newState;
				}
				if(newState.robotsLocations[index][1] >= mapDir[0].length || newState.robotsLocations[index][1] < 0) {
					newState.badState = true;
					return newState;
				}

				char nextRobotMove = mapDir[newState.robotsLocations[index][0]][newState.robotsLocations[index][1]];
				
				newState.curOutput += nextRobotMove;
				
				
				
			}
		}
		
		if(move < 4) {
			
		
			
			if(newState.robotsLocations[index][0] >= mapDir.length || newState.robotsLocations[index][0] < 0) {
				newState.badState = true;
				return newState;
			}
			if(newState.robotsLocations[index][1] >= mapDir[0].length || newState.robotsLocations[index][1] < 0) {
				newState.badState = true;
				return newState;
			}
			
			char nextRobotMove = mapDir[newState.robotsLocations[index][0]][newState.robotsLocations[index][1]];
			
			if(nextRobotMove == '#') {
				newState.badState = true;
			}
		}
		
		
		return newState;
	}
	
	
	public String toString() {
		String output = "";
		for(int i=0; i<this.robotsLocations.length; i++) {
			for(int j=0; j<this.robotsLocations[0].length; j++) {
				
				output += this.robotsLocations[i][j] + ", ";
				
			}
		}
		
		output += this.curOutput.length();
		
		return output;
	}
	
	
	public static char mapDir[][] = {{'#', '^', 'A'},
									 {'<', 'v', '>'}};


	
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
			sop("Error: (" + s + ") is not a number");
			return -1;
		}
	}
	

	public static long plong(String s) {
		if (IsNumber.isLong(s)) {
			return Long.parseLong(s);
		} else {
			sop("Error: (" + s + ") is not a number");
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
	
	public static int[][] getIntTable(ArrayList<String> lines) {
		int grid[][] = new int[lines.size()][lines.get(0).length()];
		
		for(int i=0; i<lines.size(); i++) {
			
			for(int j=0; j<grid[0].length; j++) {
				if(lines.get(i).charAt(j) == '.') {
					grid[i][j] = -1;
				} else {
					grid[i][j] = (int)(lines.get(i).charAt(j) - '0');
				}
			}
		}
		
		return grid;
	}
	

	public static char[][] getCharTable(ArrayList<String> lines) {
		char grid[][] = new char[lines.size()][lines.get(0).length()];
		
		for(int i=0; i<lines.size(); i++) {
			
			for(int j=0; j<grid[0].length; j++) {
				grid[i][j] = lines.get(i).charAt(j);

			}
		}
		
		return grid;
	}

}
