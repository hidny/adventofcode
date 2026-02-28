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

public class prob4c {

	//https://flipflop.slome.org/demo
	
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("inflipflop2025/prob2025in4.txt"));
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
			long cur = 0L;
			ArrayList ints = new ArrayList<Integer>();

			int digits[] = new int[10];
			
			int prevI = 0;
			int prevJ = 0;

			BACK_TO_I:
			for(int i=0; i<lines.size(); i++) {
				String lineI = lines.get(i);
				String tokens[] = lineI.split(",");
				int icoord1 = pint(tokens[0]);
				int icoord2 = pint(tokens[1]);
				int sumI = icoord1 + icoord2;
				
				for(int j=i+1; j<lines.size(); j++) {
					String lineJ = lines.get(j);
					String tokens2[] = lineJ.split(",");
					int jcoord1 = pint(tokens2[0]);
					int jcoord2 = pint(tokens2[1]);
					
					int sumJ = jcoord1 + jcoord2;
					
					if(sumJ < sumI) {
						String swap = lineI;
						lines.set(i, lineJ);
						lines.set(j, lineI);
						sopl("Swap!");
						i--;
						continue BACK_TO_I;
					}
					
				}
			}
			
			for(int i=0; i<lines.size(); i++) {
				
				
				line = lines.get(i);
				
				String tokens[] = line.split(",");
				int newI = pint(tokens[0]);
				int newJ = pint(tokens[1]);
				
				cur += Math.max(Math.abs(newI - prevI), Math.abs(newJ - prevJ));
				prevI = newI;
				prevJ = newJ;
				
				
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
		char grid[][] = new char[lines.size()][lines.get(0).length()];
		
		for(int i=0; i<lines.size(); i++) {
			
			for(int j=0; j<grid[0].length; j++) {
				grid[i][j] = lines.get(i).charAt(j);

			}
		}
		
		return grid;
	}

}
