package probs2018;

import java.io.File;

import number.IsNumber;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import utils.Mapping;

public class prob24Side {

	
	ArrayList<prob24Group> groups = new ArrayList<prob24Group>();
	public String sideName;
	
	public prob24Side(String line) {
		this.sideName = line.split(":")[0];
	}
	
	public void addUnits(String line) {
		groups.add(new prob24Group(this.sideName, line));
	}
	
	
	public int numberStillLiving() {
		int ret = 0;
		for(int i=0; i<groups.size(); i++) {
			if(groups.get(i).units > 0) {
				ret++;
			}
		}
		return ret;
	}
	
	public boolean isAlive() {
		if(numberStillLiving() > 0) {
			return true;
		} else {
			return false;
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
