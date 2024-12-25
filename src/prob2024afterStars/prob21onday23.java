package prob2024afterStars;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import number.IsNumber;
import probs2024.prob21state2;

public class prob21onday23 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
				int TEST = 7;
				ArrayList<prob21state4> shortestSeqs = getShortestSequence(new prob21state4(TEST), line);
				ArrayList<prob21state4> shortestSeqs2 = getShortestSequence(new prob21state4(TEST - 1), line);
				
				sopl("ShortestSeqs:");
				for(int j=0; j<shortestSeqs.size(); j++) {
					shortestSeqs.get(j).printOutputEveryLevel();
				}
				
				if(! shortestSeqs.get(0).outputByDepth[TEST - 1].equals(shortestSeqs2.get(0).outputByDepth[TEST - 2])) {
					sopl("OOPS!");
					exit(1);
				}
				
				//At 7, it works:
				if(! shortestSeqs.get(0).outputByDepth[TEST - 2].equals(shortestSeqs2.get(0).outputByDepth[TEST - 3])) {
					sopl("OOPS 2!");
					exit(1);
				}
				
				testResult(shortestSeqs.get(0).curInput, TEST, line);

				//At 8, it works:
				//if(! shortestSeqs.get(0).outputByDepth[TEST - 3].equals(shortestSeqs2.get(0).outputByDepth[TEST - 4])) {
				//	sopl("OOPS 3!");
				//	exit(1);
				//}
				
				String testLongerSolutionFunction = shortestSeqs.get(0).outputByDepth[TEST - 2];
				sopl("Test testLongerSolutionFunction:");
				
				for(int i2=2; i2<TEST; i2++) {
					
					sopl("length at " + i2 + "th robot: " + testLongerSolutionFunction.length());
					sopl(testLongerSolutionFunction);
					testResult(testLongerSolutionFunction, i2, line);
					
					testLongerSolutionFunction =  getLongerSolutionBasedOnMatrix(testLongerSolutionFunction);
				}
				sopl("length at " + TEST + "th robot: " + testLongerSolutionFunction.length());
				testResult(testLongerSolutionFunction, TEST, line);
				sopl(testLongerSolutionFunction);

				//At 9?, it works?
				//if(! shortestSeqs.get(0).outputByDepth[TEST - 4].equals(shortestSeqs2.get(0).outputByDepth[TEST - 5])) {
				//	sopl("OOPS 4!");
				//	exit(1);
				//}
				
				cur += num * shortestSeqs.get(0).curInput.length();
			}
			
			
			sopl("Answer: " + cur);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
		
		
	}
	
	public static void testResult(String sequence, int numRobots, String expected) {
		
		prob21state2 curState = new prob21state2(numRobots);
		
		sopl("Test result:");
		String curOutput = "";
		for(int i=0; i<sequence.length(); i++) {
			curState = curState.makeMove(getMoveIndex(sequence.charAt(i)));
			
			if(curState.badState) {
				sopl("reached bad state at i2 = " + i);
				sopl(sequence.substring(0, i + 1));
				exit(1);
			}
			
			if(curState.curOutput.length() > curOutput.length()) {
				//sopl(sequence.substring(0, i + 1));
				//sopl(curState.curOutput);
				//sopl(sequence.substring(0, i + 1).length());
				curOutput = curState.curOutput;
			}
		}
		
		sopl("Output of sequence: " + curState.curOutput);
		
		if(curOutput.equals(expected)) {
			sopl("Test result passed");
		} else {
			sopl("Got " + curOutput + ", but expected: " + expected);
			sopl("Test result failed!");
			exit(1);
		}
		
	}


	//pre: instructions are for directing a robot that has the arrow key controls:
	public static String getLongerSolutionBasedOnMatrix(String input) {
		
		String output = "";
		
		sopl("getLongerSolutionBasedOnMatrix");
		
		char prev = 'A';
		char next;
		
		for(int i=0; i<input.length(); i++) {
		
			next = input.charAt(i);
			int previ = getiCoord(prev);
			int prevj = getjCoord(prev);
			
			int nexti = getiCoord(next);
			int nextj = getjCoord(next);
		
			if(prev == next) {
				
				if(next != '<' && next != 'A' && next != '>') {
					
					
					sopl("Oh no! By pen and paper, this should not happen.");
					sopl(input.substring(0, i+1));
					exit(1);
				}
				//sopl("PASS TEST");
				//pass
			} else {

			
				String tmpDir = "";
				//TODO: if going from ^ to <, it won't work...
				// But by pen and paper, it shouldn't happen...
			
				for(int i4=0; i4<nextj - prevj; i4++) {
					tmpDir += ">";
				}
			
				for(int i4=0; i4<prevj - nextj; i4++) {
					tmpDir += "<";
				}
			
				for(int i4=0; i4<nexti - previ; i4++) {
					tmpDir += "v";
				}
		
				for(int i4=0; i4<previ - nexti; i4++) {
					tmpDir += "^";
				}
				
				//Stop robot from going to top-left corner:
				if(tmpDir.equals("<<v")) {
					
					tmpDir = "v<<";
				}
				
				//Testing revealed that '^>' is better than '>^'
				/*if(output.endsWith("Av<<A") == false && tmpDir.equals(">^")) {

					//sopl("It happened!2");
					tmpDir = "^>";
				}*/
				
				if(tmpDir.equals(">v")) {
					//sopl("It happened!3");
					tmpDir = "v>";
				}
				
				if(tmpDir.equals("^>")) {
					//sopl("It happened!3");
					tmpDir = ">^";
				}
				
				//Let's avoid hitting top left corner:
				if(output.endsWith("A<A") && tmpDir.equals("<v")) {

					sopl("It happened!");
					tmpDir = "v<";
				}
				
				output += tmpDir;
			}
			output += "A";
			
			prev = next;
			
		}
		
		return output;
	}
	
	public static String rearrangeSequenceToBeRepetitive(String shortest) {
		
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

			for(int i=0; i<num[1]; i++) {
				output+=">";
			}
			for(int i=0; i<num[3]; i++) {
				output+="<";
			}
			for(int i=0; i<num[0]; i++) {
				output+="^";
			}

			for(int i=0; i<num[2]; i++) {
				output+="v";
			}
			
			output+="A";
			
			//TODO: if going from ^ to <, it won't work...
			// But by pen and paper, it shouldn't happen...
			
			curIndex = nextIndex + 1;
		}
		
		return output;
	}
	
	public static char nextChar[] = new char[]{'>', '^', 'v', '<', 'A'};
	
	public static int getMoveIndex(char move) {
		for(int i=0; i<nextChar.length; i++) {
			if(move == nextChar[i]) {
				return i;
			}
		}
		return -1;
	}

	public static ArrayList<prob21state4> getShortestSequence(prob21state4 curState, String line) {
		
		
		LinkedList<prob21state4> queue = new LinkedList<prob21state4>();
		HashSet<String> listStates = new HashSet<String>();
		
		queue.add(curState);
		listStates.add(curState.toString());
		
		ArrayList<prob21state4> ret = new ArrayList<prob21state4>();
		int curShortest = -1;
		
		while( ! queue.isEmpty()) {
			
			
			prob21state4 cur = queue.remove();
			
			for(int j=0; j<nextChar.length; j++) {
				prob21state4 next =  cur.makeMove(getMoveIndex(nextChar[j]));
				
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
							ret.add(next);
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
}
