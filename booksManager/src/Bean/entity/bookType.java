package Bean.entity;

import java.io.Serializable;

public class bookType implements Serializable{
	private int id;
	private String booktype;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBooktype() {
		return booktype;
	}
	public void setBooktype(String booktype) {
		this.booktype = booktype;
	}
}
