package matrix_mult;


public class MatrixRunnable implements Runnable {
	
	private Matrix a;
	private Matrix b;
	private Matrix c;
	GlobalRow r;
	boolean ok = false;

	MatrixRunnable(Matrix a, Matrix b, Matrix c, GlobalRow r) {
		
		this.a = a;
		this.b = b;
		this.c = c;
		this.r = r;

	}

	public void run() {
		
		while (!ok) {
			
			int rr = r.get();
			
			if (!(rr < b.getRows())) {
				
				ok = true;
				return;
				
			} else {
				
				for (int i = 0; i < a.matrix.length; i += 1) {
					c.matrix[rr][i] = a.rowMult(b, i, rr);
				}
				
				r.increment();
				
			}
			
		}
		
	}

}
