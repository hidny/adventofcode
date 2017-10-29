package probs2015;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class prob7Gate {

	public static int TWO_POWER_16 = (int)Math.pow(2,  16);
	public static int LIMIT = 10000;
	
	public static prob7Gate wires[] = new prob7Gate[LIMIT];
	
	private String line;
	
	
	private boolean valueFound = false;
	private int value;
	
	public prob7Gate(String source) {
		this.line = source;
		
	}
	
	public static void addToList(String line) {
		String tokens[] = line.split(" ");
		wires[convertLetterToIndex(tokens[tokens.length - 1])] = new prob7Gate(line);
	}
	
	//Recursive call to get the value.
	public int getValue() {
		
		if(this.valueFound) {
			return this.value;
		}
		
		int valuesFromIndex[] = new int[3];
		
		
		String tokens[] = line.split(" ");

		
		for(int i=0; i<tokens.length - 1 && i<3; i++) {
			if(isInteger(tokens[i])) {
				valuesFromIndex[i] = Integer.parseInt(tokens[i]);
			} else {
				if(tokens[i].equals("AND") || tokens[i].equals("LSHIFT") || tokens[i].equals("RSHIFT") || tokens[i].equals("OR") || tokens[i].equals("NOT") || tokens[i].equals("->")) {
					valuesFromIndex[i] = -1;
				} else {
					valuesFromIndex[i] = wires[convertLetterToIndex(tokens[i])].getValue();
					
				}
			}
		}
		
		
		int ret = -1;
		
		if(tokens.length == 3) {
			ret = valuesFromIndex[0];
			
		} else if(line.contains("AND")) {
			ret = valuesFromIndex[0] & valuesFromIndex[2];
			
		} else if(line.contains("LSHIFT")) {
			ret = valuesFromIndex[0] << valuesFromIndex[2];
			ret = fixWire(ret);

		} else if(line.contains("RSHIFT")) {
			ret = valuesFromIndex[0] >> valuesFromIndex[2];
			
		} else if(line.contains("OR")) {
			ret = valuesFromIndex[0] | valuesFromIndex[2];
			
		} else if(line.contains("NOT")) {
			ret = ~valuesFromIndex[1];
			ret = fixWire(ret);
			
			
		} else {
			System.out.println("Unknown command!");
			System.exit(1);
		}
	
		this.valueFound = true;
		this.value = ret;
		
		
		return this.value;
	}
	
	public void resetValue() {
		this.valueFound = false;
		this.value = 0;
	}
	
	public void setValue(int value) {
		this.valueFound = true;
		this.value = value;
	}
	
	public static String ConvertIndexToString(int index) {
		String ret = "";
		
		while(index > 0) {
			
			char lastLetter = (char)('a' + (index-1) % 30);
			
			ret = lastLetter + ret;
			index = index - (index%30);
			index /= 30;
			
		}
		
		return ret;
	}
	
	public static int convertLetterToIndex(String letter) {
		int ret = 0;
		for(int i=0; i<letter.length(); i++) {
			//If it's not a variable, just return -1:
			if((int)(letter.charAt(i) - 'a') > 25 || (int)(letter.charAt(i) - 'a') < 0) {
				System.out.println(letter);
				return -1;
			}
			ret = (int)(1 + letter.charAt(i) - 'a') + ret*30;
		}
		
		return ret;
	}
	
	public static int fixWire(int ret) {
		ret = ret % TWO_POWER_16;


		if(ret < 0) {
			ret += TWO_POWER_16;
		}
		
		if(ret < 0 || ret >= TWO_POWER_16) {
			System.out.println("ERROR what!");
			System.out.println(ret);
			System.exit(1);
		}
		return ret;
	}
	
	public static boolean isInteger(String token) {
		try {
			int num = Integer.parseInt(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	
	public static void testing() {
		int wires[] = new int[10000];
		System.out.println(convertLetterToIndex("a"));
		System.out.println(convertLetterToIndex("z"));
		
		System.out.println(convertLetterToIndex("lz"));
		System.out.println(convertLetterToIndex("ma"));
		System.out.println(convertLetterToIndex("aaa"));
		System.out.println("Fix wires: " );
		wires[0] = -65536;
		wires[0] = fixWire(-65536);
		System.out.println(wires[0]);

		wires[0] = -65536;
		wires[0] = fixWire(wires[0]);
		System.out.println(wires[0]);

		wires[0] = -100000;
		//1111111111111110
		//0111100101100000
	
		for(int i=-10000; i< 1000000; i++) {
			wires[0] = i;
			wires[0] = fixWire(wires[0]);
		}
		System.out.println(wires[0]);
		
		boolean taken[] = new boolean[10000];
		String c1;
		String c2;
		for(int i=0; i<26; i++) {
			c1 = "" + (char)('a' + i);
			
			System.out.println(c1 + ": " + convertLetterToIndex(c1));
			System.out.println(ConvertIndexToString(convertLetterToIndex(c1)));
			
			if(ConvertIndexToString(convertLetterToIndex(c1)).equals(c1) == false) {
				System.out.println("ERROR c1");
				System.exit(1);
			}
			
			if(taken[convertLetterToIndex(c1 + "")]) {
				System.out.println("ERROR");
				System.exit(1);
			} else {
				taken[convertLetterToIndex(c1 + "")] = true;
			}
		}
		for(int i=0; i<26; i++) {
			for(int j=0; j<26; j++) {
				c1 = "" + (char)('a' + i);
				c2 = "" + (char)('a' + j);

				System.out.println(c1 + c2 + ": " + convertLetterToIndex(c1 + c2));
				System.out.println(ConvertIndexToString(convertLetterToIndex(c1 + c2)));
				if(ConvertIndexToString(convertLetterToIndex(c1 + c2)).equals(c1 + c2) == false) {
					System.out.println("ERROR");
					System.exit(1);
				}
				
				if(taken[convertLetterToIndex(c1 + c2)]) {
					System.out.println("ERROR");
					System.exit(1);
				} else {
					taken[convertLetterToIndex(c1 +c2)] = true;
				}
			}
		}
		System.exit(1);
		
	}
	
}
