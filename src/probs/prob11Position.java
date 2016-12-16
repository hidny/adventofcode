package probs;

import java.util.ArrayList;

import aStar.AstarAlgo;
import aStar.AstarNode;

public class prob11Position implements aStar.AstarNode {

	private int elevatorFloor;
	
	private ArrayList<String> rtgName = new ArrayList<String>();
	private ArrayList<Integer> rtgGeneratorFloor = new ArrayList<Integer>();
	private ArrayList<Integer> rtgMicrochipFloor = new ArrayList<Integer>();
	
	public static int NUMBER_OF_FLOORS = 4;
	
	public prob11Position() {
		elevatorFloor = 1;
	}
	
	public void AddGenerator(String generatorRTGName, int floor) {
		String desc = getTwoLetterDesc(generatorRTGName);
		if(containsName(desc) == false) {
			rtgName.add(desc);
			rtgGeneratorFloor.add(-1);
			rtgMicrochipFloor.add(-1);
			sortNames();
		}
		addGenerator(desc, floor);
	}

	
	public void AddMicrochip(String microchipRTGName, int floor) {
		String desc = getTwoLetterDesc(microchipRTGName);
		if(containsName(desc) == false) {
			rtgName.add(desc);
			rtgGeneratorFloor.add(-1);
			rtgMicrochipFloor.add(-1);
			sortNames();
		}
		
		addMicrochip(desc, floor);
	}
	
	private String getTwoLetterDesc(String microchipRTGName) {
		return microchipRTGName.substring(0, 2).toUpperCase();
	}
	
	private boolean containsName(String shortRTGName) {
		for(int i=0; i<rtgName.size(); i++) {
			if(shortRTGName.equals(rtgName.get(i))) {
				return true;
			}
		}
		return false;
	}
	

	private void addMicrochip(String shortRTGName, int floor) {
		for(int i=0; i<rtgName.size(); i++) {
			if(shortRTGName.equals(rtgName.get(i))) {
				rtgMicrochipFloor.set(i, floor);
			}
		}
	}
	
	private void addGenerator(String shortRTGName, int floor) {
		for(int i=0; i<rtgName.size(); i++) {
			if(shortRTGName.equals(rtgName.get(i))) {
				rtgGeneratorFloor.set(i, floor);
			}
		}
	}
	
	
	private void sortNames() {
		for(int i=0; i<rtgName.size(); i++) {
			for(int j=i+1; j<rtgName.size(); j++) {
				if(rtgName.get(i).compareTo(rtgName.get(j)) < 0) {
					swapPositions(i, j);
				}
			}
		}
	}
	
	private void swapPositions(int a, int b) {
		String temp = rtgName.get(a);
		rtgName.set(a, rtgName.get(b));
		rtgName.set(b, temp);
		
		int temp2 = rtgGeneratorFloor.get(a);
		rtgGeneratorFloor.set(a, rtgGeneratorFloor.get(b));
		rtgGeneratorFloor.set(b, temp2);
		
		 temp2 = rtgMicrochipFloor.get(a);
		 rtgMicrochipFloor.set(a, rtgMicrochipFloor.get(b));
		 rtgMicrochipFloor.set(b, temp2);
	}
	
	public prob11Position hardCopy() {
		prob11Position ret = new prob11Position();
		ret.elevatorFloor = this.elevatorFloor;
		for(int i=0; i<this.rtgName.size(); i++) {
			ret.rtgName.add(          this.rtgName.get(i));
			ret.rtgGeneratorFloor.add(this.rtgGeneratorFloor.get(i));
			ret.rtgMicrochipFloor.add(this.rtgMicrochipFloor.get(i));
		}
		return ret;
	}
	
	public boolean  equals (Object object) {
		if(this.toString().equals(object.toString())) {
			return true;
		} else {
			return false;
		}
	}
	
	public int hashCodeBad() {
		int ret = elevatorFloor;
		
		for(int i=0; i<rtgName.size(); i++) {
			ret = (int)(NUMBER_OF_FLOORS*ret +rtgGeneratorFloor.get(i));
			ret = (int)(NUMBER_OF_FLOORS*ret +rtgMicrochipFloor.get(i));
		}
		
		return ret;
	}
	//Note: I already take care of this in get neighbours...

	
	
