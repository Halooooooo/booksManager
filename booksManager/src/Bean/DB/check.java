package Bean.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Utils.DBUtil;

public class check {
	private  Connection conn = null;
	private  PreparedStatement pstmt= null;
	private  ResultSet rs = null;
	public Boolean checkUser(String name, String password){
		Boolean checkin = false;
		try {
			conn = DBUtil.createConnection();
			String sql = "select * from Users where u_name=? and password=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if(rs.next()){
				checkin = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, pstmt, conn);
		}
		return checkin;
	}
	public Boolean chechAdmin(String name, String password){
		Boolean checkin = false;
		try {
			conn = DBUtil.createConnection();
			String sql = "select * from Librarian where l_name=? and password=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if(rs.next()){
				checkin = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, pstmt, conn);
		}
		return checkin;
	}
	public boolean checkName(String userName) {
		Boolean checkin = true;
		try {
			conn = DBUtil.createConnection();
			String sql = "select * from Users where u_name=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userName);
			rs = pstmt.executeQuery();
			if(rs.next()){
				checkin = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, pstmt, conn);
		}
		return checkin;
	}
	public boolean checkID(int ID) {
		Boolean checkin = true;
		try {
			conn = DBUtil.createConnection();
			String sql = "select * from Users where idcard=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ID);
			rs = pstmt.executeQuery();
			if(rs.next()){
				checkin = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, pstmt, conn);
		}
		return checkin;
	}
	
}
