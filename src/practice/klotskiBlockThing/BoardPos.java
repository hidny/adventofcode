package practice.klotskiBlockThing;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Hashtable;

import aStar.AstarAlgo;
import aStar.AstarNode;


public class BoardPos  implements AstarNode{


	public static int width;
	public static int height;
	
	public static void main(String[] args) {
		
		//TODO: fakeProblem2 is easy for me, but still takes long for computer...
		// investigate ways to make it go faster
		doProblem50();
	}
	
	public static void fakeProblem1() {
		BoardPos pos = new BoardPos();
		
		height = 5;
		width = 4;
		
		pos.shapes.add(new ShapePos(0, 1, ShapeCatalog.BOX, true, 3, 1));
		
		
		sopl("Here's a model of the position for fake problem 1:");
		sopl(pos.prettyPrint());
				
		//printNeighbours(pos);
		doAstar(pos);
	}
	

	public static void fakeProblem2() {
		BoardPos pos = new BoardPos();
		
		height = 5;
		width = 4;
		
		pos.shapes.add(new ShapePos(0, 1, ShapeCatalog.BOX, true, 3, 1));
		
		for(int i=0; i<2; i++) {
			for(int j=0; j<2; j++) {
				pos.shapes.add(new ShapePos(2+i, 1+j, ShapeCatalog.UNIT));
			}
		}
		sopl("Here's a model of the position for fake problem 1:");
		sopl(pos.prettyPrint());
				
		//printNeighbours(pos);
		doAstar(pos);
	}
	

	public static void fakeProblem3() {
		BoardPos pos = new BoardPos();
		
		height = 5;
		width = 4;
		
		pos.shapes.add(new ShapePos(0, 1, ShapeCatalog.BOX, true, 3, 1));
		
		for(int j=0; j<2; j++) {
			pos.shapes.add(new ShapePos(2, 1+j, ShapeCatalog.I));
		}
		sopl("Here's a model of the position for fake problem 1:");
		sopl(pos.prettyPrint());
				
		printNeighbours(pos);
		doAstar(pos);
	}
	

	public static void fakeProblem4() {
		BoardPos pos = new BoardPos();
		
		height = 5;
		width = 4;
		
		pos.shapes.add(new ShapePos(0, 1, ShapeCatalog.BOX, true, 3, 1));
		
		for(int i=0; i<1; i++) {
			for(int j=0; j<1; j++) {
				pos.shapes.add(new ShapePos(3+i, 1+j, ShapeCatalog.UNIT));
			}
		}
		sopl("Here's a model of the position for fake problem 4:");
		sopl(pos.prettyPrint());
				
		//printNeighbours(pos);
		doAstar(pos);
	}
	
	public static void doProblem1() {
	
		//All coords are i and j where i start at 0 on top left.

		BoardPos pos = new BoardPos();
		
		height = 5;
		width = 4;

		for(int i=0; i<2; i++) {
			pos.shapes.add(new ShapePos(0 + i, 0, ShapeCatalog.UNIT));
		}
		for(int i=0; i<2; i++) {
			pos.shapes.add(new ShapePos(0+i, 3, ShapeCatalog.UNIT));
		}
		for(int i=0; i<2; i++) {
			for(int j=0; j<2; j++) {
				pos.shapes.add(new ShapePos(2+i, 1+j, ShapeCatalog.UNIT));
			}
		}
		
		pos.shapes.add(new ShapePos(4, 0, ShapeCatalog.UNIT));
		pos.shapes.add(new ShapePos(4, 3, ShapeCatalog.UNIT));
		
		pos.shapes.add(new ShapePos(2, 0, ShapeCatalog.I));
		pos.shapes.add(new ShapePos(2, 3, ShapeCatalog.I));

		pos.shapes.add(new ShapePos(0, 1, ShapeCatalog.BOX, true, 3, 1));
		
		sopl("Here's a model of the position for problem 1:");
		sopl(pos.prettyPrint());
		
		printNeighbours(pos);
		doAstar(pos);
		
	}
	

