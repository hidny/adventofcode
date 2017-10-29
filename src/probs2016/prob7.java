package probs2016;


import java.io.File;
import java.util.Scanner;

public class prob7 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in;
		try {
			 in = new Scanner(new File("prob7in.txt"));
			 String line;
			 
			 int answer = 0;
				
			 while(in.hasNextLine()) {
					line = in.nextLine();
					System.out.print(line);
					boolean inSquareBracket = false;
					boolean isABBA = false;
					
					char prevChar[] = new char[3];
					
					for(int i=0; prevChar.length < 3; i++) {
						prevChar[i] = ' ';
					}
					
					
					for(int i=0; i<line.length(); i++) {
						char currentChar = line.charAt(i);
						
						if(currentChar == '[') {
							inSquareBracket = true;
						} else if(currentChar == ']') {

							inSquareBracket = false;
						}
						
						if(checkABBA(currentChar, prevChar) ) {
							if(inSquareBracket) {
								isABBA = false;
								break;
							} else {
								isABBA = true;
							}
						}
						
						prevChar[2] = prevChar[1];
						prevChar[1] = prevChar[0];
						prevChar[0] = currentChar;
					}
					
					
					if(isABBA) {
						System.out.println("  yes");
						answer++;
					} else {
						System.out.println("  no");
					}
					
					
				}	
			 
			 System.out.println("Answer: " + answer);
				
				in.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
	}

	public static boolean checkABBA(char currentChar, char prevChar[]) {
		for(int i=0; i<prevChar.length; i++) {
			if(prevChar[i] < 'a' || prevChar[i] > 'z') {
				return false;
			}
		}
		
		if(currentChar < 'a' || currentChar > 'z') {
			return false;
		}
		
		if(currentChar == prevChar[2] && prevChar[1] == prevChar[0] && prevChar[0] != currentChar) {
			return true;
		} else {
			return false;
		}
		
	}
}
