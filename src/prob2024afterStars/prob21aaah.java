package prob2024afterStars;
import java.io.File;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

import number.IsNumber;
import probs2024.prob21state;
import probs2024.prob21state2;
import utils.Mapping;
import utils.Sort;

public class prob21aaah {

	//day1 part 1
	//2:38.01
	
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2024/prob2024in21.txt"));
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
			long matrix[][] = new long[25][25];
			for(int i2=0; i2<mapDir.length; i2++) {
				for(int j2=0; j2<mapDir[0].length; j2++) {
					for(int i3=0; i3<mapDir.length; i3++) {
						for(int j3=0; j3<mapDir[0].length; j3++) {
							
							
							char prev = mapDir[i2][j2];
							char next = mapDir[i3][j3];
							
							if(prev == '#' || next == '#') {
								continue;
							}

							int previ = getiCoord(prev);
							int prevj = getjCoord(prev);
							
							int nexti = getiCoord(next);
							int nextj = getjCoord(next);
	
							
							
							String newString = "A";
							for(int i4=0; i4<nexti - previ; i4++) {
								newString += "v";
							}

							for(int i4=0; i4<previ - nexti; i4++) {
								newString += "^";
							}
							
							for(int i4=0; i4<nextj - prevj; i4++) {
								newString += ">";
							}

							for(int i4=0; i4<prevj - nextj; i4++) {
								newString += "<";
							}
							newString += "A";
							
							int matrixJ = 5 * getMoveIndex(prev) + getMoveIndex(next);
							
							for(int i4=1; i4<newString.length(); i4++) {
								int matrixI = 5 * getMoveIndex(newString.charAt(i4-1)) + getMoveIndex(newString.charAt(i4));
								matrix[matrixJ][matrixI]++;
							}
							
							
							//matrix[5 * getMoveIndex(prev) + getMoveIndex(next)][getMoveIndex(next)] = steps;
							
							//TODO: 25x25 matrix!
							
						}
					}
				}
				
			}
			
			for(int i2=0; i2<matrix.length; i2++) {
				for(int j=0; j<matrix[0].length; j++) {
					sop(matrix[i2][j] + ", ");
				}
				sopl();
			}
			
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
			for(int i=0; i<lines.size(); i++) {
				
				
				line = lines.get(i);
				
				int num = 0;
				
				for(int j=0; j<line.length(); j++) {
					if(line.charAt(j) >= '0' && line.charAt(j) <= '9') {
						num = 10*num + (int)(line.charAt(j) - '0');
					}
				}
				
				sopl("Num: " + num);
				
				
				String shortestOld = getShortestSequence(new prob21state2(2), line);
				
				String shortest = rearrange(shortestOld);
				
				String newAnswer ="";
				
				char prev = 'A';
				for(int j=0; j<shortest.length(); j++) {
					char next = shortest.charAt(j);
					
					int nexti = getiCoord(next);
					int nextj = getjCoord(next);

					int previ = getiCoord(prev);
					int prevj = getjCoord(prev);
					
					int steps = Math.abs(nexti - previ) +  Math.abs(nextj - prevj);
					
					for(int k=0; k<steps; k++) {
						newAnswer += "#";
					}
					
					newAnswer += "A";
					prev = next;
					
					
				}
				
				long ret[] = new long[25];
				for(int i2=0; i2<shortest.length(); i2++) {
					char prev2 = 'A';
					if(i2 > 0) {
						prev2 = shortest.charAt(i2-1);
					}
					ret[5 * getMoveIndex(prev2) + getMoveIndex(shortest.charAt(i2))]++;
				}
				
				
				ret = matrixMult(matrix, ret);
				ret = matrixMult(matrix, ret);
				String matrixTest = "";
				for(int i2=0; i2<ret.length; i2++) {

					for(int j2=0; j2<ret[i2]; j2++) {
						matrixTest += "#";
					}
				}
				

				String actualbefore = getShortestSequence(new prob21state2(4), line);
				String actual = getShortestSequence(new prob21state2(4), line);
				
				sopl("ShortestOld: " + shortestOld);
				sopl("Shortest: " + shortest);
				sopl("newAnswer: " + newAnswer);
				sopl("actualbefore:    " + actualbefore);
				sopl("actual:    " + actual);
				sopl("actual:    " + actual.length());
				sopl("matrixTest:" + matrixTest);
				sopl("matrixTest:" + matrixTest.length());
				
				//cur += num * shortest.length();

				for(int i2=0; i2<23; i2++) {
					ret = matrixMult(matrix, ret);
				}
				
				long tmp = 0L;
				for(int i2=0; i2<ret.length; i2++) {

					tmp += ret[i2];
				}
				
				cur += num * tmp;
				
				//251974814009316 too high
			}

			//char map[][] = getCharTable(lines);

			
			prob21state curState = new prob21state();
			
