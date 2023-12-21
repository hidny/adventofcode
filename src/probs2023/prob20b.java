package probs2023;
import java.io.File;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

import number.IsNumber;
import utils.Mapping;
import utils.Sort;

public class prob20b {

	//day1 part 1
	//2:38.01
	
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2023/prob2023in20.txt"));
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
			
			
			HashMap<String, String> flipFlops = new HashMap<String, String>();
			
			HashMap<String, Boolean> flipFlopSetting = new HashMap<String, Boolean>();
			
			HashMap<String, String> conjuctionToOutput = new HashMap<String, String>();
			HashMap<String, prob20obj> conjuctionSetting = new HashMap<String, prob20obj>();
			
			HashMap<String, String> broadcaster = new HashMap<String, String>();
			
			//button: press sends low pulse to broadcaster

			//press and wait before pressing...
			
			//Queue for pulses!
			Queue<String> queuePulseSource = new LinkedList<String>();
			Queue<String> queuePulseDest = new LinkedList<String>();
			Queue<Boolean> queueIsHighPulse = new LinkedList<Boolean>();
			

			
			
			long cur = 0L;
			ArrayList ints = new ArrayList<Integer>();
			for(int i=0; i<lines.size(); i++) {
				
				
				line = lines.get(i);
				
				String result = line.split("->")[1].trim();
				
				if(line.startsWith("broadcaster")) {
					broadcaster.put(line.split("->")[0].trim(), result);
				
				} else if(line.startsWith("%")) {
					
					String var = line.split("->")[0].substring(1).trim();
					flipFlops.put(var, result);
					flipFlopSetting.put(var, false);
					
				} else if(line.startsWith("&")) {

					String var = line.split("->")[0].substring(1).trim();
					conjuctionToOutput.put(var, result);
					conjuctionSetting.put(var, new prob20obj());
					
				}
				
			}

			for(int i=0; i<lines.size(); i++) {
				line = lines.get(i);
				
				String var = line.split("->")[0].substring(1).trim();
				String tokens[] = getList(line.split("->")[1].trim());
				
				for(int j=0; j<tokens.length; j++) {
					if(conjuctionSetting.containsKey(tokens[j])) {
						
						conjuctionSetting.get(tokens[j]).addLabel(var);
					}
				}
				
				
			}
			
			long numLow = 0L;
			long numHigh= 0L;
			//1000 PRESSES
			
			//TODO:
			//int NUM_PRESSES =4;
			int NUM_PRESSES = 20000;
			
			OUT:
			for(int presses=0; presses<NUM_PRESSES; presses++) {
				if(presses % 10000 == 0) {
					sopl("presses: " + presses);
				}
				//sopl();
				//sopl("button --low--> broadcaster");

				numLow++;
				
				//Activate broadcaster:
				
				String dest[] = getList(broadcaster.get("broadcaster"));
				
				for(int i=0; i<dest.length; i++) {
					
					queuePulseSource.add("broadcaster");
					queuePulseDest.add(dest[i]);
					queueIsHighPulse.add(false);
					numLow++;
					
					//sopl("broadcaster --low-->" + dest[i]);
				}
			
				while(queuePulseDest.isEmpty() == false) {
	
					String newSource = queuePulseSource.poll();
					String newDest = queuePulseDest.poll();
					boolean pulse = queueIsHighPulse.poll();
					
					if(flipFlops.containsKey(newDest)) {
						
						
						if(pulse == true) {
							//High Ignore
						
						} else {
							//flipFlopSetting = new HashMap<String, Boolean>();
							boolean curSetting = flipFlopSetting.get(newDest);
							
							boolean newSetting = !curSetting;
							flipFlopSetting.remove(newDest);
							flipFlopSetting.put(newDest, newSetting);
							
							
							String newDests[] = getList(flipFlops.get(newDest));
							
							for(int i=0; i<newDests.length; i++) {
	
								queuePulseSource.add(newDest);
								queuePulseDest.add(newDests[i]);
								queueIsHighPulse.add(newSetting);
								
								/*if(newSetting) {
									sopl(newDest + " --high-->" + newDests[i]);
								} else {
									sopl(newDest + " --low-->" + newDests[i]);
								}*/
								
								if(newSetting) {
									numHigh++;
									
								} else {
	
									numLow++;
								}
							}
						}
						
					} else if(conjuctionToOutput.containsKey(newDest)) {
						
						conjuctionSetting.get(newDest).modifyLabel(newSource, pulse);
						
						String newDests[] = getList(conjuctionToOutput.get(newDest));
						
						boolean sendHigh = ! conjuctionSetting.get(newDest).allOn();
						
						for(int i=0; i<newDests.length; i++) {
	
							queuePulseSource.add(newDest);
							queuePulseDest.add(newDests[i]);
							queueIsHighPulse.add(sendHigh);
							
	
							/*if(sendHigh) {
								sopl(newDest + " --high-->" + newDests[i]);
							} else {
								sopl(newDest + " --low-->" + newDests[i]);
							}*/
							
							if(sendHigh) {
								numHigh++;
								
							} else {
	
								numLow++;
							}
						}
						
						//TODO: ahh!
					} else {
						//exit(1);
						
						if( ! pulse) {
							sopl("Answer for part 2: " + (presses + 1));
							exit(1);
							break OUT;
						}
						
						
						
						if(newDest.equals("rx") == false) {
							sopl("ERROR: unknown var " + newDest);
							exit(1);
						}
						
						if(pulse) {
							int numPresses = (presses+1);
							
							prob20obj obj = conjuctionSetting.get(newSource);

							boolean shouldPrint = false;
							for(int j=0; j<obj.setting.size(); j++) {
								if(obj.setting.get(j)) {
									shouldPrint = true;
								}
							}
							if(shouldPrint) {
								sopl();
								sopl(numPresses);
								for(int j=0; j<obj.setting.size(); j++) {
									sopl(obj.inputLabels.get(j) + ": " + obj.setting.get(j));
								}
								
								//I found a pattern. The sources to RX are switched on periodically starting at 0!
								//You just need to multiply the periods together.
							}
							
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
	
	
	public static String[] getList(String result) {
		
		String tokens[] = result.split(",");
		
		for(int i=0; i<tokens.length; i++) {
			tokens[i] = tokens[i].trim();
		}
		
		return tokens;
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
