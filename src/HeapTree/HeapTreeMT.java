package HeapTree;

import java.util.ArrayList;


public class HeapTreeMT {

	public static void main(String args[]) {
		HeapTreeObject array[] = new HeapTreeObject[11];
		HeapTreeMT heapTree = new HeapTreeMT();
		
		for(int i=0; i<array.length; i++) {
			for(int j=0; j<heapTree.size(); j++) {
				System.out.println(heapTree.heap.get(j).getHeapKey() + ":  " + (String)heapTree.heap.get(j).getHeapObject() );
			}
			System.out.println();
			System.out.println();
			
			array[i] = new HeapTreeObject( (4*i) %11, "Hello " + i);
			heapTree.insert(array[i]);
		}
		
		System.out.println("Deleting min:");
		for(int i=0; i<array.length; i++) {

			heapTree.extractMin();
			for(int j=0; j<heapTree.size(); j++) {
				System.out.println(heapTree.heap.get(j).getHeapKey() + ":  " + (String)heapTree.heap.get(j).getHeapObject() );
			}
			System.out.println();
			System.out.println();
			
		}
		
		for(int i=0; i<heapTree.size(); i++) {
			System.out.println(heapTree.heap.get(i).getHeapKey() + ":  " + (String)heapTree.heap.get(i).getHeapObject() );
		}
		
	}
	ArrayList<HeapTreeObject> heap = new ArrayList<HeapTreeObject>();
	
	/*
	 * 
	 * Basic
find-max or find-min: find a maximum item of a max-heap, or a minimum item of a min-heap, respectively (a.k.a. peek)
insert: adding a new key to the heap (a.k.a., push[2])
extract-min [or extract-max]: returns the node of minimum value from a min heap [or maximum value from a max heap] after removing it from the heap (a.k.a., pop[3])
delete-max or delete-min: removing the root node of a max- or min-heap, respectively
replace: pop root and push a new key. More efficient than pop followed by push, since only need to balance once, not

	 */
	public HeapTreeMT() {
		
	}
	
	public Object getMin() {
		return heap.get(0);
	}
	
	public Object extractMin() {
		Object temp = heap.get(0);
		
		heap.set(0, heap.get(heap.size() - 1));
		heap.remove(heap.size() - 1);
		siftUp(0);
		
		return temp;
	}
	
	public void insert(HeapTreeObject object) {
		heap.add(object);
		
		siftDown(heap.size() - 1);
	}
	
	public void siftUp(int parentIndex) {
		int indexToCompare = compareAndSwapTreeNodes(parentIndex);
		
		if(indexToCompare != -1 && heap.get(indexToCompare).getHeapKey() < heap.get(parentIndex).getHeapKey()) {
			swap(parentIndex, indexToCompare);
			siftUp(indexToCompare);
		}
		

	}
	
	public void siftDown(int childIndex) {
		if(childIndex == 0) {
			return;
		}
		int parentIndex = (childIndex+1)/2 - 1;
		
		//Technically overkill, but I don't care :)
		int indexToCompare = compareAndSwapTreeNodes(parentIndex);
		
		if(indexToCompare != -1 && heap.get(indexToCompare).getHeapKey() < heap.get(parentIndex).getHeapKey()) {
			swap(parentIndex, indexToCompare);
			siftDown(parentIndex);
		}
		
	}
	
	private int compareAndSwapTreeNodes(int parentIndex) {
		int index1 = 2*(parentIndex+1) - 1;
		int index2 = 2*(parentIndex+1);
		
		int indexToCompare = -1;
		
		if(heap.size() > index2) {
			if(heap.get(index1).getHeapKey() <= heap.get(index2).getHeapKey()) {
				indexToCompare = index1;
			} else {
				indexToCompare = index2;
			}
		} else if(heap.size() > index1) {
			indexToCompare = index1;
		} else {
			return -1;
		}
		
		
		return indexToCompare;
	}
	
	public void swap(int a, int b) {
		HeapTreeObject temp = heap.get(a);
		heap.set(a,  heap.get(b));
		heap.set(b, temp);
	}
	//sift-up: move a node up in the tree, as long as needed; used to restore heap condition after insertion. Called "sift" because node moves up the tree until it reaches the correct level, as in a sieve.
	//sift-down: move a node down in the tree, similar to sift-up; used to restore heap condition after deletion or replacement.
	
	public int size() {
		return heap.size();
	}
	
	public boolean isEmpty() {
		if(heap.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
}
