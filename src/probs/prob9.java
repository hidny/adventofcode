package probs;
import java.io.File;
import java.util.Scanner;

public class prob9 {

	public static String result = "";
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
				 
				 
				 for(int i=0; i<line.length(); i++) {
					 if(line.charAt(i) == '(') {
						 i = processBracket(line, i);
						 i--;
					 } else {
						 result += line.charAt(i);
					 }
				 }
				 
				 if(result.endsWith("\n")) {
					 result.substring(0, result.length() - 1);
				 }
				 
				 //System.out.println(result);
				 System.out.println("Answer: " + result.length());
				 /*
				 in2.next();
				 */
			 }
			 
			
			 in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
			 
	}

	public static int processBracket(String line, int i) {
		String lineSoFar = line.substring(i);
		String numbers = lineSoFar.substring(lineSoFar.indexOf('(') + 1, lineSoFar.indexOf(')'));
		String tokens[] = numbers.split("x");
		int length = Integer.parseInt(tokens[0]);
		int numRepeats = Integer.parseInt(tokens[1]);
		
		int indexStart = lineSoFar.indexOf(')') + 1;
		for(int j=0; j<numRepeats; j++) {
			for(int k = 0; k<length; k++) {
				result += lineSoFar.charAt(indexStart + k);
			}
		}
		
		return i + indexStart + length;
	}
}
