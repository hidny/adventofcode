package probs2016;


import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class prob7part2 {

	
	public static ArrayList<String> listabaOutside;
	public static ArrayList<String> listbabInside;
	
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
					listabaOutside = new  ArrayList<String>();
					listbabInside = new  ArrayList<String>();
					char prevChar[] = new char[2];
					
					for(int i=0; prevChar.length < 2; i++) {
						prevChar[i] = ' ';
					}
					
					
					for(int i=0; i<line.length(); i++) {
						char currentChar = line.charAt(i);
						
						if(currentChar == '[') {
							inSquareBracket = true;
						} else if(currentChar == ']') {

							inSquareBracket = false;
						}
						
						if(checkABA(currentChar, prevChar) ) {
							if(inSquareBracket) {

								listbabInside.add("" + currentChar + prevChar[0]);
							} else {
								listabaOutside.add("" + prevChar[0] + currentChar);
							}
						}
						
						prevChar[1] = prevChar[0];
						prevChar[0] = currentChar;
					}
					
					
					NESTED:
					for(int i=0; i<listbabInside.size(); i++) {
						for(int j=0; j<listabaOutside.size(); j++) {
							if(listbabInside.get(i).equals(listabaOutside.get(j))) {
								//System.out.println();
								//System.out.println(listbabInside.get(i));
								//System.out.println(listabaOutside.get(j));
								answer++;
								//System.out.print(" yes");
								break NESTED;
								
							}
						}
					}
					System.out.println();
				
					
				}	
			 
			 System.out.println("Answer: " + answer);
				
				in.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
	}

	public static boolean checkABA(char currentChar, char prevChar[]) {
		for(int i=0; i<prevChar.length; i++) {
			if(prevChar[i] < 'a' || prevChar[i] > 'z') {
				return false;
			}
		}
		
		if(currentChar < 'a' || currentChar > 'z') {
			return false;
		}
		
		if(currentChar == prevChar[1] && prevChar[0] != currentChar) {
			return true;
		} else {
			return false;
		}
		
	}
}
