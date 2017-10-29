package probs2016;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import arithmetic.ArithNode;

public class prob25 {

	//10000 test cases is probably good enough.
	public static int NUM_TESTCASES = 10000;
	
	public static int END_INDEX = -1;
	
	public static void main(String[] args) {
		Scanner in;
		Scanner in2;

		try {
			in = new Scanner(new File("prob25in.txt"));
			
			 
			 in2 = new Scanner(System.in);
			 String line;
			 ArrayList<String> lines = new ArrayList<String>();
			 
			 
			System.out.println("Start");
			 while(in.hasNextLine()) {
				 line = in.nextLine();
				 lines.add(line);
				 
			 }
			 
			 long answer = 0;
			
			 
			 long register[] = new long[4];
			 register[0] = 158;
			 register[1] = 0;
			 register[2] = 0;
			 register[3] = 0;
			 
			 prob12runProg.runProg(lines, register);
			 
			 
			 in2.close();
			 in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void bad() {
		Scanner in;
		Scanner in2;

		try {
			in = new Scanner(new File("prob25in.txt"));
			
			 
			 in2 = new Scanner(System.in);
			 String line;
			 ArrayList<String> lines = new ArrayList<String>();
			 
			 
			System.out.println("Start");
			 while(in.hasNextLine()) {
				 line = in.nextLine();
				 lines.add(line);
				 
			 }
			 
			 boolean foundAnswer = false;
			 
			 long answer = 0;
			
			 int currentIndex = 0;
			 
			 ArrayList<prob25Range> currentARanges = new ArrayList<prob25Range>();
			 
			 currentARanges.add(new prob25Range(lines, currentIndex));
			 
			 long goalOutput;
			 
			 for(int i=0; i<NUM_TESTCASES; i++) {
				 goalOutput = i%2;
				 
				 currentARanges = getAcceptableAForNextGoal(currentARanges, goalOutput);
				 
				 if(hasNoSolutions(currentARanges)) {
					 System.out.println("ERROR: there's no solutions!");
				 }
				 
				 if(hasOneSolution(currentARanges)) {
					 System.out.println("AHA! I got it!");
					 foundAnswer = true;
				 }
				 
				 
			 }
			 
			 if(foundAnswer == false) {
				 System.out.println("Result after " + NUM_TESTCASES + " tests: ");
			 } else {
				 System.out.println("Results obtained:");
			 }
			 
			 System.out.println("Asnwer: " + answer);
			 
			 in2.close();
			 in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static ArrayList<prob25Range> getAcceptableAForNextGoal(ArrayList<prob25Range> ranges, long goalOutput) {
		ArrayList<prob25Range> ret = new ArrayList<prob25Range>();
		//plan: list of ran
		for(int i=0; i<ranges.size(); i++) {
			ret.add(getAcceptableAForNextGoal(ranges, ranges.get(i), goalOutput));
		}
		
		//TODO: consolidate ranges here?
		
		return ret;
	}
	
	
	//calls prob 23 diff
	//and uses prob25range
	//to figure the possible value of the original register a.
	//If there's an ambiguous jump cond, it makes a note of the equation where the jump command doesn't work
	//and moves on.
	//The conditions where the jump conds don't work are all recorded.
	
	public static prob25Range getAcceptableAForNextGoal(ArrayList<prob25Range> ranges, prob25Range currentRange, long goalOutput) {
		
		
		//Idea: start make segment whole code and break segment when things start getting a little too complicated to follow easily.
		
		prob23Diff.segments = new ArrayList<prob23SegmentDiff>();
		
		ArrayList <String> lines = currentRange.getLines();
		
		int currentIndex = 0;
		END_INDEX = lines.size() - 1;
		while(currentIndex <= END_INDEX) {
			
			prob23SegmentDiff current = prob23Diff.getSegmentWithIndex(currentIndex);
			
			if(current == null) {
				current = new prob23SegmentDiff(currentIndex, lines.size() - 1);
				prob23Diff.segments.add(current);
				
			}
			
			current = prob23Diff.getSegmentEffects(lines, current);
			
			
			//Make a substitution:
			ArithNode changes[] = current.getRegisterChanges();

			for(int i=0; i<changes.length; i++) {
				changes[i] = changes[i].substitute(currentRange.getCurrentEffects());
				changes[i] = changes[i].simplifiedCopy();
			}
			System.out.println("Current End index: " + current.getEndIndex());
			currentRange.setCurrentEffects(changes);
			//For debugging:
			//TODO: maek the constant part look better.
			currentRange.printCurrentEffect();
			
			
			if(current.isSplitsOnInput()) {
				
				prob23Diff.validateSplitCommand(lines, current, currentIndex);
				
				
				if(lines.get(current.getStartIndex()).startsWith("tgl")) {
					System.out.println("ERROR: problem 25 should not have any toggles!");
					System.exit(1);
					
				} else if(lines.get(current.getStartIndex()).startsWith("jnz")) {
					
					System.out.println("Jump time");
					int exitIndex = dealWithAmbiguousJnzWithInput(ranges, currentRange, lines, currentIndex);
					
					current.setExitIndex(exitIndex);
					//TODO: maybe have a solve for 0 functionn in equation class...
					
				}
				
			} else if(lines.get(current.getStartIndex()).startsWith("out")) {
				//TODO: deal with output here.
				String token[] = lines.get(current.getStartIndex()).split(" ");
				//int index = (int)(token[1].charAt(0) - 'a');
				System.out.println("TODO:");
				System.out.println(token[1] + " = " + currentRange.getCurrentEffects().get(token[1]));
				
				//TODO keep going once you know it's ok
				
				System.exit(1);
			}
			
 			currentIndex = current.getExitIndex();
		}
		
		return null;
	}
	
	
	public static boolean hasNoSolutions(ArrayList<prob25Range> ranges) {
		//TODO
		return false;
	}
	
	public static boolean hasOneSolution(ArrayList<prob25Range> ranges) {
		//TODO
		return false;
	}
	
	public static int dealWithAmbiguousJnzWithInput(ArrayList<prob25Range> ranges, prob25Range currentRange, ArrayList<String> lines, int programCounter) {
		//System.out.println("In ambiguous jump");
		
		String token[] = lines.get(programCounter).split(" ");
		
		 long isJumpAllowed = 0;
		 if(prob12runProg.isRegister(token[1].charAt(0), prob23Diff.LIMIT_REGISTER)) {
			
			 ArithNode jumpCond = currentRange.getCurrentEffects().get(token[1]).simplifiedCopy();
			 
			 if(jumpCond.hasVariable()) {

				 currentRange.addToExcl(jumpCond);
				 
				 //In this case, we assume we are jumping.
				 isJumpAllowed = 1;
				 
				 //TODO: split with the case where the register is 0 and there's no jumping.
				 //I.e: make a new prob25Range here and add it to the list of ranges.
				 
			 } else {
				 if(jumpCond.eval() == 0) {
					 isJumpAllowed = 0;
				 } else {
					 isJumpAllowed = 1;
				 }
			 }
		 } else {
			 isJumpAllowed = Integer.parseInt(token[1]);
		 }
		 
		 long jumpNumber = 0;
		 if(prob12runProg.isRegister(token[2].charAt(0), prob23Diff.LIMIT_REGISTER)) {
			 
			 System.out.println("ERROR: there is no jump by register in prob 25");
			 System.exit(1);
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
	
	public static void dealWithOutputCommand(ArrayList<prob25Range> ranges, prob25Range currentRange, ArrayList<String> lines, int programCounter, long goalOuput) {
		
		//TODO: actually act as a filter.
		
		String token[] = lines.get(programCounter).split(" ");
		System.out.println("Here's the next goal:");
		System.out.println(currentRange.getCurrentEffects().get(token[1]).simplifiedCopy() + " = " + goalOuput);
		
		
	}
}
