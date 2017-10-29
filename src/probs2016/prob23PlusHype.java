package probs2016;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class prob23PlusHype {

	public static void main(String[] args) {
		Scanner in;
		Scanner in2;

		 boolean isPart2 = true;
		 boolean hype2017 = true;
		try {
			//in = new Scanner(new File("prob23in.txt"));
			if(hype2017) {
				in = new Scanner(new File("prob0Hype2017.txt"));
			} else {
				in = null;
			}
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
			 
			 if(hype2017 == true) {
				 register[0] = 2017;
				
				 answer = prob12runProg.runProg(lines, register);
			 } else {
				 if(isPart2) {
					 
					 answer = prob23Diff.getProb23Solution(lines, register);
				 } else {
				 
					 answer = prob12runProg.runProg(lines, register);
				 }
			 } 
			 System.out.println("Asnwer: " + answer);
			 
			 //Asnwer: 13140
			 //Answer: 479009700
			 
			 in2.close();
			 in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
