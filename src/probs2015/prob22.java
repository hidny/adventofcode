package probs2015;

import java.io.File;
import java.util.Scanner;

public class prob22 {

	public static int NUM_SPELLS = 5;
	public static int minManaSpell = 53;
	
	public static void main(String[] args) {
		Scanner in;
		try {
			in = new Scanner(new File("in2015/prob2015in22.txt"));
			
			int playerHitPoints = 50;
			int playerMana = 500;
			 
			boolean part2 = true;
			
			int bossHitPoints =0;
			int bossDamage = 0;
			
			while(in.hasNextLine()) {
				bossHitPoints = Integer.parseInt(in.nextLine().split(" ")[2]);
				bossDamage =    Integer.parseInt(in.nextLine().split(" ")[1]);
			}
			System.out.println(bossHitPoints);
			System.out.println(bossDamage);
			int minUsedInFight = Integer.MAX_VALUE;
			
			//Brute force start:
			for(int strat = 0; minUsedInFight > getMinManaWithStrat(strat); strat++) {

				if(strat == 448) {
					System.out.println("DEBUG!");
				}
				int manaUsedToWin = fight(strat, bossHitPoints, bossDamage, playerHitPoints, playerMana, part2);
				if(manaUsedToWin != -1) {
					if(manaUsedToWin < minUsedInFight) {
						minUsedInFight = manaUsedToWin;
						System.out.println("Good strat: " + strat);
						System.out.println("Possible answer: " + minUsedInFight);
					}
					
				}
				
			}
			
			
			System.out.println("Answer: " + minUsedInFight);
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public static int MAGIC_SHIELD = 7;
	
	public static int fight(long strat, int bossHitPoints, int bossDamage, int playerHitPoints, int playerMana, boolean part2) {
		int mana = 500;
		
		
		int manaUsed = 0;
		
		int shieldTimer = 0;
		int poisonTimer = 0;
		int rechargeTimer = 0;
		
		while(playerHitPoints > 0) {
			long stratNumber = strat % NUM_SPELLS;
			strat = strat/NUM_SPELLS;
			
			if(part2) {
				playerHitPoints -= 1;
				if(playerHitPoints <= 0) {
					break;
				}
			}
			
			//Pre-player turn stuff:
			if(poisonTimer > 0) {
				bossHitPoints -= 3;
			}
			if(rechargeTimer > 0) {
				mana += 101;
			}
			
			shieldTimer--;
			poisonTimer--;
			rechargeTimer--;
			
			if(bossHitPoints <= 0) {
				return manaUsed;
			}
			//End pre-player turn stuff

			//Magic Missile
			if(stratNumber == 0) {
				manaUsed+=53;
				mana-=53;
				
				bossHitPoints -= 4;
				
			//Drain
			} else if(stratNumber == 1) {
				manaUsed+=73;
				mana-=73;
				
				bossHitPoints -= 2;
				playerHitPoints += 2;
			
			//Shield
			} else if(stratNumber == 2) {
				manaUsed+=113;
				mana-=113;
				
				shieldTimer = 6;
			
			//Poison
			} else if(stratNumber == 3) {
				manaUsed+=173;
				mana-=173;
				
				poisonTimer = 6;
				
			//Recharge
			} else if(stratNumber == 4) {
				manaUsed += 229;
				mana -= 229;
				
				rechargeTimer = 5;
			}
			
			//Player tried to do a spell but out of mana
			if(mana < 0) {
				break;
			}
			
			//Pre-boss turn stuff
			if(poisonTimer > 0) {
				bossHitPoints -= 3;
			}
			
			if(rechargeTimer > 0) {
				mana += 101;
			}

			//End Pre-boss turn stuff
			
			//END player turn
			if(shieldTimer > 0) {
				playerHitPoints -= Math.max(1, bossDamage - MAGIC_SHIELD);
			} else {
				playerHitPoints -= bossDamage;
			}
			
			shieldTimer--;
			poisonTimer--;
			rechargeTimer--;
			
			//End boss turn

			if(bossHitPoints <= 0) {
				return manaUsed;
			}
		}
		
		
		return -1;
		
	}
	
	
	public static int getMinManaWithStrat(long strat) {
		
		return (int)(minManaSpell * Math.floor(Math.log(strat)/Math.log(NUM_SPELLS)));
	}
	

}
