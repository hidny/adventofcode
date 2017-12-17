package probs2017;

import java.io.File;
import java.util.Scanner;
import java.util.Stack;

import utils.Mapping;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
public class prob17aASUSED {

	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2017/prob2017in17.txt"));
			
			int count = 0;
			boolean part2 = true;
			String line = "";

			LinkedList queue = new LinkedList();
			Stack stack = new Stack();
			HashSet set = new HashSet();
			
			Mapping dict = new Mapping();
			ArrayList <String>lines = new ArrayList<String>();
			
			ArrayList <Integer> list = new ArrayList<Integer>();
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				String token[] = line.split(",");
				
				
			}
			
			int num_step = 348;
			list.add(0);
			
			int currentPos = 0;
			for(int i=1; i<=2017; i++) {
				currentPos = (currentPos + num_step) % list.size();
				list.add(currentPos+1, i);
				currentPos++;
				
			}
			

			for(int j=0; j<list.size(); j++) {
				System.out.println(list.get(j) + ", ");
			}
			System.out.println();
			
			System.out.println("Answer: " + count);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	

}
