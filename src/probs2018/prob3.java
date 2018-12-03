package probs2018;

import number.IsNumber;
import java.io.File;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import utils.Mapping;

public class prob3 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2018/prob2018in3.txt"));
			
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
			
			int array[][] = new int[1000][1000];
			
			int origCount = 0;
			for(int i=0; i<lines.size(); i++) {
				line = lines.get(i);
				String coord[] = line.split(" ")[2].split(",");
				int coordj =pint(coord[0]);
				int coordi = pint(coord[1].split(":")[0]);
				
				String dim[] = line.split(" ")[3].split("x");
				int widthj = pint(dim[0]);
				int heighti = pint(dim[1]);
				
				for(int i1=coordi; i1<coordi + heighti; i1++) {
					for(int j=coordj; j<coordj + widthj; j++) {
						array[i1][j]++;
					}
				}
			}
			
			for(int i=0; i<1000; i++) {
				for(int j=0; j<1000; j++) {
					if(array[i][j] > 1) {
						count++;
					}
				}
			}
			
			sopl("Answer: " + count);
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
}
