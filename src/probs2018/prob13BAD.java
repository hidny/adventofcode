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

public class prob13BAD {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2018/prob2018in13.txt"));
			
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
			
			int iAnswer = -1;
			int jAnswer = -1;
			
			Hashtable<Long, Integer> trail = new Hashtable<Long, Integer>();
			
			//dir: 0 up
			//1 right
			//2 down
			//3 left
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				
				
			}
			
			
			int origCount = 0;
			for(int i=0; i<lines.size(); i++) {
				
				for(int j=0; j<lines.get(i).length(); j++) {
					if(isGuy(lines, i, j)) {
						int dir = getDirectionGuy(lines, i, j);
						
						prob13Guy.guys.add(new prob13Guy(i, j, dir));	
					}
				}
				
			}
			
			
			
			while(prob13Guy.hasCollision() == false) {
				
				prob13Guy.resetItereation();
				
				
				
				
				for(int gi=0; gi<prob13Guy.guys.size(); gi++) {
					
					/*sop("Show:");
					for(int i=0; i<lines.size() && prob13Guy.hasCollision() == false; i++) {
						
						for(int j=0; j<lines.get(i).length(); j++) {
							if(prob13Guy.guyDirectionIgnoreIteration(i, j) != -100) {
								int temp = prob13Guy.guyDirectionIgnoreIteration(i, j);
								if(temp == 0) {
									System.out.print("^");
								} else if(temp == 1) {
									System.out.print(">");
									
								} else if(temp == 2) {
									System.out.print("v");
								} else if(temp == 3) {
									System.out.print("<");
								} else {
									sop("wrong dir");
									exit(1);
								}
							} else {
								System.out.print(lines.get(i).charAt(j));
							}
						}
						sopl("");
					}
					sopl("");
					sopl("");*/
					
					
					//78,106
					
					if(is4WayIntersection(lines, prob13Guy.guys.get(gi).i, prob13Guy.guys.get(gi).j)) {
						prob13Guy.guys.get(gi).setNextTurnNumber();
						prob13Guy.guys.get(gi).moveOneStraight();
					} else if(isTurn(lines, prob13Guy.guys.get(gi).i, prob13Guy.guys.get(gi).j)) {
						int curDir = prob13Guy.guys.get(gi).direction;
						
						int i = prob13Guy.guys.get(gi).i;
						int j=  prob13Guy.guys.get(gi).j;
						if(lines.get(i).charAt(j) =='/' && (curDir ==0 || curDir == 2)) {
							prob13Guy.guys.get(gi).direction = (prob13Guy.guys.get(gi).direction + 1) %4;
						} else if(lines.get(i).charAt(j) =='/'){
							prob13Guy.guys.get(gi).direction = (prob13Guy.guys.get(gi).direction + 3) %4;
							
						}
						
						if(lines.get(i).charAt(j) =='\\' && (curDir ==0 || curDir == 2)) {
							prob13Guy.guys.get(gi).direction = (prob13Guy.guys.get(gi).direction + 3) %4;
						} else if(lines.get(i).charAt(j) =='\\'){
							prob13Guy.guys.get(gi).direction = (prob13Guy.guys.get(gi).direction + 1) %4;
							
						}
						

						prob13Guy.guys.get(gi).moveOneStraight();
					} else {

						prob13Guy.guys.get(gi).moveOneStraight();
					}
					
				}
				
			}

			prob13Guy.resetItereation();
			
			sop("show final:");
			for(int i=0; i<lines.size(); i++) {
				
				for(int j=0; j<lines.get(i).length(); j++) {
					if(prob13Guy.guyDirectionIgnoreIteration(i, j) != -100) {
						int temp = prob13Guy.guyDirectionIgnoreIteration(i, j);
						if(temp == 0) {
							System.out.print("^");
						} else if(temp == 1) {
							System.out.print(">");
							
						} else if(temp == 2) {
							System.out.print("v");
						} else if(temp == 3) {
							System.out.print("<");
						} else {
							sop("wrong dir");
							exit(1);
						}
					} else {
						System.out.print(lines.get(i).charAt(j));
					}
				}
				sopl("");
			}
			sopl("");
			sopl("");
			
			
			int answer[] = prob13Guy.getCollision();
			
			int y = answer[0];
			int x = answer[1];
			sopl("Answer: " + x + "," + y);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public static boolean is4WayIntersection(ArrayList <String>lines, int i, int j) {
		if(i==0 || i == lines.size() -1) {
			return false;
		}
		if(j == 0 || j == lines.get(i).length() - 1) {
			return false;
		}
		sop(i);
		if(lines.get(i).charAt(j) =='+') {
			return true;
		}
		
		return false;
			
	}
	
	public static boolean isTurn(ArrayList <String>lines, int i, int j) {
		if(lines.get(i).charAt(j) =='/' || lines.get(i).charAt(j) =='\\') {
			return true;
		} else {
			return false;
		}
		
	}
	
	public static boolean isGuy(ArrayList <String>lines, int i, int j) {
		if(lines.get(i).charAt(j) =='^' || lines.get(i).charAt(j) =='v' || lines.get(i).charAt(j) =='<' || lines.get(i).charAt(j) =='>') {
			return true;
		} else {
			return false;
		}
	}
	
	public static int getDirectionGuy(ArrayList <String>lines, int i, int j) {
		if(lines.get(i).charAt(j) =='^') {
			return 0;
		} else if(lines.get(i).charAt(j) =='>') {
			return 1;
		} else if(lines.get(i).charAt(j) =='v') {
			return 2;
		} else if(lines.get(i).charAt(j) =='<') {
			return 3;
		} else {
			sop("not a guy");
			exit(1);
			return -1;
		}
		
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
