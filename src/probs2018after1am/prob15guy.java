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

public class prob15guy {

	public int hitPoints =200;
	public int i;
	public int j;
	
	public boolean isDead = false;
	
	public boolean elf = false;

	public boolean isMoved = false;
	
	
	public static void resetRound(ArrayList<prob15guy> guys) {
		for(int i=0; i<guys.size(); i++) {
			guys.get(i).isMoved = false;
		}
	}
	
	public prob15guy(int i, int j, boolean elf) {
		this.i = i;
		this.j = j;
		this.elf = elf;
	}
	
	//ELF_ATTACK_POWER
	public void takeHit() {
		takeHit(3);
	}
	public void takeHit(int amount) {
		this.hitPoints -= amount;
		
		if(this.hitPoints <=0) {
			this.hitPoints = 0;
			this.isDead = true;
		}
		
	}
	
	
	//return x,y
	public static int[] getNextPos(prob15pos start, ArrayList<prob15guy> guys, boolean targetElf) {
		int ret[] = new int[2];
	
		
		ArrayList<AstarNode> path;
		
		//Put the friends in the way of the A* path finder:
		prob15pos.friendsMap = new char[prob15pos.map.length][prob15pos.map[0].length];
		
		for(int k=0; k<guys.size(); k++) {
			if(guys.get(k).isDead == false && guys.get(k).elf != targetElf) {
				prob15pos.friendsMap[guys.get(k).i][guys.get(k).j] = '#';
			}
		}
		
		
		
		int bestDistance =1000;
		
		int currentDistance;
		for(int k=0; k<guys.size(); k++) {
			if(guys.get(k).isDead == false && guys.get(k).elf == targetElf) {

				prob15pos goal = new prob15pos(guys.get(k).j, guys.get(k).i);
				
				path = AstarAlgo.astar(start, goal);
				
				if(path != null) {
					currentDistance = path.size() - 1;
				} else {
					currentDistance=1000;
				}
				
				if(currentDistance < bestDistance) {
					bestDistance = currentDistance;
				}
			}
		}
		
		if(bestDistance == 0) {
			sopl("ERROR: dist 0 found");
			exit(1);
			
		} else if(bestDistance == 1) {
			//This means the guy doesn't need to move!
			return new int[] { start.coordX, start.coordY};
		} else if(bestDistance > 100) {
			
			//I'm assuming this means the guy is stuck:
			return new int[] { start.coordX, start.coordY};
		}
		
		
		
		//Prioritize moving up, left, right, or down in that order
		ArrayList<AstarNode> considerations = start.getNodeNeighbours();
		
		for(int m=0; m<considerations.size(); m++) {
			
			for(int k=0; k<guys.size(); k++) {
				if(guys.get(k).isDead == false && guys.get(k).elf == targetElf) {

					prob15pos goal = new prob15pos(guys.get(k).j, guys.get(k).i);
					
					path = AstarAlgo.astar(start, goal);
					
					if(path != null) {
						currentDistance = path.size() - 1;
					} else {
						currentDistance=1000;
					}
					
					
					if(currentDistance == bestDistance) {
						
					
						path = AstarAlgo.astar(considerations.get(m), goal);
						
						if(path != null) {
							currentDistance = path.size() - 1;
						} else {
							currentDistance=1000;
						}
						
						if(currentDistance == bestDistance - 1) {
							return new int[] { ((prob15pos)considerations.get(m)).coordX, ((prob15pos)considerations.get(m)).coordY};
							
						}
						
						
					}
					
				}

			}
		}
		
		
		sopl("ERROR: I got way too far into best dist function!");
		
		sopl("This should be logically impossible!");
		exit(1);
	
		
		return ret;
	}
	
	//TO call before every attack
	//(if you do after, you could get the wrong number of rounds counted)
	public static boolean warOver(ArrayList<prob15guy> guys) {
		//check elves:
		boolean elvesDead = true;
		boolean gobsDead = true;
		for(int i=0; i<guys.size(); i++) {
			if(guys.get(i).isDead == false && guys.get(i).elf) {
				elvesDead = false;
			}
			if(guys.get(i).isDead == false && guys.get(i).elf == false) {
				gobsDead = false;
			}
		}
		
		if(gobsDead || elvesDead) {
			return true;
		} else {
			return false;
		}
	}
	
	//Attack guy at location. Default attack is 3.
	public static void attack(ArrayList<prob15guy> guys, int coordX, int coordY) {
		attack(guys, coordX, coordY, 3);
	}
	public static void attack(ArrayList<prob15guy> guys, int coordX, int coordY, int attackPower) {
		for(int k=0; k<guys.size(); k++) {
			if(guys.get(k).isDead == false && guys.get(k).i == coordY && guys.get(k).j == coordX) {
				if(guys.get(k).elf == false) {
					guys.get(k).takeHit(attackPower);
				} else {
					guys.get(k).takeHit();
				}
			}
		}
	}
	
	//Check if location has enemy:
	public static boolean hasEnemy(ArrayList<prob15guy> guys, int coordX, int coordY, boolean targetElf) {
		for(int k=0; k<guys.size(); k++) {
			if(guys.get(k).isDead == false && guys.get(k).i == coordY && guys.get(k).j == coordX && guys.get(k).elf == targetElf) {
				return true;
			}
		}
		return false;
	}
	
	//Get num elves still alive for part 2.
	public static int getNumElves(ArrayList<prob15guy> guys) {
		int ret = 0;
		for(int k=0; k<guys.size(); k++) {
			if(guys.get(k).isDead == false && guys.get(k).elf) {
				ret++;
			}
		}
		return ret;
	}
	
	//Get enemy guy's hp at a location
	public static int enemyHp(ArrayList<prob15guy> guys, int coordX, int coordY, boolean targetElf) {
		for(int k=0; k<guys.size(); k++) {
			if(guys.get(k).isDead == false && guys.get(k).i == coordY && guys.get(k).j == coordX && guys.get(k).elf == targetElf) {
				return guys.get(k).hitPoints;
			}
		}
		sopl("enemy hp doesn\'t exist!");
		exit(1);
		return -1;
	}
	
	
	//getHitPointsLeft of all survivors to figure out the "outcome" 
	public static int getHitPointsLeft(ArrayList<prob15guy> guys) {
		int ret =0;
		for(int k=0; k<guys.size(); k++) {
			if(guys.get(k).isDead == false) {
				ret+=guys.get(k).hitPoints;
			} else {
				if(guys.get(k).hitPoints != 0) {
					sopl("weird hit points!");
					exit(1);
				}
			}
		}
		return ret;
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
}
