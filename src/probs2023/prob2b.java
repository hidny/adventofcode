package probs2023;
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

public class prob2b {

	//day1 part 1
	//2:38.01
	
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2023/prob2023in2.txt"));
			//in = new Scanner(new File("in2023/prob2023in3.txt"));
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
			boolean table[][] = new boolean[LIMIT][LIMIT];
			
			
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
			int cur = 0;
			ArrayList ints = new ArrayList<Integer>();
			for(int i=0; i<lines.size(); i++) {
				
				
				line = lines.get(i);
				
				String tokens1[] = line.split(":");
				int gameId = pint(tokens1[0].split(" ")[1]);
				
				sopl(gameId);
				
				String tokens[] = tokens1[1].split(";");
				
				boolean possible = true;
				
				int max[] = new int[3];
				
				for(int j=0; j<tokens.length; j++) {
					
					
					
					String tokens2[] = tokens[j].split(";");
					
					for(int k=0; k<tokens2.length; k++) {
						
						String tokens3[] = tokens2[k].split(",");
						
						
						
						for(int m=0; m<tokens3.length; m++) {
							
							String tokens4[] = tokens3[m].trim().split(" ");
							sopl(tokens3[m]);
							int num = pint(tokens4[0]);
							String label = tokens4[1].trim();
							
							
							if(label.equals("red") && max[0] < num) {
								max[0] = num;
							} else if(label.equals("green") &&  max[1] < num) {
								max[1] = num;
							} else if(label.equals("blue") &&  max[2] < num) {
								max[2] = num;
							}
						}
					}
				}
				
				cur += max[0] * max[1] * max[2];
				
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
