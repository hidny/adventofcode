package probs2018;

import java.io.File;

import number.IsNumber;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import utils.Mapping;

public class prob22 {

	static int LIMIT = 1000;
	
	public static int ROCKY =0;
	public static int NARROW = 1;
	public static int WET = 2;
	
	
	
	//TODO: geo index target is 0
	
	//X,Y =0 +
	//geologic index
	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2018/prob2018in22.txt"));
			
			int count = 0;
			boolean part2 = false;
			String line = "";

			LinkedList queue = new LinkedList();
			Stack stack = new Stack();
			HashSet set = new HashSet();
			
			Mapping dict = new Mapping();
			ArrayList <String>lines = new ArrayList<String>();
			
			
			boolean table[][] = new boolean[LIMIT][LIMIT];
			
			Hashtable<Long, Integer> trail = new Hashtable<Long, Integer>();
			
			//dir: 0 up
			//1 right
			//2 down
			//3 left
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				
				
			}
			
			int answer = 0;
			int y = 731;
			int x = 9;
			
			
			//7299
			
			//743
			
			//int x = 10;
			//int y = 10;
			//9,731
			for(int i=0; i<=y; i++) {
				for(int j=0; j<=x; j++) {
					int temp = type(i, j);
					if(i==y && x == j) {
						temp = ROCKY;
					}
					if(temp == WET) {
						sop("=");
						answer++;
					} else if(temp == NARROW) {
						answer = answer + 2;
						sop("|");
					}else {
						sop(".");
					}
				}
				sopl();
			}
			sopl();
			
			//7299
			sopl("Answer: " + answer);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}

	static int DEPTH = 11109;
	
	public static int type(int i, int j) {
		long temp = erosion2(i, j);
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
	
	public static long erosion2(int i, int j) {
		return (index(i,j) + DEPTH) % 20183;
		
	}
	static boolean foundIndex[][] = new boolean[LIMIT][LIMIT];
	static long ref[][] = new long[LIMIT][LIMIT];
	
	public static long index(int i, int j) {
		if(foundIndex[i][j]) {
			return ref[i][j];
		}
		if(i==0 && j ==0) {
			
			return 0;
		} else if(i==0) {
			return 16807*j;
		} else if(j==0) {
			return 48271*i;
		} else {
			foundIndex[i][j] = true;
			long temp = (erosion2(i-1, j) * erosion2(i, j-1))  %20183;
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
