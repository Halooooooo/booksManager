package Bean.entity;

import java.io.Serializable;

public class Librarian implements Serializable{
	private int l_id;
	private String l_name;
	private String password;
	
	public int getL_id() {
		return l_id;
	}
	public void setL_id(int l_id) {
		this.l_id = l_id;
	}
	public String getL_name() {
		return l_name;
	}
	public void setL_name(String l_name) {
		this.l_name = l_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Librarian() {
	}
	public Librarian(int l_id, String l_name, String password) {
		this.l_id = l_id;
		this.l_name = l_name;
		this.password = password;
	}
}
