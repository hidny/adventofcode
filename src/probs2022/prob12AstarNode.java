package probs2022;
import java.io.File;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import number.IsNumber;
import utils.Mapping;
import utils.Sort;
import aStar.*;

public class prob12AstarNode implements AstarNode{

	

	public prob12AstarNode(int i, int j, int elevation) {
		super();
		this.i = i;
		this.j = j;
		this.elevation = elevation;
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
	
	
	public int i;
	public int j;
	public int elevation;
	
	@Override
	public long getAdmissibleHeuristic(AstarNode goal) {
		return Math.abs(((prob12AstarNode)goal).i - this.i) 
				+ Math.abs(((prob12AstarNode)goal).j - this.j);
	}
	
	public static prob12AstarNode map[][];
	@Override
	public ArrayList<AstarNode> getNodeNeighbours() {
		// TODO Auto-generated method stub
		ArrayList<AstarNode> ret = new ArrayList<AstarNode>();
		
		
		if(i > 0 && (map[this.i - 1][this.j].elevation - elevation) <= 1) {
			ret.add(map[this.i - 1][this.j]);
		}
		
		if(j < map[0].length -1 && (map[this.i][this.j + 1].elevation - elevation) <= 1) {
			ret.add(map[this.i][this.j + 1]);
		}
		if(i < map.length -1 && (map[this.i + 1][this.j].elevation - elevation) <= 1) {
			ret.add(map[this.i + 1][this.j]);
		}
		if(j> 0 && (map[this.i][this.j - 1].elevation - elevation) <= 1) {
			ret.add(map[this.i][this.j -1]);
		}
		
		return ret;
	}
	@Override
	public long getCostOfMove(AstarNode nextPos) {
		// TODO Auto-generated method stub
		return 1;
	}

}
