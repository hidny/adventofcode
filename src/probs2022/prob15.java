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

public class prob15 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2022/prob2022in15.txt"));
			// in = new Scanner(new File("in2022/prob2022in16.txt"));
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
			boolean table[][] = new boolean[LIMIT][LIMIT];
			
			
			//dir: 0 up
			//1 right
			//2 down
			//3 left
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				
			}

			ArrayList ints = new ArrayList<Integer>();
			

			boolean neverMind[] = new boolean[100000000];
			boolean taken[] = new boolean[100000000];
			int MID = taken.length / 2;
			
			int yLine = 2000000;
			//int yLine = 11;
			
			for(int i=0; i<lines.size(); i++) {
				
				sopl();
				sopl("Line: " + i);
				
				line = lines.get(i);
				sopl(line);
				
				String token[] = line.split(" ");
				
				int x1 = pint(token[2].split("=")[1].split(":")[0].split(",")[0]);
				int y1 = pint(token[3].split("=")[1].split(":")[0].split(",")[0]);
				int x2 = pint(token[8].split("=")[1].split(":")[0].split(",")[0]);
				int y2 = pint(token[9].split("=")[1].split(":")[0].split(",")[0]);
				
				//up:
				int dist = Math.abs(y1 - yLine);
				int manhatan = Math.abs(y1 -y2) + Math.abs(x1 - x2);
				
				sopl("dist" + dist);
				sopl("man" + manhatan);
				for(int t=0; t<=manhatan - dist; t++) {
					taken[MID + x1 + t] = true;
					taken[MID + x1 - t] = true;
					//sopl(x1 + t);
					//sopl(x1 - t);
					//sopl("---");
				}
				
				
				if(y2 == yLine) {
					//Evil edge case:
					neverMind[MID + x2] = true;
				}
			}
			
			int takenIndex = -1;
			//3930808
			
			sopl();
			for(int i=0; i<taken.length; i++) {
				if(neverMind[i] == false && taken[i]) {
					takenIndex = i;
					count++;
				}
				
				if(i - MID > -5 && i - MID < 27){
					if(taken[i]) {
						sop("#");
					} else {
						sop(".");
					}
				}
			}
			sopl();
			//wrong: 5809295
			//wrong: 5139103
			
			sopl("Answer: " + count);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	//#############
	
	//##############
	
	//####B#########
	//#############
	

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
