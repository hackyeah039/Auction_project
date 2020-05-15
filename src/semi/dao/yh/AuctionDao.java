package semi.dao.yh;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
			//0514 빼먹은 코드 수정완료!
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, a_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				AuctionVo vo = new AuctionVo(
						rs.getInt("a_num"),
						rs.getString("a_title"),
						rs.getString("a_content"),
						rs.getInt("a_condition"),
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
	//테이블 인서트 메소드 추가 
	public int InsertAuction(AuctionVo vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int n = 0;
		try {
			con = JdbcUtil.getConn();
			String sql = "insert into auction values(seq_auction_a_num.nextval,?,?,?,sysdate,?,?,0,?,0,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getA_title());
			pstmt.setString(2, vo.getA_content());
			pstmt.setInt(3, vo.getA_condition());
			pstmt.setString(4, vo.getA_startdate());
			pstmt.setString(5, vo.getA_enddate());
			pstmt.setInt(6, vo.getC_num());
			pstmt.setInt(7, vo.getSel_number());
			pstmt.setInt(8, vo.getBidstatus());
			pstmt.setInt(9, vo.getA_startbid());
			pstmt.setInt(10, vo.getA_bidunit());
			return n = pstmt.executeUpdate();	
		} catch (SQLException se) {
			se.printStackTrace();
			return -1;
		} finally {
			JdbcUtil.close(con, pstmt, null);
		}
	}
}
