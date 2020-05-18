package semi.dao.yr;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import semi.db.yr.ConnectionPool;

public class ShipDao {
	
	
	public int updateShipInfo(int a, String courier, String invoicenum) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ConnectionPool.getCon();
			String sql= "update ship set courier = ?, invoicenum = ? where a_num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, courier);
			pstmt.setString(2, invoicenum);
			pstmt.setInt(3, a);
			int n = pstmt.executeUpdate();
			return n;
		}catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return -1;
		}finally {
			ConnectionPool.close(null, pstmt, con);
		}
		
	}
}
