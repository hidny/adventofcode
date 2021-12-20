package probs2021;
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

public class prob20 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2021/prob2021in20.txt"));
			// in = new Scanner(new File("in2021/prob2021in21.txt"));
			
			ArrayList <String>lines = new ArrayList<String>();
			
			
			while(in.hasNextLine()) {
				String line = in.nextLine();
				lines.add(line);
				
				
				
			}
			
			int count = 0;
			
			ArrayList ints = new ArrayList<Integer>();
			
			ArrayList<String> lines2 = new ArrayList<String>();
			
			for(int i=0; i<lines.size(); i++) {
				
				//int temp = Integer.parseInt(lines.get(i));
				//count+=temp;
				
				String line = lines.get(i);
				String token[] = line.split(" ");
				
				if(i > 1) {
					lines2.add(lines.get(i));
				}
				
			}
			
			for(int i=0; i<lines2.size(); i++) {
				for(int j=0; j<lines2.get(0).length(); j++) {
					sop(lines2.get(i).charAt(j));
				}
				sopl();
			}
			sopl();
			sopl();
			sopl();
			
			
			String lightThing = lines.get(0);

			for(int n=0; n<50; n++) {
				ArrayList<String> nextImage = new ArrayList<String>();
				
				for(int i=-1; i<lines2.size() + 1; i++) {
					String newImageLine = "";
					for(int j=-1; j<lines2.get(0).length() + 1; j++) {
						
						String binary ="";
						for(int i2=i-1; i2<=i+1; i2++) {
							for(int j2=j-1; j2<=j+1; j2++) {
								
								if(i2 >=0 && i2 < lines2.size()
										&& j2 >= 0  && j2 < lines2.get(0).length()) {
									binary += lines2.get(i2).charAt(j2) + "";
								} else {
									if(n%2 == 1 && lightThing.charAt((int)0) == '#') {
										binary += "#";
									} else {
										binary += ".";
									}
								}
								
							}
						}
						
						long num = ConvertBinToLong(binary);
						
						String output = lightThing.charAt((int)num) + "";
						
						newImageLine += output;
					}
					
					nextImage.add(newImageLine);
					
				}
				//Do it twice...
				for(int i=0; i<nextImage.size(); i++) {
					for(int j=0; j<nextImage.get(0).length(); j++) {
						sop(nextImage.get(i).charAt(j));
					}
					sopl();
				}
				sopl();
				sopl();
				sopl();
				
				lines2 = nextImage;
			}
			
			count = 0;
			for(int i=0; i<lines2.size(); i++) {
				for(int j=0; j<lines2.get(0).length(); j++) {
					if(lines2.get(i).charAt(j) == '#') {
						count++;
					}
				}
			}
			
			//4942
			
			sopl("Answer: " + count);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public static long ConvertBinToLong(String bin) {
		long ret = 0L;
		
		for(int i=0; i<bin.length(); i++) {
			if(bin.charAt(i) == '#') {
				ret = 2*ret + 1;
			} else if(bin.charAt(i) == '.') {
				ret = 2*ret;
			} else {
				exit(1);
				ret = 2*ret;
			}
		}
		return ret;
	}
	
	//2nd enhance!

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

	public static long plong(String s) {
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
