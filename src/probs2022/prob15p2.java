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

public class prob15p2 {

	
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
			

			
			int vars[][] = new int[lines.size()][4];
			for(int i=0; i<lines.size(); i++) {
				
				line = lines.get(i);
				String token[] = line.split(" ");
				vars[i][0] = pint(token[2].split("=")[1].split(":")[0].split(",")[0]);
				vars[i][1] = pint(token[3].split("=")[1].split(":")[0].split(",")[0]);
				vars[i][2] = pint(token[8].split("=")[1].split(":")[0].split(",")[0]);
				vars[i][3] = pint(token[9].split("=")[1].split(":")[0].split(",")[0]);
			}
			
			
			for(int y=0; y<=4000000; y++) {
				
				//int yLine = 11;
				
				int limits[][] = new int[lines.size()][2];
				
				for(int i=0; i<lines.size(); i++) {
					
					
					int x1 = vars[i][0];
					int y1 = vars[i][1];
					int x2 = vars[i][2];
					int y2 = vars[i][3];
					
					//up:
					int dist = Math.abs(y1 - y);
					int manhatan = Math.abs(y1 -y2) + Math.abs(x1 - x2);
					
					limits[i][0] = x1 - (manhatan - dist);
					limits[i][1] = x1 + (manhatan - dist);
					
					
				}
				
				
				for(int x=0; x<=4000000; x++) {
					
					boolean keepTrying = true;
					while(keepTrying) {
						
						keepTrying = false;
						for(int k=0; k<limits.length; k++) {
							if(x >= limits[k][0] && x <= limits[k][1]) {
								x = limits[k][1] + 1;
								keepTrying = true;

							}
						}
					}
					if(x > 4000000 ) {
						break;
					}
					
					sopl(x + "," + y);
					sopl("Solution");
					long answer = 4000000L * x + y;
					sopl("Answer: " + answer);
					
				}
				
				if(y % 100000 == 0) {
					sopl("y = " + y);
				}
			}
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
