package probs2022;
import java.io.File;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import number.IsNumber;
import utils.Mapping;
import utils.Sort;

public class prob19Trial3 {

	public static String robotTypes[] = new String[] {"Ore" ,"Clay", "Obsidian", "Geode"};

	public static int NUM_MINUTES = 24;
	
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2022/prob2022in19.txt"));
			// in = new Scanner(new File("in2022/prob2022in20.txt"));
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
			
			//IDEA: pseudo BREADTH FIRST SEARCH
			
			
			//TODO: For now, just handle 1st blueprint
			
			//TODO:
			//in loop:

			//Debug example in prompt:
			/*
			first.buildOrder = new ArrayList<Integer>();
			first.buildOrder.add(1);
			first.buildOrder.add(1);
			first.buildOrder.add(1);
			first.buildOrder.add(2);
			first.buildOrder.add(1);
			first.buildOrder.add(2);
			first.buildOrder.add(3);
			first.buildOrder.add(3);
			getGeodeInValidBuildOrder(buildOrdersPrev.get(0).buildOrder, blue.get(0), NUM_MINUTES);
			
			sopl("DOH");
			exit(1);
			
			//0, 0, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 3, 2, 3
			*/
		
			
			
			ArrayList<Integer> buildOrderTest = convertStringToArrayList("0, 0, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 3, 2, 3, 3");
			long num = getGeodeInValidBuildOrder(buildOrderTest, blue.get(5), NUM_MINUTES);
			
			//TODO: what happens to this solution when I comment out critical comment?
			// guess: it becomes invalid, but there should be a valid one to replace it.
	
