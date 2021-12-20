package probs2021;
import java.io.File;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import aStar.AstarNode;
import number.IsNumber;
import utils.Mapping;
import utils.Sort;

public class prob15bAstarPos implements aStar.AstarNode {

	
	
	private int i;
	private int j;

	public int getI() {
		return i;
	}

	public int getJ() {
		return j;
	}
	
	private prob15bAstarPos map[][];
	private ArrayList<String> lines;
	
	private int goali;
	private int goalj;
	
	public prob15bAstarPos(int i, int j, prob15bAstarPos map[][], ArrayList<String> lines, int goali, int goalj) {
		this.i = i;
		this.j = j;
		this.map = map;
		this.goali = goali;
		this.goalj = goalj;
		this.lines = lines;
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

	public static long plong(String s) {
		if (IsNumber.isLong(s)) {
			return Long.parseLong(s);
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

	@Override
	public long getAdmissibleHeuristic(AstarNode goal) {
		if(i == goali && j == goalj) {
			return -1;//Goal found
		} else {
			//TODO: add this adjustment later:
			//return goali + goalj - i - j + (1 - (1.0* i + 1.0*j)/(2.0*Math.max(i, j)));
			return goali + goalj - i - j;
		}
	}

	@Override
	public ArrayList<AstarNode> getNodeNeighbours() {
		// TODO Auto-generated method stub
		
		ArrayList<AstarNode> ret = new ArrayList<AstarNode>();
		
		for(int i2=Math.max(i-1, 0); i2<=Math.min(i+1, goali); i2++) {
			for(int j2=Math.max(j-1, 0); j2<=Math.min(j+1, goalj); j2++) {
				
				if((i2 != i && j2 == j) || (i2 == i && j2 != j)) {
					
					if(map[i2][j2] == null) {
						map[i2][j2] = new prob15bAstarPos(i2, j2, map, lines, goali, goalj);
					}
					
					ret.add(map[i2][j2]);
				}
				
				
			}
		}
		
		return ret;
	}


	@Override
	public long getCostOfMove(AstarNode nextPos) {
		
		int i2 = ((prob15bAstarPos)nextPos).i;
		int j2 = ((prob15bAstarPos)nextPos).j;
		
		int irem = i2 % lines.size();
		int jrem = j2 % lines.get(0).length();
				
		int iq = i2 / lines.size();
		int jq = j2 /  lines.get(0).length();
		
		int costOfMove = 1 + (( iq + jq + pint(lines.get(irem).charAt(jrem) + "") -1) % 9);
		
		
		return costOfMove;
	}
	
	public String toString() {
		return i + "," + j;
	}

}
