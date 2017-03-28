package Bean.entity;

public class Approve {
	private int id;
	private int book_id;
	private int user_id;
	private String book_name;
	private String user_name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBook_id() {
		return book_id;
	}
	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getBook_name() {
		return book_name;
	}
	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public Approve() {
	}
	public Approve(int id, int book_id, int user_id, String book_name,
			String user_name) {
		this.id = id;
		this.book_id = book_id;
		this.user_id = user_id;
		this.book_name = book_name;
		this.user_name = user_name;
	}
	
	
}
