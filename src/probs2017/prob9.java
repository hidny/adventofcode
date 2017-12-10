package probs2017;

import java.io.File;

import java.util.ArrayList;
import java.util.Scanner;

import utils.Mapping;

public class prob9 {

	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2017/prob2017in9.txt"));
			
			int count = 0;
			boolean part2 = true;
			String line = "";
			ArrayList <String>lines = new ArrayList<String>();
			Mapping dict = new Mapping();
			

			boolean cancelledChar = false;
			boolean inGarbage = false;
			int depth = 1;
			
			int countAnswerPart2= 0;
			
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				count = 0;
				
				for(int i=0; i<line.length(); i++) {
					if(cancelledChar) {
						cancelledChar = false;
						continue;
					}
					if(line.charAt(i) == '!') {
						
						cancelledChar = true;
						continue;
					}
					if(inGarbage) {
						if(line.charAt(i) == '>') {
							inGarbage = false;
						} else {
							//part2
							countAnswerPart2++;
						}
						continue;
					}
					
					
					
					if(line.charAt(i) == '<') {
						inGarbage = true;
						continue;
					}
					
					
					if(line.charAt(i) == '{') {
							count += depth;
							depth++;
					}
					
					if(line.charAt(i) == '}') {
						depth--;
					}
					
				}
				
				System.out.println("Answer2 : " + countAnswerPart2);
				
				System.out.println("Answer: " + count);
			}
			
			
			
			
			
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	

}