	public static void doProblem2() {
	
		//All coords are i and j where i start at 0 on top left.

		BoardPos pos = new BoardPos();
		
		height = 5;
		width = 4;

		for(int i=0; i<2; i++) {
			pos.shapes.add(new ShapePos(0 + i, 0, ShapeCatalog.UNIT));
		}
		for(int i=0; i<2; i++) {
			pos.shapes.add(new ShapePos(0+i, 3, ShapeCatalog.UNIT));
		}
		for(int i=0; i<2; i++) {
			for(int j=0; j<4; j++) {
				pos.shapes.add(new ShapePos(2+i, 0+j, ShapeCatalog.UNIT));
			}
		}
		
		pos.shapes.add(new ShapePos(4, 0, ShapeCatalog.UNIT));
		pos.shapes.add(new ShapePos(4, 3, ShapeCatalog.UNIT));

		pos.shapes.add(new ShapePos(0, 1, ShapeCatalog.BOX, true, 3, 1));
		
		sopl("Here's a model of the position for problem 1:");
		sopl(pos.prettyPrint());
				
		printNeighbours(pos);
		doAstar(pos);
		
	}
	

	public static void doProblem18() {
	
		//All coords are i and j where i start at 0 on top left.

		BoardPos pos = new BoardPos();
		
		height = 5;
		width = 4;

		pos.shapes.add(new ShapePos(0, 0, ShapeCatalog.I));
		
		pos.shapes.add(new ShapePos(0, 1, ShapeCatalog.BOX, true, 3, 1));
		
		for(int i=0; i<4; i++) {
			pos.shapes.add(new ShapePos(0 + i, 3, ShapeCatalog.UNIT));
		}
		

		for(int j=0; j<3; j++) {
			pos.shapes.add(new ShapePos(2, j, ShapeCatalog.I));
		}
		
		pos.shapes.add(new ShapePos(4, 1, ShapeCatalog.DASH));
		
		sopl("Here's a model of the position for problem 18:");
		sopl(pos.prettyPrint());
				
		printNeighbours(pos);
		doAstar(pos);
		
	}
	
	public static void doProblem50() {
		
		//All coords are i and j where i start at 0 on top left.

		BoardPos pos = new BoardPos();
		
		height = 5;
		width = 4;

		pos.shapes.add(new ShapePos(0, 0, ShapeCatalog.UNIT));
		pos.shapes.add(new ShapePos(0, 3, ShapeCatalog.UNIT));
		
		pos.shapes.add(new ShapePos(1, 0, ShapeCatalog.I));
		pos.shapes.add(new ShapePos(1, 3, ShapeCatalog.I));
		
		pos.shapes.add(new ShapePos(0, 1, ShapeCatalog.BOX, true, 3, 1));
		
		for(int i=0; i<3; i++) {
			pos.shapes.add(new ShapePos(2 + i, 1, ShapeCatalog.DASH));
		}

		pos.shapes.add(new ShapePos(3, 0, ShapeCatalog.UNIT));
		pos.shapes.add(new ShapePos(3, 3, ShapeCatalog.UNIT));

		
		sopl("Here's a model of the position for problem 50:");
		sopl(pos.prettyPrint());
				
		printNeighbours(pos);
		doAstar(pos);
		
	}
	
	public static void printNeighbours(BoardPos pos) {
		
		//Clear current static positions
		positions  = new Hashtable<String, Integer>();

		pos.setupShapeRecylingProgram();
		
		ArrayList<AstarNode> neighbours = pos.getNodeNeighbours();
		
		sopl("Neighbour\'s");
		
		for(int s=0; s<neighbours.size(); s++) {
			sopl(((BoardPos)neighbours.get(s)).prettyPrint());
		}
	}
	
