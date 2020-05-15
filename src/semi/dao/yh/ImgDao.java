package semi.dao.yh;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.JdbcUtil;

public class ImgDao {
	private static ImgDao instance = new ImgDao();
	
	private ImgDao() {}
	
	public static ImgDao getInstance() {
		return instance;
	}
	//파일 업로드
	public int fileListInsert(ArrayList<String> flist,int a_num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int n = 0;
		try {
			con = JdbcUtil.getConn();
			String sql = "insert from auction values(?,?)";
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
			JdbcUtil.close(con, pstmt, null);
		}
	}
}
