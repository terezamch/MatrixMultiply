package matrix_mult;

import java.util.Calendar;

public class TaskRunner {


	public static void main(String[] args) {
		// Checking for arguments and printing the input methods
		if(args.length < 6){
			System.out.println("Input -> -m <dimension> -n <dimension> -k <dimension>");
			System.out.println("options: ");
			System.out.println("-threads <number of threads>  -> change the number of threads; default 1;");
			System.out.println("-q -> quiet mode");
			System.exit(1);
		}
		// Dimensions of the matrices
		int m =new Integer(args[1]);
		int n =new Integer(args[3]);
		int k =new Integer(args[5]);
		// Number of threads
		int numberOfThreads = 1;
		boolean quiet = false;
		if(args.length > 6){
			if(args.length == 8){
				if(args[6].compareTo("-threads") == 0)
					numberOfThreads = new Integer(args[7]);
				else
					quiet = true;
			} else {
				if(args[6].compareTo("-threads") == 0)
					numberOfThreads = new Integer(args[7]);
				else
					numberOfThreads = new Integer(args[8]);
					quiet = true;
			}
		}
		if(numberOfThreads < 1)
			numberOfThreads = 1;
		long begin, end;
		Matrix a = new Matrix(m, n);
		Matrix b = new Matrix(n, k);
		Matrix c = new Matrix(k, m);
		a.insertingNumbers();
		b.insertingNumbers();
		GlobalRow rw = new GlobalRow(0);

		Thread tr[] = new Thread[numberOfThreads];
		begin = Calendar.getInstance().getTimeInMillis();
		for (int i = 0; i < numberOfThreads; i += 1) {
			MatrixRunnable r = new MatrixRunnable(a, b, c, rw);
			tr[i] = new Thread(r);
			tr[i].start();
		}
		for (int i = 0; i < numberOfThreads; i += 1) {
			try {
				tr[i].join();
			} catch (InterruptedException e) {
				System.out.println("Problem with joining Threads#" + i);
			}
		}
		end = Calendar.getInstance().getTimeInMillis();
		c.transpose();
		
		if(quiet){
			System.out.println();
			System.out.println(((end-begin)/1000.0)+"s");
		} else {
			System.out.println("Calculation time: " + ((end-begin)/1000.0)+"s");
			System.out.println("Threads used :" + numberOfThreads);
			System.out.println("Dimensions of the matrices: A[" + m + ", " + n + "]; B[" + n + "," + k + "]; C[" + m + ", " + k + "]");
		}
	}

}
