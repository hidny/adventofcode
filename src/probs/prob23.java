package probs;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class prob23 {

	public static void main(String[] args) {
		Scanner in;
		Scanner in2;

		 boolean isPart2 = true;
		try {
			in = new Scanner(new File("prob23in.txt"));
			
			//1 toggle case
			//in = new Scanner(new File("prob23infake.txt"));
			
			//2 Asked to jump nowhere cases:
			 //in = new Scanner(new File("prob23infake2.txt"));
			 
			 
			 in2 = new Scanner(System.in);
			 String line;
			 ArrayList<String> lines = new ArrayList<String>();
			 
			 long register[] = new long[4];
			 for(int i=0; i<register.length; i++) {
				 register[i] = 0;
			 }
			 //prob 23 a:
			 if(isPart2 == false) {
				 register[0] = 7;
			 } else {
				 register[0] = 12;
					
				 
			 }
			 
			System.out.println("Start");
			 while(in.hasNextLine()) {
				 line = in.nextLine();
				 lines.add(line);
				 
			 }
			 
			 long answer = 0;
			 if(isPart2) {
				 
				 answer = prob23Diff.getProb23Solution(lines, register);
			 } else {
			 
				 answer = prob12runProg.runProg(lines, register);
			 }
			 
			 System.out.println("Asnwer: " + answer);
			 
			 //Asnwer: 13140

			 
			 in2.close();
			 in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
