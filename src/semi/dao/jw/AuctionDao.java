package semi.dao.jw;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import semi.db.jw.JDBCUtil;

public class AuctionDao {
	
	public String enddate(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		//String sql="select a_enddate from auction where a_num=?";
		String sql="select to_char(hiredate,'YYYY-MM-DD HH24:MI:SS') date1 from emp where empno=?";
		try{
			con= JDBCUtil.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			String enddate =null;
			if(rs.next()) {
				enddate=rs.getString(1);
			}
			return enddate;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			JDBCUtil.close(rs, pstmt, con);
		}
		
	}
}
