package probs2021;
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

public class prob22bFaster {


	
	public static ArrayList<Boolean> settings = new ArrayList<Boolean>();
	public static ArrayList<Long> min = new ArrayList<Long>();
	public static ArrayList<Long> max = new ArrayList<Long>();
	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2021/prob2021in22.txt"));
			 //in = new Scanner(new File("in2021/prob2021in23.txt"));
			int numTimes = 0;
			 
			int count = 0;
			boolean part2 = false;

			LinkedList queue = new LinkedList();
			Stack stack = new Stack();
			HashSet set = new HashSet();
			
			
			Hashtable<Long, Integer> trail = new Hashtable<Long, Integer>();
			
			ArrayList <String>lines = new ArrayList<String>();
			
			
			//int LIMIT = 20000;
			//boolean table[][] = new boolean[LIMIT][LIMIT];
			
			
			//dir: 0 up
			//1 right
			//2 down
			//3 left
			
			//Put the data in variables... This makes the code about 100X faster!
			while(in.hasNextLine()) {
				String line = in.nextLine();
				lines.add(line);
				
				String token[] = line.split(" ");
				
				boolean setting = false;
				if(token[0].equals("on")) {
					setting = true;
				}
				
				settings.add(setting);
				
				String token2[] = token[1].split(",");
				
				long mins[] = new long[3];
				long maxes[] = new long[3];
				
				boolean stillGood = true;
				
				for(int i=0; i<3; i++) {
					String token3 = token2[i].split("=")[1];
					String token4[] = token3.split("\\.\\.");
					
					mins[i] = plong(token4[0]);
					maxes[i] =  plong(token4[1]);
					
					min.add(mins[i]);
					max.add(maxes[i]);
					
					if(mins[i] > maxes[i]) {
						stillGood = false;
					}
					
				}
			}
			
			//END put the data in variables
			
			//int BLOCK = 50;
			
			
			long origMins[] = new long[3];
			long origMaxes[] = new long[3];
			for(int i=0; i<3; i++) {
				origMins[i] = Long.MIN_VALUE;
				origMaxes[i] = Long.MAX_VALUE;
			}
			
			//for(int i=0; i<3; i++) {
			//	origMins[i] = -BLOCK;
			//	origMaxes[i] = BLOCK;
			//}
			
			long answer = getNaiveVolume(lines,  origMins, origMaxes);
			
			sopl("naive: " + answer);
			//Check 1 overlap:
			for(int numOverlap=1; numOverlap<lines.size(); numOverlap++) {
				long temp = getVolumeWithNOverlaps(lines, numOverlap, origMins, origMaxes, 0, false);

				sopl("Answer with " + numOverlap + " overlaps: " + temp);
				if(numOverlap % 2 == 1) {
					answer -= temp;
				} else {
					answer += temp;
				}
			
				sopl("Current answer: " + answer);
				
				if(temp ==0) {
					System.out.println("No more overlaps to consider");
					break;
				}
			}
			
			
			
			sopl("Answer: " + answer);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public static long getNaiveVolume(ArrayList <String>lines, long origMins[], long origMaxes[]) {
		long ret = 0L;
		
		for(int a=0; a<lines.size(); a++) {
			String line = lines.get(a);
			String token[] = line.split(" ");
			
			boolean setting = false;
			if(token[0].equals("on")) {
				setting = true;
			}
			
			String token2[] = token[1].split(",");
			
			long mins[] = new long[3];
			long maxes[] = new long[3];
			
			boolean stillGood = true;
			
			for(int i=0; i<3; i++) {

				mins[i] = Math.max(min.get(3*a+i), origMins[i]);
				maxes[i] =  Math.min(max.get(3*a+i), origMaxes[i]);
				
				if(mins[i] > maxes[i]) {
					stillGood = false;
				}
				
			}
			
			if(stillGood == false) {
				continue;
			}
			
			long curVolume = 1;
			
			for(int i=0; i<3; i++) {
				curVolume *= (maxes[i]-mins[i] + 1);
			}
			
			if(setting) {
				ret += curVolume;
			}
		}
		
		return ret;
	}
	
	public static long getVolumeWithNOverlaps(ArrayList <String>lines, int numOverlapsLeft, long origMins[], long origMaxes[], int startIndex , boolean wasOn) {
		
		if(wasOn == false && numOverlapsLeft ==0) {
			sopl("oops!");
			exit(1);
		} else if(startIndex+numOverlapsLeft>=lines.size()) {
			return 0L;
		}
		
		long ret = 0L;
		
		for(int a=startIndex; a<settings.size(); a++) {
			
			
			
			boolean setting = settings.get(a);
			
			
			long mins[] = new long[3];
			long maxes[] = new long[3];
			
			boolean stillGood = true;
			
			for(int i=0; i<3; i++) {
				
				mins[i] = Math.max(min.get(3*a+i), origMins[i]);
				maxes[i] =  Math.min(max.get(3*a+i), origMaxes[i]);
				
				if(mins[i] > maxes[i]) {
					stillGood = false;
				}
				
			}
			
			if(stillGood == false) {
				continue;
			}
			
			long curVolume = 1;
			
			for(int i=0; i<3; i++) {
				curVolume *= (maxes[i]-mins[i] + 1);
			}
			
			if(curVolume == 0) {
				exit(1);
				continue;
			}
			
			if(numOverlapsLeft == 0 && wasOn) {
				
				ret += curVolume;
				
			} else {
				if(setting == true) {
					ret += getVolumeWithNOverlaps(lines, numOverlapsLeft - 1, mins, maxes, a+1, true);
					
				} else if(wasOn) {
					ret += getVolumeWithNOverlaps(lines, numOverlapsLeft - 1, mins, maxes, a+1, true);
				} else {
					ret +=0;
				}
			}
			
		}
		
		
		
		return ret;
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
