package com.szte.recommender.project;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.szte.recommender.project.entity.Book;
import com.szte.recommender.project.entity.User;

public class CSVtoDB {

	public static void main(String[] args) {
		try {
			readFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void readFile() throws IOException {
		File myFile = new File("D:\\Egyetem\\Szakdolgozat\\adatok_remix.xlsx");
		FileInputStream fis = new FileInputStream(myFile);

		// Finds the workbook instance for XLSX file
		XSSFWorkbook myWorkBook = new XSSFWorkbook(fis);

		// Return first sheet from the XLSX workbook
		XSSFSheet mySheet = myWorkBook.getSheetAt(0);

		// Get iterator to all the rows in current sheet
		Iterator<Row> rowIterator = mySheet.iterator();

		boolean books = false;
		int bookCounter = 1;
		boolean people = false;
		int peopleCounter = 1;
		List<User> users = new ArrayList<>();
		List<Book> bookList = new ArrayList<Book>();

		// Traversing over each row of XLSX file
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();

			// For each row, iterate through each columns
			Iterator<Cell> cellIterator = row.cellIterator();
			if (!books && !people) {
				while (cellIterator.hasNext()) {

					Cell cell = cellIterator.next();

					if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
						if (cell.getStringCellValue().equalsIgnoreCase("PRICE2")) {
							books = true;
						}
					}
				}

			} else if (books && !people) {
				int id = 0;
				String title = null;
				String subtitle = null;
				int pageNumber = 0;
				int year = 0;
				int price1 = 0;
				int price2 = 0;
				for (int i = 0; i < 7; i++) {
					Cell cell = cellIterator.next();

					if (cell == null) {
						break;
					}
					if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
						if (cell.getStringCellValue().equalsIgnoreCase("NULL")) {
							continue;
						}
						if (cell.getStringCellValue().equalsIgnoreCase("FULLNAME")) {
							people = true;
							System.out.println("\n________________________________________________\n");
							books = false;
							break;
						}
						if (cell.getStringCellValue().equalsIgnoreCase("PERSON")) {
							cell = cellIterator.next();
							cell = cellIterator.next();
							cell = cellIterator.next();
							cell = cellIterator.next();
							cell = cellIterator.next();
							cell = cellIterator.next();
						}
					}
					switch (i) {
					case 0:
						if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							id = (int) cell.getNumericCellValue();
						}
						break;
					case 1:
						title = cell.getStringCellValue();
						break;
					case 2:
						subtitle = cell.getStringCellValue();
						break;
					case 3:
						pageNumber = (int) cell.getNumericCellValue();
						break;
					case 4:
						year = (int) cell.getNumericCellValue();
						break;
					case 5:
						price1 = (int) cell.getNumericCellValue();
						break;
					case 6:
						price2 = (int) cell.getNumericCellValue();
						break;
					default:

					}
				}
				String author = "author" + bookCounter;
				bookCounter++;
				if (id != 0) {
					Book newBook = new Book(id, title, subtitle, author, year, pageNumber, price1, price2);
					bookList.add(newBook);
//					System.out.println("This is the newBook: " + newBook.toString());
				}
			} else if (people) {
				int id = 0;
				String firstName = null;
				String lastName = null;
				String password = null;
				String email = null;
				String purchases = null;
				int bookId = -1;
				for (int i = 0; i < 3; i++) {
					Cell cell = cellIterator.next();
					if (cell == null) {
						break;
					}
					switch (i) {
					case 0:
						bookId = (int) cell.getNumericCellValue();
						break;
					case 1:
						id = (int) cell.getNumericCellValue();
						break;
					case 2:
						break;
					default:

					}
				}
				User newUser = null;
				boolean old = false;
				for (User user : users) {
					if (user.getId() == id) {
						newUser = user;
						old = true;
						break;
					}
				}
				if (!old) {
					firstName = "firstName" + peopleCounter;
					lastName = "lastName" + peopleCounter;
					password = "password" + peopleCounter;
					email = "email" + peopleCounter + "@example.com";
					purchases = "" + bookId;
					peopleCounter++;
					newUser = new User(id, firstName, lastName, password, email, purchases, "USER");
					users.add(newUser);
				} else {
					newUser.setPurchases(newUser.getPurchases() + "," + bookId);
				}
//				System.out.println("This is the newUser: " + newUser.toString());
			}
		}
		
		
		
		for(Book book : bookList) {
			System.out.println(book);
		}
		System.out.println("____________\n");
		for(User user : users) {
			System.out.println(user);
		}
		writeIntoTxt(users, bookList);
	}
	
	public static void writeIntoTxt(List<User> users, List<Book> books) throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter writer = new PrintWriter("dbfiller.sql", "UTF-8");
		writer.println("INSERT INTO `users`");
		writer.println("VALUES");
		int i = 0;
		for(User user : users) {
			writer.print("(" + user.getId() + ",'" + user.getFirstName() + "','" +
					user.getLastName() + "','" + user.getPassword() + "','" + user.getEmail() +
					 "','" + user.getPurchases() + "','" + user.getRole() + "')");
			i++;
			if(users.size() > i) {
				writer.println(",");
			} else {
				writer.println(";");
			}
		}
		writer.println();
		writer.println("INSERT INTO `books`");
		writer.println("VALUES");
		i = 0;
		for(Book book : books) {
			writer.print("(" + book.getId() + ",'" + book.getTitle() + "','" +
					book.getSubtitle() + "','" + book.getAuthor() + "'," +
					book.getYear() + "," + book.getPageNumber() + "," +
					book.getPrice1() + "," + book.getPrice2() + ")");
			i++;
			if(books.size() > i) {
				writer.println(",");
			} else {
				writer.println(";");
			}
		}
		writer.close();
	}

}
