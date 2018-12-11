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

public class prob10Exp {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2018/prob2018in10.txt"));
			
			int count = 0;
			boolean part2 = false;
			String line = "";

			LinkedList queue = new LinkedList();
			Stack stack = new Stack();
			HashSet set = new HashSet();
			
			Mapping dict = new Mapping();
			ArrayList <String>lines = new ArrayList<String>();
			
			
			int LIMIT_I = 100;
			int LIMIT_J = 400;
			
			int table[][] = new int[LIMIT_I][LIMIT_J];
			
			Hashtable<Long, Integer> trail = new Hashtable<Long, Integer>();
			
			//dir: 0 up
			//1 right
			//2 down
			//3 left
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				
				
			}
			Scanner input = new Scanner(System.in);
			
			int time = 0;
			int origCount = 0;
			while(time < 100) {
				table = new int[LIMIT_I][LIMIT_J];
				
				for(int i=0; i<LIMIT_I; i++) {
					for(int j=0; j<LIMIT_J;j++) {
						table[i][j] = -10000;
					}
				}
				
				for(int i=0; i<lines.size(); i++) {
					line = lines.get(i);
					//position=< 31681,  42194> velocity=<-3, -4>
					int posxj = pint(line.split("<")[1].split(",")[0]);
					int posyi = pint(line.split("<")[1].split(">")[0].split(",")[1]);
					
					String vel = line.split("<")[2];
					int velxj = pint(vel.split(",")[0]);
					int velyi = pint(vel.split(",")[1].replaceAll(">", ""));
					
					int ai = (posyi + time * velyi) % LIMIT_I;
					int aj = (posxj + time * velxj) % LIMIT_J;
					
					table[(LIMIT_I/2 + LIMIT_I + ai) % LIMIT_I][(LIMIT_J/2 + LIMIT_J + aj) % LIMIT_J] = (posxj + time * velxj) / LIMIT_J;
					sop(i);
				}
				
				for(int i=0; i<LIMIT_I; i++) {
					for(int j=0; j<LIMIT_J;j++) {
						if(table[i][j] != -10000) {
							System.out.print(table[i][j]);
						} else {
							System.out.print("_");
						}
					}
					sop("");
				}
				
				if(time >= 21) {
					input.next();
				}
				
				sop(time);
				sop("");
				sop("");
				sop("");
				time++;
			
			}
			
			
			sopl("Answer: " + count);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public static void sop(Object a) {
		System.out.println(a.toString());
	}
	public static void sopl(Object a) {
		System.out.println(a.toString());
	}
	
	public static int pint(String s) {
		if (IsNumber.isNumber(s.trim())) {
			return Integer.parseInt(s.trim());
		} else {
			sop("Error: (" + s + ") is not a number");
			exit(1);
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
