package probs2018after1am;

import java.io.File;

import number.IsNumber;
import probs2016.prob24pos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import aStar.AstarAlgo;
import aStar.AstarNode;
import utils.Mapping;

public class prob15 {

	
	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2018/prob2018in15.txt"));
			
		
			String line = "";

			ArrayList <String>lines = new ArrayList<String>();
			
			
			//dir: 0 up
			//1 right
			//2 down
			//3 left
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				
			}
			
			char map[][] = new char[lines.size()][lines.get(0).length()];
			 
			ArrayList<prob15guy> guys = new ArrayList<prob15guy>();
			
			 for(int i=0; i<map.length; i++) {
				 for(int j=0; j<map[0].length; j++) {
					 map[i][j] = lines.get(i).charAt(j);
					 if(map[i][j] == 'E') {
						 guys.add(new prob15guy(i, j, true));
						 sopl("e");
					 } else if(map[i][j] == 'G') {
						 guys.add(new prob15guy(i, j, false));
					 }
				 }
			 }
			 
			 prob15pos.setPuzzleInput(map);
			 prob15pos start;
			 prob15pos goal;
			
			
			 int roundNumber = 0;
			 
			 WAR:
			 while(prob15guy.warOver(guys) == false) {
				 
				 sopl(roundNumber);
			 

				
				 
				 for(int i=0; i<map.length; i++) {
					 
					 ArrayList<Integer> hps = new  ArrayList<Integer>();
					 
					 for(int j=0; j<map[0].length; j++) {
						 map[i][j] = lines.get(i).charAt(j);
						 
						 if(map[i][j] == '#') {
							 System.out.print("#");
						 } else  if(prob15guy.hasEnemy(guys, j, i, true)) {
							 hps.add(prob15guy.enemyHp(guys, j, i, true));
							 
							 System.out.print("E");
							 
						 } else if(prob15guy.hasEnemy(guys, j, i, false)) {
							 hps.add(prob15guy.enemyHp(guys, j, i, false));
							 
							 System.out.print("G");
						 } else {
							 System.out.print(" ");
						 }

					 }
					 System.out.print("    ");
					 for(int k=0; k<hps.size(); k++) {
						 System.out.print(hps.get(k) + "  ");
					 }
					 
					 sopl("");
				 }
				 sopl("");
				 sopl("");
				 

				 //Find every guy in "reading order"
				 for(int i=0; i<map.length; i++) {
					 for(int j=0; j<map[0].length; j++) {
						for(int k=0; k<guys.size(); k++) {
							
							if(prob15guy.warOver(guys)) {
								break WAR;
							}
							
							if(guys.get(k).i == i && guys.get(k).j == j && guys.get(k).isDead == false && guys.get(k).isMoved == false) {
								
								//MOVE
								start = new prob15pos(j, i);
								
								int coord[] = prob15guy.getNextPos(start, guys, !guys.get(k).elf);
								
								//return x,y
								guys.get(k).j = coord[0];
								guys.get(k).i = coord[1];
								
								guys.get(k).isMoved = true;
								
								//Done move
								
								//ATTACK
								
								start = new prob15pos(guys.get(k).j, guys.get(k).i);
								
								ArrayList<AstarNode> considerations = start.getNodeNeighbours();
								
								int minHp = 10000;
								int currentHp;
								
								for(int m=0; m<considerations.size(); m++) {
									if(prob15guy.hasEnemy(guys, ((prob15pos)considerations.get(m)).coordX, ((prob15pos)considerations.get(m)).coordY, !guys.get(k).elf)) {
										currentHp = prob15guy.enemyHp(guys, ((prob15pos)considerations.get(m)).coordX, ((prob15pos)considerations.get(m)).coordY, !guys.get(k).elf);
										
										if(currentHp < minHp) {
											minHp = currentHp;
										}
									}
								}
								
								//priotize attacking in the order up, left, right ,down as a tie-breaker if the hps are the same:
								for(int m=0; m<considerations.size(); m++) {
									if(prob15guy.hasEnemy(guys, ((prob15pos)considerations.get(m)).coordX, ((prob15pos)considerations.get(m)).coordY, !guys.get(k).elf)) {
										
										currentHp = prob15guy.enemyHp(guys, ((prob15pos)considerations.get(m)).coordX, ((prob15pos)considerations.get(m)).coordY, !guys.get(k).elf);
										
										if(currentHp == minHp && minHp < 1000) {
											//Attack
											prob15guy.attack(guys, ((prob15pos)considerations.get(m)).coordX, ((prob15pos)considerations.get(m)).coordY);
											//Make sure only 1 attack happens in 1 turn
											break;
											
										}
									}
								}
								
								//done attack
								
							}
						}
					 }
				 }
				 
				 
				 
				 
				 
				 //This sets all guys as unmoved in the next round:
				 prob15guy.resetRound(guys);
				 
				 roundNumber++;
			 }
			 
			 sopl("FINAL");
			 for(int i=0; i<map.length; i++) {
				 
				 ArrayList<Integer> hps = new  ArrayList<Integer>();
				 
				 for(int j=0; j<map[0].length; j++) {
					 map[i][j] = lines.get(i).charAt(j);
					 
					 if(map[i][j] == '#') {
						 System.out.print("#");
					 } else  if(prob15guy.hasEnemy(guys, j, i, true)) {
						 hps.add(prob15guy.enemyHp(guys, j, i, true));
						 
						 System.out.print("E");
						 
					 } else if(prob15guy.hasEnemy(guys, j, i, false)) {
						 hps.add(prob15guy.enemyHp(guys, j, i, false));
						 
						 System.out.print("G");
					 } else {
						 System.out.print(" ");
					 }

				 }
				 System.out.print("    ");
				 for(int k=0; k<hps.size(); k++) {
					 System.out.print(hps.get(k) + "  ");
				 }
				 
				 sopl("");
			 }
			 sopl("");
			 sopl("");
			 
			 
			 sopl("num rounds: " + roundNumber);
			 
			 int hpLeft = prob15guy.getHitPointsLeft(guys);
			 
			 sopl("Wins with " + hpLeft + " hit points left");
			 
			 
			 sopl("Outcome: " + (roundNumber * hpLeft));
			
			
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
	
	
	//TODO: put state print in a helper method.
}
