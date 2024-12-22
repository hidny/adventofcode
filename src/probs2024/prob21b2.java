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

public class prob21b2 {

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

				int PART1 = 3;
				int PART2 = 26;
				ArrayList<String> shortest = getShortestSequence(new prob21state2(2), line);
				String shortest1 = "";
				sopl(shortest.size());
				for(int j=0; j<shortest.size(); j++) {
					
					String tmp = getShortestSequenceAppend(new prob21state3(2), shortest.get(j));
					
					if(j==0) {
						shortest1 = tmp;
					} else if(tmp.length() < shortest1.length()) {
						shortest1 = tmp;
					}
				}

				//String shortest = getShortestSequence(new prob21state2(3), line);
				String shortest_again =  getShortestSequence(new prob21state2(4), line).get(0);
				
				sopl(shortest1.length() + " vs " + shortest_again.length());
				//sopl(shortest1);
				//exit(1);
				
				cur += num * shortest1.length();
				
			}

			//char map[][] = getCharTable(lines);

			
			
			sopl("Answer: " + cur);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	

	public static ArrayList<String> getShortestSequence(prob21state2 curState, String line) {
		
		
		LinkedList<prob21state2> queue = new LinkedList<prob21state2>();
		HashSet<String> listStates = new HashSet<String>();
		
		queue.add(curState);
		listStates.add(curState.toString());
		
		ArrayList<String> ret = new ArrayList<String>();
		int curShortest = -1;
		
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
					
					
					
					if(listStates.size() % 100000 == 0) {
						sopl("list States size: " + listStates.size());
					}
					
					if(next.curOutput.equals(line)) {
						
						if(curShortest == -1 || curShortest  == next.curInput.length()) {
							ret.add(next.curInput);
						}
						
						if(curShortest == -1) {
							curShortest = next.curInput.length();
						}
					} else {

						queue.add(next);
					}
				}
			}
		}
		
		
		return ret;
	}
	

	public static String getShortestSequenceAppend(prob21state3 curState, String line) {
		
		
		LinkedList<prob21state3> queue = new LinkedList<prob21state3>();
		HashSet<String> listStates = new HashSet<String>();
		
		queue.add(curState);
		listStates.add(curState.toString());
		
		while( ! queue.isEmpty()) {
			
			prob21state3 cur = queue.remove();
			
			
			for(int j=0; j<nextChar.length; j++) {
				prob21state3 next =  cur.makeMove(getMoveIndex(nextChar[j]));
				
				if(next.badState) {
					//pass
				} else if(line.startsWith(next.curOutput) == false) {
					//pass
					//sopl("Wrong start");
					
				} else if( ! listStates.contains(next.toString())) {
					listStates.add(next.toString());
					
					
					queue.add(next);

					if(listStates.size() % 100000 == 0) {
						sopl("list States size 2: " + listStates.size());
					}
					
					/*if(next.curOutput.length() > 0) {
						sopl("output: " + next.curOutput);
						sopl("goal: " + line);
					}*/
					
					if(next.curOutput.equals(line)) {
						return next.curInput;
					}
				}
			}
		}
		
		
		return "null";
	}
	
	public static boolean matches(prob21state curState, prob21state curState2) {
		
		
		for(int i=0; i<curState.robotsLocations.length; i++) {
			for(int j=0; j<curState.robotsLocations[0].length; j++) {
				
				if(curState.robotsLocations[i][j] != curState2.robotsLocations[i][j]) {
					return false;
				}
			}
		}
		
		if(curState.curOutput.equals(curState2.curOutput)) {
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
