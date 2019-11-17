package probs2018;

import java.io.File;

import number.IsNumber;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import utils.Mapping;

public class prob25 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2018/prob2018in25.txt"));
			
			int count = 0;
			boolean part2 = false;
			String line = "";

			LinkedList queue = new LinkedList();
			Stack stack = new Stack();
			HashSet set = new HashSet();
			
			Mapping dict = new Mapping();
			ArrayList <String>lines = new ArrayList<String>();
			
			
			int LIMIT = 20000;
			boolean table[][] = new boolean[LIMIT][LIMIT];
			
			Hashtable<Long, Integer> trail = new Hashtable<Long, Integer>();
			
			//dir: 0 up
			//1 right
			//2 down
			//3 left
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				
				
				
			}
			
			boolean connected[] = new boolean[lines.size()];
			
			boolean touches[][] = new boolean[lines.size()][lines.size()];
			
			int origCount = 0;
			for(int i=0; i<lines.size(); i++) {
				
				line = lines.get(i);
				
				String numS[] = line.split(",");
				
				for(int j=0; j<i; j++) {
					String line2 = lines.get(j);

					String numS2[] = line2.split(",");
					
					int temp = 0;
					for(int k=0; k<4; k++) {
						temp += Math.abs(pint(numS[k]) - pint(numS2[k]));
					}
					
					if( temp <=3) {
						touches[i][j] = true;
						touches[j][i] = true;
						sopl(i + "   " + j);
					}
					
				}
			}
			
			int answer = 0;
			
			
			for(int i=0; i<connected.length; i++) {
				if(connected[i] == false) {
					connected[i] = true;
					getConnected(connected, touches, i);
					answer++;
				}
			}
			
			
			sopl("Answer: " + answer);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public static boolean[] getConnected(boolean connected[], boolean touches[][], int index) {
		for(int i=0; i<connected.length; i++) {
			if(touches[i][index] && connected[i] == false) {
				connected[i] = true;
				connected = getConnected(connected, touches, i);
			}
		}
		return connected;
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
