package probs2017;

import java.io.File;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import utils.Mapping;

public class prob19 {

	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2017/prob2017in19.txt"));
			
			int countPart2 = 0;
			boolean part2 = true;
			String line = "";

			LinkedList queue = new LinkedList();
			Stack stack = new Stack();
			HashSet set = new HashSet();
			
			Mapping dict = new Mapping();
			ArrayList <String>lines = new ArrayList<String>();
			
			ArrayList <Integer> list = new ArrayList<Integer>();
			
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				
				
				
			}
			
			int currentj = 0;
			int currenti = 0;
			
			String answer = "";
			int dir = 2;
			
			for(int i=0; i<lines.get(0).length(); i++) {
				if(lines.get(0).charAt(i) == '|') {
					currentj = i;
				}
			}
			
			
			while(true) {
				countPart2++;
				
				ArrayList<Integer> neighbours = new ArrayList<Integer>();
				ArrayList<Integer> newDir = new ArrayList<Integer>();
				if(currenti < lines.size() - 1 && dir !=0) {
					neighbours.add(lines.get(0).length() * (currenti+1) + currentj);
					newDir.add(2);
				}
				
				if(currenti > 0 && dir !=2) {
					neighbours.add(lines.get(0).length() * (currenti-1) + currentj);
					newDir.add(0);
				}
				
				if(currentj > 0 && dir !=3) {
					neighbours.add(lines.get(0).length() * currenti + currentj + 1);
					newDir.add(1);
				}
				
				if(currentj < lines.get(0).length() - 1 && dir !=1  ) {
					neighbours.add(lines.get(0).length() * currenti + currentj - 1);
					newDir.add(3);
				}
				
				
				if(lines.get(currenti).charAt(currentj) == '|') {
					if(dir == 2) {
						currenti++;
					} else if(dir == 0) {
						currenti--;
					} else if(dir == 1) {
						currentj++;
					} else if(dir == 3) {
						currentj--;
					} else {
						break;
					}
				} else if(lines.get(currenti).charAt(currentj) == '-') {
					if(dir == 2) {
						currenti++;
					} else if(dir == 0) {
						currenti--;
					} else if(dir == 1) {
						currentj++;
					} else if(dir == 3) {
						currentj--;
					} else {
						break;
					}
				} else if(lines.get(currenti).charAt(currentj) == '+') {
					
					//TODO: COPY OF CODE:
					if(neighbours.size() == 0) {
						break;
					} else {

						int newi = -1;
						int newj = -1;
						
						dir = newDir.get(0);
						
						for(int i=0; i<newDir.size(); i++) {
							int tempi = neighbours.get(i) / lines.get(0).length();
							int tempj = neighbours.get(i) % lines.get(0).length();
							int tempDir = newDir.get(i);
							
							if(lines.get(tempi).charAt(tempj) == ' ') {
								continue;
							} else if(tempDir % 2 == 0 && lines.get(tempi).charAt(tempj) == '-') {
								continue;
							} else if(tempDir % 2 == 1 && lines.get(tempi).charAt(tempj) == '|') {
								continue;
							} else {
								 newi = neighbours.get(i) / lines.get(0).length();
								 newj = neighbours.get(i) % lines.get(0).length();
								 dir = newDir.get(i);
							}
						}
						
						if(newi == -1) {
							break;
						} else {
							currenti = newi;
							currentj = newj;
							
						}
					}
					//TODO: END COPY OF CODE
						
				}else if(lines.get(currenti).charAt(currentj) == ' ') {
					break;
				} else {
					answer += lines.get(currenti).charAt(currentj);
					
					//TODO: COPY OF CODE:
					if(neighbours.size() == 0) {
						break;
					} else {

						int newi = -1;
						int newj = -1;
						
						dir = newDir.get(0);
						
						for(int i=0; i<newDir.size(); i++) {
							int tempi = neighbours.get(i) / lines.get(0).length();
							int tempj = neighbours.get(i) % lines.get(0).length();
							int tempDir = newDir.get(i);
							
							if(lines.get(tempi).charAt(tempj) == ' ') {
								continue;
							
							} else {
								 newi = neighbours.get(i) / lines.get(0).length();
								 newj = neighbours.get(i) % lines.get(0).length();
								 dir = newDir.get(i);
							}
						}
						
						if(newi == -1) {
							break;
						} else {
							currenti = newi;
							currentj = newj;
							
						}
						
						
						
					}
					//TODO: END COPY OF CODE
				}
				//LXWCKGRAOY
				System.out.println("--------");
				System.out.println(currenti);
				System.out.println(currentj);
				
			}
			
			
			//LXWCKGR
			
			System.out.println(currenti);
			System.out.println(currentj);
			System.out.println("Answer 1: " + answer);
			
			//17302
				
			System.out.println("Answer 2: " + (countPart2));
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	

}
