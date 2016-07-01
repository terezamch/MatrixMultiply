package matrix_mult;

import java.util.Random;

public class Matrix {
	private int rows;
	private int columns;
	public double matrix[][];

	Matrix(int row, int col) {
		this.rows = row;
		this.columns = col;
		matrix = new double[this.rows][this.columns];
	}

	private void swapDimentions() {
		int tmp = this.rows;
		this.rows = this.columns;
		this.columns = tmp;
	}

	public void insertingNumbers() {
		Random randomGeneration = new Random();
		for (int i = 0; i < rows; i +=1)
			for (int j = 0; j < columns; j += 1)
				matrix[i][j] = randomGeneration.nextDouble();
	}

	public void transpose() {
		swapDimentions();
		double tmp[][] = new double[this.rows][this.columns];
		for (int i = 0; i < this.rows; i += 1)
			for (int j = 0; j < this.columns; j += 1)
				tmp[i][j] = matrix[j][i];
		matrix = tmp;
		tmp = null;
	}

	public int getColumns() {
		return this.columns;
	}

	public int getRows() {
		return this.rows;
	}

	public double rowMult(Matrix b, int lidx, int ridx) {
		double result = 0;
		for (int i = 0; i < matrix[lidx].length; i += 1)
			result += this.matrix[lidx][i] * b.matrix[ridx][i];
		return result;
	}

}
