package com.szte.recommender.project.recommender;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

public class ExpressionHelper {

	/**
	 * Merges all the expression in to one big expression
	 * 
	 * @param vector - array of the expressions which are needed to be squared
	 * @param lambda - if lambda is not 0, then creates the ridge regression part
	 * @param beta - if beta is not 0, then creates the lasso regression part
	 * @param vectorW - array of the unknown variables
	 * @return all expressions merged together
	 */
	public static String generateWholeExpression(String[] vector, double lambda, double beta, String[] vectorW) {
		String result = "";

		String[] expressions = new String[vector.length];
		for (int i = 0; i < expressions.length; i++) {
			expressions[i] = squareSingleExpression(vector[i]);
			result += expressions[i];
			if (i < expressions.length - 1) {
				result += " + ";
			}
		}

		if (lambda != 0) {
			result += getRidgeRegressionPart(lambda, vectorW);
		}

		if (beta != 0) {
			result += getLassoRegressionPart(beta, vectorW);
		}

		return result;
	}

	/**
	 * Generates the square expression of a given string. String has unknown
	 * variables so it needs to be stored as a String
	 * 
	 * @param input - string to square
	 * @return square expression of input(String)
	 */
	private static String squareSingleExpression(String input) {
		String result = "";
		input = input.replaceAll("\\s", "");
		String[] vars = input.split("-");

		if (vars.length == 2) {
			return vars[1] + "^2";
		}

		if (StringUtils.isBlank(vars[0])) {
			for (int i = 1; i < vars.length; i++) {
				for (int j = 1; j < vars.length; j++) {
					if (vars[i].equals(vars[j])) {
						result += vars[i] + "^2";
					} else if (NumberUtils.isNumber(vars[i])) {
						String tmp;
						if (result.charAt(result.length() - 2) == '+') {
							tmp = result.substring(0, result.length() - 2);
						} else {
							tmp = result;
						}
						tmp += " - " + vars[i] + " * " + vars[j];
						result = tmp;
					} else if (NumberUtils.isNumber(vars[j])) {
						String tmp;
						if (result.charAt(result.length() - 2) == '+') {
							tmp = result.substring(0, result.length() - 2);
						} else {
							tmp = result;
						}
						tmp += " - " + vars[j] + " * " + vars[i];
						result = tmp;
					} else {
						result += vars[i] + " * " + vars[j];
					}
					if (!(i + 1 == vars.length && j + 1 == vars.length)) {
						result += " + ";
					}
				}
			}
		} else {
			for (int i = 0; i < vars.length; i++) {
				for (int j = 0; j < vars.length; j++) {
					if (vars[i].equals(vars[j])) {
						result += vars[i] + "^2";
					} else if (NumberUtils.isNumber(vars[i])) {
						String tmp;
						if (result.charAt(result.length() - 2) == '+') {
							tmp = result.substring(0, result.length() - 2);
						} else {
							tmp = result;
						}
						tmp += " - " + vars[i] + " * " + vars[j];
						result = tmp;
					} else if (NumberUtils.isNumber(vars[j])) {
						String tmp;
						if (result.charAt(result.length() - 2) == '+') {
							tmp = result.substring(0, result.length() - 2);
						} else {
							tmp = result;
						}
						tmp += " - " + vars[j] + " * " + vars[i];
						result = tmp;
					} else {
						result += vars[i] + " * " + vars[j];
					}
					if (!(i + 1 == vars.length && j + 1 == vars.length)) {
						result += " + ";
					}
				}
			}
		}

		return result;
	}

	/**
	 * Generates the ridge regression part of an expression
	 * 
	 * @param lambda  - regularization parameter
	 * @param vectorW - w vector
	 * @return lambda * (squares of the vectorW elements)
	 */
	private static String getRidgeRegressionPart(double lambda, String[] vectorW) {
		String result = " + ";
		int size = vectorW.length;

		for (int i = 0; i < size; i++) {
			result += "" + lambda + " * " + vectorW[i] + "^2";
			if (i < size - 1) {
				result += " + ";
			}
		}

		return result;
	}

	/**
	 * Generates the lasso regression part of an expression
	 * 
	 * @param beta    - regularization parameter
	 * @param vectorW - w vector
	 * @return beta * (vectorW elements)
	 */
	private static String getLassoRegressionPart(double beta, String[] vectorW) {
		String result = " + ";
		int size = vectorW.length;

		for (int i = 0; i < size; i++) {
			result += "" + beta + " * " + vectorW[i];
			if (i < size - 1) {
				result += " + ";
			}
		}

		return result;
	}
}
