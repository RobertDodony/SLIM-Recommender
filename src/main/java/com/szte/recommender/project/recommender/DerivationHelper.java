package com.szte.recommender.project.recommender;
import javacalculus.core.CALC;
import javacalculus.core.CalcParser;
import javacalculus.evaluator.CalcSUB;
import javacalculus.struct.CalcDouble;
import javacalculus.struct.CalcObject;
import javacalculus.struct.CalcSymbol;

public class DerivationHelper {

	/**
	 * Get's all the derivatives of the expression using different variables
	 * 
	 * @param expression - expression of which we want the derivative version
	 * @param vectorW    - array of variables which we want to use for getting the
	 *                   derivative version of the expression
	 * @return array of derivative expressions
	 * @throws Exception
	 */
	public static CalcObject[] getCalcObjects(String expression, String[] vectorW) throws Exception {
		CalcObject[] results = new CalcObject[vectorW.length];
		for (int i = 0; i < vectorW.length; i++) {
			results[i] = getDerivative(expression, vectorW[i]);
		}
		return results;
	}

	/**
	 * Calculates the derivative of the given expression using the given variable
	 * 
	 * @param expression - expression of which we want the derivative version
	 * @param variable   - variable which we use to get the derivative
	 * @return return the derivative object
	 * @throws Exception
	 */
	private static CalcObject getDerivative(String expression, String variable) throws Exception {
		String command = "DIFF(" + expression + ", " + variable + ")";
		CalcParser parser = new CalcParser();
		CalcObject parsed = parser.parse(command);
		CalcObject result = parsed.evaluate();

		result = subst(result, "a1", 0.0);
		result = subst(result, "a2", 10.0);
		result = CALC.SYM_EVAL(result);

		return result;
	}

	private static CalcObject subst(CalcObject input, String var, double number) {
		CalcSymbol symbol = new CalcSymbol(var);
		CalcDouble value = new CalcDouble(number);
		return CalcSUB.numericSubstitute(input, symbol, value);
	}

	/**
	 * Splits the String derivative version into pieces and gets the individual
	 * numeric values of the different unknown variables
	 * 
	 * @param calc    - derivative version of the expression
	 * @param vectorW - an array of the unknown variables in the given expression
	 * @return array of the numeric values in front of the unknown variables in the
	 *         expression
	 */
	public static double[] decodeDerivative(CalcObject calc, String[] vectorW) {
		String codedString = calc.toString();
		String[] pieces = codedString.split("MULTIPLY");
		double[] vector = new double[vectorW.length + 1];
		for (int i = 0; i < pieces.length; i++) {
			if (i == 0) {
				String[] a = pieces[i].split("\\(");
				if (a.length > 1) {
					vector[vector.length - 1] = 0.5 * (-1 * Double.parseDouble(a[1].substring(0, a[1].length() - 1)));
				} else {
					vector[vector.length - 1] = 0;
				}
			} else {
				for (int j = 0; j < vectorW.length; j++) {
					if (pieces[i].contains(vectorW[j])) {
						String[] a = pieces[i].split(",");
						vector[j] = 0.5 * Double.parseDouble(a[0].substring(1));
					}
				}
			}
		}

		return vector;
	}

}
