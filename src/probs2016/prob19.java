package probs2016;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class prob19 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in;
		Scanner in2;
		try {
			 in = new Scanner(new File("prob19in.txt"));
			 in2 = new Scanner(System.in);
			 String line ="";
			 boolean isPart2 = true;
			 
			System.out.println("Start");
			 while(in.hasNextLine()) {
				 line = in.nextLine();
				 
			 }
			 
			 int input = Integer.parseInt(line);
			 
			 if(isPart2 == false) {
				 for(int i=1; i<10; i++) {
					 System.out.println(getAnswerPart1(i));
				 }
				 System.out.println("Answer: " + getAnswerPart1(input));
			 } else {

				 System.out.println("Answer: " + getAnswerPart2(input));
				 //System.out.println("Answer: " + getAnswerPart2(input));
			 }
			 
			 in2.close();
			 in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static int getAnswerPart1(int input) {
		String binary = "";
		
		while(input > 0) {
			if(input % 2 == 1) {
				binary = "1" + binary;
			} else {
				binary = "0" + binary;
			}
			input /= 2;
		}
		
		binary = binary.substring(1) + "1";
		
		int ret =0;
		int mult = 1;
		for(int i=0; i<binary.length(); i++) {
			if(binary.charAt(binary.length() - 1 - i) == '1') {
				ret = ret + mult;
			}
			
			mult *= 2;
		}
		
		return ret;
	}
	
	public static int getAnswerPart2(int input) {
		int firstKillerIndex = 0;
		int firstKilledIndex = 1;
		int championIndex = 0;
		int sizeOfCircle = 2;
		
		int prevKiller;
		
		while(sizeOfCircle < input) {
			prevKiller = firstKillerIndex;
			
			firstKillerIndex = (prevKiller - 1 + sizeOfCircle) % sizeOfCircle;
			
			sizeOfCircle++;
			
			if((firstKillerIndex + (sizeOfCircle/2)) % sizeOfCircle < firstKillerIndex) {
				
			}
			
			firstKilledIndex = (firstKillerIndex + (sizeOfCircle/2)) % (sizeOfCircle - 1);
			
			if(firstKilledIndex <= championIndex) {
				championIndex++;
			}
			if(firstKilledIndex <= firstKillerIndex) {
				firstKillerIndex++;
			}
			/*System.out.println("Size: " + sizeOfCircle + "  answer: " + part2AnswerFormula(championIndex, firstKillerIndex, sizeOfCircle));
			System.out.println(firstKillerIndex + " kills " + firstKilledIndex);
			System.out.println("Champ" + championIndex);
			System.out.println();*/
		}
		
		return part2AnswerFormula(championIndex, firstKillerIndex, sizeOfCircle);
	}
	
	public static int part2AnswerFormula(int championIndex, int firstKillerIndex, int sizeOfCircle) {
		return (championIndex - firstKillerIndex + sizeOfCircle) % sizeOfCircle  + 1;
	}
}
