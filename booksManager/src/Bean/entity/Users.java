package Bean.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Users implements Serializable{
	private int u_id;
	private String password;
	private String u_name;
	private int u_type;
	
	private int phone;
	private String email;
	private int idcard;
	private double money;
	private int borrow;
	private final static int USER_TYPE_NORMAL = 0;
	private final static int USER_TYPE_VIP = 1;
	private final static int USER_TYPE_ERROR = -1;
	public static Map<Integer,String> USER_TYPE_MAP;
	static{
		USER_TYPE_MAP=new HashMap<Integer, String>();
		USER_TYPE_MAP.put(USER_TYPE_NORMAL, "普通用户");
		USER_TYPE_MAP.put(USER_TYPE_VIP, "VIP用户");
		USER_TYPE_MAP.put(USER_TYPE_ERROR, "已停用");
	}
	public Users() {
	}
	public Users(int u_id, String password, String u_name, int u_type,
			int phone, String email, int idcard, double money, int borrow) {
		this.u_id = u_id;
		this.password = password;
		this.u_name = u_name;
		this.u_type = u_type;
		this.phone = phone;
		this.email = email;
		this.idcard = idcard;
		this.money = money;
		this.borrow = borrow;
	}
	public int getU_id() {
		return u_id;
	}
	public void setU_id(int u_id) {
		this.u_id = u_id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getU_name() {
		return u_name;
	}
	public void setU_name(String u_name) {
		this.u_name = u_name;
	}
	public int getU_type() {
		return u_type;
	}
	public void setU_type(int u_type) {
		this.u_type = u_type;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getIdcard() {
		return idcard;
	}
	public void setIdcard(int idcard) {
		this.idcard = idcard;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public int getBorrow() {
		return borrow;
	}
	public void setBorrow(int borrow) {
		this.borrow = borrow;
	}
	@Override
	public String toString() {
		return "Users [u_id=" + u_id + ", password=" + password + ", u_name="
				+ u_name + ", u_type=" + u_type + ", phone=" + phone
				+ ", email=" + email + ", idcard=" + idcard + ", money="
				+ money + ", borrow=" + borrow + "]";
	}
}
