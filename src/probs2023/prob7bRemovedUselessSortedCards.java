package probs2023;
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

public class prob7bRemovedUselessSortedCards {

	//day1 part 1
	//2:38.01
	
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2023/prob2023in7.txt"));
			//in = new Scanner(new File("in2023/prob2023in0.txt"));
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
			boolean table[][] = new boolean[LIMIT][LIMIT];
			
			
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
			int cur = 0;
			ArrayList ints = new ArrayList<Integer>();
			

			ArrayList<String> cards = new ArrayList<String>();
			ArrayList<String> cardsSorted = new ArrayList<String>();
			ArrayList<Long> bids = new ArrayList<Long>();
			
			for(int i=0; i<lines.size(); i++) {
				
				
				line = lines.get(i);

				cards.add(line.split(" ")[0]);
				cardsSorted.add(sortByRank(line.split(" ")[0]));
				bids.add(new Long(pint(line.split(" ")[1])));
			}

			
			
			String ranks[] = {"J", "2", "3", "4", "5", "6", "7", "8", "9", "T", "Q", "K", "A"};
			
			
			int FIVE_KIND = 0;
			int FOUR_KIND = 1;
			int FULL_HOUSE = 2;
			int THREE_KIND = 3;
			int TWO_PAIRS = 4;
			int PAIR = 5;
			int HIGH = 6;
			
			
			
			int typeOfWin[] = new int[cardsSorted.size()];
			for(int i=0; i<typeOfWin.length; i++) {
				typeOfWin[i] = HIGH;
			}
			
			//249894243 too high.
			
			for(int i=0; i<cardsSorted.size(); i++) {
				

				int freq[] = new int[ranks.length];
				
				String hand = cardsSorted.get(i);

				int numJokers = 0;
				for(int j=0; j<hand.length(); j++) {
					if(hand.charAt(j) == 'J') {
						numJokers++;
					}
				}
				
				for(int j=0; j<hand.length(); j++) {
					
					for(int k=0; k<ranks.length; k++) {
						if(hand.charAt(j) == ranks[k].charAt(0)
								&& hand.charAt(j) != 'J') {
							freq[k]++;
						}
					}
					
				}
				
				//Dump jokers to highest freq:
				
				int highestFreqIndex = 0;
				for(int k=0; k<ranks.length; k++) {
					if(freq[k] > freq[highestFreqIndex]) {
						highestFreqIndex = k;
					}
				}
				
				freq[highestFreqIndex] += numJokers;
				
				
				//5 of a kind:
				for(int k=0; k<ranks.length; k++) {
					
					if(freq[k] == 5) {
						typeOfWin[i] = FIVE_KIND;
						break;
						
					} else if(freq[k] == 4) {
						typeOfWin[i] = FOUR_KIND;
						break;
					
					} else if(freq[k] == 3) {
						
						if(typeOfWin[i] == PAIR) {
							typeOfWin[i] = FULL_HOUSE;
						} else {
							typeOfWin[i] = THREE_KIND;
						}
						
					} else if(freq[k] == 2) {
						
						if(typeOfWin[i] == THREE_KIND) {
							typeOfWin[i] = FULL_HOUSE;
						
						} else if(typeOfWin[i] == PAIR) {
							typeOfWin[i] = TWO_PAIRS;
						} else {
							typeOfWin[i] = PAIR;
						}
					}
					
				}
				
				
			
			}//AKJ9K

			for(int i=0; i<cardsSorted.size(); i++) {
				
				
				for(int j=i+1; j<cardsSorted.size(); j++) {
				
					if(typeOfWin[i] > typeOfWin[j]) {
						//swap
						//ArrayList<String> cards = new ArrayList<String>();
						//ArrayList<Long> bids = new ArrayList<Long>();
						//int typeOfWin[];
						
						String tmp = cardsSorted.get(i) + "";
						cardsSorted.set(i, cardsSorted.get(j) + "");
						cardsSorted.set(j, tmp);
						
						long tmp2 = bids.get(i);
						bids.set(i, bids.get(j));
						bids.set(j, tmp2);
						
						int tmp3 = typeOfWin[i];
						typeOfWin[i] = typeOfWin[j];
						typeOfWin[j] = tmp3;
						

						String tmp4 = cards.get(i) + "";
						cards.set(i, cards.get(j) + "");
						cards.set(j, tmp4);
								
						
					} else if(typeOfWin[i] == typeOfWin[j]) {
						
						for(int k=0; k<cards.get(i).length(); k++) {
							if(getRank(cards.get(i).charAt(k)) < getRank(cards.get(j).charAt(k))) {
								//Swap
								String tmp = cardsSorted.get(i) + "";
								cardsSorted.set(i, cardsSorted.get(j) + "");
								cardsSorted.set(j, tmp);
								
								long tmp2 = bids.get(i);
								bids.set(i, bids.get(j));
								bids.set(j, tmp2);
								
								int tmp3 = typeOfWin[i];
								typeOfWin[i] = typeOfWin[j];
								typeOfWin[j] = tmp3;
								

								String tmp4 = cards.get(i) + "";
								cards.set(i, cards.get(j) + "");
								cards.set(j, tmp4);
								
								break;
							} else if(getRank(cards.get(i).charAt(k)) > getRank(cards.get(j).charAt(k))) {
								break;
							}
						}
					}
					
					
				}
			}
			
