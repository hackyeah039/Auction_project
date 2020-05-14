package semi.dao.yh;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import semi.vo.yh.AuctionVo;

public class AuctionDao {
	private static AuctionDao instance = new AuctionDao();
	
	private AuctionDao() {}
	
	public static AuctionDao getInstance() {
		return instance;
	}
	public AuctionVo searchAuction(int a_num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JdbcUtil.getConn();
			String sql = "select * from auction where a_num = ?";
			//0514 빼먹은 코드 수정완료
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, a_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				AuctionVo vo = new AuctionVo(
						rs.getInt("a_num"),
						rs.getString("a_title"),
						rs.getString("a_content"),
						rs.getString("a_condition"),
						rs.getDate("a_regdate"),
						rs.getString("a_startdate"),
						rs.getString("a_enddate"),
						rs.getInt("a_check"),
						rs.getInt("c_num"),
						rs.getInt("a_jjim"),
						rs.getInt("sel_number"),
						rs.getInt("bidstatus"),
						rs.getInt("a_startbid"),
						rs.getInt("a_bidunit")
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
