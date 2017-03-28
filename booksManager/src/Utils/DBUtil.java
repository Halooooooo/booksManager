package Utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.Test;

import Bean.entity.Bill;
import Bean.entity.Users;
import Bean.entity.book;





public class DBUtil {
	private static String driver = null;
	private static String url = null;
	private static String name = null;
	private static String password = null;
	private static Connection conn = null;
	private static Statement stmt = null;
	private static PreparedStatement pstmt = null;
	private static ResultSet rs = null;
	static{
		try {
			Properties p = new Properties();
			InputStream in = DBUtil.class.getResourceAsStream("/db.properties");
			p.load(in);
			driver = p.getProperty("driver");
			url = p.getProperty("url");
			name = p.getProperty("name");
			password = p.getProperty("password");
			
			Class.forName(driver);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("驱动问题");
		}
	}
	public synchronized final static Connection createConnection(){
		try {
			conn = DriverManager.getConnection(url, name, password);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("连接失败");
		}
		return conn;
	}
	public static void close(Connection connection,PreparedStatement statement){
		if(statement!=null){
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(connection!=null){
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void close(ResultSet rs,PreparedStatement statement,Connection connection){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(statement!=null){
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(connection!=null){
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	private static int getTotalByBook(String search){
		String sql = "";
		if("".equals(search)||search==null){
			sql = "select count(*) from books";
		}else{
			sql = "select count(*) from books where b_name like '%"+search+"%'";
		}
		int number = 0;
		try{
			conn =createConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(sql);
			if(rs.next()){
				number = rs.getInt(1);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(rs,pstmt,conn);
		}
		return number;
	}
	private static int getTotalByUser(String search){
		String sql = "";
		if("".equals(search)||search==null){
			sql = "select count(*) from Users";
		}else{
			sql = "select count(*) from Users where u_name like '%"+search+"%'";
		}
		int number = 0;
		try{
			conn =createConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(sql);
			if(rs.next()){
				number = rs.getInt(1);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(rs,pstmt,conn);
		}
		return number;
	}
	public static void getListByBook(PageBean<book> pb,String search){
		int totalCount = getTotalByBook(search);
		pb.setTotalInfo(totalCount);
		String name = "";
		if(search!=null){
			name=search;
		}
		
		if (pb.getCurrentPage() <=0) {
			pb.setCurrentPage(1);
		} else if (pb.getCurrentPage() > pb.getTotalPage()){
			pb.setCurrentPage(pb.getTotalPage());
		}
		int currentPage = pb.getCurrentPage();
		int index = (currentPage-1)*pb.getPageInfo();
		int Info = pb.getPageInfo();
		String sql = "select * from books where b_name like '%"+ name+"%' limit ?,?";
		try{
			conn = createConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, index);
			pstmt.setInt(2,Info);
			rs = pstmt.executeQuery();
			List<book> bookList = new ArrayList<book>();
			while(rs.next()){
				book b = new book();
				b.setBookId(rs.getInt("b_id"));
				b.setBookName(rs.getString("b_name"));
				b.setBooktype(rs.getInt("b_type"));
				b.setAuthor(rs.getString("author"));
				b.setPrice(rs.getDouble("price"));
				b.setNowNumber(rs.getInt("now_number"));
				b.setTotalNumber(rs.getInt("numbers"));
				bookList.add(b);
			}
			pb.setPageData(bookList);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(rs, pstmt, conn);
		}
	}
	public static void getListByUser(PageBean<Users> pb,String search){
		int totalCount = getTotalByUser(search);
		pb.setTotalInfo(totalCount);
		String name = "";
		if(search!=null){
			name=search;
		}
		if (pb.getCurrentPage() <=0) {
			pb.setCurrentPage(1);
		} else if (pb.getCurrentPage() > pb.getTotalPage()){
			pb.setCurrentPage(pb.getTotalPage());
		}
		int currentPage = pb.getCurrentPage();
		int index = (currentPage-1)*pb.getPageInfo();
		int Info = pb.getPageInfo();
		String sql = "select * from Users where u_name like '%"+name+"%' limit ?,?";
		try{
			conn = createConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, index);
			pstmt.setInt(2,Info);
			rs = pstmt.executeQuery();
			List<Users> userList = new ArrayList<Users>();
			while(rs.next()){
				Users u = new Users();
				u.setU_id(rs.getInt("u_id"));
				u.setU_name(rs.getString("u_name"));
				u.setU_type(rs.getInt("u_type"));
				u.setPassword(rs.getString("password"));
				u.setIdcard(rs.getInt("idcard"));
				u.setEmail(rs.getString("email"));
				u.setBorrow(rs.getInt("borrow"));
				u.setMoney(rs.getDouble("money"));
				u.setPhone(rs.getInt("phone"));
				userList.add(u);
			}
			pb.setPageData(userList);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(rs, pstmt, conn);
		}
	}
	private static int getTotalCountBill(int type){
		String sql = "";
		if(-1==type){
			sql="select Count(*) from Bill";
		}else{
			sql="select Count(*) from Bill where billtype=?";
		}
		int number = 0;
		try{
			conn = createConnection();
			pstmt = conn.prepareStatement(sql);
			if(-1!=type){
				pstmt.setInt(1, type);
			}
			rs = pstmt.executeQuery();
			if(rs.next()){
				number = rs.getInt(1);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return number;
	}
	public static void getListByBill(PageBean<Bill> pb, int type) {
		int totalCount = getTotalCountBill(type);
		pb.setTotalInfo(totalCount);
		if (pb.getCurrentPage()<=0) {
			pb.setCurrentPage(1);
		} else if (pb.getCurrentPage() > pb.getTotalPage()){
			pb.setCurrentPage(pb.getTotalPage());
		}
		int currentPage = pb.getCurrentPage();
		int index = (currentPage-1)*pb.getPageInfo();
		int Info = pb.getPageInfo();
		String sql = "";
		if(-1==type){
			sql="select * from Bill limit ?,?";
		}else{
			sql="select * from Bill where billtype=? limit ?,?";
		}
		try{
			conn = createConnection();
			pstmt = conn.prepareStatement(sql);
			if(-1!=type){
				pstmt.setInt(1, type);
				pstmt.setInt(2, index);
				pstmt.setInt(3, Info);
			}else{
				pstmt.setInt(1, index);
				pstmt.setInt(2, Info);
			}
			rs = pstmt.executeQuery();
			List<Bill> billList = new ArrayList<Bill>();
			while(rs.next()){
				Bill b = new Bill();
				b.setBillid(rs.getInt("billid"));
				b.setBilltype(rs.getInt("billtype"));
				b.setBilldate(rs.getDate("billdate"));
				b.setUsername(rs.getString("username"));
				b.setMoney(rs.getDouble("money"));
				billList.add(b);
			}
			pb.setPageData(billList);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(rs, pstmt, conn);
		}
	}
	@Test
	public void t(){
		System.out.println(getTotalCountBill(2));
	}
//	@Test
//	private void connect() throws Exception{
//		Class.forName(driver);
//		conn = (Connection) DriverManager.getConnection(url, name, password);
//		stmt = (Statement) conn.createStatement();
//		String sql = "select * from booktype where t_id=1";
//		rs = stmt.executeQuery(sql);
//		rs.next();
//		System.out.println(rs.getString("t_id"));
//		System.out.println(rs.getString("t_name"));
//	}
//	public static book createEntity(ResultSet rs, Class clazz) {
//		book b = null;
//		try {
//			Class<?> forName = Class.forName(clazz.getName());
//			if(forName.getSimpleName()=="book"){
//				b = (book)forName.newInstance();
//				System.out.println(clazz.getSimpleName());
//				b.setBookId(Integer.parseInt(rs.getString("b_id")));
//				b.setBookName(rs.getString("b_name"));
//				b.setBooktype(Integer.parseInt(rs.getString("b_type")));
//				b.setAuthor(rs.getString("author"));
//				b.setPrice(rs.getDouble("price"));
//				b.setTotalNumber(rs.getInt("numbers"));
//				b.setNowNumber(rs.getInt("now_number"));
//				
//			}
////			Field[] field = clazz.getDeclaredFields();
////			for(Field f:field){
////				if(f.getType().toString().endsWith("String")){
////					System.out.println("String leixing");
////					System.out.println(f.getName().substring(0, 1).toUpperCase()+f.getName().substring(1));
////				}else if(f.getType().toString().endsWith("int")){
////					System.out.println("int leixing");
////					System.out.println(f.getName().substring(0, 1).toUpperCase()+f.getName().substring(1));
////				}else if(f.getType().toString().endsWith("double")){
////					System.out.println("double leixing");
////					System.out.println(f.getName().substring(0, 1).toUpperCase()+f.getName().substring(1));
////				}
////			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
//		return b;
//	}
}
