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

public class prob13b {

	//day1 part 1
	//2:38.01
	
	public static void main(String[] args) {
		Scanner in;
		sopl("start");
		try {
			in = new Scanner(new File("in2023/prob2023in13.txt"));
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
			boolean table[][] = new boolean[LIMIT][LIMIT];
			
			
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
			for(int i=0; i<lines.size(); i++) {
				
				
				line = lines.get(i);
				
				
			}

			boolean matrix[][] = getNextMatrix(lines, 0);
			
			cur = 0L;
			for(int i=0; i<lines.size(); i++) {
				
				if(lines.get(i).trim().equals("")) {
					
					//Calc:
					//-1 means no:
					int hori = getHoriReflect(matrix);

					int vert = getVertReflect(matrix);
					
					if(hori != -1) {
						cur += 100*(hori+1);
					}
					
					if(vert!=-1) {
						cur += vert + 1;
					}
					
					if(hori != -1 && vert != -1) {
						sopl("Warning");
						exit(1);
					}
					
					sopl("hori: " + (hori+1));
					sopl("vert: " + (vert+1));
					
					for(int i2=0; i2<matrix.length; i2++) {
						
						if(i2<9) {
							sop((i2 + 1) + "  ");
						} else {
							sop((i2 + 1)+ " ");
						}
						for(int j2=0; j2<matrix[0].length; j2++) {
							if(matrix[i2][j2]) {
								sop("#");
							} else {
								sop(".");
							}
						}
						sopl();
					}
					sopl();
					
					sopl(cur);
					
					matrix = getNextMatrix(lines, i+1);
					
					
				}
				
				
			}
			
			//32663
			
			int finalHori = getHoriReflect(matrix);

			int finalVert = getVertReflect(matrix);

			if(finalHori != -1) {
				cur += 100*(finalHori+1);
			}
			
			if(finalVert!=-1) {
				cur += finalVert+1;
			}

			if(finalVert != -1 && finalVert != -1) {
					sopl("Warning");
					exit(1);
			}
			
			
			sopl("hori: " + (finalHori+1));
			sopl("vert: " + (finalVert+1));
			
			for(int i2=0; i2<matrix.length; i2++) {
				
				if(i2<9) {
				sop((i2 + 1) + "  ");
				} else {
					sop((i2 + 1)+ " ");
				}
				for(int j2=0; j2<matrix[0].length; j2++) {
					if(matrix[i2][j2]) {
						sop("#");
					} else {
						sop(".");
					}
				}
				sopl();
			}
			sopl();

			sopl("Answer: " + cur);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}

	//32663
	
	public static int getHoriReflect(boolean matrix[][]) {
		
		int x = 0;
		boolean found = false;
		
		boolean smudged;
		//------------------------------
		for(int ret=0; ret<matrix.length - 1; ret++) {
			
			boolean stillGood = true;
			smudged = false;
			
			REFLECT_CHECK:
			for(int i=ret; i>=0; i--) {
				
				int reflectI = ret + (ret - i) + 1;
				if(reflectI >= matrix.length) {
					continue;
				}
				
				for(int j=0; j<matrix[0].length; j++) {
					
					if(matrix[i][j] != matrix[reflectI][j]) {
						
						if(smudged == false) {
							smudged = true;
						} else {
							stillGood = false;
							break REFLECT_CHECK;
						}
					} 
					
				}
				
			}
			if(smudged && stillGood) {
				x += ret;
				found = true;
			}
			
			
		}
		
		if(! found) {
			return -1;
		}
		return x;
	}
	

	public static int getVertReflect(boolean matrix[][]) {
		
		int x = 0;
		//|
		//|
		//|
		boolean smudged;

		boolean found = false;
		for(int ret=0; ret<matrix[0].length - 1; ret++) {
			
			boolean stillGood = true;
			smudged = false;
			
			REFLECT_CHECK:
			for(int j=ret; j>=0; j--) {
				
				int reflectJ = ret + (ret - j) + 1;
				if(reflectJ >= matrix[0].length) {
					continue;
				}
				
				for(int i=0; i<matrix.length; i++) {
					
					if(matrix[i][j] != matrix[i][reflectJ]) {
						if(smudged == false) {
							smudged = true;
						} else {
							stillGood = false;
							break REFLECT_CHECK;
						}
					} 
					
				}
				
			}
			if(smudged && stillGood) {
				x += ret;
				found = true;
			}
			
			
		}
		

		if(! found) {
			return -1;
		}
		return x;
	}
	
	public static boolean[][] getNextMatrix(ArrayList<String> lines, int startI) {
		
		if(startI >= lines.size()) {
			return null;
		}
		
		int vertSize = 0;
		for(int i=startI; i<lines.size(); i++) {
			
			
			if(lines.get(i).trim().equals("")) {
				break;
			} else {
				vertSize++;
			}
		}
		
		boolean ret[][] = new boolean[vertSize][lines.get(startI).length()];
		
		for(int i=0; i<ret.length; i++) {
			for(int j=0; j<ret[0].length; j++) {
				ret[i][j] = lines.get(startI + i).charAt(j) == '#';
			}
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
