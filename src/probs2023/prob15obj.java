package probs2023;

public class prob15obj {

	String label;
	
	int boxNum;
	
	int focalLength;
	
	//TODO: start at 1
	
	public long getNum(int slotIndex) {
		//
		return (boxNum + 1) * (slotIndex + 1) * focalLength;
	}

	public prob15obj(String label, int boxNum, int focalLength) {
		super();
		this.label = label;
		this.boxNum = boxNum;
		this.focalLength = focalLength;
	}
	
	
}
