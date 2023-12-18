package probs2023;
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

public class prob14b2 {

	//day1 part 1
	//2:38.01
	
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2023/prob2023in14.txt"));
			//in = new Scanner(new File("in2023/prob2023in4.txt"));
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
			ArrayList ints = new ArrayList<Integer>();
			for(int i=0; i<lines.size(); i++) {
				
				
				line = lines.get(i);
			}
			
			int table[][] = new int[lines.size()][lines.get(0).length()];
			
			for(int i=0; i<lines.size(); i++) {
				for(int j=0; j<lines.get(0).length(); j++) {

					if(lines.get(i).charAt(j) == '.') {
						table[i][j] = 0;
					} else if(lines.get(i).charAt(j) == '#') {
						table[i][j] = 1;
					} else if(lines.get(i).charAt(j) == 'O') {
						table[i][j] = 2;
					}
				}
			}
			
			long cur = 0L;
			
			
			int curIndex = 0;
			int answers[] = new int[100000];
			for(int i=0; i<3818; i++) {
			//for(int i=0; i<7013; i++) {
				//Go North:
				table = north(table);
				
				//printTable(table);
	
				table = west(table);
				
				//printTable(table);
	
				table = south(table);
				
				//printTable(table);
	
				table = east(table);

				//printTable(table);


				//int tableTemp[][] = north(table);
				
				if(i==2) {
					//exit(1);
				}
				
				if(i % 1000 == 0) {
					sopl(getAnswer(table));
					
					
				}

				answers[curIndex] = (int)getAnswer(table);

				sopl("table tmp");
				sopl(answers[curIndex]);
				sopl("end table tmp");
				
				int numAnswers = 0;
				for(int k=1; curIndex - k > 3 && numAnswers <4; k++) {
					if(answers[curIndex-k] == answers[curIndex] 
							&& answers[curIndex-k - 1] == answers[curIndex - 1]
							&& answers[curIndex-k - 2] == answers[curIndex - 2]) {
						sopl("Possible period of " + k);
						numAnswers++;
						
						
						
						
					}
				}
				
				curIndex++;
			}
			
			//112679
			
			//112651
			
			//104405: nope!
			
			//104409

			sopl("Answer: " + answers[curIndex-1]);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public static long getAnswer(int table[][]) {
		long cur = 0L;
		
		//table.length - i;
		
		for(int j=0; j<table[0].length; j++) {
			
			
			for(int i=0; i<table.length; i++) {
				
				if(table[i][j] == 0) {
				} else if(table[i][j] == 2) {
					
					cur += (table.length - i);
					
				} else if(table[i][j] == 1) {
					
				}
			}
			//TODO: act as if 1
		}
		
		return cur;
	}

	public static int[][] north(int table[][]) {
		int newTable[][] = new int[table.length][table[0].length];
		
		for(int j=0; j<table[0].length; j++) {
			
			int startI = 0;
			int numRounded = 0;
			
			for(int i=0; i<table.length; i++) {
				
				if(table[i][j] == 0) {

					newTable[i][j] = 0;
					
				} else if(table[i][j] == 1) {
					
					for(int k=0; k<numRounded; k++) {
						
						newTable[startI + k][j] = 2;
					}
					
					numRounded = 0;
					startI = i + 1;

					newTable[i][j] = 1;
					
				} else if(table[i][j] == 2) {
					numRounded++;
				}
			}
			
			for(int k=0; k<numRounded; k++) {
				newTable[startI + k][j] = 2;
			}
			
			//TODO: act as if 1
		}
		
		return newTable;
	}
	
	
	public static int[][] south(int table[][]) {
		int newTable[][] = new int[table.length][table[0].length];
		
		for(int j=0; j<table[0].length; j++) {
			
			int startI = table.length - 1;
			int numRounded = 0;
			
			for(int i=table.length - 1; i>=0; i--) {
				
				if(table[i][j] == 0) {

					newTable[i][j] = 0;
					
				} else if(table[i][j] == 1) {
					
					for(int k=0; k<numRounded; k++) {
						
						newTable[startI - k][j] = 2;
					}
					
					numRounded = 0;
					startI = i - 1;

					newTable[i][j] = 1;
					
				} else if(table[i][j] == 2) {
					numRounded++;
				}
			}
			
			for(int k=0; k<numRounded; k++) {
				newTable[startI - k][j] = 2;
			}
			
			//TODO: act as if 1
		}
		
		return newTable;
	}
	
	
	public static int[][] east(int table[][]) {
		int newTable[][] = new int[table.length][table[0].length];
		
		for(int i=0; i<table.length; i++) {
			
			int startJ = table[0].length - 1;
			int numRounded = 0;
			
			for(int j=table[0].length - 1; j>=0; j--) {
				
				if(table[i][j] == 0) {

					newTable[i][j] = 0;
					
				} else if(table[i][j] == 1) {
					
					for(int k=0; k<numRounded; k++) {
						
						newTable[i][startJ - k] = 2;
					}
					
					numRounded = 0;
					startJ = j - 1;

					newTable[i][j] = 1;
					
				} else if(table[i][j] == 2) {
					numRounded++;
				}
			}
			
			for(int k=0; k<numRounded; k++) {
				
				newTable[i][startJ - k] = 2;
			}
			
			//TODO: act as if 1
		}
		
		return newTable;
	}
	
	public static int[][] west(int table[][]) {
		int newTable[][] = new int[table.length][table[0].length];
		
		for(int i=0; i<table.length; i++) {
			
			int startJ = 0;
			int numRounded = 0;
			
			for(int j=0; j<table[0].length; j++) {
				
				if(table[i][j] == 0) {

					newTable[i][j] = 0;
					
				} else if(table[i][j] == 1) {
					
					for(int k=0; k<numRounded; k++) {
						
						newTable[i][startJ + k] = 2;
					}
					
					numRounded = 0;
					startJ = j + 1;

					newTable[i][j] = 1;
					
				} else if(table[i][j] == 2) {
					numRounded++;
				}
			}
			
			for(int k=0; k<numRounded; k++) {
				
				newTable[i][startJ + k] = 2;
			}
			
			//TODO: act as if 1
		}
		
		return newTable;
	}
	
	public static void printTable(int table[][]) {
		for(int i=0; i<table.length; i++) {
			for(int j=0; j<table[0].length; j++) {
				if(table[i][j] == 0) {
					sop('.');
				} else if(table[i][j] == 1) {
					sop("#");
				} else if(table[i][j] == 2) {
					sop("O");
				}
			}
			sopl();
		}
		sopl();
		sopl();
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
	
	public static void exit() {
		exit(0);
	}
	public static void exit(int code) {
		sop("Exit with code " + code);
		
		System.exit(code);
	}

}