	public static void doAstar(BoardPos pos) {
		sopl("DO ASTAR");
		ArrayList<AstarNode> path;
		

		//Clear current static positions
		positions  = new Hashtable<String, Integer>();

		pos.setupShapeRecylingProgram();
		
		registerBoard(pos);
		
		path = AstarAlgo.astar(pos, null);
		
		int numMoves = 0;
		int numMoves2 = 0;
		sopl("Path:");
		for(int i=0; i<path.size(); i++) {

			
			if(i>0) {
				if(path.get(i-1).getCostOfMove(path.get(i)) > 0) {
					System.out.println("REAL MOVE");
					numMoves2++;
				}
			
				numMoves += path.get(i-1).getCostOfMove(path.get(i));
			}
			sopl(((BoardPos)path.get(i)).prettyPrint());
		}
		
		sopl("Number of Moves: " + numMoves);
		sopl("Number of real Moves: " + numMoves2);
	}
	
	//TODO: replace arraylist with array (1 less complex object)
	public ArrayList<ShapePos> shapes = new ArrayList<ShapePos>();
	public int indexSelected = -1;
	
	public BoardPos makeHardCopy() {
		BoardPos newBoard = new BoardPos();
		for(int i=0; i<shapes.size(); i++) {
			//Soft copy shapes to make it go faster:
			//(Hard copy list though)
			newBoard.shapes.add(shapes.get(i));
		}

		newBoard.indexSelected = this.indexSelected;
		
		return newBoard;
	}
	
	
	//TODO: This heuristic is pretty bad...
	//TODO: This heuristic probably doesn't work for set 2.
	//Don't use goal param...
	public long getAdmissibleHeuristic(AstarNode goal) {
		if(goal != null) {
			System.out.println("ERROR: used goal param");
			System.exit(1);
		}
		
		
		//Let's keep it simple and just calc the manhattan distance goal shape has to goal
		ShapePos goalShape = getGoalShape();
		
		int yDist = Math.abs(goalShape.getI() - goalShape.goali);
		int xDist = Math.abs(goalShape.getJ() - goalShape.goalj);
		
		if(yDist + xDist == 0) {
			return -1;
		} else if(this.indexSelected != -1 && shapes.get(this.indexSelected).isGoalShape) {
			return 0;
		} else {
			return 1;
		}
	}
	
	public void setGoalIJ(int i, int j) {
		getGoalShape().goali = i;
		getGoalShape().goalj = j;
	}
	
	public ShapePos getGoalShape() {
		for(int i=0; i<shapes.size(); i++) {
			if(shapes.get(i).isGoalShape) {
				return shapes.get(i);
				
			}
		}
		
		return null;
	}

	public static final int NUM_DIRS = 4;
	@Override
	
	public ArrayList<AstarNode> getNodeNeighbours() {
		
		ArrayList<AstarNode> neighbours = new ArrayList<AstarNode>();
		
		for(int s=0; s<this.shapes.size(); s++) {
			for(int d=0; d<NUM_DIRS; d++) {
				if(couldMoveShapeDir(s, d)) {
					
					//sopl(s + " " + d);
					//TODO: maybe don't do a hard copy to save some time...
					BoardPos tempBoard = moveShapeDirHardcopy(s, d);
					//TODO: ONLY DO HARD COPY INSIDE IF COND
					
					if(boardPosAlreadyFoundWithLessOrEqualMovesNeeded(tempBoard) == false) {
					
						registerBoard(tempBoard);
						neighbours.add(positionAddresses.get(tempBoard.toString()));
						
					}
					
					//SANITY TEST
					if(positionAddresses.get(tempBoard.toString()).equals(tempBoard) == false) {
						sopl("ERROR: something went wrong!");
					}
					//END SANITY TEST
				}
			}
		}
		
		return neighbours;
	}
	
	@Override
	public long getCostOfMove(AstarNode nextPos) {
		//Move a shape only costs 1 move
		if(((BoardPos)nextPos).indexSelected == this.indexSelected) {
			return 0;
		} else {
			return 1;
		}
	}
		
	
	public boolean  equals (Object object) {
		if(this.toString().equals(object.toString())) {
			return true;
		} else {
			return false;
		}
	}
	
	
	
