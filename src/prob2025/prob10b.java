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

public class prob10b {

	
	public static void main(String[] args) {
		sopl("Start");
		Scanner in;
		try {
			in = new Scanner(new File("in2025/prob2025in10.txt"));
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
			
			long triangle[][] = createPascalTriangle(25);

			int most = 0;
			int most2 = 0;
			int most3 = 0;
			long cur = 0L;
			ArrayList ints = new ArrayList<Integer>();
			
			NEXT_LINE:
			for(int i=0; i<lines.size(); i++) {
				
				
				line = lines.get(i);
				
				int listCounters[] = getListCounters(line);
				
				String tokens[] = line.split(" ");
				
				long optionNumbers[] = new long[tokens.length -2];
				
				for(int j=1; j<tokens.length - 1; j++) {
					optionNumbers[j - 1] = getOptionNumber(tokens[j]);
					sopl("option number: " + optionNumbers[j-1]);
				}
				
				for(int numPresses=0; true; numPresses++) {
					sopl("Num presses: " + numPresses);
					boolean combo[] = new boolean[numPresses + optionNumbers.length - 1];
					
					for(int k=0; k<optionNumbers.length - 1; k++) {
						combo[k] = true;
					}
					
					NEXT_COMBO:
					while(combo != null) {
						
						int counterTrial[] = new int[listCounters.length];
						
						int indexOption = 0;
						for(int j=0; j<combo.length; j++) {
							
							if(combo[j]) {
								indexOption++;
							} else {
								
								for(int k=0; k<counterTrial.length; k++) {
									if( (optionNumbers[indexOption] & ( 1 << k)) != 0) {
										counterTrial[k]++;
										
										if(counterTrial[k] > listCounters[k]) {
											//sopl("boom");
											combo = utilsPE.Combination.getNextCombination(combo);
											continue NEXT_COMBO;
										}
									}
								}
							}
							
						}
						
						boolean good =true;
						for(int k=0; k<counterTrial.length; k++) {
							if(counterTrial[k] != listCounters[k]) {
								good = false;
							}
						}
						
						if(good) {
							cur+=numPresses;
							continue NEXT_LINE;
						}
						
						
						combo = utilsPE.Combination.getNextCombination(combo);
					}
				}
				
				/*
				for(int j=0; j<optionNumbers.length; j++) {

					sopl("j = " + j);
					boolean combo[] = new boolean[optionNumbers.length];
					for(int k=0; k<j; k++) {
						combo[k] = true;
					}
					
					while(combo != null) {
						
						long trialNum = 0L;
						
						for(int k=0; k<combo.length; k++) {
							if(combo[k]) {
								trialNum ^= optionNumbers[k];
							}
						}
						
						sopl("Trial num: " + trialNum);
						
						
						if(trialNum == targetNumber) {
							cur += j;
							continue NEXT_LINE;
						}
						
						combo = utilsPE.Combination.getNextCombination(combo);
					}
				}*/
				
			}


			sopl("Answer: " + cur);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public static int[] getListCounters(String line) {
		
		String list = line.split("\\{")[1].split("\\}")[0];
		
		String tokens[] = list.split(",");
		
		int ret[] = new int[tokens.length];
		
		for(int i=0; i<ret.length; i++) {
			ret[i] = pint(tokens[i]);
		}
		return ret;
	}

	public static long[][] createPascalTriangle(int size) {
		size = size+1;
		long pascalTriangle[][] = new long[size][size];
		
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				pascalTriangle[i][j] = 0;
			}
		}
		
		pascalTriangle[0][0] = 1;
				
		for(int i=1; i<size; i++) {
			for(int j=0; j<size; j++) {
				pascalTriangle[i][j] = pascalTriangle[i-1][j];
				if(j>0) {
					pascalTriangle[i][j] += pascalTriangle[i-1][j-1];
				}
			}
		}
		
		return pascalTriangle;
	}
	
	
	public static long getOptionNumber(String s) {
		
		sopl(s);
		String s2 = s.split("\\(")[1].split("\\)")[0];
		
		String tokens[] = s2.split(",");
		
		long ret=0L;
		
		for(int i=0; i<tokens.length; i++) {
			ret += Math.pow(2, pint(tokens[i]));
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

}
