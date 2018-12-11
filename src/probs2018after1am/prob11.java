package probs2018after1am;

import java.io.File;

import number.IsNumber;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import utils.Mapping;

public class prob11 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2018/prob2018in11.txt"));
			
			int count = 0;
			boolean part2 = true;
			String line = "";

			LinkedList queue = new LinkedList();
			Stack stack = new Stack();
			HashSet set = new HashSet();
			
			Mapping dict = new Mapping();
			ArrayList <String>lines = new ArrayList<String>();
			
			
			int LIMIT = 20000;
			
			Hashtable<Long, Integer> trail = new Hashtable<Long, Integer>();
			
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				
				
			}
			
			int DIM = 300;
			int INPUT = pint(line);
			
			//5034
			int powerLevel[][] = new int[DIM + 1][DIM + 1];
			
			for(int i=1; i<=DIM; i++) {

				for(int j=1; j<=DIM; j++) {
					int rackId = j + 10;
					int pow = rackId * i;
					pow += INPUT;
					pow *= rackId;
					
					int hun = (pow /100) % 10;
					if(hun > 9 ) {
						exit(1);
					}
					
					pow = hun - 5;
					
					powerLevel[i][j] = pow;
				}
			}
			
			int answer = -20000;
			for(int i=1; i<=DIM; i++) {
				for(int j=1; j<=DIM; j++) {
					
					if(part2) {
						for(int sideLength = 1; sideLength + i <= DIM && sideLength + j <= DIM; sideLength++) {
							int current = 0;
							
							for(int i1=0; i1<sideLength; i1++) {
								for(int j1=0; j1<sideLength; j1++) {
									current += powerLevel[i + i1][j + j1];
								}
							}
						
							if(current> answer) {
								sop(j + "," + i + "," + sideLength);
								answer = current;
							}
						}
					} else {
						//part1:
						int current = 0;
						int sideLength = 3;
						
						for(int i1=0; i1<sideLength && i + i1 < DIM + 1; i1++) {
							for(int j1=0; j1<sideLength &&  j+ j1 < DIM + 1; j1++) {
								current += powerLevel[i + i1][j + j1];
							}
						}
					
						if(current> answer) {
							sop(j + "," + i);
							answer = current;
						}
					}
				}
					
			}
			
			
			sopl("Answer: " + answer);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public static void sop(Object a) {
		System.out.println(a.toString());
	}
	public static void sopl(Object a) {
		System.out.println(a.toString());
	}
	
	public static int pint(String s) {
		if (IsNumber.isNumber(s)) {
			return Integer.parseInt(s);
		} else {
			sop("Error: (" + s + " is not a number");
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
