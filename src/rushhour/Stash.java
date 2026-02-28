package rushhour;

public class Stash {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	
	public static RushHourState easyFeb27() {

		RushHourState test = new RushHourState(6);
		
		//Features:
		//Cars are auto-labeled
		//Defaults to car length 2.
		//There's a warning there's an overlap
		
		
		test.insertCar(0, 2, true);
		test.insertCar(0, 4, true);
		test.insertCar(2, 3, true, true);
		test.insertCar(3, 0, true);

		test.insertCar(0, 1, 3, false);
		test.insertCar(2, 2, false);
		test.insertCar(1, 5, false);
		test.insertCar(3, 5, 3, false);
		
		test.goalJ = 4;
		
		
		return test;
	}
	
	
	public static RushHourState medFeb27() {

		RushHourState test = new RushHourState(6);
		
		//Features:
		//Cars are auto-labeled
		//Defaults to car length 2.
		//There's a warning there's an overlap
		
		
		test.insertCar(0, 0, 3, true);
		test.insertCar(2, 0, true, true);
		test.insertCar(3, 4, true);
		test.insertCar(4, 3, true);

		test.insertCar(1, 2, false);
		test.insertCar(1, 3, false);
		test.insertCar(3, 2, false);
		test.insertCar(0, 5, false);
		
		test.goalJ = 4;
		
		
		return test;
	}
	
	

	public static RushHourState hardFeb27() {

		RushHourState test = new RushHourState(6);
		
		//Features:
		//Cars are auto-labeled
		//Defaults to car length 2.
		//There's a warning there's an overlap
		
		
		test.insertCar(0, 0, true);
		test.insertCar(2, 0, true, true);
		test.insertCar(4, 3, true);
		test.insertCar(5, 3, true);

		test.insertCar(0, 2, false);
		test.insertCar(0, 3, 3, false);
		test.insertCar(4, 1, false);
		test.insertCar(3, 5, 3, false);
		
		test.goalJ = 4;
		
		
		return test;
	}
	
	public static RushHourState reddit() {

		RushHourState test = new RushHourState(6);
		
		//Features:
		//Cars are auto-labeled
		//Defaults to car length 2.
		//There's a warning there's an overlap
		
		
		test.insertCar(0, 1, true);
		test.insertCar(0, 3, 3, true);
		test.insertCar(1, 2, true);
		test.insertCar(2, 0, true, true);
		test.insertCar(3, 0, true);
		test.insertCar(3, 3, true);
		test.insertCar(4, 0, true);
		test.insertCar(5, 0, 3, true);

		test.insertCar(0, 0, false);
		test.insertCar(1, 4, false);
		test.insertCar(3, 2, false);
		test.insertCar(4, 3, false);
		test.insertCar(3, 5, 3, false);
		
		test.goalJ = 4;
		
		
		return test;
	}
}
