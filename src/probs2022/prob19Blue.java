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

public class prob19Blue {

	public int oreOre=-1;

	public int clayOre=-1;
	
	public int obsOre = -1;
	public int obsClay = -1;
	
	public int geodeOre = -1;
	public int geodeObs = -1;
	
	
	public prob19Blue(int oreOre, int clayOre, int obsOre, int obsClay, int geodeOre, int geodeObs) {
		super();
		this.oreOre = oreOre;
		this.clayOre = clayOre;
		this.obsOre = obsOre;
		this.obsClay = obsClay;
		this.geodeOre = geodeOre;
		this.geodeObs = geodeObs;
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
