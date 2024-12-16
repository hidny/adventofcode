package probs2024;
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

public class prob15b {

	//day1 part 1
	//2:38.01
	
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2024/prob2024in15.txt"));
			//in = new Scanner(new File("in2024/prob2024in0.txt"));
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
			long cur = 0L;
			ArrayList ints = new ArrayList<Integer>();
			
			
			char map[][] = getCharTable(lines);

			for(int i=0; i<map.length; i++) {
				for(int j=0; j<map[0].length; j++) {
					sop(map[i][j]);
				}
				sopl();
			}
			sopl();
			
			String moves = "";
			for(int i=0; i<lines.size(); i++) {
				
				
				line = lines.get(i);
				if(! line.contains("#")) {
					moves += line;
				}
			}
			
			char moves2[] = moves.toCharArray();

			sopl("moves2: ");
			for(int i=0; i<moves2.length; i++) {
				sop(moves2[i]);
			}
			sopl();
			sopl("test last: " + moves2[moves2.length - 1]);
			
			
			int curi = -1;
			int curj = -1;
			for(int i=0; i<map.length; i++) {
				for(int j=0; j<map[0].length; j++) {
					if(map[i][j] == '@') {
						curi = i;
						curj = j;
					}
				}
			}
			
			int diri;
			int dirj;
			
