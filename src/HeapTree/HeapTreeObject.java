package HeapTree;

public class HeapTreeObject {

	private double heapKey;
	
	private Object heapObject;

	public HeapTreeObject(double heapKey, Object heapObject) {
		this.heapKey = heapKey;
		this.heapObject = heapObject;
	}

	public double getHeapKey() {
		return heapKey;
	}

	public Object getHeapObject() {
		return heapObject;
	}
	
	
}
