package com.szte.recommender.project.recommender;

public class VectorHelper {

	/**
	 * Generates the w1, w2... vector. w1x1 = w(vector)1(row index)x(char between
	 * row and column index)1(column index)
	 * 
	 * @param size        - size of the vector (size will always be n)
	 * @param columnIndex - index of the column
	 * @return generated String array
	 */
	public static String[] generateVariableVector(int size, int columnIndex) {
		String[] result = new String[size];

		for (int i = 0; i < size; i++) {
			result[i] = "w" + i + "x" + columnIndex;
		}

		return result;
	}

	/**
	 * Takes two vectors and multiplies them together
	 * 
	 * @param vector1 - first vector
	 * @param vector2 - second vector
	 * @return the multiplied result vector of the two vectors
	 */
	public static double multiplySameSizeVectors(double[] vector1, double[] vector2) {
		double result = 0;

		for (int i = 0; i < vector1.length; i++) {
			result += vector1[i] * vector2[i];
		}

		return result;
	}

	/**
	 * Multiplies the A matrix with the w vector
	 * 
	 * @param matrix - A matrix
	 * @param w      - w vector
	 * @return A * w, it will be a String vector
	 */
	public static String[] matrixTimesWvector(double[][] matrix, String[] w) {
		String[] result = new String[matrix.length];
		for (int i = 0; i < matrix.length; i++) {
			result[i] = "";
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] == 1) {
					if (!result[i].isEmpty()) {
						result[i] += " + ";
					}
					result[i] += w[j];
				}
			}
			if(result[i].equals("")) {
				result[i] += 0;
			}
		}
		return result;
	}

	/**
	 * Subtracts vectorW from vectorA, result of || aj - A * wj ||
	 * 
	 * @param vectorA - j.-th column of matrix A
	 * @param vectorW - j.-th w vector
	 * @return subtraction of vectorA and vectorW
	 */
	public static String[] columnMinusWVector(double[] vectorA, String[] vectorW) {
		String[] result = new String[vectorA.length];

		for (int i = 0; i < result.length; i++) {
			result[i] = "";

			if (vectorA[i] > 0) {
				result[i] += vectorA[i] + " - ";
			} else {
				result[i] += "- ";
			}

			result[i] += vectorW[i];
			result[i] = result[i].replace('+', '-');
		}
		
		return result;
	}

	/**
	 * Returns the index column of the given matrix
	 * 
	 * @param matrix - A matrix
	 * @param index  - index of the column which we are looking for
	 * @return the A[*][index] column
	 */
	public static double[] getColumn(double[][] matrix, int index) {
		double[] result = new double[matrix.length];

		for (int i = 0; i < result.length; i++) {
			result[i] = matrix[i][index];
		}

		return result;
	}

}
