package probs2015;

import java.io.File;
import java.util.Scanner;

public class prob21 {

	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2015/prob2015in21.txt"));
			
			int count = 0;
			boolean part2 = true;
			
			int bossHitPoint = 0;
			int bossDamage = 0;
			int bossArmor = 0;
			
			while(in.hasNextLine()) {
				bossHitPoint= Integer.parseInt(in.nextLine().split(" ")[2]);
				bossDamage= Integer.parseInt(in.nextLine().split(" ")[1]);
				bossArmor= Integer.parseInt(in.nextLine().split(" ")[1]);
			}
			
			prob21Equip weapon[] = new prob21Equip[5];
			
			weapon[0] = new prob21Equip(8, 4, 0);
			weapon[1] = new prob21Equip(10, 5, 0);
			weapon[2] = new prob21Equip(25, 6, 0);
			weapon[3] = new prob21Equip(40, 7, 0);
			weapon[4] = new prob21Equip(74, 8, 0);
			
			prob21Equip armor[] = new prob21Equip[5];
			
			armor[0] = new prob21Equip(13, 0, 1);
			armor[1] = new prob21Equip(31, 0, 2);
			armor[2] = new prob21Equip(53, 0, 3);
			armor[3] = new prob21Equip(75, 0, 4);
			armor[4] = new prob21Equip(102, 0, 5);

			prob21Equip ring[] = new prob21Equip[6];
			
			ring[0] = new prob21Equip(25, 1, 0);
			ring[1] = new prob21Equip(50, 2, 0);
			ring[2] = new prob21Equip(100, 3, 0);
			ring[3] = new prob21Equip(20, 0, 1);
			ring[4] = new prob21Equip(40, 0, 2);
			ring[5] = new prob21Equip(80, 0, 3);
					
			
			int playerHitPoints = 100;
			int playerDamage;
			int playerArmor;
			
			int amountOfGold;
			
			int minAmountOfGold = Integer.MAX_VALUE;
			int maxAmountOfGold = 0;
			
			boolean combo[];
			
			for(int i=0; i<weapon.length; i++) {
				playerDamage = 0;
				playerArmor = 0;
				amountOfGold = 0;
				
				//equip weapon.
				playerDamage += weapon[i].getDamage();
				playerArmor += weapon[i].getArmor();
				amountOfGold += weapon[i].getCost();
				
				
				for(int j=0; j<armor.length + 1; j++) {
					//equip armor
					if(j < armor.length) {
						
						playerDamage += armor[j].getDamage();
						playerArmor += armor[j].getArmor();
						amountOfGold += armor[j].getCost();
					} else {
						//no armor
					}
					
					//lazy way of going though all combinations of 0-2 rings:
					combo = new boolean[ring.length + 2];
					combo[0] = true;
					combo[1] = true;
					//equip ring(s)
					while(combo != null) {
						
						for(int k=0; k<ring.length; k++) {
							if(combo[k]) {
								playerDamage += ring[k].getDamage();
								playerArmor += ring[k].getArmor();
								amountOfGold += ring[k].getCost();
							}
							
						}
						
						if(part2 == false) {
							//fight!
							if( fightWon(bossHitPoint, bossDamage, bossArmor, playerHitPoints, playerDamage, playerArmor) ){
								if(minAmountOfGold > amountOfGold) {
									minAmountOfGold = amountOfGold;
								}
							}
						} else {
							if( fightWon(bossHitPoint, bossDamage, bossArmor, playerHitPoints, playerDamage, playerArmor) == false ){
								if(maxAmountOfGold < amountOfGold) {
									maxAmountOfGold = amountOfGold;
								}
							}
						}
						for(int k=0; k<ring.length; k++) {
							if(combo[k]) {
								playerDamage -= ring[k].getDamage();
								playerArmor -= ring[k].getArmor();
								amountOfGold -= ring[k].getCost();
							}
						}
						
						combo = utilsPE.Combination.getNextCombination(combo);
					}
					
					if(j < armor.length) {
						playerDamage -= armor[j].getDamage();
						playerArmor -= armor[j].getArmor();
						amountOfGold -= armor[j].getCost();
					} else {
						//no armor
					}
				}
			}
			if(part2 == false) {
				System.out.println("Answer: " + minAmountOfGold);
			} else {
				System.out.println("Answer: " + maxAmountOfGold);
			}
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	

	public static boolean fightWon(int bossHitPoint, int bossDamage, int bossArmor, int playerHitPoints, int playerDamage, int playerArmor) {
		System.out.println("BOSS");
		System.out.println(bossHitPoint);
		System.out.println(bossDamage);
		System.out.println(bossArmor);
		
		System.out.println("YOU");
		System.out.println(playerHitPoints);
		System.out.println(playerDamage);
		System.out.println(playerArmor);
		
		while(playerHitPoints > 0) {
			
			bossHitPoint -= Math.max(1, playerDamage - bossArmor);
			
			if(bossHitPoint <= 0) {
				System.out.println("WIN!");
				return true;
			}
			
			playerHitPoints -=  Math.max(1, bossDamage - playerArmor);
			
		}

		System.out.println("LOST!");
		return false;
	}
	
	
}
