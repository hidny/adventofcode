package probs;

import java.util.ArrayList;
import java.util.Scanner;

import arithmetic.ArithNode;


public class prob23Diff {

	private static ArrayList<prob23SegmentDiff> segments;
	
	public static char LIMIT_REGISTER = (char)((int) 'a' + prob23SegmentDiff.NUM_REGISTERS - 1);
	
	public static long getProb23Solution(ArrayList<String> lines, long input[]) {

		//Idea: make segment whole code and break segment if it gets too complicated.
		segments = new ArrayList<prob23SegmentDiff>();
		
		String varNames[] = new String[prob23SegmentDiff.NUM_REGISTERS];
		
		for(int i=0; i<varNames.length; i++) {
			varNames[i] = "" + (char)('a' + i);
		}
		
		long output[];
		
		int currentIndex = 0;
		int endIndex = lines.size() - 1;
		while(currentIndex <= endIndex) {
			
			prob23SegmentDiff current = getSegmentWithIndex(currentIndex);
			
			if(current == null) {
				current = new prob23SegmentDiff(currentIndex, lines.size() - 1);
				segments.add(current);
				
			}
			
			current = getSegmentEffects(lines, current);
			
			ArithNode changes[] = current.getRegisterChanges();

			
			output = new long[input.length];
			
			for(int i=0; i<input.length; i++) {
				output[i] = changes[i].eval(varNames, input);
			}
			input = output;
			
			if(current.isSplitsOnInput()) {
				
				validateSplitCommand(lines, current, currentIndex);
				
				
				if(lines.get(current.getStartIndex()).startsWith("tgl")) {

					System.out.println("Toggle time!");
					dealWithAmbiguousTglWithInput(lines, current, currentIndex, input);
					
				} else if(lines.get(current.getStartIndex()).startsWith("jnz")) {
					//System.out.println("Jump time");
					int exitIndex = dealWithAmbiguousJnzWithInput(lines, current, currentIndex, input);
					
					current.setExitIndex(exitIndex);
					
				}
			}
 			currentIndex = current.getExitIndex();
		}
		
		return input[0];
	}
	
	
	public static prob23SegmentDiff getSegmentWithIndex(int index) {
		for(int i=0; i<segments.size(); i++) {
			if(segments.get(i).getStartIndex() == index) {
				return segments.get(i);
			}
		}
		System.out.println("Could not find segment starting with index " + index + ".");
		return null;
	}
	
