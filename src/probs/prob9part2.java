package probs;
import java.io.File;
import java.util.Scanner;

public class prob9part2 {

	public static long result = 0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in;
		Scanner in2;
		try {
			 in = new Scanner(new File("prob9in.txt"));
			 in2 = new Scanner(System.in);
			 String line;
			 
			 int answer = 0;
			 
			 String tokens[];
			 String numbers[];
			 
			 int amt;
				
			 while(in.hasNextLine()) {
				 line = in.nextLine();
				 
				 System.out.println("Line" + line);
				 System.out.println(line.length());
				 
				 line = "(" + line.length() + "x1)" + line;
				 
				 processBracket(line);
				 
				 
				 
				 //System.out.println(result);
				 System.out.println("Answer: " + result);
				 /*
				 in2.next();
				 */
			 }
			 
			
			 in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
			 
	}


	public static int processBracket(String line ) {
		return processBracket(line, 0 ,1 );
		
	}
	public static int processBracket(String line, int i, int numRepeatsPrev) {
		String lineSoFar = line.substring(i);
		String numbers = lineSoFar.substring(lineSoFar.indexOf('(') + 1, lineSoFar.indexOf(')'));
		String tokens[] = numbers.split("x");
		int length = Integer.parseInt(tokens[0]);
		int numRepeats = Integer.parseInt(tokens[1]);
		
		int indexStart = lineSoFar.indexOf(')') + 1;
		int indexEnd = indexStart + length;
		
		lineSoFar = lineSoFar.substring(indexStart, indexEnd);
		
		
		for(int k = 0; k<length; k++) {
			if(lineSoFar.charAt(k) == '('&& lineSoFar.substring(k).indexOf(")") != -1) {
					k = processBracket(lineSoFar, k, numRepeatsPrev*numRepeats);
					k--;
				
			} else {
				result = result + numRepeatsPrev*numRepeats;
			}
		}
		
		
		return i + indexStart + length;
	}
}
