package probs2019;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import utils.Sort;

import number.IsNumber;

public class prob0testing {

	public static void main(String args[]) {

		
		//Sort comparable
		ArrayList<Comparable> list = new ArrayList<Comparable>();
		
		list.add(new prob2ob(4));
		list.add(new prob2ob(3));
		list.add(new prob2ob(5));
		list.add(new prob2ob(2));
		list.add(new prob2ob(1));
		
		Object sorted[] = utils.Sort.sortList(list);
		for(int i=0; i<sorted.length; i++) {
			sopl(((prob2ob)sorted[i]).num);
		}
		
		ArrayList<Comparable> list2 = new ArrayList<Comparable>();
		
		list2.add(new Integer(4));
		list2.add(new Integer(3));
		list2.add(new Integer(5));
		list2.add(new Integer(2));
		list2.add(new Integer(1));
		
		Object sorted2[] = utils.Sort.sortList(list2);
		for(int i=0; i<sorted2.length; i++) {
			sopl(sorted2[i]);
		}
		//End Sort comparable
		
		//Sort hash:
		Hashtable<Integer, Object> test = new Hashtable<Integer, Object>();
		
		test.put(1, "test1");
		test.put(3, "test3");
		test.put(4, "test4");
		test.put(2, "test2");
		test.put(5, "test5");
		
		Set a = test.keySet();
		
		Iterator iter= a.iterator();
		
		ArrayList<Comparable> listNum = new ArrayList<Comparable>();
		
		while(iter.hasNext()) {
			listNum.add((Comparable)iter.next());
		}
		
		Object sortedList[]  = utils.Sort.sortList(listNum);
		
		for(int i=0; i<sortedList.length; i++) {
			sopl(sortedList[i]);
			sopl(test.get(sortedList[i]));
		}
		//End sort hash
		
		
		
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
