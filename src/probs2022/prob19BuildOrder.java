package probs2022;
import java.io.File;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import number.IsNumber;
import utils.Mapping;
import utils.Sort;

public class prob19BuildOrder {

	public static String robotTypes[] = new String[] {"Ore" ,"Clay", "Obsidian", "Geode"};

	public static int NUM_MINUTES = 24;
	
	public ArrayList<Integer> buildOrder = new ArrayList<Integer>();

	public int robots[];
	public int resources[];
	
	public void setupRobotsArray() {
		
		int robotList1[] = new int[4];
		for(int j=0; j<buildOrder.size(); j++) {
			robotList1[buildOrder.get(j)]++;
		}
		robots = robotList1;
	}
	
	public static void addToColliderFinder(HashMap <Integer, ArrayList<prob19BuildOrder>> colliderFinder, prob19BuildOrder buildOrder) {
		
		int indexToUse = buildOrder.getRobotsIndexNumber();
		
		if(colliderFinder.containsKey(indexToUse)) {
			colliderFinder.get(indexToUse).add(buildOrder);
		} else {
			
			ArrayList<prob19BuildOrder> newList = new ArrayList<prob19BuildOrder>();
			newList.add(buildOrder);
			colliderFinder.put(indexToUse, newList);
		}
	}
	
	public int getRobotsIndexNumber() {
		int factor = buildOrder.size() + 1;
		
		int ret = 0;
		for(int j=0; j<robots.length; j++) {
			ret = factor * ret + robots[j];
		}
		
		return ret;
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

}
