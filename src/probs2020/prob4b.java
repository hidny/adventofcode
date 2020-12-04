package probs2020;
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

public class prob4b {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2020/prob2020in4.txt"));
			 //in = new Scanner(new File("in2020/prob2020in1.txt.test"));
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
			
			
			ArrayList ints = new ArrayList<Integer>();
			
			String array[] = {"byr",
			"iyr",
			"eyr",
			"hgt",
			"hcl",
			"ecl",
			"pid",
			"cid"} ;
			
			
			boolean found[] = new boolean[array.length];
			
			for(int j=0; j<found.length; j++) {
				found[j] = false;
			}
			boolean counted = false;
			boolean invalid = false;
			for(int i=0; i<lines.size(); i++) {
				
				line = lines.get(i);
				
				if(line.equals("")) {

					for(int j=0; j<found.length; j++) {
						found[j] = false;
					}
					counted = false;
					invalid = false;
				}
				
				
				String token[] = line.split(" ");
				for(int j=0; j<token.length; j++) {
					String key = token[j].split(":")[0];
					
					for(int k=0; k<array.length; k++) {
						if(key.equals(array[k])) {
							found[k] = true;
							
							String value = token[j].split(":")[1];
							
							if(k == 0) {
								if(pint(value) < 1920 || pint(value) > 2002) {
									invalid = true;
								}
							} else if(k == 1) {
								if(pint(value) < 2010 || pint(value) > 2020) {
									invalid = true;
								}
							} else if(k==2) {
								if(pint(value) < 2020 || pint(value) > 2030) {
									invalid = true;
								}
							} else if(k==3) {
								
								if(value.contains("cm")) {
									value = value.substring(0, value.indexOf("c"));
									if(pint(value) < 150 || pint(value) > 193) {
										invalid = true;
									}
								} else if(value.contains("in")) {
									value = value.substring(0, value.indexOf("i"));
									if(pint(value) < 59 || pint(value) > 76) {
										invalid = true;
									}
									
								} else {
									invalid = true;
								}
							} else if(k==4) {
								if(value.startsWith("#") && value.length() > 1) {
									value= value.substring(1);
									
									if(value.length() == 6) {
										try {
											long temp = Long.parseLong(value,16);
										} catch(Exception e) {
											invalid = true;
										}
									} else {
										invalid = true;
									}
								} else {
									invalid = true;
								}
							} else if(k==5) {
								String tmp[] = {"amb", "blu", "brn", "gry", "grn", "hzl", "oth"};
								boolean checkValid = false;
								for(int g=0; g<tmp.length; g++) {
									if(tmp[g].equals(value)) {
										checkValid = true;
										break;
									}
								}
								
								if(checkValid == false) {
									invalid = true;
								}
							} else if(k==6) {
								
								if(value.length() == 9 && number.IsNumber.isLong(value)) {
									
								} else {
									invalid = true;
								}
							}
							/*	String array[] = {"byr",
			"iyr",
			"eyr",
			"hgt",
			"hcl",
			"ecl",
			"pid",
			"cid"} ;
			*/
						}
					}
				}
				
				boolean countIt = true;
				for(int j=0; j<array.length; j++) {
					if(found[j] == false && j!=7) {
						countIt = false;
						break;
					}
				}
				
				
				if(countIt && counted == false && invalid == false) {
					count++;
					counted = true;
				}
			}
			
			sopl("Answer: " + count);
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
