package com.szte.recommender.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "books")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "title")
	private String title;

	@Column(name = "subtitle")
	private String subtitle;

	@Column(name = "author")
	private String author;

	@Column(name = "year")
	private int year;
	
	@Column(name = "page_number")
	private int pageNumber;

	@Column(name = "price1")
	private int price1;

	@Column(name = "price2")
	private int price2;
	
	public Book() {
	}

	public Book(int id, String title, String subtitle, String author, int year, int pageNumber, int price1,
			int price2) {
		this.id = id;
		this.title = title;
		this.subtitle = subtitle;
		this.author = author;
		this.year = year;
		this.pageNumber = pageNumber;
		this.price1 = price1;
		this.price2 = price2;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPrice1() {
		return price1;
	}

	public void setPrice1(int price1) {
		this.price1 = price1;
	}

	public int getPrice2() {
		return price2;
	}

	public void setPrice2(int price2) {
		this.price2 = price2;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", subtitle=" + subtitle + ", author=" + author + ", year="
				+ year + ", pageNumber=" + pageNumber + ", price1=" + price1 + ", price2=" + price2 + "]";
	}

}
