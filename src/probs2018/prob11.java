package probs2018;

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
			boolean part2 = false;
			String line = "";

			LinkedList queue = new LinkedList();
			Stack stack = new Stack();
			HashSet set = new HashSet();
			
			Mapping dict = new Mapping();
			ArrayList <String>lines = new ArrayList<String>();
			
			
			int LIMIT = 20000;
			boolean table[][] = new boolean[LIMIT][LIMIT];
			
			Hashtable<Long, Integer> trail = new Hashtable<Long, Integer>();
			
			//dir: 0 up
			//1 right
			//2 down
			//3 left
			
			//rack id
			//x coord + 10
			
			//rackid * y coord
			//mult + grid serial num
			
			
			//pow = pow * rackid
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				
				
			}
			
			//int DIM = 300;
			//int INPUT = 5034;
			int DIM = 300;
			int INPUT = 5034;
			
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
					
					
					for(int limit = 1; limit + i <= DIM && limit + j <= DIM; limit++) {
						int current = 0;
						
						for(int i1=0; i1<limit; i1++) {
							for(int j1=0; j1<limit; j1++) {
								current += powerLevel[i + i1][j + j1];
							}
						}
					
						if(current> answer) {
							sop(j + "," + i + "," + limit);
							answer = current;
						}
					}
				}
					
			}
			
			
			//229,251,20
			
			int origCount = 0;
			for(int i=0; i<lines.size(); i++) {
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
