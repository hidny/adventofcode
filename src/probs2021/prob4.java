package probs2021;
import java.io.File;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import number.IsNumber;
import utils.Mapping;
import utils.Sort;

public class prob4 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 //in = new Scanner(new File("in2021/prob2021in4.txt"));
			 in = new Scanner(new File("in2021/prob2021in4.txt"));
			int numTimes = 0;
			 
			int count = 0;
			boolean part2 = false;

			LinkedList queue = new LinkedList();
			Stack stack = new Stack();
			HashSet set = new HashSet();
			
			
			Hashtable<Long, Integer> trail = new Hashtable<Long, Integer>();
			
			ArrayList <String>lines = new ArrayList<String>();
			
			
			//int LIMIT = 20000;
			//boolean table[][] = new boolean[LIMIT][LIMIT];
			
			
			//dir: 0 up
			//1 right
			//2 down
			//3 left
			
			while(in.hasNextLine()) {
				String line = in.nextLine();
				lines.add(line);
				
				
			}
			
			
			ArrayList ints = new ArrayList<Integer>();
			
			String numsToken[] = lines.get(0).split(",");
			
			
			ArrayList<String> boards = new ArrayList<String>();
			
			int curRow = 0;
			
			String curBoard = "";
			
			for(int i=1; i<lines.size(); i++) {
				
				
				//int temp = Integer.parseInt(lines.get(i));
				//count+=temp;
				
				String line = lines.get(i);
				
				if(line.equals("")) {

					if(curBoard.length() > 0) {
						boards.add(curBoard.trim());
					}
					
					curRow = 0;
					curBoard = "";
					
					continue;
				} else {
					
					String line2 = line.replace("  ", " ").trim();
					curBoard += line2 + " ";
					//sopl(curBoard);
				}
				
				
			}
			
			//Forgot about last board...
			//That was the test bug. I bet I could've ignored it for the win!
			if(curBoard.length() > 0) {
				boards.add(curBoard.trim());
			}
			
			curRow = 0;
			curBoard = "";
			
			boolean picked[] = new boolean[1000];
			
			for(int i=0; i<numsToken.length; i++) {
				
				
				int numPicked = pint(numsToken[i]);
				picked[numPicked] = true;
				
				for(int j=0; j<boards.size(); j++) {
					
					String curBoard2[] = boards.get(j).split(" ");
					
					sopl(boards.get(j));
					
					boolean array[][] = new boolean[5][5];
					
					//Wasted time debugging this.
					sopl("Debug:");
					for(int k=0; k<curBoard2.length; k++) {
						if(k%5 == 0) {
							sopl();
						}
						if(picked[pint(curBoard2[k])]) {
							array[k/5][k%5] = true;
							sop(pint(curBoard2[k]) + " ");
						}
					}
					sopl();
					sopl();
					
					
					//horizontal
					boolean solutionFoundH = false;
					int horLineI = -1;
					
					for(int i2=0; i2<5; i2++) {
						solutionFoundH = true;
						for(int j2=0; j2<5; j2++) {
							if(array[i2][j2] == false) {
								solutionFoundH = false;
								break;
							}
							
						}
						
						if(solutionFoundH) {
							break;
						}
					}
					
					boolean solutionFoundV = false;
					//Vertical

					for(int j2=0; j2<5; j2++) {
						solutionFoundV = true;
						for(int i2=0; i2<5; i2++) {
							if(array[i2][j2] == false) {
								solutionFoundV = false;
								break;
							}
							
						}
						
						if(solutionFoundV) {
							break;
						}
					}
					
					if(solutionFoundV || solutionFoundH) {
						
						int sum = 0;
						for(int i2=0; i2<5; i2++) {
							for(int j2=0; j2<5; j2++) {
								if(picked[pint(curBoard2[i2*5 + j2])] == false) {
									sum+= pint(curBoard2[i2*5 + j2]);
								}
							}
							
						}
						long answer = sum * numPicked;

						sopl("number picked: " + numPicked);
						sopl("Answer: " + answer);
						
						System.exit(1);
					}
					
				}
			}
			
			sopl("Answer: " + count);
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
			sop("Error: (" + s + " is not a number");
			return -1;
		}
	}

	public static long plong(String s) {
		if (IsNumber.isLong(s)) {
			return Long.parseLong(s);
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
