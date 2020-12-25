package probs2020;
import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import number.IsNumber;
import utils.Mapping;
import utils.Sort;

public class prob25 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2020/prob2020in25.txt"));

			int count = 0;

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
			
			while(in.hasNextLine()) {
				String line = in.nextLine();
				lines.add(line);
				
				
			}
			
			
			ArrayList<Integer> ints = new ArrayList<Integer>();
			
			for(int i=0; i<lines.size(); i++) {
				
				//int temp = Integer.parseInt(lines.get(i));
				//count+=temp;
				
				String line = lines.get(i);
				String lineCopy = String.copyValueOf(line.toCharArray());

				
			}
			
			int subjectNumber = 7;
			
			
			//int pub1 = 5764801;
			
			//int pub2 = 17807724;
			
			int pub1 = 8335663;
			int pub2 = 8614349;
			
			int N = 20201227;
			
			//BigInteger sub1 = utilsPE.Combination.getAPowerPmodMOD(new BigInteger(subjectNumber+ ""), new BigInteger(pub1 + ""), new BigInteger(N + ""));
			
			//BigInteger sub2 = utilsPE.Combination.getAPowerPmodMOD(new BigInteger(subjectNumber+ ""), new BigInteger(pub2 + ""), new BigInteger(N + ""));
			
			
			BigInteger current = new BigInteger(subjectNumber+ "");
			
			int numLoops = 1;
			
			while(current.compareTo(new BigInteger("" + pub1)) != 0) {
				
				current=current.multiply(new BigInteger(subjectNumber+ "")).divideAndRemainder(new BigInteger(N + ""))[1];
				//sopl(current);
				//sopl();
				numLoops++;
			}
			
			sopl("numLoops: " + numLoops);
			
			BigInteger current2 = new BigInteger(subjectNumber+ "");
			int numLoops2 = 1;
			
			while(current2.compareTo(new BigInteger("" + pub2)) != 0) {
				
				current2=current2.multiply(new BigInteger(subjectNumber+ "")).divideAndRemainder(new BigInteger(N + ""))[1];
				//sopl(current);
				//sopl();
				numLoops2++;
			}
			
			sopl("numLoops2: " + numLoops2);
			
			
			
			BigInteger answer = utilsPE.Combination.getAPowerPmodMOD(new BigInteger(pub1+ ""), (new BigInteger(numLoops2 + "")), new BigInteger(N + ""));

			BigInteger answer2 = utilsPE.Combination.getAPowerPmodMOD(new BigInteger(pub2+ ""), (new BigInteger(numLoops + "")), new BigInteger(N + ""));
			
			sopl("Answer: " + answer);
			sopl("Answer2: " + answer2);
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
