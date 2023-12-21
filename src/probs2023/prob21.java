package probs2023;
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

public class prob21 {

	//day1 part 1
	//2:38.01
	
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2023/prob2023in21.txt"));
			//in = new Scanner(new File("in2023/prob2023in0.txt"));
			int numTimes = 0;
			 
			int count = 0;
			boolean part2 = false;
			String line = "";

			LinkedList queue = new LinkedList();
			Stack stack = new Stack();
			HashSet set = new HashSet();
			
			
			Hashtable<Long, Integer> trail = new Hashtable<Long, Integer>();
			
			ArrayList <String>lines = new ArrayList<String>();
			
			
			int LIMIT = 20000;
			
			
			//dir: 0 up
			//1 right
			//2 down
			//3 left
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				
			}

			int most = 0;
			int most2 = 0;
			int most3 = 0;
			long cur = 0L;
			ArrayList ints = new ArrayList<Integer>();
			for(int i=0; i<lines.size(); i++) {
				
				
				line = lines.get(i);
				
			}
			boolean table[][] = new boolean[lines.size()][lines.get(0).length()];
			boolean soFar[][] = new boolean[lines.size()][lines.get(0).length()];
			
			int startI = -1;
			int startJ = -1;
			
			for(int i=0; i<table.length; i++) {
				for(int j=0; j<table[0].length; j++) {
					
					soFar[i][j] = false;
					
					if(lines.get(i).charAt(j) == '#') {
						table[i][j] = true;
					} else if(lines.get(i).charAt(j) == 'S') {
						
						startI = i;
						startJ = j;
					} else {
						table[i][j] = false;
					}
					
				}
			}

			soFar[startI][startJ] = true;
			
			//4223
			cur = getAnswer(table, soFar, 0, 64);
			
			
			sopl("Answer: " + cur);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}

	
	public static long getAnswer(boolean table[][], boolean soFar[][], int depth, int maxDepth) {
		
		
		for(int d=0; d<maxDepth; d++) {
			
			boolean newList[][] = new boolean[table.length][table[0].length];
			
			for(int i=0; i<table.length; i++) {
				for(int j=0; j<table[0].length; j++) {
					
					if(soFar[i][j]) {
						for(int i2=Math.max(0, i-1); i2<=i+1 && i2<table.length; i2++) {
							for(int j2=Math.max(0, j-1); j2<=j+1 && j2 <table[0].length; j2++) {
								
								if((i2 != i && j2 != j) || (i2 == i && j2 == j)) {
									continue;
								}
								
								if(table[i2][j2] == false) {
									newList[i2][j2] = true;
								}
							}
						}
					}
				}
			}
			
			soFar = newList;
		}
		
		long ret = 0L;
		for(int i=0; i<table.length; i++) {
			for(int j=0; j<table[0].length; j++) {
				if(soFar[i][j]) {
					ret++;
					sop("O");
				} else if(table[i][j]) {
					sop("#");
				} else {
					sop(".");
				}
			}
			sopl();
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
