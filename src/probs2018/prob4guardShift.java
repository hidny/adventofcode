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

public class prob4guardShift implements Comparable {

	public int id;
	
	public String date;

	public ArrayList<String> entries = new ArrayList<String>();

	boolean sleep[] = new boolean[60];
	
	public prob4guardShift(int id, String date) {
		this.id = id;
		this.date = date;
	}
	
	
	@Override
	public int compareTo(Object o) {
		prob4guardShift otherDate = (prob4guardShift)o;
		
		if(isDateAfter(otherDate.date)) {
			return -1;
		} else {
			return 1;
		}
	}
	
	public boolean isDateAfter(String dateIn) {
		
		
		return isDateAfter(this.date, dateIn);
	}
	
	public static boolean isDateAfter(String orig, String dateIn) {
		long date2 = lint(dateIn.replaceAll("-", "").replaceAll(" ", "").replaceAll(":", ""));
		
		long date1 = lint(orig.replaceAll("-", "").replaceAll(" ", "").replaceAll(":", ""));
		
		if(date2 > date1) {
			return true;
		} else if(date1 > date2){
			return false;
		} else {
			return false;
		}
	}
	
	public void addEntry(String entry) {
		String date = entry.split("]")[0].substring(1);
		
		for(int i=0; i<entries.size();i++) {
			String dateEntry = entries.get(i).split("]")[0].substring(1);
			
			if(isDateAfter(date, dateEntry)) {
				entries.add(i, entry);
				return;
			}
		}
		
		entries.add(entry);
	}
	
	
	//pre: entries are in order
	public int getSleepTime() {
		int sleepTime = 0;
		
		for(int i=0; i<entries.size();i++) {
			
			int index1 = pint(entries.get(i).split("]")[0].substring(1).split(":")[1]);
			
			if(entries.get(i).contains("wakes")) {
				System.out.println("SHIT!");
				System.exit(1);
			}
			
			i+=1;
			int index2;
			if(i<entries.size()) {
				index2 = pint(entries.get(i).split("]")[0].substring(1).split(":")[1]);
				
				if(entries.get(i).contains("sleeps")) {
					System.out.println("SHIT!");
					System.exit(1);
				}
			} else {
				index2 = 60;
			}
			
			for(int j=index1; j<index2; j++) {
				sleep[j] = true;
			}
			sleepTime += (index2-index1);
			
		}
		
		return sleepTime;
	}
	

	public static int pint(String s) {
		if (IsNumber.isNumber(s)) {
			return Integer.parseInt(s);
		} else {
			System.out.println("Error: (" + s + " is not a number");
			return -1;
		}
	}
	
	public static long lint(String s) {
		if (IsNumber.isLong(s)) {
			return Long.parseLong(s);
		} else {
			System.out.println("Error: (" + s + ") is not a long");
			return -1;
		}
	}
	
}
