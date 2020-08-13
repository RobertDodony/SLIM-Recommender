package com.szte.recommender.project.recommender;

import java.text.DecimalFormat;

public class SimilarityCalculator {

	/**
	 * Calculates the average of the item's ratings.
	 * 
	 * @param matrix - given matrix
	 * @param item   - index of the given item
	 * @return the average of the item's ratings
	 */
	private static double meanRating(double[][] matrix, int item) {
		double result;
		double sumOfRatings = 0;
		int numberOfRatings = 0;

		for (int i = 0; i < matrix[item].length; i++) {
			if (matrix[item][i] < 5) {
				sumOfRatings += matrix[item][i];
				numberOfRatings++;
			}
		}

		result = (double) sumOfRatings / numberOfRatings;

		return result;
	}

	/**
	 * Calculates the difference between the user's rating of the item and the
	 * item's average rating.
	 * 
	 * @param matrix    - given matrix
	 * @param itemIndex - index of the column in the matrix that represents the item
	 * @param row       - given user
	 * @return difference between the users rating of the item and the average
	 *         rating of the given item
	 */
	private static double meanCentered(double[][] matrix, int itemIndex, int row) {
		double result = matrix[row][itemIndex] - meanRating(matrix, row);
		return result;
	}

	/**
	 * Calculates the similarity between two items.
	 * 
	 * @param matrix     - given matrix
	 * @param itemIndex1 - index of the first item
	 * @param itemIndex2 - index of the second item
	 * @return the similarity [-1, 1] between the two items
	 */
	private static double adjustedCousine(double[][] matrix, int itemIndex1, int itemIndex2) {
		double result = 0;
		double numerator = 0;
		double denominator1 = 0;
		double denominator2 = 0;

		for (int i = 0; i < matrix.length; i++) {
			if (matrix[i][itemIndex1] < 5 && matrix[i][itemIndex2] < 5) {
				numerator += meanCentered(matrix, itemIndex1, i) * meanCentered(matrix, itemIndex2, i);
				denominator1 += Math.pow(meanCentered(matrix, itemIndex1, i), 2);
				denominator2 += Math.pow(meanCentered(matrix, itemIndex2, i), 2);
			}
		}
		if (denominator1 == 0) {
			denominator1 = Double.MIN_VALUE;
		}
		if (denominator2 == 0) {
			denominator2 = Double.MIN_VALUE;
		}

		result = numerator / (Math.sqrt(denominator1) * Math.sqrt(denominator2));

		DecimalFormat df = new DecimalFormat("#.000");
		result = Double.valueOf(df.format(result));

		return result;
	}

	/**
	 * Calculates all of the item's similarities compared to the targetItem.
	 * 
	 * @param matrix     - given matrix
	 * @param targetItem - item which we compare the similarities of the other items
	 * @return an array of doubles which contain the similarities
	 */
	public static double[] similarityOfItems(double[][] matrix, int targetItem) {
		double[] result = new double[matrix[0].length];

		for (int i = 0; i < result.length; i++) {
			if (targetItem != i) {
				result[i] = adjustedCousine(matrix, targetItem, i);
			} else {
				result[i] = -2;
			}
		}

		return result;
	}

}
