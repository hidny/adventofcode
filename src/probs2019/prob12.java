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

public class prob12 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			
			String filename = "in2019/prob2019in12.txt";
			//String filename = "in2019/prob2019in12.txt";
			
			in = new Scanner(new File(filename));
			int numTimes = 0;
			 
			int count = 0;
			boolean part2 = true;
			String line = "";

			LinkedList queue = new LinkedList();
			Stack stack = new Stack();
			HashSet set = new HashSet();
			
			Hashtable<Long, Integer> trail = new Hashtable<Long, Integer>();
			
			ArrayList <String>lines = new ArrayList<String>();
			
			
			
			
			//Read data:

			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				
				
			}
			
			prob12Particle particle[] = new prob12Particle[lines.size()];
			
			char vars[] = new char[]{'x', 'y', 'z'};
			
			int posTemp[] = new int[vars.length];
			
			for(int i=0; i<particle.length; i++) {
				
				for(int j=0; j<vars.length; j++) {
					int start = lines.get(i).indexOf(vars[j] + "=");
					int end = -1;
					if(lines.get(i).substring(start).indexOf(",") != -1) {
						end = start + lines.get(i).substring(start).indexOf(",");
					} else {
						end = start + lines.get(i).substring(start).indexOf(">");
					}
					posTemp[j] = pint(lines.get(i).substring(start + 2, end));
				}
	
				particle[i] = new prob12Particle(posTemp[0], posTemp[1], posTemp[2]);
				sopl(particle[i].pos[0] + "," + particle[i].pos[1] + "," + particle[i].pos[2]);
			}
			
			
			
			if(part2 == false) {

				int NUM_STEPS_PART1 = 1000;
				
				if(filename.equals("in2019/prob2019in12.txt.test")){
					NUM_STEPS_PART1 = 10;
				}
				
				doPart1(particle, NUM_STEPS_PART1);
			} else {
				
				long answer = 1;
				long equations[][] = new long[particle[0].pos.length][2];
				for(int i=0; i < equations.length; i++) {
					sopl("Get 1st repeat and period:");
					equations[i] = getFirstRepeatIndexAndPeriod(i, particle);
					
				}
				
				//I just realized that it repeats to step 1, so no Chinese remainder theorem needed :(
				for(int i=0; i<equations.length; i++) {
					answer *= equations[i][0] / utilsPE.GCD.getGCD(answer, equations[i][0]);
					
				}
				
				sopl("Part 2 answer: " + answer);
				
			}
			
			
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public static void doPart1(prob12Particle particle[], int numSteps) {
		
		sopl("End step");
		
		for(int n=0; n<numSteps; n++) {
			
			for(int j=0; j<particle.length; j++) {
				for(int k=0; k<particle.length; k++) {
					if(j!=k) {

						for(int m=0; m<particle[j].pos.length; m++) {
							if(particle[j].pos[m] < particle[k].pos[m]) {
								particle[j].vel[m]++;
							} else if(particle[j].pos[m] > particle[k].pos[m]) {
								particle[j].vel[m]--;
							}
						}

					}
				}
			}
			
			for(int i=0; i<particle.length; i++) {
				for(int m=0; m<particle[i].pos.length; m++) {
					particle[i].pos[m] += particle[i].vel[m];
				}
				sopl(particle[i]);
			}
			
			sopl("end step");
		}
		
		int answer = 0;
		for(int i=0; i<particle.length; i++) {
			
			answer += particle[i].energy();
		}
		sopl("Answer: " + answer);
	}

	//TODO: Assuming the moons aren't interchangeable... as long as the pos and vel match
	
	public static long[] getFirstRepeatIndexAndPeriod(int dimIndex, prob12Particle particle[]) {
		long ret[] = new long[2];
		
		Hashtable<String, Integer> prevSteps = new Hashtable<String, Integer>();
		
		int stepNum = 0;
		while(true) {
			
			String tmpKey = "";
			for(int j=0; j<particle.length; j++) {
				tmpKey += particle[j].pos[dimIndex] + "," + particle[j].vel[dimIndex] + ",";
			}
			
			if(prevSteps.containsKey(tmpKey)) {
				ret[0] = stepNum;
				ret[1] = stepNum - prevSteps.get(tmpKey);
				
				sopl(ret[0]);
				sopl(ret[1]);
				sopl("aaaa");
				break;
			}
			prevSteps.put(tmpKey, stepNum);
			
			for(int j=0; j<particle.length; j++) {
				for(int k=0; k<particle.length; k++) {
					if(j!=k) {

						if(particle[j].pos[dimIndex] < particle[k].pos[dimIndex]) {
							particle[j].vel[dimIndex]++;
						} else if(particle[j].pos[dimIndex] > particle[k].pos[dimIndex]) {
							particle[j].vel[dimIndex]--;
						}
					}
				}
			}
			
			for(int i=0; i<particle.length; i++) {
					particle[i].pos[dimIndex] += particle[i].vel[dimIndex];
			}
			
			stepNum++;
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
