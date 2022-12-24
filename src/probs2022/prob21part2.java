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

public class prob21part2 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2022/prob2022in21.txt"));
			 //in = new Scanner(new File("in2022/prob2022in22.txt"));
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

			ArrayList <prob21Node> nodes = new ArrayList <prob21Node>();
			
			
			prob21Node human = new prob21Node();
			ArrayList ints = new ArrayList<Integer>();
			for(int i=0; i<lines.size(); i++) {
				
				
				line = lines.get(i);
				String token[] = line.split(" ");
				
				
				prob21Node current = new prob21Node();

				current.label = token[0].split(":")[0];
				if(current.label.equals("humn")) {
					current.isLeaf = true;
					current.num = 0;
					//Do something!
					human = current;
					
				} else if(token.length == 2) {
					current.isLeaf = true;
					current.num = pint(token[1]);
					
				} else {
					current.isLeaf = false;
					current.labels[0] = token[1];
					current.labels[1] = token[3];
					current.operation = token[2].charAt(0);
					
				}
				
				sopl("add");
				nodes.add(current);
			}
			
			int rootIndex = prob21Node.getRoot(nodes);
			prob21Node root = nodes.get(rootIndex);
			
			long answer = prob21Node.getCalc(root);
			
			long prevDiff = -1;
			long diff = -1;
			
			//human.num = 2147483647999L;
			//long lhs = prob21Node.getCalc(root.leaves[0]);
			//long rhs = prob21Node.getCalc(root.leaves[1]);
			
			//sopl(lhs);
			//sopl(rhs);
			//exit(1);
			
			long lhs = -2;
			long rhs = -1;
			
			long maxI = 1000;
			while(lhs != rhs) {
				human.num = maxI;
				
				lhs = prob21Node.getCalc(root.leaves[0]);
				rhs = prob21Node.getCalc(root.leaves[1]);
				

				//I knew lhs > rhs because of a previous run, oh well!
				if(lhs > rhs) {
					maxI *=2;
				
				} else {
					break;
				}
				
				sopl(lhs);
				sopl(maxI);
				sopl("??");
			}

			sopl(lhs);
			sopl(rhs);
			sopl(maxI);
			sopl("??");
			sopl("---");
			long minI =0;
			long currentI = maxI /2;
			//exit(1);
			
			Scanner ind = new Scanner(System.in);
			while(lhs != rhs) {
				human.num = currentI;
				
				lhs = prob21Node.getCalc(root.leaves[0]);
				rhs = prob21Node.getCalc(root.leaves[1]);
				
				if(lhs < rhs) {
					maxI = currentI;
					currentI = (maxI + minI) / 2;
				
				} else if(rhs < lhs) {
					minI = currentI;
					currentI = (maxI + minI) / 2;
					
				}
				sopl(minI);
				sopl(currentI);
				sopl(maxI);
				sopl("what");
				//ind.next();
			}
			
			sopl("Answer: " + currentI);
			
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
