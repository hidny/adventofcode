package probs2018;

import number.IsNumber;
import java.io.File;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import utils.Mapping;
import utils.Sort;

public class prob4part2 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2018/prob2018in4.txt"));
			
			int count = 0;
			boolean part2 = false;
			String line = "";

			LinkedList queue = new LinkedList();
			Stack stack = new Stack();
			HashSet set = new HashSet();
			
			Mapping dict = new Mapping();
			ArrayList <String>lines = new ArrayList<String>();
			
			
			int LIMIT = 20000;
			boolean table[][] = new boolean[LIMIT][LIMIT];
			
			Hashtable<Long, Integer> trail = new Hashtable<Long, Integer>();
			
			
			ArrayList guardsArrayList = new ArrayList<prob4guardShift>();
			
			//dir: 0 up
			//1 right
			//2 down
			//3 left
			
			
			//use stacks for guards.
			
			
			ArrayList shift = new ArrayList<Boolean[]>();
			
			
			ArrayList numbers = new ArrayList<Double>();
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				
				String parse[] = line.split(" ");
				
				if(line.contains("Guard")) {
					int id = (int)pint(parse[3].split("#")[1]);
					String date = line.split("]")[0].substring(1);
					guardsArrayList.add(new prob4guardShift(id, date));
				}
			}
			
			prob4guardShift guards[] = new prob4guardShift[guardsArrayList.size()];
			
			for(int i=0; i<guards.length; i++) {
				guards[i] = (prob4guardShift)guardsArrayList.get(i);
			}
			
			guards = (prob4guardShift[])Sort.quickSort(guards);
			
			for(int i=0; i<guards.length; i++) {
				System.out.println(guards[i].date);
			}
			
			//for(int i=0; i<guards.length; i++) {
			//	System.out.println(guards[i].date);
			//}
			
			for(int i=0; i<lines.size(); i++) {
				line = lines.get(i);
				if(line.contains("Guard") == false) {
					String date = line.split("]")[0].substring(1);
					
					prob4guardShift currentGuard = guards[getIndexGuard(guards, date)];
					
					currentGuard.addEntry(line);
					
					sop(guards[getIndexGuard(guards, date)].date);
					sop(date);
					sop("");
					
					
				}
				
				
			}
			
			for(int i=0; i<guards.length; i++) {
				System.out.println(guards[i].date);
				for(int j=0; j<guards[i].entries.size(); j++) {
					sop(guards[i].entries.get(j));
				}
				sop("");
				sop("");
			}
			
			for(int i=0; i<guards.length; i++) {
				int index = guards[i].id;
				
				guards[i].getSleepTime();
			}
			
			int sleepAmounts[][] = new int[10000][60];
			
			for(int i=0; i<guards.length; i++) {
				int index = guards[i].id;
				prob4guardShift currentGuard = guards[i];
				
				for(int j=0; j<60; j++) {
					if(currentGuard.sleep[j]) {
						sleepAmounts[index][j]++;
						sop("1");
					}
				}
			}

			int maxGuardSleepIndex=0;
			int maxSleepIndex=0;
			
			for(int i=0; i<sleepAmounts.length; i++) {

				for(int j=0; j<sleepAmounts[i].length; j++) {
					//sop("1");
					if(sleepAmounts[i][j] > sleepAmounts[maxGuardSleepIndex][maxSleepIndex]) {
						maxGuardSleepIndex = i;
						maxSleepIndex = j;
					}
				}

			}
			sop("hello");
			sop("Answer: " + (maxGuardSleepIndex * maxSleepIndex));
			
			//goal 1: guard most asleep:
			/*
			int sleepAmounts[] = new int[10000];
			//TODO initialize
			
			for(int i=0; i<guards.length; i++) {
				int index = guards[i].id;
				
				sleepAmounts[index] += guards[i].getSleepTime();
			}
			
			int indexMostSleep=0;
			
			for(int i=1; i<sleepAmounts.length; i++) {
				if(sleepAmounts[i] > sleepAmounts[indexMostSleep]) {
					indexMostSleep = i;
				}
			}
			
			System.out.println("Guard #" + indexMostSleep + " sleeps the most");
			
			int sleepTimes[] = new int[60];
			
			for(int i = 0; i<guards.length; i++) {
				prob4guardShift currentGuard = guards[i];
				
				if(currentGuard.id == indexMostSleep) {
					for(int j=0; j<60; j++) {
						if(currentGuard.sleep[j]) {
							sleepTimes[j]++;
						}
					}
				}
			}
			
			int maxSleepIndex=0;
			for(int j=0; j<60; j++) {
				if(sleepTimes[j] > sleepTimes[maxSleepIndex]) {
					maxSleepIndex = j;
				}
			}
			
			System.out.println("Max sleep minute:" + maxSleepIndex);
			
			sop("Answer: " + (maxSleepIndex * indexMostSleep));
			*/
			
			/*

			long sortedList[] = new long[numbers.size()];
			for(int i=0; i<numbers.size(); i++) {
				sortedList[i] = (int)numbers.get(i);
				System.out.println(sortedList[i]);
			}
			sortedList = utils.Sort.sort(sortedList);
			
			for(int i=0; i<sortedList.length - 1; i++) {
				if(sortedList[i] == sortedList[i+1]) {
				}
				sop(sortedList[i]);
			}
			*/
			
			int origCount = 0;
			
			
			
			//sopl("Answer: " + count);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public static void sop(Object a) {
		System.out.println(a.toString());
	}
	public static void sopl(Object a) {
		System.out.println(a.toString());
	}
	
	public static long pint(String s) {
		if (IsNumber.isNumber(s)) {
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
	
	
	public static int getIndexGuard(prob4guardShift guards[], String date) {
		return getIndexGuard(guards, date, 0, guards.length);
	}
	
	public static int getIndexGuard(prob4guardShift guards[], String date, int min, int max) {
		if(guards[(max + min) /2].isDateAfter(date) ) {
			if(max > min + 1) {
				return getIndexGuard(guards, date, (max + min) /2, max);
			} else {
				return min;
			}
		} else {
			return getIndexGuard(guards, date, min, (max + min) /2);
		}
	}
}
