package probs2017;

import java.io.File;

import java.util.ArrayList;
import java.util.Scanner;

public class prob12 {

	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2017/prob2017in12.txt"));
			
			int count = 0;
			boolean part2 = true;
			String line = "";
			
			while(in.hasNextLine()) {
				line = in.nextLine();
			}
			
			System.out.println("Answer: " + count);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	

}
