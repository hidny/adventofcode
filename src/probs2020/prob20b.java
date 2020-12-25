package probs2020;
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

public class prob20b {


	public static int LENGTH_GRID = 12;
	//public static int LENGTH_GRID = 3;
	public static int LENGTH_BORDER = -1;
	

	public static int NUM_ROT_AND_FLIP = 8;

	public static ArrayList<Long> codes = new ArrayList<Long>();
	
	public static boolean tiles[][][] = null;
	
	public static void main(String[] args) {
		Scanner in;
		try {
			 //in = new Scanner(new File("in2020/prob2020in20.txt"));
			 in = new Scanner(new File("in2020/prob2020in20.txt"));
			int numTimes = 0;
			 
			int count = 0;
			boolean part2 = false;

			LinkedList queue = new LinkedList();
			Stack stack = new Stack();
			HashSet set = new HashSet();
			
			
			Hashtable<Long, Integer> trail = new Hashtable<Long, Integer>();
			
			ArrayList <String>lines = new ArrayList<String>();
			
			
			//int LIMIT = 20000;
			//boolean table[][] = new boolean[LIMIT][LIMIT];
			
			
			//dir: 0 up
			//1 right
			//2 down
			//3 left
			
			while(in.hasNextLine()) {
				String line = in.nextLine();
				lines.add(line);
				
				
			}
			
			
			
			
			int tileIndex = -1;
			int rowIndex = -1;
			
			
			for(int i=0; i<lines.size(); i++) {
				
				//int temp = Integer.parseInt(lines.get(i));
				//count+=temp;
				
				String line = lines.get(i);
				String lineCopy = String.copyValueOf(line.toCharArray());

				if(line.contains("Tile")) {
					sopl("Tile");
					codes.add(plong(line.split(" ")[1].split(":")[0]));
					sopl(plong(line.split(" ")[1].split(":")[0]));
					tileIndex++;
					rowIndex = 0;
					
				} else if(line.contains(".") || line.contains("#")) {
					
					if(LENGTH_BORDER == -1) {
						LENGTH_BORDER = line.length();
						tiles= new boolean [LENGTH_GRID * LENGTH_GRID][LENGTH_BORDER][LENGTH_BORDER];
						System.out.println("New grid");
					}
					
					for(int j=0; j<line.length(); j++) {
						if(line.charAt(j) == '#') {
							tiles[tileIndex][rowIndex][j] = true;
							
						} else {
							tiles[tileIndex][rowIndex][j] = false;
						}
					}
					rowIndex++;
				}
				
				
			}
			
			sopl("Tile index:");
			sopl(tileIndex);
			
			boolean borders[][][] = new boolean[NUM_ROT_AND_FLIP][tiles.length][tiles[0][0].length];
			
			//even: up to down or left to right
			
			for(int i=0; i<tiles.length; i++) {
				
				for(int j=0; j<tiles[i].length; j++) {
					borders[0][i][j] = tiles[i][0][j];
					borders[1][i][j] = tiles[i][j][LENGTH_BORDER - 1];
					borders[2][i][j] = tiles[i][LENGTH_BORDER - 1][LENGTH_BORDER - j - 1];
					borders[3][i][j] = tiles[i][LENGTH_BORDER - j - 1][0];
					
					
					borders[4][i][j] = tiles[i][0][LENGTH_BORDER - j - 1];
					

					borders[5][i][j] = tiles[i][j][0];
					
					borders[6][i][j] = tiles[i][LENGTH_BORDER - 1][j];
					
					borders[7][i][j] = tiles[i][LENGTH_BORDER - j - 1][LENGTH_BORDER - 1];
					

					

				}
			}
			
					
			for(int i=0; i<borders[0].length; i++) {
				for(int j=i+1; j<borders[0].length; j++) {
					for(int r1= 0; r1 <8; r1++) {
						for(int r2= 0; r2 <8; r2++) {
							if(bordersMatch(borders, r1, i, r2, j)) {
								System.out.println(codes.get(i) + " rotated " + r1 + "  match " + codes.get(j) + " rotated "+ r2);
							}
						}
					}
				}
			}
			
			buildTable(borders);
			
			sopl("end");
			
			System.out.println("Number of sea monsters: " + numSeaMonsters);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public static int orientation[];
	
	public static int order[];
	
	public static void buildTable(boolean borders[][][]) {
		orientation = new int[borders[0].length];
		order = new int[borders[0].length];
		
		for(int i=0; i<borders[0].length; i++) {
			order[i] = - 1;
		}
		
		buildTable(borders, 0);
	}
	
	public static String dragon[] = { "                  # ",
			                          "#    ##    ##    ###",
			                          " #  #  #  #  #  #   "};
	

	public static int numSeaMonsters = 0;
	
	public static void countNumSeaMonstersPart2() {
		boolean checkDragon[][] = buildDragon();
		
		boolean map[][] = buildMap();
		

		boolean mapMonsters[][] =new boolean[map.length][map[0].length];
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[0].length; j++) {
				mapMonsters[i][j] = false;
			}
		}
		
		int temp_Num_Monsters = 0;
	
		sopl("countNumSeaMonsters " + numSeaMonsters);
		
		for(int i=0; i < map.length; i++) {
			for(int j=0; j<map[i].length; j++) {
				
				boolean foundDragonSoFar = true;
				
				SEARCH_DRAGON:
				for(int i2=0; i2<checkDragon.length; i2++) {
					
					if(i + i2 >= map.length) {
						foundDragonSoFar = false;
						 break SEARCH_DRAGON;
					}
					
					for(int j2=0; j2<checkDragon[0].length; j2++) {
						if(j + j2 >= map[0].length) {
							foundDragonSoFar = false;
							continue SEARCH_DRAGON;
						}
						
						
						if(checkDragon[i2][j2] && !map[i + i2][j + j2]) {
							foundDragonSoFar = false;
							continue SEARCH_DRAGON;
							
						}
						
					}
				}
				
				
				if(foundDragonSoFar) {
					temp_Num_Monsters++;
					for(int i2=0; i2<checkDragon.length; i2++) {
						for(int j2=0; j2<checkDragon[0].length; j2++) {
							if(checkDragon[i2][j2]) {
								mapMonsters[i + i2][j + j2] = true;
							}
						}
					}
				}
				
				
				
			}
		}
		
		int count = 0;
		for(int i=0; i < map.length; i++) {
			for(int j=0; j<map[i].length; j++) {
				if(map[i][j] && mapMonsters[i][j] == false) {
					count++;
				}
			}
		}
		
		if(temp_Num_Monsters >0 && count > 0) {
			sopl("Answer part 2: " + count);
			//exit(1);
		}
		
		
	}
	public static boolean[][] buildDragon() {
		
		boolean ret[][] = new boolean[dragon.length][dragon[0].length()];
		
		for(int i=0; i<dragon.length; i++) {
			for(int j=0; j<dragon[0].length(); j++) {
				if(dragon[i].charAt(j) == '#') {
					ret[i][j] = true;
				} else {
					ret[i][j] = false;
				}
			}
		}
		
		return ret;
	}

