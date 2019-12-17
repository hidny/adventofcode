package utils;

import java.util.ArrayList;

import probs2019.prob2ob;

public class Sort {
	
	public static void main(String args[]) {
		double a[] = new double[5];
		a[0] = 3.0;
		a[1] = 2.5;
		a[2] = 5.1;
		a[3] = 1.2;
		a[4] = 4.1;
		
		double b[] = sort(a);
		for(int i=0; i<b.length; i++) {
			System.out.println(b[i]);
		}
		
	}
	
	
	public static String[] sort(String a[] ) {
		return (String[])quickSort(a);
	}
	
	public static double[] sort(double a[]) {
		Double array[] = new Double[a.length];
		for(int i=0; i<array.length; i++) {
			array[i] = a[i];
		}
		
		array = (Double[])quickSort(array);
		
		for(int i=0; i<array.length; i++) {
			a[i] = array[i] ;
		}
		
		return a;
	}
	
	public static long[] sort(long a[]) {
		Long array[] = new Long[a.length];
		for(int i=0; i<array.length; i++) {
			array[i] = a[i];
		}
		
		array = (Long[])quickSort(array);
		
		for(int i=0; i<array.length; i++) {
			a[i] = array[i] ;
		}
		
		return a;
	}
	
	public static int[] sort(int a[]) {
		Integer array[] = new Integer[a.length];
		for(int i=0; i<array.length; i++) {
			array[i] = a[i];
		}
		
		array = (Integer[])quickSort(array);
		
		for(int i=0; i<array.length; i++) {
			a[i] = array[i] ;
		}
		
		return a;
	}
	
	public static String sort(String a) {
		char charArray[] = a.toCharArray();
		char temp2;

		String retS = "";
		for(int i=0; i<charArray.length; i++) {
			for(int j=i+1; j<charArray.length; j++) {
				if(charArray[i]<charArray[j]) {
					temp2 = charArray[i];
					charArray[i] = charArray[j];
					charArray[j] = temp2;
				}
			}
			retS += charArray[i];
		}
		return retS;
		
	}

	//pre: Array list must be Comparable for this to work
	//It also returns an object array...
	public static Object[] sortList(ArrayList<Comparable> a) {
		Comparable aArray[] = new Comparable[a.size()];
		
		for(int i=0; i<aArray.length; i++) {
			aArray[i] = a.get(i);
		}
		
		return Sort.quickSort(aArray);
	}
	
	public static Object[] quickSort(Comparable a[]) {
		return quickSort(a, 0, a.length);
	}
	//TODO: Test this (I only used it for problem 155 so far (and it worked))
	public static Object[] quickSort(Comparable a[], int length) {
		return quickSort(a, 0, length);
	}
	
	private static Object[] quickSort(Comparable a[], int start, int end) {
		int randIndex = start + (int)((end - start) * Math.random());
		if(end - start > 100000) {
			System.out.println(start + " to " + end);
		}
		//Base case: how to sort an array length 1:
		if(end - start <= 1) {
			return a;
		}
		
		swap(a, start, randIndex);
		
		//a[start] is now the pivot...
		
		int leftIndex = start + 1;
		
		int rightIndex = end - 1;
		
		
		while(leftIndex<rightIndex) {
			if((rightIndex - leftIndex) % 100000 == 0) {
				System.out.println(rightIndex - leftIndex);
			}
			if(a[start].compareTo(a[leftIndex]) < 0) {
				swap(a, leftIndex, rightIndex);
				rightIndex--;
			} else {
				leftIndex++;
			}
		}
		
		int pivot;
		if(a[leftIndex] == null || a[start].compareTo(a[leftIndex]) < 0) {
			swap(a, start, leftIndex-1);
			pivot = leftIndex-1;
		} else {
			swap(a, start, leftIndex);
			pivot = leftIndex;
		}
		
		if(pivot == end) {
			System.out.println("WTF!!! pivot = end");
			System.exit(0);
		}
		
		//Hack code to efficiently deal with tables that have many duplicate entries:
		int leftPivot = pivot;
		int rightPivot = pivot;
		while(leftPivot>start && a[leftPivot].compareTo(a[leftPivot - 1]) == 0) {
			leftPivot--;
		}
		while(rightPivot + 1 < end && a[rightPivot].compareTo(a[rightPivot + 1]) == 0) {
			rightPivot++;
		}
		//end hack code.
		
		quickSort(a, start, leftPivot);
		quickSort(a, rightPivot+1, end);
		
		return a;
	}
	
	private static void swap(Comparable a[], int i, int j) {
		Comparable temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	//TODO
	/*
	public static String sort(ArrayList array) {
		char charArray[] = a.toCharArray();
		char temp2;

		String retS = "";
		for(int i=0; i<charArray.length; i++) {
			for(int j=i+1; j<charArray.length; j++) {
				if(charArray[i]<charArray[j]) {
					temp2 = charArray[i];
					charArray[i] = charArray[j];
					charArray[j] = temp2;
				}
			}
			retS += charArray[i];
		}
		return retS;
		
	}*/
	
	
}
