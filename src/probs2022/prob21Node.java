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

public class prob21Node {

	public boolean isLeaf = false;
	long num = 0L;
	
	public prob21Node leaves[] = new prob21Node[2];
	
	public String label = "";
	public String labels[] = new String[2];
	public char operation = 'N';
	

	public static long getCalc(prob21Node root) {
		if(root.isLeaf) {
			return root.num;
		} else {
			if(root.operation == '+') {
				return getCalc(root.leaves[0]) + getCalc(root.leaves[1]);
				
			} else if(root.operation == '-') {
				return getCalc(root.leaves[0]) - getCalc(root.leaves[1]);
				
			} else if(root.operation == '*') {
				return getCalc(root.leaves[0]) * getCalc(root.leaves[1]);
				
			} else if(root.operation == '/') {
				return getCalc(root.leaves[0]) / getCalc(root.leaves[1]);
			} else {
				sopl("doh!");
				exit(1);
				return -1;
			}
		}
	}
	public static int getRoot(ArrayList<prob21Node> nodes) {
		
		nodes = getLeaves(nodes);
		
		sopl("hello");
		sopl(nodes.size());
		for(int index = 0; index<nodes.size(); index++) {
			int touched = numTouched(nodes.get(index));
			if(touched == nodes.size()) {
				return index;
			}
			sopl("size: " + touched);
		}
		return -1;
	}
	
	
	
	public static ArrayList<prob21Node> getLeaves(ArrayList<prob21Node> nodes) {
		
		for(int index = 0; index<nodes.size(); index++) {
			if(nodes.get(index).isLeaf == false) {
				nodes.get(index).leaves[0] = nodes.get(getLabelIndex(nodes, nodes.get(index).labels[0]));
		
				nodes.get(index).leaves[1] = nodes.get(getLabelIndex(nodes, nodes.get(index).labels[1]));
			}
		}
		
		return nodes;
	}
	
	
	public static int numTouched(prob21Node node) {
		if(node.isLeaf) {
			return 1;
		} else {
			return 1 + numTouched(node.leaves[0]) + numTouched( node.leaves[1]);
		}
	}
	

	public static int getLabelIndex(ArrayList<prob21Node> nodes, String label) {
		for(int i=0; i<nodes.size(); i++) {
			if(nodes.get(i).label.equals(label)) {
				return i;
			}
		}
		
		return -1;
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
			sop("Error: (" + s + " is not a number");
			return -1;
		}
	}

	public static long plong(String s) {
		if (IsNumber.isLong(s)) {
			return Long.parseLong(s);
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
