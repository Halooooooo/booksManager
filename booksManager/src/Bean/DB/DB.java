package Bean.DB;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import Bean.entity.Approve;
import Bean.entity.ManagerHomeInfo;
import Bean.entity.Users;
import Bean.entity.book;
import Bean.entity.bookType;
import Utils.DBUtil;


public class DB {
	private static Connection conn = null;
	private static PreparedStatement pstmt= null;
	private static CallableStatement prepareCall =null;
	private static ResultSet rs = null;
	static List<book> list  = null;
	/**
	 * 书籍列表
	 * @return
	 */
	public static List<book> getBooksList() {
		try {
			conn = DBUtil.createConnection();
			pstmt = (PreparedStatement) conn.prepareStatement("select * from books");
			rs = pstmt.executeQuery();
			list = new ArrayList<book>();
			while(rs.next()){
				book b = new book();
				b.setBookId(Integer.parseInt(rs.getString("b_id")));
				b.setBookName(rs.getString("b_name"));
				b.setBooktype(Integer.parseInt(rs.getString("b_type")));
				b.setAuthor(rs.getString("author"));
				b.setPrice(rs.getDouble("price"));
				b.setTotalNumber(rs.getInt("numbers"));
				b.setNowNumber(rs.getInt("now_number"));
				list.add(b);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, pstmt,conn);
		}
		return list;
	}
	public static List<bookType> bts = null;
	/**
	 * 书籍类型
	 * @return
	 */
	public static List<bookType> getBookType (){
		try {
			bts = new ArrayList<bookType>();
			conn = DBUtil.createConnection();
			pstmt = (PreparedStatement) conn.prepareStatement("select * from booktype");
			rs = pstmt.executeQuery();
			while(rs.next()){
				bookType bt = new bookType();
				bt.setId(rs.getInt("t_id"));
				bt.setBooktype(rs.getString("t_name"));
				bts.add(bt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, pstmt,conn);
		}
		return bts;
	}
	public static void delect(){
		
	}
	public static void update(){
		
	}
	public static void addBook(String bookName, String author, String type,
			String price, String totalNumber, String nowNumber)  {
		try{
			
			conn = DBUtil.createConnection();
			String sql = "insert into books(b_name,b_type,author,price,numbers,now_number) values(?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bookName);
			pstmt.setInt(2, Integer.parseInt(type));
			pstmt.setString(3, author);
			pstmt.setDouble(4, Double.parseDouble(price));
			pstmt.setInt(5, Integer.parseInt(totalNumber));
			pstmt.setInt(6, Integer.parseInt(nowNumber));
			pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, pstmt, conn);
		}
	}
	public static void delect(int id) {
		try{
			conn = DBUtil.createConnection();
			pstmt = conn.prepareStatement("delete from books where b_id=?");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(conn, pstmt);
		}
	}
	public static book findById(int id) {
		book b = new book();
		try{
			conn = DBUtil.createConnection();
			pstmt = conn.prepareStatement("select * from books where b_id=?");
			pstmt.setInt(1,id);
			rs = pstmt.executeQuery();
			rs.next();
			b.setBookId(Integer.parseInt(rs.getString("b_id")));
			b.setBookName(rs.getString("b_name"));
			b.setBooktype(Integer.parseInt(rs.getString("b_type")));
			b.setAuthor(rs.getString("author"));
			b.setPrice(rs.getDouble("price"));
			b.setTotalNumber(rs.getInt("numbers"));
			b.setNowNumber(rs.getInt("now_number"));
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, pstmt, conn);
		}
		return b;
	}
	public static void updateBook(int id, String type, String bookName,
			String author, String price, String totalNumber, String nowNumber) {
		try{
			conn = DBUtil.createConnection();
			String sql = "update books SET b_name=?,b_type=?,author=?,price=?,numbers=?,now_number=? where b_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bookName);
			pstmt.setInt(2, Integer.parseInt(type));
			pstmt.setString(3, author);
			pstmt.setDouble(4, Double.parseDouble(price));
			pstmt.setInt(5, Integer.parseInt(totalNumber));
			pstmt.setInt(6, Integer.parseInt(nowNumber));
			pstmt.setInt(7,id);
			pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, pstmt, conn);
		}
	}
	public static Map<Integer, bookType> getBookTypeMap() {
		Map<Integer,bookType> bts = new HashMap<Integer, bookType>();
		try {
			conn = DBUtil.createConnection();
			pstmt = (PreparedStatement) conn.prepareStatement("select * from booktype");
			rs = pstmt.executeQuery();
			while(rs.next()){
				bookType bt = new bookType();
				bt.setId(rs.getInt("t_id"));
				bt.setBooktype(rs.getString("t_name"));
				bts.put(bt.getId(), bt);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, pstmt, conn);
		}
		return bts;
	}
	public static Users getUserByName(String name) {
		try {
			conn = DBUtil.createConnection();
			pstmt = (PreparedStatement) conn.prepareStatement("select * from Users where u_name=?");
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			if(rs.next()){
				Users u = new Users(Integer.parseInt(rs.getString("u_id")),rs.getString("password"),rs.getString("u_name"),rs.getInt("u_type"),
						rs.getInt("phone"),rs.getString("email"),rs.getInt("idcard"),rs.getDouble("money"),rs.getInt("borrow"));
				return u;
			}else{
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, pstmt, conn);
		}
		return null;
	}
	public static boolean borrowBook(int bookid, int userid, int approveid) {
		try {
			conn = DBUtil.createConnection();
			String sql = "call borrowbook(?,?,?)";
			prepareCall = conn.prepareCall(sql);
			prepareCall.setInt(1, bookid);
			prepareCall.setInt(2, userid);
			prepareCall.setInt(3, approveid);
			rs = prepareCall.executeQuery();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs,prepareCall,conn);
		}
		return true;
	}
	public static List<Approve> getApproveList() {
		List<Approve> list = new ArrayList<Approve>();
		try {
			conn = DBUtil.createConnection();
			String sql = "select * from approve";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				int id  = rs.getInt("id");
				int book_id = rs.getInt("book_id");
				int user_id = rs.getInt("user_id");
				String user_name = rs.getString("user_name");
				String book_name = rs.getString("book_name");
				Approve a = new Approve(id,book_id,user_id,book_name,user_name);
				list.add(a);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, pstmt, conn);
		}
		return list;
	}
	public static void setApprove(int u_id, String u_name, String bookid,
			String bookName) {
		try {
			conn = DBUtil.createConnection();
			String sql = "insert into approve(book_id,user_id,user_name,book_name) values (?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(bookid));
			pstmt.setInt(2, u_id);
			pstmt.setString(3, u_name);
			pstmt.setString(4, bookName);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, pstmt, conn);
		}
	}
	public static Users findUserById(int userid){
		Users u = new Users();
		try {
			conn = DBUtil.createConnection();
			String sql = "select * from Users where u_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userid);
			rs = pstmt.executeQuery();
			if(rs.next()){
				u.setEmail(rs.getString("email"));
				u.setU_id(userid);
				u.setU_name(rs.getString("u_name"));
				u.setU_type(rs.getInt("u_type"));
				u.setBorrow(rs.getInt("borrow"));
				u.setMoney(rs.getDouble("money"));
				u.setPhone(rs.getInt("phone"));
				u.setIdcard(rs.getInt("idcard"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, pstmt, conn);
		}
		return u;
	}
	public static List<Users> ReturnBookUsers(int bookid) {
		List<Users> u = new ArrayList<Users>();
		PreparedStatement prepareStatement  = null;
		ResultSet executeQuery = null;
		try {
			conn = DBUtil.createConnection();
			String sql = "select * from borrowBooks where borrow_book_id=?";
			prepareStatement = conn.prepareStatement(sql);
			prepareStatement.setInt(1,bookid);
			executeQuery = prepareStatement.executeQuery();
			while(executeQuery.next()){
				int userid = executeQuery.getInt("borrow_user_id");
				u.add(DB.findUserById(userid));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(executeQuery, prepareStatement, conn);
		}
		return u;
	}
	public static long getBorrowTime(int bookid, int userid) {
		long time = 0;
		try {
			conn = DBUtil.createConnection();
			String sql = "select borrow_date from borrowBooks where borrow_book_id=? and borrow_user_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bookid);
			pstmt.setInt(2, userid);
			rs = pstmt.executeQuery();
			if(rs.next()){
				time = rs.getDate("borrow_date").getTime();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, pstmt, conn);
		}
		return time;
	}
	public static void setMoney(double money, int userid) {
		try{
			conn = DBUtil.createConnection();
			String sql = "update Users SET money = ? where u_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setDouble(1, money);
			pstmt.setInt(2, userid);
			pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(conn, pstmt);
		}
	}
	public static void ReturnBook(int bookid, int userid) {
		try{
			conn = DBUtil.createConnection();
			String sql = "call returnbook(?,?)";
			prepareCall = conn.prepareCall(sql);
			prepareCall.setInt(1, bookid);
			prepareCall.setInt(2, userid);
			prepareCall.executeQuery();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(conn, prepareCall);
		}	
	}
	public static List<book> getBorrowBook() {
		List<book> list = new ArrayList<book>();
		try{
			conn = DBUtil.createConnection();
			String sql = "select * from books where numbers>now_number";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				book b = new book();
				b.setBookId(Integer.parseInt(rs.getString("b_id")));
				b.setBookName(rs.getString("b_name"));
				b.setBooktype(Integer.parseInt(rs.getString("b_type")));
				b.setAuthor(rs.getString("author"));
				b.setPrice(rs.getDouble("price"));
				b.setTotalNumber(rs.getInt("numbers"));
				b.setNowNumber(rs.getInt("now_number"));
				list.add(b);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs,pstmt,conn);
		}
		return list;
	}
	public static List<Users> getUserList(String search) {
		List<Users> list = new ArrayList<Users>();
		try{
			conn = DBUtil.createConnection();
			String sql = "select * from Users like %?%";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, search);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Users u = new Users(Integer.parseInt(rs.getString("u_id")),rs.getString("password"),rs.getString("u_name"),rs.getInt("u_type"),
						rs.getInt("phone"),rs.getString("email"),rs.getInt("idcard"),rs.getDouble("money"),rs.getInt("borrow"));
				list.add(u);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs,pstmt,conn);
		}
		return list;
	}
	public static void setUser(String u_id, String u_name, String idcard,
			String email, String phone, String borrow) {
		try{
			conn = DBUtil.createConnection();
			String sql = "update Users u_name=?,idcard=?,email=?,phone=?,borrow=?where id =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, u_name);
			pstmt.setInt(2, Integer.parseInt(idcard));
			pstmt.setString(3, email);
			pstmt.setInt(4,Integer.parseInt(phone));
			pstmt.setInt(5, Integer.parseInt(borrow));
			pstmt.setInt(6, Integer.parseInt(u_id));
			pstmt = conn.prepareStatement(sql);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(conn,pstmt);
		}
	}
	public static void SignUser(String u_name, String password, int phone,
			String email, int idcard) {
		try{
			conn = DBUtil.createConnection();
			String sql = "insert into Users(u_name,password,idcard,email,phone) values (?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, u_name);
			pstmt.setString(2, password);
			pstmt.setInt(3, idcard);
			pstmt.setString(4, email);
			pstmt.setInt(5, phone);
			pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(conn,pstmt);
		}
	}
	public static void makeVIP(Users user) {
		try{
			conn = DBUtil.createConnection();
			String sql = "update Users set money=?,u_type=?,borrow=? where u_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setDouble(1, user.getMoney());
			pstmt.setInt(2, user.getU_type());
			pstmt.setInt(3, user.getBorrow());
			pstmt.setInt(4, user.getU_id());
			pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(conn,pstmt);
		}
	}
	public static void recharge(String u_name, int type, double money) {
		try{
			conn = DBUtil.createConnection();
			String sql = "insert into Bill (billtype,billdate,username,money) values(?,now(),?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, type);
			pstmt.setString(2, u_name);
			pstmt.setDouble(3, money);
			pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(conn,pstmt);
		}
	}
	@Test
	public void test() throws Exception{
		try {
			Class<?> MHI = Class.forName("Bean.entity.ManagerHomeInfo");
			ManagerHomeInfo mhi = (ManagerHomeInfo) MHI.newInstance();
			conn = DBUtil.createConnection();
			String sql = "call HomeInfo()";
			CallableStatement prepareCall = conn.prepareCall(sql);
			boolean hadResults = prepareCall.execute();    
			Field[] filed = MHI.getDeclaredFields();
			for(Field f: filed){
				if(hadResults){
					Method method ;
					rs = prepareCall.getResultSet();
					if(rs.next()){
						if(f.getType().getName().equals("int")){
							method = MHI.getMethod("set"+f.getName().substring(0,1).toUpperCase()+f.getName().substring(1),int.class);
							int i = rs.getInt(1);
							method.invoke(mhi,i);
						}else if(f.getType().getName().equals("double")){
							method = MHI.getMethod("set"+f.getName().substring(0,1).toUpperCase()+f.getName().substring(1),double.class);
							double i = rs.getDouble(1);
							method.invoke(mhi,i);
						}
					}
					hadResults = prepareCall.getMoreResults();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		}
	}
	public static ManagerHomeInfo findHomeInfo() throws Exception {
		
		Class MHI = Class.forName("Bean.entity.ManagerHomeInfo");
		ManagerHomeInfo mhi = (ManagerHomeInfo) MHI.newInstance();
		try{
			conn = DBUtil.createConnection();
			String sql = "call HomeInfo()";
			prepareCall = conn.prepareCall(sql);
			boolean hadResults = prepareCall.execute();    
			Field[] filed = MHI.getDeclaredFields();
			for(Field f: filed){
				if(hadResults){
					Method method ;
					rs = prepareCall.getResultSet();
					if(rs.next()){
						if(f.getType().getName().equals("int")){
							method = MHI.getMethod("set"+f.getName().substring(0,1).toUpperCase()+f.getName().substring(1),int.class);
							int i = rs.getInt(1);
							method.invoke(mhi,i);
						}else if(f.getType().getName().equals("double")){
							method = MHI.getMethod("set"+f.getName().substring(0,1).toUpperCase()+f.getName().substring(1),double.class);
							double i = rs.getDouble(1);
							method.invoke(mhi,i);
						}
					}
					hadResults = prepareCall.getMoreResults();
				}
			}
			return mhi;
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs,prepareCall,conn);
		}
		return mhi;
	}
	public static void setExpress(int userid, int bookid, int spot, String spotInfo, int phone, double money) {
		try{
			conn = DBUtil.createConnection();
			String sql = "insert into expressBook (bookid,userid,spot,spotInfo,phone,money) values(?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userid);
			pstmt.setInt(2, bookid);
			pstmt.setInt(3, spot);
			pstmt.setString(4, spotInfo);
			pstmt.setInt(5, phone);
			pstmt.setDouble(6, money);
			pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(conn,pstmt);
		}
	}
	public static void express(int bookid, int u_id) {
		try{
			conn = DBUtil.createConnection();
			String sql = "call expressbook(?,?)";
			prepareCall = conn.prepareCall(sql);
			prepareCall.setInt(1, bookid);
			prepareCall.setInt(2, u_id);
			prepareCall.execute();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(conn,prepareCall);
		}
	}
	public static void timeStamp(int u_id, long createTime) {
		try{
			conn = DBUtil.createConnection();
			String sql = "insert into timeStamps (userid,time) values(?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, u_id);
			pstmt.setLong(2, createTime);
			pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(conn,pstmt);
		}
	}
	public static long delStamp(int u_id) {
		long time = 0;
		try{
			conn = DBUtil.createConnection();
			String sql = "select time from timeStamps where userid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, u_id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				time = rs.getLong(1);
			}
			sql = "delete from timeStamps where userid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, u_id);
			pstmt.execute();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(conn,pstmt);
		}
		return time;
	}
	public static void setUserTime(int userid, long time) {
		try{
			conn = DBUtil.createConnection();
			String sql = "select * from userTime where userid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userid);
			rs = pstmt.executeQuery();
			if(rs.next()){
				//you
				long oldtime = rs.getLong("time");
				sql="update userTime SET time = ? where userid=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setLong(1, oldtime+time);
				pstmt.setInt(2, userid);
				pstmt.executeUpdate();
			}else{
				//wu
				sql="insert into userTime(userid,time) values(?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, userid);
				pstmt.setLong(2, time);
				pstmt.executeUpdate();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs,pstmt,conn);
		}
	}
	
	public static void deleteuser(int id) {
		try{
			conn = DBUtil.createConnection();
			String sql = "delete from Users where u_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs,pstmt,conn);
		}
	}
}
