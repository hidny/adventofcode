package probs;

import java.util.ArrayList;

import arithmetic.ArithNode;

//JNZ logic is here because if I don't seperate this logic out, it clutters the code.

public class prob23jnz {
	
	public enum jnzType {
	    JUMPCANCELLED, JUMPNOWHERE, JUMPMULT,
	    AMBIGUOUS
	}
	
	

	//I know it's not nice to redeclare const... but...
	public static char LIMIT_REGISTER = 'd';
	
	public static jnzType getType(prob23SegmentDiff segment, ArrayList<String> lines, int currentLine) {
		
		String inst = lines.get(currentLine);
		
		if(segment.getStartIndex() == currentLine) {
			return jnzType.AMBIGUOUS;
		}
		
		if(isJumpCancelled(segment, currentLine, inst)) {
			return jnzType.JUMPCANCELLED;
		}
		
		if(isAlwaysJumpTrue(segment, currentLine, inst)) {

			//TODO: maybe make always jump true and constant jump number part of the same segment
			//for prob 25, maybe have another special type to say the variable a is just as free?
			if(hasConstantJumpNumber(segment, currentLine, inst)) {
				long jumpNumber = getJumpNumber(segment, currentLine, inst);
				
				if(jumpNumber == 0) {
					return jnzType.JUMPNOWHERE;
				}
			}
			return jnzType.AMBIGUOUS;
		}
		
		if(hasConstantJumpNumber(segment, currentLine, inst)) {
			long jumpNumber = getJumpNumber(segment, currentLine, inst);
			
			if(jumpNumber == 0) {
				return jnzType.JUMPNOWHERE;
				
			} else if(jumpNumber > 0) {
				return jnzType.AMBIGUOUS;
				
			} else {
				
				if(jumpNumber + currentLine < 0) {
					return jnzType.AMBIGUOUS;
				}
				if((int)(currentLine + jumpNumber) < segment.getStartIndex()) {
					return jnzType.AMBIGUOUS;
				}
				//instJumpsInLoopJumpsoutOfLoop(ArrayList <String> lines, prob23SegmentDiff segment, int startIndex, int endIndex)
				if(instJumpsInLoopJumpsoutOfLoop(lines, segment, (int)(currentLine + jumpNumber), currentLine - 1) ){
					return jnzType.AMBIGUOUS;
				}
				
				ArithNode numBackJumps = getNumBackJumps(segment, currentLine, inst, jumpNumber);
				System.out.println("In prob23jnz: " + numBackJumps.simplifiedCopy());
				
				
				return jnzType.JUMPMULT;
			}
			
			
		} else {
			return jnzType.AMBIGUOUS;
		}
		
		
	}
	// pre: segment.getStartIndex() != currentLine
	public static boolean isJumpCancelled(prob23SegmentDiff segment, int currentLine, String inst) {
		boolean isJumpCancelled = false;
		 String token[] = inst.split(" ");
		 if(prob12runProg.isRegister(token[1].charAt(0), LIMIT_REGISTER) == true) {
			 
			 ArithNode condNumber = segment.getRegisterChanges(segment.getStartIndex(), currentLine)[prob12runProg.getRegisterIndex(token[1])];
			 if(condNumber.hasVariable() == false && condNumber.eval() == 0) {
				 isJumpCancelled = true;
			 }
		 } else if(Integer.parseInt(token[1]) == 0) {
			 isJumpCancelled = true;
		 }
		 
		 return isJumpCancelled;
	}
	
	// pre: segment.getStartIndex() != currentLine
	public static boolean hasConstantJumpNumber(prob23SegmentDiff segment, int currentLine, String inst) {
		 boolean hasConstantJumpNumber = false;
		 String token[] = inst.split(" ");
		 
		 if(prob12runProg.isRegister(token[2].charAt(0), LIMIT_REGISTER)) {
			 if(segment.getRegisterChanges(segment.getStartIndex(), currentLine)[prob12runProg.getRegisterIndex(token[2])].hasVariable() == false) {
				 hasConstantJumpNumber = true;
			 }
		 } else {
			 hasConstantJumpNumber = true;
		 }
		 
		 return hasConstantJumpNumber;
	}
	
