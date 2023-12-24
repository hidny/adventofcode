package probs2023;
import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

import chineseRemainderTheorem.CRTTuple;
import chineseRemainderTheorem.ChineseRemainderTheoremSolver;
import chineseRemainderTheorem.ChineseRemainderTheoremSolverBigInt;
import number.IsNumber;
import utils.Mapping;
import utils.Sort;

public class prob24b {

	//day1 part 1
	//2:38.01
	
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2023/prob2023in24.txt"));
			//in = new Scanner(new File("in2023/prob2023in0.txt"));
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
			int table[][] = new int[LIMIT][LIMIT];
			
			
			//dir: 0 up
			//1 right
			//2 down
			//3 left
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				
			}

			int most = 0;
			int most2 = 0;
			int most3 = 0;
			long cur = 0L;
			
			ArrayList <prob24obj> trajs = new ArrayList <prob24obj>();
			ArrayList ints = new ArrayList<Integer>();
			for(int i=0; i<lines.size(); i++) {
				
				
				line = lines.get(i);
				
				String tokens[] = line.trim().split("@");
				
				String token1[] = tokens[0].trim().split(",");

				String token2[] = tokens[1].trim().split(",");
				
				prob24obj tmp = new prob24obj();
				for(int j=0; j<token1.length; j++) {
					tmp.start[j] = plong(token1[j].trim());
				}
				for(int j=0; j<token2.length; j++) {
					tmp.vel[j] = plong(token2[j].trim());
				}
				
				trajs.add(tmp);
			}

			double min = 200000000000000.0;
			double max = 400000000000000.0;
			
			//double min = 7.0;
			//double max = 27.0;
			cur = 0L;
			sopl("hello");
			
			
			ArrayList<CRTTuple> equationsX = new ArrayList<CRTTuple>();
			
			HashSet<Long> divisorsUsed = new HashSet<Long>();
			
			//int debug = 0;
			
			for(int i=0; i<trajs.size(); i++) {
				
				
				long divisors[] = getPrimeDivisors((long)Math.abs(trajs.get(i).vel[0]));
				
				for(int j=0; j<divisors.length; j++) {
					if(divisorsUsed.contains(divisors[j])) {
						continue;
					}
					
					//if(debug>20) {
					//	continue;
					//}
					long curDiv = divisors[j];
					
					equationsX.add(new CRTTuple(((trajs.get(i).start[0] % curDiv) + curDiv) % curDiv, (long)(curDiv)));
					
					divisorsUsed.add(curDiv);

					
					sopl(trajs.get(i).start[0] + " == " + ((trajs.get(i).start[0] % curDiv) + curDiv) % curDiv + " mod " + (long)(curDiv));
					
					//debug++;
				}
			}
			
			BigInteger[] xAnswer = ChineseRemainderTheoremSolverBigInt.solve(equationsX);
			

			sopl(xAnswer[0]);
			sopl(xAnswer[1]);
			
			for(int i=0; i<equationsX.size(); i++) {
				for(int j=i+1; j<equationsX.size(); j++) {
					
					if(equationsX.get(i).getMod() > equationsX.get(j).getMod()) {
						CRTTuple tmp = equationsX.get(i);
						
						equationsX.set(i, equationsX.get(j));
						equationsX.set(j, tmp);
						
					}
					
				}
			}
			
			for(int i=0; i<equationsX.size(); i++) {
				sopl(equationsX.get(i));
			}
			
			/*
			 * 
466524257155584768566309182018
518680737162987298208545326090
			 */
			sopl("----");
			//CRTTuple xAnswer2 = ChineseRemainderTheoremSolver.solve(equationsX);
			
			//sopl(xAnswer2);
			
			
			
			//x 4672 mod 6603
			
			//x ≡ 222571 (mod 270723).
			
			sopl("Answer: " + cur);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}


	public static long[] getPrimeDivisors(long n) {
		long nTemp = n;
		long LIMIT = (long)Math.sqrt(n);
		
		int numPrimeDivisors = 0;
		for(long i=2; i<=LIMIT; i++) {
			if(nTemp % i == 0 && isPrime(i)) {
				numPrimeDivisors++;
				while(nTemp % i == 0) {
					nTemp /= i;
				}
			}
		}
		if(nTemp > 1) {
			numPrimeDivisors++;
		}
		
		long divisors[] = new long[numPrimeDivisors];
		
		nTemp = n;
		int currentIndex = 0;
		for(long i=1; i<=LIMIT; i++) {
			if(nTemp % i == 0 && isPrime(i)) {
				divisors[currentIndex] = i;
				currentIndex++;
				while(nTemp % i == 0) {
					nTemp /= i;
				}
			}
		}
		if(nTemp > 1) {
			divisors[currentIndex] = nTemp;
			currentIndex++;
		}
		
		
		return divisors;
	}
	
	public static boolean isPrime(long num) {
		if(num<=1) {
			return false;
		}
		
		int sqrt = (int)Math.sqrt(num);
		for(int i=2; i<=sqrt ; i++) {
			if(num%i == 0) {
				return false;
			}
		}
		
		return true;
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
			sop("Error: (" + s + ") is not a number");
			return -1;
		}
	}
	
	public static long plong(String s) {
		if (IsNumber.isLong(s)) {
			return Long.parseLong(s);
		} else {
			sop("Error: (" + s + ") is not a number");
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
