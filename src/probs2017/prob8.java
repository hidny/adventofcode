package probs2017;

import java.io.File;

import java.util.ArrayList;
import java.util.Scanner;

public class prob8 {

	public static void main(String[] args) {
		Scanner in;
		Scanner in2 = new Scanner(System.in);
		try {
			 in = new Scanner(new File("in2017/prob2017in8.txt"));
			
			long count = 0;
			boolean part2 = false;
			String line = "";
			ArrayList <String>lines = new ArrayList<String>();
			
			utils.Mapping dict = new utils.Mapping();
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				String token[] = line.split(" ");
				lines.add(line);
				
				String reg = token[0];
				
				boolean inc = false;
				if(token[1].equals("inc")) {
					inc = true;
				}
				
				int value = Integer.parseInt(token[2]);
				
				if(isCondTrue(token, dict)) {
					int mult = 1;
					if(inc == false) {
						mult = -1;
					}
					
					if(dict.has(reg)) {
						dict.set(reg, dict.get(reg) + mult * value);
					} else {
						dict.set(reg, mult * value);
					}
					
					System.out.println(line);
					System.out.println(reg + ":" + dict.get(reg));
				} else{
					System.out.println(line);
					System.out.println("false");
					System.out.println(reg + ":" + dict.get(reg));
				}
				System.out.println();
				//in2.next();
				if(part2) {
					for(int i=0; i<dict.size(); i++) {
						if(dict.number.get(i) > count) {
							count = dict.number.get(i);
						}
					}
				}
			}
			
			
			for(int i=0; i<dict.size(); i++) {
				if(dict.number.get(i) > count) {
					count = dict.number.get(i);
				}
			}
			
			
			
			System.out.println("Answer: " + count);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	
	public static boolean isCondTrue(String token[], utils.Mapping dict) {
		
		String label1 = token[4];
		long value1 = 0;
		//Why didn't I have an isNumber function... :(
		if(number.IsNumber.isNumber(label1)) {
		//if(label1.charAt(0) >= '0' && label1.charAt(0) <= '9' || label1.charAt(0) == '-') {
			value1 = Integer.parseInt(label1);
		} else if(dict.has(label1)) {
			value1 = (long)dict.get(label1);
		}
		
		String label2 = token[6];
		long value2 = 0;
		if(number.IsNumber.isNumber(label2)) {
		//if(label2.charAt(0) >= '0' && label2.charAt(0) <= '9' || label2.charAt(0) == '-') {
			value2 = Integer.parseInt(label2);
		} else if(dict.has(label2)) {
			value2 = dict.get(label2);
		}
		
		String cond = token[5];
		
		if(cond.equals(">")) {
			return value1 > value2;
			
		} else if(cond.equals(">=")) {
			return value1 >= value2;
			
		} else if(cond.equals("<")) {
			return value1 < value2;
			
		} else if(cond.equals("<=")) {
			return value1 <= value2;
			
		} else if(cond.equals("==")) {
			return value1 == value2;
			
		} else if(cond.equals("!=")) {
			return value1 != value2;
		} else {
			System.out.println("AAAAH" + cond);
			System.exit(1);
			return false;
		}
	}
	// wrong 4206

}
