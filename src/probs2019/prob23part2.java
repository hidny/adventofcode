package probs2019;
import java.io.File;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import number.IsNumber;
import utils.Mapping;
import utils.Sort;

import probs2019after1am.*;

public class prob23part2 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			
			 in = new Scanner(new File("in2019/prob2019in23.txt"));

			String line = "";


			HashSet<String> setOfYValuesSent = new HashSet<String>();
			
			line = "";
			while(in.hasNextLine()) {
				line += in.nextLine();
			}
			
			String cmds[] = line.split(",");
			
			
			LinkedList<Long> inputQueue = new LinkedList<Long>();
			
			int NUM_MACHINES = 50;
			
			int PART_1_STOP_SIGNAL = 255;
			
			IntCode intCode[] = new IntCode[NUM_MACHINES];
			
			for(int i=0; i<intCode.length; i++) {
				intCode[i] = new IntCode(cmds, inputQueue);

				//pause on output, so I can process it...
				intCode[i].setPauseOnOutput();
				
				//Give each machine a unique number:
				intCode[i].inputQueue.add((long)i);
				

				//Say input -1 if empty input queue
				intCode[i].setDefaultInput(-1);
				
				//Say to process single line at a time
				intCode[i].setProcessSingleLineAtATime();
			}
			
			
			boolean haltedList[] = new boolean[NUM_MACHINES];
			int numMachinesHalted = 0;
			
			long currentNATX = -1;
			long currentNATY = -1;
			
			boolean idleList[] = new boolean[NUM_MACHINES];
			int numMachinesIdle = 0;
			
			//TODO: lower to 3??
			int PART2_IDLE_LIMIT = 5;
			
			OUTER:
			while(numMachinesHalted < NUM_MACHINES) {
				
				//Check if all machines are idle:
				if(numMachinesHalted + numMachinesIdle == NUM_MACHINES) {
					sopl("All machines are idle or halted");
					sopl("Num halted: " + numMachinesHalted);
					
					if(setOfYValuesSent.contains(currentNATY + "")) {
						sopl("Answer for part 2: " + currentNATY);
						break OUTER;
					}
					
					//Make list of machines not idle anymore:
					numMachinesIdle = 0;
					for(int i=0; i<NUM_MACHINES; i++) {
						idleList[i] = false;
						intCode[i].makeMachineNotSeemIdle();
					}
					
					setOfYValuesSent.add(currentNATY + "");
					intCode[0].inputQueue.add(currentNATX);
					intCode[0].inputQueue.add(currentNATY);
					
				}

				sopl("Next iteration of all " + NUM_MACHINES + " machines.");

				for(int i=0; i<NUM_MACHINES; i++) {
					if(intCode[i].isHalted() ) {
						
						if(haltedList[i] == false) {
							sopl("Machine " + i + " is halted");
							haltedList[i] = true;
							numMachinesHalted++;
							
							if(idleList[i] == true) {
								numMachinesIdle--;
								idleList[i] = false;
							}
						}
						
						continue;
					}
					
					long ret = intCode[i].runProg();
					
					//TODO: check if ret is actual output (intCode[i].prob23LastReturnOutputs())
					
					boolean lastReturnOutputs = intCode[i].isLastProgOutputActualOutput();
					
					if(lastReturnOutputs) {

						sopl("Got " + ret);
						//Get X and Y
						//TODO: should I not use intCode[i].setProcessUntilOutput() ?
						//TODO 2: check that there's no input...
						intCode[i].setProcessUntilOutput();
						long x = intCode[i].runProg();
						long y = intCode[i].runProg();
						intCode[i].setProcessSingleLineAtATime();
						
						if(ret >=0 && ret < NUM_MACHINES) {

							sopl("Got " + ret + ", " + x + "," + y);
							//Add to input queue of machine ret.
							
							intCode[(int)ret].inputQueue.add(x);
							intCode[(int)ret].inputQueue.add(y);
							
							
							
						} else if(ret == PART_1_STOP_SIGNAL) {
							
							sopl("Sent to NAT (Not Always Transmitting) Got " + ret + ", " + x + "," + y);
							//Add to input queue of machine ret.
							
							currentNATX = x;
							currentNATY = y;
							
							/*
							*/
							
						} else  {
							sopl("ERROR: prog output: " + ret);
							exit(1);
						}
					}
					
					
					//Update on whether intCodeMachine is idle or Not
					if(intCode[i].checkNumEmptyInputFetchesInARowWithoutOutputOrRealInput() >= PART2_IDLE_LIMIT) {
						if(idleList[i] == false) {
							idleList[i] = true;
							numMachinesIdle++;
						}
					} else if(intCode[i].checkNumEmptyInputFetchesInARowWithoutOutputOrRealInput() == 0) {
						if(idleList[i] == true) {
							idleList[i] = false;
							numMachinesIdle--;
						}
					}
				}
			}
			
			
			
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public static long BLACK = 0L;
	public static long WHITE = 1L;
	
	public static long getColour(Hashtable<String, Long> paint, int x, int y) {
		if(paint.get(x + "," + y) == null) {
			return BLACK;
		} else if(paint.get(x + "," + y) == BLACK) {
			return BLACK;
		} else {
			return WHITE;
		}
	}
	

	public static void setColour(Hashtable<String, Long> paint, int x, int y, long colour) {
		if(paint.get(x + "," + y) != null) {
			paint.remove(x + "," + y);
		}
		paint.put(x + "," + y, colour);
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
	
	public static void exit() {
		exit(0);
	}
	public static void exit(int code) {
		sop("Exit with code " + code);
		
		System.exit(code);
	}

}
