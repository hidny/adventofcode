package probs2017;

import java.io.File;

import java.util.ArrayList;
import java.util.Scanner;

import utils.Mapping;

public class prob10 {

	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2017/prob2017in10.txt"));
			
			int count = 0;
			boolean part2 = true;
			String line = "";
			ArrayList <String>lines = new ArrayList<String>();
			Mapping dict = new Mapping();
			int array[] = new int[256];
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				String token[] = line.split(",");
				lines.add(line);
				
				
				for(int i=0; i<array.length; i++) {
					array[i] = i;
				}
				
				int curPos = 0;
				int skipSize = 0;
				
				for(int i=0; i<token.length; i++) {
					int length = Integer.parseInt(token[i]);
					
					array = reverse(array, curPos, length);
					curPos += length  + skipSize;
					skipSize++;
					
				}
				
				//Lengths larger than the size of the list are invalid.
				
			}
			
			
			
			
			
			System.out.println("Answer: " + (array[0] * array[1]));
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	

	public static int[] reverse(int a[], int curPos, int length) {
		int temp[] = new int[length];
		for(int i=0; i<temp.length; i++) {
			temp[i] = a[(i + curPos) % a.length];
		}
		
		for(int i=0; i<temp.length; i++) {
			a[(i + curPos) % a.length] = temp[temp.length -1 -i];
		}
		
		return a;
		
	}
	
}
