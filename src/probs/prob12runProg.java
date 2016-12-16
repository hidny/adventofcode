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
				 
				 if(token[0].equals("cpy")) {
					 if(token[1].charAt(0) >= 'a' && token[1].charAt(0) <= LIMIT_REGISTER) {
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
					 if(token[1].charAt(0) >= 'a' && token[1].charAt(0) <= LIMIT_REGISTER) {
						 jumpNumber = register[getRegisterIndex(token[1])];
					 } else {
						 jumpNumber = Integer.parseInt(token[1]);
					 }
					 
					 if(jumpNumber != 0) {
						 programCounter += Integer.parseInt(token[2]);
						 continue;
					 }
				 }
				 
				 programCounter++;
			 }
			 
			 return register[0];
			 
	}
	
	public static int getRegisterIndex(String reg) {
		return getRegisterIndex(reg.toLowerCase().charAt(0));
	}
	public static int getRegisterIndex(char reg) {
		return (int)(reg - 'a');
	}

}
