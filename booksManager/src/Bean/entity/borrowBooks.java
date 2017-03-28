package Bean.entity;

import java.util.Date;

public class borrowBooks {
	private int borrowid;
	private int borrow_user_id;
	private int borrow_book_id;
	private Date borrow_date;
	public int getBorrowid() {
		return borrowid;
	}
	public void setBorrowid(int borrowid) {
		this.borrowid = borrowid;
	}
	public int getBorrow_user_id() {
		return borrow_user_id;
	}
	public void setBorrow_user_id(int borrow_user_id) {
		this.borrow_user_id = borrow_user_id;
	}
	public int getBorrow_book_id() {
		return borrow_book_id;
	}
	public void setBorrow_book_id(int borrow_book_id) {
		this.borrow_book_id = borrow_book_id;
	}
	public Date getBorrow_date() {
		return borrow_date;
	}
	public void setBorrow_date(Date borrow_date) {
		this.borrow_date = borrow_date;
	}
	public borrowBooks(int borrowid, int borrow_user_id, int borrow_book_id,
			Date borrow_date) {
		this.borrowid = borrowid;
		this.borrow_user_id = borrow_user_id;
		this.borrow_book_id = borrow_book_id;
		this.borrow_date = borrow_date;
	}
	public borrowBooks() {
	}
	
}
