package probs2017;

import java.io.File;

import java.util.ArrayList;
import java.util.Scanner;

import utils.Mapping;

public class prob12 {

	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2017/prob2017in12.txt"));

			
			int BIG_NUM = 10000;
			 
			int countPart1 = 0;
			boolean part2 = true;
			String line = "";
			
			boolean exists[] = new boolean[BIG_NUM];
			boolean array[][] = new boolean[BIG_NUM][BIG_NUM];
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				String token[] = line.split(" ");
				
				int firstNum = Integer.parseInt(token[0]);
				
				exists[firstNum] = true;
				
				for(int i=2; i<token.length; i++) {
					array[firstNum][Integer.parseInt(token[i].split(",")[0])] = true;
				}
				
				
			}
			
			boolean touching[] = new boolean[BIG_NUM];
			boolean touched[] = new boolean[BIG_NUM];
			
			boolean progress;
			
			int groups = 0;
			
			//While loop that goes through the index of every node checking for as yet untouched groups:
			int startIndex = 0;
			while(startIndex < BIG_NUM) {
				
				//Check if startIndex is an element of an untouched group: (Part 1 is for the size of the group that has node 0)
				if(touched[startIndex] == false && exists[startIndex]) {
					//Find all elements of current group:
					for(int i=0; i<touching.length; i++ ) {
						touching[i] = false;
					}
					
					countPart1++;
					touching[startIndex] =true;
					progress = true;
					
					while(progress == true){
						progress = false;
						for(int i=0; i<10000; i++) {
							if(touching[i]) {
								
								for(int j=0; j<BIG_NUM; j++) {
									if(touching[j] == false) {
										if(array[i][j] ) {
											touching[j] = true;
											touched[j] = true;
											progress = true;
											countPart1++;
										}
									}
								}
							}
						}
						
						
						
					}
					System.out.println(countPart1);
					if(part2 == false) {
						//151
						System.out.println("Answer: " + countPart1);
						System.exit(1);
					}
					countPart1 = 0;
					groups++;
					
					//End find all elements of current group:
					
				}
				
				startIndex++;
			}
			
			//186
			System.out.println("Answer2: " + groups);
			
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	

}
