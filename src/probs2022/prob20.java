package probs2022;
import java.io.File;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import number.IsNumber;
import utils.Mapping;
import utils.Sort;

public class prob20 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 //in = new Scanner(new File("in2022/prob2022in20.txt"));
			in = new Scanner(new File("in2022/prob2022in21.txt"));
			int numTimes = 0;
			 
			int count = 0;
			boolean part2 = false;
			String line = "";

			LinkedList queue = new LinkedList();
			Stack stack = new Stack();
			HashSet set = new HashSet();
			
			
			Hashtable<Long, Integer> trail = new Hashtable<Long, Integer>();
			
			ArrayList <String>lines = new ArrayList<String>();
			
			
			int LIMIT = 20000;
			boolean table[][] = new boolean[LIMIT][LIMIT];
			
			
			//dir: 0 up
			//1 right
			//2 down
			//3 left
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				
			}

			int zeroIndex =-1;
			ArrayList<Integer> ints = new ArrayList<Integer>();
			for(int i=0; i<lines.size(); i++) {
				
				
				line = lines.get(i);
				String token[] = line.split(" ");
				
				ints.add(pint(line));
				
				if(pint(line) == 0) {
					zeroIndex = i;
				}
				
			}
			
			
			int array[] = new int[ints.size()];
			int arrayLocation[] = new int[ints.size()];
			
			for(int i=0; i<ints.size(); i++) {
				array[i] = ints.get(i);
				arrayLocation[i] = i;
			}
			
			
			for(int i=0; i<ints.size(); i++) {
				
				sopl();
				sopl();
				sopl();
				sopl("i = " + i);
				int numMove = ints.get(i);
				sopl("Num move: " + numMove);
				
				
				int location = arrayLocation[i];
				sopl("location: " + location);
				
				int tmp = array[arrayLocation[i]];
				int tmpNewPosition = (arrayLocation[i] + numMove + 10 * array.length)%array.length;
				sopl(tmpNewPosition);
				
				boolean tmpCheck[] = new boolean[arrayLocation.length];
				
				sopl("Location before move:");
				for(int j=0; j<arrayLocation.length; j++) {
						sop(arrayLocation[j] + " ");
				}
				sopl();
				sopl("values before move:");
				for(int j=0; j<array.length; j++) {
						sop(array[j] + " ");
				}
				sopl();
				if(tmp != numMove) {
					sopl("oops!");
					
					for(int j=0; j<array.length; j++) {
						sop(array[j] + " ");
					}
					sopl();
					sopl("tmp = " + tmp);
					sopl("numMove = " + numMove);
					exit(1);
				}
				
				boolean isNegative = false;
				if(numMove < 0) {
					isNegative = true;
				}
				
				if(!isNegative) {
					for(int j=0; j<arrayLocation.length; j++) {
						
						if(i == j) {
							continue;
						}
						if(numMove % arrayLocation.length >= (arrayLocation[j] - location + 10 * array.length) % arrayLocation.length ) {
							arrayLocation[j]--;
							
							arrayLocation[j] = (arrayLocation[j] + arrayLocation.length) % arrayLocation.length;
						}
						
					}
				} else {
					int negStart = (location + numMove + 10 * array.length)% arrayLocation.length;
					
					for(int j=0; j<arrayLocation.length; j++) {

						if(i == j) {
							continue;
						}
						
						if((0 - numMove  + 10 * array.length) % arrayLocation.length >= (arrayLocation[j] - negStart + 10 * array.length) % arrayLocation.length ) {
							arrayLocation[j]++;
							
							arrayLocation[j] = (arrayLocation[j] + 10 * array.length) % arrayLocation.length;
						}
					}
				}
				
				
				for(int j=0; j<(int)Math.abs(numMove); j++) {
					
					if(! isNegative) {
						array[(location + j)%array.length] = array[(location + j + 1)%array.length];
						
					} else {
						//sopl(location);
						//sopl("j = " + j);
						array[(location - j + 10 * array.length)%array.length] = array[(location - j - 1 + 10 * array.length)%array.length];
					}
					
					
					//arrayLocation[
				}
				sopl((location + numMove + 10 * array.length)%array.length + " goes to " + tmp);
				array[(location + numMove + 10 * array.length)%array.length] = tmp;

				sopl("pos " + i + " goes to " + tmpNewPosition);
				arrayLocation[i] = tmpNewPosition;
			}
			
			
			
			int num1 = array[(arrayLocation[zeroIndex] + 1000) % array.length];
			int num2 = array[(arrayLocation[zeroIndex] + 2000) % array.length];
			int num3 = array[(arrayLocation[zeroIndex] + 3000) % array.length];

			sopl(num1);
			sopl(num2);
			sopl(num3);
			
			count = num1 + num2 + num3;
			
			sopl("Answer: " + count);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}

	public static void sop(Object a) {
		System.out.print(a.toString());
	}
	public static void sopl(Object a) {
		System.out.println(a.toString());
	}

	public static void sopl() {
		System.out.println();
	}
	
	public static int pint(String s) {
		if (IsNumber.isNumber(s)) {
			return Integer.parseInt(s);
		} else {
			sop("Error: (" + s + " is not a number");
			return -1;
		}
	}

	public static long plong(String s) {
		if (IsNumber.isLong(s)) {
			return Long.parseLong(s);
		} else {
			sop("Error: (" + s + " is not a number");
			return -1;
		}
	}
	
	public static void exit() {
		exit(0);
	}
	public static void exit(int code) {
		sop("Exit with code " + code);
		
		System.exit(code);
	}

}
