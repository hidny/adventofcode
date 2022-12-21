package probs2022;
import java.io.File;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import aStar.AstarAlgo;
import aStar.AstarNode;
import number.IsNumber;
import utils.Mapping;
import utils.Sort;

public class prob18b {

	//I noticed that the input only goes up to 100, and took advantage.
	// I tried to go fast by not making the constant and explaining it.
	
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2022/prob2022in18.txt"));
			// in = new Scanner(new File("in2022/prob2022in19.txt"));
			int numTimes = 0;
			 
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

			HashSet<Integer> coordHash = new HashSet<Integer>();
			ArrayList<prob18Coord> coords = new ArrayList<prob18Coord>();
			for(int i=0; i<lines.size(); i++) {
				
				
				line = lines.get(i);
				String token[] = line.split(",");
				
				coords.add(new prob18Coord(pint(token[0]), pint(token[1]), pint(token[2])));
				
				coordHash.add(pint(token[0]) * 10000 + pint(token[1]) * 100 + pint(token[2]));
				
			}
			
			int count = 6 * lines.size();
			
			
			for(int i=0; i<lines.size(); i++) {
				for(int j=i+1; j<lines.size(); j++) {
					
					prob18Coord a = coords.get(i);
					prob18Coord b = coords.get(j);
					
					if(a.x == b.x && a.y == b.y && Math.abs(a.z - b.z) == 1) {
						count-=2;
					} else if(a.x == b.x && a.z == b.z && Math.abs(a.y - b.y) == 1) {
						count-=2;
					} else if(a.z == b.z && a.y == b.y && Math.abs(a.x - b.x) == 1) {
						count-=2;
					}
				}
			}
			
			prob18Coord.setupMap(coordHash);
			
			sopl("Part 1 answer: " + count);
			
			for(int i=0; i<100; i++) {
				//sopl(i);
				for(int j=0; j<100; j++) {
					//sopl(j);
					for(int k=0; k<100; k++) {
						
						if(coordHash.contains(prob18Coord.getWallNum(i, j, k))) {
							continue;
						}
						
						ArrayList<AstarNode> ret = AstarAlgo.astar(prob18Coord.map[i][j][k], null);
						
						
						if(ret == null) {
							sopl("pocket at " + i + ", " + j + ", " + k);
							
							for(int m=0; m<lines.size(); m++) {
								prob18Coord a = coords.get(m);
								
								if(a.x == i && a.y == j && Math.abs(a.z - k) == 1) {
									count--;
								}
								if(a.x == i && a.z == k && Math.abs(a.y - j) == 1) {
									count--;
								}
								if(a.z == k && a.y == j && Math.abs(a.x - i) == 1) {
									count--;
								}
							}
							
							sopl("Current count: " + count);
						} else {

							//sopl("Size: " + ret.size());
							prob18Coord.there[i][j][k] = true;
						}
					}
				}
			}
			
			
			sopl("Answer: " + count);
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
