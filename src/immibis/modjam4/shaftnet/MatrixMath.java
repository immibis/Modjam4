package immibis.modjam4.shaftnet;

public class MatrixMath {
	
	public static class SingularMatrixException extends Exception {}

	/**
	 * Converts the matrix to reduced row echelon form (using Gauss-Jordan Reduction)
	 * The matrix is indexed as [row][col]
	 */
	public static void toReducedRowEchelonForm(double[][] matrix) throws SingularMatrixException {
		int ncols = matrix[0].length, nrows = matrix.length;
		for(int col = 0; col < ncols && col < nrows; col++) {
			int maxLeadingValueRow = -1;
			double maxLeadingValue = Double.NEGATIVE_INFINITY;
			for(int row = col; row < nrows; row++) {
				double leadingValue = Math.abs(matrix[row][col]);
				if(Double.isInfinite(leadingValue) || Double.isNaN(leadingValue))
					leadingValue = leadingValue;
				if(leadingValue > maxLeadingValue) {
					maxLeadingValue = leadingValue;
					maxLeadingValueRow = col;
				}
			}
			if(maxLeadingValueRow == -1) {
				//throw new AssertionError();
				
				// at least don't crash the client
				for(int x = 0; x < nrows; x++)
					for(int y = 0; y < ncols; y++)
						matrix[x][y] = 0;
				return;
			}
			if(maxLeadingValue == 0)
				throw new SingularMatrixException();
			
			swapRows(matrix, col, maxLeadingValueRow);
			
			double multiplier = 1.0 / matrix[col][col];
			// row[col] *= multiplier
			for(int col2 = 0; col2 < ncols; col2++)
				matrix[col][col2] *= multiplier;
			
			for(int rowBelow = col+1; rowBelow < nrows; rowBelow++) {
				// row[rowBelow] += x*row[col]
				double x = -matrix[rowBelow][col]/matrix[col][col];
				for(int col2 = 0; col2 < ncols; col2++)
					matrix[rowBelow][col2] += x*matrix[col][col2];
			}
		}
		
		for(int col = Math.min(ncols, nrows)-1; col >= 0; col--) {
			if(matrix[col][col] != 1)
				throw new AssertionError();
			for(int rowAbove = col-1; rowAbove >= 0; rowAbove--) {
				double x = -matrix[rowAbove][col];
				// row[rowAbove] += row[col]*x
				for(int col2 = 0; col2 < ncols; col2++)
					matrix[rowAbove][col2] += matrix[col][col2]*x;
			}
		}
		
		for(int x = 0; x < nrows; x++)
			for(int y = 0; y < ncols; y++)
				if(matrix[x][y] == -0.0)
					matrix[x][y] = 0.0;
	}

	private static void swapRows(double[][] matrix, int a, int b) {
		double[] temp = matrix[a];
		matrix[a] = matrix[b];
		matrix[b] = temp;
	}

}