	public static boolean[][] buildMap() {
		
		buildMapDebug();
		
		int LENGTH_TILE = LENGTH_BORDER - 2;
		
		boolean ret[][] = new boolean[LENGTH_GRID *LENGTH_TILE][LENGTH_GRID *LENGTH_TILE];
		
		for(int i=0; i<LENGTH_GRID; i++) {
			for(int j=0; j<LENGTH_GRID; j++) {
				
				int indexTile = order[i * LENGTH_GRID + j];
				int orient = orientation[i * LENGTH_GRID + j];
				
				if(orient == 0) {
					
					for(int i2=0; i2<LENGTH_TILE; i2++) {
						for(int j2=0; j2<LENGTH_TILE; j2++) {
							
							ret[i * LENGTH_TILE + i2][j * LENGTH_TILE + j2] = tiles[indexTile][i2+1][j2+1];
						}
					}
				} else if( orient == 1) {
					for(int i2=0; i2<LENGTH_TILE; i2++) {
						for(int j2=0; j2<LENGTH_TILE; j2++) {
							
							ret[i * LENGTH_TILE + i2][j * LENGTH_TILE + j2] = tiles[indexTile][j2+1][LENGTH_BORDER -2 - i2];
						}
					}
				} else if( orient == 2) {
					for(int i2=0; i2<LENGTH_TILE; i2++) {
						for(int j2=0; j2<LENGTH_TILE; j2++) {
							
							ret[i * LENGTH_TILE + i2][j * LENGTH_TILE + j2] = tiles[indexTile][LENGTH_BORDER -2 - i2][LENGTH_BORDER -2 - j2];
						}
					}
				} else if( orient == 3) {
					for(int i2=0; i2<LENGTH_TILE; i2++) {
						for(int j2=0; j2<LENGTH_TILE; j2++) {
							
							ret[i * LENGTH_TILE + i2][j * LENGTH_TILE + j2] = tiles[indexTile][LENGTH_BORDER -2 - j2][i2+1];
						}
					}
				} else if(orient == 4) {
					
					for(int i2=0; i2<LENGTH_TILE; i2++) {
						for(int j2=0; j2<LENGTH_TILE; j2++) {
							
							ret[i * LENGTH_TILE + i2][j * LENGTH_TILE + j2] = tiles[indexTile][i2+1][LENGTH_BORDER -2 - j2];
						}
					}
				} else if( orient == 5) {
					for(int i2=0; i2<LENGTH_TILE; i2++) {
						for(int j2=0; j2<LENGTH_TILE; j2++) {
							
							ret[i * LENGTH_TILE + i2][j * LENGTH_TILE + j2] = tiles[indexTile][j2+1][i2+1];
						}
					}
				} else if( orient ==6) {
					for(int i2=0; i2<LENGTH_TILE; i2++) {
						for(int j2=0; j2<LENGTH_TILE; j2++) {
							
							ret[i * LENGTH_TILE + i2][j * LENGTH_TILE + j2] = tiles[indexTile][LENGTH_BORDER -2 - i2][j2+1];
						}
					}
				} else if( orient == 7) {
					for(int i2=0; i2<LENGTH_TILE; i2++) {
						for(int j2=0; j2<LENGTH_TILE; j2++) {
							
							ret[i * LENGTH_TILE + i2][j * LENGTH_TILE + j2] = tiles[indexTile][LENGTH_BORDER -2 - j2][LENGTH_BORDER -2 - i2];
						}
					}
				}
			}
		}
		
		return ret;
	}
	
	

