package probs2018;

import java.io.File;

import number.IsNumber;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import utils.Mapping;

public class prob24Group {

	private int origUnits = -1;
	public int units = -1;
	public int hp = -1;
	
	public int damage;
	public String attackType ="";
	public int initiative;
	
	public String sideName = "";
	
	
	String line;
	
	public int attackingIndex = -1;
	public boolean attacked = false;
	
	public void restoreOrigHealth() {
		this.units = origUnits;
	}
	
	public void boost1() {
		this.damage++;
	}
	
	public void resetRound() {
		attacked = false;
		attackingIndex = -1;
	}
	
	public prob24Group(String sideName, String line) {
		origUnits = pint(line.split(" ")[0]);
		
		units = pint(line.split(" ")[0]);
		hp = pint(line.split(" ")[4]);
		
		sopl(line);
	
		damage = pint(line.split("with an attack")[1].split(" ")[3]);
		attackType = line.split("with an attack")[1].split(" ")[4];
		initiative = pint(line.split("with an attack")[1].split(" ")[8]);
		
		this.line = line;
		this.sideName = sideName;
		
		this.reprint();
	}
	
	public void reprint() {
		
		sopl(units + " units each with " + hp + " hit points ");
		
		if(line.contains(units + " units each with " + hp + " hit points ") == false) {
			sopl("ERROR in input");
			exit(2);
		}
		
		String temp = line.split("\\)")[0];
		
		sopl("weak:");
		if(temp.contains("weak to")) {
			temp = temp.split("weak to")[1];
			if(temp.contains("immune")) {
				temp = temp.split("immune")[0];
			}
		} else {
			temp ="";
		}
		System.out.println(temp);
		
		sopl("immune:");
		temp = line.split("\\)")[0];
		if(temp.contains("immune")) {
			temp = temp.split("immune")[1];
			if(temp.contains("weak to")) {
				temp = temp.split("weak to")[0];
			}
		} else {
			temp ="";
		}
		System.out.println(temp);
		sopl();
		
		if(line.contains("with an attack that does " + damage + " " + attackType + " damage at initiative " + initiative) == false) {
			sopl("ERROR in input");
			exit(2);
		}
		sopl("with an attack that does " + damage + " " + attackType + " damage at initiative " + initiative);
	}
	
	public int getEffectivePower() {
		return units * damage;
	}
	
	public boolean isWeak(String attackType) {
		String temp = line.split("\\)")[0];
		if(temp.contains("weak to")) {
			temp = temp.split("weak to")[1];
			if(temp.contains("immune")) {
				temp = temp.split("immune")[0];
			}
		} else {
			return false;
			
		}
		if(temp.contains(attackType)) {
			return true;
		} else {
			return false;
		}
	}
	public boolean isImmumne(String attackType) {
		String temp = line.split("\\)")[0];
		if(temp.contains("immune")) {
			temp = temp.split("immune")[1];
			if(temp.contains("weak to")) {
				temp = temp.split("weak to")[0];
			}
		} else {
			return false;
			
		}
		if(temp.contains(attackType)) {
			return true;
		} else {
			return false;
		}
	}
	
	
	public void attack(ArrayList<prob24Side> sides, prob24Group defender) {
		
		if(prob24.getName(sides, this).split(" ")[0].equals(prob24.getName(sides, defender).split(" ")[0])) {
			sopl("ERROR: self destruct");
			exit(1);
			
		}
		if(this.attackingIndex < 0) {
			sopl("Bad index!");
			exit(1);
			return;
		}
		
		if(this.units == 0) {
			//TODO: should I just return?
			sopl("No units for attack!");
			exit(1);
			return;
		}
		
		int damage = this.getEffectivePower();
		if(defender.isImmumne(this.attackType)) {
			damage = 0;
		} else if(defender.isWeak(this.attackType)){
			damage *=2;
		}
		
		int numUnits = Math.min(damage / defender.hp, defender.units);
		
	
		if(numUnits != 1) {
			//sopl(prob24.getName(sides, this) + " attacks defending group " + (this.attackingIndex+1) + ", killing " + numUnits + " units");
		} else {
			//sopl(prob24.getName(sides, this) + " attacks defending group " + (this.attackingIndex+1) + ", killing " + numUnits + " unit");
		}
		
		if(defender.units ==0) {
			sopl("aah. Defender already dead");
			exit(1);
		}
		
		defender.units -= numUnits;
		
		//sop(prob24)
		
	}
	
	public static void sop(Object a) {
		System.out.print(a.toString());
	}
	public static void sopl(Object a) {
		System.out.println(a.toString());
	}

	public static void sopl() {
		System.out.println();
	}
	
	public static int pint(String s) {
		if (IsNumber.isNumber(s)) {
			return Integer.parseInt(s);
		} else {
			sop("Error: (" + s + " is not a number");
			return -1;
		}
	}
	
	public static void exit() {
		exit(0);
	}
	public static void exit(int code) {
		sop("Exit with code " + code);
		
		System.exit(code);
	}
}
