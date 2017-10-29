package probs2015;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class prob23 {

	//The code implement the collatz function!
	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2015/prob2015in23.txt"));
			
			int count = 0;
			boolean part2 = true;
			
			int register[] = new int[2];
			
			register[0] = 0;
			register[1] = 0;
			
			if(part2) {
				register[0] = 1;
			}
			ArrayList<String> lines = new ArrayList<String>();
			while(in.hasNextLine()) {
				lines.add(in.nextLine());
			}
			
			int pc = 0;
			
			while(pc < lines.size()) {
				String code = lines.get(pc);
				String token[] = code.split(" ");
				if(token[0].equals("hlf")) {
					register[getIndex(token[1])] /= 2;
				} else if(token[0].equals("tpl")) {
					register[getIndex(token[1])] *= 3;
				} else if(token[0].equals("inc")) {
					register[getIndex(token[1])]++;
				} else if(token[0].equals("jmp")) {
					pc += getValue(token[1]);
					continue;
				} else if(token[0].equals("jie")) {
					if( (2 + register[getIndex(token[1])]) % 2 == 0) {
						pc += getValue(token[2]);
						continue;
					}
				} else if(token[0].equals("jio")) {
					if( ( register[getIndex(token[1])])== 1) {
						pc += getValue(token[2]);
						continue;
					}
				}
				
				//go to the next line of code:
				pc++;
			}
			
			System.out.println("Answer: " + register[getIndex("b")]);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	
	
	public static int getIndex(String reg) {
		return (int)(reg.charAt(0) - 'a');
	}

	public static int getValue(String value) {
		int mult = 1;
		if(value.charAt(0) == '-') {
			mult = -1;
		}
		
		return mult * Integer.parseInt(value.substring(1));
	}
}
