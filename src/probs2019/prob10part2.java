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
import utilsPE.GCD;

public class prob10part2 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2019/prob2019in10.txt"));
			 //in = new Scanner(new File("in2019/prob2019in10.txt.test"));
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
			
			int NUM_ASTEROID_TO_DESTROYED = 200;
			int numAsteroidDestroyed = 0;
			
			//dir: 0 up
			//1 right
			//2 down
			//3 left
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				
				
			}
			
			int landingI = -1;
			int landingJ = -1;
			
			for(int i1=0; i1<lines.size(); i1++) {
				for(int j1=0; j1<lines.get(0).length(); j1++) {
					
					if(lines.get(i1).charAt(j1) == '#') {
						int curNum = 0;
						
						for(int i=0; i<lines.size(); i++) {
							OUT:
							for(int j=0; j<lines.get(0).length(); j++) {
								
								
								if(i==i1 && j==j1) {
									//Don't shoot current asteroid.
									continue;
								}
								
								if(lines.get(i).charAt(j) == '#') {
									curNum++;
									
									for(int k=1; k<Math.abs(utilsPE.GCD.getGCD(i - i1, j-j1)); k++) {
										if(lines.get(i1 + 
												(int)((k*(i-i1))/Math.abs(utilsPE.GCD.getGCD(i - i1, j-j1))))
												.charAt(j1 + 
														(int)((k*(j-j1))/Math.abs(utilsPE.GCD.getGCD(i - i1, j-j1)))) == '#') {
											//sopl("cover");
											curNum--;
											continue OUT;
											//.charAt(j1 + (int)(k*(j-j1)/Math.abs(utilsPE.GCD.getGCD(i - i1, j-j1)) == '#') {
												
										}
									}
									
									if(i != i1 && j == j1) {
										for(int k=1; k<Math.abs(i - i1); k++) {
											
											if(lines.get(i1 + k *(i-i1)/Math.abs(i - i1))
													.charAt(j1) == '#') {
												//sopl("cover |");
												curNum--;
												continue OUT;
												//.charAt(j1 + (int)(k*(j-j1)/Math.abs(utilsPE.GCD.getGCD(i - i1, j-j1)) == '#') {
													
											}
										}
									}
									
									if(i == i1 && j != j1) {
										for(int k=1; k<Math.abs(j - j1); k++) {
											
											if(lines.get(i1)
													.charAt(j1 + k *(j-j1)/Math.abs(j - j1)) == '#') {
												//sopl("cover --");
												curNum--;
												continue OUT;
												//.charAt(j1 + (int)(k*(j-j1)/Math.abs(utilsPE.GCD.getGCD(i - i1, j-j1)) == '#') {
													
											}
										}
									}
								}
							}
						}
						
						if(curNum > count) {
							sopl(i1 + ", "+ j1);
							count = curNum;
							landingI = i1;
							landingJ = j1;
						}
					}
				}
			}
			
			
			
			sopl("Answer: " + (count));
			sopl("Answer: " + getCountSingleRotation(lines, landingI, landingJ));
			
			lines.set(landingI, lines.get(landingI).substring(0, landingJ) + "O" + lines.get(landingI).substring(landingJ+1));
			
			while(numAsteroidDestroyed + getCountSingleRotation(lines, landingI, landingJ) < NUM_ASTEROID_TO_DESTROYED) {
				numAsteroidDestroyed += getCountSingleRotation(lines, landingI, landingJ);
				
				//destroy whole rotation:
				lines = destorySingleRotation(lines, landingI, landingJ);
				sopl("destorySingleRotation");
			}
			
			sopl();
			for(int i=0; i<lines.size(); i++) {
				for(int j=0; j<lines.get(i).length(); j++) {
					sop(lines.get(i).charAt(j));
				}
				sopl();
			}
			sopl();
			
			Object ret[] = orderAsteroidsToDestroy(lines, landingI, landingJ);
			
			for(int i=0; i<ret.length; i++) {
				sopl(ret[i]);
			}
			

			//-1 because it's 0 based...
			int indexAns = NUM_ASTEROID_TO_DESTROYED - numAsteroidDestroyed - 1;
			
			sopl("Answer: " + ret[indexAns]);
			
			int x = landingJ + ((prob10part2Obj)ret[indexAns]).x;
			int y = landingI - ((prob10part2Obj)ret[indexAns]).y;
			
			sopl(100*x + y);
			//TODO order destruction..
			
			//TODO: graph the thing....
			//1810 wrong
			
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public static int getCountSingleRotation(ArrayList <String>lines, int i1, int j1) {
		
		int curNum = 0;
		
		for(int i=0; i<lines.size(); i++) {
			OUT:
			for(int j=0; j<lines.get(0).length(); j++) {
				
				
				if(i==i1 && j==j1) {
					//Don't shoot current asteroid.
					continue;
				}
				
				if(lines.get(i).charAt(j) == '#') {
					curNum++;
					
					for(int k=1; k<Math.abs(utilsPE.GCD.getGCD(i - i1, j-j1)); k++) {
						if(lines.get(i1 + 
								(int)((k*(i-i1))/Math.abs(utilsPE.GCD.getGCD(i - i1, j-j1))))
								.charAt(j1 + 
										(int)((k*(j-j1))/Math.abs(utilsPE.GCD.getGCD(i - i1, j-j1)))) == '#') {
							//sopl("cover");
							curNum--;
							continue OUT;
							//.charAt(j1 + (int)(k*(j-j1)/Math.abs(utilsPE.GCD.getGCD(i - i1, j-j1)) == '#') {
								
						}
					}
					
					if(i != i1 && j == j1) {
						for(int k=1; k<Math.abs(i - i1); k++) {
							
							if(lines.get(i1 + k *(i-i1)/Math.abs(i - i1))
									.charAt(j1) == '#') {
								//sopl("cover |");
								curNum--;
								continue OUT;
								//.charAt(j1 + (int)(k*(j-j1)/Math.abs(utilsPE.GCD.getGCD(i - i1, j-j1)) == '#') {
									
							}
						}
					}
					
					if(i == i1 && j != j1) {
						for(int k=1; k<Math.abs(j - j1); k++) {
							
							if(lines.get(i1)
									.charAt(j1 + k *(j-j1)/Math.abs(j - j1)) == '#') {
								//sopl("cover --");
								curNum--;
								continue OUT;
								//.charAt(j1 + (int)(k*(j-j1)/Math.abs(utilsPE.GCD.getGCD(i - i1, j-j1)) == '#') {
									
							}
						}
					}
				}
			}
		}
		return curNum;
	}

	public static ArrayList<String> destorySingleRotation(ArrayList <String>lines, int i1, int j1) {
		
		
		for(int i=0; i<lines.size(); i++) {
			OUT:
			for(int j=0; j<lines.get(0).length(); j++) {
				
				
				if(i==i1 && j==j1) {
					//Don't shoot current asteroid.
					continue;
				}
				
				if(lines.get(i).charAt(j) == '#') {
					
					for(int k=1; k<Math.abs(utilsPE.GCD.getGCD(i - i1, j-j1)); k++) {
						if(lines.get(i1 + 
								(int)((k*(i-i1))/Math.abs(utilsPE.GCD.getGCD(i - i1, j-j1))))
								.charAt(j1 + 
										(int)((k*(j-j1))/Math.abs(utilsPE.GCD.getGCD(i - i1, j-j1)))) == '#') {
							//sopl("cover");
							continue OUT;
							//.charAt(j1 + (int)(k*(j-j1)/Math.abs(utilsPE.GCD.getGCD(i - i1, j-j1)) == '#') {
								
						}
					}
					
					if(i != i1 && j == j1) {
						for(int k=1; k<Math.abs(i - i1); k++) {
							
							if(lines.get(i1 + k *(i-i1)/Math.abs(i - i1))
									.charAt(j1) == '#') {
								//sopl("cover |");
								continue OUT;
								//.charAt(j1 + (int)(k*(j-j1)/Math.abs(utilsPE.GCD.getGCD(i - i1, j-j1)) == '#') {
									
							}
						}
					}
					
					if(i == i1 && j != j1) {
						for(int k=1; k<Math.abs(j - j1); k++) {
							
							if(lines.get(i1)
									.charAt(j1 + k *(j-j1)/Math.abs(j - j1)) == '#') {
								//sopl("cover --");
								continue OUT;
								//.charAt(j1 + (int)(k*(j-j1)/Math.abs(utilsPE.GCD.getGCD(i - i1, j-j1)) == '#') {
									
							}
						}
					}
					
					//AT THIS POINT: KILL IT:
					
					lines.set(i, lines.get(i).substring(0, j) + "X" + lines.get(i).substring(j+1));
				}
			}
		}
		
		
		return lines;
	}
	
	
