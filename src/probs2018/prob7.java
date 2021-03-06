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

public class prob7 {

	//2016->prob24pos for grid used on A*
	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2018/prob2018in7.txt"));
			
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
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				
			}
			
			
			String output = "";
			
			
			boolean done[] = new boolean[26];
			
			boolean hasRequirement[];
			
			
			int origCount = 0;
			
			boolean progress = true;
			
			while(progress) {
				progress = false;
				hasRequirement = new boolean[26];
				for(int i=0; i<lines.size(); i++) {
					int waiter =(int)((lines.get(i).split(" ")[7].charAt(0)-'A'));
					int req = (int)((lines.get(i).split(" ")[1].charAt(0)-'A'));
					
					if(done[req] == false) {
						hasRequirement[waiter] = true;
					}
				}
				
				boolean contestant[] = new boolean[26];
				for(int i=0; i<26; i++) {
					if(done[i] == false && hasRequirement[i] == false) {
						hasRequirement[i] = false;
						
						progress = true;
						contestant[i] = true;
						//output += (char)('A' + i) + "";
					}
				}
				
				
				for(int i=0; i<26; i++) {
					if(contestant[i]) {
						done[i] = true;
						output += (char)('A' + i) + "";
						System.out.println(output);
						break;
					}
				}
			}
			
			
			sopl("Answer: " + output);
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
