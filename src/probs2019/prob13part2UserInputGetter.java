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

import probs2019after1am.*;

public class prob13part2UserInputGetter implements IntCodeInputReader {

	private Scanner userIn = new Scanner(System.in);
	
	private long maxX = 0;
	private long maxY = 0;
	Hashtable<String, Long> screen;
	
	public prob13part2UserInputGetter(Hashtable<String, Long> screen, long maxX, long maxY) {
		this.screen = screen;
		this.maxX = maxX;
		this.maxY = maxY;
	}
	
	public void refreshDims(long maxX, long maxY) {
		this.maxX = maxX;
		this.maxY = maxY;
	}
	
	
	@Override
	public long getInput() {
	
		printScreen(screen, maxX, maxY);
		
		//String dir = userIn.next();
		String dir = "NO INPUT";
		/*
		try
		{
		    Thread.sleep(40);
		}
		catch(InterruptedException ex)
		{
		    Thread.currentThread().interrupt();
		}
		*/
		if(dir.contains("a")) {
			sopl("left");
			return -1L;
			
		} else if(dir.contains("d")) {
			sopl("right");
			return 1L;
			
		} else if(dir.contains("s")) {
			sopl("straight");
			return 0L;
		} else {
			if(dirballx > 0 && paddlex < ballx) {
				return 1L;
			} else if(dirballx < 0 && paddlex > ballx) {
				return -1L;
			} else {
				return 0L;
			}
		}
	}
	
	
	private int dirballx = -1;
	private int ballx = -1;
	private int paddlex = -1;

	
	public void printScreen(Hashtable<String, Long> screen, long maxX, long maxY) {
		for(int y=0; y<maxY + 2; y++) {
			for(int x=0; x<maxX + 2; x++) {
				if(screen.get(x + "," + y) == null) {
					sop(" ");
				} else if(screen.get(x + "," + y) == prob13part2.EMPTY) {
					sop(" ");
				} else if(screen.get(x + "," + y) == prob13part2.WALL) {
					sop("W");
				} else if(screen.get(x + "," + y) == prob13part2.BLOCK) {
					sop("#");
				} else if(screen.get(x + "," + y) == prob13part2.HORI_PADDLE) {
					
					paddlex = x;
					sop("=");
					
				} else if(screen.get(x + "," + y) == prob13part2.BALL) {
					
					if(x > ballx) {
						dirballx = 1;
					} else if( x < ballx) {
						dirballx = -1;
					} else {
						dirballx = 0;
					}
					ballx = x;
					sop("O");
					
				}
			}
			sopl();
		}
		
		if(screen.get("-1,0") != null) {
			sopl("SCORE: " + screen.get("-1,0"));
		}
		sopl("____");
		
		
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
