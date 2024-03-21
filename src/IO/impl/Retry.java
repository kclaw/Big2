package IO.impl;

public class Retry {
	
	public static Retry of(int times) {
		return new Retry(times);
	}
	
	private int times;
	public Retry(int times) {
		this.times = times;
	}
	int times(){
		return this.times;
	}
}
