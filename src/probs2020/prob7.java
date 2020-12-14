package probs2020;
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

public class prob7 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2020/prob2020in7.txt"));
			 //in = new Scanner(new File("in2020/prob2020in1.txt.test"));
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
			
			ArrayList<String>  bags= new ArrayList<String>();
			
			ArrayList<String> contents = new ArrayList<String>();
			
			//ArrayList<ArrayList<String>> contents = new ArrayList<ArrayList<String>>();)
			for(int i=0; i<lines.size(); i++) {
				String line = lines.get(i);
				
				if(line.trim().equals("")) {
					//Blank line means something this year...
				}
				
				String tokens[] = line.split("contain");
				
				String thing = tokens[0].trim().replace(" bags", " bag");
				//sopl(thing);
				
				bags.add(thing);
				
				contents.add(tokens[1].trim());
				
				//sopl(contents.get(i));
			}
			
			if(contents.size() != bags.size()) {
				sopl("ah!");
			}
			
			findBagsWithGold(bags, contents, "shiny gold bag");
			sopl("Answer: " + answer);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public static boolean found[];
	public static int answer = 0;
	
	public static void findBagsWithGold(ArrayList<String>  bags, ArrayList<String> contents, String target) {
		
		found = new boolean[contents.size()];
		for(int i=0; i<contents.size(); i++) {
			found[i] = false;
		}
		findBags(bags, contents, target);
	}
	
	public static void findBags(ArrayList<String>  bags, ArrayList<String> contents, String target) {

		for(int i=0; i<contents.size(); i++) {
			if(contents.get(i).contains(target) && found[i] == false) {
				found[i] = true;
				answer++;
				findBags(bags, contents, bags.get(i));
			}
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
	
	public static void exit() {
		exit(0);
	}
	public static void exit(int code) {
		sop("Exit with code " + code);
		
		System.exit(code);
	}

}
