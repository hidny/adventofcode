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

public class prob23 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2018/prob2018in23.txt"));
			
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
			
			int maxRadiusIndex = 0;
			int maxRadius = 0;
			
			int answer[] = new int[lines.size()];
			
			
			int origCount = 0;
			for(int i=0; i<lines.size(); i++) {
				int posix = pint(lines.get(i).split("<")[1].split(">")[0].split(",")[0]);
				int posiy = pint(lines.get(i).split("<")[1].split(">")[0].split(",")[1]);
				int posiz = pint(lines.get(i).split("<")[1].split(">")[0].split(",")[2]);
				
				int ri = pint(lines.get(i).split("=")[2]);
				
				if(ri > maxRadius) {
					maxRadius = ri;
					maxRadiusIndex=i;
				}
				
				for(int j=0; j<lines.size(); j++) {
				
					int posjx = pint(lines.get(j).split("<")[1].split(">")[0].split(",")[0]);
					int posjy = pint(lines.get(j).split("<")[1].split(">")[0].split(",")[1]);
					int posjz = pint(lines.get(j).split("<")[1].split(">")[0].split(",")[2]);

					int rj = pint(lines.get(i).split("=")[2]);
					
					sopl(Math.abs(posix - posjx) + Math.abs(posiy - posjy) + Math.abs(posiz - posjz));
					
					if(Math.abs(posix - posjx) + Math.abs(posiy - posjy) + Math.abs(posiz - posjz) <= ri) {
						answer[i]++;
					}
				}
			}
			
			sopl(maxRadiusIndex);
			
			//519
			sopl("Answer: " + answer[maxRadiusIndex]);
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
	
	public static void exit() {
		exit(0);
	}
	public static void exit(int code) {
		sop("Exit with code " + code);
		
		System.exit(code);
	}
}
