package probs2023;
import java.io.File;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

import aStar.AstarNode;
import number.IsNumber;
import utils.Mapping;
import utils.Sort;

public class prob23obj {


	public static int table[][];
	

	public static prob23obj map[][];
	
	public static void setNeighbours() {
		
		map = new prob23obj[table.length][table[0].length];
		for(int i=0; i<table.length; i++) {
			for(int j=0; j<table[0].length; j++) {
				map[i][j] = new prob23obj();
				map[i][j].i = i;
				map[i][j].j = j;
			}
		}
	}
	
	public int i;
	public int j;

	
	public boolean reachedGoal() {
		return this.i == map.length - 1 || this.j == map[0].length;
	}
	
	public ArrayList<prob23obj> getNodeNeighbours() {
		// TODO Auto-generated method stub
		
		ArrayList<prob23obj> ret = new ArrayList<prob23obj>();
		
		
		
		for(int i2=Math.max(0, i-1); i2<=Math.min(i+1, table.length - 1); i2++) {
			for(int j2=Math.max(0, j-1); j2<=Math.min(j+1, table[0].length - 1); j2++) {
			
				//sopl(i2 + "," + j2 + "," + table.length);
				if(i2 != i && j2 != j) {
					continue;
				} else if(i2 == i && j2 == j) {
					continue;
				} else if(table[i2][j2] == 1) {
					continue;
				}
				
				if(table[i2][j2] == 10 && i2 > i) {
					continue;
				} else if(table[i2][j2] == 11 && j2 < j) {
					continue;
				} else if(table[i2][j2] == 12 && i2 < i) {
					continue;
				} else if(table[i2][j2] == 13 && j2 > j) {
					continue;
				}
				
				
				if(table[i][j] == 10) {
					if(i2 < i) {
						ret.add(map[i2][j2]);
					}
				} else if(table[i][j] == 11) {
					if(j2 > i) {
						ret.add(map[i2][j2]);
						
					}
				} else if(table[i][j] == 12) {
					if(i2 > i) {
						ret.add(map[i2][j2]);
						
					}
				} else if(table[i][j] == 13) {
					if(j2 < j) {
						ret.add(map[i2][j2]);
						
					}
				} else if(table[i][j] == 1) {
					sopl("oops. Wall");
					exit(1);
				} else if(table[i][j] == 0) {
					
					ret.add(map[i2][j2]);
					
				} else {
					exit(1);
				}
			}
		}
		
		//sopl("Neighbours: " + this.i + "," + this.j + ": " + ret.size());
		return ret;
	}
	
	public long getCostOfMove(AstarNode nextPos) {
		// TODO Auto-generated method stub
		return 1;
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

}