	//private String tempToString = "";
	
	
	public String toString() {
		//TODO
		//if(tempToString.equals("") == false) {
		//	return tempToString;
		//}
		
		BigInteger ret = BigInteger.ZERO;
		BigInteger MULT = new BigInteger("2");
		
		
		boolean tableTaken[][] = new boolean[height][width];
		boolean tableLeftRightAttached[][] = new boolean[height][width];
		boolean tableUpDownAttached[][] = new boolean[height][width];
		
		for(int i=0; i<tableTaken.length; i++) {
			for(int j=0; j<tableTaken[0].length; j++) {
				tableTaken[i][j] = false;
				tableLeftRightAttached[i][j] = false;
				tableUpDownAttached[i][j] = false;
			}
		}
		
		for(int s=0; s<shapes.size(); s++) {
			ShapePos curShape = shapes.get(s);
			boolean curTable[][] = curShape.table;

			for(int i=0; i<curTable.length; i++) {
				for(int j=0; j<curTable[0].length; j++) {
					if(curTable[i][j] == TAKEN) {
						int iBoard = curShape.getI() + i;
						int jBoard = curShape.getJ() + j;
						
						if(iBoard >=0 && iBoard<height
						&& jBoard >=0 && jBoard<width) {
							if(tableTaken[iBoard][jBoard] == false) {
								tableTaken[iBoard][jBoard] = true;
							}

							if(j+1 < curTable[0].length) {
								if(curTable[i][j+1] == TAKEN) {
									tableLeftRightAttached[iBoard][jBoard] = true;
								}
							}
							ret = ret.multiply(MULT);
							if(i+1 < curTable.length) {
								if(curTable[i+1][j] == TAKEN) {
									tableUpDownAttached[iBoard][jBoard] = true;
								}
							}
							
							
						}
					}
				}
			}
		}
		
		for(int i=0; i<height; i++) {
			for(int j=0; j<width; j++) {
				if(tableTaken[i][j]) {
					ret = ret.add(BigInteger.ONE);
				}
				ret = ret.multiply(MULT);

				if(tableLeftRightAttached[i][j]) {
                   ret = ret.add(BigInteger.ONE);
				}
				ret = ret.multiply(MULT);
				
				if(tableUpDownAttached[i][j]) {
					ret = ret.add(BigInteger.ONE);
				}
				
				ret = ret.multiply(MULT);
			}
		}
		/*
		 * if(indexSelected >=0) {
			ret += "Current Shape selected location (i, j) = " + this.shapes.get(indexSelected).getI() + ", " + this.shapes.get(indexSelected).getJ() + "\n";
		} else {
			ret += "No Shape selected!" + "\n";
		}
		ret += "Current Goal Shape location (i, j) = " + this.getGoalShape().getI() + ", " + this.getGoalShape().getJ() + "\n";
		ret += "Goal location (i, j) = " + this.getGoalShape().goali + ", " + this.getGoalShape().goalj + "\n";
		ret+= "\n\n";
		
		
		 */
		for(int i=0; i<Math.log(this.shapes.size()) + 2; i++) {
			ret = ret.multiply(MULT);
		}
		if(this.indexSelected == -1) {
			ret = ret.add(new BigInteger("" + this.shapes.size()));
		} else {
			ret = ret.add(new BigInteger("" + this.indexSelected));
		}
		for(int i=0; i<Math.log(width * height) + 2; i++) {
			ret = ret.multiply(MULT);
		}
		ret = ret.add(new BigInteger("" + (this.getGoalShape().getI() * this.getGoalShape().getJ()) ) );
		
		
		//sopl("TEST: " + ret.toString());
		return ret.toString();
	}
	//Every unique pos should have it's own string...
	public String prettyPrint() {
		if(isPossiblePos() == false) {
			sopl("ERROR: trying to print illegal position");
			System.exit(1);
			return "false";
		}
		
		boolean tableTaken[][] = new boolean[height][width];
		boolean tableLeftRightAttached[][] = new boolean[height][width];
		boolean tableUpDownAttached[][] = new boolean[height][width];
		
		for(int i=0; i<tableTaken.length; i++) {
			for(int j=0; j<tableTaken[0].length; j++) {
				tableTaken[i][j] = false;
				tableLeftRightAttached[i][j] = false;
				tableUpDownAttached[i][j] = false;
			}
		}
		
		for(int s=0; s<shapes.size(); s++) {
			ShapePos curShape = shapes.get(s);
			boolean curTable[][] = curShape.table;

			for(int i=0; i<curTable.length; i++) {
				for(int j=0; j<curTable[0].length; j++) {
					if(curTable[i][j] == TAKEN) {
						int iBoard = curShape.getI() + i;
						int jBoard = curShape.getJ() + j;
						
						if(iBoard >=0 && iBoard<height
						&& jBoard >=0 && jBoard<width) {
							if(tableTaken[iBoard][jBoard] == false) {
								tableTaken[iBoard][jBoard] = true;
							}
							
							if(j+1 < curTable[0].length) {
								if(curTable[i][j+1] == TAKEN) {
									tableLeftRightAttached[iBoard][jBoard] = true;
								}
							}
							
							if(i+1 < curTable.length) {
								if(curTable[i+1][j] == TAKEN) {
									tableUpDownAttached[iBoard][jBoard] = true;
								}
							}
							
						}
					}
				}
			}
		}
		
		int BORDER_SIZE = 1;
		int CELL_SIZE = 8;
		//TODO: make it look better...
		String ret = "";
		for(int i=-1; i< CELL_SIZE * tableTaken.length + 1; i++) {
			for(int j=-1; j< CELL_SIZE * tableTaken[0].length + 1; j++) {
				
				if(i==-1 || j==-1 || i==CELL_SIZE * tableTaken.length || j ==CELL_SIZE * tableTaken[0].length) {
					ret += "+";
					continue;
				}
				int blockI = i / CELL_SIZE;
				int blockJ = j / CELL_SIZE;
				
				if(tableTaken[blockI][blockJ] == false) {
					ret += " ";
				} else {
					boolean notBoarderY = i % CELL_SIZE >= BORDER_SIZE 
							&& i % CELL_SIZE <= CELL_SIZE - 1 - BORDER_SIZE;
					
					boolean notBoarderX = j % CELL_SIZE >= BORDER_SIZE 
							&& j % CELL_SIZE <= CELL_SIZE - 1 - BORDER_SIZE;

					if(i>0 && i % CELL_SIZE < BORDER_SIZE) {
						blockI--;
					}
					
					if(j>0 && j % CELL_SIZE < BORDER_SIZE) {
						blockJ--;
					}
					
					if((notBoarderY || tableUpDownAttached[blockI][blockJ])
							&& (notBoarderX || tableLeftRightAttached[blockI][blockJ])) {
						ret += "*";
					} else {
						ret += " ";
					}
					
				}
			}
			ret += "\n";
		}

		if(indexSelected >=0) {
			ret += "Current Shape selected location (i, j) = " + this.shapes.get(indexSelected).getI() + ", " + this.shapes.get(indexSelected).getJ() + "\n";
		} else {
			ret += "No Shape selected!" + "\n";
		}
		ret += "Current Goal Shape location (i, j) = " + this.getGoalShape().getI() + ", " + this.getGoalShape().getJ() + "\n";
		ret += "Goal location (i, j) = " + this.getGoalShape().goali + ", " + this.getGoalShape().goalj + "\n";
		ret+= "\n\n";
		
		return ret;
	}
	
