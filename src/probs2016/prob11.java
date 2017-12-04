package probs2016;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import aStar.AstarAlgo;
import aStar.AstarNode;

//I could get prob 11 part 2 solved in under 8 seconds.
//:)
public class prob11 {

	public static String result = "";
	
	public static int NUM_BOTS = 500;
	public static prob10Bot bots[] = new prob10Bot[NUM_BOTS];
	public static prob10Output outputs[] = new prob10Output[NUM_BOTS];
	
	
	public static void main(String[] args) {
		
		for(int i=0; i<bots.length; i++) {
			bots[i] = new prob10Bot();
			outputs[i] = new prob10Output();
		}
		
		Scanner in;
		Scanner in2;
		try {
			//40 seconds.
			 in = new Scanner(new File("prob11in2.txt"));
			 //in = new Scanner(new File("prob11intest.txt"));
			 in2 = new Scanner(System.in);
			 String line;
			
			 prob11Position pos = new prob11Position();
			 
			 String elementName;
			 
			System.out.println("Start");
			 while(in.hasNextLine()) {
				 line = in.nextLine();
				 
				 System.out.println("Line: " + line);
				 
				 String token[] = line.split(" ");
				
				 int floor = -1;
				 
				 if(token[1].equals("first")) {
					 floor = 1;
				 } else if(token[1].equals("second")) {
					 floor = 2;
				 } else if(token[1].equals("third")) {
					 floor = 3;
				 } else if(token[1].equals("fourth")) {
					 floor = 4;
 				 } else {
 					 System.out.println("ERROR: can't find floor: " + token[1]);
 					 System.exit(1);
 				 }

				 for(int i=0; i<token.length; i++) {
					 if(token[i].contains("compatible")) {
						 elementName = token[i].split("-")[0];
						 pos.AddMicrochip(elementName, floor);
					 } else if(token[i].contains("generator")) {
						 elementName = token[i - 1];
						 pos.AddGenerator(elementName, floor);
						 
					 }
				 }
				 
			 }
			 
			 System.out.println("Start pos:\n" + pos);
			
			 System.out.println();
			 System.out.println("GOAL: " + pos.getGoal());
			 
			 
			 ArrayList<AstarNode> pathWithSymmetry = AstarAlgo.astar(pos, pos.getGoal());

			 if(pathWithSymmetry != null) {
				 prob11Position.printRealPathFromSymmetricPath(pos, pathWithSymmetry);
				 
				 System.out.println("Answer: " + (pathWithSymmetry.size() -1) );
			 } else {
				 System.out.println("No solutions");
			 }
			 in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
			 
	}
	
}
