package flipflop2025;
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

public class prob7c {

	//https://flipflop.slome.org/demo
	
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("inflipflop2025/prob2025in7.txt"));
			//in = new Scanner(new File("inflipflop2025/prob2025in0.txt"));
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
			int table342[] = new int[LIMIT];
			
			
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
			long cur = 0;
			ArrayList ints = new ArrayList<Integer>();

			int digits[] = new int[10];
			
			createPascalsTriangle(100);
			
			for(int i=0; i<lines.size(); i++) {
				
				sopl();
				sopl(line);
				
				line = lines.get(i);
				String tokens[] = line.split(" ");
				int a = pint(tokens[0]);
				int b = pint(tokens[1]);
				
				long tmp = getAnswer3(a, b);
				
				sopl("Answer for line: " + tmp);
				cur += tmp;
			}

			
			sopl("Answer: " + cur);
			
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public static Hashtable<String, Long> keys = new Hashtable<String, Long>();
	
	public static long getAnswer3(int dims, int size) {
		
		int coord[] = new int[dims];
		keys = new Hashtable<String, Long>();
		keys.put(convertToString(coord), 1L);
		//sopl("Put: " + convertToString(coord));
		
		return getAnswer3(dims, size, coord);
		
	}
	
	public static long getAnswer3(int dims, int size, int coord[]) {
		
		int curDimIndex;
		
		while(true) {
			curDimIndex = dims - 1;
			
			//Increment:
			coord[curDimIndex]++;
			while(coord[curDimIndex] >= size) {
				coord[curDimIndex] = 0;
				curDimIndex--;
				
				if(curDimIndex < 0) {
					return getAnswerPart3(dims, size);
				}
				coord[curDimIndex]++;
			}
			
			if(keys.containsKey(convertToString(coord))) {
				sopl("Doh!");
				exit(1);
			}
			
			long answerForCell = 0L;
			for(int j=0; j<dims; j++) {
				coord[j]--;
				//sopl("Convert to string: " + convertToString(coord));
				if(keys.containsKey(convertToString(coord))) {
					//sopl("Let us go: ");
					answerForCell += keys.get(convertToString(coord));
				}
				coord[j]++;
			}
			sopl(convertToString(coord) + " - > " + answerForCell);
			keys.put(convertToString(coord), answerForCell);
			
		}
		
	}
	
	public static long getAnswerPart3(int dims, int size) {
		String key = "";
		for(int i=0; i<dims; i++) {
			key += (size-1) + ",";
		}
		return keys.get(key);
	}
	
	public static String convertToString(int coord[]) {
		String ret = "";
		for(int i=0; i<coord.length; i++) {
			ret += coord[i] + ",";
		}
		return ret;
	}
	
	public static long getAnswer2(int x, int y, int z) {
		
		long coord[][][] = new long[x][y][z];
		coord[0][0][0] = 1L;
		
		for(int i=0; i<x; i++) {
			for(int j=0; j<y; j++) {
				for(int k=0; k<z; k++) {
					
					if(i > 0) {
						coord[i][j][k] += coord[i-1][j][k];
					}
					if(j > 0) {
						coord[i][j][k] += coord[i][j-1][k];
					}
					if(k > 0) {
						coord[i][j][k] += coord[i][j][k-1];
					}
				}
			}
		}
		
		return coord[x-1][y-1][z-1];
	}
	
	public static double pascalsTriangle[][] = null;
	public static final double TWO = 2.0;
	
	public static void createPascalsTriangle(int n) {
		
		pascalsTriangle = new double[n+1][n+1];
		
		for(int i=0; i<pascalsTriangle.length; i++) {
			for(int j=0; j<pascalsTriangle[0].length; j++) {
				pascalsTriangle[i][j] = 0.0;
			}
		}
		
		pascalsTriangle[0][0] = 1.0;
		
		for(int i=1; i<pascalsTriangle.length; i++) {
			for(int j=0; j<pascalsTriangle[0].length; j++) {
				
				if(j == 0) {
					pascalsTriangle[i][j] = 1.0;
				} else {
					pascalsTriangle[i][j] = pascalsTriangle[i - 1][j] + pascalsTriangle[i - 1][j - 1];
				}
				
				//System.out.print(pascalsTriangle[i][j] + "    ");
			}
			//System.out.println();
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
		char grid[][] = new char[lines.size()][lines.get(0).length()];
		
		for(int i=0; i<lines.size(); i++) {
			
			for(int j=0; j<grid[0].length; j++) {
				grid[i][j] = lines.get(i).charAt(j);

			}
		}
		
		return grid;
	}

}
