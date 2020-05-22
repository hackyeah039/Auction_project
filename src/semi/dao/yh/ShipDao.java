package semi.dao.yh;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.ConnectionPool;

public class ShipDao {
	private static ShipDao instance = new ShipDao();
	
	private ShipDao() {}
	
	public static ShipDao getInstance() {
		return instance;
	}
	// 인서트 테이블 메소드 추가 
	public int InsertShip(String s_way, int s_price) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int n = 0;
		try {
			con = ConnectionPool.getConn();
			String sql = "insert into Ship values(seq_ship_s_num.nextval, ?, ?, seq_auction_a_num.currval)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, s_way);
			pstmt.setInt(2, s_price);
			return n = pstmt.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace();
			return -1;
		} finally {
			ConnectionPool.close(null, pstmt, con);
		}
	}
}
