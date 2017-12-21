package probs2017;

import java.io.File;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import utils.Mapping;

public class prob21 {

	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2017/prob2017in21.txt"));
			

			int count = 0;
			boolean part2 = false;
			String line = "";

			LinkedList queue = new LinkedList();
			Stack stack = new Stack();
			HashSet set = new HashSet();
			
			Mapping dict = new Mapping();
			ArrayList <String>lines = new ArrayList<String>();
			
			ArrayList <Integer> list = new ArrayList<Integer>();
			
			
			ArrayList <prob21Trans> trans = new ArrayList<prob21Trans>();
			
			boolean start[][] = new boolean[3][3];
			start[0][1] = true;
			start[1][2] = true;
			start[2][0] = true;
			start[2][1] = true;
			start[2][2] = true;
			
			printDetail(start);
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				String token[] = line.split(",");
				
				trans.add(new prob21Trans(line));
				
				
			}
			
			boolean current[][] = start;
			
			if(part2) {
				for(int i=0; i<18; i++) {
					current = prob21Trans.transform(trans, current);
				
					//print(current);
				}
			} else {
				for(int i=0; i<5; i++) {
					current = prob21Trans.transform(trans, current);
				
					printDetail(current);
				}
			}
			print(current);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	

	public static void printDetail(boolean table[][]) {
		int count = 0;
		for(int i=0; i<table.length; i++) {
			for(int j=0; j<table[0].length; j++) {
				if(table[i][j]) {
					System.out.print("#");
					count++;
				} else {
					System.out.print(".");
					
				}
			}
			System.out.println();
		}
		System.out.println("current number on: " + count);
	}

	public static void print(boolean table[][]) {
		int count = 0;
		for(int i=0; i<table.length; i++) {
			for(int j=0; j<table[0].length; j++) {
				if(table[i][j]) {
					//System.out.print("#");
					count++;
				} else {
					//System.out.print(".");
					
				}
			}
			//System.out.println();
		}
		System.out.println("Answer: " + count);
	}
}
