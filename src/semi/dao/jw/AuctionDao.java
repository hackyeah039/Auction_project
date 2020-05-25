package semi.dao.jw;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import semi.db.jw.ConnectionPool;
import semi.vo.jw.BidVo;

public class AuctionDao {
	private static AuctionDao instance = new AuctionDao();
	private AuctionDao() {};
	public static AuctionDao getInstance() {
		return instance;
	}
	public String enddate(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		String sql="select to_char(a_enddate,'MONTH DD,YYYY,HH24:MI:SS') from auction where a_num=?";
		try{
			con= ConnectionPool.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			String enddate =null;
			if(rs.next()) {
				enddate=rs.getString(1);
				System.out.println(enddate+"날짜입니다.");
			}
			return enddate;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			ConnectionPool.close(rs, pstmt, con);
		}
	}
	
}
