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

public class prob24part2 {

	
	public static void main(String[] args) {
		Scanner in;
		
		Scanner input = new Scanner(System.in);
		
		try {
			 in = new Scanner(new File("in2018/prob2018in24.victor.txt"));
			
			int count = 0;
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
			
			ArrayList<prob24Side> sides = new ArrayList<prob24Side>();
			
			
			boolean newGroup = true;
			for(int i=0; i<lines.size(); i++) {
				line = lines.get(i);
				if(line.equals("")) {
					newGroup = true;
				} else if(newGroup) {
					sides.add(new prob24Side(line));
					newGroup = false;
				} else {
					
					sides.get(sides.size() -1 ).addUnits(line);
					
				}
				
				
			}
			
			
			//exit(0);
			int boost = 0;
			
			while(true) {

				int iter = 0;
				while(sides.get(0).isAlive() && sides.get(1).isAlive() && iter < 1000000) {
					iter++;
					
					
					//sopl();
					//sopl();
	
					resetRound(sides);
					//printStatus(sides);
					
	
				//	input.next();
					//sopl();
					
					
					orderSelectionInitiative(sides);
					
					
					ArrayList<prob24Group> attackChoiceOrder = orderAttackInitiate(sides);
					
					//sopl();
					//sopl("Order of attackers:");
					for(int i=0; i<attackChoiceOrder.size(); i++) {
						
						prob24Group attacker = attackChoiceOrder.get(i);
						if(attacker.units > 0) {
							
							
							String attackerSide = attackChoiceOrder.get(i).sideName;
							
							int defenderSide = 0;
							if(sides.get(0).sideName.equals(attackerSide)) {
								defenderSide=1;
							}
							
							if(attacker.attackingIndex >= 0) {
									
								prob24Group defender = sides.get(defenderSide).groups.get(attacker.attackingIndex);
								
								if(defender.units == 0) {
									sopl("ERROR: defender already gone!");
									exit(1);
								}
							
								attacker.attack(sides, defender);
							}
						}
					}
					
					//sopl();
					//sopl();
					//sopl();
					//sopl();
					
					
				}
					
				if(sides.get(0).isAlive() && sides.get(1).isAlive() == false) {
					break;
				} else {
					sopl(boost);
					printStatus(sides);
					sopl();
					sopl();
					//immune
					for(int j=0; j<sides.get(0).groups.size(); j++) {
							sides.get(0).groups.get(j).boost1();
					}
					
					for(int i=0; i<sides.size(); i++) {
						for(int j=0; j<sides.get(i).groups.size(); j++) {
							sides.get(i).groups.get(j).restoreOrigHealth();
						}
					}
					
					//part2
					boost++;
				}
			}
			
			
			//END OF LOOP
				
			printStatus(sides);
			
			//get answer:
			count = 0;
			for(int i=0; i<sides.size(); i++) {
				for(int j=0; j<sides.get(i).groups.size(); j++) {
					if(sides.get(i).groups.get(j).units > 0) {
						count += sides.get(i).groups.get(j).units;
					}
				}
			}
			
			sopl("Answer: " + boost);
			in.close();
			
			//get answer:
			count = 0;
			for(int i=0; i<sides.size(); i++) {
				for(int j=0; j<sides.get(i).groups.size(); j++) {
					if(sides.get(i).groups.get(j).units > 0) {
						count += sides.get(i).groups.get(j).units;
					}
				}
			}
			
			sopl("Answer: " + count);
			
			//21966
			//21966
			//20819
			//20819
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public static String getName(ArrayList<prob24Side> sides, prob24Group group) {
		for(int i=0; i<sides.size(); i++) {
			if(sides.get(i).sideName.equals(group.sideName)) {
				for(int j=0; j<sides.get(i).groups.size(); j++) {
					if(sides.get(i).groups.get(j).equals(group)) {
						return group.sideName + " group " + (j+1);
					}
				}
			}
		}
		
		exit(1);
		return "";
	}
	
	public static void resetRound(ArrayList<prob24Side> sides) {
		for(int i=0; i<sides.size(); i++) {
			for(int j=0; j<sides.get(i).groups.size(); j++) {
				sides.get(i).groups.get(j).attacked = false;
				sides.get(i).groups.get(j).attackingIndex = -1;
				
			}
		}
		
	}
	
	
	public static void printStatus(ArrayList<prob24Side> sides) {
		for(int i=0; i<sides.size(); i++) {
			sopl(sides.get(i).sideName + ":");
			for(int j=0; j<sides.get(i).groups.size(); j++) {
				if(sides.get(i).groups.get(j).units > 0) {
					sopl("Group " + (j+1) + " contains " + sides.get(i).groups.get(j).units + " units");
				}
			}
			if(sides.get(i).isAlive() == false) {
				sopl("No groups remain.");
			}
		}
	}

	//Copy order selection in example:
	public static ArrayList<prob24Group> orderSelectionInitiative(ArrayList<prob24Side> sides) {

		ArrayList<prob24Group> output = new ArrayList<prob24Group>();
		
		for(int side=1; side>=0; side--) {
			for(int i=0; i<sides.get(side).groups.size(); i++) {

				prob24Group attacker = sides.get(side).groups.get(i);
			
				if(attacker.units == 0) {
					
					continue;
				}
				
				
				int indexAdd = 0;
				
				if(side==0) {
					indexAdd = sides.get(1).numberStillLiving();
				}
				
				for(;indexAdd<output.size(); indexAdd++) {
					if(output.get(indexAdd).getEffectivePower() < attacker.getEffectivePower()) {
						break;
					} else if(output.get(indexAdd).getEffectivePower() == attacker.getEffectivePower()) {
						if(output.get(indexAdd).initiative < attacker.initiative) {
							break;
						}
					}
				}
				if(indexAdd  <output.size()) {
					output.add(indexAdd, sides.get(side).groups.get(i));
				} else {
					output.add(sides.get(side).groups.get(i));
				}
			}
		}
		
		for(int i=0; i<output.size(); i++) {
				
				prob24Group attacker = output.get(i);
				int defendingSide = 0;
				
				if(sides.get(0).sideName.equals(attacker.sideName)) {
					defendingSide = 1;
				} else {
					defendingSide = 0;
				}
				
				
				
				int indexBestDamage = -1;
				//TODO: just use index...
				
				int bestDamage = 0;
				prob24Group pickedDefender = null;
				
				//Check damage 
				for(int k=0; k<sides.get(defendingSide).groups.size(); k++) {
					prob24Group defender = sides.get(defendingSide).groups.get(k);
					
					if(defender.units > 0 && defender.attacked == false) {
						
						int damage = attacker.getEffectivePower();
						if(defender.isImmumne(attacker.attackType)) {
							damage = 0;
						} else if(defender.isWeak(attacker.attackType)){
							damage *=2;
						}
						
						//TODO
						if(damage > 0) {
							if(pickedDefender == null || damage > bestDamage) {
								indexBestDamage = k;
								bestDamage = damage;
								pickedDefender = sides.get(defendingSide).groups.get(k);
								
								
							} else if(damage == bestDamage) {
								if(defender.getEffectivePower() > pickedDefender.getEffectivePower()) {
									indexBestDamage = k;
									bestDamage = damage;
									pickedDefender = defender;
								
								} else if(defender.getEffectivePower() == pickedDefender.getEffectivePower()) {
									if(defender.initiative > pickedDefender.initiative) {
										indexBestDamage = k;
										bestDamage = damage;
										pickedDefender = defender;
										
									}
								}
							}
							//sopl(getName(sides, attacker) + " would deal defending group " + (k+1) + " " + damage + " damage");
							
						}
						
					}
				}
				
				//TEST:
				//if(indexBestDamage >=0 && bestDamage ==0) {
				//	sopl(bestDamage);
					//exit(1);
			//	}
				
				attacker.attackingIndex = indexBestDamage;
				
				if(attacker.attackingIndex >=0) {
					if(bestDamage == 0) {
						sopl("ERROR: best damage 0");
						exit(1);
					}
					
					if(sides.get(defendingSide).groups.get(indexBestDamage).attacked) {
						sopl("ERROR: defender already attacked!");
						exit(1);
					}
					sides.get(defendingSide).groups.get(indexBestDamage).attacked = true;
				}
				
			}
			//sopl();
		
		//sopl();
		//sopl();
		return output;
		
	}
	
	public static ArrayList<prob24Group> orderAttackInitiate(ArrayList<prob24Side> sides) {
		ArrayList<prob24Group> output = new ArrayList<prob24Group>();
		
		for(int side=0; side<2; side++) {
			for(int i=0; i<sides.get(side).groups.size(); i++) {
				
				prob24Group current = sides.get(side).groups.get(i);
				
				if(current.units > 0) {
					int indexAdd = 0;
					for(;indexAdd<output.size(); indexAdd++) {
						if(output.get(indexAdd).initiative < current.initiative) {
							break;
						}
					}
					if(indexAdd < output.size()) {
						output.add(indexAdd, current);
					} else {
	
						output.add(current);
					}
				}
			}
		}
		
		for(int i=1; i<output.size(); i++) {
			if(output.get(i-1).initiative < output.get(i).initiative) {
				sopl("ERROR in orderAttackInitiate");
				exit(1);
			}
		}
		if(output.size() != sides.get(0).numberStillLiving() + sides.get(1).numberStillLiving()) {
			sopl("ERROR in orderAttackInitiate. bad count");
			exit(1);
		}
		return output;
		
	}
	
	
	/*
	 * During the target selection phase, each group attempts to choose one 
	 * target. In decreasing order of effective power, groups choose their 
	 * targets; in a tie, the group with the higher initiative chooses first.
	 * 
	 */
	
	

	
	public static boolean fightStillGoing(ArrayList<prob24Side> sides) {
		if(sides.get(0).isAlive() && sides.get(1).isAlive()) {		
			return true;
		} else {
			return false;
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
	
	public static void exit() {
		exit(0);
	}
	public static void exit(int code) {
		sop("Exit with code " + code);
		
		System.exit(code);
	}
}
