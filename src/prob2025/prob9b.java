package prob2025;
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

public class prob9b {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2025/prob2025in9.txt"));
			//in = new Scanner(new File("in2025/prob2025in0.txt"));
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
			boolean table342[][] = new boolean[LIMIT][LIMIT];
			
			
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
			long cur = 0;
			ArrayList ints = new ArrayList<Integer>();
			
			long coordx[] = new long[lines.size()];
			long coordy[] = new long[lines.size()];
			
			int numLeft = 0;
			int numRight = 0;
			
			int prevDir = -10;
			int curDir = -1;
			

			for(int i=0; i<lines.size(); i++) {
				
				
				line = lines.get(i);
				
				
				coordx[i] = plong(line.split(",")[0]);
				coordy[i] = plong(line.split(",")[1]);
			}
			
			for(int i=0; i<lines.size(); i++) {
				
			
				if(coordx[i] != coordx[(i + coordx.length - 1) % coordx.length]) {
					
					if(coordx[i] > coordx[(i + coordx.length - 1) % coordx.length]) {
						curDir = 1;
					} else {

						curDir = 3;
					}

					if(i > 0) {
						if((prevDir + 1) % 4 == curDir) {
							numRight++;
							sopl("r1");
						} else if((prevDir + 3) % 4 == curDir) {
							numLeft++;
							sopl("l1");

						} else {
							sopl("doh");
							exit(1);
						}
					}
					prevDir = curDir;
					
				} else if(coordy[i] != coordy[(i + coordx.length - 1) % coordx.length]){

					if(coordy[i] > coordy[(i + coordx.length - 1) % coordx.length]) {
						curDir = 2;
					} else {

						curDir = 0;
					}

					if(i > 0) {
						if((prevDir + 1) % 4 == curDir) {
							numRight++;
							sopl("r2");
						} else if((prevDir + 3) % 4 == curDir) {
							numLeft++;
							sopl("l2");
						} else {
							sopl("doh");
							exit(1);
						}
					}
					prevDir = curDir;
					
				} else {
					sopl("doh!");
					exit(1);
				}
			}
				
			sopl("Num right: " + numRight);
			sopl("Num left: " + numLeft);
			//Clockwise!
			
			
			
			for(int i=0; i<lines.size(); i++) {
				for(int j=i+1; j<lines.size(); j++) {
					
					
					
					
					//long getAreaCutOut
					
					long area = getAreaCutOut(
							(int)Math.min(coordx[i], coordx[j]),
							(int)Math.max(coordx[i], coordx[j]),
							(int)Math.min(coordy[i], coordy[j]),
							(int)Math.max(coordy[i], coordy[j]),
							true,
							coordx,
							coordy);
					
					if(area > cur) {
						cur = area;
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
	
	//public static boolean meh[][];
	
	public static long getAreaCutOut(int minX, int maxX, int minY, int maxY, boolean clockwise, long coordx[], long coordy[]) {
		
		long area = (1+Math.abs(minX - maxX)) * (1+Math.abs(minY - maxY));
		
		//meh = null;
		//meh = new boolean[1+Math.abs(minX - maxX)][(1+Math.abs(minY - maxY))];
		
		//TODO:
		//ArrayList<SetOfLines> curves = new ArrayList<SetOfLines>();
		
		
		for(int i=0; i<coordx.length; i++) {
			
			long nextX = coordx[(i + 1) % coordx.length];
			long nextY = coordy[(i + 1) % coordx.length];
			
			long x = coordx[i];
			long y = coordy[i];
			
			if(x == nextX) {
				if(x > minX && x < minY) {
					if(y <= minY && nextY<=minY) {
						//pass
					} else if(y >= maxY && nextY >= maxY) {
						//pass
					} else {
						return -1L;
						
						
					}
						
				} else {
					//pass
				}
				
			} else if(y == nextY) {
				if(y > minY && y < maxY) {
					if(x <= minX && nextX<=minX) {
						//pass
					} else if(x >= maxX && nextX >= maxX) {
						//pass
					} else {
						return -1L;
					}
					
				} else {
					//pass
				}
				
			} else {
				sopl("doh2");
				exit(1);
			}
			
		}

		sopl(area);
		//TODO: calc area based on curves...
		
		return area;
		
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
	
	public static int[][] getIntTable(ArrayList<String> lines) {
		int grid[][] = new int[lines.size()][lines.get(0).length()];
		
		for(int i=0; i<lines.size(); i++) {
			
			for(int j=0; j<grid[0].length; j++) {
				if(lines.get(i).charAt(j) == '.') {
					grid[i][j] = -1;
				} else {
					grid[i][j] = (int)(lines.get(i).charAt(j) - '0');
				}
			}
		}
		
		return grid;
	}
	

	public static char[][] getCharTable(ArrayList<String> lines) {
		char grid[][] = new char[lines.size()][lines.get(0).length()];
		
		for(int i=0; i<lines.size(); i++) {
			
			for(int j=0; j<grid[0].length; j++) {
				grid[i][j] = lines.get(i).charAt(j);

			}
		}
		
		return grid;
	}

}