	// pre: segment.getStartIndex() != currentLine
	public static boolean isAlwaysJumpTrue(prob23SegmentDiff segment, int currentLine, String inst) {
		boolean isAlwaysJump = false; 
		String token[] = inst.split(" ");
		 
		 if(isJumpCancelled(segment, currentLine, inst) == false) {
			 isAlwaysJump = false;
		 } else if(prob12runProg.isRegister(token[1].charAt(0), LIMIT_REGISTER)) {
			 ArithNode condNumber = segment.getRegisterChanges(segment.getStartIndex(), currentLine)[prob12runProg.getRegisterIndex(token[1])];
			 if(condNumber.hasVariable() == false && condNumber.eval() != 0) {
				 isAlwaysJump = true;
			 }
		 } else {
			 isAlwaysJump = true;
		 }
		 
		 return isAlwaysJump;
	}
	
	// pre: segment.getStartIndex() != currentLine
	//pre hasConstantJumpNumber is true.
	public static long getJumpNumber(prob23SegmentDiff segment, int currentLine, String inst) {
		 long jumpNumber = 0;
		 String token[] = inst.split(" ");
		 
		 if(prob12runProg.isRegister(token[2].charAt(0), LIMIT_REGISTER)) {
			 if(segment.getRegisterChanges(segment.getStartIndex(), currentLine)[prob12runProg.getRegisterIndex(token[2])].hasVariable() == false) {
				 jumpNumber = segment.getRegisterChanges(segment.getStartIndex(), currentLine)[prob12runProg.getRegisterIndex(token[2])].eval();
			 } else {
				 System.out.println("ERROR: precondition not satisfied for getJumpNumber!");
				 System.exit(1);
			 }
		 } else {
			 jumpNumber = Integer.parseInt(token[2]);
		 }
		 
		 return jumpNumber;
		
	}
	// pre: segment.getStartIndex() != currentLine
	public static ArithNode getNumBackJumps(prob23SegmentDiff segment, int currentLine, String inst, long jumpNumber) {

		System.out.println("Negative jump/loop! The way it is now:");
		System.out.println(currentLine);
		System.out.println(inst);
		System.out.println(jumpNumber);
		
		System.out.println(segment);
		
		ArithNode registerChangesAfterLoop[]  = segment.getRegisterChanges((int)(currentLine + jumpNumber), currentLine);
		
		System.out.println("Changes after loop:");
		for(int i=0; i<registerChangesAfterLoop.length; i++) {
			System.out.println( (char)('a' + i) + " = " + registerChangesAfterLoop[i].simplifiedCopy());
		}
		
		System.out.println("Relative changes after loop:");
		//Get diff after loop:
		for(int i=0; i<registerChangesAfterLoop.length; i++) {
			registerChangesAfterLoop[i] = new ArithNode('-', registerChangesAfterLoop[i], new ArithNode("" + (char)('a' + i))).simplifiedCopy();

			System.out.println( (char)('a' + i) + " += " + registerChangesAfterLoop[i].simplifiedCopy());
		}

		String token[] = inst.split(" ");
		
		int indexLoopRegister = prob12runProg.getRegisterIndex(token[1].charAt(0));
		
		ArithNode currentValueLoopRegister = segment.getRegisterChanges(segment.getStartIndex(), currentLine)[indexLoopRegister];
		
		ArithNode changePerLoop =  registerChangesAfterLoop[indexLoopRegister];
		
		ArithNode mult = new ArithNode('/', currentValueLoopRegister,  new ArithNode('*', new ArithNode(-1), changePerLoop));
		System.out.println("mult:");
		System.out.println(mult.simplifiedCopy());
		mult = mult.simplifiedCopy();
		
		return mult;
	}
	// pre: segment.getStartIndex() != currentLine
	public static boolean instJumpsInLoopJumpsoutOfLoop(ArrayList <String> lines, prob23SegmentDiff segment, int startIndex, int endIndex) {
		boolean everythingInLoopDoesntJumpOut = true;
		for(int i=startIndex; i<=endIndex; i++) {
			String inst = lines.get(i);
			if(inst.startsWith("jnz") && isJumpCancelled(segment, i, inst) == false) {
				if(hasConstantJumpNumber(segment, i, inst)) {
					long jumpNumber = getJumpNumber(segment, i, inst);
					
					if(i + jumpNumber > endIndex + 1 || i + jumpNumber < startIndex) {
						everythingInLoopDoesntJumpOut = false;
						break;
					}
					
				} else {
					everythingInLoopDoesntJumpOut = false;
				}
			}
		}
		
		
		return !everythingInLoopDoesntJumpOut;
	}
	
}
