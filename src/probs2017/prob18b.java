package probs2017;

import java.io.File;
import number.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import java.util.concurrent.LinkedTransferQueue;

import utils.Mapping;

public class prob18b implements Runnable{

	public static ArrayList <Integer> list = new ArrayList<Integer>();
	public static ArrayList <String>lines = new ArrayList<String>();
	
	public static LinkedTransferQueue sendP0 = new LinkedTransferQueue();
	public static LinkedTransferQueue sendP1 = new LinkedTransferQueue();
	
	
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2017/prob2017in18.txt"));
			
			int count = 0;
			String line = "";
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				
				
			}
			Thread thread = new Thread(new prob18b());
			thread.start();
			
			runProg(lines, 0);
			
			
			
			System.out.println("Answer: " + count);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public static void runProg(ArrayList <String> lines, long programNum) {
		System.out.println(programNum + ": START");
		Mapping dict = new Mapping();
		long recoverSound = 0;
		
		long count = 0;
		
		dict.set("p", programNum);
		
		try {
			int pc = 0;
			while(pc < lines.size()) {
				//System.out.println(programNum);
				String token[] = lines.get(pc).split(" ");
				if(token[0].equals("snd")) {

					long temp1 = getReg(dict, token[1]);
					if(programNum == 1) {
						count++;
						System.out.println("Current count: " + count);
						sendP1.add(temp1);
						
						System.out.println(programNum +  ": SENT " + temp1 );
					} else {
						sendP0.add(temp1);
						
						System.out.println(programNum + ": SENT " + temp1 );
					}
					
				} else if(token[0].equals("set")) {
					dict.set(token[1], getReg(dict, token[2]));
					
				} else if(token[0].equals("add")) {
					dict.set(token[1], dict.get(token[1]) + getReg(dict, token[2]));
					
				} else if(token[0].equals("mul")) {
					dict.set(token[1], dict.get(token[1]) * getReg(dict, token[2]));
					
				} else if(token[0].equals("mod")) {
					dict.set(token[1], dict.get(token[1]) % getReg(dict, token[2]));
					
				} else if(token[0].equals("rcv")) {

					
					System.out.println(programNum + ": RCV... (if deadlock, get last current count)");
					while(true) {
						if(programNum == 1) {
							if(sendP0.isEmpty()) {
								Thread.sleep(10);
								continue;
							}
							recoverSound = (long)sendP0.take();
							break;
						} else {
							if(sendP1.isEmpty()) {
								Thread.sleep(10);
								continue;
							}
							
							recoverSound = (long)sendP1.take();
							break;
						}
					}
					System.out.println(programNum + ": RCV " + recoverSound);
				
					dict.set(token[1], recoverSound);
					
					
				} else if(token[0].equals("jgz") ) {
					if(getReg(dict, token[1]) > 0) {
						pc += getReg(dict, token[2]);
						continue;
					}
				}
				
				
				pc++;
			}
			
			System.out.println("END PROG" + programNum);
		} catch( Exception e) {
			e.printStackTrace();
		}
	
	}

	public static long getReg(Mapping dict, String reg) {
		if(IsNumber.isNumber(reg)) {
			return Integer.parseInt(reg);
		} else {
			return dict.get(reg);
		}
	}


	@Override
	public void run() {

		runProg(lines, 1);
		
	}
}
