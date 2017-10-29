package probs2016;


import java.io.File;
import java.util.Scanner;

public class prob3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in;
		try {
			 in = new Scanner(new File("prob3in.txt"));
			
			int count = 0;
			
			long a;
			long b;
			long c;
			long a2;
			long b2;
			long c2;
			long a3;
			long b3;
			long c3;
			while(in.hasNextInt()) {
				a = in.nextLong();
				b = in.nextLong();
				c = in.nextLong();
				

				a2 = in.nextLong();
				b2 = in.nextLong();
				c2 = in.nextLong();
				

				a3 = in.nextLong();
				b3 = in.nextLong();
				c3 = in.nextLong();
				
				if(isTriangle(a, a2, a3) ) {
					count++;
				}
				if(isTriangle(b, b2, b3) ) {
					count++;
				}
				if(isTriangle(c, c2, c3) ) {
					count++;
				}
			}
			
			System.out.println("Answer: " + count);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public static boolean isTriangle(long a, long b, long c) {
		if(a + b <= c || a + c <= b || b + c <= a) {
			return false;
		} else {
			return true;
		}
	}

}
