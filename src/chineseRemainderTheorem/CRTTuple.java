package chineseRemainderTheorem;

public class CRTTuple {

	//x = num (modulo mod)
	private long num;
	private long mod;
	public long getNum() {
		return num;
	}
	public long getMod() {
		return mod;
	}
	
	public CRTTuple(long num, long mod) {
		this.num = num % mod;
		this.mod = mod;
		if(this.num < 0) {
			this.num += this.mod;
		}
	}
	
	public String toString() {
		return "x = "+ this.num + " (mod " + this.mod + ")";
	}
	
	public boolean test(long test) {
		if(test % this.mod == this.num) {
			return true;
		} else {
			return false;
		}
	}
}