	//TODO: play with when not too lazy...
	//TODO pre: the position is possible.
	//public int hashCode() {
	//	return 0;
	//}
	//public int hashCode();
	//public boolean  equals (Object object);

	private int num_moves_from_start=0;
	
	public BoardPos moveShapeDirHardcopy(int indexS, int dir) {
		if(couldMoveShapeDir(indexS, dir) == false) {
			return null;
		}
		BoardPos newBoard = this.makeHardCopy();
		
		newBoard.indexSelected = indexS;
		
		if(getCostOfMove(newBoard) > 0) {
			newBoard.num_moves_from_start++;
		}

		newBoard.shapes.set(indexS, newBoard.shapes.get(indexS).getShapeThatIsMovedInDir(dir));
		
		return newBoard;
	
	}
	
	public boolean couldMoveShapeDir(int indexS, int dir) {
		if(shapes.get(indexS).isMovable == false) {
			return false;
		}
		
		shapes.set(indexS, shapes.get(indexS).getShapeThatIsMovedInDir(dir));
		
		boolean ret = isPossiblePos();

		shapes.set(indexS, shapes.get(indexS).getShapeThatIsMovedInDir(getOppositeDir(dir)));
		
		return ret;
	}
	
	
	public static int getOppositeDir(int dir) {
		return (dir + 2) % 4;
	}


