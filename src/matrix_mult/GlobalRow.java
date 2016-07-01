package matrix_mult;

public class GlobalRow {
	
	public int row;

	GlobalRow(int r) {
		row = r;
	}
	
	public synchronized void increment() {
		
		row += 1;
		
	}
	
	public synchronized int get() {
		
		return row;
		
	}
}
