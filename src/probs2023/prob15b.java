package probs2023;
import java.io.File;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

import number.IsNumber;
import utils.Mapping;
import utils.Sort;

public class prob15b {

	//day1 part 1
	//2:38.01
	
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2023/prob2023in15.txt"));
			//in = new Scanner(new File("in2023/prob2023in0.txt"));
			int numTimes = 0;
			 
			int count = 0;
			boolean part2 = false;
			String line = "";

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
				line = in.nextLine();
				lines.add(line);
				
			}

			long cur = 0L;
			
			ArrayList ints = new ArrayList<Integer>();
			for(int i=0; i<lines.size(); i++) {
				
				
				line = lines.get(i);
				
				
				//cur += pint(line);
			}

			String tokens[] = lines.get(0).split(",");
			
			prob15obj boxes[][]= new prob15obj[256][1000];
			
			for(int i=0; i<boxes.length; i++) {
				for(int j=0; j<boxes[0].length; j++) {
					boxes[i][j] = null;
				}
			}
			
			
			long curBoxNum = 0;
			for(int i=0; i<tokens.length; i++) {
				curBoxNum = 0;
				
				sopl(tokens[i]);
				
				//517336
				for(int j=0; j<tokens[i].length(); j++) {
					
					if(tokens[i].charAt(j) != '-' && tokens[i].charAt(j) != '=' ) {
						curBoxNum = (17*(curBoxNum + (int)(tokens[i].charAt(j)))) % 256;
					
						//sopl(i +": " +curBoxNum);
					} else {
						break;
					}
				}
				
				sopl(curBoxNum);
				
				boolean setNew = false;
				for(int j=0; j<tokens[i].length(); j++) {
					if(tokens[i].charAt(j) == '=') {
						setNew = true;
					}
				}
				
				int focalLense = -1;
				
				String label = "ahhh";
				if(setNew) {
					
					label = tokens[i].split("=")[0];
					focalLense = pint(tokens[i].split("=")[1]);
				} else {
					label = tokens[i].split("-")[0];
				}
				
				sopl("label: " + label);
				
				
				if(setNew == false) {
					
					for(int j=0; boxes[(int)curBoxNum][j] != null; j++) {
						
						if(boxes[(int)curBoxNum][j].label.equals(label)) {
							
							for(int k=j; k<boxes[(int)curBoxNum].length - 1; k++) {
								boxes[(int)curBoxNum][k] = boxes[(int)curBoxNum][k + 1];
							}
							sopl("Debug");
						}
					}
				} else {
					
					
					
					boolean inserted = false;
					int insIndex = 0;
					for(; boxes[(int)curBoxNum][insIndex] != null; insIndex++) {
					
						if(boxes[(int)curBoxNum][insIndex].label.equals(label)) {
							boxes[(int)curBoxNum][insIndex] = new prob15obj(label, (int)curBoxNum, focalLense);
							inserted = true;
						}
					}
					
					if(inserted == false) {
						boxes[(int)curBoxNum][insIndex] = new prob15obj(label, (int)curBoxNum, focalLense);
					}
				}
				
			}
			
			sopl("SUM");
			cur = 0;
			for(int i=0; i<boxes.length; i++) {
				
				for(int j=0; boxes[i][j] != null; j++) {
					
					sopl("box " + i +" slot " + j);
					sopl(boxes[i][j].label);
					sopl(boxes[i][j].getNum(j));
					cur += boxes[i][j].getNum(j);
				}
			}

			sopl("Answer: " + cur);
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
			sop("Error: (" + s + ") is not a number");
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
