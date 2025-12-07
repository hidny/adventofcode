package prob2025;
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
import java.util.Iterator;

public class prob2b {

	//day1 part 1
	//2:38.01
	
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2025/prob2025in2.txt"));
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
			
			boolean invalidIdsPart2[][] = new boolean[20][100000];
			
			
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
				
				String tokens[] = line.split(",");
				
				for(int j=0; j<tokens.length; j++) {
					String tokens2[] = tokens[j].split("-");
					sopl();
					sopl(tokens[j]);
					
					long lhs = plong(tokens2[0]);
					long rhs = plong(tokens2[1]);
					
					
					for(int m=2; m<10; m++) {
						
						int minNumDigits = tokens2[0].length() / m;
						int maxNumDigits = tokens2[1].length() / m;
						
						for(int k=(int)Math.pow(10, minNumDigits-1); k<Math.pow(10, maxNumDigits); k++) {
							
							String potNumS = "";
							for(int n=0; n<m; n++) {
								potNumS += (k + "");
							}
							if(potNumS.length() > 18) {
								break;
							}
							long potNum = plong(potNumS);
							
							if(potNum >= lhs && potNum <= rhs) {
								invalidIdsPart2[m][k] = true;
								sopl(potNum);
							}
						}
					}
					
				}
			}

			
			HashSet<Long> invalids = new HashSet<Long>();
			for(int i=0; i<invalidIdsPart2.length; i++) {
				
				for(int j=0; j<invalidIdsPart2[0].length; j++) {
					
					if(invalidIdsPart2[i][j]) {
						
						String potNumS = "";
						for(int n=0; n<i; n++) {
							potNumS += (j + "");
						}
						if(potNumS.length() > 18) {
							break;
						}
						long potNum = plong(potNumS);
						
						invalids.add(potNum);
						
					}
				}
			}
			
			Iterator<Long> list = invalids.iterator();
			
			while(list.hasNext()) {
				cur += list.next().longValue();
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
