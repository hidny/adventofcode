package probs2022;
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

public class prob7Stuff {

	public String name = "";
	public int amount=0;
	public boolean isDir= false;
	
	public ArrayList<prob7Stuff> stuff = new ArrayList<prob7Stuff>();
	
	public prob7Stuff(String name, int amount, boolean isDir) {
		super();
		this.name = name;
		this.amount = amount;
		this.isDir = isDir;
	}
	
	
	public static void printTree(prob7Stuff root) {
		printTree(root, 0);
	}
	
	public static void printTree(prob7Stuff root, int depth) {
		
		String space = "";
		for(int i=0; i<depth; i++) {
			space += " ";
		}
		
		if(root.isDir) {
			sopl(root.name);
			
			for(int i=0; i<root.stuff.size(); i++) {
				printTree(root.stuff.get(i), depth + 1);
			}
		} else {
			sopl(root.amount + " " + root.name);
		}
		
		
		
		
	}
	
	
	public static long answer = 0;
	

	public static long sumTree(prob7Stuff root, int depth) {
		
		long sum = 0;
		
		String space = "";
		for(int i=0; i<depth; i++) {
			space += " ";
		}
		
		if(root.isDir) {
			sopl(root.name);
			
			for(int i=0; i<root.stuff.size(); i++) {
				long tmp =sumTree(root.stuff.get(i), depth + 1);
				sum += tmp;
			}
			
			
			
		} else {
			sopl(root.amount + " " + root.name);
			
			sum += root.amount;
		}
		
		
		if(sum < 100000 && root.isDir) {
			answer += sum;
			sopl("HERE: " + sum);
		}
		
		return sum;
		
		
	}
	
	public static long closestToTarget = 100000000L;
	
	public static long findClosestOverTarget(prob7Stuff root, int depth, long minToDelete) {
		
		long sum = 0;
		
		String space = "";
		for(int i=0; i<depth; i++) {
			space += " ";
		}
		
		if(root.isDir) {
			sopl(root.name);
			
			for(int i=0; i<root.stuff.size(); i++) {
				long tmp =sumTree(root.stuff.get(i), depth + 1);
				findClosestOverTarget(root.stuff.get(i), depth, minToDelete);
				sum += tmp;
			}
			
			
			
		} else {
			sopl(root.amount + " " + root.name);
			
			sum += root.amount;
		}
		
		
		if(root.isDir) {
			if(sum >= minToDelete) {
				if(sum < closestToTarget ) {
					closestToTarget = sum;
					sopl("HELLO: " + closestToTarget);
				}
				
			}
			
			sopl("......"+ sum);
		}
		
		return sum;
		
		
	}

	

	public static void sopl(Object a) {
		System.out.println(a.toString());
	}

	public static void sopl() {
		System.out.println();
	}
}
