package probs2016;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import aStar.AstarAlgo;
import aStar.AstarNode;

public class prob13 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in;
		Scanner in2;
		try {
			 in = new Scanner(new File("prob13in.txt"));
			 in2 = new Scanner(System.in);
			 String line;
			 
			 long register[] = new long[4];
			 for(int i=0; i<register.length; i++) {
				 register[i] = 0;
			 }
			 
			 long input = 0;
			 
			System.out.println("Start");
			 while(in.hasNextLine()) {
				 input =  Integer.parseInt(in.nextLine());
			 }
			 
			 System.out.println("input: " + input);
			 prob13pos.setPuzzleInput(input);
			 
			 prob13pos.main(null);
			 
			 prob13pos originalPos = new prob13pos(1, 1);
			 
			 prob13pos goal = new prob13pos(31, 39);
			 
			 ArrayList<AstarNode> path = AstarAlgo.astar(originalPos, goal);

			 long answer = path.size() -1;
			 System.out.println("Answer: " + answer);
			 //82
			 
			 in2.close();
			 in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
