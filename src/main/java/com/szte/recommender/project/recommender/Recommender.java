package com.szte.recommender.project.recommender;

import java.util.ArrayList;
import java.util.List;

import com.szte.recommender.project.entity.Book;
import com.szte.recommender.project.entity.User;
import com.szte.recommender.project.service.UserDetailsImpl;

public class Recommender {

//	private static double[][] matrix = {
//		{0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//		{0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//		{0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0},
//		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1},
//		{1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//		{0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0},
//		{0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//		{0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0},
//		{1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//		{0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1},
//		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0},
//		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0},
//		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0},
//		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 1, 0},
//		{0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0},
//		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 1, 1},
//		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0},
//		{0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1},
//		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 1, 0, 0, 1, 1, 1, 1},
//		{0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0},
//		{0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//		{0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0},
//		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//		{0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0},
//		{0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 0},
//	};
	private static double[][] matrix;
	
	/**
	 * Initializes the purchases matrix, takes all the data from the DB
	 * 
	 * @param users - all of the users
	 * @param books - all of the books (items)
	 */
	private static void initMatrix(List<User> users, List<Book> books) {
		int usersSize = users.size();
		int booksSize = books.size();
		matrix = new double[usersSize][booksSize];
		for (int i = 0; i < usersSize; i++) {
			User newUser = users.get(i);
			if (newUser.getPurchases() != null) {
				String[] purchases = newUser.getPurchases().split(",");
				for (int j = 0; j < booksSize; j++) {
					if (contains(purchases, convertFromDB(books.get(j).getId()) + "")) {
						matrix[i][j] = 1;
					} else {
						matrix[i][j] = 0;
					}
				}
			} else {
				for (int j = 0; j < booksSize; j++) {
					matrix[i][j] = 0;
				}
			}
		}

		ConsolePrinter.printMatrix(matrix);
	}

	/**
	 * Gets the logged in user's purchased books
	 * 
	 * @param books - list of books
	 * @param user - logged in user
	 * @return - user's purchased books
	 */
	public static List<Book> purchasedBooks(List<Book> books, User user) {
		List<Book> purchasedBooks = new ArrayList<Book>();
		int booksSize = books.size();
		if (user.getPurchases() != null) {
			String[] purchases = user.getPurchases().split(",");
			for (int j = 0; j < booksSize; j++) {
				if (contains(purchases, convertFromDB(books.get(j).getId()) + "")) {
					purchasedBooks.add(books.get(j));
				}
			}
		}
		return purchasedBooks;
	}

	/**
	 * Converts id from database, because the database id's start from 1
	 * 
	 * @param id - given id
	 * @return the given id - 1
	 */
	private static int convertFromDB(int id) {
		return --id;
	}

	/**
	 * Checks if the purchases contain the given book
	 * 
	 * @param purchases - all purchases of the user
	 * @param id        - checked book's id
	 * @return true if the user has purchased the given book, false otherwise
	 */
	private static boolean contains(String[] purchases, String id) {
		for (int i = 0; i < purchases.length; i++) {
			if (purchases[i].equals(id)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Predicts all the ratings for the non purchased items
	 * 
	 * @param userId - logged in user
	 * @param booksSize - number of books in the matrix
	 * @return indexes of the descending list of predicted ratings
	 * @throws Exception
	 */
	private static int[] getPredictedRatings(int userId, int booksSize) throws Exception {
		double[] predictedRatings = new double[booksSize];
		long startTime = System.currentTimeMillis();

		for (int i = 0; i < booksSize; i++) {
			if (matrix[userId][i] > 0.1) {
				predictedRatings[i] = -99;
			} else {
				predictedRatings[i] = RecommenderHelper.predictRating(matrix, userId, i);
			}
		}
		
		int purchasedItems = 0;
		for(int i = 0; i < booksSize; i++) {
			if(matrix[userId][i] > 0.1) {
				purchasedItems++;
			}
		}
		
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("___________________________________________");
		System.out.println();
		System.out.println("Total runtime = " + totalTime + " ms");
		System.out.println("___________________________________________");
		System.out.println("Results: ");
		ConsolePrinter.printVector(predictedRatings);
		System.out.println("________________________________");
		int[] indexes = RecommenderHelper.getBestKIndices(predictedRatings, booksSize - purchasedItems);
		for(int i : indexes) {
			System.out.println("index = " + i + "    predicted rating = " + predictedRatings[i]);
		}
		return indexes;

	}

	/**
	 * Returns the user's position in the users list (could be the same as id)
	 * 
	 * @param id - id
	 * @param users - users list
	 * @return index of the user in the users list
	 */
	private static int getUserIndexInList(int id, List<User> users) {
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getId() == id) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Recommends the most appropriate books for the logged in user
	 * 
	 * @param users - list of users
	 * @param books - list of books
	 * @return list of books, ordered by a descending list of predicted ratings
	 * @throws Exception
	 */
	public static List<Book> recommendedBooksForUser(List<User> users, List<Book> books) throws Exception {
		System.out.println("Prediction started!!!!!!!!!!!!!!");
		int loggedInUserIdInList = getUserIndexInList(UserDetailsImpl.getUserId(), users);
		System.out.println("loggedInUserIdinList = " + loggedInUserIdInList);

		initMatrix(users, books);
		int[] indexes = getPredictedRatings(loggedInUserIdInList, books.size());

		System.out.println("Prediction finised!!!!!!!!!!!!!!!");

		List<Book> recommendedBooks = new ArrayList<Book>();
		for (int i = 0; i < indexes.length; i++) {
			recommendedBooks.add(books.get(indexes[i]));
		}

		return recommendedBooks;
	}

}