	private int hashCode = -1;
	//This hash code takes advantage of symmetry to reduce the search space
	public int hashCode() {
		if(hashCode != -1) {
			return hashCode;
		} else{
			ArrayList<Integer> numbers = new ArrayList<Integer>();
			
			int temp;
			
			for(int i=0; i<rtgName.size(); i++) {	
				temp = NUMBER_OF_FLOORS*rtgGeneratorFloor.get(i) +rtgMicrochipFloor.get(i);
				numbers.add(temp);
			}
			
			
			for(int i=0; i<numbers.size(); i++) {
				for(int j=i+1; j<numbers.size(); j++) {
					if(numbers.get(i) < numbers.get(j)) {
						temp = numbers.get(i);
						numbers.set(i, numbers.get(j));
						numbers.set(j, temp);
					}
				}
			}
			int ret = 0;
			ret = elevatorFloor;
			
			for(int i=0; i<numbers.size(); i++) {
				ret = (int)(NUMBER_OF_FLOORS*NUMBER_OF_FLOORS*ret + numbers.get(i));
			}
			
			hashCode = ret;

			return ret;
		}
	}
	
	public String toString() {
		String ret = "\n";
		for(int floor = NUMBER_OF_FLOORS; floor>0; floor--) {
			ret += "F" + floor + " ";
			if(floor == elevatorFloor) {
				ret += "E  ";
			} else {
				ret += ".  ";
			}
			for(int i=0; i<rtgName.size(); i++) {
				if(rtgGeneratorFloor.get(i) == floor) {
					ret += rtgName.get(i) + "G ";
				} else {
					ret += ".   ";
				}
				
				if(rtgMicrochipFloor.get(i) == floor) {
					ret += rtgName.get(i) + "M ";
				} else {
					ret += ".   ";
				}
			}
			
			ret += "\n";
		}
		
		return ret += "\n";
	}
	
	
	
	@Override
	public long getAdmissibleHeuristic() {
		int ret = 0;
		long lowestFloorObj = NUMBER_OF_FLOORS;
		long secondLowestFloorObj = NUMBER_OF_FLOORS;
		for(int i=0; i<rtgName.size(); i++) {
			ret += 2*(NUMBER_OF_FLOORS-rtgGeneratorFloor.get(i));
			ret += 2*(NUMBER_OF_FLOORS-rtgMicrochipFloor.get(i));
			
			if(rtgGeneratorFloor.get(i) < lowestFloorObj) {
				lowestFloorObj = (long)(rtgGeneratorFloor.get(i));
			} else if(rtgGeneratorFloor.get(i) < secondLowestFloorObj) {
				secondLowestFloorObj = (long)(rtgGeneratorFloor.get(i));
			}
			
			if(rtgMicrochipFloor.get(i) < lowestFloorObj) {
				lowestFloorObj = (long)(rtgMicrochipFloor.get(i));
			} else if(rtgMicrochipFloor.get(i) < secondLowestFloorObj) {
				secondLowestFloorObj = (long)(rtgMicrochipFloor.get(i));
			}
			
		}

		ret -= 2* (NUMBER_OF_FLOORS-secondLowestFloorObj);
		ret -= 1* (NUMBER_OF_FLOORS-lowestFloorObj);
		
		ret += 2*(this.elevatorFloor - secondLowestFloorObj);
		ret += this.elevatorFloor - lowestFloorObj;
		

		//If there's 3 generators alone in a floor above all microchips, that's not good and will cost at least 
		//1 extra move to clear out
		//This bit of logic makes the code find a solution 10x faster.
		//The only thing is that I didn't prove it was true... :(
		int numGen;
		int numMicro;
		int totalMicroFound = 0;
		
		for(int floor=NUMBER_OF_FLOORS; floor>lowestFloorObj; floor--) {
			numGen = 0;
			numMicro = 0;
			for(int i=0; i<rtgName.size(); i++) {
				if(rtgGeneratorFloor.get(i) == floor) {
					numGen++;
				}
				
				if(rtgMicrochipFloor.get(i) == floor) {
					numMicro++;
					totalMicroFound++;
				}
			}
		
			if(numGen < numMicro) {
				break;
			} else if(numGen >= 3 && numMicro == 0) {
				if(totalMicroFound < rtgName.size()) {
					ret++;
					break;
				}
			}
		}
		
		return ret;
	}

