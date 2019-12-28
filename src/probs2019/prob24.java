package probs2019;
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

public class prob24 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			// in = new Scanner(new File("in2019/prob2019in24.txt.test"));
			 in = new Scanner(new File("in2019/prob2019in24.txt"));
			int numTimes = 0;
			 
			int count = 0;
			boolean part2 = false;
			String line = "";

			HashSet<Long> set = new HashSet<Long>();
			
			
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
			
			boolean map[][] = new boolean[lines.size()][lines.get(0).length()];
			for(int i=0; i<map.length; i++) {
				for(int j=0; j<map[i].length; j++) {
					if(lines.get(i).charAt(j) == '#') {
						map[i][j] = true;
					} else {
						map[i][j] = false;
					}
				}
			}
			
			boolean currentMap[][];
			while(set.contains(getDiversity(map)) == false) {
				set.add(getDiversity(map));
				
				currentMap = new boolean[lines.size()][lines.get(0).length()];
				
				for(int i=0; i<currentMap.length; i++) {
					for(int j=0; j<currentMap[0].length; j++) {
						int numNeighbours = getNumNeighbours(map, i, j);
						
						if(map[i][j] == true && numNeighbours != 1) {
							//A bug dies (becoming an empty space) unless there is exactly one bug adjacent to it.
							currentMap[i][j] = false;

						} else if(map[i][j] == false 
								&& (numNeighbours == 1 || numNeighbours == 2)) {
							//An empty space becomes infested with a bug if exactly one or two bugs are adjacent to it.
							currentMap[i][j] = true;
						} else {
							currentMap[i][j] = map[i][j];
						}
					}
				}
				
				
				for(int i=0; i<map.length; i++) {
					for(int j=0; j<map[0].length; j++) {
						if(map[i][j]) {
							sop("*");
						} else {
							sop(".");
						}
					}
					sopl();
				}
				sopl();
				sopl("========");
				map = currentMap;
			}
			
			for(int i=0; i<map.length; i++) {
				for(int j=0; j<map[0].length; j++) {
					if(map[i][j]) {
						sop("*");
					} else {
						sop(".");
					}
				}
				sopl();
			}
			sopl();
			sopl("========");
			
			sopl("Answer: " + getDiversity(map));
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	public static int getNumNeighbours(boolean map[][], int i, int j) {
		
		int numNeighbours = 0;
		for(int i2=Math.max(0, i-1); i2<=Math.min(map.length - 1, i+1); i2++) {
			for(int j2=Math.max(0, j-1); j2<=Math.min(map[0].length - 1, j+1); j2++) {
				
				if((i2 != i && j2 == j) || (i2 == i && j2 != j)) {
					//At this point i2, j2 is one of 4 adjacent tiles:
					if(map[i2][j2]) {
						numNeighbours++;
					}
					
				}
			}
		}
		
		return numNeighbours;
	}
	
	public static long getDiversity(boolean map[][]) {
		long ret = 0L;
		for(int i=map.length -1; i>=0; i--) {
			for(int j=map[0].length-1; j>=0; j-- ) {
				
				if(map[i][j]) {
					ret = 2*ret + 1;
				} else {
					ret = 2*ret + 0;
				}
			}
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
	
	public static void exit() {
		exit(0);
	}
	public static void exit(int code) {
		sop("Exit with code " + code);
		
		System.exit(code);
	}

}
