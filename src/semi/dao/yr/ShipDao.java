package semi.dao.yr;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import semi.db.yr.ConnectionPool;
import semi.vo.yr.PaymentVo;

public class ShipDao {
	
	  public PaymentVo getBuyerInfo(int paynum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ConnectionPool.getCon();
			String sql = "select * from payment where pay_num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, paynum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int pay_num = rs.getInt("pay_num");
				String pay_addr = rs.getString("pay_addr");
				int pay_status = rs.getInt("pay_status");
				int bid_number = rs.getInt("bid_number");
				Date pay_deadline = rs.getDate("pay_deadline");
				
				return new PaymentVo(pay_num, pay_addr, pay_status, bid_number, pay_deadline);
			}
			else return null;
			
		}catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			ConnectionPool.close(rs, pstmt, con);
		}
		  
	  }
	  
	  
	
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
	
	//구매종료 상태로 업그레이드

	public int updateBidStatus(int a) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ConnectionPool.getCon();
			String sql= "update auction set  bidstatus = 3  where a_num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, a);

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
