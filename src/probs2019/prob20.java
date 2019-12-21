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

public class prob20 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			String filename = "in2019/prob2019in20.txt";
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
			dist = new int[lines.size()][lines.get(0).length()];

			for(int i=0; i<origMap.length; i++) {
				for(int j=0; j<origMap[0].length; j++) {
					origMap[i][j] = lines.get(i).charAt(j);
					dist[i][j] = -1;
				}
			}
			
			//TODO: breadth first search
			LinkedList<prob20obj> queue = new LinkedList<prob20obj>();
			
			int startLocation[] = getOtherWarpLocation(origMap, 'A', 'A', -1, -1);
			int endLocation[] = getOtherWarpLocation(origMap, 'Z', 'Z', -1, -1);
			
			int answer = -1;
			
			dist[startLocation[0]][startLocation[1]] = 0;
			queue.add(new prob20obj(startLocation[0], startLocation[1], dist[startLocation[0]][startLocation[1]]));

			LinkedList<prob20obj> deleteQueue = new LinkedList<prob20obj>();
			
			int oldMaxDist = 0;
			
			while(queue.size() > 0) {
				prob20obj cur = queue.getFirst();
				queue.remove();
				
				deleteQueue.add(cur);
				if(cur.dist > oldMaxDist) {
					oldMaxDist = cur.dist;
					
					while(deleteQueue.getFirst().dist <= cur.dist - 2) {
						prob20obj tmp = deleteQueue.getFirst();
						
						if(dist[tmp.i][tmp.j] == -1) {
							sopl("ERROR deleting array element");
							exit(1);
						}
						dist[tmp.i][tmp.j] = -1;
						
						deleteQueue.remove();
						
					}
				}
				
				
				 ArrayList<prob20obj> neighbours = getNeighbours(cur.i, cur.j);
				 
				 for(int i=0; i<neighbours.size(); i++) {
					 queue.add(neighbours.get(i));
				 }
				
				 if(dist[endLocation[0]][endLocation[1]] != -1) {
					 answer = dist[endLocation[0]][endLocation[1]];
					 break;
				 }
			}
			
			for(int i=0; i<dist.length; i++) {
				for(int j=0; j<dist[i].length; j++) {
					if(dist[i][j] >=0) {
						String ret = "" + dist[i][j]%10;
						sop(ret);
					} else {
						sop(origMap[i][j]);
					}
				}
				sopl();
			}
			sopl("Answer: " + answer);
			
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public static char origMap[][];
	public static int dist[][];
	
	public static char EMPTY = '.';
	public static char WALL = '#';
	
	public static ArrayList<prob20obj> getNeighbours(int i, int j) {
		
		ArrayList<prob20obj> ret = new ArrayList<prob20obj>();
		
		for(int i2=Math.max(i-1, 0); i2<=Math.min(i+1, origMap.length - 1); i2++) {
			for(int j2=Math.max(j-1, 0); j2<=Math.min(j+1, origMap[i2].length - 1); j2++) {
				
				if(dist[i2][j2] != -1) {
					continue;
				}
				
				if(i2 == i || j2==j) {
					if(origMap[i2][j2] == EMPTY) {
						ret.add(new prob20obj(i2, j2, dist[i][j] + 1));
						
						dist[i2][j2] = dist[i][j] + 1;
						
					} else if(origMap[i2][j2] >= 'A' && origMap[i2][j2] <= 'Z') {
						
						//sopl("TEST");
						for(int i3=Math.max(i2-1, 0); i3<=Math.min(i2+1, origMap.length - 1); i3++) {
							for(int j3=Math.max(j2-1, 0); j3<=Math.min(j2+1, origMap[i3].length - 1); j3++) {
								
								if(i3 == i2 && j3 == j2) {
									continue;
								}
								
								if((i3 == i2 || j3==j2)) {
									
									//sopl(i2 +"," + j2 +": " + i3 +"," + j3);
									if(origMap[i3][j3] >= 'A' && origMap[i3][j3] <= 'Z') {
										
										//sopl(origMap[i3][j3]);
										
										int warp[] = getOtherWarpLocation(origMap, origMap[i2][j2], origMap[i3][j3], i, j);
										
										if(warp != null && dist[warp[0]][warp[1]] == -1) {
											ret.add(new prob20obj(warp[0], warp[1], dist[i][j] + 1));
											
											sopl("Warp");
											dist[warp[0]][warp[1]] = dist[i][j] + 1;
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
	
	//get i, j coord of warp label
	public static int[] getOtherWarpLocation(char origMap[][], char label1, char label2, int origI, int origJ) {
		
		int tempRet[] = null;
		int ret[] = null;
		boolean isRealWarp = false;
		if(label1 =='A' || label2 == 'A') {
			sopl("debug");
		}
		
		//sopl("TEST" + label1 +", " + label2 + ", " + origI + ", " + origJ);
		for(int i=0; i<origMap.length; i++) {
			for(int j=0; j<origMap[i].length; j++) {
				if(origMap[i][j] == label1 || origMap[i][j] == label2) {
					//sopl("!: " + label1 + "1: " + i + "," + j);
					//sopl("2:" + label2 + "1: " + i + "," + j);
					
					
					for(int i2=Math.max(i-1, 0); i2<=Math.min(i+1, origMap.length - 1); i2++) {
						for(int j2=Math.max(j-1, 0); j2<=Math.min(j+1, origMap[i2].length - 1); j2++) {
							
							if(i2 == i && j2 == j) {
								continue;
							}
							
							if(i2 ==origI && j2 == origJ) {
								continue;
							}
							
							if(i2 != i && j2 != j) {
								continue;
							}
							
							//sopl(i2 +"," + j2);
							
							if(origMap[i2][j2] == EMPTY && (i2 == i || j2==j)) {
							//	sopl(label1 + "2: " +i2 +","+j2);
							//	sopl(label2 + "2: " +i2 +","+j2);
								tempRet = new int[] {i2, j2};
							}
							
							if((origMap[i][j] == label1 && origMap[i2][j2] == label2 )
								|| (origMap[i][j] == label2 && origMap[i2][j2] == label1 )) {
								isRealWarp = true;
							}
							
							
						}
					}
					
					if(isRealWarp && tempRet != null) {
						return tempRet;
						
					} else {
						tempRet = null;
						isRealWarp = false;
					}
				}
			}
		}
		
		
		return null;
	}
	
	public static long BLACK = 0L;
	public static long WHITE = 1L;
	
	public static long getColour(Hashtable<String, Long> paint, int x, int y) {
		if(paint.get(x + "," + y) == null) {
			return BLACK;
		} else if(paint.get(x + "," + y) == BLACK) {
			return BLACK;
		} else {
			return WHITE;
		}
	}
	

	public static void setColour(Hashtable<String, Long> paint, int x, int y, long colour) {
		if(paint.get(x + "," + y) != null) {
			paint.remove(x + "," + y);
		}
		paint.put(x + "," + y, colour);
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