	public static final boolean TAKEN = true;
	
	
	
	public boolean isPossiblePos() {
		

		//public ArrayList<ShapePos> shapes = new ArrayList<ShapePos>();
		//public int width;
		//public int height;


		boolean tableTaken[][] = new boolean[height][width];
		
		for(int i=0; i<tableTaken.length; i++) {
			for(int j=0; j<tableTaken[0].length; j++) {
				tableTaken[i][j] = false;
			}
		}
		
		for(int s=0; s<shapes.size(); s++) {
			ShapePos curShape = shapes.get(s);
			boolean curTable[][] = curShape.table;

			for(int i=0; i<curTable.length; i++) {
				for(int j=0; j<curTable[0].length; j++) {
					if(curTable[i][j] == TAKEN) {
						int iBoard = curShape.getI() + i;
						int jBoard = curShape.getJ() + j;
						
						if(iBoard >=0 && iBoard<height
						&& jBoard >=0 && jBoard<width) {
							if(tableTaken[iBoard][jBoard] == false) {
								tableTaken[iBoard][jBoard] = true;
							} else {
								return false;
							}
						} else {
							return false;
						}
					}
				}
			}
		}
		return true;
	}


	public static Hashtable<String, Integer> positions
    = new Hashtable<String, Integer>();
	
	public static Hashtable<String, BoardPos> positionAddresses
    = new Hashtable<String, BoardPos>();
	
	public static boolean boardPosAlreadyFoundWithLessOrEqualMovesNeeded(BoardPos pos) {
		
		if(positions.get(pos.toString()) != null
				&& pos.num_moves_from_start >= positions.get(pos.toString())) {
			return true;
		} else {
			return false;
		}
	}
	
	public static int testNumRegistered = 0;
	public static int testNumCurrent = 0;
	public static void registerBoard(BoardPos pos) {
		
		if(positions.get(pos.toString()) != null) {
			
			//SANITY TEST
			if(pos.num_moves_from_start >= positions.get(pos.toString())) {
				
				if(pos.num_moves_from_start == positions.get(pos.toString())) {
					sopl("ERROR: registering an equal board!");
				} else {
					sopl("ERROR: registering an inferior board!");
				}
				System.exit(1);
				
			}
			//END SANITY TEST
	
			positions.remove(pos.toString());
			testNumCurrent--;
			
		}
		
		positions.put(pos.toString(), pos.num_moves_from_start);
		positionAddresses.put(pos.toString(), pos);

		//TESTING:
		testNumRegistered++;
		testNumCurrent++;
		
		if(testNumRegistered % 1000 == 0) {
			sopl("TEST HEAP SPACE TAKEN: Num registered: " +testNumRegistered + " (current: " + testNumCurrent + ")");
		}
		//END TESTING
	}
	
	
	public static void sop(Object a) {
		System.out.print(a.toString());
	}
	public static void sopl(Object a) {
		System.out.println(a.toString());
	}
	
	public void setupShapeRecylingProgram() {
		for(int i=0; i<shapes.size(); i++) {
			shapes.get(i).setupShapeRecyclingProgram(this.height, this.width);
		}
	}
}