	@Override
	public ArrayList<AstarNode> getNodeNeighbours() {
		return getNodeNeighboursOrbitals(true);
	}
	public ArrayList<AstarNode> getNodeNeighboursOrbitals(boolean returnOrbitals) {
		
	
		ArrayList<AstarNode> realNeighbours = new ArrayList<AstarNode>();
		
		prob11Position temp;
		
		int numOfObjects = numberOfObjectsOnCurrentFloor();
		
		int newFloor;
		
		int index1;
		int index2;
		
		for(int dir = 0; dir<2; dir++) {
			//Get floor:
			//up
			if(dir == 1 && elevatorFloor < NUMBER_OF_FLOORS) {
				newFloor = elevatorFloor + 1;
			//down
			} else if(dir == 0 && elevatorFloor > 1) {
				newFloor = elevatorFloor -1;
				
			} else {
				continue;
			}
			
			//0 objects:
			//temp = this.hardCopy();
			//temp.elevatorFloor = newFloor;
			//ret.add(temp);
			
			//1 object:
			for(int i=0; i<numOfObjects; i++) {
				temp = this.hardCopy();
				temp.elevatorFloor = newFloor;
				index1 = getIndexOFIthObjectOnFloor(i);
				if(index1 % 2 == 0) {
					temp.rtgGeneratorFloor.set(index1/2, newFloor);
				} else {
					temp.rtgMicrochipFloor.set(index1/2, newFloor);
				}
				if(temp.positionWontDestroyMicrochip(temp)) {
					realNeighbours.add(temp);
				}
			}
			
			//2 objects:
			for(int i=0; i<numOfObjects; i++) {
				for(int j=i+1; j<numOfObjects; j++) {
					temp = this.hardCopy();
					temp.elevatorFloor = newFloor;
					index1 = getIndexOFIthObjectOnFloor(i);
					index2 = getIndexOFIthObjectOnFloor(j);
					
					if(index1 % 2 == 0) {
						temp.rtgGeneratorFloor.set(index1/2, newFloor);
					} else {
						temp.rtgMicrochipFloor.set(index1/2, newFloor);
					}
					
					if(index2 % 2 == 0) {
						temp.rtgGeneratorFloor.set(index2/2, newFloor);
					} else {
						temp.rtgMicrochipFloor.set(index2/2, newFloor);
					}
					

					if(temp.positionWontDestroyMicrochip(temp)) {
						realNeighbours.add(temp);
					}
				}
				
				
			}
		}
		
		
		if(returnOrbitals) {
			ArrayList<AstarNode> symmetricNeighbours = new ArrayList<AstarNode>();
			for(int i=0; i<realNeighbours.size(); i++) {
				symmetricNeighbours.add(((prob11Position)realNeighbours.get(i)).getSymmeticPos());
			}
	
			for(int i=0; i<symmetricNeighbours.size(); i++) {
				for(int j=i+1; j<symmetricNeighbours.size(); j++) {
					if(symmetricNeighbours.get(i).hashCode() == symmetricNeighbours.get(j).hashCode()) {
						symmetricNeighbours.remove(j);
						j--;
					}
				}
			}
			
			return symmetricNeighbours;
		} else {
			return realNeighbours;
		}
	}
	