			//248670665
			//248670665
			
			
			//TOO HIGH
			//250392979
			
			cur = 0;
			for(int i=0; i<cardsSorted.size(); i++) {
				int rank = cardsSorted.size() - i;
				sopl(rank);
				
				sopl(cards.get(i));
				sopl(typeOfWin[i]);
				
				cur += rank * bids.get(i);
				sopl();
			}
			

			sopl("Answer: " + cur);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public static int getRank(char c) {
		
		if(c >= '0' && c <= '9') {
			return (int) (c - '0');
		} else if(c == 'T') {
			return 10;
		} else if(c == 'J') {
			return 0;
		} else if(c == 'Q') {
			return 12;
		} else if(c == 'K') {
			return 13;
		} else if(c == 'A') {
			return 14;
		} else {
			exit(1);
			return -1;
		}
	}
	
	public static String sortByRank(String hand) {
		
		for(int i=0; i<hand.length(); i++) {
			for(int j=i+1; j<hand.length(); j++) {
				
				if(getRank(hand.charAt(i)) < getRank(hand.charAt(j))) {
					
					hand = hand.substring(0, i) + hand.charAt(j) + hand.substring(i+1, j) + hand.charAt(i) + hand.substring(j+1);
				}
			}
		}
		
		//check 4 in a row:

		boolean done = false;
		
		for(int i=0; i<hand.length(); i++) {
			
			int numInARow = 1;
			for(int j=i+1; j<hand.length(); j++) {
				
				if(getRank(hand.charAt(i)) == getRank(hand.charAt(j))) {
					numInARow++;
					
				} else {
					break;
				}
				
				
			}
			
			if(numInARow >= 3) {
				done = true;
				
				if(i > 0) {
					if(numInARow == 5) {
						
					} else if(numInARow == 4) {
						hand = hand.substring(1) + hand.charAt(0);
					} else if(numInARow == 3) {
						
						//sopl("hello2");
						if(i == 1) {
							hand = hand.substring(1, 4) + hand.charAt(0) + hand.charAt(4);
						} else if(i == 2) {
							hand = hand.substring(2, 5) + hand.charAt(0) + hand.charAt(1);
						}
					}
					
					break;
				}
			}
			
			if(numInARow == 2) {
				hand = hand.substring(i, i + 2) + hand.substring(0, i) + hand.substring(i+2, 5);
				
				if(hand.length() != 5) {
					sopl("doh");
					exit(1);
				}
			}
		}
		

		//2 pairs:
		if(done == false) {
			int numPairs = 0;
			
			int oldPairRank = -1;
			char oldPairChar = 'D';
			
			for(int i=1; i<hand.length(); i++) {
				
				if(getRank(hand.charAt(i)) == getRank(hand.charAt(i-1))) {
					numPairs++;
					
					
					
					if(numPairs == 2) {
						
						if(getRank(hand.charAt(i)) > oldPairRank) {
							
							char newPairChar = hand.charAt(i);
							
							hand = hand.replace(oldPairChar, '?');
							
							hand = hand.replace(newPairChar, oldPairChar);
							hand = hand.replace('?', newPairChar);
							
							for(int k=0; k<5; k++) {
								if(hand.charAt(k) != oldPairChar && hand.charAt(k) != newPairChar ) {
									
									//sopl("hello");
									hand = hand.substring(0, k) + hand.substring(k+1, 5) + hand.charAt(k);
								}
							}
						}
					} else if(numPairs > 2) {
						sopl("doh");
						exit(1);
					}
					
					oldPairRank = getRank(hand.charAt(i));
					oldPairChar = hand.charAt(i);
				}
			}
		}
		
		//sopl(hand);
		return hand;
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
