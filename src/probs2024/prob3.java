package probs2024;
import java.io.File;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

import number.IsNumber;
import utils.Mapping;
import utils.Sort;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class prob3 {

	//day1 part 1
	//2:38.01
	
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2024/prob2024in3.txt"));
			//in = new Scanner(new File("in2024/prob2024in0.txt"));
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
			boolean table342[][] = new boolean[LIMIT][LIMIT];
			
			
			//dir: 0 up
			//1 right
			//2 down
			//3 left
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				
			}

			int most = 0;
			int most2 = 0;
			int most3 = 0;
			long cur = 0L;
			ArrayList ints = new ArrayList<Integer>();
			for(int i=0; i<lines.size(); i++) {
				
				
				line = lines.get(i);
				//sopl(line);
				
				ArrayList<String> allMatches = new ArrayList<String>();
				 //Matcher m = Pattern.compile(".*mul(\\d{1,3},\\d{1,3}).*")
				Matcher m = Pattern.compile("mul\\(\\d{1,3},\\d{1,3}\\)")
				     .matcher(line);
				 while (m.find()) {
				   allMatches.add(m.group());
				   
				 }
				 
				 for(int j=0; j<allMatches.size(); j++) {
					 sopl(allMatches.get(j));
					 String tokens[] = allMatches.get(j).toString().split(",");
					 
					 sopl(tokens[0]);
					 sopl(tokens[1]);
					 int num1 = pint(tokens[0].split("\\(")[1]);
					 int num2 = pint(tokens[1].split("\\)")[0]);
					 cur+=num1*num2;
				 }
				 
			}

			

			sopl("Answer: " + cur);
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
			sop("Error: (" + s + ") is not a number");
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
