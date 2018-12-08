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

public class prob6 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2018/prob2018in6.txt"));
			
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
			for(int i=0; i<lines.size(); i++) {
				
				
			}
			
			int array[] = new int[lines.size()];
			
			boolean infinite[] = new boolean[lines.size()];
			
			for(int i=-1000; i<1000; i++) {
				for(int j=-1000; j<=1000; j+=2000) {
					int p = getClosest(lines, i, j);
					
					if(p>=0) {
						infinite[p] = true;
					}
				}
			}
			

			for(int j=-1000; j<1000; j++) {
				for(int i=-1000; i<=1000; i+=2000) {
					int p = getClosest(lines, i, j);
					
					if(p>=0) {
						infinite[p] = true;
					}
				}
			}
			
			
			for(int i=-100; i<1000; i++) {
				for(int j=-100; j<1000; j++) {
					int p = getClosest(lines, i, j);
					if(p>=0){
						array[p]++;
					}
					
				}
				
			}
			
			int answer = 0;
			for(int i=0; i<lines.size(); i++) {
				if(infinite[i] == false) {
					int current = array[i];
					if(current > answer) { 
						answer = current;
					}
				}
			}
			
			
			sopl("Answer: " + answer);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	
	
	
	//return -1 on tie.
	public static int getClosest(ArrayList <String>lines, int i, int j) {
		int bestIndex = 0;
		
		int bestDistance = -1;
		
		
		for(int p=0; p<lines.size(); p++) {
			int iy = pint(lines.get(p).split(",")[1].trim());
			int jx = pint(lines.get(p).split(",")[0].trim());
			
			int currentDist = Math.abs(i - iy) + Math.abs(j - jx);
			
			if(p ==0) {
				bestDistance = currentDist;
			} else if(bestDistance == currentDist) {
				bestIndex = -1;
			} else if(currentDist < bestDistance) {
				bestDistance = currentDist;
				bestIndex = p;
			}
			
		}
		
		return bestIndex;
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
