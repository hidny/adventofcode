package probs2017;

import java.io.File;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import number.IsNumber;
import utils.Mapping;

public class prob23pseudo {

	public static void main(String[] args) {
		int a = 1;
		int b = 0;
		int c = 0;
		int d = 0;
		int e = 0;
		int f = 0;
		int g = 0;
		int h = 0;
		
		
		b = 84;
		c = b;
		
		//(cancelled)
		
		b = b*100; //5
		
		b += 100000; //6
		
		c = b;
		
		c += 17000;//8
		
		
		
		do {
			f = 1; //9
			d = 2;//10
			
			int sqrtB = (int)Math.sqrt(b);
			
			int prime = 1;
			for(int i=2; i<=sqrtB; i++) {
				if(b % i == 0) {
					prime = 0;
					break;
				}
			}
			
			//f==0?
			if(prime == 0) {//25
				h++;//26
			}
			
			
			//*******
			g = b;//27
			g -=c;//28
			
			
			
			if(b == c ) { // if b == c break
				System.out.println(h);
				break;
			}
			//*******
			b += 17;
			
		} while(true);//It will loop 1000 times
	}
}
