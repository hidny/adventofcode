package probs2015;

import java.io.File;
import java.util.Scanner;

public class prob10b {

	public static int LENGTH_NUM = 3;
	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2015/prob2015in10.txt"));
			
			int count = 0;
			boolean part2 = false;
			String line = "";
			while(in.hasNextLine()) {
				line = in.nextLine();
			}
			
			boolean current[] = turnToBoolArray(line);
			printSequence(current);
			
			int NUM_TIMES = 40;
			if(part2) {
				 NUM_TIMES = 50;
			}
			for(int i=0; i<NUM_TIMES; i++) {
				current = operation(current);
				//printSequence(current);
			}
			
			System.out.println("Answer: " + (current.length / LENGTH_NUM));
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	

	public static boolean[] turnToBoolArray(String line) {
		boolean output[] = new boolean[line.length() * LENGTH_NUM];
		for(int i=0; i<line.length(); i++) {
			insertNum(output, i, (int)(line.charAt(i) - '0'));
			
		}
		return output;
	}
	
	public static boolean[] operation(boolean array[]) {
		boolean output[] = new boolean[array.length * 2];
		int currentLength = 0;
		
		int origLength = array.length/LENGTH_NUM;
		
		int numTimes = 0;
		for(int i=0; i<origLength; i+=numTimes) {
			int num = getNum(array, i);
			
			numTimes = 1;
			
			for(int k = i+1; k<origLength; k++) {
				if(num != getNum(array, k)) {
					break;
				} else {
					numTimes++;
				}
			}
			
			insertNum(output, currentLength, numTimes);
			insertNum(output, currentLength+1, num);
			currentLength += 2;
		}
		
		boolean ret[] =new boolean[currentLength * LENGTH_NUM];
		
		for(int i=0; i<ret.length/LENGTH_NUM; i++) {
			insertNum(ret, i, getNum(output, i));
		}
			
		return ret;
	}
	
	public static void printSequence(boolean array[]) {
		for(int i=0; i<array.length/LENGTH_NUM; i++) {
			System.out.print( getNum(array,i));
		}
		System.out.println();
	}
	
	public static int getNum(boolean array[], int i) {
		int ret = 0;
		for(int j=0; j<LENGTH_NUM; j++) {
			
			if(array[LENGTH_NUM*i + j]) {

				ret = 2*ret + 1;
			} else {
				ret = 2*ret;
			}
		}
		return ret;
	}
	
	public static void insertNum(boolean array[], int i, int num) {
		if(num == 0) {
			array[LENGTH_NUM*i] = false;
			array[LENGTH_NUM*i + 1] = false;
			array[LENGTH_NUM*i + 2] = false;
			
		} else if(num == 1) {
			array[LENGTH_NUM*i] = false;
			array[LENGTH_NUM*i + 1] = false;
			array[LENGTH_NUM*i + 2] = true;
			
		} else if(num == 2) {
			array[LENGTH_NUM*i] = false;
			array[LENGTH_NUM*i + 1] = true;
			array[LENGTH_NUM*i + 2] = false;
			
		}  else if(num == LENGTH_NUM) {
			array[LENGTH_NUM*i] = false;
			array[LENGTH_NUM*i + 1] = true;
			array[LENGTH_NUM*i + 2] = true;
			
		}  else if(num == 4) {
			array[LENGTH_NUM*i] = true;
			array[LENGTH_NUM*i + 1] = false;
			array[LENGTH_NUM*i + 2] = false;
			
		}  else if(num == 5) {
			array[LENGTH_NUM*i] = true;
			array[LENGTH_NUM*i + 1] = false;
			array[LENGTH_NUM*i + 2] = true;
			
		}  else if(num == 6) {
			array[LENGTH_NUM*i] = true;
			array[LENGTH_NUM*i + 1] = true;
			array[LENGTH_NUM*i + 2] = false;
			
		}  else if(num == 7) {
			array[LENGTH_NUM*i] = true;
			array[LENGTH_NUM*i + 1] = true;
			array[LENGTH_NUM*i + 2] = true;
			
		} 
	}
}
