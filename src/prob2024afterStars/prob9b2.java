package prob2024afterStars;
import java.io.File;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

import number.IsNumber;
import utils.Mapping;
import utils.Sort;

public class prob9b2 {

	//day1 part 1
	//2:38.01
	
	public static void main(String[] args) {
		Scanner in;
		try {
			//in = new Scanner(new File("in2024/prob2024in9.txt"));
			in = new Scanner(new File("in2024/prob2024in0.txt"));
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
			boolean table342[][] = new boolean[LIMIT][LIMIT];
			
			
			//dir: 0 up
			//1 right
			//2 down
			//3 left
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				
			}

			int most = 0;
			int most2 = 0;
			int most3 = 0;
			long cur = 0L;
			ArrayList ints = new ArrayList<Integer>();
			for(int i=0; i<lines.size(); i++) {
				
				
				line = lines.get(i);

				boolean data = true;
				
				int posIndex = 0;
				int blockID = 0;
				
				for(int j=0; j<line.length(); j++) {
					
					if(data) {
						//cur+= blockID * posIndex;
						//sopl(blockID + " * " + posIndex);
						
						blockID++;
						posIndex += (int)(line.charAt(j) - '0');
						
						
					} else {
						posIndex += (int)(line.charAt(j) - '0');
					}
					data = !data;
				}
				
				int table[] = new int[posIndex];
				//sopl(posIndex);
				for(int j=0; j<table.length; j++) {
					table[j] = -1;
				}
				
				posIndex = 0;
				blockID = 0;
				
				data = true;
				
				for(int j=0; j<line.length(); j++) {

					int numPos = (int)(line.charAt(j) - '0');
					
					if(data) {
						//cur+= blockID * posIndex;
						//sopl(blockID + " * " + posIndex);
						for(int k=posIndex; k<posIndex + numPos; k++) {
							table[k] = blockID;
						}
						
						blockID++;
						
						
						
					} else {
						for(int k=posIndex; k<posIndex + numPos; k++) {
							table[k] = -1;
						}
					}
					posIndex += numPos;
					data = !data;
				}
				
				for(int j=0; j<table.length; j++) {
					if(table[j] == -1) {
						sop('.');
					} else {
						sop(table[j]);
					}
				}
				sopl();
				
				//MOVE
				
				for(int backIndex=table.length - 1; backIndex >=0; ) {
					
					while(backIndex >= 0 && table[backIndex] == -1) {
						backIndex--;
					}
					if(backIndex <0) {
						break;
					}
					
					int origBack = table[backIndex];
					int k = backIndex;
					
					
					int sizeBack = 0;
					
					while(k >=0 && table[k] == origBack) {
						sizeBack++;
						k--;
					}
					
					for(int j=0; j<backIndex;) {

						if(table[j] != -1) {
							j++;
							continue;
						}
						
						int sizeFront = 0;
						k=j;
						while(k<table.length && table[k] == -1) {
							sizeFront++;
							k++;
						}
						
						if(sizeBack <= sizeFront) {
							for(k=0; k<sizeBack; k++) {
								table[j+k] = table[backIndex-k];
								table[backIndex-k] = -1;
							}
							break;
							
						}
						
						j+=sizeFront;
						
					}
					
					backIndex -= sizeBack;
				}
				//END MOVE

				for(int j=0; j<table.length; j++) {
					if(table[j] == -1) {
						sop('.');
					} else {
						sop(table[j]);
					}
				}
				sopl();
				
				for(int j=0; j<table.length; j++) {
					if( table[j] >= 0) {
						cur += j * table[j];
						sopl(j + " * " + table[j] + " = " + (j * table[j]));
					}
				}
			}


			sopl("Answer: " + cur);
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
			sop("Error: (" + s + ") is not a number");
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
