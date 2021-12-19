package probs2021;
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

public class prob19Point {

	
	public ArrayList<String> distRelAbs = new  ArrayList<String>();
	
	public String point;
	
	public prob19Point(String point) {
		this.point = point;
	}
	
	public void addDist(String dist) {
		distRelAbs.add(dist);
	}
	
	public int minDistFromEdge() {
		String coord2[] =point.split(",");
		
		int ret = 1000;
		
		for(int i=0; i<coord2.length; i++) {
			
			int cur = 1000 - Math.abs(pint(coord2[i]));
			
			if(cur < ret) {
				ret = cur;
			}
		}
		
		return ret;
	}
	
	public static boolean couldBeSamePoint(prob19Point a, prob19Point b) {
		
		
		//Didn't use these vars...
		//I think these these used properly 
		//could've disproven a few pairs of points, but I got lazy...
		//int distFromEdge = a.minDistFromEdge();
		//int distFromEdge2 = b.minDistFromEdge();
		//int maxDist = Math.max(distFromEdge, distFromEdge2);
		
		int numHits = 0;
		
		for(int i=0; i<a.distRelAbs.size(); i++) {
			
				
			for(int j=0; j<b.distRelAbs.size(); j++) {
					
				if(a.distRelAbs.get(i).equals(b.distRelAbs.get(j))) {
					numHits++;
				}
			}
			
		}
		
		// 5 is significant right???
		//Solution that worked had 3...
		if(numHits > 5) {
			//sopl(a.point + " is probably " + b.point);
			return true;
		}
		
		return false;
	}
	

	public static int pint(String s) {
		if (IsNumber.isNumber(s)) {
			return Integer.parseInt(s);
		} else {
			sop("Error: (" + s + " is not a number");
			return -1;
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
}
