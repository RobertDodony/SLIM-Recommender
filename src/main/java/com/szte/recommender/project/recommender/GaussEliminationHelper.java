package com.szte.recommender.project.recommender;
import javacalculus.struct.CalcObject;

public class GaussEliminationHelper {

	private static final double EPSILON = 1e-10;

	/**
	 * Collects all the number on the left side of the gaussMatrix, all the columns
	 * besides the last one
	 * 
	 * @param gaussMatrix - given gaussMatrix
	 * @return returns the gaussMatrix without the last column
	 */
	public static double[][] getAforGauss(double[][] gaussMatrix) {
		double[][] A = new double[gaussMatrix.length][gaussMatrix[0].length - 1];

		for (int i = 0; i < gaussMatrix.length; i++) {
			for (int j = 0; j < gaussMatrix[0].length - 1; j++) {
				if (gaussMatrix[i][j] == 0) {
					A[i][j] = 0.0000001;
				} else {
					A[i][j] = gaussMatrix[i][j];
				}
			}
		}
		return A;
	}

	/**
	 * Collects the numbers on the right side of the gaussMatrix, only the last
	 * column
	 * 
	 * @param gaussMatrix - given gaussMatrix
	 * @return last column of the matrix, on the right side
	 */
	public static double[] getBforGauss(double[][] gaussMatrix) {
		double[] B = new double[gaussMatrix.length];
		for (int i = 0; i < gaussMatrix.length; i++) {
			if (gaussMatrix[i][gaussMatrix[0].length - 1] == 0) {
				B[i] = 0.0000001;
			} else {
				B[i] = gaussMatrix[i][gaussMatrix[0].length - 1];
			}
		}
		return B;
	}

	/**
	 * Generates a gauss matrix from the derivative versions
	 * 
	 * @param calcObjects - array of the derivative versions
	 * @param vectorW1
	 * @return a gauss matrix
	 */
	public static double[][] generateGaussMatrix(CalcObject[] calcObjects, String[] vectorW) {
		double[][] gaussMatrix = new double[calcObjects.length][calcObjects.length + 1];
		for (int i = 0; i < gaussMatrix.length; i++) {
			gaussMatrix[i] = DerivationHelper.decodeDerivative(calcObjects[i], vectorW);
		}
		return gaussMatrix;
	}

	/**
	 * Gaussian elimination with partial pivoting
	 * 
	 * @param A - gaussMatrix left side
	 * @param b - gaussMatrix right side
	 * @return the minimized w vector
	 */
	public static double[] calculateGauss(double[][] A, double[] b) {
		int n = b.length;

		for (int p = 0; p < n; p++) {

			// find pivot row and swap
			int max = p;
			for (int i = p + 1; i < n; i++) {
				if (Math.abs(A[i][p]) > Math.abs(A[max][p])) {
					max = i;
				}
			}
			double[] temp = A[p];
			A[p] = A[max];
			A[max] = temp;
			double t = b[p];
			b[p] = b[max];
			b[max] = t;

			// singular or nearly singular
			if (Math.abs(A[p][p]) <= EPSILON) {
				throw new ArithmeticException("Matrix is singular or nearly singular");
			}

			// pivot within A and b
			for (int i = p + 1; i < n; i++) {
				double alpha = A[i][p] / A[p][p];
				b[i] -= alpha * b[p];
				for (int j = p; j < n; j++) {
					A[i][j] -= alpha * A[p][j];
				}
			}
		}

		// back substitution
		double[] x = new double[n];
		for (int i = n - 1; i >= 0; i--) {
			double sum = 0.0;
			for (int j = i + 1; j < n; j++) {
				sum += A[i][j] * x[j];
			}
			x[i] = (b[i] - sum) / A[i][i];
		}
		return x;
	}
}
