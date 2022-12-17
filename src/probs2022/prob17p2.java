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

public class prob17p2 {

	//3160
	
	//1514285713988
	//1514285714288
	
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2022/prob2022in17.txt"));
			//in = new Scanner(new File("in2022/prob2022in18.txt"));
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
			for(int i=0; i<lines.size(); i++) {
				
				
				line = lines.get(i);
				String token[] = line.split(" ");
				
				
			}
			
		
			
			boolean shapes[][][] = new boolean[5][][];
			
			String a = "####";
			boolean ab[][] = new boolean[][]{{true, true, true, true}};
			
			boolean bb[][] = new boolean[][]{{false, true, false},
												{true, true, true},
												{false, true, false}
			};
			String b = ".#." + "\n" +
                       "###" + "\n" +
                       ".#.";
			
			boolean cb[][] = new boolean[][]{{false, false, true},
											{false, false, true},
											{true, true, true}
			};
			String c = "..#" + "\n" +
                       "..#" + "\n" +
                       "###";
			
			boolean db[][] = new boolean[][]{{true},{true},{true},{true}};
			
			String d = "#" + "\n" +
                       "#" + "\n" +
                       "#" + "\n" +
                       "#";
			
			boolean eb[][] = new boolean[][]{{true,true},{true,true}};
			
			String e = "##" + "\n" +
					   "##";
			
			shapes[0] = ab;
			shapes[1] = bb;
			shapes[2] = cb;
			shapes[3] = db;
			shapes[4] = eb;
			
			
			sopl("shapes length: " + shapes.length);
			sopl("wind length: " + lines.get(0).length());
			
			long lookback = shapes.length * lines.get(0).length();
			sopl(lookback);
			
	
			boolean array[][] = new boolean[1000000][7];
			
			long lookbackArray[] = new long[ array.length];
			
			
			
			int minICoord = array.length;
			
			int lineIndex = 0;
			

			int extraHeight = 0;
			
