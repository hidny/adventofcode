package practice.snaky_problem.AI2026;

import java.util.HashSet;

import practice.snaky_problem.AI2026.numberTableTrial;

//TODO: do actual unit tests.
public class numberTableTrialTests {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		testFindSnakeIn1Basic();
		testSnake2Basic();
		testSnakeOpponentSnake1Basic();
		
		System.out.println("All tests passed...");
	}

	

	public static void testFindSnakeIn1Basic() {
		
		numberTableTrial.adjustTreatsAfterAdd(true, 5, 5);
		numberTableTrial.adjustTreatsAfterAdd(true, 5, 6);

		numberTableTrial.adjustTreatsAfterAdd(true, 4, 6);
		numberTableTrial.adjustTreatsAfterAdd(true, 4, 7);
		
		
		HashSet<Integer> ret = numberTableTrial.findMThreatLocationCoord(true, 4);
		
		if(ret.size() != 4) {
			System.out.println("Miscounted 4 threats!");
			System.exit(1);
		}
		
		if(numberTableTrial.snakeInOne(true)) {
			System.out.println("snakeInOne False test failed!");
			System.exit(1);
			
		} else {
			System.out.println("Test no snake-in-one: pass");
		}
		
		numberTableTrial.adjustTreatsAfterAdd(true, 4, 8);
		
		if(numberTableTrial.snakeInOne(true)) {
			System.out.println("Test snake-in-one: pass");
			//pass
		} else {
			System.out.println("snakeInOne True test failed!");
			System.exit(1);
		}
		
	}
	
	public static void testSnake2Basic() {

		numberTableTrial.clear();
		//Test Mate in 2:
		
		numberTableTrial.adjustTreatsAfterAdd(true, 4, 6);
		numberTableTrial.adjustTreatsAfterAdd(true, 4, 7);
		numberTableTrial.adjustTreatsAfterAdd(true, 4, 8);
		numberTableTrial.adjustTreatsAfterAdd(true, 4, 9);
		numberTableTrial.adjustTreatsAfterAdd(true, 4, 10);
		
		if(numberTableTrial.hasSnakyIn2(true)) {
			System.out.println("test found mate in 2 passed!");
		} else {

			System.out.println("test found mate in 2 failed!");
			System.exit(1);
		}

		System.out.println("Debug");
		numberTableTrial.adjustTreatsAfterRemove(true, 4, 10);
		
		
		if(! numberTableTrial.hasSnakyIn2(true)) {
			System.out.println("test didn't find mate in 2 passed!");
			
		} else {
			System.out.println("test didn't find mate in 2 failed!");
			System.exit(1);
		}
		numberTableTrial.clear();
		
	}
	
	public static void testSnakeOpponentSnake1Basic() {
		numberTableTrial.clear();
		//Test Mate in 2:
		
		numberTableTrial.adjustTreatsAfterAdd(false, 4, 6);
		numberTableTrial.adjustTreatsAfterAdd(false, 4, 7);
		numberTableTrial.adjustTreatsAfterAdd(false, 4, 8);
		numberTableTrial.adjustTreatsAfterAdd(false, 4, 9);
		numberTableTrial.adjustTreatsAfterAdd(false, 4, 10);
		numberTableTrial.adjustTreatsAfterAdd(false, 5, 10);
		
		if(numberTableTrial.opponentHasSnakeIn1(true)) {
			System.out.println("test oppenent mate 1 passed!");
		} else {

			System.out.println("test oppenent mate 1 failed!");
			System.exit(1);
		}

		System.out.println("Debug");
		numberTableTrial.adjustTreatsAfterRemove(false, 5, 10);
		numberTableTrial.adjustTreatsAfterAdd(false, 5, 9);
		
		
		if(! numberTableTrial.hasSnakyIn2(true)) {
			System.out.println("test didn't find opponent mate in 1 passed!");
			
		} else {
			System.out.println("test didn't find opponent mate in 1 failed!");
			System.exit(1);
		}
		numberTableTrial.clear();
		
		
		numberTableTrial.adjustTreatsAfterAdd(false, 4, 6);
		numberTableTrial.adjustTreatsAfterAdd(false, 4, 7);
		numberTableTrial.adjustTreatsAfterAdd(false, 4, 8);
		numberTableTrial.adjustTreatsAfterAdd(false, 4, 9);
		numberTableTrial.adjustTreatsAfterAdd(false, 4, 10);
		
		if(! numberTableTrial.hasSnakyIn2(true)) {
			System.out.println("test didn't find opponent mate in 1 passed! (2)");
			
		} else {
			System.out.println("test didn't find opponent mate in 1 failed! (2)");
			System.exit(1);
		}
		numberTableTrial.clear();
		
	}
	
}
