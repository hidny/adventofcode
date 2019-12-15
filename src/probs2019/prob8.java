package probs2019;
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

public class prob8 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2019/prob2019in8.txt"));
			 //in = new Scanner(new File("in2019/prob2019in8.txt.test"));
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
			
			int layerSize = 25*6;
			
			int indexFewest0s = 0;
			int fewest0s = layerSize;
			
			for(int i=0; layerSize*i<line.length(); i++) {
				
				int num0s = 0;
				for(int j=0; j<layerSize; j++) {
					if(line.charAt(layerSize * i + j) == '0') {
						num0s++;
					}
				}
				if(num0s < fewest0s) {
					indexFewest0s = layerSize * i;
					fewest0s = num0s;
				}
			}
			
			int num1s = 0;
			int num2s = 0;
			for(int j=0; j<layerSize; j++) {
				if(line.charAt(indexFewest0s + j) == '1') {
					num1s++;
				} else if(line.charAt(indexFewest0s + j) == '2') {
					num2s++;
				}
			}
			
			
			sopl("Answer: " + (num1s*num2s));
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
	
	public static void exit() {
		exit(0);
	}
	public static void exit(int code) {
		sop("Exit with code " + code);
		
		System.exit(code);
	}

}
