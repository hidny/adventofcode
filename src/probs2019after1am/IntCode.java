package probs2019after1am;
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

public class IntCode {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2019/prob2019in9.txt"));

			String line = "";

			long INPUT = 1;
			
			
			line = "";
			while(in.hasNextLine()) {
				line += in.nextLine();
			}
			
			String cmds[] = line.split(",");
			
			LinkedList<Long> inputQueue = new LinkedList<Long>();
			//Add input:
			inputQueue.add(2L);
			inputQueue.add(1L);
			inputQueue.add(0L);
			
			IntCode prog = new IntCode(cmds, inputQueue);
			prog.runProg();
			
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public IntCode(String cmdsArray[], LinkedList<Long> inputQueueInput) {
		//HARD CODE just in case...
		cmds = new String[cmdsArray.length];
		
		//TODO: don't hard-copy if the programs share and modify same code.
		for(int i=0; i<cmdsArray.length; i++) {
			this.cmds[i] = cmdsArray[i];
		}
		
		//TODO: don't hard-copy if the programs share input.
		for(int i=0; i<inputQueueInput.size(); i++) {
			this.inputQueue.add(inputQueueInput.getFirst());
			
			inputQueueInput.add(inputQueueInput.getFirst());
			inputQueueInput.remove();
		}
		
	}
	

	private boolean pauseOnOutput = false;
	
	public void setPauseOnOutput() {
		this.pauseOnOutput = true;
	}
	//TODO: make into object so mult instances can run at once.
	
	public LinkedList<Long> inputQueue = new LinkedList<Long>();
	
	public String cmds[];
	
	public Hashtable<Long, String> extra = new Hashtable<Long, String>();
	
	private long position = 0;

	public long relativeBase = 0;

	private boolean halted = false;
	
	public boolean isHalted() {
		return halted;
	}
	
	private IntCodeInputReader inputReader;
	public void setInputReader(IntCodeInputReader r) {
		inputReader = r;
	}
	
	public long runProg() {
		
		
		//sopl("Start");
		
		while(pint(getCmds(position)) % 100 != 99) {
		
			long lngth = 4;
			long opCode = pint(getCmds(position)) % 10;
			
			if(opCode == 3 || opCode ==4 || opCode == 9) {
				lngth = 2;

			} else if(opCode == 5 || opCode ==6) {
				lngth = 3;

			} else {
				lngth = 4;
			}
			

			//DEBUG
			/*
			sopl();
			sop("Test inst: (" + position + ") "+ getCmds(position));
			for(int i=1; i<lngth; i++) {
				sop(" " + getCmds(position + i));
			}
			sopl();
			*/
			if(getCmds(position).length() != 5) {
				sopl("ERROR!");
				exit(1);
			}
			
			long var1 = -1;
			long var2 = -1;
			long var3 = -1;

			if(lngth >=2 ) {
				var1 = pint(getCmds(position + 1));
				
				if( opCode != 3 && getCmds(position).charAt(2) == '0') {
					var1 = pint(getCmds(pint(getCmds(position + 1))));

				} else if(opCode != 3 && getCmds(position).charAt(2) == '2') {
					var1 = pint(getCmds(relativeBase + pint(getCmds(position + 1))));
					
				} else if(getCmds(position).charAt(2) == '2') {
					var1 = relativeBase + pint(getCmds(position + 1));
					//exit(1);
				}
			} else {
				sopl("ERROR!");
				exit(1);
			}
			
			if(lngth >=3 ) {
				var2 = pint(getCmds(position + 2));
				
				if(getCmds(position).charAt(1) == '0') {
					var2 = pint(getCmds(pint(getCmds(position + 2))));
									
				} else if(getCmds(position).charAt(1) == '2') {
					var2 = pint(getCmds(relativeBase + pint(getCmds(position + 2))));
					
				}
			}

			if(lngth >=4) {
			    var3 = pint(getCmds(position + 3));
			    
			    if(getCmds(position).charAt(0) == '2') {
					var3 = relativeBase + pint(getCmds(position + 3));
				}
			}
			
			if(opCode == 1) {
				long temp = var1 + var2;
				setCmds(var3, temp + "");
				
			} else if(opCode == 2) {
				long temp = var1 * var2;
				setCmds(var3,  temp + "");
				
			} else if(opCode == 3) {
				sopl("get Input");
				//INPUT HERE
				if(inputQueue.isEmpty()) {
					
					if(inputReader == null) {
						sopl("ERROR: empty input queue and null inputReader... intCode doesn\'t know what the input should be!");
					} else {
						//sopl("WARN: empty input");
					}
					
					//TODO: TEST
					long inputRec = inputReader.getInput();
					//sop((char)inputRec);
					setCmds(var1, "" + inputRec);
					
				} else {
					//sopl("input queue");
					
					//if(inputQueue.getFirst() > 0 && inputQueue.getFirst() < 256) {
					//	sop(((char)(long)inputQueue.getFirst()));
					//}
					setCmds(var1, "" + inputQueue.getFirst());
					inputQueue.removeFirst();
				}
				
			} else if(opCode == 4) {
				if(this.pauseOnOutput) {

					position += lngth;
					return var1;
				} else {
					sopl("Output: " + var1);
					
				}
				
			} else if(opCode == 5) {
				//sopl(var1 + "!= lit(0) ??");
				if(var1 != 0) {
					position = var2;
					continue;
				}
				
			} else if(opCode == 6) {
				if(var1 == 0) {
					position = var2;
					continue;
				}

			} else if(opCode == 7) {
				if(var1 < var2) {
					setCmds(var3, "1");
					
				} else {
					setCmds(var3, "0");
				}

			} else if(opCode == 8) {
				//DEBUG
				//sopl(var1 + " == " + var2);

				if(var1 == var2) {
					setCmds(var3, "1");
					
				} else {
					setCmds(var3, "0");	
				}

			} else if(opCode == 9) {
				//Adjust relative base:
				
				relativeBase += var1;

				//DEBUG:
				//sopl("rel Base: " + relativeBase);
				
			} else {
				sopl();
				sopl("ERROR UNKNOWN OPCODE:" + opCode);
				exit(1);

				break;
			}
			
			position += lngth;
		}
		
		//return sucess...
		halted = true;
		return 99L;
	}
	

		
	public String getCmds(long i) {
		while(getCmdsInternal(i).startsWith("-") == false && getCmdsInternal(i).length() < 5) {
			setCmds(i, "0" + getCmdsInternal(i), true);
		}
		return getCmdsInternal(i);
	}
	public String getCmdsInternal(long i) {
		if(i < cmds.length) {
			return cmds[(int)i];
		} else if(extra.get(i) != null) {
			return extra.get(i) + "";
		} else {
			return "0";
		}
	}
	
	public void setCmds(long i, String var) {
		setCmds(i, var, false);
	}
	
	public void setCmds(long i, String var, boolean quiet) {
		if(i < cmds.length) {
			
			//DEBUG
			/*
			if(quiet == false) {
				sopl("Replace: pos(" + i + ") with " + var);
			}
			*/
			cmds[(int)i] = var + "";
		} else {
			//DEBUG
			/*
			if(extra.get(i) == null)  {
				sopl("Add orig: pos(" + i + ") with " + var);
			} else if(quiet == false) {
				sopl("Replace: pos(" + i + ") with " + var);
			}
			*/
			
			if(extra.get(i) != null)  {
				extra.remove(i);
			}
			
			
			extra.put(i, var);
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
	
	public static long pint(String s) {
		if (IsNumber.isLong(s)) {
			return Long.parseLong(s);
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
//3819083914


