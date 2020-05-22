package semi.dao.yh;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.ConnectionPool;
import semi.vo.yh.BiddingVo;

public class BiddingDao {
	private static BiddingDao instance = new BiddingDao();
	
	private BiddingDao() {}
	
	public static BiddingDao getInstance() {
		return instance;
	}
	//입찰 최대값 구하기
	public int showMaxBid(int a_num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ConnectionPool.getConn();
			String sql = "select max(bid_price) as bid_price from bid where a_num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, a_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("bid_price");
			}
			return 0;
		} catch (SQLException se) {
			se.printStackTrace();
			return -1;
		} finally {
			ConnectionPool.close(rs, pstmt, con);
		}
	}
	public int insertBid(BiddingVo vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionPool.getConn();
			String sql = "insert into bid values (?,?,?,sysdate,seq_bid_bid_number.nextval)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, vo.getM_num());
			pstmt.setInt(2, vo.getA_num());
			pstmt.setInt(3, vo.getBid_price());
			return pstmt.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace();
			return -1;
		} finally {
			ConnectionPool.close(null, pstmt, con);
		}
	}
}
