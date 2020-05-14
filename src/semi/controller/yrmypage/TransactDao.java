package semi.controller.yrmypage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import semi.db.yr.ConnectionPool;

public class TransactDao {
	
	public ArrayList<Integer> buyerBidinglist(int mnum) {

		ArrayList<Integer> bidlist = new ArrayList<Integer>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ConnectionPool.getCon();
			String sql = "select auction.a_num from bid, auction "
					+ "where bid.a_num=auction.a_num and bid.m_num = ? and " + 
					"bidstatus = 2;";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mnum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {
					int a = rs.getInt("a_num");
					bidlist.add(a);
				} while (rs.next());
				return bidlist;
			} else {
				return null;
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			ConnectionPool.close(rs, pstmt, con);
		}
	}
	
}
