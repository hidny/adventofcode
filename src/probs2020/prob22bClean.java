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

public class prob22bClean {

	
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
						deck1.add(pint(line.trim()));
						
					} else if(player2Add) {
						deck2.add(pint(line.trim()));
						
						
					} else {
						sopl("ahh1");
						exit(1);
					}
				}
				
			}
			

			doesPlayer1WinCombat(deck1, deck2, true);
		
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	
	public static boolean isPlayer1WinsRecursiveCombat(int indexCardOrig, ArrayList<Integer> deck1Orig, int numCard1, ArrayList<Integer> deck2Orig, int numCard2) {
		
		//sopl("RECURSION");
		ArrayList<Integer> deck1 = new ArrayList<Integer>();
		ArrayList<Integer> deck2 = new ArrayList<Integer>();
		
		
		//Copy the decks:
		for(int i=indexCardOrig; i<indexCardOrig + numCard1; i++) {
			deck1.add(deck1Orig.get(i));
			//sop(deck1.get(i - indexCardOrig) + " ");
		}
		//sopl();
		

		for(int i=indexCardOrig; i<indexCardOrig + numCard2; i++) {
			deck2.add(deck2Orig.get(i));
			//sop(deck2.get(i - indexCardOrig) + " ");
		}
		//sopl();
		
		return doesPlayer1WinCombat(deck1, deck2, false);
	}
	
	public static boolean doesPlayer1WinCombat(ArrayList<Integer> deck1, ArrayList<Integer> deck2, boolean origCombat) {

		HashSet<String> gameState = new HashSet();
		
		int indexCard = 0;
		//Copy war:
		while(indexCard < deck1.size() && indexCard < deck2.size()) {
			
			String tmpString = getGameString(indexCard, deck1, deck2);
			
			if(gameState.contains(tmpString)) {
				// Player 1 wins.
				break;
			}
			gameState.add(tmpString);
			
			
			int player1Card = deck1.get(indexCard);
			int player2Card = deck2.get(indexCard);
			

			
			//sopl("p1 plays " + player1Card);
			//sopl("p2 plays " + player2Card);
			//sopl();
			
			if(player1Card <= deck1.size() - indexCard - 1
					&& player2Card <= deck2.size() - indexCard - 1 ) {
				//Recursive combat!
				
				if(isPlayer1WinsRecursiveCombat(indexCard + 1, deck1, player1Card, deck2, player2Card)) {
					deck1.add(player1Card);
					deck1.add(player2Card);
				} else {
					deck2.add(player2Card);
					deck2.add(player1Card);
				}
				
				
			} else if(player1Card > player2Card) {
				deck1.add(player1Card);
				deck1.add(player2Card);
				
			} else {
				deck2.add(player2Card);
				deck2.add(player1Card);
				
			}
			
			indexCard++;
		}
		

		
		if(origCombat == false) {
			
			//sopl("END RECURSION");
			
			if(indexCard == deck1.size()) {
				//sopl("P2 wins RECURSION");
				return false;
			} else {
				//sopl("P1 wins RECURSION");
				return true;
			}
		} else {
			
			boolean ret = true;
			
			//Answer for part 2:
			ArrayList<Integer> winDeck = null;
			if(indexCard == deck1.size()) {
				
				//Only way player 2 wins:
				winDeck = deck2;
				ret = false;
				
				sopl("Player 2 wins the thing!");
				
			} else {
				sopl("Player 1 wins the thing!");
				winDeck = deck1;
			}
			
			long count = 0;
			
			for(int i=indexCard; i<winDeck.size(); i++) {
				
				sopl(winDeck.get(i));
				count += winDeck.get(i) * (winDeck.size() - i);
			}
			
			
			
			sopl("Answer: " + count);
			
			//End answer for part 2
			
			return true;
		}
	}
	
	public static String getGameString(int indexCard, ArrayList<Integer> deck1, ArrayList<Integer> deck2) {
		String ret ="";
		
		for(int i=indexCard; i<deck1.size(); i++) {
			ret += deck1.get(i) + ",";
		}
		ret += ":";
		for(int i=indexCard; i<deck2.size(); i++) {
			ret += deck2.get(i) + ",";
		}
		return ret;
		
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
