package probs2024;
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

public class prob23 {

	//day1 part 1
	//2:38.01
	
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2024/prob2024in23.txt"));
			//in = new Scanner(new File("in2024/prob2024in0.txt"));
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
			boolean table342[][] = new boolean[LIMIT][LIMIT];
			
			
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
			ArrayList ints = new ArrayList<Integer>();
			
			Hashtable<String, Integer> labelsToIndex = new Hashtable<String, Integer>();
			Hashtable<Integer, String> indexToLabel = new Hashtable<Integer, String>();
			
			int curIndex = 0;
			for(int i=0; i<lines.size(); i++) {
				
				
				line = lines.get(i);
				
				String label1 = line.split("-")[0];
				String label2 = line.split("-")[1];
				
				if(labelsToIndex.containsKey(label1) == false) {
					labelsToIndex.put(label1, curIndex);
					indexToLabel.put(curIndex, label1);
					curIndex++;
				}
				
				if(labelsToIndex.containsKey(label2) == false) {
					labelsToIndex.put(label2, curIndex);
					indexToLabel.put(curIndex, label2);
					curIndex++;
				}
			}
			int numElements = labelsToIndex.size();
			boolean connections[][] = new boolean[numElements][numElements];
			
			
			for(int i=0; i<lines.size(); i++) {
				
				
				line = lines.get(i);
				
				String label1 = line.split("-")[0];
				String label2 = line.split("-")[1];
				
				int index1 = labelsToIndex.get(label1);
				int index2 = labelsToIndex.get(label2);
				
				connections[index1][index2] = true;
				connections[index2][index1] = true;
			}
			
			boolean connectionsWithThree[] = new boolean[numElements];
			
			for(int i=0; i<numElements; i++) {
				
				for(int j=i+1; j<numElements; j++) {
					
					for(int k=j+1; k<numElements; k++) {
						
						if(indexToLabel.get(i).startsWith("t") || indexToLabel.get(j).startsWith("t") || indexToLabel.get(k).startsWith("t")) {
							
							if(connections[i][j] && connections[j][k] && connections[k][i]) {
								cur++;
							}
							
						} else {
							continue;
						}
						
					}
				}
			}
			//2364


			sopl("Answer: " + cur);
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
	
	public static int[][] getIntTable(ArrayList<String> lines) {
		int grid[][] = new int[lines.size()][lines.get(0).length()];
		
		for(int i=0; i<lines.size(); i++) {
			
			for(int j=0; j<grid[0].length; j++) {
				if(lines.get(i).charAt(j) == '.') {
					grid[i][j] = -1;
				} else {
					grid[i][j] = (int)(lines.get(i).charAt(j) - '0');
				}
			}
		}
		
		return grid;
	}
	

	public static char[][] getCharTable(ArrayList<String> lines) {
		char grid[][] = new char[lines.size()][lines.get(0).length()];
		
		for(int i=0; i<lines.size(); i++) {
			
			for(int j=0; j<grid[0].length; j++) {
				grid[i][j] = lines.get(i).charAt(j);

			}
		}
		
		return grid;
	}

}