	private static prob23SegmentDiff getSegmentEffects(ArrayList<String> lines, prob23SegmentDiff segment) {
		
		String token[];
		
		
		if(segment.getStartIndex() == 20) {
			//System.out.println("DEBUG");
		}
		
		if(segment.isLineChangedOnTgl() == false && segment.alreadyAnalysed()) {
			//System.out.println("Segment already analysez!");
			return segment;
		}

		segment.setLineChangedOnTgl(false);
		
		int currentLine = segment.getStartIndex();
		
		
		boolean analysingLineFirstTime;
		
		while(currentLine < segment.getExitIndex() && currentLine < lines.size()) {
			
			token = lines.get(currentLine).split(" ");
			 
			if(segment.getNumTimesRan(currentLine).isZero()) {
				analysingLineFirstTime = true;
				segment.setNumTimesRanToValue(currentLine, new ArithNode(1));
			} else {
				analysingLineFirstTime = false;
			}
			
			System.out.println("Number of times ran: " + segment.getNumTimesRan(currentLine));
			 
			 if(token[0].equals("cpy") && prob12runProg.isRegister(token[2].charAt(0), LIMIT_REGISTER)) {
				 if(prob12runProg.isRegister(token[2].charAt(0), LIMIT_REGISTER)) {
					 if(prob12runProg.isRegister(token[1].charAt(0), LIMIT_REGISTER) ) {
						 
						 ArithNode temp;
						 for(int i=segment.getStartIndex(); i<currentLine+1; i++) {
							 for(int j=0; j<prob23SegmentDiff.NUM_REGISTERS; j++) {
								 if(prob12runProg.getRegisterIndex(token[2]) == j) {
									 temp = new ArithNode('*', segment.getNumTimesRan(currentLine), segment.getRegisterChanges(i, currentLine)[prob12runProg.getRegisterIndex(token[1])]);
									 segment.setRegisterChange(i, currentLine+1, j, temp);
								 } else {
									 makeNoChangesToRegister(segment, i, currentLine, j);
								 }
								 
							 }
							 System.out.println("--------------");
						 }
					 } else {
						 ArithNode temp;
						 for(int i=segment.getStartIndex(); i<currentLine+1; i++) {
							 for(int j=0; j<prob23SegmentDiff.NUM_REGISTERS; j++) {
								 if(prob12runProg.getRegisterIndex(token[2]) == j) {
									 temp = new ArithNode('*', segment.getNumTimesRan(currentLine), new ArithNode(Integer.parseInt(token[1])));
									 segment.setRegisterChange(i, currentLine+1, j, temp);
								 } else {
									 makeNoChangesToRegister(segment, i, currentLine, j);
								 }
								 
							 }
							 System.out.println("--------------");
						 }
						
					 }
				 } else {
					 System.out.println("WARNING: bad cpy command. Was it toggled?");
					 noteThatLineMadeNoChanges(segment, currentLine);
					
				 }
				 //System.exit(1);
				 
			 } else if(token[0].equals("inc") || token[0].equals("dec")) {
				 
				 ArithNode temp;
				 char operation;
				 for(int i=segment.getStartIndex(); i<currentLine+1; i++) {
					 for(int j=0; j<prob23SegmentDiff.NUM_REGISTERS; j++) {
						 if(prob12runProg.getRegisterIndex(token[1]) == j) {
							 if(token[0].equals("inc")) {
								 operation = '+';
							 } else {
								 operation = '-';
							 }
							 temp = new ArithNode(operation, segment.getRegisterChanges(i, currentLine)[j], segment.getNumTimesRan(currentLine));
							 segment.setRegisterChange(i, currentLine+1, j, temp);
						 } else {
							 makeNoChangesToRegister(segment, i, currentLine, j);
						 }
						 
					 }
					 System.out.println("--------------");
				 }
				 
				 
			 
			 } else if(token[0].equals("jnz")) {
				System.out.println("----------------------------");
				System.out.println("Analyzing jnz");
				if(analysingLineFirstTime == false) {
					System.out.println("Been there, seen that...");
					//If jnz already seen, that means it was a JUMPMULT:
					noteThatLineMadeNoChanges(segment, currentLine);
				} else {
					prob23jnz.jnzType type = prob23jnz.getType(segment, lines, currentLine);
					
					System.out.println("TYPE: " + type);
					System.out.println(analysingLineFirstTime);
					
					if(type == prob23jnz.jnzType.JUMPMULT && analysingLineFirstTime) {
						String inst = lines.get(currentLine);
						long jumpNumber = prob23jnz.getJumpNumber(segment, currentLine, inst);
						
						ArithNode numBackJumps = prob23jnz.getNumBackJumps(segment, currentLine, inst, jumpNumber);
						
						
						for(int i=(int)(currentLine + jumpNumber); i<= currentLine; i++) {
							ArithNode currentNumTimes = segment.getNumTimesRan(i);
							segment.setNumTimesRanToValue(i, (new ArithNode('+', currentNumTimes, new ArithNode('*', currentNumTimes, numBackJumps))).simplifiedCopy() );
							System.out.println("line: " + i + ": " + segment.getNumTimesRan(i));
							
						}
						
						currentLine = (int)(currentLine + jumpNumber);
						
						continue;
					} else if(type == prob23jnz.jnzType.JUMPCANCELLED || type == prob23jnz.jnzType.JUMPNOWHERE ){
						noteThatLineMadeNoChanges(segment, currentLine);
					} else {
						BreakSegmentAndAddOneLineSegment(lines, currentLine, segment);
					}
				}
					
				System.out.println("----------------------------");
				 
			 //for prob 23:
			 } else if(token[0].equals("tgl")) {
				 BreakSegmentAndAddOneLineSegment(lines, currentLine, segment);
				 
			}
			
			
			currentLine++;
		 }
		
		if(currentLine < segment.getExitIndex()) {
			//Note no change after last line:
			//so it will correctly return the answer at the end
			noteThatLineMadeNoChanges(segment, currentLine);
		}
		
		System.out.println("Finished analysing segment!");
		
		return segment;
	}
	
	public static void BreakSegmentAndAddOneLineSegment(ArrayList<String> lines, int currentLine, prob23SegmentDiff segment) {
		//There's 3 cases here, not two.
		if(currentLine == segment.getStartIndex() && currentLine == segment.getEndIndex()) {
			//On Toggle Segment:
			 noteThatLineMadeNoChanges(segment, currentLine);
			 
			 //if toggle is var, which it is...
			 segment.setCasesSplitOnInput();
			 
		 } else {
			 //Chop segment into 3 pieces.
			 //1 piece is before toggle.
			 //1 piece is the toggle
			 //1 piece is after toggle.

			 //1 piece is before toggle:
			 if(segment.getStartIndex() < currentLine ) {
				 segment.changeEndIndexLocation(currentLine - 1);
				 //noteThatLineMadeNoChanges(segment, currentLine);
			 }

			 //1 piece is the toggle:
			 prob23SegmentDiff temp = getSegmentWithIndex(currentLine);
			 if(temp != null) {
				 temp.changeEndIndexLocation(currentLine);
				 noteThatLineMadeNoChanges(temp, currentLine);
			 } else {
				 temp = new prob23SegmentDiff(currentLine, currentLine);
				 //noteThatLineMadeNoChanges(temp, currentLine);
				 
				 //createNewSegment for Toggle:
				 segments.add(new prob23SegmentDiff(currentLine, currentLine));
			 }

			 //1 piece is after toggle:
			 prob23SegmentDiff temp2 = getSegmentWithIndex(currentLine + 1);
			 if(temp2 == null) {
				 //create new segment for end part:
				 segments.add(new prob23SegmentDiff(currentLine + 1, lines.size() - 1));
			 }
		 }
	}

