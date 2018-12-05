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

public class prob5part2 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2018/prob2018in5.txt"));
			
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
			
			int origCount = 0;
			
			int size = line.length();
			int size2;
			int bestSize=99999999;

			for(int d=0; d<26; d++) {
				String dline = line.replaceAll((char)('a' + d) + "", "");
				dline = dline.replaceAll((char)('A' + d) + "", "");
				
				while(true) {
					size = dline.length();
					
					for(int c=0; c<26; c++) {
						//sop(size);
						dline = dline.replaceAll((char)('a' + c) + "" + (char)('A' + c) + "", "") ;
						dline = dline.replaceAll((char)('A' + c) + "" +  (char)('a' + c) + "", "");
						
						
					}
					size2 = dline.length();
					
					if(size == size2) { break;}
				}
				
				if(size < bestSize) {
					//sop("d");
					bestSize = size;
					sop('a' + d);
				}
			}

			
			sopl("Answer: " + bestSize);
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
