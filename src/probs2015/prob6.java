package probs2015;

import java.io.File;
import java.util.Scanner;

public class prob6 {

	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2015/prob2015in6.txt"));
			
			int count = 0;
			boolean part2 = false;
			
			int DIM = 1000;
			int array[][] = new int[DIM][DIM];
			for(int i=0; i<array.length; i++) {
				for(int j=0; j<array[0].length; j++) {
					array[i][j] = 0;
				}
			}
			while(in.hasNextLine()) {
				String line = in.nextLine();
				String tokens[] = line.split(" ");
				int type = 0;
				int i1 =0;
				int j1 = 0;
				int i2 =0;
				int j2 =0;
				if(line.startsWith("turn on")) {
					type = 1;
					
					i1 = Integer.parseInt(tokens[2].split(",")[0]);
					j1 = Integer.parseInt(tokens[2].split(",")[1]);
					
					i2 = Integer.parseInt(tokens[4].split(",")[0]);
					j2 = Integer.parseInt(tokens[4].split(",")[1]);
					
				} else if(line.startsWith("turn off")) {
					type = 0;
					i1 = Integer.parseInt(tokens[2].split(",")[0]);
					j1 = Integer.parseInt(tokens[2].split(",")[1]);
					
					i2 = Integer.parseInt(tokens[4].split(",")[0]);
					j2 = Integer.parseInt(tokens[4].split(",")[1]);
				} else if(line.startsWith("toggle")) {
					type = 2;

					i1 = Integer.parseInt(tokens[1].split(",")[0]);
					j1 = Integer.parseInt(tokens[1].split(",")[1]);
					
					i2 = Integer.parseInt(tokens[3].split(",")[0]);
					j2 = Integer.parseInt(tokens[3].split(",")[1]);
				} else {
					System.out.println("ERROR!");
					System.exit(1);
				}

				int temp;
				if(i1 > i2) {
					temp = i1;
					i1 = i2;
					i2  = temp;
				}
				
				if(j1 > j2) {
					temp =j1;
					j1 = j2;
					j2  = temp;
				}
				
				for(int i=i1; i<=i2; i++) {
					for(int j=j1; j<=j2; j++) {
						if(part2 == false) {
							if(type == 0) {
								array[i][j] = 0;
							} else if(type == 1){
								array[i][j] = 1;
							} else {
								if(array[i][j] == 0) {
									array[i][j] = 1;
								} else {
									array[i][j] = 0;
								}
							}
						} else {
							if(type == 0) {
								array[i][j]--;
								if(array[i][j] < 0) {
									array[i][j] = 0;
								}
							} else if(type == 1){
								array[i][j]++;
							} else {
								array[i][j]+= 2;
							}
						}
					}
				}
			}
			
			for(int i=0; i<array.length; i++) {
				for(int j=0; j<array[0].length; j++) {
					count += array[i][j];
					if(array[i][j] >= 1) {
						System.out.print("#");
					} else{
						System.out.print(".");
					}
				}
				System.out.println();
			}
			
			System.out.println("Answer: " + count);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	

}
