package probs2015;

import java.io.File;
import java.util.Scanner;

import utils.MD5;

public class prob4 {

	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2015/prob2015in4.txt"));
			
			int count = 0;
			boolean part2 = false;
			
			System.out.println(MD5.getHexFromMD5("abcdef609043"));
			String key = "";
			while(in.hasNextLine()) {
				key = in.nextLine();
			}
			for(int i=1; true; i++) {
				if(part2) {
					if(MD5.getHexFromMD5(key + i).startsWith("000000")) {
						System.out.println("Answer: " + i);
						break;
					}
				} else if(MD5.getHexFromMD5(key + i).startsWith("00000")) {
					System.out.println("Answer: " + i);
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
