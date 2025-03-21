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
import utils.Mapping;
import utils.Sort;

public class prob22b4after {

	//day1 part 1
	//2:38.01
	
	
	//Lol! Just guessed without doing the full search!
	
	//AAHHH! I could've just put the diffs with prices in a hashtable with only 19^4 elements...
	
	
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2024/prob2024in22.txt"));
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
			
			long listOfNums[] = new long[lines.size()];
			for(int i=0; i<lines.size(); i++) {
				listOfNums[i] = plong(lines.get(i)); 
			}

			int most = 0;
			int most2 = 0;
			int most3 = 0;
			
			long cur = 0L;
			
			int MULT_OF_TEN = 0;
			
			sopl("start fishing index: " + (10*MULT_OF_TEN));
			//1786
			ArrayList <String> attemptsToTry = new ArrayList <String>();
			for(int i=0; i<2; i++) {
				
				
				long first = listOfNums[10 * MULT_OF_TEN + i];
				

				long prevPrices[] = new long[2001];
				prevPrices[0] = first;
				
				long prevPricesChanges[] = new long[2000];
				
				for(int j=0; j<2000; j++) {
					long next = nextNum(first);

					prevPrices[j + 1] = next%10;
					
					prevPricesChanges[j] = (next%10) - (first%10);
					
					//sop(prevPricesChanges[j] + ",");
					
					first = next;
				}
				
				for(int j=3; j<2000; j++) {

					String attempt = "";
					for(int k=0; k<4; k++) {
						
						attempt += prevPricesChanges[j-3+k] + ",";
						
					}
					attempt = attempt.substring(0, attempt.length() - 1);
					
					attemptsToTry.add(attempt);
					
				}
				
			}
			
			
			//1910?
					
			NEXT_COMBO:
			for(int i2=0; i2 < attemptsToTry.size(); i2++) {
				
				if(i2 % 100 == 0) {
					sopl(i2);
				}
				
				String tokens[] = attemptsToTry.get(i2).split(",");
				int ret[] = new int[4];	
				for(int i=0; i<ret.length; i++) {
					ret[i] = pint(tokens[i]);
				}
				
				int movement = 0;

				int maxMove = 9 -(-9);
				for(int j=0; j<ret.length; j++) {
					movement += ret[j];
					//if(Math.abs(movement) > maxMove ) {
					if(Math.abs(movement) > maxMove ) {
						//sopl("next combo");
						continue NEXT_COMBO;
					}
				}
				
				
				
				long trialPrices = 0L;
				
				ArrayList ints = new ArrayList<Integer>();
				for(int i=0; i<lines.size(); i++) {
					
					
					
					
					long first = listOfNums[i];
					

					long prevPrices[] = new long[2001];
					prevPrices[0] = first;
					
					long prevPricesChanges[] = new long[2000];
					
					for(int j=0; j<2000; j++) {
						long next = nextNum(first);

						prevPrices[j + 1] = next%10;
						
						prevPricesChanges[j] = (next%10) - (first%10);
						
						//sop(prevPricesChanges[j] + ",");
						
						first = next;
					}
					//sopl();
					
					long curPrice = 0;
					
					for(int j=3; j<2000; j++) {

						boolean nope = false;
						
						for(int k=0; k<ret.length; k++) {
							if(ret[k] != prevPricesChanges[j-3+k]) {
								nope = true;
								break;
							}
						}
						
						if(nope == false) {
							curPrice = prevPrices[j + 1];
							break;
						}
					}
					
					//sopl("cur Price: " + curPrice);
					trialPrices += curPrice;
					
				}
				
				if(trialPrices > cur) {
					cur = trialPrices;
					sopl("Current answer: " + cur);
				}
				
			}
			

			sopl("Answer: " + cur);
			in.close();
			
			
			long secretNum = 123;
			
			for(int i=0; i<10; i++) {
				secretNum = nextNum(secretNum);
				sopl(secretNum);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public static int[] getPotentialSequence(int index) {
		
		int ret[] = new int[4];
		
		int cur = index;
		for(int i=0; i<ret.length; i++) {
			
			int next = -9 + (cur % 19);
			
			ret[i] = next;
			cur /= 19;
		}
		
		if(cur >= 1) {
			return null;
		}
		
		return ret;
		
	}
	
	public static int PRUNE_NUM = 16777216;
	public static long nextNum(long num) {
		
		long numtimes64 = 64 * num;
		
		long mixed = numtimes64 ^ num;
		
		num = mixed % PRUNE_NUM;
		
		long div = num / 32;
		
		long mixed2 = div ^ num;
		
		num = mixed2 % PRUNE_NUM;
		
		
		long mult2 = 2048 * num;
		
		long mixed3 = mult2 ^ num;
		
		return mixed3  % PRUNE_NUM;
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
