package probs2022;
import java.io.File;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import number.IsNumber;
import utils.Mapping;
import utils.Sort;

public class prob23b {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			
			//WRONG: 3617
			//4044
			in = new Scanner(new File("in2022/prob2022in23.txt"));
			//in = new Scanner(new File("in2022/prob2022in24.txt"));
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
			
			HashSet<String> locationsPrev = new HashSet<String>();
			
			boolean current[][] = new boolean[lines.size()][lines.get(0).length()];;
			
			for(int i=0; i<current.length; i++) {
				for(int j=0; j<current[0].length; j++) {
					if(lines.get(i).charAt(j) == '.') {
						current[i][j] = false;
					} else if(lines.get(i).charAt(j) == '#') {
						current[i][j] = true;
						locationsPrev.add(i + "," + j);
					} else {
						sopl("doh1");
						exit(1);
					}
				}
			}
			
			for(int round =0; true; round++) {
				
				Iterator<String> keys = locationsPrev.iterator();
				
				int prevSize = 0;
				int hadNope = 0;
				

				HashSet<String> locationsTmp = new HashSet<String>();
				
				//Proposing:
				while(keys.hasNext()) {
					
					String nextString = keys.next();
					int i = pint(nextString.split(",")[0]);
					int j = pint(nextString.split(",")[1]);
					
					boolean foundNeighbour = false;
					
					SEARCH_NEIGH:
					for(int i2=i-1; i2<=i+1; i2++) {
						for(int j2=j-1; j2<=j+1; j2++) {
							if(i2 == i && j2 == j) {
								continue;
							}
							
							if(locationsPrev.contains(i2 + "," + j2)) {
								foundNeighbour = true;
								break SEARCH_NEIGH;
							}
							
						}
					}
					
					if(foundNeighbour == false) {
						locationsTmp.add(i + "," + j);
						//sopl("Don't move 1");
					} else {
						
						int startIndex = round % 4;
						
						int curIndex= 0;
						
						
						for(int k=0; k<2; k++) {
							
							//N
							if(curIndex >= startIndex && curIndex <= startIndex + 4) {
								//sopl("Nb4");
								if(!locationsPrev.contains((i-1) + "," + (j-1))
									&& !locationsPrev.contains((i-1) + "," + (j))
									&& !locationsPrev.contains((i-1) + "," + (j+1))) {
									//Propose N
									
									if(locationsTmp.contains((i-1) + "," + j)) {
										if(! locationsTmp.contains((i-1) + "," + j + ",nope")) {
											hadNope = 1;
										}
										locationsTmp.add((i-1) + "," + j + ",nope");
										locationsTmp.add(i + "," + j);
									} else {
										locationsTmp.add((i-1) + "," + j);
									}
									break;
								}
							}
							curIndex++;
							
							//S
							if(curIndex >= startIndex && curIndex <= startIndex + 4) {
								//sopl("Sb4");
								if(!locationsPrev.contains((i+1) + "," + (j-1))
										&& !locationsPrev.contains((i+1) + "," + (j))
										&& !locationsPrev.contains((i+1) + "," + (j+1))) {
									//Propose S
									
									if(locationsTmp.contains((i+1) + "," + j)) {
										if(! locationsTmp.contains((i+1) + "," + j + ",nope")) {
											hadNope = 1;
										}
										locationsTmp.add((i+1) + "," + j + ",nope");
										locationsTmp.add(i + "," + j);
									} else {
										locationsTmp.add((i+1) + "," + j);
									}
									break;
								}
							}
							curIndex++;
							
							//W
							if(curIndex >= startIndex && curIndex <= startIndex + 4) {
								//sopl("Wb4");
								if(!locationsPrev.contains((i-1) + "," + (j-1))
										&& !locationsPrev.contains((i) + "," + (j-1))
										&& !locationsPrev.contains((i+1) + "," + (j-1))) {
									//Propose N
									
									if(locationsTmp.contains(i + "," + (j-1))) {
										if(! locationsTmp.contains(i + "," + (j-1) + ",nope")) {
											hadNope = 1;
										}
										locationsTmp.add(i + "," + (j-1) + ",nope");
										locationsTmp.add(i + "," + j);
									} else {
										locationsTmp.add(i + "," + (j-1));
									}
									break;
								}
							}
							curIndex++;
							
							//E
							if(curIndex >= startIndex && curIndex <= startIndex + 4) {
								//sopl("Eb4");
								if(!locationsPrev.contains((i-1) + "," + (j+1))
										&& !locationsPrev.contains((i) + "," + (j+1))
										&& !locationsPrev.contains((i+1) + "," + (j+1))) {
									//Propose N
									
									if(locationsTmp.contains(i + "," + (j+1))) {
										
										if(! locationsTmp.contains(i + "," + (j+1) + ",nope")) {
											hadNope = 1;
										}
										
										locationsTmp.add(i + "," + (j+1) + ",nope");
										locationsTmp.add(i + "," + j);
									} else {
										locationsTmp.add(i + "," + (j+1));
									}
									break;
								}
							}
							curIndex++;
							
							
							if(k == 1) {
								locationsTmp.add(i + "," + j);
								break;
							}
							
						}
						
						//End proposal loop.
						
					}
					
					

					//sopl("locationTmpSize (should incr): " + locationsTmp.size());
					if(prevSize + 1 + hadNope != locationsTmp.size()) {
						sopl("DOH! Size 1" );
						exit(1);
					}
					prevSize = locationsTmp.size();
					hadNope = 0;
				}
				

				HashSet<String> locations = new HashSet<String>();
				
				//Moving:
				keys = locationsPrev.iterator();
				
				prevSize = 0;
				
				int part2NumMoved = 0;
				
				while(keys.hasNext()) {
					
					
					String nextString = keys.next();
					int i = pint(nextString.split(",")[0]);
					int j = pint(nextString.split(",")[1]);
					
					boolean foundNeighbour = false;
					
					SEARCH_NEIGH:
					for(int i2=i-1; i2<=i+1; i2++) {
						for(int j2=j-1; j2<=j+1; j2++) {
							if(i2 == i && j2 == j) {
								continue;
							}
							
							if(locationsPrev.contains(i2 + "," + j2)) {
								foundNeighbour = true;
								
								break SEARCH_NEIGH;
							}
							
						}
					}
					
					if(foundNeighbour == false) {
						locations.add(i + "," + j);
						
					} else {
						int startIndex = round % 4;
						
						int curIndex= 0;
						
						for(int k=0; k<2; k++) {
							
							//N
							if(curIndex >= startIndex && curIndex <= startIndex + 4) {
								//sopl("N");
								if(!locationsPrev.contains((i-1) + "," + (j-1))
										&& !locationsPrev.contains((i-1) + "," + (j))
										&& !locationsPrev.contains((i-1) + "," + (j+1))) {
									//Propose N
									
									if(locationsTmp.contains((i-1) + "," + j + ",nope")) {
										locations.add(i+ "," + j);
									} else {
										locations.add((i-1) + "," + j);
										part2NumMoved++;
									}
									break;
								}
							}
							curIndex++;
							
							//S
							if(curIndex >= startIndex && curIndex <= startIndex + 4) {
								//sopl("S");
								if(!locationsPrev.contains((i+1) + "," + (j-1))
										&& !locationsPrev.contains((i+1) + "," + (j))
										&& !locationsPrev.contains((i+1) + "," + (j+1))) {
									//Propose S
									
									if(locationsTmp.contains((i+1) + "," + j + ",nope")) {
										locations.add(i+ "," + j);
									} else {
										locations.add((i+1) + "," + j);
										part2NumMoved++;
									}
									break;
								}
							}
							curIndex++;
							
							//W
							if(curIndex >= startIndex && curIndex <= startIndex + 4) {
								//sopl("W");
								if(!locationsPrev.contains((i-1) + "," + (j-1))
										&& !locationsPrev.contains((i) + "," + (j-1))
										&& !locationsPrev.contains((i+1) + "," + (j-1))) {
									//Propose N
									
									if(locationsTmp.contains(i + "," + (j-1) + ",nope")) {
										locations.add(i+ "," + j);
									} else {
										locations.add(i + "," + (j-1));
										part2NumMoved++;
									}
									break;
								}
							}
							curIndex++;
							
							//E
							if(curIndex >= startIndex && curIndex <= startIndex + 4) {
								//sopl("E");
								if(!locationsPrev.contains((i-1) + "," + (j+1))
										&& !locationsPrev.contains((i) + "," + (j+1))
										&& !locationsPrev.contains((i+1) + "," + (j+1))) {
									//Propose N
									
									if(locationsTmp.contains(i + "," + (j+1) + ",nope")) {
										locations.add(i+ "," + j);
									} else {
										locations.add(i + "," + (j+1));
										part2NumMoved++;
									}
									break;
								}
							}
							curIndex++;
							

							if(k == 1) {
								locations.add(i + "," + j);
								break;
							}
						}
					}
					

					//sopl("locations Size (should incr): " + locations.size());
					if(prevSize + 1 != locations.size()) {
						sopl("DOH! Size 2" );
						exit(1);
					}
					prevSize = locations.size();
					
				}
				//End adding elves
				
			
				//sopl()
				
				//TODO: PRINT
				if(part2NumMoved == 0 || round % 100 == 0) {
					Iterator<String> keys2 = locationsPrev.iterator();
					
					int mini = -1;
					int maxi = -1;
					int minj = -1;
					int maxj= -1;
					
					boolean firstEntry = true;
					
					while(keys2.hasNext()) {
						
						String nextKey = keys2.next();
						
						int i= pint(nextKey.split(",")[0]);
						int j= pint(nextKey.split(",")[1]);
						
						if(firstEntry) {
							mini = i;
							maxi = i;
							minj = j;
							maxj = j;
							firstEntry = false;
						}
						
						if(i>maxi) {
							maxi = i;
						}
						
						if(j>maxj) {
							maxj = j;
						}
						
						if(i<mini) {
							mini = i;
						}
						if(j<minj) {
							minj = j;
						}
						
					}
					
					sopl("Round " + round + ":");
					sopl("Num elves: " + locationsPrev.size());
					for(int i=mini; i<=maxi; i++) {
						for(int j=minj; j<=maxj; j++) {
							if(locationsPrev.contains(i + "," + j)) {
								sop("#");
							} else {
								sop(".");
							}
						}
						sopl();
					}
					sopl();
				}
				
				if(part2NumMoved == 0) {
					sopl("No elves moved in round index: " + round);
					sopl("Answer part2: " + (round + 1));
					break;
				}
				
				//END TODO PRINT
				locationsPrev = locations;
			}
			
			Iterator<String> keys = locationsPrev.iterator();
			
			int mini = -1;
			int maxi = -1;
			int minj = -1;
			int maxj= -1;
			
			boolean firstEntry = true;
			
			while(keys.hasNext()) {
				
				String nextKey = keys.next();
				
				int i= pint(nextKey.split(",")[0]);
				int j= pint(nextKey.split(",")[1]);
				
				if(firstEntry) {
					mini = i;
					maxi = i;
					minj = j;
					maxj = j;
					firstEntry = false;
				}
				
				if(i>maxi) {
					maxi = i;
				}
				
				if(j>maxj) {
					maxj = j;
				}
				
				if(i<mini) {
					mini = i;
				}
				if(j<minj) {
					minj = j;
				}
				
			}
			
			long area = (maxi - mini + 1) * (maxj - minj + 1);
			
			long answer = area - locationsPrev.size();
			
			
			sopl("Answer: " + answer);
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
