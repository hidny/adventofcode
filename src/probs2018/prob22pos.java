package probs2018;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import aStar.AstarNode;

public class prob22pos implements aStar.AstarNode {

	public int coordX;
	public int coordY;
	
	public static long puzzleInput = 0;
	
	public static char map[][];
	
	public static void main(String args[]) {
		
	}
	
	
	
	public boolean torch= false;
	public boolean climbingGear = false;
	
	public prob22pos() {
		this.coordX = 0;
		this.coordY = 0;
		this.torch = true;
		this.climbingGear = false;
	}
	public prob22pos(int coordX, int coordY, boolean torch, boolean climbingGear) {
		this.coordX = coordX;
		this.coordY = coordY;
		this.torch = torch;
		this.climbingGear = climbingGear;
		
		if(this.torch && this.climbingGear) {
			System.out.println("ERROR: both equipped");
			System.exit(1);
		}
	}
	
	@Override
	public long getAdmissibleHeuristic(aStar.AstarNode goal) {
		prob22pos goalPos = (prob22pos)goal;
		return Math.abs(goalPos.coordX - coordX) + Math.abs(goalPos.coordY - coordY);
	}

	
	public boolean viableMove(int coordX, int coordY) {
		
		if(coordX <0 || coordY < 0) {
			return false;
		}
		
		int type = prob22part2.type(coordY, coordX);
		
	
		if(type == prob22part2.ROCKY ) {
			if(this.climbingGear || this.torch) {
				return true;
			}
		} else if(type == prob22part2.WET) {
			if(this.climbingGear || this.torch == false) {
				return true;
			}
		} else if(type == prob22part2.NARROW) {
			if(this.torch || this.climbingGear == false) {
				return true;
			}
		}
		
		return false;
		
	}
	
	
	/*
    public static int ROCKY =0;
	public static int NARROW = 1;
	public static int WET = 2;
	 */
	@Override
	public ArrayList<AstarNode> getNodeNeighbours() {
		ArrayList<AstarNode> ret = new ArrayList<AstarNode>();

		int type = prob22part2.type(coordY, coordX);
		
		if(type == prob22part2.ROCKY && this.climbingGear == false && this.torch == false){
			System.out.println("AH1!");
			System.exit(1);
		}
		
		if(type == prob22part2.WET && this.torch == true){
			System.out.println("AH2!");
			System.exit(1);
		}

		if(type == prob22part2.NARROW && this.climbingGear == true){
			System.out.println("AH3!");
			System.exit(1);
		}
		
		//Order it for prob 15:
		if(viableMove(coordX, coordY -1 )) {
			
			ret.add(new prob22pos(coordX, coordY-1, this.torch, this.climbingGear));
		}
		if(viableMove(coordX-1, coordY )) {
			ret.add(new prob22pos(coordX-1, coordY, this.torch, this.climbingGear));
			
		}
		if(viableMove(coordX+1, coordY )) {
			ret.add(new prob22pos(coordX+1, coordY, this.torch, this.climbingGear));
			
		}

		if(viableMove(coordX, coordY+1 )) {
			ret.add(new prob22pos(coordX, coordY+1, this.torch, this.climbingGear));
		}
		
		
		
		
		
		//switch:
		//torch:
		if(this.torch == false &&  (type == prob22part2.ROCKY || type == prob22part2.NARROW)) {
			
			ret.add(new prob22pos(coordX, coordY, true, false));
		}
		
		//climbingGear
		if(this.climbingGear == false &&  (type == prob22part2.ROCKY || type == prob22part2.WET)) {
			ret.add(new prob22pos(coordX, coordY, false, true));
		}
		
		//neither
		if((this.torch == true || this.climbingGear == true) &&  (type == prob22part2.WET || type == prob22part2.NARROW)) {
			ret.add(new prob22pos(coordX, coordY, false, false));
		}
		
		return ret;
	}

	@Override
	public long getCostOfMove(AstarNode nextPos) {
		if(((prob22pos)nextPos).coordY != this.coordY || ((prob22pos)nextPos).coordX != this.coordX) {
			return 1;
		} else {
			//Change + move
			return 7;
		}
	}
	
	
	
	public static char friendsMap[][];
	
	
	//AHA:
	//This helps. I have to override equals so the hash will use hashCode... :(
	public boolean  equals (Object object) {
		if(this.toString().equals(object.toString())) {
			return true;
		} else {
			return false;
		}
	}
	
	public int hashCode() {
		int temp = 0;
		//after submit: I got away with this double touch no climbing boo boo loll
		if(this.torch) {
			temp += (int)Math.pow(2, 29);
			
		}
		
		if(this.torch) {
			temp += (int)Math.pow(2, 30);
			
		}
		if(this.torch && this.climbingGear) {
			System.out.println("both oops");
			System.exit(1);
		}
		
		
		return temp + this.coordX * ((int)Math.pow(2, 16)) + this.coordY;
		
	}
	
	public String toString() {
		if(this.torch && this.climbingGear) {
			System.out.println("Both!");
			System.exit(1);
		}
		return "( " + this.coordX + ", " + this.coordY + ") torch:" + this.torch + "   gear:" + this.climbingGear;
	}
}
