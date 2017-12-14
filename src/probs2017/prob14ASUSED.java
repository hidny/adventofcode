package probs2017;

import java.io.File;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import utils.Mapping;

public class prob14ASUSED {

	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2017/prob2017in14.txt"));
			
			int count = 0;
			boolean part2 = true;
			String lineIn = "";
			
			LinkedList queue = new LinkedList();
			Stack stack = new Stack();
			HashSet set = new HashSet();
			
			Mapping dict = new Mapping();

			int array[] = new int[256];
			
			while(in.hasNextLine() ) {
				lineIn = in.nextLine();
				
				//TODO
				for(int num=0; num<128; num++) {
					array = new int[256];
					
					String lineIt = lineIn + "-" + num;
					
					for(int i=0; i<array.length; i++) {
						array[i] = i;
					}
					
					System.out.println(lineIt);
					
					int curPos = 0;
					int skipSize = 0;
					
					for(int b=0; b<64; b++) {
						for(int i=0; i<lineIt.length(); i++) {
							int length = (int)(lineIt.charAt(i));
							//System.out.print(length + ",");
							
							array = reverse(array, curPos, length);
							curPos += length  + skipSize;
							skipSize++;
							
						}
						
						int temp[] = new int[] {17, 31, 73, 47, 23};
						
						for(int i=0; i<temp.length; i++) {
							int length = temp[i];
							
							array = reverse(array, curPos, length);
							curPos += length  + skipSize;
							skipSize++;
							
						}
					}
					
					//System.out.println();
					
					
					int arrayDense[] = new int[16];
					for(int i=0; i<16; i++) {
						
						arrayDense[i] = array[16*i];
						for(int j=1; j<16; j++) {
	
							
							arrayDense[i] ^= array[16*i + j];
							
							
						}
						//System.out.println(arrayDense[i]);
					}
					
					//int temp2 = 42;
					String ret = "";
					for(int i=0; i<16; i++) {
						int temp = arrayDense[i];
						
						ret += getHexFromNumber(temp/16);
						
						
						ret += getHexFromNumber(temp % 16);
					}
					System.out.println("TEST:");
					printBin(arrayDense[0]);
					for(int i=0; i<16; i++) {
						count+=countOnes(arrayDense[i]);
					}
					
					//System.out.println(ret);
					//2b0c9cc0449507a0 db 3b ab d5 7a d9 e8 d8
				}
			}
			
			
			System.out.println("Answer: " + count);
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

	private static int countOnes(int num) {
		int ret = 0;
		
		int mult = 1;
		
		for(int i=128; i>0; i/=2) {
			if(num >= i) {
				ret++;
				num -= i;
			}
		}
		
		return ret;
	}
}
