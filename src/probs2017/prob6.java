package probs2017;

import java.io.File;

import java.util.ArrayList;
import java.util.Scanner;

public class prob6 {

	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2017/prob2017in6.txt"));
			
			int count = 0;
			boolean part2 = false;
			String line = "";
			ArrayList <String>lines = new ArrayList<String>();
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				String token[] = line.split("\t");
				
				lines.add(line);
				

				//100000 is big enough... :)
				int number[][] = new int[100000][token.length];
				
				for(int i=0; i<token.length; i++) {
					number[0][i] = Integer.parseInt(token[i]);
				}
				boolean noMatch = true;
				while(noMatch) {
					count++;
					
					int maxIndex = 0;
					
					for(int i=1; i<token.length; i++) {
						System.out.print(number[count-1][i] + "\t");
						if(number[count-1][i] > number[count-1][maxIndex]) {
							maxIndex = i;
						}
					}
					System.out.println();
					int num = number[count-1][maxIndex];
					
					
					for(int i=0; i<token.length; i++) {
						number[count][i] = number[count-1][i];
					}
					number[count][maxIndex] = 0;
					for(int i=0; i<num; i++) {
						number[count][(maxIndex + 1 + i )% token.length]++;
					}
					
					if(checkMatch(number, count, part2)) {
						break;
					}
				}
			}
			
			
			System.out.println("Answer: " + count);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public static boolean checkMatch(int number[][], int count, boolean part2) {
		
		for(int i=0; i<count-1; i++) {
			boolean good = true;
			for(int j=0; j<number[0].length; j++) {
				if(number[i][j] != number[count][j]) {
					good = false;
					break;
				}
				
			}
			
			if(good) { 
				if(part2) {
					System.out.println(count - i);
					System.exit(1);
				}
				return true;
			}
		}
		
		return false;
	}
	

}