	public static void validateSplitCommand(ArrayList<String> lines, prob23SegmentDiff current, int programCounter) {
		if(current.getEndIndex() != programCounter) {
			System.out.println("ERROR: Splits on input with a segment greater than 1 line is not allowed.");
			System.exit(1);
		}
		
		String token[] = lines.get(current.getStartIndex()).split(" ");
		
		if(prob12runProg.isRegister(token[1].charAt(0), LIMIT_REGISTER) == false) {
			//System.out.println("FOR PROB 25: Don;t need to split on input with a segment than doesn't have variable inside it.");
			//TODO
			//System.out.println("TODO: FIX IT FOR PROB 25");
			//System.exit(1);
		}
	}
	
	
	//TODO: for prob 25, I mihgt have to make prob23Diff objects depending on how the split happens.
	public static void dealWithAmbiguousTglWithInput(ArrayList<String> lines, prob23SegmentDiff current, int programCounter, long input[]) {
		String token[] = lines.get(current.getStartIndex()).split(" ");
		
		long jumpNumber;
		if(prob12runProg.isRegister(token[1].charAt(0), LIMIT_REGISTER)) {
			 jumpNumber = input[ prob12runProg.getRegisterIndex(token[1]) ];
		 } else {
			 jumpNumber = Integer.parseInt(token[1]);
		 }
		
		 if(programCounter + jumpNumber < lines.size() && programCounter + jumpNumber > 0) {
			 String oldInst = lines.get((int)(programCounter + jumpNumber));
			 String tokenOldInst[] = oldInst.split(" ");
			 int numArgs = tokenOldInst.length - 1;
			 if(numArgs == 1) {
				 if(tokenOldInst[0].equals("inc")) {
					 tokenOldInst[0] = "dec";
				 } else {
					 tokenOldInst[0] = "inc";
				 }
			 } else {
				 if(tokenOldInst[0].equals("jnz")) {
					 tokenOldInst[0] = "cpy";
				 } else {
					 tokenOldInst[0] = "jnz";
				 }
			 }
			 
			 String newInst = "";
			 for(int i=0; i<tokenOldInst.length; i++) {
				 newInst += tokenOldInst[i];
				 if(i + 1 < tokenOldInst.length) {
					 newInst += " ";
				 }
			 }
			 
			 lines.set((int)(programCounter + jumpNumber), newInst);
			 
			 long lineChangeIndex = programCounter + jumpNumber;
			 
			 //note that segments are effected by line change and change segment lengths:
			 for(int i=0; i<segments.size(); i++) {
				 prob23SegmentDiff seg = segments.get(i);
				 if((lineChangeIndex >= seg.getStartIndex() && lineChangeIndex <= seg.getEndIndex()) || (lineChangeIndex == seg.getExitIndex() && seg.getEndIndex() + 1 == seg.getExitIndex())) {
					 
					 if(lines.get(seg.getStartIndex()).startsWith("tgl") == false) {
						 seg.extendLength(lines);
					 }
					 seg.setLineChangedOnTgl(true);
				 }
			 }
			 
			 System.out.println("Segment changed!");
		 } else {
			 System.out.println("Segment out of bounds!");
		 }
		 
	}
	
	
	//TODO: for prob 25, I mihgt have to make prob23Diff objects depending on how the split happens.
		public static int dealWithAmbiguousJnzWithInput(ArrayList<String> lines, prob23SegmentDiff current, int programCounter, long input[]) {
			//TODO just take from prob12runprog
			//System.out.println("In ambiguous jump");
			
			String token[] = lines.get(programCounter).split(" ");
			
			 long isJumpAllowed = 0;
			 if(prob12runProg.isRegister(token[1].charAt(0), LIMIT_REGISTER)) {
				 isJumpAllowed = input[prob12runProg.getRegisterIndex(token[1])];
			 } else {
				 isJumpAllowed = Integer.parseInt(token[1]);
			 }
			 
			 long jumpNumber = 0;
			 if(prob12runProg.isRegister(token[2].charAt(0), LIMIT_REGISTER)) {
				 jumpNumber = input[prob12runProg.getRegisterIndex(token[2])];
			 } else {
				 jumpNumber = Integer.parseInt(token[2]);
			 }
			 
			 if(isJumpAllowed != 0) {
				 programCounter += jumpNumber;
			 } else {
				 programCounter += 1;
			 }
			 
			 return programCounter;
		}
		
		
		public static void noteThatLineMadeNoChanges(prob23SegmentDiff segment, int currentLine) {
			
			 for(int i=segment.getStartIndex(); i<currentLine+1; i++) {
				 for(int j=0; j<prob23SegmentDiff.NUM_REGISTERS; j++) {
					 makeNoChangesToRegister(segment, i, currentLine, j);
				 }
				 System.out.println("--------------");
			 }
		}
		
		public static void makeNoChangesToRegister(prob23SegmentDiff segment, int prevLine, int currentLine, int registerIndex) {
			 ArithNode temp = segment.getRegisterChanges(prevLine, currentLine)[registerIndex];
			 
			 segment.setRegisterChange(prevLine, currentLine+1, registerIndex, temp);
		}

		
}
