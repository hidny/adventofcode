package probs2016;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class prob12Part2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in;
		Scanner in2;
		try {
			 in = new Scanner(new File("prob12in.txt"));
			 in2 = new Scanner(System.in);
			 String line;
			 ArrayList<String> lines = new ArrayList<String>();
			 
			 long register[] = new long[4];
			 for(int i=0; i<register.length; i++) {
				 register[i] = 0;
			 }
			 register[2] = 1;
			 
			System.out.println("Start");
			 while(in.hasNextLine()) {
				 line = in.nextLine();
				 lines.add(line);
				 
			 }
			 
			 long answer = prob12runProg.runProg(lines, register);
			 
			 System.out.println("Asnwer: " + register[0]);
			 
			 in2.close();
			 in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
