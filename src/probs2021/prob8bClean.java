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

public class prob8bClean {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2021/prob2021in8.txt"));
			 //in = new Scanner(new File("in2021/prob2021in9.txt"));
			int numTimes = 0;
			 
			int count = 0;
			boolean part2 = false;

			LinkedList queue = new LinkedList();
			Stack stack = new Stack();
			HashSet set = new HashSet();
			
			
			Hashtable<Long, Integer> trail = new Hashtable<Long, Integer>();
			
			ArrayList <String>lines = new ArrayList<String>();
			
			
			//int LIMIT = 20000;
			//boolean table[][] = new boolean[LIMIT][LIMIT];
			
			
			//dir: 0 up
			//1 right
			//2 down
			//3 left
			
			while(in.hasNextLine()) {
				String line = in.nextLine();
				lines.add(line);
				
				
			}
			
			
			ArrayList ints = new ArrayList<Integer>();
			
			long answer = 0;
			
			for(int i=0; i<lines.size(); i++) {
				
				//int temp = Integer.parseInt(lines.get(i));
				//count+=temp;
				String line = lines.get(i);
				sopl(line);
				String token[] = line.substring(0, line.indexOf('|')).split(" ");
				
				String token2[] = line.substring(line.indexOf('|') + 1).trim().split(" ");
				
				String one="";
				String seven="";
				String four="";
				String eight="";
				
				for(int j=0; j<token.length; j++) {

					//long pow = (long)Math.pow(10, 3 - j);
					
					if(token[j].length() == 2) {
						sopl("one: " +token[j]);
						one = token[j];
						
					} else if(token[j].length() == 4) {
						sopl("four: " + token[j]);
						four = token[j];
						
					} else if(token[j].length() == 3) {
						sopl("seven: " + token[j]);
						
						seven=token[j];
						
					} else if(token[j].length() == 7) {
						sopl("eight: " + token[j]);
						eight=token[j];
					}
				}
				
				
				
				
				
				String three = "";
				
				for(int j=0; j<token.length; j++) {

					//long pow = (long)Math.pow(10, 3 - j);
					
					if(token[j].length() == 5) {
						
						boolean foundThree = true;
						for(int k=0; k<seven.length(); k++) {
							if(token[j].indexOf(seven.charAt(k)) == -1) {
								foundThree = false;
								break;
							}
						}
						
						
						if(foundThree) {
							three = token[j];
							break;
						}
						
					}
				}
				
				sopl("three: " + three);
				
				
				String nine = "";
				
				for(int j=0; j<token.length; j++) {

					//long pow = (long)Math.pow(10, 3 - j);
					
					if(token[j].length() == 6) {
						
						boolean foundNine = true;
						for(int k=0; k<four.length(); k++) {
							if(token[j].indexOf(four.charAt(k)) == -1) {
								foundNine = false;
								break;
							}
						}
						
						
						if(foundNine) {
							nine = token[j];
							break;
						}
						
					}
				}
				
				sopl("nine: " + nine);
				
				String zero = "";
				
				for(int j=0; j<token.length; j++) {

					//long pow = (long)Math.pow(10, 3 - j);
					
					if(token[j].length() == 6) {
						
						if(token[j].equals(nine)) {
							continue;
							
						}
						
						boolean foundZero = true;
						for(int k=0; k<seven.length(); k++) {
							if(token[j].indexOf(seven.charAt(k)) == -1) {
								foundZero = false;
								break;
							}
						}
						
						
						if(foundZero) {
							zero = token[j];
							break;
						}
						
					}
				}
				
				sopl("zero: " + zero);
				
				String six = "";
				
				for(int j=0; j<token.length; j++) {

					//long pow = (long)Math.pow(10, 3 - j);
					
					if(token[j].length() == 6) {
						
						if(token[j].equals(nine)) {
							continue;
							
						} else if(token[j].equals(zero)) {
							continue;
						}
						six = token[j];
						
					}
				}
				
				sopl("six: " + six);
				
				String five = "";
				for(int j=0; j<token.length; j++) {

					//long pow = (long)Math.pow(10, 3 - j);
					
					if(token[j].length() == 5) {

						int numMismatch = 0;
						
						for(int k=0; k<six.length(); k++) {
							if(token[j].indexOf(six.charAt(k)) == -1) {
								numMismatch++;
							}
						}
						
						
						if(numMismatch == 1) {
							five = token[j];
							break;
						}
					}
					
				}
				
				sopl("five: " + five);
				
				String two = "";
				for(int j=0; j<token.length; j++) {

					//long pow = (long)Math.pow(10, 3 - j);
					
					if(token[j].length() == 5) {
						if(token[j].equals(five) || token[j].equals(three)) {
							continue;
						} else {
							two = token[j];
						}
					}
					
				}
				
				sopl("Two: " + two);
				
				int miniAnswer = 0;
				for(int j=0; j<token2.length; j++) {
					long pow = (long)Math.pow(10, 3-j);
					String num = token2[j];
					
					sopl("num: " + num);
					if(matches(num, zero)) {
						miniAnswer += 0*pow;
						
					} else if(matches(num, one)) {
						miniAnswer += 1*pow;
						
					} else if(matches(num, two)) {
						miniAnswer += 2*pow;
						
					} else if(matches(num, three)) {
						miniAnswer += 3*pow;
						
					} else if(matches(num, four)) {
						miniAnswer += 4*pow;
						
					} else if(matches(num, five)) {
						miniAnswer += 5*pow;
						
					} else if(matches(num, six)) {
						miniAnswer += 6*pow;
						
					} else if(matches(num, seven)) {
						miniAnswer += 7*pow;
						
					} else if(matches(num, eight)) {
						miniAnswer += 8*pow;
						
					} else if(matches(num, nine)) {
						miniAnswer += 9*pow;
						
					} else {
						sopl("doh");
						exit(1);
					}
				}
				
				sopl("miniAnswer: " + miniAnswer);
				
				answer += miniAnswer;

				sopl("end line");
			}
			
			
			//1037600
			
			sopl("Answer: " + answer);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}

	public static boolean matches(String a, String b) {
		if(a.length() != b.length()) {
			return false;
		}
		
		for(int i=0; i<a.length(); i++) {
			if(b.indexOf(a.charAt(i)) == -1) {
				return false;
			}
		}
		
		return true;
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
