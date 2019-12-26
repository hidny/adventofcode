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

import probs2019after1am.*;

public class prob21InputGenerator implements IntCodeInputReader {

	public Scanner userIn = new Scanner(System.in);
	
	public IntCode intCode = null;
	
	public prob21InputGenerator(IntCode intCode) {
		this.intCode = intCode;
	}
	
	@Override
	public long getInput() {
		
		String nextInst = userIn.nextLine();
		
		nextInst += "\n";
		long ret = (long)nextInst.charAt(0);
		for(int i=1; i<nextInst.length(); i++) {
			
			intCode.inputQueue.add((long)nextInst.charAt(i));
			//sop((char)nextInst.charAt(i));
			
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
	
	public static void exit() {
		exit(0);
	}
	public static void exit(int code) {
		sop("Exit with code " + code);
		
		System.exit(code);
	}

}
