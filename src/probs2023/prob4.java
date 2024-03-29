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

public class prob4 {

	//day1 part 1
	//2:38.01
	
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2023/prob2023in4.txt"));
			//in = new Scanner(new File("in2023/prob2023in5.txt"));
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
			for(int i=0; i<lines.size(); i++) {
				
				
				line = lines.get(i);
				
				sopl(line);
				
				String tokens1[] = line.split(":");
				
				sopl(tokens1[1].split("\\|")[1].trim());
				String winning[] = tokens1[1].split("\\|")[0].trim().split(" ");
				String mine[] = tokens1[1].split("\\|")[1].trim().split(" ");
				
				int numMatches = 0;
				for(int i2=0; i2<winning.length; i2++) {
					for(int j=0; j<mine.length; j++) {
						
						if(winning[i2].equals("") || mine[j].equals("")) {
							continue;
						}
						if(pint(winning[i2]) == pint(mine[j])) {
							numMatches++;
						}
					}
				}
				
				if(numMatches > 0) {
					cur += Math.pow(2, numMatches - 1);
				}
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
	
	public static void exit() {
		exit(0);
	}
	public static void exit(int code) {
		sop("Exit with code " + code);
		
		System.exit(code);
	}

}