	private AstarNode getSymmeticPos() {
		prob11Position ret = this.hardCopy();
		for(int i=0; i<ret.rtgName.size(); i++) {
			for(int j=i+1; j<ret.rtgName.size(); j++) {
				if(ret.rtgGeneratorFloor.get(i) * NUMBER_OF_FLOORS + ret.rtgMicrochipFloor.get(i)  <  ret.rtgGeneratorFloor.get(j) * NUMBER_OF_FLOORS + ret.rtgMicrochipFloor.get(j)) {
					ret.swapPositions(i, j);
				}
			}
		}
		
		return ret;
	}
	
	private boolean positionWontDestroyMicrochip(prob11Position temp) {
		for(int i=0; i<rtgGeneratorFloor.size(); i++) {
			//Check if the microchip is protecting the generator:
			if(rtgGeneratorFloor.get(i) != rtgMicrochipFloor.get(i)) {
				//If the microchip isn't protecting, check if any other microchip is frying the generator:
				for(int j=0; j<rtgMicrochipFloor.size(); j++) {
					if(rtgGeneratorFloor.get(i) == rtgMicrochipFloor.get(j)) {
						//microchip fried!
						return false;
					}
				}
			}
		}
		
		return true;
	}
	
	private int getIndexOFIthObjectOnFloor(int targetIndex) {
		int index = -1;
		for(int i=0; i<rtgName.size(); i++) {
			if(rtgGeneratorFloor.get(i) == elevatorFloor) {
				index++;
				if(index == targetIndex) {
					return 2*i;
				}
			}
			
			if(rtgMicrochipFloor.get(i) == elevatorFloor) {
				index++;
				if(index == targetIndex) {
					return 2*i + 1;
				}
			}
		}
		
		System.out.println("ERROR: could not find ith object!");
		System.exit(1);
		return -1;
	}
	
	private int numberOfObjectsOnCurrentFloor() {
		int ret = 0;
		for(int i=0; i<rtgName.size(); i++) {
			if(rtgGeneratorFloor.get(i) == elevatorFloor) {
				ret++;
			}
			
			if(rtgMicrochipFloor.get(i) == elevatorFloor) {
				ret++;
			}
		}
		
		return ret;
	}

	@Override
	public long getCostOfMove(AstarNode nextPos) {
		ArrayList<AstarNode> listOfMoves = this.getNodeNeighbours();
		boolean foundNeighbour = false;
		for(int i=0; i<listOfMoves.size(); i++) {
			if(nextPos.equals(listOfMoves.get(i))) {
				foundNeighbour = true;
				break;
			}
		}
		if(foundNeighbour == false) {
			System.out.println("ERROR: could not find neighbour!");
			System.exit(1);
		}
		
		return 1;
	}
	
	
	//Note: if goals are too numerous, for some other Astar app,
	//I'll have to implement isGoal()
	//Not this time though... :)
	public prob11Position getGoal() {
		prob11Position ret = this.hardCopy();
		
		ret.elevatorFloor = NUMBER_OF_FLOORS;
		for(int i=0; i<ret.rtgGeneratorFloor.size(); i++) {
			ret.rtgGeneratorFloor.set(i, NUMBER_OF_FLOORS);
			ret.rtgMicrochipFloor.set(i, NUMBER_OF_FLOORS);
			
		}
		
		return ret;
	}
	
	
	public static void printRealPathFromSymmetricPath(prob11Position originalPos, ArrayList<AstarNode> pathWithSymmetry) {
		
		 prob11Position currentRealPos = originalPos;
		 ArrayList<AstarNode> candidateRealPos;
		 
		 System.out.println(("Path:"));
		 for(int i=0; i<pathWithSymmetry.size(); i++) {

			System.out.println("----------");
			System.out.println("Estimate: " + pathWithSymmetry.get(i).getAdmissibleHeuristic());
			if(i==0) {
				 System.out.println(currentRealPos);
				
			} else {
				candidateRealPos = (currentRealPos.getNodeNeighboursOrbitals(false));
				for(int j=0; j<candidateRealPos.size(); j++) {
					if(candidateRealPos.get(j).hashCode() == pathWithSymmetry.get(i).hashCode()) {
						currentRealPos = (prob11Position)candidateRealPos.get(j);
						System.out.println(currentRealPos);
						break;
					}
				}
				
			}
		 }
	}

}
