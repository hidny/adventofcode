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

public class prob17 {

	
	public static int ymax=-100000;
	public static int ymin=10000;;
	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2018/prob2018in17.txt"));
			
			int count = 0;
			boolean part2 = false;
			String line = "";

			LinkedList queue = new LinkedList();
			Stack stack = new Stack();
			HashSet set = new HashSet();
			
			Mapping dict = new Mapping();
			ArrayList <String>lines = new ArrayList<String>();
			
			
			int LIMIT = 20000;
			int table[][] = new int[LIMIT][LIMIT];
			
			int miny=10000;
			int maxy = 0;
			
			
			Hashtable<Long, Integer> trail = new Hashtable<Long, Integer>();
			
			//dir: 0 up
			//1 right
			//2 down
			//3 left
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				
				String arguments[] = line.split(", ");
				
				boolean horizontal = false;
				if(arguments[0].charAt(0) == 'x') {
					horizontal = false;
				} else {
					horizontal = true;
				}
				
				int arg1 = pint(arguments[0].split("=")[1]);
				
				String tmp = arguments[1].split("=")[1];
				String tmp2[] = arguments[1].split("=")[1].split("\\.");
				int arg2Start = pint(tmp.split("\\.")[0]);
				int arg2End = pint(arguments[1].split("=")[1].split("\\.")[2]);
				
				//1 = clay
				for(int t=arg2Start; t<=arg2End; t++) {
					if(horizontal) {
						table[arg1][t] = CLAY;
						if(arg1 > ymax) {
							ymax = t;
						}
						
						if(arg1 < ymin) {
							ymin = t;
						}
					} else {
						table[t][arg1] = CLAY;
						if(t > ymax) {
							ymax = t;
						}
						
						if(t < ymin) {
							ymin = t;
						}
					}
				}
				
			}

			int xstart = 500;
			int ystart = 0;
			drainWater(table, xstart, ystart);
			
			int answer = getAnswer(table);
			
			printStatusBig(table);
			sopl("Answer: " + answer);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public static int CLAY = 1;
	
	public static int FALLING = 2;
	public static int WATER = 3;
	
	public static int SPACE = 0;
	
	public static Scanner input = new Scanner(System.in);
	
	public static void printStatus(int table[][]) {
		printStatus(table, true);
	}
	
	public static void printStatus(int table[][], boolean pause) {
	/*	for(int i=0; i<30; i++) {
			for(int j=450; j<550; j++) {
				if(table[i][j] == SPACE) {
					sop(".");
				} else if(table[i][j] == CLAY) {
					sop("#");
				} else if(table[i][j] == WATER) {
					sop("~");
				} else if(table[i][j] == FALLING) {
					sop("|");
				}
			}
			sopl();
		}
		sopl();
		sopl();
		if(pause) {
			//input.next();
		}*/
	}
	
	
	public static void printStatusBig(int table[][]) {
		for(int i=ymin; i<=ymax; i++) {
				for(int j=0; j<2000; j++) {
					if(table[i][j] == SPACE) {
						sop(".");
					} else if(table[i][j] == CLAY) {
						sop("#");
					} else if(table[i][j] == WATER) {
						sop("~");
					} else if(table[i][j] == FALLING) {
						sop("|");
					}
				}
				sopl();
			}
			sopl();
			sopl();
			
		}
	
	public static int getAnswer(int table[][]) {
		int answer = 0;
		for(int i=ymin; i<=ymax; i++) {
			for(int j=0; j<table.length; j++) {
				if(i ==0 && j==500) {
					sopl("oops");
					//dont count source
					continue;
				}
				if(table[i][j] == WATER) {
					answer++;
				} else if(table[i][j] == FALLING) {
					//answer++;
				}
			}
		}
		return answer;
	}
	
	public static void drainWater(int table[][], int xstart, int ystart) {
	
		//go down:
		//int ystart = 0;
		//int xstart = 500;
		
		printStatus(table, false);
		
		for(int ynext = ystart; ynext<=ymax; ynext++) {
			printStatus(table, false);
			//sopl("hello");
			
			if(table[ynext][xstart] == SPACE || table[ynext][xstart] == FALLING) {
				table[ynext][xstart] = FALLING;

				printStatus(table, false);
				
				//TODO: print state?
				
			} else if(table[ynext][xstart] == CLAY || table[ynext][xstart] == WATER) {
				//see if we could fill it
				
				//hit clay... go up one
				ynext--;
				
				boolean rowIsFalling = false;
				
				//go to the right:
				for(int xright = xstart+1; table[ynext][xright] != CLAY; xright++) {
					
					if(table[ynext+1][xright] != CLAY && table[ynext+1][xright]!=WATER) {
						//Row is falling
						rowIsFalling = true;
						break;
					}
				}
				if(rowIsFalling == false) {
					for(int xleft = xstart-1; table[ynext][xleft] != CLAY; xleft--) {
						
						
						if(table[ynext+1][xleft] != CLAY && table[ynext+1][xleft]!=WATER) {
							//Row is falling
							rowIsFalling = true;
							break;
						}
					}
				}
				
				int fallingXLeft = -1;
				int fallingXRight = -1;
				
				if(rowIsFalling) {
					//fall to right:
					int xright = xstart+1;
					
					for(; table[ynext][xright] != CLAY; xright++) {

						if(table[ynext+1][xright] != CLAY && table[ynext+1][xright]!=WATER) {
							fallingXRight = xright;
							//Row is falling
							break;
						}
						
						table[ynext][xright] = FALLING;
						
					}
					
					
					//Fall to the left:
					int xleft = xstart-1;
					for(; table[ynext][xleft] != CLAY; xleft--) {
	

						if(table[ynext+1][xleft] != CLAY && table[ynext+1][xleft]!=WATER) {
							fallingXLeft = xleft;
							//Row is falling
							break;
						}
						
						table[ynext][xleft] = FALLING;
						
					}
					//TODO: let water fall to left and right... be recursive...
				} else {
					
					//TODO: copy paste code...
					//fill to right:
					for(int xright = xstart+1; table[ynext][xright] != CLAY; xright++) {
						
						table[ynext][xright] = WATER;
						
						
						if(table[ynext+1][xright] != CLAY && table[ynext+1][xright]!=WATER) {
							//Row is falling
							break;
						}
					}
					
					//Fill to the left:
					for(int xleft = xstart-1; table[ynext][xleft] != CLAY; xleft--) {
						
						table[ynext][xleft] = WATER;
						
						if(table[ynext+1][xleft] != CLAY && table[ynext+1][xleft]!=WATER) {
							//Row is falling
							break;
						}
					}
					
					//Fill middle:
					if(table[ynext][xstart] != CLAY) {
						table[ynext][xstart] = WATER;
					} else {
						sopl("Unexpected clay");
						exit(1);
					}
				}
				

				printStatus(table);
				
				if( rowIsFalling == false) {
					ynext -= 2;
				} else {
					if(fallingXLeft != -1) {
						drainWater(table, fallingXLeft, ynext);
					}
					
					if(fallingXRight != -1){
						drainWater(table, fallingXRight, ynext);
					}
					
					break;
					//TODO: recursive
				}
			
			}
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
	
	//TOO HIGH:
	//38453
}
