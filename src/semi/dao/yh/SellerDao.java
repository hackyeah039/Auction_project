package semi.dao.yh;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.JdbcUtil;
import semi.vo.yh.sellerVo;

public class SellerDao {
		private static SellerDao instance = new SellerDao();
		
		private SellerDao() {}
		
		public static SellerDao getInstance() {
			return instance;
		}
		
		public sellerVo searchSeller(int sel_number) {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				con = JdbcUtil.getConn();
				String sql = "select * from seller where sel_number = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, sel_number);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					sellerVo vo = new sellerVo(
					rs.getLong("account"),
					rs.getInt("m_num"),
					rs.getInt("sel_number")
					);
					return vo;
				}
				return null;
			} catch (SQLException se) {
				se.printStackTrace();
				return null;
			} finally {
				JdbcUtil.close(con, pstmt, rs);
			}
		}
		public ArrayList<sellerVo> listAccount(int m_num1){
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				con = JdbcUtil.getConn();
				String sql = "select * from seller where m_num=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, m_num1);
				rs = pstmt.executeQuery();
				ArrayList<sellerVo> list = new ArrayList<sellerVo>();
				while(rs.next()) {
					Long account = rs.getLong(1);
					int m_num = rs.getInt(2);
					int sel_number = rs.getInt(3);
					sellerVo vo=new sellerVo(account, m_num, sel_number);
					list.add(vo);
				}
				return list;
			} catch (SQLException se) {
				se.printStackTrace();
				return null;
			} finally {
				JdbcUtil.close(con, pstmt, rs);
			}
		}
		public int checkAccount(long a) {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				con = JdbcUtil.getConn();
				String sql = "select * from seller where sel_number = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setLong(1, a);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					return 1;
				}
				return 0;
			} catch (SQLException se) {
				se.printStackTrace();
				return -1;
			} finally {
				JdbcUtil.close(con, pstmt, rs);
			}
		}
}
