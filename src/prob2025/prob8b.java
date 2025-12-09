package prob2025;
import java.io.File;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

import number.IsNumber;
import utils.Mapping;
import utils.Sort;

public class prob8b {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2025/prob2025in8.txt"));
			//in = new Scanner(new File("in2025/prob2025in0.txt"));
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
			String linea;
			String lineb;
			distMem = new double[lines.size()][lines.size()];

			for(int i=0; i<lines.size(); i++) {
				for(int j=0; j<lines.size(); j++) {
					distMem[i][j] = -1.0;
				}
			}
			
			for(int i=0; i<lines.size(); i++) {
				linea = lines.get(i);
				for(int j=0; j<lines.size(); j++) {
					lineb = lines.get(j);
					
					getDist(linea, i, lineb, j);
				}
			}


			sopl("Answer: " + cur);
			in.close();
			
			boolean connections[][] = new boolean[lines.size()][lines.size()];
			int numNonTrivialConnections = 0;
			
			for(int numPairs=0; numNonTrivialConnections < lines.size() - 1; numPairs++) {
				
				
				double LowestDist = Double.POSITIVE_INFINITY;
				int bestI = -1;
				int bestJ = -1;
				
				for(int i=0; i<lines.size(); i++) {
					linea = lines.get(i);
					for(int j=i+1; j<lines.size(); j++) {
						lineb = lines.get(j);
						
						double curDist = getDist(linea, i, lineb, j);
						
						if(! connections[i][j] && curDist < LowestDist) {
							bestI = i;
							bestJ = j;
							LowestDist = curDist;
						}
					}
				}

				if(isNonTrivialConnection(connections, bestI, bestJ)) {
					
					sopl("isNonTrivialConnection: " + bestI + ", " + bestJ);
					numNonTrivialConnections++;
					
					if(numNonTrivialConnections == lines.size() - 1) {
						sopl("Answer: " + (plong(lines.get(bestI).split(",")[0]) * plong(lines.get(bestJ).split(",")[0])));
					}
				}
				connections[bestI][bestJ] = true;
				connections[bestJ][bestI] = true;
				
			}
			
			//sopl("Answer: " + (longest * secondLongest * thirdLongest));
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public static boolean isNonTrivialConnection(boolean connections[][], int bestI, int bestJ) {
		
		return isNonTrivialConnection(connections, bestI, bestJ, new boolean[connections.length]);
		
	}
	public static boolean isNonTrivialConnection(boolean connections[][], int curI, int bestJ, boolean used[]) {
		
		if(connections[curI][bestJ]) {
			return false;
		}
		used[curI] = true;
		
		for(int j=0; j<connections.length; j++) {
			
			if(connections[curI][j] && used[j] == false) {
				
				boolean ans = isNonTrivialConnection(connections, j, bestJ, used);
				if(ans == false) {
					return false;
				}
			}
		}
		
		return true;
		
	}
	
	public static boolean[] getSize(int rootIndex, HashSet <String> pairUsed, int length) {
		
		boolean tmpFound[] = new boolean[length];
		
		return getSize(rootIndex, pairUsed, tmpFound);
	}
	
	public static boolean[] getSize(int rootIndex, HashSet <String> pairUsed, boolean tmpFound[]) {
	
		int ret = 0;
		tmpFound[rootIndex] = true;
		ret++;
		
		Iterator <String>list = pairUsed.iterator();
		
		while(list.hasNext()) {
			String next = list.next();
			
			if(next.startsWith(rootIndex + ",")) {
				int neighIndex = pint(next.split(",")[1]);
				
				if(tmpFound[neighIndex] == false) {
					tmpFound = getSize(neighIndex, pairUsed, tmpFound);
				}
			}
			
		}
		
		
		return tmpFound;
	}
	
	public static double distMem[][];
	
	public static double getDist(String a, int i, String b, int j) {
		
		if(distMem[i][j] >= 0.0) {
			return distMem[i][j];
		}
		
		long coorda[] = new long[3];
		long coordb[] = new long[3];
		
		String tokensA[] = a.split(",");
		String tokensB[] = b.split(",");
		
		for(int i2=0; i2<coorda.length; i2++) {
			coorda[i2] = plong(tokensA[i2]);
			coordb[i2] = plong(tokensB[i2]);
		}
		
		distMem[i][j] = Math.sqrt(
				  (coorda[0] - coordb[0]) * (coorda[0] - coordb[0])
				+ (coorda[1] - coordb[1]) * (coorda[1] - coordb[1])
				+ (coorda[2] - coordb[2]) * (coorda[2] - coordb[2])
				);
		
		distMem[j][i] = distMem[i][j];
		
		return distMem[i][j];
		
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

}
