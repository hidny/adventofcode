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

public class prob10Fixed {

	
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
			
			
			int LIMIT_I = 102;
			int LIMIT_J = 102;
			
			boolean table[][] = new boolean[LIMIT_I][LIMIT_J];
			
			Hashtable<Long, Integer> trail = new Hashtable<Long, Integer>();
			
			//dir: 0 up
			//1 right
			//2 down
			//3 left
			
			int minJ =0;
			int maxJ =0;
			
			int minI =0;
			int maxI =0;
			
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				
				
			}
			Scanner input = new Scanner(System.in);
			
			int time = 10000;
			int origCount = 0;
			while(time < 1000000) {
				table = new boolean[LIMIT_I][LIMIT_J];
				
				for(int i=0; i<lines.size(); i++) {
					line = lines.get(i);
					//position=< 31681,  42194> velocity=<-3, -4>
					int posxj = pint(line.split("<")[1].split(",")[0]);
					int posyi = pint(line.split("<")[1].split(">")[0].split(",")[1]);
					
					String vel = line.split("<")[2];
					int velxj = pint(vel.split(",")[0]);
					int velyi = pint(vel.split(",")[1].replaceAll(">", ""));
					
					//sop(line);
					//sop(posxj);
					//sop(posyi);
					
					//sop(velxj);
					//sop(velyi);
					//sop("---");
					//input.next();
					
					
					int ai = (posyi + time * velyi) % LIMIT_I;
					
					ai = (ai + LIMIT_I) % LIMIT_I;
					
					int aj = (posxj + time * velxj) % LIMIT_J;
					aj = (aj + LIMIT_J) % LIMIT_J;
					
					if(i == 0) {
						 minJ = posxj + time * velxj;
						 maxJ = posxj + time * velxj;
						
						 minI =(posyi + time * velyi);
						 maxI =(posyi + time * velyi);
					}
					int foundI = posyi + time * velyi;
					int foundJ = posxj + time * velxj;
					
					if(maxJ < foundJ) {
						maxJ = foundJ;
					}
					
					if(minJ > foundJ) {
						minJ = foundJ;
					}
					
					if(maxI < foundI) {
						maxI = foundI;
					}
					
					if(minI > foundI) {
						minI = foundI;
					}
					
					//sop(foundJ + " " + foundI);
					table[ai][aj] = true;
				}
				
				for(int i=0; i<LIMIT_I; i++) {
					for(int j=0; j<LIMIT_J;j++) {
						if(table[i][j]) {
							System.out.print("#");
						} else {
							System.out.print("_");
						}
					}
					sop("");
				}
				
				sop(maxJ);
				sop(minJ);
				sop(maxI);
				sop(minI);
				sop("Width (Fixed): " + (maxJ - minJ));
				sop("Height: " + (maxI - minI));
				sop("Time: " + time);
				sop("");
				sop("");
				sop("");
				if(maxI - minI < 100) {
					input.next();
				}
				
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
