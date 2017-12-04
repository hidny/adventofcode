package probs2017;

import java.io.File;

import java.util.ArrayList;
import java.util.Scanner;

public class prob4 {
//  4   00:02:26   175      0   00:05:24   207      0
// I'm still proud of myself...
// Under 3 minutes is a personal record.
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2017/prob2017in4.txt"));
			
			int count = 0;
			boolean part2 = false;
			String line = "";
			
		
			while(in.hasNextLine()) {
				line = in.nextLine();
				String token[] = line.split(" ");
				
				boolean nope = false;
				for(int i=0; i<token.length; i++) {
					for(int j=i+1; j<token.length; j++) {
						if(part2) {
							if(sort(token[i]).equals(sort(token[j]))) {
								nope = true;
								break;
							}
						} else {
							if(token[i].equals(token[j])) {
								nope = true;
								break;
							}
						}
					}
				}
				
				if(nope == false) {
					count++;
				}
			}
			
			System.out.println("Answer: " + count);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	

	public static String sort(String a) {
		char charArray[] = a.toCharArray();
		char temp2;

		String retS = "";
		for(int i=0; i<charArray.length; i++) {
			for(int j=i+1; j<charArray.length; j++) {
				if(charArray[i]<charArray[j]) {
					temp2 = charArray[i];
					charArray[i] = charArray[j];
					charArray[j] = temp2;
				}
			}
			retS += charArray[i];
		}
		return retS;
		
	}
}
