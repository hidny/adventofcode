package probs;

import java.io.File;
import java.util.Scanner;

public class prob16 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in;
		Scanner in2;
		try {
			 in = new Scanner(new File("prob16in.txt"));
			 boolean isPart2 = true;
			 in2 = new Scanner(System.in);
			 String line = "";
			 
			 //int diskLengthTest = 20;
			 int diskLength = 0;
			 if(isPart2 == false) {
				 diskLength = 272;
			 } else {
				 diskLength = 35651584;
			 }
			
			 
			System.out.println("Start");
			 while(in.hasNextLine()) {
				 line = in.nextLine();
				 System.out.println(line);
				 
				 
			 }
			 
			 boolean a[] = getBoolArrayFromString(line);
			 while(a.length < diskLength) {
				 a = FillTheDisk(a);
			 }

			 boolean b[] = new boolean[diskLength];
			 for(int i=0; i<b.length; i++) {
				 b[i] = a[i];
			 }
			 a = b;
			 
			 //Calc checksum:
			 while(a.length % 2 == 0) {
				 a = checkSum(a);
			 }

			 System.out.println("Checksum: " + getStringFromBoolArray(a));
			 
			 in2.close();
			 in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boolean[] FillTheDisk(boolean a[]) {
		boolean b[] = new boolean[a.length];
		for(int i=0; i<a.length; i++) {
			if(a[i]) {
				b[a.length - 1 - i] = false;
			} else {
				b[a.length - 1 - i] = true;
			}
		}
		
		boolean ret[] = new boolean[a.length * 2 + 1];
		for(int i=0; i<ret.length; i++) {
			if(i<a.length) {
				ret[i] = a[i];
			} else if(i >= a.length + 1) {
				ret[i] = b[i - a.length - 1];
			} else {
				ret[i] = false;
			}
		}
		return ret;
	}
	
	//pre: a is even length
	public static boolean[] checkSum(boolean a[]) {
		boolean ret[] = new boolean[a.length / 2];
		for(int i=0; i<a.length; i=i+2) {
			if((a[i] && a[i+1]) || (a[i] == false && a[i+1] == false)) {
				ret[i/2] = true;
			} else {
				ret[i/2] = false;
			}
		}
		return ret;
	}
	
	public static boolean[] getBoolArrayFromString(String a) {
		boolean ret[] = new boolean[a.length()];
		for(int i=0; i<a.length(); i++) {
			ret[i] = (a.charAt(i) == '1');
		}
		return ret;
	}
	
	public static String getStringFromBoolArray(boolean a[]) {
		String ret = "";
		for(int i=0; i<a.length; i++) {
			if(a[i]) {
				ret += "1";
			} else {
				ret += "0";
			}
		}
		return ret;
	}
}

