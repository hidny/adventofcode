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

import probs2019after1am.*;

public class prob20part2 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			String filename = "in2019/prob2019in20.txt";
			//String filename = "in2019/prob2019in20.txt.testpart2";
			//String filename = "in2019/prob2019in20.txt";
			in = new Scanner(new File(filename));
			 
			 
			sopl(filename);
			
			String line = "";

			ArrayList <String>lines = new ArrayList<String>();
			
			
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				sopl(line);
				
			}
			
			origMap = new char[lines.size()][lines.get(0).length()];
			explored = new boolean[lines.size()][lines.get(0).length()][100000];
			
			for(int i=0; i<origMap.length; i++) {
				for(int j=0; j<origMap[0].length; j++) {
					origMap[i][j] = lines.get(i).charAt(j);

					for(int k=0; k<explored[i][j].length; k++) {
						explored[i][j][k] = false;
					}
				}
			}
			
			//TODO: breadth first search
			LinkedList<prob20objpart2> queue = new LinkedList<prob20objpart2>();
			
			int startLocation[] = prob20.getOtherWarpLocation(origMap, 'A', 'A', -1, -1);
			int endLocation[] = prob20.getOtherWarpLocation(origMap, 'Z', 'Z', -1, -1);
			
			int answer = -1;
			
			dist.put(startLocation[0] +"," + startLocation[1] + "," + 0, 0);
			explored[startLocation[0]][startLocation[1]][0] = true;
			
			queue.add(new prob20objpart2(startLocation[0], startLocation[1], 0, dist.get(startLocation[0] +"," + startLocation[1] + "," + 0)));

			LinkedList<prob20objpart2> deleteQueue = new LinkedList<prob20objpart2>();
			int oldMaxDist = 0;
			
			while(queue.size() > 0) {
				prob20objpart2 cur = queue.getFirst();
				queue.remove();

				deleteQueue.add(cur);
				if(cur.dist > oldMaxDist) {
					oldMaxDist = cur.dist;
					
					while(deleteQueue.getFirst().dist <= cur.dist - 2) {
						prob20objpart2 tmp = deleteQueue.getFirst();
						
						if(dist.containsKey(tmp.i + "," + tmp.j + "," + tmp.k) == false) {
							sopl("ERROR deleting hash indexes");
							exit(1);
						}
						dist.remove(tmp.i + "," + tmp.j + "," + tmp.k);
						
						deleteQueue.remove();
						
					}
				}
				
				 ArrayList<prob20objpart2> neighbours = getNeighbours(cur.i, cur.j, cur.k);
				 
				 for(int i=0; i<neighbours.size(); i++) {
					 queue.add(neighbours.get(i));
				 }
				
				 if(dist.get(endLocation[0] +"," + endLocation[1] + "," + 0) != null) {
					 answer = dist.get(endLocation[0] +"," + endLocation[1] + "," + 0);
					 break;
				 }
			}
			
			
			//PRINT DEBUG
			for(int k=0; k<Math.min(2000, debugMaxK + 2); k++) {
				sopl("LEVEL " + k);
				for(int i=0; i<origMap.length; i++) {
					for(int j=0; j<origMap[i].length; j++) {
						if(dist.get(i +"," + j + "," + k) != null) {
							String ret = "" + dist.get(i +"," + j + "," + k)%10;
							sop(ret);
						} else if(explored[i][j][k]) {
							sop("x");
						} else {
							sop(origMap[i][j]);
						}
					}
					sopl();
				}
	
				sopl();
				sopl();
			}
			
			sopl("Max dist: " + oldMaxDist);
			sopl("Max level:: " + debugMaxK);
			sopl("Answer: " + answer);
			
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	
	
	public static char origMap[][];
	
	public static Hashtable<String, Integer> dist = new Hashtable<String, Integer>();
	public static boolean explored[][][];
	
	public static char EMPTY = '.';
	public static char WALL = '#';
	
	public static int debugMaxK = 0;
	
	public static int OUTSIDE_BORDER = 5;
	
	public static ArrayList<prob20objpart2> getNeighbours(int i, int j, int k) {
		
		//if(k>= 1) {
		//	sopl("DEBUG L" + k);
		//}
		
		ArrayList<prob20objpart2> ret = new ArrayList<prob20objpart2>();
		
		for(int i2=Math.max(i-1, 0); i2<=Math.min(i+1, origMap.length - 1); i2++) {
			for(int j2=Math.max(j-1, 0); j2<=Math.min(j+1, origMap[i2].length - 1); j2++) {
				
				
				if(explored[i2][j2][k]) {
					continue;
				}
				
				
				if(i2 == i || j2==j) {
					//At this point (i2, j2) is not (i, j) and might be worth exploring:

					if(origMap[i2][j2] == EMPTY) {
						
						//If (i2, j2) is an EMPTY space, explore it:
						ret.add(new prob20objpart2(i2, j2, k, dist.get(i +"," + j + "," + k)  + 1));
						
						dist.put(i2 +"," + j2 + "," + k, dist.get(i +"," + j + "," + k)  + 1);
						explored[i2][j2][k] = true;
						
					} else if(origMap[i2][j2] >= 'A' && origMap[i2][j2] <= 'Z') {
						

						//If (i2, j2) is a WARP location WARP to the correct other location...
						//or don't warp if it's AA or ZZ.
						
						for(int i3=Math.max(i2-1, 0); i3<=Math.min(i2+1, origMap.length - 1); i3++) {
							for(int j3=Math.max(j2-1, 0); j3<=Math.min(j2+1, origMap[i3].length - 1); j3++) {
								
								if(i3 == i2 && j3 == j2) {
									continue;
								}
								
								if((i3 == i2 || j3==j2)) {
									
									
									//sopl(i2 +"," + j2 +": " + i3 +"," + j3);
									if(origMap[i3][j3] >= 'A' && origMap[i3][j3] <= 'Z') {

										//At this point (i2, j2) and (i3, j3) are the 2 label locations,
										//and we'll find the other label location to warp to and warp there
										
										//sopl(origMap[i3][j3]);
										int warp[] = null;
										if(i2 < i3 || j2 < j3) {
											//Find the other warp location with label (i2, j2) then (i3, j3)
											warp = prob20.getOtherWarpLocation(origMap, origMap[i2][j2], origMap[i3][j3], i, j);
										} else {
											//Find the other warp location with labels reversed (i3, j3) then (i2, j2) 
											warp = prob20.getOtherWarpLocation(origMap, origMap[i3][j3], origMap[i2][j2], i, j);
										}
										
										if(warp != null) {
											
											int newK = k;
											
											
											if( isWarpingOutsideBorder(origMap, i2, j2) 
													&& isWarpingOutsideBorder(origMap, warp[0], warp[1]) == false) {
												//GO DOWN A LEVEL (UNLESS ALREADY IN LEVEL 0)
												newK = k - 1;
												
												if(newK < 0) {
													continue;
												}
												
											} else if( isWarpingOutsideBorder(origMap, i2, j2) == false
													&& isWarpingOutsideBorder(origMap, warp[0], warp[1]) ) {
												//GO UP A LEVEL (UNLESS IN LEVEL 0)
												newK = k + 1;
	
												if(newK %10 == 0 && newK > debugMaxK) {
													debugMaxK = newK;
													sopl("New max level: " + debugMaxK);
												}
												
	
												if(newK > 10000) {
													sopl("ERROR: TOO FAR!");
													continue;
												}
											} else {
												sopl("ERROR: bad warp! (They are either both outside doors, or inside doors)");
												sopl(i + "," + j + "," + origMap[i2][j2] + "," + origMap[i3][j3]);
												sopl(warp[0] + "," + warp[1]);
												exit(1);
											}
											
	
											if(explored[warp[0]][warp[1]][newK] == false) {
												
												//Add warp location as area to explore if not explored:
												ret.add(new prob20objpart2(warp[0], warp[1], newK, dist.get(i +"," + j + "," + k)  + 1));
												dist.put(warp[0] +"," + warp[1] + "," + newK, dist.get(i +"," + j + "," + k)  + 1);
												explored[warp[0]][warp[1]][newK] = true;

											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		return ret;
	}
	
	//post: if warp location (i, j) is on the outside, it's warping to the next level...
	public static boolean isWarpingOutsideBorder(char origMap[][], int i, int j) {
		if( i < OUTSIDE_BORDER || origMap.length - OUTSIDE_BORDER < i || j < OUTSIDE_BORDER || origMap[i].length - OUTSIDE_BORDER < j) {
			return true;
		} else {
			return false;
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

}