			for(int i=0; i<moves2.length; i++) {
				diri=0;
				dirj=0;
				
				if(moves2[i] == '^') {
					
					diri=-1;
					
					
				} else if(moves2[i] == '>') {
					dirj=1;
					
				} else if(moves2[i] == 'v') {
					diri=1;
					
				} else if(moves2[i] == '<') {
					dirj=-1;
					
				} else {
					sopl("oops!");
					exit(1);
				}
				
				//Has space:
				int nexti=curi+diri;
				int nextj=curj+dirj;
				
				if(diri != 0) {
					
					boolean pushingVertPrev[] = new boolean[map[0].length];
					boolean pushingVert[] = new boolean[map[0].length];
					pushingVert[curj] = true;
					pushingVertPrev = pushingVert;
					
					pushingVert = new boolean[map[0].length];

					boolean foundBlocker = false;

					int numVertPush = 0;
					boolean pushed[][] = new boolean[map.length][map[0].length];
					//pushed[curi][curj] = true;
					
					pushed[nexti][curj] = true;
					
					do {
						
						for(int j=0; j<pushingVert.length; j++) {
							pushingVert[j] = false;
						}
						
						//Find out if blocking...
						for(int j=0; j<pushingVertPrev.length; j++) {
							
							
							if(pushingVertPrev[j]) {
								
								if(map[nexti][j] == '#') {
									
									foundBlocker = true;
								} else if(map[nexti][j] == '[') {
									
									pushingVert[j] = true;
									pushingVert[j+1] = true;
									
									pushed[nexti+diri][j] = true;
									pushed[nexti+diri][j+1] = true;
									
								} else if(map[nexti][j] == ']') {
									
									pushingVert[j] = true;
									pushingVert[j-1] = true;

									pushed[nexti+diri][j] = true;
									pushed[nexti+diri][j-1] = true;
									
								}
								
							}
							
						}
						
						numVertPush = 0;
						for(int j=0; j<pushingVert.length; j++) {
							if(pushingVert[j]) {
								numVertPush++;
							}
						}

						if(numVertPush > 0) {
							pushingVertPrev = pushingVert;
							pushingVert = new boolean[map[0].length];
						}
						
						nexti=nexti+diri;
					
						sopl(".");
					//TODO
					} while(foundBlocker == false && numVertPush > 0);
					
					if(foundBlocker == true) {
						//Do nothing
					} else {
						
						for(int i2=0; i2<pushed.length; i2++) {
							for(int j=0; j<pushingVertPrev.length; j++) {
								
								int iuse = i2;
								if(diri == 1) {
									iuse = pushed.length - 1 - i2;
								}
								
								if(pushed[iuse][j]) {
									
									sopl("Push");
									sopl((iuse- diri) + "," + j);
									sopl("to: " + iuse + "," + j);
									map[iuse][j] = map[iuse- diri][j];
									map[iuse- diri][j] = '.';
									
									
								}
							}
						}
						
						
						/*sopl("No blcoker!");
						sopl("Next i: " + nexti);
						sopl("cur i: " + nexti);
						sopl("dir i: " + diri);
						pushingVert = new boolean[map[0].length];

						nexti=nexti-diri;
						
						while(curi  != nexti) {
							//Find out if blocking...
							
							for(int j=0; j<pushingVert.length; j++) {
								pushingVert[j] = false;
							}
							
							for(int j=0; j<pushingVertPrev.length; j++) {
								
								
								if(pushingVertPrev[j]) {
									if(map[nexti - diri][j] == '#') {
										sopl("Doh! #");
										exit(1);
										foundBlocker = true;
										
									} else if(map[nexti - diri][j] == '[') {
										pushingVert[j] = true;
										pushingVert[j+1] = true;
										
									} else if(map[nexti - diri][j] == ']') {
										pushingVert[j] = true;
										pushingVert[j-1] = true;
										
									}
									
								}
								
							}
							
							int numDebug =0;
							for(int j=0; j<pushingVertPrev.length; j++) {
								if(pushingVertPrev[j]) {
									numDebug++;
									sopl("MT move " + (nexti) + " to " + (nexti+diri));
									sopl("MT move " + (j) + " to " + (j));
									sopl("MT move " + map[nexti-diri][j] + " to " + map[nexti][j]);
									map[nexti][j] = map[nexti-diri][j];
									map[nexti-diri][j] = '.';
									
								}
							}
							if(numDebug> 1) {
								System.out.println("Debug");
							}

							pushingVertPrev = pushingVert;
							pushingVert = new boolean[map[0].length];
							
							numVertPush = 0;
							for(int j=0; j<pushingVertPrev.length; j++) {
								if(pushingVertPrev[j]) {
									numVertPush++;
								}
							}
						
							nexti=nexti-diri;

							sopl("!");
						//TODO
						}
						*/
						
						
						curi += diri;
						
					}
					
					
				} else {
					while(map[nexti][nextj] ==']' || map[nexti][nextj] =='[') {
						nexti=nexti+diri;
						nextj=nextj+dirj;
					}
					
					if(map[nexti][nextj] == '#') {
						//do nothing
					} else if(map[nexti][nextj] == '.') {
						//move:
						while(nexti != curi || nextj != curj) {
							map[nexti][nextj] = map[nexti - diri][nextj - dirj];
							nexti = nexti - diri;
							nextj = nextj - dirj;
							sopl(map[nexti - diri][nextj - dirj] + "to ");
							sopl(map[nexti][nextj]);
						}
						map[curi][curj] = '.';
						
						sopl("move");
						curi = curi + diri;
						curj = curj + dirj;
					}
				}
				
				/*sopl("Move " + i + "(" + moves2[i] + ")]]:");
				sopl("curi,curj: " + curi + ", " + curj);
				for(int i2=0; i2<map.length; i2++) {
					for(int j=0; j<map[0].length; j++) {
						sop(map[i2][j]);
					}
					sopl();
				}
				sopl();
				sopl();
				*/
				
			}
			
			//1432364
			//1432369
			cur = 0L;
			
			for(int i=0; i<map.length; i++) {
				for(int j=0; j<map[0].length; j++) {
					if(map[i][j] == '[' ) {
						cur += 100*i + j;
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
		
		int height = 0;
		int num= 0;
		for(int i=0; i<lines.size(); i++) {
			if(lines.get(i).startsWith("####")) {
				num++;
				if(num == 2) {
					break;
				}
			} else {
				height++;
			}
		}
		height++;
		height++;
		char grid[][] = new char[height][2 * lines.get(0).length()];
		
		for(int i=0; i<height; i++) {
			
			for(int j=0; j<lines.get(i).length(); j++) {
				
				grid[i][2 * j] = lines.get(i).charAt(j);
				
				if(lines.get(i).charAt(j) == '@') {
					grid[i][2 * j + 1] = '.';
				} else if(lines.get(i).charAt(j) == 'O') {
					
					grid[i][2 * j] = '[';
					grid[i][2 * j + 1] = ']';
					
				} else {
					grid[i][2 * j + 1] = grid[i][2 * j];
				}

			}
		}
		
		return grid;
	}

}
