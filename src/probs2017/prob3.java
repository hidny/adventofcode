package probs2017;

import java.io.File;
import java.util.Scanner;

import java.util.ArrayList;

public class prob3 {

	public static void main(String[] args) {
		Scanner in;
		try {
			
			 in = new Scanner(new File("in2017/prob2017in3.txt"));
			
			int count = 0;
			boolean part2 = true;
			String line = "";
			
			int NUM = 0;
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				NUM = Integer.parseInt(line);
			}
			
			if(part2 == false) {
				//int num = NUM;
				/*//Time pressure answer (wrong way)
				for(int num = NUM; num <= NUM; num++) {
					int i;
					for(i=0; (1+2*i)*(1+2*i) < num; i++) {
						System.out.println((1+2*i)*(1+2*i));
						
					}
					System.out.println(num + ": " + i);
					
					int dist = 2*i;
					
					//I got lucky....
					dist -= ((1+2*i)*(1+2*i) - num) % i;
					System.out.println(dist);
					
					
				}*/
				
				int i=0;
				for(i=0; (1+2*i)*(1+2*i) < NUM; i++) {
					
				}
				
				
				int distToCorner = (1+2*i)*(1+2*i) - NUM;
				
				int dist = 0;
				if(distToCorner % (2*i) >= i) {
					dist = distToCorner % (2*i);
				} else {
					dist = 2*i - distToCorner % (2*i);
				}
				
				
				
				System.out.println(NUM + ": " + dist);
			
			} else if(part2) {
			
				int SPACE = 2000;
				int START = SPACE/2;
				long values[][] = new long[SPACE][SPACE];
				for(int i=0; i<values.length; i++) {
					for(int j=0; j<values.length; j++) {
						values[i][j] = 0;
					}
				}
				
				values[START][START] = 1;
				
				for(int num=2; num<=NUM; num++) {
					
					//note: avoid using i, when using coordinates
					int i =0;
					for(; (1+2*i)*(1+2*i) < num; i++) {
						
					}
					int x = i;
					int y = i;
					int currentNum = (1+2*i)*(1+2*i);
					int line1 =0;
					int line2 =0;
					int line3 =0;
					int line4 =0;
					while(currentNum > num && line1<2*i) {
						x--;
						line1++;
						currentNum--;
					}
					
					while(currentNum > num && line2<2*i) {
						y--;
						line2++;
						currentNum--;
					}
					
					while(currentNum > num && line3<2*i) {
						x++;
						line3++;
						currentNum--;
					}
					
					while(currentNum > num && line4<2*i) {
						y++;
						line4++;
						currentNum--;
					}
					for(int iy=y-1; iy<=y+1; iy++) {
						for(int j=x-1; j<=x+1; j++) {
							if(iy != y || j != x) {
								values[START+y][START+x] += values[START+iy][START+j];
							}
						}
					}
					if(values[START+y][START+x] > NUM) {
						System.out.println("Answer: " + values[START+y][START+x]);
						System.exit(1);
					}
					
				}
			}
			//279138
			
			
			in.close();
			//1129367314
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	

}
