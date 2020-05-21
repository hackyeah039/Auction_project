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
	public ArrayList<AuctionVo> SearchList(int startRow, int endRow, String field, String keyword){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ConnectionPool.getConn();
			//상훈 - mainList에 sql구문만 바꿔서 주면 될듯 
			String sql = null;
			//field 검색조건 비어있을 경우
			if(field == null || field.equals("")) {
				sql = "select * from (select aa.*, rownum rn from(select a.*, m.m_id from auction a, seller s, members m where a.sel_number = s.sel_number and s.m_num = m.m_num and (a_enddate - sysdate) > 0 order by a_num desc) aa)where rn>=? and rn <=?";
			} else {
				sql = "select * from (select aa.*, rownum rn from(select a.*, m.m_id from auction a, seller s, members m where a.sel_number = s.sel_number and s.m_num = m.m_num and (a_enddate - sysdate) > 0 and " + field + " like '%" + keyword + "%' order by a_num desc) aa)where rn>=? and rn <=?";
			}
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			ArrayList<AuctionVo> list = new ArrayList<AuctionVo>();
			//그냥 꺼냄  
			while(rs.next()) {
				AuctionVo vo = new AuctionVo();
				list.add(vo);
			}
			return list;
		} catch (SQLException se) {
			se.printStackTrace();
			return null;
		} finally {
			ConnectionPool.close(con, pstmt, rs);
		}
	}
}
