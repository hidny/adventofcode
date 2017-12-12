package probs2017;

import java.io.File;

import java.util.ArrayList;
import java.util.Scanner;

import utils.Mapping;

public class prob12ASUSED {

	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2017/prob2017in12.txt"));
			
			int count = 0;
			boolean part2 = false;
			String line = "";
			Mapping dict = new Mapping();
			ArrayList <String>lines = new ArrayList<String>();
			
			boolean exists[] = new boolean[10000];
			boolean array[][] = new boolean[10000][10000];
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				String token[] = line.split(" ");
				
				int firstNum = Integer.parseInt(token[0]);
				
				exists[firstNum] = true;
				
				for(int i=2; i<token.length; i++) {
					array[firstNum][Integer.parseInt(token[i].split(",")[0])] = true;
				}
				
				
			}
			
			boolean touching[] = new boolean[10000];
			boolean touched[] = new boolean[10000];
			
			boolean progress;
			
			int groups = 0;
			
			int startIndex = 0;
			while(startIndex < 10000) {
				if(touched[startIndex] == false && exists[startIndex]) {
					for(int i=0; i<touching.length; i++ ) {
						touching[i] = false;
					}
					touching[startIndex] =true;
					progress = true;
					
					for(int repeat =0; repeat<100000 && progress == true; repeat++){
						progress = false;
						for(int i=0; i<10000; i++) {
							if(touching[i]) {
								
								for(int j=0; j<10000; j++) {
									if(touching[j] == false) {
										if((array[i][j] || array[j][i])) {
											touching[j] = true;
											touched[j] = true;
											progress = true;
											count++;
										}
									}
								}
							}
						}
						
						
						
					}
					System.out.println(count);
					if(part2 == false) {
						//151
						System.out.println("Answer: " + count);
						System.exit(1);
					}
					count = 0;
					groups++;
					
				}
				
				startIndex++;
			}
			
			for(int i=0; i<10000; i++) {
				if(touching[i]) {
					count++;
				}
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
