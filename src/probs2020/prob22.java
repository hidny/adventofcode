package probs2020;
import java.io.File;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

import number.IsNumber;
import utils.Mapping;
import utils.Sort;

public class prob22 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2020/prob2020in22.txt"));
			 //in = new Scanner(new File("in2020/prob2020in22.txt.test"));
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
			
			
			ArrayList<Integer> deck1 = new ArrayList<Integer>();
			
			ArrayList<Integer> deck2 = new ArrayList<Integer>();
			
			boolean player1Add = false;
			boolean player2Add = false;
			
			for(int i=0; i<lines.size(); i++) {
				
				sopl("hello");
				//int temp = Integer.parseInt(lines.get(i));
				//count+=temp;
				
				String line = lines.get(i);
				String lineCopy = String.copyValueOf(line.toCharArray());

				if(line.startsWith("Player 1")) {
					player1Add = true;
				} else if(line.startsWith("Player 2")) {
					player2Add = true;
					player1Add = false;
				} else if(line.trim().equals("")) {
					//nothing
				} else {
					
					if(player1Add) {
						sopl(pint(line.trim()));
						deck1.add(pint(line.trim()));
						
					} else if(player2Add) {
						deck2.add(pint(line.trim()));
						sopl(pint(line.trim()));
						
						
					} else {
						sopl("ahh1");
						exit(1);
					}
				}
				
			}
			
			sopl(deck1.size());
			sopl(deck2.size());
			int indexCard = 0;
			
			while(indexCard < deck1.size() && indexCard < deck2.size()) {
				
				int player1Card = deck1.get(indexCard);
				int player2Card = deck2.get(indexCard);
				
				sopl("p1 plays " + player1Card);
				sopl("p2 plays " + player2Card);
				sopl();
				
				if(player1Card > player2Card) {
					deck1.add(player1Card);
					deck1.add(player2Card);
					
				} else {
					deck2.add(player2Card);
					deck2.add(player1Card);
					
				}
				
				indexCard++;
			}

			ArrayList<Integer> winDeck = null;
			if(indexCard == deck1.size()) {
				winDeck = deck2;
			} else {
				winDeck = deck1;
			}
			
			
			for(int i=indexCard; i<winDeck.size(); i++) {
				
				sopl(winDeck.get(i));
				count += winDeck.get(i) * (winDeck.size() - i);
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
