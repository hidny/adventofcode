package probs2018after1am;

import java.io.File;

import number.IsNumber;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import utils.Mapping;

public class prob7part2 {

	//2016->prob24pos for grid used on A*
	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2018/prob2018in7.txt"));
			
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
				
			}
			
			
			String output = "";
			
			
			int timeDone[] = new int[26];
			
			for(int i=0; i<timeDone.length; i++) {
				timeDone[i] =1000000;
			}
			
			boolean workingOnit[] = new boolean[26];
			
			boolean hasRequirement[];
			
			
			int time = 0;
			int max_people = 5;
			
			

			sop("time workers endTime order");
			
			SEARCH:
			while(true) {
				
				//sop(time);
				
				//Get requirements
				hasRequirement = new boolean[26];
				for(int i=0; i<lines.size(); i++) {
					
					//GENIUS OBSERVATION: I don't need to make a java object the stuff in the input file, the entry in the input could be treated as a java object :P
					//SUPER GENIUS OBSERVATION: why are you using Java for this???
					
					int waiter =(int)((lines.get(i).split(" ")[7].charAt(0)-'A'));
					int req = (int)((lines.get(i).split(" ")[1].charAt(0)-'A'));
					
					if(workingOnit[req] == false || timeDone[req] > time) {
						hasRequirement[waiter] = true;
					}
				}
				
				//Get contestants that could be worked on now:
				boolean contestant[] = new boolean[26];
				for(int i=0; i<26; i++) {
					if(workingOnit[i] == false && hasRequirement[i] == false) {
						
						contestant[i] = true;
					}
				}
				
				//5 people!
				//You + 4 elves!
				
				//Get number of People available to work:
				int numberOfPeopleWorking = 0;
				for(int i=0; i<26; i++) {
					if(workingOnit[i] == true && timeDone[i] > time) {
						numberOfPeopleWorking++;
					}
				}
				
				
				//Try to assign more workers to more task:
				for(int i=0; i<26; i++) {
					if(contestant[i] && numberOfPeopleWorking < max_people) {
						
						timeDone[i] = 60 + time + 1 + i;
						numberOfPeopleWorking++;
						workingOnit[i] = true;
						
						output += (char)('A' + i) + "";
						sop(time + "      "+ numberOfPeopleWorking + "      " + timeDone[i] + "      " + output);
					}
				}

				//See if work assignment needs to continue and at what time it should continue:
				for(int i=0; i<26; i++) {
					if(workingOnit[i] == false) {
						
						//Skip to the time where no one is working anymore:
						int nextTime = 1000000;
						for(int j=0; j<26; j++) {
							if(workingOnit[j] == true && timeDone[j] > time) {
								if(nextTime > timeDone[j]) {
									nextTime = timeDone[j];

								}
							}
						}
						time = nextTime;
						
						
						continue SEARCH;
					}
				}
				
				//End it here.
				break;
				
			}
			
			
			//Get time of last thing done:
			int answer = 0;
			for(int i=0; i<26; i++) {
				if(timeDone[i] > answer) {
					answer = timeDone[i];
				}
				
			}
			
			sopl("Answer: " + answer);
			
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
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
