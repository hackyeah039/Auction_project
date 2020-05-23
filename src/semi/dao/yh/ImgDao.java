package semi.dao.yh;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import db.ConnectionPool;

public class ImgDao {
	private static ImgDao instance = new ImgDao();
	
	private ImgDao() {}
	
	public static ImgDao getInstance() {
		return instance;
	}
	//테이블 인서트 메소드 추가 
	public int fileListInsert(ArrayList<String> flist,int a_num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int n = 0;
		try {
			con = ConnectionPool.getConn();
			String sql = "insert into img values(?,?)";
			pstmt=con.prepareStatement(sql);
			for(String a:flist) {
				pstmt.setInt(1, a_num);
				pstmt.setString(2, a);
				n+=pstmt.executeUpdate();
			}
			return n;
		} catch (SQLException se) {
			se.printStackTrace();
			return -1;
		} finally {
			ConnectionPool.close(null, pstmt, con);
		}
	}
}
