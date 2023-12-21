package probs2023;
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

public class prob21b {

	//day1 part 1
	//2:38.01
	
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2023/prob2023in21.txt"));
			//in = new Scanner(new File("in2023/prob2023in0.txt"));
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
			long cur = 0L;
			ArrayList ints = new ArrayList<Integer>();
			for(int i=0; i<lines.size(); i++) {
				
				
				line = lines.get(i);
				
			}
			boolean table[][] = new boolean[lines.size()][lines.get(0).length()];
			boolean soFar[][] = new boolean[lines.size()][lines.get(0).length()];
			
			int startI = -1;
			int startJ = -1;
			
			for(int i=0; i<table.length; i++) {
				for(int j=0; j<table[0].length; j++) {
					
					soFar[i][j] = false;
					
					if(lines.get(i).charAt(j) == '#') {
						table[i][j] = true;
					} else if(lines.get(i).charAt(j) == 'S') {
						
						startI = i;
						startJ = j;
					} else {
						table[i][j] = false;
					}
					
				}
			}

			soFar[startI][startJ] = true;
			
			//4223
			cur = getAnswer(table, soFar, 0, 1000);
			
			
			sopl("Answer: " + cur);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}

	
	public static long getAnswer(boolean table[][], boolean soFar[][], int depth, int maxDepth) {
		
		
		long prevAnswers[] = new long[10000];
		

		HashSet<String> soFar2 = new HashSet<String>();
		for(int i=0; i<table.length; i++) {
			for(int j=0; j<table[0].length; j++) {
				
				if(soFar[i][j]) {
					soFar2.add(i + "," + j);
				}
				
			}
		}

		maxDepth = 1000;
		for(int d=0; d<maxDepth; d++) {
			
			if(d % 131 == 65) {
				sopl("Depth " + d + " size: " + soFar2.size());
			}
			
			HashSet<String> newList = new HashSet<String>();
			
			Iterator<String> it = soFar2.iterator();
			
			while(it.hasNext()) {
				String coord = (String)it.next();
				
				int i = pint(coord.split(",")[0]);
				int j = pint(coord.split(",")[1]);
				
				for(int i2=i-1; i2<=i+1 ; i2++) {
					for(int j2=j-1; j2<=j+1; j2++) {
						
						int i2tmp = ((i2 % table.length) + table.length) % table.length;
						int j2tmp = ((j2 % table[0].length) + table[0].length) % table[0].length;
						
						if((i2 != i && j2 != j) || (i2 == i && j2 == j)) {
							continue;
						}
						
						if(table[i2tmp][j2tmp] == false) {
							newList.add(i2 + "," + j2);
						}
					}
				}
				
			}
					
						

			soFar2 = newList;
			
			long cur = soFar2.size();
			
			//15505
			
			//TODO: it is prob quadratic...: (quadratic periodical)
			//It is quadratic:
			//The period is 131 and goal step (26501365) mod 31 == 65,
			// so focus on 65 mod 131:
			
			// based on a website with lots of ads:(not recommendded: https://www.analyzemath.com/parabola/three_points_para_calc.html )
			//Slightly better: https://www.mathway.com/Algebra
			//Even better: https://matrixcalc.org/slu.html#solve-using-Gaussian-elimination%28%7B%7B65%5E2,65,1,3944%7D,%7B196%5E2,196,1,35082%7D,%7B458%5E2,458,1,190388%7D%7D%29
			
			//3 points: (458, 190388); (196, 35082); (65, 3944)
			// answer = (15505 * x^2 + 32273 * x + 76614) / 17161
			
			// therefore, final answer is:
			// From wolfram: (15505 * x^2 + 32273 * x + 76614) / 17161;x = 26501365
			
			//I got global points! Rank 69! Nice!
			
			//I very smartly ran my program for too long and crashed my computer. I had to do the wolfram alpha calc and submission on my smart phone...
			
			
			
			//sopl("cur: " + cur);
			prevAnswers[d] = cur;
			
			if((d+1) % 131 == 65) {
				found:
				for(int d2=d-1; d2> 5 + d /2; d2--) {
					if((prevAnswers[d] -  prevAnswers[d2]) - (prevAnswers[d2] -  prevAnswers[d - 2*(d-d2)]) == 
							(prevAnswers[d - 1] -  prevAnswers[d2 - 1]) - (prevAnswers[d2 - 1] -  prevAnswers[d - 2*(d-d2) - 1])) {
						
						boolean stillGood = true;
						for(int k=2; k<20; k++) {
							if((prevAnswers[d] -  prevAnswers[d2]) - (prevAnswers[d2] -  prevAnswers[d - 2*(d-d2)]) != 
									(prevAnswers[d - k] -  prevAnswers[d2 - k]) - (prevAnswers[d2 - k] -  prevAnswers[d - 2*(d-d2) - k])) {
								stillGood = false;
							}
						}
						if(stillGood) {
							sopl("Found period? d = " + d);
							sopl("Period length: " + (d - d2));
							sopl("Diff: " + (prevAnswers[d] -  prevAnswers[d2]));
							
							long ret = 0L;
							for(int i=0; i<table.length; i++) {
								for(int j=0; j<table[0].length; j++) {
									if(soFar[i][j]) {
										ret++;
										sop("O");
									} else if(table[i][j]) {
										sop("#");
									} else {
										sop(".");
									}
								}
								sopl();
							}
							
							break found;
							/*
							 * Found period? d = 24
	Period length: 3
	Diff: 92
	
							 */
							
							
							
						}
					}
				}
			}
			
			
			
		}
		
		return soFar2.size();
	}
	
	//period: 131
	//26501365 % 131 == 65
	
	
	
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
