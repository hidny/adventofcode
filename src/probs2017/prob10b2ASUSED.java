package probs2017;

import java.io.File;

import java.util.ArrayList;
import java.util.Scanner;

import utils.Mapping;

public class prob10b2ASUSED {

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
			
			while(in.hasNextLine() ) {
				line = in.nextLine();
				String token[] = line.split(",");
				lines.add(line);
				
				array = new int[256];
				
				for(int i=0; i<array.length; i++) {
					array[i] = i;
				}
				
				int curPos = 0;
				int skipSize = 0;
				
				for(int b=0; b<64; b++) {
					for(int i=0; i<line.length(); i++) {
						int length = (int)(line.charAt(i));
						System.out.print(length + ",");
						
						array = reverse(array, curPos, length);
						curPos += length  + skipSize;
						skipSize++;
						
					}
					
					int temp[] = new int[] {17, 31, 73, 47, 23};
					
					for(int i=0; i<temp.length; i++) {
						int length = temp[i];
						System.out.print(length + ",");
						
						array = reverse(array, curPos, length);
						curPos += length  + skipSize;
						skipSize++;
						
					}
				}
				
				System.out.println();
				
				//Lengths larger than the size of the list are invalid.
				System.out.println("AAH");
				
				int arrayDense[] = new int[16];
				for(int i=0; i<16; i++) {
					System.out.println("AAH2");
					
					arrayDense[i] = array[16*i];
					for(int j=1; j<16; j++) {
						System.out.println("AAH3");
						System.out.println();
						printBin(arrayDense[i]);

						printBin(array[16*i + j]);
						System.out.println("------------");
						
						arrayDense[i] ^= array[16*i + j];
						
						printBin(arrayDense[i]);
						System.out.println();
						
					}
					System.out.println(arrayDense[i]);
				}
				
				//int temp2 = 42;
				String ret = "";
				for(int i=0; i<16; i++) {
					int temp = arrayDense[i];
					//TEST
					//temp = temp2;
					//first number:
					
					ret += getHexFromNumber(temp/16);
					
					
					ret += getHexFromNumber(temp % 16);
				}
				
				System.out.println(ret);
			}
			
			
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
	
	

	private static String getHexFromNumber(int number) {
		if(number < 10) {
			return "" + (char)((int)'0' + number);
		} else {
			return "" + (char)((int)'a' + number - 10);
		}
	}
	
	private static void printBin(int num) {
		
		int mult = 1;
		
		String ret = "";
		for(int i=128; i>0; i/=2) {
			if(num >= i) {
				ret += "1";
				num -= i;
			} else {
				ret +="0";
			}
		}
		
		System.out.println(ret);
	}
}
//a2582a3a0e66e6e86e3812dcb672a272
//25145ce378d119b8afef42e7aa16f52a
