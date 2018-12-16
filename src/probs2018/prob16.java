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

public class prob16 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2018/prob2018in16.txt"));
			
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
			
			//dir: 0 up
			//1 right
			//2 down
			//3 left
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				
				if(line.contains("Before: ") ) {
					//sopl(line);
					
					
					String state1 = line.split("\\[")[1].split("\\]")[0];
					
					line = in.nextLine();
					lines.add(line);
					String command = line;

					line = in.nextLine();
					String state2 = (line.split("\\[")[1]).split("\\]")[0];
				
					sopl(state1);
					sopl(command);
					sop(state2);
					sopl("---");
					
					int numMatches = getNumMatches(state1, command, state2);
					if(numMatches >= 3) {
						count++;
					}
				}
			}
			
			
			sopl("Answer: " + count);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public static int getNumMatches(String state1, String command, String state2) {
		String regString[] = state1.split(",");
		int regS[] = new int[regString.length];
		for(int i=0; i<regS.length ; i++) {
			regS[i] = pint(regString[i].trim());
		}
		String regFinalString[] = state2.split(",");
		int regF[] = new int[regFinalString.length];
		for(int i=0; i<regF.length ; i++) {
			regF[i] = pint(regFinalString[i].trim());
		}
		
		String regCommandString[] = command.split(" ");
		
		int regCommand[] = new int[regCommandString.length];
		for(int i=0; i<regCommand.length ; i++) {
			regCommand[i] = pint(regCommandString[i].trim());
		}
		
		
		int ret = 0;
		
		int temp[];
		//add r:
		temp = resetTemp(regS);
		temp[regCommand[3]] = temp[regCommand[1]] + temp[regCommand[2]];
		
		if(checkEq(temp, regF)) {
			ret++;
		}
		

		//add i:
		temp = resetTemp(regS);
		
		temp[regCommand[3]] = temp[regCommand[1]] + regCommand[2];
		
		if(checkEq(temp, regF)) {
			ret++;
		}
		
		//mulr:
		temp = resetTemp(regS);
		temp[regCommand[3]] = temp[regCommand[1]] * temp[regCommand[2]];
		
		if(checkEq(temp, regF)) {
			ret++;
		}
		
		//muli:
		temp = resetTemp(regS);
		
		temp[regCommand[3]] = temp[regCommand[1]] * regCommand[2];
		
		if(checkEq(temp, regF)) {
			ret++;
		}
		
		//bitandr
		temp = resetTemp(regS);
		
		temp[regCommand[3]] = temp[regCommand[1]] & temp[regCommand[2]];
		
		if(checkEq(temp, regF)) {
			ret++;
		}
		
		//bit andi
		temp = resetTemp(regS);
		
		temp[regCommand[3]] = temp[regCommand[1]] & regCommand[2];
		
		if(checkEq(temp, regF)) {
			ret++;
		}
		
		
		//bitor r
		temp = resetTemp(regS);
		
		temp[regCommand[3]] = temp[regCommand[1]] | temp[regCommand[2]];
		
		if(checkEq(temp, regF)) {
			ret++;
		}
		
		//bit or i
		temp = resetTemp(regS);
		
		temp[regCommand[3]] = temp[regCommand[1]] | regCommand[2];
		
		if(checkEq(temp, regF)) {
			ret++;
		}
		
		
		//store r
		temp = resetTemp(regS);
		
		temp[regCommand[3]] = temp[regCommand[1]];
		
		if(checkEq(temp, regF)) {
			ret++;
		}
		
		//store or i
		temp = resetTemp(regS);
		
		temp[regCommand[3]] = regCommand[1];
		
		if(checkEq(temp, regF)) {
			ret++;
		}
				
		
		//greater than
		//gtir
		temp = resetTemp(regS);
		
		if(regCommand[1] > temp[regCommand[2]]) {
			temp[regCommand[3]] = 1;
		} else {
			temp[regCommand[3]] = 0;
		}
		
		if(checkEq(temp, regF)) {
			ret++;
		}
		
		//gtri
		temp = resetTemp(regS);
		
		if(temp[regCommand[1]] > regCommand[2]) {
			temp[regCommand[3]] = 1;
		} else {
			temp[regCommand[3]] = 0;
		}
		
		if(checkEq(temp, regF)) {
			ret++;
		}
		
		//gtrr
		temp = resetTemp(regS);
		
		if(temp[regCommand[1]] > temp[regCommand[2]]) {
			temp[regCommand[3]] = 1;
		} else {
			temp[regCommand[3]] = 0;
		}
		
		if(checkEq(temp, regF)) {
			ret++;
		}
		
		
		//Equality:
		//gtir
		temp = resetTemp(regS);
		
		if(regCommand[1] == temp[regCommand[2]]) {
			temp[regCommand[3]] = 1;
		} else {
			temp[regCommand[3]] = 0;
		}
		
		if(checkEq(temp, regF)) {
			ret++;
		}
		
		//gtri
		temp = resetTemp(regS);
		
		if(temp[regCommand[1]] == regCommand[2]) {
			temp[regCommand[3]] = 1;
		} else {
			temp[regCommand[3]] = 0;
		}
		
		if(checkEq(temp, regF)) {
			ret++;
		}
		
		//gtrr
		temp = resetTemp(regS);
		
		if(temp[regCommand[1]] == temp[regCommand[2]]) {
			temp[regCommand[3]] = 1;
		} else {
			temp[regCommand[3]] = 0;
		}
		
		if(checkEq(temp, regF)) {
			ret++;
		}
		
		return ret;			
	}
	
	public static int[] resetTemp(int regS[]) {
		int temp[] = new int[regS.length];
		
		for(int i=0; i<temp.length; i++) {
			temp[i] = regS[i];
		}
		return temp;
	}
	
	
	public static boolean checkEq(int temp[], int regF[]) {
		
		for(int i=0; i<temp.length; i++) {
			if(temp[i] != regF[i]) {
				return false;
			}
		}
		return true;
	}
	
	public static void sop(Object a) {
		System.out.println(a.toString());
	}
	public static void sopl(Object a) {
		System.out.println(a.toString());
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
