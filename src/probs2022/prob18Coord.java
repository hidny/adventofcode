package probs2022;
import java.io.File;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import aStar.AstarAlgo;
import aStar.AstarNode;
import number.IsNumber;
import utils.Mapping;
import utils.Sort;

public class prob18Coord implements AstarNode{

	public int x;
	public int y;
	public int z;

	public prob18Coord(int x, int y, int z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
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
	
	
	public static void setupMap(HashSet<Integer> coordHash) {
		map = new prob18Coord[100][100][100];
		
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[0].length; j++) {
				for(int k=0; k<map[0][0].length; k++) {
					map[i][j][k] = new prob18Coord(i, j, k);
				}
			}
		}
		
		there[0][0][0] = true;
		walls = coordHash;
	}
	
	public static HashSet<Integer> walls = new HashSet<Integer>();
			
	public static prob18Coord map[][][] = new prob18Coord[100][100][100];
	
	public static boolean there[][][] = new boolean[100][100][100];
	@Override
	public long getAdmissibleHeuristic(AstarNode goal) {
		// TODO Auto-generated method stub
		
		if(there[this.x][this.y][this.z]) {
			//sopl("hello " + this.z + "");
			return AstarAlgo.GOAL_FOUND;
		}
		
		return this.x + this.y + this.z;
	}
	
	@Override
	public ArrayList<AstarNode> getNodeNeighbours() {
		// TODO Auto-generated method stub
		ArrayList<AstarNode> ret = new  ArrayList<AstarNode>();

		if( this.z > 0 && ! walls.contains(getWallNum(this.x, this.y, this.z-1)) ) {
			ret.add(map[this.x][this.y][this.z-1]);
		}

		if( this.y > 0 && ! walls.contains(getWallNum(this.x, this.y-1, this.z)) ) {
			ret.add(map[this.x][this.y-1][this.z]);
		}
		
		
		if( this.x > 0 && ! walls.contains(getWallNum(this.x-1, this.y, this.z)) ) {
			ret.add(map[this.x-1][this.y][this.z]);
		}
		
		if( this.x < map.length -1 && ! walls.contains(getWallNum(this.x+1, this.y, this.z)) ) {
			ret.add(map[this.x+1][this.y][this.z]);
		}
		
		if( this.y < map[0].length -1 && ! walls.contains(getWallNum(this.x, this.y+1, this.z)) ) {
			ret.add(map[this.x][this.y+1][this.z]);
		}
		
		if( this.z < map[0][0].length -1 && ! walls.contains(getWallNum(this.x, this.y, this.z+1)) ) {
			ret.add(map[this.x][this.y][this.z+1]);
		}
		
		
		return ret;
	}
	@Override
	public long getCostOfMove(AstarNode nextPos) {
		// TODO Auto-generated method stub
		return 1;
	}

	
	public static int getWallNum(int i, int j, int k) {
		return i*10000 + j *100 + k;
	}
}
