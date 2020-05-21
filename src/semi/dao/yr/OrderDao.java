package semi.dao.yr;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import semi.db.yr.ConnectionPool;
import semi.vo.yr.MembersVo;
import semi.vo.yr.PaymentVo;
import semi.vo.yr.ShipVo;

public class OrderDao {
	
	

	public ShipVo getShipInfo(int anum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ConnectionPool.getCon();
			String sql = "select * from ship where a_num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, anum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int s_num = rs.getInt("s_num");
				String s_way = rs.getString("s_way");
				int s_price = rs.getInt("s_price");
				int a_num = rs.getInt("a_num");
//				String courier = rs.getString("courier");
//				int invoicenum = rs.getInt("invoicenum");

				return new ShipVo(s_num, s_way, s_price, a_num, null, 0);
			}else {
				return null;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return null;
		} finally {
			ConnectionPool.close(rs, pstmt, con);
		}
	}
	

//	해당 paynum에 입력받은 수령인, 주소, 전화번호로 update

	public int updatePayInfo(PaymentVo vo, ArrayList<Integer> paynumlist) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {		
			con = ConnectionPool.getCon();
			
			int n = 0;

			for (int paynum : paynumlist) {
				String sql = "update payment set pay_addr = ?, pay_name = ?,"
						+ "pay_phone = ?, pay_status = 1 where pay_num = ?";

				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, vo.getPay_addr());
				pstmt.setString(2, vo.getPay_name());
				pstmt.setString(3, vo.getPay_phone());
				pstmt.setInt(4, paynum);
				
				n = pstmt.executeUpdate();
				if(n<0) {
					return -1;
				}else {
					n += n;
				}
			}
			return n;
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return -1;
		} finally {
			ConnectionPool.close(null, pstmt, con);
		}
	}
	
	
	public MembersVo getMemberInfo(int mnum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {		
			con = ConnectionPool.getCon();
			String sql = "select * from members where m_num = ?";
	
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mnum);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int m_num = rs.getInt("m_num");
				String m_name = rs.getString("m_name");
				int m_phone = rs.getInt("m_phone");
				String m_addr = rs.getString("m_addr");
				
				return new MembersVo(mnum, m_name, m_phone, m_addr);
			}
			return null;

		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return null;

		} finally {
			ConnectionPool.close(rs, pstmt, con);
		}
	}
	
}
