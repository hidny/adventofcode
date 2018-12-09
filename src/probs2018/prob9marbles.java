package probs2018;

public class prob9marbles {

	prob9marbles prev;
	prob9marbles next;
	
	int number;
	
	prob9marbles(int number) {
		this.number = number;
		this.next = this;
		this.prev = this;
	}
	
	public void removeNext() {
		next = next.next;
		next.prev = this;
	}
	
	public void addNext(int number) {
		prob9marbles add = new prob9marbles(number);
		prob9marbles tmp = this.next;
		
		add.next = this.next;
		add.prev = this;
		
		this.next = add;
		this.next.next.prev = add;
	}
}