			ROCK_SEARCH:
			for(long rock=0; true; rock++) {

				if(rock % 1000000 == 0) {
					sopl(rock);
				}
				
				if(rock % lookback == 1000000000000L % lookback) {
					
					lookbackArray[(int)(rock / lookback)] = array.length - minICoord + extraHeight;
					
					
					if(rock > 6 * lookback) {

						sopl("hello in");
						for(int factor = 1; true; factor++) {
							
							
							//I somehow got 1581449275319...
							/*
							 * FACTOR USED 345
NUM_CHECKS USED = 4
Part 2 Answer:
1581449275319

Rocks fallen: 195576925
FACTOR USED 345
NUM_CHECKS USED = 10
Part 2 Answer:
1581449275319
Answer: 309294943

Quesion: why 345?

							 */
							int NUM_CHECKS = 10;
							if(rock/lookback - (NUM_CHECKS + 1) * factor < 0) {
								break;
							}

							if(rock % (factor * lookback) != 1000000000000L % (factor * lookback)) {
								continue;
							}
							
							
							
							boolean repeat = true;
							long repeatFactorOrQuotient = -1L;
							for(int j=0; j<NUM_CHECKS; j++) {
								
								if(lookbackArray[(int)((int)(rock / lookback) - j * factor)] - lookbackArray[(int)((int)(rock / lookback) - (j+1) * factor)]
										!= lookbackArray[(int)((int)(rock / lookback) - (j+1) * factor)] - lookbackArray[(int)((int)(rock / lookback) - (j+2) * factor)]) {
									
									//sopl("NOPE");
									repeat = false;
									
									break;
								}
							}
                           
							repeatFactorOrQuotient = lookbackArray[(int)(rock/lookback)] - lookbackArray[(int)(rock/lookback) - factor];
									
							if(repeat) {

								sopl();
								sopl();
								sopl("Rocks fallen: " + rock);
								sopl("FACTOR USED " + factor);
								sopl("NUM_CHECKS USED = " + NUM_CHECKS);
								
								sopl("Part 2 Answer:");
								long answer = lookbackArray[(int)(rock/lookback)] + ((1000000000000L - rock)/(factor * lookback)) * repeatFactorOrQuotient;
								
								sopl(answer);
								break ROCK_SEARCH;
							}
						}
						
					}
				}
				

				
				boolean shapeToUse[][] = shapes[((int)rock) % shapes.length];
				
				//3 from top is -4...
				int curI = minICoord - 4;
				int curJ = 2;
				
				int prevI = curI;
				int prevJ = curJ;
				
				while(true) {
					
					//Do dir:
					char dir = lines.get(0).charAt(lineIndex % lines.get(0).length());
					lineIndex++;
					
					

					prevJ = curJ;
					if(dir == '<') {
						curJ--;
					} else if(dir == '>') {
						curJ++;
					} else {
						sopl("doh");
						exit(1);
					}
					
					if(! withinSideBounds(curI, curJ, array, shapeToUse)) {
					
						curJ = prevJ;
						
					// Go left or right
					} else if(couldPutShapeOnPoint(curI, curJ, array, shapeToUse)) {
						
						prevJ = curJ;
					} else {
						//Keep going!
						curJ = prevJ;
					}
					
					prevI = curI;
					curI++;
					//Go down:
					if(couldPutShapeOnPoint(curI, curJ, array, shapeToUse)) {
						
						prevI = curI;
					} else {
						break;
					}
					
					
				}
				
				putShapeOnPoint(prevI, prevJ, array, shapeToUse);
				
				minICoord = Math.min(minICoord, prevI - shapeToUse.length + 1);
				
				int tmpHeight = array.length - minICoord;

				int AMOUNT_TO_TAKE = array.length/3;
				//int AMOUNT_TO_TAKE = 1000;
	
				if(tmpHeight > 2*AMOUNT_TO_TAKE) {
					sopl("Adjust");
					
					extraHeight += AMOUNT_TO_TAKE;
					
					for(int i=array.length - AMOUNT_TO_TAKE - 1; i>=0; i--) {
						for(int j=0; j<array[0].length; j++) {
							array[i + AMOUNT_TO_TAKE][j] = array[i][j];
							array[i][j] = false;
						}
					}
					minICoord+= AMOUNT_TO_TAKE;
					
				}
				//1514285714288
				
				/*for(int i=minICoord; i<minICoord + 20 && i<array.length; i++) {
					for(int j=0; j<array[0].length; j++) {
						if(array[i][j]) {
							sop("#");
						} else {
							sop(".");
						}
						
					}
					sopl();
				}
				sopl();
				sopl();
				sopl();
				
				if(rock == 10) {
					exit(1);
				}*/
			}
			
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public static long getArrayContent(long array[], long index) {
		return array[(int)(index % array.length)];
	}
	//i, j bottom left.
	public static boolean couldPutShapeOnPoint(int i, int j, boolean array[][], boolean shape[][]) {
		
		for(int ip=0; ip<shape.length; ip++) {
			for(int jp=0; jp<shape[0].length; jp++) {

				int iEnter = i - (shape.length - 1) + ip;
				int jEnter = j + jp;
				
				if(iEnter < 0 || iEnter >= array.length) {
					
					return false;
				}
				
				if(jEnter < 0 || jEnter >= array[0].length) {
					return false;
				}
				
				if(shape[ip][jp] && array[iEnter][jEnter]) {
					return false;
				}
			}
		}
		
		
		return true;
	}

	public static boolean putShapeOnPoint(int i, int j, boolean array[][], boolean shape[][]) {
		
		for(int ip=0; ip<shape.length; ip++) {
			for(int jp=0; jp<shape[0].length; jp++) {
				
				int iEnter = i - (shape.length - 1) + ip;
				int jEnter = j + jp;
				
				if(shape[ip][jp] && array[iEnter][jEnter] == true) {
					sopl("DOH!");
					exit(1);
				}
				
				if(shape[ip][jp]) {
					array[iEnter][jEnter] = true;
				}
			}
		}
		
		
		return true;
	}
	public static boolean withinSideBounds(int i, int j, boolean array[][], boolean shape[][]) {
		
		for(int jp=0; jp<shape[0].length; jp++) {
			
			
			int jEnter = j + jp;
			
			if(jEnter < 0 || jEnter >= array[0].length) {
				return false;
			}
				
		}
		
		
		return true;
	}
	
	
	public static int getNewLines(String shape) {
		int ret = 0;
		for(int i=0; i<shape.length(); i++) {
			if(shape.charAt(i) == '\n') {
					ret++;
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
