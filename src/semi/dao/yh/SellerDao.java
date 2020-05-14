package semi.dao.yh;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
					rs.getInt("account"),
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
}
