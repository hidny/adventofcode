package probs2017;

import java.io.File;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

public class prob25 {

	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2017/prob2017in25.txt"));
			
			int count = 0;
			String line = "";

			ArrayList <String>lines = new ArrayList<String>();
			
			ArrayList<Integer> sideA = new ArrayList<Integer>();
			ArrayList<Integer> sideB = new ArrayList<Integer>();
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				
				
				
			}
			
			ArrayList<prob25State> states = new ArrayList<prob25State>();
			
			System.out.println(lines.get(0));
			String startState = "" + lines.get(0).split(" ")[3].charAt(0);
			sopl(startState);
			
			int numSteps = Integer.parseInt(lines.get(1).split(" ")[5]);
			
			prob25State current;
			for(int i=3; i<lines.size(); i++) {
				if(lines.get(i).contains("In state")) {
					current = new prob25State();
					current.stateName = (lines.get(i).trim()).split(" ")[2].split(":")[0];
					i++;
					i++;
					current.writeState0 =  Integer.parseInt("" + (lines.get(i).trim()).split(" ")[4].charAt(0));
					
					i++;
					if((lines.get(i).trim()).split(" ")[6].equals("right.")) {
						current.dir0 = 1;
					} else if((lines.get(i).trim()).split(" ")[6].equals("left.")) {
						current.dir0 = -1;
					} else {
						sopl("parse dir error");
					}
					
					i++;
					
					current.moveState0 = "" + (lines.get(i).trim()).split(" ")[4].charAt(0);
					
					i++;
					i++;
					current.writeState1 =  Integer.parseInt("" + (lines.get(i).trim()).split(" ")[4].charAt(0));
					
					i++;
					if((lines.get(i).trim()).split(" ")[6].equals("right.")) {
						current.dir1 = 1;
					} else if((lines.get(i).trim()).split(" ")[6].equals("left.")) {
						current.dir1 = -1;
					} else {
						sopl("parse dir error");
					}
					
					i++;
					
					current.moveState1 = "" + (lines.get(i).trim()).split(" ")[4].charAt(0);
					
					
					
					states.add(current);
				}
			}
			
			Hashtable <Integer, Integer> tape = new Hashtable <Integer, Integer>();
			
			int currentLocation = 0;
			
			
			String currentStateString = startState;
			for(int i=0; i<numSteps; i++) {
				int stateIndex = -1;
				
				for(int j=0; j<states.size(); j++) {
					if(states.get(j).stateName.equals(currentStateString)) {
						stateIndex = j;
					}
				}
				
				if(stateIndex==-1) {
					sopl("Could not find state" + currentStateString);
				}
				
				prob25State currentState = states.get(stateIndex);
				
				
				int tapeRead = readTape(tape, currentLocation);
				
				if(tapeRead == 0) {
					if(currentState.writeState0 == 1) {
						tape.put(currentLocation, 1);
					} else  {
						if(tape.containsKey(currentLocation)) {
							tape.remove(currentLocation);
						}
					}
					
					currentLocation += currentState.dir0;
					
					currentStateString = currentState.moveState0;
				} else {
					if(currentState.writeState1 == 1) {
						tape.put(currentLocation, 1);
					} else  {
						if(tape.containsKey(currentLocation)) {
							tape.remove(currentLocation);
						}
					}
					
					currentLocation += currentState.dir1;
					
					currentStateString = currentState.moveState1;
				}
				
				
				
			}
			
			
			
			sopl("hello");
			System.out.println("Answer: " + tape.size());
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public static int readTape(Hashtable <Integer, Integer> tape, int currentLocation) {
		
		if(tape.containsKey(currentLocation)) {
			return 1;
		} else {
			return 0;
		}
	}
	

	public static void sopl() {
		sopl("");
	}
	
	public static void sopl(String out) {
		System.out.println(out);
	}

	public static void sop(String out) {
		System.out.print(out);
	}
	
}