	public static boolean[][] buildMapDebug() {
		
		boolean ret[][] = new boolean[LENGTH_GRID *LENGTH_BORDER][LENGTH_GRID *LENGTH_BORDER];
		
		for(int i=0; i<LENGTH_GRID; i++) {
			for(int j=0; j<LENGTH_GRID; j++) {
				
				int indexTile = order[i * LENGTH_GRID + j];
				int orient = orientation[i * LENGTH_GRID + j];
				
				if(orient == 0) {
					
					for(int i2=0; i2<LENGTH_BORDER; i2++) {
						for(int j2=0; j2<LENGTH_BORDER; j2++) {
							
							ret[i * LENGTH_BORDER + i2][j * LENGTH_BORDER + j2] = tiles[indexTile][i2][j2];
						}
					}
				} else if( orient == 1) {
					for(int i2=0; i2<LENGTH_BORDER; i2++) {
						for(int j2=0; j2<LENGTH_BORDER; j2++) {
							
							ret[i * LENGTH_BORDER + i2][j * LENGTH_BORDER + j2] = tiles[indexTile][j2][LENGTH_BORDER - 1 - i2];
						}
					}
				} else if( orient == 2) {
					for(int i2=0; i2<LENGTH_BORDER; i2++) {
						for(int j2=0; j2<LENGTH_BORDER; j2++) {
							
							ret[i * LENGTH_BORDER + i2][j * LENGTH_BORDER + j2] = tiles[indexTile][LENGTH_BORDER - 1 - i2][LENGTH_BORDER - 1 - j2];
						}
					}
				} else if( orient == 3) {
					for(int i2=0; i2<LENGTH_BORDER; i2++) {
						for(int j2=0; j2<LENGTH_BORDER; j2++) {
							
							ret[i * LENGTH_BORDER + i2][j * LENGTH_BORDER + j2] = tiles[indexTile][LENGTH_BORDER - 1 - j2][i2];
						}
					}
				} else if(orient == 4) {
					
					for(int i2=0; i2<LENGTH_BORDER; i2++) {
						for(int j2=0; j2<LENGTH_BORDER; j2++) {
							
							ret[i * LENGTH_BORDER + i2][j * LENGTH_BORDER + j2] = tiles[indexTile][i2][LENGTH_BORDER - 1 - j2];
						}
					}
				} else if( orient == 5) {
					for(int i2=0; i2<LENGTH_BORDER; i2++) {
						for(int j2=0; j2<LENGTH_BORDER; j2++) {
							
							ret[i * LENGTH_BORDER + i2][j * LENGTH_BORDER + j2] = tiles[indexTile][j2][i2];
						}
					}
				} else if( orient ==6) {
					for(int i2=0; i2<LENGTH_BORDER; i2++) {
						for(int j2=0; j2<LENGTH_BORDER; j2++) {
							
							ret[i * LENGTH_BORDER + i2][j * LENGTH_BORDER + j2] = tiles[indexTile][LENGTH_BORDER - 1 - i2][j2];
						}
					}
				} else if( orient == 7) {
					for(int i2=0; i2<LENGTH_BORDER; i2++) {
						for(int j2=0; j2<LENGTH_BORDER; j2++) {
							
							ret[i * LENGTH_BORDER + i2][j * LENGTH_BORDER + j2] = tiles[indexTile][LENGTH_BORDER - 1 - j2][LENGTH_BORDER - 1 - i2];
						}
					}
				}
			}
		}
		
		for(int i=1; i<LENGTH_GRID; i++) {
			for(int j=1; j<LENGTH_GRID; j++) {
				
				for(int k=0; k<LENGTH_BORDER; k++) {
					
					//sopl("check ( " + i +", " + j + "," + k + ") :");
					
					
					if(ret[i * LENGTH_BORDER  + k][j * LENGTH_BORDER] != ret[i * LENGTH_BORDER + k][j * LENGTH_BORDER  - 1]) {
						sopl("DOH!2");
						exit(1);
					}
					
					if(ret[i * LENGTH_BORDER][j * LENGTH_BORDER + k] != ret[i * LENGTH_BORDER  - 1 ][j * LENGTH_BORDER + k]) {
						sopl("DOH!");
						
						sopl(orientation[i * (LENGTH_GRID - 1) +j] + " above " + orientation[i * LENGTH_GRID +j]);
						exit(1);
					}
				}
			}
		}
		
		return ret;
	}
	
	
	public static int iter = 0;
	
	
	public static void buildTable(boolean borders[][][], int curIndex) {
		
		if(curIndex >= codes.size()) {
			
			long answer = codes.get(order[0]) * codes.get(order[LENGTH_GRID - 1]) * codes.get(order[LENGTH_GRID * LENGTH_GRID  - 1]) * codes.get(order[LENGTH_GRID * LENGTH_GRID  - LENGTH_GRID]);
			
			sopl();
			for(int i=0; i<order.length; i++) {
				sop(order[i] + " ");
			}
			sopl();
			for(int i=0; i<order.length; i++) {
				if(order[i] >= 0) {
					sop(codes.get(order[i]) + " ");
				}
			}
			sopl();
			sopl("Answer: " + answer);
			//exit(1);
			
			//TODO: find sea monster
			
			countNumSeaMonstersPart2();
			
			return;
		}
		
		if(curIndex > 12) {
			//sopl("HEY");
		}
		//Slow but whatever
		boolean taken[] = new boolean[codes.size()];
		for(int i=0; i<taken.length; i++) {
			taken[i] = false;
		}
		for(int i=0; i<curIndex; i++) {
			taken[order[i]] = true;
		}
		
		for(int i=0; i<codes.size(); i++) {
			//sopl("I" + i);
			if(taken[i] == false) {
			
					//try this one.

					//int lastHorizontalCodeIndex = order[curIndex - 1];

					int lastOrToUseHor = -1;
					if(curIndex % LENGTH_GRID > 0) {
						int lastOrientation = orientation[curIndex - 1];
						
						if(lastOrientation  < 4) {
							lastOrToUseHor = (lastOrientation +  1) % 4;
							
						} else {
							lastOrToUseHor = 4 + (lastOrientation + 1) % 4;
						}
					} else {
						lastOrToUseHor = -1;
					}

					int lastOrToUseVert = -1;
					if(curIndex >= LENGTH_GRID) {
						
						int aboveOrientation = orientation[curIndex - LENGTH_GRID];
						//0 -> 5
						//1 -> 4
						//2 -> 7
						//3 -> 6
						if(aboveOrientation  < 4) {
							lastOrToUseVert = 4 + (2 + 8 - aboveOrientation) % 4;
						
						//4 - > 1
						//5 - > 0
						//6 -> 3
						//7 -> 2
						} else {
							lastOrToUseVert = (2 + 8 - aboveOrientation) % 4;
						}
					} else {
						lastOrToUseVert = -1;
					}

					
					boolean borderMatch;
					for(int rot=0; rot<8; rot++) {
						if(order[4] != -1 && codes.get(order[0]) == 1951 && codes.get(order[1]) == 2311 && codes.get(order[2]) == 3079 && codes.get(order[3]) == 2729 && codes.get(order[4]) == 1427
								&& i==5 && (rot == 7)) {
							iter++;
							sopl("iter: " + iter);
							for(int j=0; j<order.length; j++) {
								if(order[j] >= 0) {
									sop(codes.get(order[j]) + " ");
								}
							}
							sopl();
						}
						
						//sopl(rot);
						borderMatch = true;
						//Hor
						int curIndexToUseHor = -1;
							
						if(rot  < 4) {
							curIndexToUseHor = 4 + (9 - rot) % 4;
							
						} else {
							curIndexToUseHor = ((9 - rot) % 4);
						}
					
						int curIndexToUseVert = rot;
						
						if(lastOrToUseHor != -1 && codes.get(order[curIndex - 1]) == 1951 && codes.get(i) == 2311) {
							if(bordersMatch(borders, lastOrToUseHor, order[curIndex - 1], curIndexToUseHor, i)) {
								sopl(codes.get(order[curIndex - 1]) + " rotated " + lastOrToUseHor + "  versus " + codes.get(i) + " rotated " + curIndexToUseHor);
								sopl("MATCH");
							}
						}
						if(lastOrToUseHor != -1 && bordersMatch(borders, lastOrToUseHor, order[curIndex - 1], curIndexToUseHor, i) == false) {
							
							borderMatch = false;

						} else {
							//sopl("Match hor");
						}

						if(lastOrToUseVert != -1 && bordersMatch(borders, lastOrToUseVert, order[curIndex - LENGTH_GRID], curIndexToUseVert, i) == false) {
							borderMatch = false;
							/*sopl(curIndex + ": no match");
							
							sopl("start");
							for(int k=0; k<order.length;k++) {
								sop(order[k] + " ");
							}
							sopl();
							for(int k=0; k<order.length;k++) {
								sop(orientation[k] + " ");
							}
							sopl();
							sopl("end");*/
							
						} else  {
							//sopl("Match vert");
						}
						
						
						if(borderMatch) {
							
							//sopl("I" + i +"what");
							orientation[curIndex] = rot;
							order[curIndex] = i;
							//sopl("Build table: " + curIndex);
							curIndex = curIndex + 1;
							
							buildTable(borders, curIndex);
							
							curIndex = curIndex - 1;
							
							orientation[curIndex] = -1;
							order[curIndex] = -1;
							//sopl("Out of build table");
						}
						
						if(order[4] != -1 && codes.get(order[0]) == 1951 && codes.get(order[1]) == 2311 && codes.get(order[2]) == 3079 && codes.get(order[3]) == 2729 && codes.get(order[4]) == 1427
								&& i==5 && (rot == 7)) {
							iter++;
							sopl("iter: " + iter);
							for(int j=0; j<order.length; j++) {
								if(order[j] >= 0) {
									sop(codes.get(order[j]) + " ");
								}
							}
							sopl();
						}

				}
			}
		}
		
		//sopl("end");
		
	}
	
	
	
	
	public static boolean bordersMatch(boolean borders[][][] , int or1, int tile1, int or2, int tile2) {
		
		for(int k=0; k<borders[or1][tile1].length; k++) {
			if(borders[or1][tile1][k] != borders[or2][tile2][k]) {
				return false;
			}
		}
		
		return true;
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
