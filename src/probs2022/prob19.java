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

public class prob19 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 //in = new Scanner(new File("in2022/prob2022in19.txt"));
			 in = new Scanner(new File("in2022/prob2022in20.txt"));
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

			ArrayList<prob19Blue> blue = new ArrayList<prob19Blue>();
			ArrayList ints = new ArrayList<Integer>();
			for(int i=0; i<lines.size(); i++) {
				
				
				line = lines.get(i);
				String token[] = line.split(" ");
				
				
				prob19Blue cur = new prob19Blue(pint(token[6]),
						pint(token[12]),
						pint(token[18]),
						pint(token[21]),
						pint(token[27]),
						pint(token[30])
						);
			
				blue.add(cur);
			}
			int bestIndex = -1;
			for(int i=0; i<blue.size(); i++) {
			

				int maxAmount = 0;
				int curArray[] = new int[4];
				curArray[0] = 1;
				
				int curResources[] = new int[4];
				
				
				//exit(0);
				
				//Only need 2 separators!
				int numSep = 2;
				int numExtra = 12;
				boolean combo[] = new boolean[numSep + numExtra];
				for(int j=0; j<numSep; j++) {
					combo[j] = true;
				}
				
				 
				int debug = 0;
				while(combo != null) {
					
					int maxArray[] = new int[3];
					int curIndex = 0;
					
					for(int j=0; j<maxArray.length; j++) {
						maxArray[j] = 1;
					}
					
					for(int j=0; curIndex < maxArray.length && j<combo.length; j++) {
						if(combo[j] == false) {
							maxArray[curIndex]++;
						} else {
							curIndex++;
						}
					}

					int curMax= getMaxLimit(blue.get(i), curArray, curResources, NUM_MINUTES, maxArray);
					
					
					
					
					if(curMax > maxAmount) {
						maxAmount = curMax;
						sopl("Max for current array below: " + curMax);
						sopl("Max for current array below: " + maxAmount);
						for(int j=0; j<maxArray.length; j++) {
							sopl(maxArray[j]);
						}
						sopl();
					}
					
					
					
					debug++;
					sopl("Num combos tried: " + debug);
					combo = utilsPE.Combination.getNextCombination(combo);
				}
				
				curMaxGeode = -1;
				//maxAmount= getMaxLimit(blue.get(i), curArray, curResources, NUM_MINUTES, new int[]{ 10, 10, 10});
				sopl("Max amount found 222:");
				sopl(maxAmount);
				
			}
			
			//count = maxAmount * (bestIndex + 1);
			
			//sopl("Answer: " + count);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	

	public static int curMaxGeode = -1;
	public static int getMaxLimit(prob19Blue cur, int curArray[], int curResources[], int minLeft, int maxArray[]) {
		
		if(minLeft == 0) {
			/*sopl("minLeft is 0");
			if(curResources[3] > 0) {
				sopl(curResources[3]);
			}*/
			return curResources[3];
		}
		
		
		//TODO: fix and uncomment:
		/*int minite = (NUM_MINUTES - minLeft + 1);
		if(minite < 5) {
			sopl("Minute " + minite + ":");
			for(int i=0; i<curArray.length; i++) {
				sopl(curArray[i]);
			}
			sopl();
			for(int i=0; i<curArray.length; i++) {
				sopl(curResources[i]);
			}
			sopl();
			sopl();
		}
		
		
		//Calc max geode:
		long maxOre = curResources[0] + curArray[0] * minLeft;
		
		//TODO: have a max ore cut off machine number
		//TODO: 1st buy ore machines, 2nd clay, 3rd ob, 4th geode
		
		//TODO: no limit geode!
		// when clay starts.
		for(int i=minLeft; i>0; i--) {
			
			
			if(minLeft > cur.oreOre + 1) {
				long numNew = maxOre /cur.oreOre;
				maxOre += (minLeft - (cur.oreOre + 1)) * (maxOre/cur.oreOre);
			}
			
				
		}
		
		//todo: HAVE MAX AND IDEA
		//todo: MAX CLAY is higher because future clay machines will make more clay...
		long maxClay = curResources[1] + curArray[1]*minLeft + maxOre / cur.clayOre;
		
		long maxObs = curResources[2] + curArray[2]*minLeft + Math.min(maxOre/cur.obsOre, maxClay/cur.obsClay);
		//TODO: there's a bug!
		long maxGeode = curResources[3] + curArray[3]*minLeft + Math.min(maxOre/cur.geodeOre, maxObs/cur.geodeObs) + 1;//cheat +1
		
		
		if(curMaxGeode >= maxGeode) {
			//sopl("cutoff: " + curMaxGeode + "vs" + maxGeode);
			//Cut off
			return curMaxGeode;
		
		}
		*/
		//Buy stuff... recursively
		
		//TODO: buy to max ore machines, then buy to max clay machines, then buy to max clay machines
		
		int curMax = -1;
		
		for(int i=0; i<=curResources[0]/cur.oreOre; i++) {
			
			int tmpOre = curResources[0] - i * cur.oreOre;
			
			if(curArray[0] + i > maxArray[0]) {
				break;
			}
			
			//TODO: limit the # ore machine to some number from combo
			
			for(int j=0; j<=tmpOre/cur.clayOre; j++) {
				int tmpOre2 = tmpOre - j * cur.clayOre;

				if(curArray[1] + j > maxArray[1]) {
					break;
				}

				//TODO: limit the # clay machine to some number from combo
				
				
				for(int k=0; k<=tmpOre2/cur.obsOre && k<=curResources[1]/cur.obsClay; k++) {
					int tmpOre3 = tmpOre2 - k * cur.obsOre;
					int tmpClay = curResources[1] - k  * cur.obsClay;
					
					if(curArray[2] + k > maxArray[2]) {
						break;
					}

					//TODO: limit the # obs machine to some number from combo
					
					//Always buy max geodeObs:
					int m = Math.min(tmpOre3/cur.obsOre, curResources[2]/cur.geodeObs);
					
					int tmpOre4 = tmpOre3 - m * cur.geodeOre;
					int tmpObs = curResources[2] - m  * cur.geodeObs;
					
					
					if(tmpOre4 < 0 || tmpClay < 0 || tmpObs <0 ) {
						sopl("Doh!");
						exit(1);
					}
					
					int newArray[] = new int[4];
					newArray[0] = curArray[0] + i;
					newArray[1] = curArray[1] + j;
					newArray[2] = curArray[2] + k;
					newArray[3] = curArray[3] + m;
					
					int newResources[] = new int[4];
					newResources[0] = tmpOre4;
					newResources[1] = tmpClay;
					newResources[2] = tmpObs;
					newResources[3] = curResources[3];
					

					for(int n=0; n<curArray.length; n++) {
						newResources[n] += curArray[n];
					}
					
					int trial =  -1;
					if(newArray[1] == 0 && newResources[0] >= Math.max(cur.oreOre, cur.clayOre)) {
						//No! Have to spend!
					
					/*
					} else if(newResources[0] >= Math.max(Math.max(cur.oreOre, cur.clayOre), Math.max(cur.geodeOre, cur.obsOre)) 
							&& newResources[1] >= cur.obsClay
							&& newResources[2] >= cur.geodeObs) {
						//Need to spend!
						//TODO: always spend of geodeObs if in doubt!
						
					} else if(newResources[1] >= 3 * cur.obsClay) {
						//Spend clay asap? TODO
						
					} else if(newResources[2] >= 3 * cur.geodeObs) {
						//Spend obs asap? TODO
				*/
					} else if(newArray[0] > maxArray[0]) {
						//hint
						
					} else if(newArray[1] > maxArray[1]) {
						//hint
						
					} else if(newArray[2] > maxArray[2]) {
						//hint
					} else {
					
						trial = getMaxLimit(cur, newArray, newResources, minLeft - 1, maxArray);
					}
					if(trial > curMax) {
						curMax = trial;
						
					}
					if(trial > curMaxGeode) {
						curMaxGeode = trial;
						sopl("New max: " + trial);
					}
					
				
					
				}
			}
			
			
		}
		
		
		return curMax;
	}
	
	public static int NUM_MINUTES = 24;
	
	public static int getMaxCheat(prob19Blue cur, int curArray[], int curResources[], int minLeft) {
		
		if(minLeft == 0) {
			/*sopl("minLeft is 0");
			if(curResources[3] > 0) {
				sopl(curResources[3]);
			}*/
			return curResources[3];
		}
		
		

		int minite = (NUM_MINUTES - minLeft + 1);
		/*if(minite < 5) {
			sopl("Minute " + minite + ":");
			for(int i=0; i<curArray.length; i++) {
				sopl(curArray[i]);
			}
			sopl();
			for(int i=0; i<curArray.length; i++) {
				sopl(curResources[i]);
			}
			sopl();
			sopl();
		}*/
		
		//Buy stuff... recursively
		
		int curMax = -1;
		
		for(int i=0; i<=curResources[0]/cur.oreOre; i++) {
			
			int tmpOre = curResources[0] - i * cur.oreOre;
			
			for(int j=0; j<=tmpOre/cur.clayOre; j++) {
				int tmpOre2 = tmpOre - j * cur.clayOre;
				
				for(int k=0; k<=tmpOre2/cur.obsOre && k<=curResources[1]/cur.obsClay; k++) {
					int tmpOre3 = tmpOre2 - k * cur.obsOre;
					int tmpClay = curResources[1] - k  * cur.obsClay;
					
					for(int m=0; m<=tmpOre3/cur.obsOre && m<=curResources[2]/cur.geodeObs; m++) {
						int tmpOre4 = tmpOre3 - m * cur.geodeOre;
						int tmpObs = curResources[2] - m  * cur.geodeObs;
						
						
						if(tmpOre4 < 0 || tmpClay < 0 || tmpObs <0 ) {
							sopl("Doh!");
							exit(1);
						}
						
						int newArray[] = new int[4];
						newArray[0] = curArray[0] + i;
						newArray[1] = curArray[1] + j;
						newArray[2] = curArray[2] + k;
						newArray[3] = curArray[3] + m;
						
						int newResources[] = new int[4];
						newResources[0] = tmpOre4;
						newResources[1] = tmpClay;
						newResources[2] = tmpObs;
						newResources[3] = curResources[3];
						

						for(int n=0; n<curArray.length; n++) {
							newResources[n] += curArray[n];
						}
						
						int trial =  -1;
						/*if(newArray[2] == 0 && newResources[0] >= Math.max(cur.oreOre, cur.clayOre)&& minLeft >5) {
							//No! Have to spend!
	
						} else if(newResources[0] >= Math.max(Math.max(cur.oreOre, cur.clayOre), Math.max(cur.geodeOre, cur.obsOre)) 
								&& newResources[1] >= cur.obsClay
								&& newResources[2] >= cur.geodeObs
								&& minLeft >5) {
							//Need to spend!
							
						} elseif(newResources[1] >= cur.obsClay) {
							//Spend clay asap?
							
						} else if(newResources[2] >= cur.geodeObs) {
							//Spend obs asap?
					
						} else 
						*/if(newArray[0] > 1) {
							//hint
							
						} else if(newArray[1] > 4) {
							//hint
							
						} else if(newArray[2] > 2) {
							//hint
						} else if(newArray[3] > 2) {
							//hint
						
						} else {
						
							trial = getMaxCheat(cur, newArray, newResources, minLeft - 1);
						}
						if(trial > curMax) {
							curMax = trial;
						}
						
					}
					
				}
			}
			
			
		}
		
		
		return curMax;
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