			/*String moves = "<v<A>>^AvA^A<vA<AA>>^AAvA<^A>AAvA^A<vA>^AA<A>A<v<A>A>^AAAvA<^A>A";
			
			for(int i2=0; i2<curState.robotsLocations.length; i2++) {
				for(int j2=0; j2<curState.robotsLocations[0].length; j2++) {
					sop(curState.robotsLocations[i2][j2] + ", ");
				}
				sopl();
			}
			
			for(int i=0; i<moves.length(); i++) {
				curState = curState.makeMove(getMoveIndex(moves.charAt(i)));
				
				if(curState.badState) {
					sopl("reached bad state");
				}
				sopl("after: " + moves.charAt(i));
				sopl("At i= " + i + ": (" + curState.curOutput + ")");
				
				for(int i2=0; i2<curState.robotsLocations.length; i2++) {
					for(int j2=0; j2<curState.robotsLocations[0].length; j2++) {
						sop(curState.robotsLocations[i2][j2] + ", ");
					}
					sopl();
				}
				
			}
			*/
			
			sopl("Answer: " + cur);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
public static long[] matrixMult(long matrix[][], long ret[]) {
		
		long ret2[] = new long[ret.length];
		
		
		for(int i=0; i<ret2.length; i++) {
			
			for(int j=0; j<ret2.length; j++) {
				
				ret2[i] += matrix[i][j] * ret[j];
			}
		}
		
		return ret2;
		
	}
	
	public static void print(long ret[]) {
		
		for(int i=0; i<ret.length; i++) {
			
				
			sop(ret[i] + ",");
		}
		sopl();
	}
	
	
	public static String rearrange(String shortest) {
		
		String output = "";
		
		int curIndex = 0;
		
		while(curIndex < shortest.length()) {
			int nextIndex = curIndex + shortest.substring(curIndex).indexOf('A');
			if(nextIndex == -1) {
				break;
			}
			
			String tmp = shortest.substring(curIndex, nextIndex);
			
			int num[] = new int[4];
			for(int j=0; j<tmp.length(); j++) {
				
				if(tmp.charAt(j) == '^') {
					num[0]++;
				} else if(tmp.charAt(j) == '>') {
					num[1]++;
				} else if(tmp.charAt(j) == 'v') {
					num[2]++;
				} else if(tmp.charAt(j) == '<') {
					num[3]++;
				}
			}
			
			for(int i=0; i<num[0]; i++) {
				output+="^";
			}
			for(int i=0; i<num[1]; i++) {
				output+=">";
			}
			for(int i=0; i<num[2]; i++) {
				output+="v";
			}
			for(int i=0; i<num[3]; i++) {
				output+="<";
			}
			
			output+="A";
			curIndex = nextIndex + 1;
		}
		
		return output;
	}
	
	public static int getiCoord(char in) {
		if(in == '^' || in == 'A') {
			return 0;
		} else {
			return 1;
		}
	}
	public static int getjCoord(char in) {
		if(in == '<') {
			return 0;
		} else if(in == '^' || in == 'v') {
			return 1;
		} else {
			return 2;
		}
	}
	
	public static String getShortestSequence(prob21state2 curState, String line) {
		
		
		LinkedList<prob21state2> queue = new LinkedList<prob21state2>();
		HashSet<String> listStates = new HashSet<String>();
		
		queue.add(curState);
		listStates.add(curState.toString());
		
		while( ! queue.isEmpty()) {
			
			
			prob21state2 cur = queue.remove();
			
			
			for(int j=0; j<nextChar.length; j++) {
				prob21state2 next =  cur.makeMove(getMoveIndex(nextChar[j]));
				
				if(next.badState) {
					//pass
				} else if(line.startsWith(next.curOutput) == false) {
					//pass
					
				} else if( ! listStates.contains(next.toString())) {
					listStates.add(next.toString());
					
					
					queue.add(next);
					
					//sopl("list States size: " + listStates.size());
					
					if(next.curOutput.equals(line)) {
						return next.curInput;
					}
				}
			}
		}
		
		
		return "null";
	}
	
	public static boolean matches(prob21state curState, prob21state curstate) {
		
		
		for(int i=0; i<curState.robotsLocations.length; i++) {
			for(int j=0; j<curState.robotsLocations[0].length; j++) {
				
				if(curState.robotsLocations[i][j] != curstate.robotsLocations[i][j]) {
					return false;
				}
			}
		}
		
		if(curState.curOutput.equals(curstate.curOutput)) {
			return true;
		} else {
			return false;
		}
	}
	

	public static char nextChar[] = new char[]{'^', '>', 'v', '<', 'A'};
	public static int getMoveIndex(char move) {
		int innerMove = -1;
		if(move == '^') {
			innerMove = 0;
		} else if(move == '>') {
			innerMove = 1;
		} else if(move == 'v') {
			innerMove = 2;
		} else if(move == '<') {
			innerMove = 3;
		} else if(move == 'A') {
			innerMove = 4;
		}
		if(innerMove == -1) {
			sopl("ahh: " + move);
		}
		return innerMove;
	}
	
	public static char mapDir[][] = {{'#', '^', 'A'},
									 {'<', 'v', '>'}};


	public static char mapKeys[][] = {{'7', '8', '9'},
									  {'4', '5', '6'},
									  {'1', '2', '3'},
									  {'#', '0', 'A'}};
	
	
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
		char grid[][] = new char[lines.size()][lines.get(0).length()];
		
		for(int i=0; i<lines.size(); i++) {
			
			for(int j=0; j<grid[0].length; j++) {
				grid[i][j] = lines.get(i).charAt(j);

			}
		}
		
		return grid;
	}

}
