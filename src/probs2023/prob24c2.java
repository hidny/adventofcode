package probs2023;
import java.io.File;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

import number.IsNumber;
import utils.Mapping;
import utils.Sort;

public class prob24c2 {

	//day1 part 1
	//2:38.01
	
	public static void main(String[] args) {
		Scanner in;
		try {
			//in = new Scanner(new File("in2023/prob2023in24.txt"));
			in = new Scanner(new File("in2023/prob2023in0.txt"));
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
			
			ArrayList<String> vars = new ArrayList<String>();

			vars.add("xs");
			vars.add("ys");
			vars.add("zs");

			vars.add("x1");
			vars.add("y1");
			vars.add("z1");

			vars.add("x2");
			vars.add("y2");
			vars.add("z2");
			
			vars.add("x3");
			vars.add("y3");
			vars.add("z3");


			vars.add("t1");
			vars.add("t2");
			vars.add("t3");
			vars.add("result");
			
			long matrix[][] = new long[18][vars.size()];
			
			int curIndex = 0;
			
			int index1 = 0;
			int index2 = 1;
			int index3 = 2;
			
			int xIndex = 0;
			int yIndex = 1;
			int zIndex = 2;
			
			long vx = -987;
			long vy = -123;
			long vz = -456;
			
			/*long vx = -3;
			long vy = 1;
			long vz = 2;
			*/
			//TODO AHA: no need for z
			
			matrix[curIndex][symbolToIndex("x1", vars)] = 1L;
			matrix[curIndex][symbolToIndex("xs", vars)] = -1L;
			matrix[curIndex][symbolToIndex("t1", vars)] = -vx;
			matrix[curIndex][symbolToIndex("result", vars)] = 0;
			curIndex++;
			
			matrix[curIndex][symbolToIndex("x2", vars)] = 1L;
			matrix[curIndex][symbolToIndex("xs", vars)] = -1L;
			matrix[curIndex][symbolToIndex("t2", vars)] = -vx;
			matrix[curIndex][symbolToIndex("result", vars)] = 0;
			curIndex++;
			

			matrix[curIndex][symbolToIndex("x3", vars)] = 1L;
			matrix[curIndex][symbolToIndex("xs", vars)] = -1L;
			matrix[curIndex][symbolToIndex("t3", vars)] = -vx;
			matrix[curIndex][symbolToIndex("result", vars)] = 0;
			curIndex++;

			//y

			matrix[curIndex][symbolToIndex("y1", vars)] = 1L;
			matrix[curIndex][symbolToIndex("ys", vars)] = -1L;
			matrix[curIndex][symbolToIndex("t1", vars)] = -vy;
			matrix[curIndex][symbolToIndex("result", vars)] = 0;
			curIndex++;
			
			matrix[curIndex][symbolToIndex("y2", vars)] = 1L;
			matrix[curIndex][symbolToIndex("ys", vars)] = -1L;
			matrix[curIndex][symbolToIndex("t2", vars)] = -vy;
			matrix[curIndex][symbolToIndex("result", vars)] = 0;
			curIndex++;
			

			matrix[curIndex][symbolToIndex("y3", vars)] = 1L;
			matrix[curIndex][symbolToIndex("ys", vars)] = -1L;
			matrix[curIndex][symbolToIndex("t3", vars)] = -vy;
			matrix[curIndex][symbolToIndex("result", vars)] = 0;
			curIndex++;
			

			
			//z

			matrix[curIndex][symbolToIndex("z1", vars)] = 1L;
			matrix[curIndex][symbolToIndex("zs", vars)] = -1L;
			matrix[curIndex][symbolToIndex("t1", vars)] = -vz;
			matrix[curIndex][symbolToIndex("result", vars)] = 0;
			curIndex++;
			

			matrix[curIndex][symbolToIndex("z2", vars)] = 1L;
			matrix[curIndex][symbolToIndex("zs", vars)] = -1L;
			matrix[curIndex][symbolToIndex("t2", vars)] = -vz;
			matrix[curIndex][symbolToIndex("result", vars)] = 0;
			curIndex++;

			matrix[curIndex][symbolToIndex("z3", vars)] = 1L;
			matrix[curIndex][symbolToIndex("zs", vars)] = -1L;
			matrix[curIndex][symbolToIndex("t3", vars)] = -vz;
			matrix[curIndex][symbolToIndex("result", vars)] = 0;
			curIndex++;
			
			
			//x

			matrix[curIndex][symbolToIndex("x1", vars)] = 1L;
			matrix[curIndex][symbolToIndex("t1", vars)] = -trajs.get(index1).vel[xIndex];
			matrix[curIndex][symbolToIndex("result", vars)] = trajs.get(index1).start[xIndex];
			curIndex++;
			
			matrix[curIndex][symbolToIndex("x2", vars)] = 1L;
			matrix[curIndex][symbolToIndex("t2", vars)] = -trajs.get(index2).vel[xIndex];
			matrix[curIndex][symbolToIndex("result", vars)] = trajs.get(index2).start[xIndex];
			curIndex++;
			

			matrix[curIndex][symbolToIndex("x3", vars)] = 1L;
			matrix[curIndex][symbolToIndex("t3", vars)] = -trajs.get(index3).vel[xIndex];
			matrix[curIndex][symbolToIndex("result", vars)] = trajs.get(index3).start[xIndex];
			curIndex++;
			
			//y:
			matrix[curIndex][symbolToIndex("y1", vars)] = 1L;
			matrix[curIndex][symbolToIndex("t1", vars)] = -trajs.get(index1).vel[yIndex];
			matrix[curIndex][symbolToIndex("result", vars)] = trajs.get(index1).start[yIndex];
			curIndex++;
			
			matrix[curIndex][symbolToIndex("y2", vars)] = 1L;
			matrix[curIndex][symbolToIndex("t2", vars)] = -trajs.get(index2).vel[yIndex];
			matrix[curIndex][symbolToIndex("result", vars)] = trajs.get(index2).start[yIndex];
			curIndex++;
			

			matrix[curIndex][symbolToIndex("y3", vars)] = 1L;
			matrix[curIndex][symbolToIndex("t3", vars)] = -trajs.get(index3).vel[yIndex];
			matrix[curIndex][symbolToIndex("result", vars)] = trajs.get(index3).start[yIndex];
			curIndex++;
			

			
			//z:
			matrix[curIndex][symbolToIndex("z1", vars)] = 1L;
			matrix[curIndex][symbolToIndex("t1", vars)] = -trajs.get(index1).vel[zIndex];
			matrix[curIndex][symbolToIndex("result", vars)] = trajs.get(index1).start[zIndex];
			curIndex++;
			
			matrix[curIndex][symbolToIndex("z2", vars)] = 1L;
			matrix[curIndex][symbolToIndex("t2", vars)] = -trajs.get(index2).vel[zIndex];
			matrix[curIndex][symbolToIndex("result", vars)] = trajs.get(index2).start[zIndex];
			curIndex++;
			

			matrix[curIndex][symbolToIndex("z3", vars)] = 1L;
			matrix[curIndex][symbolToIndex("t3", vars)] = -trajs.get(index3).vel[zIndex];
			matrix[curIndex][symbolToIndex("result", vars)] = trajs.get(index3).start[zIndex];
			curIndex++;
			

			sopl();
			for(int i=0; i<matrix.length; i++) {
				for(int j=0; j<matrix[0].length; j++) {
					sop(matrix[i][j] + ", ");
				}
				sopl();
			}
			sopl();
			
			sopl(convertToPythonFormat(matrix));
			
			sopl(matrix.length + " by " + matrix[0].length);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public static String convertToPythonFormat(long matrix[][]) {
		String ret = "[";
		
		for(int i=0; i<matrix.length; i++) {
			
			ret += "[";
			
			for(int j=0; j<matrix[0].length; j++) {
				
				if(j<matrix[0].length - 1) {
					ret += " " + matrix[i][j] + ",";
				} else {
					ret += " " + matrix[i][j];
				}
			}
			
			if(i< matrix.length - 1) {
				ret += "],";
			} else {
				ret += "]";
			}
		}
		
		ret += "]";
		
		return ret;
	}
	
	
	
	public static int symbolToIndex(String symbol, ArrayList<String> labels) {
		for(int i=0; i<labels.size(); i++) {
			if(labels.get(i).equals(symbol)) {
				return i;
			}
		}
		
		return -1;
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
