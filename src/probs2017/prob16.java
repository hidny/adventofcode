package probs2017;

import java.io.File;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import utils.Mapping;

public class prob16 {

	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2017/prob2017in16.txt"));
			
			String line = "";

			
			
			String current = "abcdefghijklmnop";
			String orig = current;
			boolean foundCycle = false;
			int cycleLength = -1;
			
			int NUM_ITER = 1000000000;
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				String token[] = line.split(",");
				
				for(int num2=0; num2<NUM_ITER; num2++) {
					
					for(int i=0; i<token.length; i++) {
	
						
						if(token[i].startsWith("s")) {
							int num = Integer.parseInt(token[i].substring(1));
							
							current = current.substring(current.length() - num) + current.substring(0, current.length() - num);
						} else if(token[i].startsWith("x")) {
							String subToken[] = token[i].split("/");
							int progA = Integer.parseInt(subToken[0].substring(1));
							int progB = Integer.parseInt(subToken[1]);
						
							current = swap(current, progA, progB);
							
						} else {
							String subToken[] = token[i].split("/");
							char progAc = subToken[0].charAt(1);
							char progBc = subToken[1].charAt(0);
							
							int progA = current.indexOf(progAc);
							int progB = current.indexOf(progBc);
							

							current = swap(current, progA, progB);
							
						}
					}
					
					if(num2 == 0) {
						System.out.println("Answer for part a: " + current);
					}
					
					//Observation after the fact:
					//Because the dance commands are the same, if we could find a cycle after a dance,
					//then it means it will cycle into the original configuration
					//i.e if D^i = D^i+m then D^0 = D^m
					//when D^n is the config after n dances
					
					if(current.equals(orig)) {
						System.out.println("Found match!");
						cycleLength = num2 + 1;
						System.out.println("Cycle Length: " + cycleLength);
						
						foundCycle = true;
					}
					
					if(foundCycle) {
						if((num2 + 1) % cycleLength == NUM_ITER % cycleLength) {
							//found answer for part b
							break;
						}
					}
					
				}
				
			}
			
			//bpcekomfgjdlinha
			
			System.out.println("Answer for part b: " + current);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public static String swap(String input, int index1, int index2) {
		
		char temp = input.charAt(index1);
		
		String tempS = input.substring(0, index1) + input.charAt(index2);
		if(tempS.length() < input.length()) {
			tempS += input.substring(index1 + 1);
		}
		input = tempS;
		
		tempS = input.substring(0, index2) + temp;
		if(tempS.length() < input.length()) {
			tempS += input.substring(index2 + 1);
		}
		input = tempS;
		
		return input;
	}
	

}
