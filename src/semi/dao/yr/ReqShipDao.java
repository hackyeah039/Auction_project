package semi.dao.yr;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import semi.db.yr.ConnectionPool;
import semi.vo.yr.PaymentVo;

/**
 * 
 * @author yurae
 * 
 * 배송요청 dao(구매자 정보가져오기,)
 *
 */
public class ReqShipDao {
	  
	  public PaymentVo getBuyerInfo(int anum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ConnectionPool.getCon();
			String sql = "";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, anum);
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
}
