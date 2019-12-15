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

public class prob5part2 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2019/prob2019in5.txt"));

			boolean part2 = false;
			String line = "";

			int INPUT = 1;
			if(part2) {
				INPUT = 5;
			}
			
			line = "";
			while(in.hasNextLine()) {
				line += in.nextLine();
			}
			
			String cmds[] = line.split(",");
			
			int position = 0;
			
			while(pint(cmds[position]) % 100 != 99) {
				
				int lngth = 4;
				int opCode = pint(cmds[position]) % 10;
				
				if(opCode >= 3 && opCode <=4) {
					lngth = 2;

				} else if(opCode >= 5 && opCode <=6) {
					lngth = 3;

				} else {
					lngth = 4;
				}
				
				//pad with 0s...
				while(cmds[position].length() < 5) {
					cmds[position] = "0" + cmds[position];
				}
				
				sop("Test inst: " + cmds[position]);
				for(int i=1; i<lngth; i++) {
					sop(" " + cmds[position + i]);
				}
				sopl();
				
				int var1 = pint(cmds[position + 1]);
				int var2 = -1;
				int var3 = -1;

				if(opCode != 3 && cmds[position].charAt(2) != '1') {
					var1 = pint(cmds[pint(cmds[position + 1])]);//HERE
				}
				
				if(lngth >=3 ) {
					var2 = pint(cmds[position + 2]);
					
					if(cmds[position].charAt(1)!= '1') {
						var2 = pint(cmds[pint(cmds[position + 2])]);
										
					}
				}

				if(lngth >=4) {
				    var3 = pint(cmds[position + 3]);
				    
				   //He said this should never happen... reading is hard :(
				    /*if(cmds[position].charAt(0) != '1') {
						var3 = pint(cmds[pint(cmds[position + 3])]);
						sopl("boo");
						
				    }*/
				}
				
				if(opCode == 1) {
					int temp = var1 + var2;
					cmds[var3] = temp + "";
					
				} else if(opCode == 2) {
					int temp = var1 * var2;
					cmds[var3] = temp + "";
					
				} else if(opCode == 3) {
					//INPUT HERE
					cmds[var1] = "" + INPUT;
					
				} else if(opCode == 4) {
					sopl(var1);
					
				} else if(opCode == 5) {
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
						cmds[var3] = "1";
						
					} else {
						cmds[var3] = "0";
					}

				} else if(opCode == 8) {
					if(var1 == var2) {
						cmds[var3] = "1";
						
					} else {
						cmds[var3] = "0";	
					}

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
/*
 * Test2: 00003
Test2: 00001
Test2: 01105
Test2: 01105
Test2: 01105
Test2: 01005
Test2: 01005
Test2: 01106
Test2: 01106
Test2: 01006
Test2: 01006
Test2: 01105
Test2: 00001
Test2: 01101
Test2: 00105
Test2: 01106
Test2: 00001
Test2: 01101
Test2: 00106
Test2: 01008
Test2: 00102
Test2: 01006
Test2: 00101
Test2: 00108
Test2: 00102
Test2: 01005
Test2: 00101
Test2: 00107
Test2: 01002
Test2: 01005
Test2: 00101
Test2: 01107
Test2: 01002
Test2: 01005
Test2: 01008
Test2: 00102
Test2: 01006
Test2: 01007
Test2: 01002
Test2: 01006
Test2: 01001
Test2: 00008
Test2: 00102
Test2: 01005
Test2: 01001
Test2: 00008
Test2: 00102
Test2: 01006
Test2: 00101
Test2: 01107
Test2: 01002
Test2: 01006
Test2: 01107
Test2: 00102
Test2: 01005
Test2: 00101
Test2: 01108
Test2: 00102
Test2: 01006
Test2: 01001
Test2: 00007
Test2: 01002
Test2: 01006
Test2: 00007
Test2: 00102
Test2: 01005
Test2: 01108
Test2: 01002
Test2: 01006
Test2: 00008
Test2: 01002
Test2: 01005
Test2: 00101
Test2: 01007
Test2: 00102
Test2: 01006
Test2: 00108
Test2: 01002
Test2: 01006
Test2: 01108
Test2: 00102
Test2: 01005
Test2: 00101
Test2: 00108
Test2: 00102
Test2: 01005
Test2: 01007
Test2: 00102
Test2: 01006
Test2: 01008
Test2: 01002
Test2: 01006
Test2: 00107
Test2: 01002
Test2: 01006
Test2: 00101
Test2: 00007
Test2: 00102
Test2: 01005
Test2: 01001
Test2: 00107
Test2: 00102
Test2: 01005
Test2: 01001
Test2: 00004
15163975
*/
