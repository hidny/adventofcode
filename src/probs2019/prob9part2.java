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

public class prob9part2 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2019/prob2019in9.txt"));

			String line = "";

			long INPUT = 2;
			
			
			line = "";
			while(in.hasNextLine()) {
				line += in.nextLine();
			}
			
			cmds = line.split(",");
			
			long position = 0;
			
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
				

				//sopl();
				//sop("Test inst: (" + position + ") "+ getCmds(position));
				for(int i=1; i<lngth; i++) {
					//sop(" " + getCmds(position + i));
				}
				//sopl();
				
				if(getCmds(position).length() != 5) {
					sopl("ERROR!");
					exit(1);
				}
				if(position == 31) {
					sopl("debug");
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
				    
				   //He said this should never happen on write... reading is hard :(
				    
				   // if(getCmds(position).charAt(0) == '0') {
					//	var3 = pint(getCmds(pint(getCmds(position + 3))));
					//	
				   // } else {
				    //	sopl("error: weird var3");
				   // 	exit(1);
				    //}
				    
					//} else 
				    if(getCmds(position).charAt(0) == '2') {
				    	//TODO
				    	//jexit(1);
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
					//INPUT HERE
					setCmds(var1, "" + INPUT);
					
				} else if(opCode == 4) {
					sopl("Output: " + var1);
					
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
					sopl(var1 + " == " + var2);
					if(var1 == var2) {
						setCmds(var3, "1");
						
					} else {
						setCmds(var3, "0");	
					}

				} else if(opCode == 9) {
					//Adjust relative base:
					
					relativeBase += var1;
					sopl("rel Base: " + relativeBase);
					
					//sopl("Test inst: " + getCmds(position));
					//sopl("next pos: " + getCmds(position + 1));
					
					//sopl("rel base reset : " + relativeBase + " var1  " + var1);
					
				} else {
					sopl();
					sopl("ERROR UNKNOWN OPCODE:" + opCode);
					exit(1);

					break;
				}
				
				position += lngth;
			}
			
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}

	
	public static String cmds[];
	
	public static Hashtable<Long, String> extra = new Hashtable<Long, String>();
	
	public static long relativeBase = 0;
	
	public static String getCmds(long i) {
		while(getCmdsInternal(i).startsWith("-") == false && getCmdsInternal(i).length() < 5) {
			//sopl("Set cmd: " +  "0" + getCmdsInternal(i));
			setCmds(i, "0" + getCmdsInternal(i), true);
		}
		return getCmdsInternal(i);
	}
	public static String getCmdsInternal(long i) {
		if(i < cmds.length) {
			return cmds[(int)i];
		} else if(extra.get(i) != null) {
			//sopl("TEST");
			return extra.get(i) + "";
		} else {
			return "0";
		}
	}
	
	public static void setCmds(long i, String var) {
		setCmds(i, var, false);
	}
	
	public static void setCmds(long i, String var, boolean quiet) {
		if(i < cmds.length) {
			
			if(quiet == false) {
				sopl("Replace: pos(" + i + ") with " + var);
			}
			cmds[(int)i] = var + "";
		} else {
			if(extra.get(i) == null)  {
				sopl("Add orig: pos(" + i + ") with " + var);
			} else if(quiet == false) {
				sopl("Replace: pos(" + i + ") with " + var);
			}
			
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


