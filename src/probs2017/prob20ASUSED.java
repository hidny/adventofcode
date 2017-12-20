package probs2017;

import java.io.File;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import utils.Mapping;

public class prob20ASUSED {

	public static void main(String[] args) {
		Scanner in;
		try {
			 in = new Scanner(new File("in2017/prob2017in20.txt"));
			
			 int count = 0;
			boolean part2 = true;
			String line = "";

			LinkedList queue = new LinkedList();
			Stack stack = new Stack();
			HashSet set = new HashSet();
			
			Mapping dict = new Mapping();
			ArrayList <String>lines = new ArrayList<String>();
			
			ArrayList <Integer> list = new ArrayList<Integer>();
			
			
			ArrayList <prob20Particle> particles = new ArrayList<prob20Particle>();
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				lines.add(line);
				String token[] = line.split(",");
				
				particles.add(new prob20Particle(line));
				
			}
			
			//ug
			boolean collision[] = new boolean[particles.size()];
			
			for(int j=0; j<particles.size(); j++) {
				for(int k=j+1; k<particles.size(); k++) {
					if(prob20Particle.checkCollision(particles.get(j), particles.get(k))) {
						collision[k] = true;
						collision[j] = true;
					}
				}
			}
			
			for(int j= particles.size() - 1; j>=0;j--) {
				if(collision[j]) {
					particles.remove(j);
				}
			}
			//
			
			for(int i=0; i<1000000; i++) {
				for(int j=0; j<particles.size(); j++) {

					//System.out.println(j + ": " + particles.get(j).dist());
					particles.get(j).tick();
				}
				

				collision = new boolean[particles.size()];
				
				for(int j=0; j<particles.size(); j++) {
					for(int k=j+1; k<particles.size(); k++) {
						if(prob20Particle.checkCollision(particles.get(j), particles.get(k))) {
							collision[k] = true;
							collision[j] = true;
						}
					}
				}
				
				for(int j= particles.size() - 1; j>=0;j--) {
					if(collision[j]) {
						particles.remove(j);
					}
				}
				
			
				if(part2 == false) {
					int answer = 0;
					
					for(int j=0; j<particles.size(); j++) {
						if(particles.get(answer).dist() > particles.get(j).dist()) {
							answer = j;
						}
					}
					
					System.out.println("Answer: " + answer);
				} else {
					System.out.println("Answer: " + particles.size());
				}
			
				
			}
			
			
			if(part2 == false) {
				int answer = 0;
				
				for(int j=0; j<particles.size(); j++) {
					if(particles.get(answer).dist() > particles.get(j).dist()) {
						answer = j;
					}
				}
				
				System.out.println("Answer: " + answer);
			} else {
				System.out.println("Answer: " + particles.size());
			}
		
			
			in.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	

}
