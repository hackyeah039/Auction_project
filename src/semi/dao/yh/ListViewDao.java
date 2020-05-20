package semi.dao.yh;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.ConnectionPool;
import semi.vo.yh.AuctionVo;

public class ListViewDao {
	//검색 조건 추가 메소드
	private static ListViewDao instance = new ListViewDao();
	
	private ListViewDao() {}
	
	public static ListViewDao getInstance() {
		return instance;
	}
	public ArrayList<AuctionVo> SearchList(int startrow, int endrow, String field, String keyword){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ConnectionPool.getConn();
			String sql = null;
			//field 검색조건 비어있을 경우
			return vo;
		} catch (SQLException se) {
			se.printStackTrace();
			return null;
		}
	}
}
