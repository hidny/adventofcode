package practice;

import java.util.Queue;
import java.util.*;

public class ToolTrial {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		LinkedList queue = new LinkedList();
		Stack stack = new Stack();
		HashSet set = new HashSet();
		
		queue.add(3);
		queue.add(4);
		queue.add(5);
		queue.add(6.6);
		
	
		System.out.println(queue.pop());
		System.out.println(queue.pop());
		System.out.println(queue.pop());
		System.out.println(queue.pop());
		queue.add(6.7);
		System.out.println(queue.pop());
		
		stack.push(1);
		stack.push(2.1);
		stack.push("3");
		
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		
		set.add(1);
		set.add("2");
		set.add(1);
		set.iterator();
		
	}

}
