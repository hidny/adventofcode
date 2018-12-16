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

public class prob11part2Ben {

	
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
			
			int powerLevelSquare[][][] = new int[DIM + 1][DIM + 1][DIM + 1];
			
			powerLevelSquare[0] = powerLevel;
			
			String answer = "";
			
			int maxPower = -20000;
			for(int sideLength = 1; sideLength <= DIM && sideLength <= DIM; sideLength++) {
				for(int i=1; i<=DIM - sideLength + 1; i++) {
					for(int j=1; j<=DIM -sideLength + 1; j++) {
					
							int current = 0;
							if(sideLength == 1) {
								current = powerLevelSquare[0][i][j];
							} else {
								current = powerLevelSquare[sideLength - 2][i][j];
								for(int i1=0; i1<sideLength; i1++) {
									current += powerLevel[i + i1][j + sideLength - 1];
								}
								for(int j1=0; j1<sideLength; j1++) {
									current += powerLevel[i + sideLength - 1][j + j1];
								}
								
								current -= powerLevel[i + sideLength - 1][j + sideLength - 1];
								
								powerLevelSquare[sideLength - 1][i][j] = current;
							}
							
							
							if(current> maxPower) {
								answer = j + "," + i + "," + sideLength;
								sop(answer);
								
								maxPower = current;
							}
					
					}
				}
			}
			
			
			sopl("maxPower: " + maxPower);
			sopl("answer: " + answer);
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
