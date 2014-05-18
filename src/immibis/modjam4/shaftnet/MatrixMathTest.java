package immibis.modjam4.shaftnet;

import java.util.Arrays;

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
		System.out.println(Arrays.toString(m[0]));
		System.out.println(Arrays.toString(m[1]));
		System.out.println(Arrays.toString(m[2]));
		assertArrayEquals(new double[][] {
			{ 1, 0, 0, -0.5},
			{ 0, 1, 0, -2},
			{ 0, 0, 1, -1},
		}, m);
		
		
		m = new double[][] {
			{ 1, 0, 0, 0},
			{ 0, 1, 1, 0},
			{ 0, 0, 0, 1}
		};
		try {
			MatrixMath.toReducedRowEchelonForm(m);
			fail("Expected SingularMatrixException");
		} catch(SingularMatrixException e) {
		}
	}
}
