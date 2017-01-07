package probs;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class prob12runProg {

	public static long runProg(ArrayList<String> lines, long register[]){
			
			char LIMIT_REGISTER = (char)((int) 'a' + register.length - 1);
			
			
			String token[];
			int programCounter = 0;
			while(programCounter < lines.size() && programCounter >=0) {

				 token = lines.get(programCounter).split(" ");
				 
				 if(token[0].equals("cpy") && isRegister(token[2].charAt(0), LIMIT_REGISTER)) {
					 if(isRegister(token[1].charAt(0), LIMIT_REGISTER) ) {
						 register[getRegisterIndex(token[2])] = register[getRegisterIndex(token[1])];
					 } else {
						 register[getRegisterIndex(token[2])] = Integer.parseInt(token[1]);
					 }
				 } else if(token[0].equals("inc")) {
					 register[getRegisterIndex(token[1])]++;
					 
				 } else if(token[0].equals("dec")) {
					 
					 register[getRegisterIndex(token[1])]--;
				 
				 } else if(token[0].equals("jnz")) {
					 long jumpNumber = 0;
					 if(isRegister(token[1].charAt(0), LIMIT_REGISTER)) {
						 jumpNumber = register[getRegisterIndex(token[1])];
					 } else {
						 jumpNumber = Integer.parseInt(token[1]);
					 }
					 
					 long dist = 0;
					 if(isRegister(token[2].charAt(0), LIMIT_REGISTER)) {
						 dist = register[getRegisterIndex(token[2])];
					 } else {
						 dist = Integer.parseInt(token[2]);
					 }
					 
					 if(jumpNumber != 0) {
						 programCounter += dist;
						 continue;
					 }
				 //for prob 23:
				 } else if(token[0].equals("tgl")) {
					 System.out.println("At toggle:");
					 for(int i=0; i<register.length; i++) {
						 System.out.println((char)('a' + i) + " = " + register[i]);
					 }
					 System.out.println("-----------------");
					 long jumpNumber = 0;
					 if(isRegister(token[1].charAt(0), LIMIT_REGISTER)) {
						 jumpNumber = register[getRegisterIndex(token[1])];
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
					 }
				 }
				 
				 programCounter++;
			 }
			 
			 return register[0];
			 
	}
	
	public static boolean isRegister(char var, int LIMIT_REGISTER) {
		if(var >= 'a' && var <= LIMIT_REGISTER) {
			return true;
		} else {
			return false;
		}
	}
	
	public static int getRegisterIndex(String reg) {
		return getRegisterIndex(reg.toLowerCase().charAt(0));
	}
	public static int getRegisterIndex(char reg) {
		return (int)(reg - 'a');
	}

}
