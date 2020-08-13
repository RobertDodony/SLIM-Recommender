package com.szte.recommender.project.recommender;
import java.util.Arrays;
import java.util.Comparator;
import com.szte.recommender.project.recommender.IndexValuePair;

import javacalculus.struct.CalcObject;

public class RecommenderHelper {

	private static final double lambda = 3.8;
	private static final double beta = 0.2;
	private static final int numberOfSimilarItems = 17;
	private static final int size = numberOfSimilarItems + 1;
	private static int[] topNindexes;

	public static int getSize() {
		return size;
	}
	
	public static int[] getTopNindexes() {
		return topNindexes;
	}	
	
	/**
	 * Predicts the rating for originalMatrix[row][column] element
	 * 
	 * @param originalMatrix - the original matrix (the local variable matrix will be a reduced matrix of the most similar items)
	 * @param row - user index
	 * @param column - item index
	 * @return predicted rating for the originalMatrix[row][column] element
	 * @throws Exception
	 */
	public static double predictRating(double[][] originalMatrix, int row, int column) throws Exception {
		
		//similarity of items compared to target item
		double[] itemSimilarities = SimilarityCalculator.similarityOfItems(originalMatrix, column);
		
		//topN most similar items
		topNindexes = getBestKIndices(itemSimilarities, size);
		
		//original ratings for the most similar items of the user
		double[] onlySimUserRatings = new double[size];
		onlySimUserRatings[0] = originalMatrix[row][column];
		for(int i = 1; i < onlySimUserRatings.length; i++) {
			onlySimUserRatings[i] = originalMatrix[row][topNindexes[i - 1]];
		}

		//create a smaller matrix to speed up the calculations and improve the data's relevance
		double[][] matrix = generateNewMatrix(originalMatrix, column, topNindexes);
		
		int columnIndexInNewMatrix = 0;

		// vectorW = w vector which contains the unknown elements, this will be
		// w1(column), w2(column) ...
		String[] vectorW = VectorHelper.generateVariableVector(matrix[0].length, columnIndexInNewMatrix);

		// A_vectorW = multiplies the A matrix with the w vector
		String[] A_vectorW = VectorHelper.matrixTimesWvector(matrix, vectorW);

		// column_A = the given column vector of A matrix
		double[] column_A = VectorHelper.getColumn(matrix, columnIndexInNewMatrix);

		// expressionParts = we subtract A_vectorW from column_A, this creates the -
		// w0x6 - w3x6 .... parts
		String[] expressionParts = VectorHelper.columnMinusWVector(column_A, A_vectorW);
		
		// expression = the expressionParts are merged together
		String expression = ExpressionHelper.generateWholeExpression(expressionParts, lambda, beta, vectorW);
		
		// results = takes the expressions and gets the derivative expressions
		CalcObject[] results = DerivationHelper.getCalcObjects(expression, vectorW);

		// gaussMatrix = generates a gauss Matrix from the derivative expressions
		double[][] gaussMatrix = GaussEliminationHelper.generateGaussMatrix(results, vectorW);

		// A = left side of Gauss
		double[][] A = GaussEliminationHelper.getAforGauss(gaussMatrix);

		// b = right side of Gauss
		double[] b = GaussEliminationHelper.getBforGauss(gaussMatrix);

		// result = w vector has been minimalized
		double[] result = GaussEliminationHelper.calculateGauss(A, b);

		// diagonal W has to be 0
		result[columnIndexInNewMatrix] = 0;

		// predictedRating = predict the rating for matrix[row][column] element
		double predictedRating = VectorHelper.multiplySameSizeVectors(onlySimUserRatings, result);

		return predictedRating;
	}

	/**
	 * Orders the array by value, but keeps the original indexes
	 * 
	 * @param array - given array
	 * @param num - how many of the top items do we want
	 * @return topN indexes (size = num)
	 */
	public static int[] getBestKIndices(double[] array, int num) {
        //create sort able array with index and value pair
        IndexValuePair[] pairs = new IndexValuePair[array.length];
        for (int i = 0; i < array.length; i++) {
            pairs[i] = new IndexValuePair(i, array[i]);
        }

        //sort
        Arrays.sort(pairs, new Comparator<IndexValuePair>() {
            public int compare(IndexValuePair o1, IndexValuePair o2) {
                return Double.compare(o2.value, o1.value);
            }
        });

        //extract the indices
        int[] result = new int[num];
        for (int i = 0; i < num; i++) {
            result[i] = pairs[i].index;
        }
        return result;
    }
	
	/**
	 * Generates a smaller matrix than the original, based on the most similar items compared to the target item
	 * 
	 * @param oldMatrix - old matrix
	 * @param targetItemIndex - target item's index
	 * @param mostSimilarItemsIndexes - indexes of the most similar items compared to the target item
	 * @return
	 */
	private static double[][] generateNewMatrix(double[][] oldMatrix, int targetItemIndex, int[] mostSimilarItemsIndexes) {
		int oldMatrixLength = oldMatrix.length;
		int newMatrixNumberOfColumns = mostSimilarItemsIndexes.length + 1;
		int[] requiredColumns = new int[newMatrixNumberOfColumns];
		double[][] newMatrix = new double[oldMatrixLength][newMatrixNumberOfColumns];
		
		requiredColumns[0] = targetItemIndex;
		for(int i = 1; i < newMatrixNumberOfColumns; i++) {
			requiredColumns[i] = mostSimilarItemsIndexes[i - 1];
		}
		
		for(int i = 0; i < newMatrixNumberOfColumns; i++) {
			for(int j = 0; j < oldMatrixLength; j++) {
				newMatrix[j][i] = oldMatrix[j][requiredColumns[i]];
			}
		}
		
		
		return newMatrix;
	}

}
