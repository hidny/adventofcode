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

public class prob9b {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2022/prob2022in9.txt"));
			//in = new Scanner(new File("in2022/prob2022in10.txt"));
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
			
			//boolean draw[][] = new boolean[100][100];
			//int CENTER = draw.length / 2;
			
			//dir: 0 up
			//1 right
			//2 down
			//3 left
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				
			}

			ArrayList ints = new ArrayList<Integer>();
			
			
			HashSet<String> taken = new HashSet<String>();
			

			
			
			
			
			int ropeI[] = new int[10];
			int ropeJ[] = new int[10];
			

			taken.add(ropeI[0] + "," + ropeJ[0]);
			
			
			for(int i=0; i<lines.size(); i++) {
				
				
				line = lines.get(i);
				String token[] = line.split(" ");
				
				String dir = token[0];
				int amount = pint(token[1]);
				
				int iDir = 0;
				int jDir = 0;
				
				if(dir.equals("U")) {
					iDir = -1;
					
				} else if(dir.equals("R")) {
					jDir = 1;
					
				} else if(dir.equals("D")) {
					iDir = 1;
					
				} else if(dir.equals("L")) {
					jDir = -1;
					
				} else{
					sopl("doh");
				}
				
				for(int k=0; k<amount; k++) {
					
					ropeI[0] += iDir;
					ropeJ[0] += jDir;
					
					for(int m=1; m<ropeI.length; m++) {
						if(Math.abs(ropeI[m-1] - ropeI[m]) > 1
								|| Math.abs(ropeJ[m-1] - ropeJ[m]) > 1) {
							
							//Move:
							
							if(ropeI[m-1] > ropeI[m]) {
								ropeI[m] += 1;
							} else if(ropeI[m-1] < ropeI[m]) {
								ropeI[m] -= 1;
							}
							
							if(ropeJ[m-1] > ropeJ[m]) {
								ropeJ[m] += 1;
							} else if(ropeJ[m-1] < ropeJ[m]) {
								ropeJ[m] -= 1;
							
							}
							
							//2545
							if(m == ropeI.length - 1) {
								taken.add(ropeI[m] + "," + ropeJ[m]);
								//draw[CENTER - ropeI[m]][CENTER - ropeJ[m]] = true;
							}
						}
					}
					
				}
				
			}
			
			
			sopl("Answer: " + taken.size());
			
			/*for(int i=0; i<draw.length; i++) {
				for(int j=0; j<draw[0].length; j++) {
					if(draw[i][j]) {
						sop("#");
					} else{
						sop(".");
					}
				}
				sopl();
			}*/
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
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
