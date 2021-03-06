package probs2017;

import java.io.File;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import utils.Mapping;

public class prob14 {

	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2017/prob2017in14.txt"));
			
			int count = 0;
			String lineIn = "";
			

			int array[] = new int[256];
			
			while(in.hasNextLine() ) {
				lineIn = in.nextLine();
				
				for(int num=0; num<128; num++) {
					array = new int[256];
					
					String lineIt = lineIn + "-" + num;
					
					//TODO: PUT INTO FUNCTION
					for(int i=0; i<array.length; i++) {
						array[i] = i;
					}
					
					
					int curPos = 0;
					int skipSize = 0;
					
					for(int b=0; b<64; b++) {
						for(int i=0; i<lineIt.length(); i++) {
							int length = (int)(lineIt.charAt(i));
							
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
					
					
					int arrayDense[] = new int[16];
					for(int i=0; i<16; i++) {
						
						arrayDense[i] = array[16*i];
						for(int j=1; j<16; j++) {
	
							
							arrayDense[i] ^= array[16*i + j];
							
							
						}
					}
					
					//TODO: END PUT INTO FUNCTION
					
					System.out.println("TEST:");
					printBin(arrayDense[0]);
					for(int i=0; i<16; i++) {
						count+=countOnes(arrayDense[i]);
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
	
	
	//TODO: PUT INTO OTHER UTILS CLASS
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
	//TODO: PUT INTO OTHER UTILS CLASS
	private static void printBin(int num) {
		
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
		
		
		for(int i=128; i>0; i/=2) {
			if(num >= i) {
				ret++;
				num -= i;
			}
		}
		
		return ret;
	}
}
