package probs2017;

import java.io.File;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import utils.Mapping;

public class prob16ASUSED {

	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2017/prob2017in16.txt"));
			
			int count = 0;
			boolean part2 = true;
			String line = "";

			LinkedList queue = new LinkedList();
			Stack stack = new Stack();
			HashSet set = new HashSet();
			
			Mapping dict = new Mapping();
			ArrayList <String>lines = new ArrayList<String>();

			//HashMap <String, Integer> linesCheck = new HashMap <String, Integer>();
			
			String start = "abcdefghijklmnop";
			while(in.hasNextLine()) {
				line = in.nextLine();
				String token[] = line.split(",");
				
				for(int num2=0; num2<1000000000; num2++) {
					if(num2 % 1000000 == 0) {
						System.out.println(num2);
					}
					for(int i=0; i<token.length; i++) {
	
						
						if(token[i].startsWith("s")) {
							int num = Integer.parseInt(token[i].substring(1));
							
							start = start.substring(start.length() - num) + start.substring(0, start.length() - num);
						} else if(token[i].startsWith("x")) {
							String subToken[] = token[i].split("/");
							int progA = Integer.parseInt(subToken[0].substring(1));
							int progB = Integer.parseInt(subToken[1]);
							
							char temp = start.charAt(progA);
							
							String tempS = start.substring(0, progA) + start.charAt(progB);
							if(tempS.length() < start.length()) {
								tempS += start.substring(progA + 1);
							}
							start = tempS;
							
							tempS = start.substring(0, progB) + temp;
							if(tempS.length() < start.length()) {
								tempS += start.substring(progB + 1);
							}
							start = tempS;
							
						} else {
							String subToken[] = token[i].split("/");
							char progAc = subToken[0].charAt(1);
							char progBc = subToken[1].charAt(0);
							
							int progA = start.indexOf(progAc);
							int progB = start.indexOf(progBc);
							
	
							char temp = start.charAt(progA);
							String tempS = start.substring(0, progA) + start.charAt(progB);
							if(tempS.length() < start.length()) {
								tempS += start.substring(progA + 1);
							}
							
							start = tempS;
							
							tempS = start.substring(0, progB) + temp;
							if(tempS.length() < start.length()) {
								tempS += start.substring(progB + 1);
							}
							start = tempS;
						}
					}
					if(lines.size() == 15) {
						System.out.println(start);
						System.exit(1);
					}
					
					if(checkMatch(start, lines) == true) {
						System.out.println("Found match!");
						System.out.println(start);

						System.out.println(lines.size() );
						System.exit(1);
					} else {
						lines.add(start);
					}
				}
				
			}
			
			System.out.println("Answer: " + start);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public static boolean checkMatch(String current, ArrayList<String> lines) {
		for(int i=0; i<lines.size(); i++) {
			if(current.equals(lines.get(i))) {
				System.out.println(lines.get(i));
				System.out.println(i);
				return true;
			}
		}
		
		return false;
	}
	

}
