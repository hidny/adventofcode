package probs;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class prob21 {

	public static void main(String[] args) {
		Scanner in;
		Scanner in2;
		try {
			 in = new Scanner(new File("prob21in.txt"));
			 boolean isPart2 = false;
			 
			 in2 = new Scanner(System.in);
			 String line;
			 ArrayList<String> lines = new ArrayList<String>();
			 
			 
			System.out.println("Start");
			 while(in.hasNextLine()) {
				 line = in.nextLine();
				 lines.add(line);
				 
			 }
			 String testInput;
			 if(isPart2 == false) {
				 testInput = "abcdefgh";
			 } else {
				 testInput= "fbgdceah";
				 //testInput = "ghfacdbe";
				 
			 }
			 
			 System.out.println("Input: " + testInput);
			 
			 for(int i=0; i<lines.size(); i++) {
				 
				 
				 if(isPart2 == false) {
					 System.out.println(lines.get(i));
					 testInput = modifyPart1(testInput, lines.get(i));
				 } else {
					 System.out.println(lines.get(lines.size() - 1 - i));
					 testInput = modifyPart2(testInput, lines.get(lines.size() - 1 - i));
				 }
				 System.out.println(testInput);
			 }
			 //ghfacdbe
			 in2.close();
			 in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String modifyPart1(String testInput, String line) {

			String token[] = line.split(" ");
		 if(line.startsWith("swap position")) {
			 int a = Integer.parseInt(token[2]);
			 int b = Integer.parseInt(token[5]);
			
			 testInput = swapPositions(testInput, a, b);
			 
		 } else if(line.startsWith("swap letter")) {
			 char a = token[2].charAt(0);
			 char b = token[5].charAt(0);

			 testInput = swapPositions(testInput, testInput.indexOf(a), testInput.indexOf(b));
			 
		 } else if(token[0].equals("rotate") && token[1].equals("based") == false) {

			 int a = -1;
			 if(token[1].equals("left")) {
				 a = Integer.parseInt(token[2]);
			 } else {
				 a = testInput.length() - Integer.parseInt(token[2]);
			 }
			 
			 a = a % testInput.length();
			 testInput = testInput.substring(a) + testInput.substring(0, a);
			 
		 } else if(token[0].equals("rotate") && token[1].equals("based")) {

			 char inputChar = token[6].charAt(0);
			 
			 testInput = rotateBased(testInput, inputChar);
		 } else if(line.startsWith("reverse positions")) {
			 int a = Integer.parseInt(token[2]);
			 int b = Integer.parseInt(token[4]);
			 
			 testInput = reversePosition(testInput, a, b);
		 } else if(line.startsWith("move position")) {
			int a = Integer.parseInt(token[2]);
			int b = Integer.parseInt(token[5]);
			String letterToMove = testInput.substring(a, a + 1);
			if(a+1 < testInput.length()) {
				testInput = testInput.substring(0, a) + testInput.substring(a+1);
			} else {
				testInput = testInput.substring(0, a);
			}
			
			if(b < testInput.length()) {
				testInput = testInput.substring(0, b) + letterToMove + testInput.substring(b);
			} else {
				testInput = testInput.substring(0, b) + letterToMove;
			}
			
		 } else {
			 System.out.println("ERROR: unknown command!");
			 System.exit(1);
		 }
		 return testInput;
	}
	
	public static String modifyPart2(String testInput, String line) {

		String token[] = line.split(" ");
		 if(line.startsWith("swap position")) {
			 int a = Integer.parseInt(token[2]);
			 int b = Integer.parseInt(token[5]);
			
			 testInput = swapPositions(testInput, a, b);
			 
		 } else if(line.startsWith("swap letter")) {
			 char a = token[2].charAt(0);
			 char b = token[5].charAt(0);
	
			 testInput = swapPositions(testInput, testInput.indexOf(a), testInput.indexOf(b));
			 
		 } else if(token[0].equals("rotate") && token[1].equals("based") == false) {
			 int a = -1;
			 if(token[1].equals("right")) {
				 a = Integer.parseInt(token[2]);
			 } else {
				 a = testInput.length() - Integer.parseInt(token[2]);
			 }
			 
			 a = a % testInput.length();
			 testInput = testInput.substring(a) + testInput.substring(0, a);
			 
		 } else if(token[0].equals("rotate") && token[1].equals("based")) {

			 char inputChar = token[6].charAt(0);
			 
			 //Just brute force this thing:
			 String newTestInput;
			 for(int a = 0; a<testInput.length(); a++) {
				 newTestInput = testInput.substring(a) + testInput.substring(0, a);
				 
				 if(testInput.equals(rotateBased(newTestInput, inputChar))) {
					 testInput = newTestInput;
					 break;
				 }
				 
			 }
		 } else if(line.startsWith("reverse positions")) {
			 int a = Integer.parseInt(token[2]);
			 int b = Integer.parseInt(token[4]);
			 
			 testInput = reversePosition(testInput, a, b);
		 } else if(line.startsWith("move position")) {
			int b = Integer.parseInt(token[2]);
			int a = Integer.parseInt(token[5]);
			
			String letterToMove = testInput.substring(a, a + 1);
			if(a+1 < testInput.length()) {
				testInput = testInput.substring(0, a) + testInput.substring(a+1);
			} else {
				testInput = testInput.substring(0, a);
			}
			
			if(b < testInput.length()) {
				testInput = testInput.substring(0, b) + letterToMove + testInput.substring(b);
			} else {
				testInput = testInput.substring(0, b) + letterToMove;
			}
			
		 } else {
			 System.out.println("ERROR: unknown command!");
			 System.exit(1);
		 }
	 return testInput;
}
	
	public static String swapPositions(String input, int a, int b) {
		if(a == b) {
			return input;
		}
		 if(a > b) {
			 int temp = a;
			 a = b;
			 b = temp;
		 }
		 
		 input = input.substring(0, a) + input.charAt(b) + input.substring(a + 1, b) + input.charAt(a) + input.substring(b + 1);
		 
		 return input;
	}
	
	public static String reversePosition(String input, int a, int b) {
		if(a == b) {
			return input;
		}
		if(a > b) {
			int temp = a;
			a = b;
			b = temp;
		 }
		 String part1 = input.substring(0, a);
		 String part3 ="";
		 
		 if(b + 1 < input.length()) {
			 part3 = input.substring(b + 1);
		 } else {
			 part3 = "";
		 }
		 String part2Before = input.substring(a, b+1);
		 String part2After = "";
		 for(int i=0; i<part2Before.length(); i++) {
			 part2After += part2Before.charAt(part2Before.length() - 1- i);
		 }
		 
		 input = part1 + part2After + part3;
		 
		 return input;
	}
	

	public static String rotateBased(String testInput, char inputChar) {
		 int origIndex = testInput.indexOf(inputChar);
		 int a = origIndex;
		 
		 //Weird instruction...
		 //I guess reading is important
		 if(origIndex >= 4) {
			 a++;
		 }
		 
		 a++;
		 
		 a = (2 * testInput.length() - a) % testInput.length();

		 return testInput.substring(a) + testInput.substring(0, a);
		 
	}
	
}
