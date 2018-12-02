package probs2018;

import java.io.File;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import utils.Mapping;
import number.IsNumber;
public class prob2part2 {

	
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
				sop(line);
				
				for(int i=0; i<lines.size()-1; i++) {
					
					//missingIndex
					for(int j=0; j<lines.get(i).length(); j++) {
						boolean matches = true;
						
						//curentIndex
						for(int k=0; k<lines.get(i).length(); k++) {
							
							if(j == k && lines.get(i).charAt(k) == line.charAt(k)) {
								matches = false;
								break;
							}
							
							if(j != k && lines.get(i).charAt(k) != line.charAt(k)) {
								matches = false;
								break;
							}
						}
						
						if(matches) {
							for(int k=0; k<lines.get(i).length(); k++) {
								
								if(j != k) {
									System.out.print(lines.get(i).charAt(k));
								}
							}
							System.out.println();
							System.exit(1);
						}
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
