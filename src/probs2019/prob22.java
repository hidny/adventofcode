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

public class prob22 {

	
	public static void main(String[] args) {
		Scanner in;
		try {

			
			String filename = "in2019/prob2019in22.txt.test";
			//String filename = "in2019/prob2019in22.txt";
			
			 in = new Scanner(new File(filename));
			 //in = new Scanner(new File("in2019/prob2019in22.txt.test"));
		
			String line = "";

			ArrayList <String>lines = new ArrayList<String>();
			
			//dir: 0 up
			//1 right
			//2 down
			//3 left
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				
				
			}
			
			int numCards = -1;
			long numRepeats = -1;
			int part2PosTest = prob22part2.CARD_IN_POS_TEST;
			
			boolean isTestInput = false;
			if(filename.contains("test")) {
				numCards = prob22part2.NUM_CARDS_TEST;
				isTestInput = true;
				numRepeats = prob22part2.NUM_REPEATS_TEST;

			} else {
				numCards = 10007;
				numRepeats = 1;
			}
			
			int array[] = new int[numCards];
			
			int topOfDeckIndex = 0;
			
			//boolean rev = false;
			
			for(int i=0; i<array.length; i++) {
				array[i] = i;
			}
			
			
			
			for(int i=0; i<lines.size() * numRepeats; i++) {
				String cmd = lines.get(i % lines.size());
				
				if(cmd.startsWith("#")) {
					continue;
				}
				
				if(cmd.equals("deal into new stack")) {
					//rev = true;
					
					//TODO: might be a slow way to do this:
					int newArray[] = new int[array.length];
					
					for(int j=0; j<array.length; j++) {
						newArray[array.length - 1 - j] = array[(topOfDeckIndex + j) % array.length];
					}
					
					topOfDeckIndex = 0;
					
					array = newArray;
					
					
				} else if(cmd.startsWith("deal with increment")) {
					String tmp[] = cmd.split(" ");
					
					int stepNum = pint(tmp[tmp.length - 1]);
					int newArray[] = new int[array.length];
					
					//TODO: it's actually the mod inverse...
					//for(int j=0; j<array.length; j++) {
					//	newArray[j] = array[(stepNum * j) % array.length];
					//}
					
					for(int j=0; j<array.length; j++) {
						newArray[(stepNum * j) % array.length] = array[(topOfDeckIndex + j) % array.length];
					}
					
					topOfDeckIndex = 0;
					
					array = newArray;
					
				} else if(cmd.startsWith("cut")) {
					String tmp[] = cmd.split(" ");
					
					int cutNum = pint(tmp[tmp.length - 1]);
					
					topOfDeckIndex = (array.length + topOfDeckIndex + cutNum) % array.length;
				} else {
					sopl("ERROR: unknown command");
					exit(1);
				}
				
				
				sopl(cmd);
				if(isTestInput) {
					sop("Result: ");
					for(int j=0; j<array.length; j++) {
						sop(array[(topOfDeckIndex + j) % array.length] + " ");
					}
					sopl();
				}
			}
			
			int newArray[] = new int[array.length];
			
			//Put the top of deck on the top:
			for(int i=0; i<array.length; i++) {
				newArray[i] = array[(topOfDeckIndex + i) % array.length];
			}
			array = newArray;
			
			if(isTestInput) {
				sopl("Final:");
				sop("Result: ");
				for(int i=0; i<array.length; i++) {
					sop(array[i] + " ");
				}
				
				sopl();
				
				sopl("part2 should be " + array[part2PosTest]);
				sopl();
			} else {
				
				for(int i=0; i<array.length; i++) {
					if(array[i] == 2019) {
						sopl("Answer: " + i);
						break;
					}
				}
			}
			
			
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
