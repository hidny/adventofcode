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

public class prob24c {

	//day1 part 1
	//2:38.01
	
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2023/prob2023in24.txt"));
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
			int table[][] = new int[LIMIT][LIMIT];
			
			
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
			
			sopl("start");
			
			ArrayList <prob24obj> trajs = new ArrayList <prob24obj>();
			ArrayList ints = new ArrayList<Integer>();
			for(int i=0; i<lines.size(); i++) {
				
				
				line = lines.get(i);
				
				String tokens[] = line.trim().split("@");
				
				String token1[] = tokens[0].trim().split(",");

				String token2[] = tokens[1].trim().split(",");
				
				prob24obj tmp = new prob24obj();
				for(int j=0; j<token1.length; j++) {
					tmp.start[j] = plong(token1[j].trim());
				}
				for(int j=0; j<token2.length; j++) {
					tmp.vel[j] = plong(token2[j].trim());
				}
				
				trajs.add(tmp);
			}
			
			int size = 100;
			boolean viableX[][] = new boolean[2][size];
			boolean viableY[][] = new boolean[2][size];
			boolean viableZ[][] = new boolean[2][size];
			
			
			for(int i=0; i<viableX.length; i++) {
				for(int j=0; j<viableX[0].length; j++) {
					
					int curVel = j;
					
					if(i != 1) {
						curVel *= -1;
						if(curVel == 0) {
							continue;
						}
					}
					
					boolean minXFound = false;
					boolean maxXFound = false;
					long minX = 0;
					long maxX = 0;
					
					for(int k=0; k<trajs.size(); k++) {
						
						prob24obj tmp = trajs.get(k);
								
						if(curVel > tmp.vel[0]) {
							maxXFound = true;
							maxX = tmp.start[0];
						}
						
						if(curVel < tmp.vel[0]) {
							minXFound = true;
							minX = tmp.start[0];
						}
					}
					
					if(minXFound && maxXFound && minX > maxX) {
						viableX[i][j] = false;
					} else {
						viableX[i][j] = true;
						sopl("curVelX = " + curVel + " is viable");
					}
				}
			}
			sopl();
			for(int i=0; i<viableY.length; i++) {
				for(int j=0; j<viableY[0].length; j++) {
					
					int curVel = j;
					
					if(i != 1) {
						curVel *= -1;
						if(curVel == 0) {
							continue;
						}
					}
					
					boolean minYFound = false;
					boolean maxYFound = false;
					long minY = 0;
					long maxY = 0;
					
					for(int k=0; k<trajs.size(); k++) {
						
						prob24obj tmp = trajs.get(k);
								
						if(curVel > tmp.vel[1]) {
							maxYFound = true;
							maxY = tmp.start[1];
						}
						
						if(curVel < tmp.vel[1]) {
							minYFound = true;
							minY = tmp.start[1];
						}
					}
					
					if(minYFound && maxYFound && minY > maxY) {
						if(curVel == 1) {
							sopl("minY:" + minY);
							sopl("maxY:" + maxY);
						}
						viableY[i][j] = false;
					} else {
						viableY[i][j] = true;
						sopl("curVelY = " + curVel + " is viable");
					}
				}
			}
			
			
			sopl();

			for(int i=0; i<viableZ.length; i++) {
				for(int j=0; j<viableZ[0].length; j++) {
					
					int curVel = j;
					
					if(i != 1) {
						curVel *= -1;
						if(curVel == 0) {
							continue;
						}
					}
					
					boolean minZFound = false;
					boolean maxZFound = false;
					long minZ = 0;
					long maxZ = 0;
					
					for(int k=0; k<trajs.size(); k++) {
						
						prob24obj tmp = trajs.get(k);
								
						if(curVel > tmp.vel[2]) {
							maxZFound = true;
							maxZ = tmp.start[2];
						}
						
						if(curVel < tmp.vel[2]) {
							minZFound = true;
							minZ = tmp.start[2];
						}
					}
					
					if(minZFound && maxZFound && minZ > maxZ) {
						viableZ[i][j] = false;
					} else {
						viableZ[i][j] = true;
						sopl("curVelZ = " + curVel + " is viable");
					}
				}
			}
			
			sopl("Answer: " + cur);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public static String convertToPythonFormat(long matrix[][]) {
		String ret = "[";
		
		for(int i=0; i<matrix.length; i++) {
			
			ret += "[";
			
			for(int j=0; j<matrix[0].length; j++) {
				
				if(j<matrix[0].length - 1) {
					ret += " " + matrix[i][j] + ",";
				} else {
					ret += " " + matrix[i][j];
				}
			}
			
			if(i< matrix.length - 1) {
				ret += "],";
			} else {
				ret += "]";
			}
		}
		
		ret += "]";
		
		return ret;
	}
	
	
	
	public static int symbolToIndex(String symbol, ArrayList<String> labels) {
		for(int i=0; i<labels.size(); i++) {
			if(labels.get(i).equals(symbol)) {
				return i;
			}
		}
		
		return -1;
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
	
	public static long plong(String s) {
		if (IsNumber.isLong(s)) {
			return Long.parseLong(s);
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
