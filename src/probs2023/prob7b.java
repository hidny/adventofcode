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

public class prob7b {

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
			ArrayList<Long> bids = new ArrayList<Long>();
			
			for(int i=0; i<lines.size(); i++) {
				
				
				line = lines.get(i);

				cards.add(line.split(" ")[0]);
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
			
			
			
			int typeOfWin[] = new int[cards.size()];
			for(int i=0; i<typeOfWin.length; i++) {
				typeOfWin[i] = HIGH;
			}
			
			//249894243 too high.
			
			for(int i=0; i<cards.size(); i++) {
				

				int freq[] = new int[ranks.length];
				
				String hand = cards.get(i);

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
				
				
				if(numJokers == 1) {

					if(typeOfWin[i] == FIVE_KIND) {

						sopl("doh3");
						exit(1);
					}
					if(typeOfWin[i] == FOUR_KIND) {
						typeOfWin[i] = FIVE_KIND;
						
					} else if(typeOfWin[i] == FULL_HOUSE) {
						sopl("doh");
						exit(1);
						
					} else if(typeOfWin[i] == THREE_KIND) {
						typeOfWin[i] = FOUR_KIND;
						
					} else if(typeOfWin[i] == TWO_PAIRS) {
						typeOfWin[i] = FULL_HOUSE;
						
					} else if(typeOfWin[i] == PAIR) {
						typeOfWin[i] = THREE_KIND;
						
					} else if(typeOfWin[i] == HIGH) {
						typeOfWin[i] = PAIR;
					}
				}
				
				if(numJokers == 2) {
					
					if(typeOfWin[i] < THREE_KIND) {

						sopl("doh4");
						exit(1);
					}
					
					if(typeOfWin[i] == THREE_KIND) {
						typeOfWin[i] = FIVE_KIND;
						
					} else if(typeOfWin[i] == PAIR) {
						typeOfWin[i] = FOUR_KIND;
						
					} else if(typeOfWin[i] == HIGH) {
						typeOfWin[i] = THREE_KIND;
					}
				}

				if(numJokers == 3) {
					
					if(typeOfWin[i] < PAIR) {

						sopl("doh5");
						exit(1);
					}
					
					if(typeOfWin[i] == PAIR) {
						typeOfWin[i] = FIVE_KIND;
						
					} else if(typeOfWin[i] == HIGH) {
						typeOfWin[i] = FOUR_KIND;
					}
				}
				
				if(numJokers >= 4 ) {
					
					if(typeOfWin[i] != HIGH) {
						sopl("DOH!");
						exit(1);
					}
					typeOfWin[i] = FIVE_KIND;
					
				}
				
			
			}//AKJ9K

			for(int i=0; i<cards.size(); i++) {
				
				
				for(int j=i+1; j<cards.size(); j++) {
				
					if(typeOfWin[i] > typeOfWin[j]) {
						//swap
						//ArrayList<String> cards = new ArrayList<String>();
						//ArrayList<Long> bids = new ArrayList<Long>();
						//int typeOfWin[];
						
						
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
			for(int i=0; i<cards.size(); i++) {
				int rank = cards.size() - i;
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
