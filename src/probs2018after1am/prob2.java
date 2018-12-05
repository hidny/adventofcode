package probs2018after1am;

import java.io.File;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import utils.Mapping;
import number.IsNumber;
public class prob2 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2018/prob2018in2.txt"));
			
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
			

			int numTwos = 0;
			int numThrees = 0;
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				
				dict = new Mapping();
				for(int i=0; i<line.length(); i++) {
					dict.set(line.charAt(i) + "", 1 + dict.get(line.charAt(i) + ""));
				}
				
				boolean twosFound =false;
				boolean threesFound = false;
				for(int i=0; i<26; i++) {
					if(dict.get("" + (char)((int)('a' + i))) == 2 && twosFound == false) {
						numTwos++;
						twosFound = true;
					} else if(dict.get("" + (char)((int)('a' + i))) == 3 && threesFound == false) {
						numThrees++;
						threesFound = true;
					}
				}
			}
			
			int checksum = numTwos * numThrees;
			
			int origCount = 0;
			for(int i=0; i<lines.size(); i++) {
			}
			
			sopl("Answer: " + checksum);
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

}
