package immibis.modjam4.shaftnet;

import immibis.modjam4.shaftnet.MatrixMath.SingularMatrixException;

import org.junit.Test;

import static org.junit.Assert.*;

public class MatrixMathTest {
	@Test
	public void testRREF() throws SingularMatrixException {
		double[][] m = new double[][] {
			{ 2, 0,-1, 0},
			{ 0,-1, 2, 0},
			{ 0,-1, 0, 2}
		};
		MatrixMath.toReducedRowEchelonForm(m);
		assertArrayEquals(new double[][] {
			{ 1, 0, 0, -0.5},
			{ 0, 1, 0, -2},
			{ 0, 0, 1, -1},
		}, m);
	}
}
