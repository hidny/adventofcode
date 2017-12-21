package probs2017;

import java.io.File;
import number.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import utils.Mapping;

public class prob18ASUSED {

	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2017/prob2017in18.txt"));
			
			int count = 0;
			boolean part2 = false;
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
			
			int recoverSound = 0;
			int pc = 0;
			while(pc < lines.size()) {
				
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
				} else if(token[0].equals("add")) {
					dict.set(token[1], dict.get(token[1]) + getReg(dict, token[2]));
					
				} else if(token[0].equals("mul")) {
					dict.set(token[1], dict.get(token[1]) * getReg(dict, token[2]));
					
				} else if(token[0].equals("mod")) {
					dict.set(token[1], dict.get(token[1]) % getReg(dict, token[2]));
					
				} else if(token[0].equals("rcv")) {
					if(getReg(dict, token[1]) > 0 && recoverSound > 0) {
						System.out.println("rcv: " + recoverSound);
						System.exit(1);
					}
					
					
				} else if(token[0].equals("jgz") ) {
					if(getReg(dict, token[1]) > 0) {
						pc += getReg(dict, token[2]);
						continue;
					}
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
	

	public static int getReg(Mapping dict, String reg) {
		if(IsNumber.isNumber(reg)) {
			return Integer.parseInt(reg);
		} else {
			return (int)dict.get(reg);
		}
	}
}
