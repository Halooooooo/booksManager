package Bean.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Bill {
	private int billid;
	private int billtype;
	private Date billdate;
	private String username;
	private Double money;
	private final static int BILL_TYPE_ALL = -1;
	private final static int BILL_TYPE_USERADD = 0;
	private final static int BILL_TYPE_BOOKADD = 1;
	private final static int BILL_TYPE_USERDEL = 2;
	private final static int BILL_TYPE_VIP = 3;
	public static Map<Integer,String> TYPE_MAP = null;
	static{
		TYPE_MAP = new HashMap<Integer,String>();
		TYPE_MAP.put(BILL_TYPE_ALL, "全部查询");
		TYPE_MAP.put(BILL_TYPE_USERADD, "用户充值");
		TYPE_MAP.put(BILL_TYPE_BOOKADD, "书籍增加");
		TYPE_MAP.put(BILL_TYPE_USERDEL, "用户罚款");
		TYPE_MAP.put(BILL_TYPE_VIP, "VIP服务");
	}
	public int getBillid() {
		return billid;
	}
	public void setBillid(int billid) {
		this.billid = billid;
	}
	public int getBilltype() {
		return billtype;
	}
	public void setBilltype(int billtype) {
		this.billtype = billtype;
	}
	public Date getBilldate() {
		return billdate;
	}
	public void setBilldate(Date billdate) {
		this.billdate = billdate;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Bill() {	}
	public Bill(int billid, int billtype, Date billdate, String username,
			Double money) {
		this.billid = billid;
		this.billtype = billtype;
		this.billdate = billdate;
		this.username = username;
		this.money = money;
	}
	
}
