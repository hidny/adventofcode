package probs2017;

import java.io.File;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import number.IsNumber;
import utils.Mapping;

public class prob23a {

	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2017/prob2017in23.txt"));
			
			int count = 0;
			boolean part2 = false;
			String line = "";

			LinkedList queue = new LinkedList();
			Stack stack = new Stack();
			HashSet set = new HashSet();
			
			Mapping dict = new Mapping();
			ArrayList <String>lines = new ArrayList<String>();
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				String token[] = line.split(",");
				
				
			}
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				String token[] = line.split(",");
				
				
			}
			
			int recoverSound = 0;
			int pc = 0;
			while(pc < lines.size()) {

				
				//System.out.println(pc);
				String token[] = lines.get(pc).split(" ");
				if(token[0].equals("snd")) {

					int temp1 = getReg(dict, token[1]);
					recoverSound = temp1;
					//if(temp1 > 0) {
					//	System.out.println(temp);
					//	System.exit(1);
					//}
				} else if(token[0].equals("set")) {
					dict.set(token[1], getReg(dict, token[2]));
					
				} else if(token[0].equals("sub")) {
					dict.set(token[1], dict.get(token[1]) - getReg(dict, token[2]));
					
				} else if(token[0].equals("mul")) {
					count++;
					dict.set(token[1], dict.get(token[1]) * getReg(dict, token[2]));
					
				} else if(token[0].equals("jnz") ) {
					
					if(getReg(dict, token[1]) != 0) {
						pc += getReg(dict, token[2]);
						continue;
					}
				} else if(token[0].equals("nop") ) {
					//do nothing
				} else {
					sopl("oops");
					System.exit(1);
				}
				
				
				pc++;
			}
			
			System.out.println("Answer: " + count);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	
	public static void sopl() {
		sopl("");
	}
	
	public static void sopl(String out) {
		System.out.println(out);
	}

	public static void sop(String out) {
		System.out.print(out);
	}
	
	public static int getReg(Mapping dict, String reg) {
		if(IsNumber.isNumber(reg)) {
			return Integer.parseInt(reg);
		} else {
			return (int)dict.get(reg);
		}
	}
}
