package probs2019;
import java.io.File;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import number.IsNumber;
import utils.Mapping;
import utils.Sort;

public class prob12Particle {

	public int vel[] = new int[3];
	
	public int pos[] = new int[3];
	
	public prob12Particle(int x, int y, int z) {
		super();
		pos[0] = x;
		pos[1] = y;
		pos[2] = z;

		vel[0] = 0;
		vel[1] = 0;
		vel[2] = 0;
	}
	
	public int energy() {
		int pot = 0;
		int kin = 0;
		for(int i=0; i<pos.length; i++) {
			pot +=  Math.abs(pos[i]);
			kin +=  Math.abs(vel[i]);
		}
		
		return pot *kin;
	}
	
	public String toString() {
		return "pos=<x=" + pos[0] + ", y=  "+ pos[1] +", z= "+ pos[2] +">, vel=<x= " + vel[0] +", y= " + vel[1] + ", z= " + vel[2] + ">";
	}
	
}
