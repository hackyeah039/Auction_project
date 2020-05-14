package semi.controller.shdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.JDBCUtil;

public class CheckUpDao {
	public int checkup(int a_num) {
		int n=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			String sql="update auction set a_check=a_check+1  where a_num=?";
			con=JDBCUtil.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, a_num);
			n=pstmt.executeUpdate();
			return n;
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			JDBCUtil.close(null, pstmt, con);
		}
	}
}