			//Before uncommenting:
			/*Answer for blueprint 6: 10
Exit with code 1
(/
			
			sopl("Answer for blueprint 6: " + num);
			
			
			exit(1);
			
			//End Debug
			
			
			int quality = 0;

			for(int blueIndex = 0; blueIndex<blue.size(); blueIndex++) {

				sopl();
				sopl();
				sopl();
				sopl();
				sopl();
				sopl("Blue print number: " + (blueIndex + 1));
				ArrayList<prob19BuildOrder> buildOrdersPrev = new ArrayList<prob19BuildOrder>();
				
				
				prob19BuildOrder first = new prob19BuildOrder();
				first.buildOrder = new ArrayList<Integer>();
				
				buildOrdersPrev.add(first);
				
				
				int maxGeode = 0;
				ArrayList<Integer> curBestArray = new ArrayList<Integer> ();
				
				for(int depth=0; buildOrdersPrev.size() > 0; depth++) {
					
					sopl();
					sopl("Depth: " + depth);
					sopl("Num build orders " + buildOrdersPrev.size());
					sopl("Current max geode: " + maxGeode);
					
					ArrayList<prob19BuildOrder> buildOrdersAtDepth = new ArrayList<prob19BuildOrder>();

					//HashMap <Integer, ArrayList<prob19BuildOrder>> colliderFinder = new  HashMap <Integer, ArrayList<prob19BuildOrder>>();
					
					for(int i=0; i<buildOrdersPrev.size(); i++) {
						
						for(int j=0; j<robotTypes.length; j++) {
							//Try to add robot type at end of build order:
							
							//Setup unsafe:
							buildOrdersPrev.get(i).buildOrder.add(j);
							
							if(couldAddRobotToBuildOrder(buildOrdersPrev.get(i).buildOrder, blue.get(blueIndex), NUM_MINUTES)) {
								int numGeode = getGeodeInValidBuildOrder(buildOrdersPrev.get(i).buildOrder, blue.get(blueIndex), NUM_MINUTES);

								
								if(numGeode > maxGeode) {
									maxGeode = numGeode;
									
									curBestArray = new ArrayList<Integer> ();
									
									for(int m=0; m<buildOrdersPrev.get(i).buildOrder.size(); m++) {
										
										curBestArray.add(buildOrdersPrev.get(i).buildOrder.get(m));
									}
								}
								
								
								prob19BuildOrder newBuild = new prob19BuildOrder();
								
								//Araylist is inefficient compared to BigInt, but whatever!
								ArrayList<Integer> buildArray = new ArrayList<Integer>();
								buildArray.addAll(buildOrdersPrev.get(i).buildOrder);
								
								newBuild.buildOrder = buildArray;
								
								newBuild.resources = getResourcesInValidBuildOrder(buildOrdersPrev.get(i).buildOrder, blue.get(blueIndex), NUM_MINUTES, false);
								newBuild.setupRobotsArray();
								
								//TODO: hold on.
								buildOrdersAtDepth.add(newBuild);
								//onlyAddIfNoDominatedAndRemoveDominated(newBuild, colliderFinder);
								
							} else {
								//sopl("Invalid build order!");
							}
							

							//Tear down unsafe:
							buildOrdersPrev.get(i).buildOrder.remove(buildOrdersPrev.get(i).buildOrder.size() - 1);
							
						}
						
					}
					
					/*Iterator<ArrayList<prob19BuildOrder>> values = colliderFinder.values().iterator();
					
					buildOrdersPrev = new ArrayList<prob19BuildOrder>();
					
					while(values.hasNext()) {
						buildOrdersPrev.addAll(values.next());
					}
					*/
					buildOrdersPrev = buildOrdersAtDepth;
					
				} //END DEPTH for loop
			

				sopl();
				sopl("END LOOP");
				sopl("Num build orders " + buildOrdersPrev.size());
				sopl("Current max geode: " + maxGeode);
				
				sopl("Cur best array:");
				for(int i=0; i<curBestArray.size(); i++) {
					sop(curBestArray.get(i) + ", ");
				}
				sopl();
				
				quality += maxGeode * (blueIndex + 1);
			} //END Bluepring for loop

			//For each possible countinuation of build order
			//could exec buildorder
			//(have it be that each minute ore has to be bought first.)
			
			
			//exec buildorder and get results for each minute
			
			//Optional?
				//	check if result is "dominated" at some minute. If so, eliminate
			
			//display final build order with the minutes attached (like in starcraft!)
			
			//Keep track of buildorder that makes the most geodo at minute 24....
			
			//once build length 20, we probably have an answer.
			
			sopl("Answer: " + quality);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	

	public static ArrayList<Integer> convertStringToArrayList(String sequence) {
		String token[] = sequence.split(",");
		ArrayList<Integer> ret = new ArrayList<Integer>();
		
		for(int i=0; i<token.length; i++) {
			ret.add(pint(token[i].trim()));
		}
		
		return ret;
		
	}
	
	public static void onlyAddIfNoDominatedAndRemoveDominated(prob19BuildOrder newBuild, HashMap <Integer, ArrayList<prob19BuildOrder>> colliderFinder) {
		
		boolean removedOne = false;
		
		int newBuildRobotIndex = newBuild.getRobotsIndexNumber();
		
		if(colliderFinder.containsKey(newBuildRobotIndex)) {

			ArrayList<prob19BuildOrder> relevantBuildOrders = colliderFinder.get(newBuildRobotIndex);
		
			NEXT_COMPARE:
			for(int i=0; i<relevantBuildOrders.size(); i++) {
				
				prob19BuildOrder buildInList = relevantBuildOrders.get(i);
				
	
				if(buildInList.buildOrder.size() != newBuild.buildOrder.size()) {
					sopl("DOH! sizes");
					exit(1);
				}
				
				for(int j=0; j<newBuild.robots.length; j++) {
					if(newBuild.robots[j] != buildInList.robots[j]) {
						sopl("OOPS relevant build orders!");
						exit(1);
						continue NEXT_COMPARE;
					}
				}
				
				boolean newIsBiggerOrSame = true;
				boolean listIsBiggerOrSame = true;
	
				for(int j=0; j<newBuild.resources.length; j++) {
					if(newBuild.resources[j] > buildInList.resources[j]) {
						listIsBiggerOrSame = false;
					} else if(newBuild.resources[j] < buildInList.resources[j]) {
						newIsBiggerOrSame = false;
					}
					
				}
				
				if(listIsBiggerOrSame) {
					if(removedOne) {
						sopl("DOH! Removed one even though we got dominated!");
						exit(1);
					}
					return;
				} else if(newIsBiggerOrSame) {
					relevantBuildOrders.remove(i);
					i--;
					removedOne = true;
					
				
					
				} else {
					//Do nothing...
				}
				
			}
		}
		
		prob19BuildOrder.addToColliderFinder(colliderFinder, newBuild);
		
	}
	
	public static boolean couldAddRobotToBuildOrder(ArrayList<Integer> buildOrder, prob19Blue blue, int numMinutes) {
		
		if( getResourcesInValidBuildOrder(buildOrder, blue, numMinutes, true)[0] == -1) {
			return false;
		} else {
			return true;
		}
	}

	public static int getGeodeInValidBuildOrder(ArrayList<Integer> buildOrder, prob19Blue blue, int numMinutes) {
		return getResourcesInValidBuildOrder(buildOrder, blue, numMinutes, false)[3];
	}

	public static int[] getResourcesInValidBuildOrder(ArrayList<Integer> buildOrder, prob19Blue blue, int numMinutes, boolean cutShortForValidity) {
		
		int resources[] = new int[4];
		
		int curPurchaseIndex = 0;
		
		int lastPurchaseMinute = -1;
		int lastRobotIndex = -1;
		
		
		int robots[] = new int[4];
		int robotsPrevMinute[] = new int[4];

		robots[0] = 1;
		robotsPrevMinute[0] = 1;
		
		int min = 1;
		
		int resourcesPrevMinute[] = new int[4];
		
		NEXT_MIN:
		for(; min<=numMinutes && (!cutShortForValidity || curPurchaseIndex < buildOrder.size()); min++) {

			for(int j=0; j<resources.length; j++) {
				resourcesPrevMinute[j] = resources[j];
				
			}
			for(int j=0; j<resources.length; j++) {
				resources[j] += robotsPrevMinute[j];
			}
			
			for(int j=0; j<resources.length; j++) {
				robotsPrevMinute[j] = robots[j];
			}
			
			//loop to buy robots
			
			while(curPurchaseIndex < buildOrder.size()) {

				int robotToBuy = buildOrder.get(curPurchaseIndex);
			
				if(robotToBuy == 0) {
					if(resourcesPrevMinute[0] >= blue.oreOre) {
						//sopl("Buy ore earlier");
						//could've bought it ealier!
						return new int[]  {-1};
						
					} else if(resources[0] >= blue.oreOre) {
						//Buy it
						
						resources[0] -= blue.oreOre;
	
						resourcesPrevMinute[0] -= blue.oreOre;
						
					} else {
						continue NEXT_MIN;
					}
				} else if(robotToBuy == 1) {

					if(resourcesPrevMinute[0] >= blue.clayOre) {
						//sopl("Buy ore earlier 2");
						//could've bought it ealier!
						return new int[]  {-1};
						
					} else if(resources[0] >= blue.clayOre) {
						//Buy it
						
						resources[0] -= blue.clayOre;

						resourcesPrevMinute[0] -= blue.clayOre;
						
						
					} else {

						continue NEXT_MIN;
					}
				} else if(robotToBuy == 2) {
					
					/*if(resourcesPrevMinute[0] >= blue.obsOre && resourcesPrevMinute[1] >= blue.obsClay) {
						//sopl("Buy ore earlier 3");
						//DOESN:T work for some reason!
						//could've bought it ealier!
						return new int[]  {-1};
						
					} else */if(resources[0] >= blue.obsOre && resources[1] >= blue.obsClay) {
						//Buy it
						
						resources[0] -= blue.obsOre;
						resources[1] -= blue.obsClay;

						resourcesPrevMinute[0] -= blue.obsOre;
						resourcesPrevMinute[1] -= blue.obsClay;
						
					} else {

						continue NEXT_MIN;
					}
				} else if(robotToBuy == 3) {
					
					if(resourcesPrevMinute[0] >= blue.geodeOre && resourcesPrevMinute[2] >= blue.geodeObs) {
						//could've bought it ealier!
						//This logically shouldn't happen though:
						//sopl("Buy ore earlier 4");
						
						return new int[]  {-1};
						
					} else if(resources[0] >= blue.geodeOre && resources[2] >= blue.geodeObs) {
						//Buy it
						
						resources[0] -= blue.geodeOre;
						resources[2] -= blue.geodeObs;
						

						resourcesPrevMinute[0] -= blue.geodeOre;
						resourcesPrevMinute[2] -= blue.geodeObs;
						
						
					} else {

						
						continue NEXT_MIN;
					}
				} else {
					sopl("OOPS!");
					exit(1);
				}
				
				//Note:
				//At this point, the purchase happened!

				robots[robotToBuy]++;
				/*if(robotToBuy == 0) {
					sopl("Bought ore at minute " + (min + 1));
				} else if(robotToBuy == 1) {
					sopl("Bought clay at minute " + (min + 1));
				} else if(robotToBuy == 2) {
					sopl("Bought obs at minute " + (min + 1));
				} else if(robotToBuy == 3) {
					sopl("Bought geode at minute " + (min + 1));
				}
				*/
				int ORE_TO_GEODE_TIME = 8;
				
				//int ORE_TO_GEODE_TIME = 0;
				
				int timeLeft = numMinutes - min;
				if(lastPurchaseMinute == min && robotToBuy < lastRobotIndex) {
					//Enforce an arbitrary ordering:
					return new int[]  {-1};

				
				} else if(timeLeft <=1) {
					//Last minute purchase doesn't count
					return new int[]  {-1};
					
				} else if(robotToBuy != 3 && timeLeft <= 3) {
					//Last 2nd minute purchase of non-geode doesn't count
					return new int[]  {-1};
					
					
				} else if(robotToBuy == 0 && numMinutes - min <= 1 + blue.oreOre + ORE_TO_GEODE_TIME) {
					//Don't buy a ore robot if it's just going to pay for itself.
					return new int[]  {-1};
					
				}
					
				lastPurchaseMinute = min;
				lastRobotIndex = robotToBuy;
				
				curPurchaseIndex++;
				
				
				break;
			
			}
			
			if(numMinutes - min >= 1 + blue.oreOre && curPurchaseIndex == buildOrder.size()) {
				//Just buy ore at the end.
				while(true) {
					int robotToBuy = 0;
					
					if(robotToBuy == 0) {
						if(resources[0] >= blue.oreOre) {
							//Buy it
							
							resources[0] -= blue.oreOre;
							
						} else {
							continue NEXT_MIN;
						}
					}

					robots[robotToBuy]++;
					
					
				}
				
			}
			//end loop.
		}
		
		if(min < numMinutes || curPurchaseIndex < buildOrder.size()) {
			//sopl("It was cut short!");
			//Maybe it was invalid too
		} else {
			//Debug:
			/*
			sopl("build:");
			for(int i=0; i<buildOrder.size(); i++) {
				sopl(buildOrder.get(i));
			}
			
			sopl("Result in resources:");
			for(int i=0; i<resources.length; i++) {
				sopl(resources[i]);
			}
			sopl("Result in robots:");
			for(int i=0; i<robots.length; i++) {
				sopl(robots[i]);
			}
			
				sopl();
				sopl();
				sopl();
				sopl();
			*/
		}
		
		if(curPurchaseIndex == buildOrder.size()) {
			return resources;
		} else {
			return new int[]  {-1};
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
