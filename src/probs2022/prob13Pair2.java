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

public class prob13Pair2 {

	public boolean isList = false;
	
	public long num = -1;
	
	ArrayList <prob13Pair2>list = null;
	
	
	public static ArrayList<prob13Pair2> parseParquet(String paquet) {
		
		sopl("PARSE");
		curIndex = 0;
		
		return parseParquet(paquet, 0);
		
	}
	
	public static int curIndex = 0;

	public static ArrayList<prob13Pair2> parseParquet(String paquet, int depth) {
		
		ArrayList<prob13Pair2> cur = new ArrayList<prob13Pair2>();
		
		
		
		if(paquet.charAt(curIndex) == '[') {
			
		} else {
			sopl("oops2");
		}
		
		for(int i =curIndex+1; i<paquet.length(); i++) {
			//sopl(i);
			if(paquet.charAt(i) == '[') {
				curIndex = i;
				cur.add(new prob13Pair2(true, -1, parseParquet(paquet, depth)));
				i = curIndex;
				

			} else if(paquet.charAt(i) == ']') {
				
				curIndex = i;
				return cur;
			} else if(paquet.charAt(i) >= '0' && paquet.charAt(i) <= '9') {
				
				long num = (int)(paquet.charAt(i) - '0');
				while(i + 1 < paquet.length() && paquet.charAt(i+1) >= '0' && paquet.charAt(i+1) <= '9') {
					
					i++;
					num = 10* num + (long)(paquet.charAt(i) - '0');
				}
				
				cur.add(new prob13Pair2(false, num, null));
				
				
			} else if(paquet.charAt(i) == ',') {
				//pass
				
			} else if(paquet.charAt(i) == ' ') {
				//pass
			} else {
				sopl("What: " + paquet.charAt(i));
			}
		}
		return cur;
		
	}
	

	public prob13Pair2() {

	}
	
	public prob13Pair2(boolean isList, long num, ArrayList<prob13Pair2> list) {
		super();
		this.isList = isList;
		this.num = num;
		this.list = list;
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
