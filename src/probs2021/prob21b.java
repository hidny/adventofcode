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

public class prob21b {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2021/prob2021in21.txt"));
			// in = new Scanner(new File("in2021/prob2021in22.txt"));
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
			
			
			for(int i=0; i<lines.size(); i++) {
				
				//int temp = Integer.parseInt(lines.get(i));
				//count+=temp;
				
				String line = lines.get(i);
				String token[] = line.split(" ");
				
				sopl(token[0]);
				
			}
			
			int WIN = 21;
			
			long scores[][][][] = new long[10][10][50][50];
			long prevScores[][][][] = new long[10][10][50][50];
			
			//int score1 = 0;
			//int score2 = 0;
			
			int startpos1 = 3 -1;
			int startpos2 = 5 -1;
			
			
			scores[startpos1][startpos2][0][0] = 1;
			
			long numWinsPlayer1 = 0;
			long numWinsPlayer2 = 0;
			
			long numBranches = 1;
			
			for(int n=0; n<60; n++) {
				
					for(int m=0; m<3; m++) {
						//Three rolls!
						prevScores = scores;
						scores = new long[10][10][50][50];
						
						for(int pos1=0; pos1<10; pos1++) {
							for(int pos2=0; pos2<10; pos2++) {
								
								for(int i=0; i<scores[0][0].length; i++) {
									for(int j=0; j<WIN; j++) {
										
										if( prevScores[pos1][pos2][i][j] > 0) {
										//Roll1:
											if(m==2) {
												//You only get points after 3 rolls:
												scores[(pos1+1) % 10][pos2][i+1+ (pos1+1) % 10][j] += prevScores[pos1][pos2][i][j];
												scores[(pos1+2) % 10][pos2][i+1+ (pos1+2) % 10][j] += prevScores[pos1][pos2][i][j];
												scores[(pos1+3) % 10][pos2][i+1+ (pos1+3) % 10][j] += prevScores[pos1][pos2][i][j];
											} else {
												scores[(pos1+1) % 10][pos2][i][j] += prevScores[pos1][pos2][i][j];
												scores[(pos1+2) % 10][pos2][i][j] += prevScores[pos1][pos2][i][j];
												scores[(pos1+3) % 10][pos2][i][j] += prevScores[pos1][pos2][i][j];
												
											}
											//sopl(i);
											//sopl("Num branches3: " + numBranches);
											numBranches += 2*prevScores[pos1][pos2][i][j];
										}
										
									}
								}
							}
						}
						//sopl("Num branches1: " + numBranches);
					}
					

					int sum = 0;
					for(int pos1=0; pos1<10; pos1++) {
						for(int pos2=0; pos2<10; pos2++) {
							
							for(int i=0; i<50; i++) {
								for(int j=0; j<50; j++) {
									if(scores[pos1][pos2][i][j] > 0) {
										sum += scores[pos1][pos2][i][j];
									}
								}
							}
								
						}
						//sopl("Num branches2: " + numBranches);
					}
					sopl("sum11: " + sum);
					sopl("sum wins p1: " + numWinsPlayer1);
					sopl("sum wins p2: " + numWinsPlayer2);
					sopl("Num branches11: " + numBranches);
					sopl();
					
					//TODO: remove winning scores
					
					for(int pos1=0; pos1<10; pos1++) {
						for(int pos2=0; pos2<10; pos2++) {
							
							for(int i=WIN; i<scores[0][0].length; i++) {
								for(int j=0; j<WIN; j++) {
									
									//Roll1:
									numWinsPlayer1 += scores[pos1][pos2][i][j];
									scores[pos1][pos2][i][j] = 0;
									
									
								}
								
								for(int j=WIN; j<scores[0][0][0].length; j++) {
									
									if(scores[pos1][pos2][i][j] != 0) {
										sopl("oops1!");
										exit(1);
									}
								}
							}
						}
					}
					
					sum = 0;
					for(int pos1=0; pos1<10; pos1++) {
						for(int pos2=0; pos2<10; pos2++) {
							
							for(int i=0; i<50; i++) {
								for(int j=0; j<50; j++) {
									if(scores[pos1][pos2][i][j] > 0) {
										sum += scores[pos1][pos2][i][j];
									}
								}
							}
								
						}
						//sopl("Num branches2: " + numBranches);
					}
					sopl("sum12: " + sum);
					sopl("sum wins p1: " + numWinsPlayer1);
					sopl("sum wins p2: " + numWinsPlayer2);
					sopl("Num branches12: " + numBranches);
					sopl();
					
					
					for(int m=0; m<3; m++) {
						//Three rolls!
						prevScores = scores;
						scores = new long[10][10][50][50];
						
						for(int pos1=0; pos1<10; pos1++) {
							for(int pos2=0; pos2<10; pos2++) {
								
								for(int i=0; i<WIN; i++) {
									for(int j=0; j<scores[0][0][0].length; j++) {
										
										if( prevScores[pos1][pos2][i][j] > 0) {
											if(m==2) {
												//You only get points after 3 rolls:
												scores[pos1][(pos2+1) % 10][i][j+1+ (pos2+1) % 10] += prevScores[pos1][pos2][i][j];
												scores[pos1][(pos2+2) % 10][i][j+1+ (pos2+2) % 10] += prevScores[pos1][pos2][i][j];
												scores[pos1][(pos2+3) % 10][i][j+1+ (pos2+3) % 10] += prevScores[pos1][pos2][i][j];
											} else {
												scores[pos1][(pos2+1) % 10][i][j] += prevScores[pos1][pos2][i][j];
												scores[pos1][(pos2+2) % 10][i][j] += prevScores[pos1][pos2][i][j];
												scores[pos1][(pos2+3) % 10][i][j] += prevScores[pos1][pos2][i][j];
												
											}
											numBranches += 2*prevScores[pos1][pos2][i][j];
											//sopl(j);
											//sopl("Num branches4: " + numBranches);
										}
									}
								}
							}
						}
					}
					
					sum = 0;
					for(int pos1=0; pos1<10; pos1++) {
						for(int pos2=0; pos2<10; pos2++) {
							
							for(int i=0; i<50; i++) {
								for(int j=0; j<50; j++) {
									if(scores[pos1][pos2][i][j] > 0) {
										sum += scores[pos1][pos2][i][j];
									}
								}
							}
								
						}
						//sopl("Num branches2: " + numBranches);
					}
					sopl("sum13: " + sum);
					sopl("sum wins p1: " + numWinsPlayer1);
					sopl("sum wins p2: " + numWinsPlayer2);
					sopl("Num branches13: " + numBranches);
					sopl();
					
					
				
					//TODO: remove winning scores
					
					for(int pos1=0; pos1<10; pos1++) {
						for(int pos2=0; pos2<10; pos2++) {
							
							for(int i=0; i<WIN; i++) {
								for(int j=WIN; j<scores[0][0][0].length; j++) {
									
									//Roll1:
									numWinsPlayer2 += scores[pos1][pos2][i][j];
									scores[pos1][pos2][i][j] = 0;
								}
								
								
							}
							for(int i=WIN; i<scores[0][0].length; i++) {
								for(int j=WIN; j<scores[0][0][0].length; j++) {
									
									if(scores[pos1][pos2][i][j] != 0) {
										sopl("oops2!");
										exit(1);
									}
								}
							}
						}
						//sopl("Num branches2: " + numBranches);
					}
					

					sum = 0;
					for(int pos1=0; pos1<10; pos1++) {
						for(int pos2=0; pos2<10; pos2++) {
							
							for(int i=0; i<50; i++) {
								for(int j=0; j<50; j++) {
									if(scores[pos1][pos2][i][j] > 0) {
										sum += scores[pos1][pos2][i][j];
									}
								}
							}
								
						}
						//sopl("Num branches2: " + numBranches);
					}
					sopl("sum14: " + sum);
					sopl("sum wins p1: " + numWinsPlayer1);
					sopl("sum wins p2: " + numWinsPlayer2);
					sopl("Num branches14: " + numBranches);
					sopl();
					
					sopl();
					sopl();
				}
				
			int sum = 0;
			for(int pos1=0; pos1<10; pos1++) {
				for(int pos2=0; pos2<10; pos2++) {
					
					for(int i=0; i<50; i++) {
						for(int j=0; j<50; j++) {
							if(scores[pos1][pos2][i][j] > 0) {
								sopl("What?");
								sum += scores[pos1][pos2][i][j];
							}
						}
					}
						
				}
				//sopl("Num branches2: " + numBranches);
			}
				
			sopl(numWinsPlayer1);
			sopl(numWinsPlayer2);
			
			sopl("Answer: " + Math.max(numWinsPlayer1, numWinsPlayer2));
			
			sopl("Num branches: " + numBranches);
			in.close();
			
			//6923
			
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
