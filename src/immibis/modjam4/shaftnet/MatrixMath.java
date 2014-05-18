package immibis.modjam4.shaftnet;

public class MatrixMath {
	
	public static class SingularMatrixException extends Exception {}

	/**
	 * Converts the matrix to reduced row echelon form (using Gauss-Jordan Reduction)
	 * The matrix is indexed as [row][col]
	 */
	public static void toReducedRowEchelonForm(double[][] matrix) throws SingularMatrixException {
		int ncols = matrix[0].length, nrows = matrix.length;
		for(int col = 0; col < ncols; col++) {
			int maxLeadingValueRow = -1;
			double maxLeadingValue = Double.NEGATIVE_INFINITY;
			for(int row = 0; row < nrows; row++) {
				double leadingValue = Math.abs(matrix[row][col]);
				if(leadingValue > maxLeadingValue) {
					maxLeadingValue = leadingValue;
					maxLeadingValueRow = col;
				}
			}
			if(maxLeadingValueRow == -1)
				throw new AssertionError();
			if(maxLeadingValue == 0)
				throw new SingularMatrixException();
			
			swapRows(matrix, col, maxLeadingValueRow);
			
			for(int rowBelow = col+1; rowBelow < nrows; rowBelow++) {
				// row[rowBelow] += x*row[col]
				double x = -matrix[rowBelow][col]/matrix[col][col];
				for(int col2 = 0; col2 < ncols; col2++)
					matrix[rowBelow][col2] += x*matrix[col][col2];
			}
		}
	}

	private static void swapRows(double[][] matrix, int a, int b) {
		double[] temp = matrix[a];
		matrix[a] = matrix[b];
		matrix[b] = temp;
	}

}
