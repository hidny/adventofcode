package practice.snaky_problem.AI;

import java.util.ArrayList;

import practice.snaky_problem.GUI.Constants;
import practice.snaky_problem.logic.BoardStateStatic;

public class BasicRandomHandler implements MoveHandlerInterface {

	public int[] handleMove(int i, int j) {
		
		
		int ret[] = new int[2];
		ret[0] =-1;
		ret[1] =-1;
		
		ArrayList<String> pos = new ArrayList<String>();
		
		for(int i2=i-1; i2<=i+1; i2++) {

			for(int j2=j-1; j2<=j+1; j2++) {
				
				if(j2>=0 && i2>=0 && i2<Constants.NUM_CELLS_VERT && j2<Constants.NUM_CELLS_HORI) {
				
					if((i2 !=i && j2 == j) || (i2 == i && j2 != j) ) {
						if(BoardStateStatic.get(i2, j2) == Constants.EMPTY) {
							pos.add(i2 + "," +j2);
							
						}
					}
				}
			}
		}
		
		if(pos.size() > 0) {
			int index = (int)(pos.size() * Math.random());
			
			ret[0] = Integer.parseInt(pos.get(index).split(",")[0]);
			ret[1] = Integer.parseInt(pos.get(index).split(",")[1]);
		} else {
			
			for(int i2=0; i2<Constants.NUM_CELLS_VERT; i2++) {

				for(int j2=0; j2<Constants.NUM_CELLS_HORI; j2++) {
					if(BoardStateStatic.get(i2, j2) == Constants.EMPTY) {
						
						ret[0] = i2;
						ret[1] = j2;
					}
						
				}
			}
		}
		
		
		return ret;
	}
}
