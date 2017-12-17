package probs2017;

import java.io.File;
import java.util.Scanner;
import java.util.Stack;

import utils.Mapping;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
public class prob17a {

	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2017/prob2017in17.txt"));
			
			String line = "";

			
			ArrayList <Integer> list = new ArrayList<Integer>();
			
			int NUM_STEPS= 0;
			while(in.hasNextLine()) {
				line = in.nextLine();
				NUM_STEPS = Integer.parseInt(line);
			}
			
			list.add(0);
			
			int currentPos = 0;
			for(int i=1; i<=2017; i++) {
				currentPos = (currentPos + NUM_STEPS) % list.size();
				list.add(currentPos+1, i);
				currentPos++;
				
			}
			

			for(int j=1; j<list.size(); j++) {
				if(list.get(j) == 2017) {
					System.out.println("Answer: " + list.get(j+1));
					break;
				}
			}
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	

}
