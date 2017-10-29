package probs2016;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import chineseRemainderTheorem.CRTTuple;
import chineseRemainderTheorem.ChineseRemainderTheoremSolver;

public class prob15 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in;
		Scanner in2;
		try {
			 in = new Scanner(new File("prob15in.txt"));
			 boolean isPart2 = true;
			 in2 = new Scanner(System.in);
			 String line;
			 String token[];
			 
			 ArrayList<CRTTuple> equations = new ArrayList<CRTTuple>();
			 
			 
			System.out.println("Start");
			 while(in.hasNextLine()) {
				 line = in.nextLine();
				 token = line.split(" ");
				 
				 int discNumber = 0;
				 int time = 0;
				 int numPositions = 0;
				 int position = 0;
				 
				 System.out.println(line);
				 for(int i=0; i<token.length; i++) {
					 if(token[i].contains("#")) {
						 discNumber = Integer.parseInt(token[i].split("#")[1]);
					 } else if(token[i].contains("positions;")) {
						 numPositions = Integer.parseInt(token[i-1]);
					 } else if(token[i].contains("time")) {
						 position = Integer.parseInt(token[i].split("=")[1].split(",")[0]);
					 } else if(token[i].contains("position")) {
						 System.out.println(token[i+1]);
						 position = Integer.parseInt(token[i+1].substring(0, token[i+1].length() - 1));
					 }
				 }
				 
				 System.out.println("Disc number: " + discNumber);
				 System.out.println("numPositions: " + numPositions);
				 System.out.println("Time: " + time);
				 System.out.println("position: " + position);
				 System.out.println();
				 
				 System.out.println(new CRTTuple(  numPositions - position - discNumber + time, numPositions));
				 equations.add(new CRTTuple(  numPositions - position - discNumber + time, numPositions));
			 }
			 
			 if(isPart2) {
				 System.out.println("Part 2:");
				 int numPositions2 = 11;
				 int discNumber2 = equations.size() + 1;
				 int time2 = 0;
				 int position2 = 0;
				 equations.add(new CRTTuple(  numPositions2 - position2 - discNumber2 + time2, numPositions2));
			 }
			 
			 
			 System.out.println("Solution:");
			 CRTTuple solution = ChineseRemainderTheoremSolver.solve(equations);
			 
			 for(int i=0; i<equations.size(); i++) {
				 System.out.println(equations.get(i));
				 if(equations.get(i).test(solution.getNum() ) == false) {
					 System.out.println("Uh oh!");
				 }
			 }
			 System.out.println("Solution: " + solution.getNum() + " modulo " + solution.getMod());
			 
			 
			 in2.close();
			 in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//319061
	//vs
	//319061
}
