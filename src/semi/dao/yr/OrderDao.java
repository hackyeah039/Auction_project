package semi.dao.yr;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import semi.db.yr.ConnectionPool;
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
				String courier = rs.getString("courier");
				int invoicenum = rs.getInt("invoicenum");

				return new ShipVo(s_num, s_way, s_price, a_num, courier, invoicenum);
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
}