public static Object[] orderAsteroidsToDestroy(ArrayList <String>lines, int i1, int j1) {
		

		ArrayList<Comparable> asteroidToDestroy = new ArrayList<Comparable>();
		
		for(int i=0; i<lines.size(); i++) {
			OUT:
			for(int j=0; j<lines.get(0).length(); j++) {
				
				
				if(i==i1 && j==j1) {
					//Don't shoot current asteroid.
					continue;
				}
				
				if(lines.get(i).charAt(j) == '#') {
					
					for(int k=1; k<Math.abs(utilsPE.GCD.getGCD(i - i1, j-j1)); k++) {
						if(lines.get(i1 + 
								(int)((k*(i-i1))/Math.abs(utilsPE.GCD.getGCD(i - i1, j-j1))))
								.charAt(j1 + 
										(int)((k*(j-j1))/Math.abs(utilsPE.GCD.getGCD(i - i1, j-j1)))) == '#') {
							//sopl("cover");
							continue OUT;
							//.charAt(j1 + (int)(k*(j-j1)/Math.abs(utilsPE.GCD.getGCD(i - i1, j-j1)) == '#') {
								
						}
					}
					
					if(i != i1 && j == j1) {
						for(int k=1; k<Math.abs(i - i1); k++) {
							
							if(lines.get(i1 + k *(i-i1)/Math.abs(i - i1))
									.charAt(j1) == '#') {
								//sopl("cover |");
								continue OUT;
								//.charAt(j1 + (int)(k*(j-j1)/Math.abs(utilsPE.GCD.getGCD(i - i1, j-j1)) == '#') {
									
							}
						}
					}
					
					if(i == i1 && j != j1) {
						for(int k=1; k<Math.abs(j - j1); k++) {
							
							if(lines.get(i1)
									.charAt(j1 + k *(j-j1)/Math.abs(j - j1)) == '#') {
								//sopl("cover --");
								continue OUT;
								//.charAt(j1 + (int)(k*(j-j1)/Math.abs(utilsPE.GCD.getGCD(i - i1, j-j1)) == '#') {
									
							}
						}
					}
					
					//AT THIS POINT: KILL IT:
					
					//sopl("New: " + new prob10part2Obj(i1, j1, i, j));
					asteroidToDestroy.add(new prob10part2Obj(i1, j1, i, j));
					
				}
			}
		}
		
		sopl(asteroidToDestroy.size());
		
		
		Object ret[] = utils.Sort.sortList(asteroidToDestroy);
		
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
