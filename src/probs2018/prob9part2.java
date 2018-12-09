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

public class prob9part2 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2018/prob2018in9.txt"));
			
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
			
			//LinkedList<Integer> marbles = new LinkedList<Integer>();
			
			prob9marbles currentIndex = new prob9marbles(0);
			
			//int NUM_PLAYERS = 13 ;
			//int NUM_MARBLES = 7999 ;
			int NUM_PLAYERS = 418  ;
			int NUM_MARBLES =  7133900 ;
				
			int lastScore = 0;
			long points[] = new long[NUM_PLAYERS];
			
			
			int marbleNumber = 1;
			
			int playerNumber = 0;
			
			
			
			while(true) {
				lastScore = 0;
				
				
				if(marbleNumber > 0 && marbleNumber % 23 == 0) {
					//Diff
					lastScore = marbleNumber;
					
					prob9marbles marbleB4RM = currentIndex.prev.prev.prev.prev.prev.prev.prev.prev;

					lastScore += marbleB4RM.next.number;
					
					marbleB4RM.removeNext();
					
					
					currentIndex = marbleB4RM.next;
					
					
					points[playerNumber] += lastScore;
					
					
					
				} else {
					
					prob9marbles marbleB4ADD = currentIndex.next;
					
					marbleB4ADD.addNext(marbleNumber);
					
					currentIndex = marbleB4ADD.next;
					
				}
				
				playerNumber = (playerNumber + 1) % points.length;
				marbleNumber++;
				
				if(marbleNumber > NUM_MARBLES) {
					 break;
				}
				
			}
			
			int origCount = 0;
			
			long answer = 0;
			for(int i=0; i<points.length; i++) {
				if(points[i] > answer) {
					answer = points[i];
				}
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
