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

public class prob8 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2018/prob2018in8.txt"));
			
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
				sop(line);
				
			}
			
			
			int origCount = 0;
			for(int i=0; i<lines.size(); i++) {
			}
			
			count = sum(line, 0);
			
			sopl("Answer: " + count);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	
	public static int sum(String line, int index) {
		return sum(line.split(" "), index); 
	}
	
	public static int currentIndex = 0;
	
	public static int sum(String line[], int index) {
		//int numChildren = 
		int numChildren = pint(line[index]);
		
		int numMetaData = pint(line[index+1]);
		
		currentIndex = index +2;
		int ret = 0;
		for(int i=0; i<numChildren; i++) {
			ret += sum(line, currentIndex);
		}
		
		for(int i=0; i<numMetaData; i++) {
			ret += pint(line[currentIndex + i]);
			
		}
		currentIndex = currentIndex + numMetaData;
		
		return ret;
		
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
