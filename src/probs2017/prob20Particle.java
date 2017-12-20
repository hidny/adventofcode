package probs2017;

import java.io.File;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import utils.Mapping;

public class prob20Particle {
	private long pos[] = new long[3];
	
	private long vel[] = new long[3];
	
	private long acc[] = new long[3];
	
	public prob20Particle(String line) {
		int startPos = line.indexOf('<');
		int endPos = line.indexOf('>');
		
		String pos = line.substring(startPos+1, endPos);
		String token[] = pos.split(",");
		for(int i=0; i<token.length; i++) {
			this.pos[i] = Integer.parseInt(token[i].trim());
		}
		
		//Ugly copy/paste code:
		line = line.substring(endPos + 1);
		
		startPos = line.indexOf('<');
		endPos = line.indexOf('>');
		
		pos = line.substring(startPos+1, endPos);
		token = pos.split(",");
		for(int i=0; i<token.length; i++) {
			this.vel[i] = Integer.parseInt(token[i].trim());
		}
		
		//Ugly copy/paste code:
		line = line.substring(endPos + 1);
		
		startPos = line.indexOf('<');
		endPos = line.indexOf('>');
		
		pos = line.substring(startPos+1, endPos);
		token = pos.split(",");
		for(int i=0; i<token.length; i++) {
			this.acc[i] = Integer.parseInt(token[i].trim());
		}
		
	}
	
	public long dist() {
		long ret = 0;
		for(int i=0; i<pos.length; i++) {
			ret += Math.abs(pos[i]);
		}
		return ret;
	}
	
	private void applyAcc() {
		for(int i=0; i<acc.length; i++) {
			vel[i] += acc[i];
		}
	}
	
	private void applyVel() {
		for(int i=0; i<vel.length; i++) {
			pos[i] += vel[i];
		}
	}
	
	public void tick() {
		applyAcc();
		applyVel();
		
	}
	
	public static boolean checkCollision(prob20Particle a, prob20Particle b) {
		for(int i=0; i<a.pos.length; i++) {
			if(a.pos[i] != b.pos[i]) {
				return false;
			}
		}
		
		return true;
	}

}
