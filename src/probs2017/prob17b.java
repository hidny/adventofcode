package probs2017;

import java.io.File;
import java.util.Scanner;
import java.util.Stack;

import utils.Mapping;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
public class prob17b {

	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2017/prob2017in17.txt"));
			String line = "";
			
			int NUM_ITERATIONS  = 50000000;
			
			int NUM_STEPS= 0;
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				
				NUM_STEPS = Integer.parseInt(line);
			}
			
			int currentPos = 0;
			int answer = -1;
			
			for(int i=1; i<=NUM_ITERATIONS; i++) {
				currentPos = (currentPos + NUM_STEPS) % i;
				if(currentPos == 0) {
					answer = i;
				}
				currentPos++;
				
			}
			System.out.println(answer);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	

}
