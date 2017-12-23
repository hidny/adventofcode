package probs2017;

import java.io.File;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import number.IsNumber;
import utils.Mapping;

public class prob23b {

	public static void main(String[] args) {
		
		try {
			int b = 108400;
			int c = 125400;
			
			int h=0;
			//I missed the = sign the first try...
			for(int i=0; i<=1000; i++) {
				if(isPrime(b)) {
					
				} else {
					h++;
				}
				b += 17;
			}
			
			System.out.println("b = " + b);
			System.out.println("c = " + c);
			System.out.println("ANSWER = " + h);
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	public static boolean isPrime(long num) {
		if(num<=1) {
			return false;
		}
		
		int sqrt = (int)Math.sqrt(num);
		for(int i=2; i<=sqrt ; i++) {
			if(num%i == 0) {
				return false;
			}
		}
		
		return true;
	}
	
	public static void sopl() {
		sopl("");
	}
	
	public static void sopl(String out) {
		System.out.println(out);
	}

	public static void sop(String out) {
		System.out.print(out);
	}
	
	public static int getReg(Mapping dict, String reg) {
		if(IsNumber.isNumber(reg)) {
			return Integer.parseInt(reg);
		} else {
			return (int)dict.get(reg);
		}
	}
}
