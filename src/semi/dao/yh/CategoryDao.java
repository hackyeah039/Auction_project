package semi.dao.yh;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.ConnectionPool;
import semi.vo.yh.CategoryVo;

public class CategoryDao {
	private static CategoryDao instance = new CategoryDao();
	
	private CategoryDao() {}
	
	public static CategoryDao getInstance() {
		return instance;
	}
	
	public ArrayList<CategoryVo> ShowList(){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ConnectionPool.getConn();
			String sql = "select * from category";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			ArrayList<CategoryVo> clist = new ArrayList<CategoryVo>();
			while(rs.next()) {
				int c_num = rs.getInt(1);
				String c_des = rs.getString(2);
				CategoryVo vo = new CategoryVo(c_num, c_des);
				clist.add(vo);
			}
			return clist;
		} catch (SQLException se) {
			se.printStackTrace();
			return null;
		} finally {
			ConnectionPool.close(rs, pstmt, con);
		}
	}
}
