package Bean.entity;

import java.io.Serializable;

public class book implements Serializable{
	private int bookId;
	private String bookName;
	private int booktype; //
	private String author;
	private double price;
	private int totalNumber;
	private int nowNumber;
	
	public book() {
	}
	public book(int bookId, String bookName, int booktype, String author,
			double price, int totalNumber, int nowNumber) {
		this.bookId = bookId;
		this.bookName = bookName;
		this.booktype = booktype;
		this.author = author;
		this.price = price;
		this.totalNumber = totalNumber;
		this.nowNumber = nowNumber;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public int getBooktype() {
		return booktype;
	}
	public void setBooktype(int booktype) {
		this.booktype = booktype;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getTotalNumber() {
		return totalNumber;
	}
	public void setTotalNumber(int totalNumber) {
		this.totalNumber = totalNumber;
	}
	public int getNowNumber() {
		return nowNumber;
	}
	public void setNowNumber(int nowNumber) {
		this.nowNumber = nowNumber;
	}
}
