package probs2021;
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

public class prob13 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2021/prob2021in13.txt"));
			 //in = new Scanner(new File("in2021/prob2021in14.txt"));
			int numTimes = 0;
			 
			int count = 0;
			boolean part2 = false;

			LinkedList queue = new LinkedList();
			Stack stack = new Stack();
			HashSet set = new HashSet();
			
			
			Hashtable<Long, Integer> trail = new Hashtable<Long, Integer>();
			
			ArrayList <String>lines = new ArrayList<String>();
			
			
			int LIMIT = 20000;
			boolean table[][] = new boolean[LIMIT][LIMIT];
			
			
			//dir: 0 up
			//1 right
			//2 down
			//3 left
			
			while(in.hasNextLine()) {
				String line = in.nextLine();
				lines.add(line);
				
				
			}
			
			
			ArrayList ints = new ArrayList<Integer>();
			
			int maxX = 0;
			int maxY = 0;
			
			int lineIndex = -1;
			for(int i=0; i<lines.size(); i++) {
				
				//int temp = Integer.parseInt(lines.get(i));
				//count+=temp;
				
				
				String line = lines.get(i);
				String token[] = line.split(",");
				
				if(line.equals("") ) {
					lineIndex = i;
					break;
				}
				
				table[pint(token[1])][pint(token[0])] = true;
				
				if(pint(token[1]) > maxY) {
					maxY = pint(token[1]);
				}
				

				if(pint(token[0]) > maxX) {
					maxX = pint(token[0]);
				}
				
				
			}
			
			
			boolean newTable[][] = new boolean[maxY+1][maxX+1];
			for(int i=0; i<newTable.length; i++) {
				for(int j=0; j<newTable[0].length; j++) {
					newTable[i][j] = table[i][j];
				}
			}

			table = newTable;
			/*for(int i=0; i<newTable.length; i++) {
				for(int j=0; j<newTable[0].length; j++) {
					if(newTable[i][j]) {
						sop("#");
					} else {
						sop(".");
					}
				}
				sopl();
			}
			sopl("--");
			*/
			for(;lineIndex<lines.size(); lineIndex++) {
				
				if(lines.get(lineIndex).equals("")) {
					continue;
				}
				
				newTable = new boolean[table.length][table[0].length];
				
				boolean foldedTable[][] = null;
				
				int numSym = pint(lines.get(lineIndex).split("=")[1]);
				if(lines.get(lineIndex).startsWith("fold along y=")) {
					
					
					for(int i=0; i<newTable.length; i++) {
						for(int j=0; j<newTable[0].length; j++) {
							
							newTable[i][j] = table[i][j];
							
							int otherNum = -1;
							
							otherNum = i + 2 * (numSym-i);
							
							
							if(otherNum >= 0 && otherNum <table.length) {
								newTable[i][j] = table[i][j] || table[otherNum][j];
							}
						}
					}
					
					if(table.length - numSym > numSym) {
						sopl("1");
						foldedTable = new boolean[table.length - numSym - 1][table[0].length];
						
						for(int i=0; i<foldedTable.length; i++){
							for(int j=0; j<foldedTable[0].length; j++) {
								foldedTable[foldedTable.length - 1 - i][j] = newTable[numSym+1+i][j];
							}
						}
					} else {
						sopl("2");
						foldedTable = new boolean[numSym][table[0].length];
						
						for(int i=0; i<foldedTable.length; i++){
							for(int j=0; j<foldedTable[0].length; j++) {
								foldedTable[i][j] = newTable[i][j];
							}
						}
					}
					
				} else if(lines.get(lineIndex).startsWith("fold along x=")) {
					
					for(int i=0; i<newTable.length; i++) {
						for(int j=0; j<newTable[0].length; j++) {
							
							newTable[i][j] = table[i][j];
							
							int otherNum = -1;
							
							otherNum = j + 2 * (numSym-j);
							//sopl(j + " reflects " + otherNum);
							
							
							if(otherNum >= 0 && otherNum <table[0].length) {
								newTable[i][j] = table[i][j] || table[i][otherNum];
							}
						}
					}
					
					if(table[0].length - numSym > numSym) {
						sopl("3");
						foldedTable = new boolean[table.length][table[0].length  - numSym - 1];
						
						for(int i=0; i<foldedTable.length; i++){
							for(int j=0; j<foldedTable[0].length; j++) {
								foldedTable[i][foldedTable[0].length - 1 - j] = newTable[i][numSym+1+j];
							}
						}
					} else {
						sopl("4");
						foldedTable = new boolean[table.length][numSym];
						
						for(int i=0; i<foldedTable.length; i++){
							for(int j=0; j<foldedTable[0].length; j++) {
								foldedTable[i][j] = newTable[i][j];
							}
						}
					}
					
				}
				

				table = foldedTable;
				
/*
				for(int i=0; i<table.length; i++) {
					for(int j=0; j<table[0].length; j++) {
						if(table[i][j]) {
							sop("#");
							count++;
						} else {
							sop(".");
						}
					}
					sopl();
				}
	*/			
				
			}
			
			for(int i=0; i<table.length; i++) {
				for(int j=0; j<table[0].length; j++) {
					if(table[i][j]) {
						sop("#");
						count++;
					} else {
						sop(".");
					}
				}
				sopl();
			}
			
			
			//sopl("Answer: " + count);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
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
