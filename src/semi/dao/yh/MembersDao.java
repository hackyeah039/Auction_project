package semi.dao.yh;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.ConnectionPool;
import semi.vo.yh.MembersVo;

public class MembersDao {
	private static MembersDao instance = new MembersDao();
	
	private MembersDao() {}
	
	public static MembersDao getInstance() {
		return instance;
	}
	//회원 조회
	public MembersVo SearchMember(int m_num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ConnectionPool.getConn();
			String sql = "select * from members where m_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, m_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				MembersVo vo = new MembersVo(
					rs.getInt("m_num"),
					rs.getString("m_name"),
					rs.getString("m_email"),
					rs.getInt("m_phone"),
					rs.getString("m_addr"),
					rs.getString("m_id"),
					rs.getString("m_pwd"),
					rs.getInt("trust"),
					rs.getInt("m_type"),
					rs.getDate("m_regdate")
				);			
				return vo;
			}
			return null;
		} catch (SQLException se) {
			se.printStackTrace();
			return null;
		} finally {
			ConnectionPool.close(rs, pstmt, con);
		}
	}
	//신뢰도 조회
	public int ShowTrust(int m_num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ConnectionPool.getConn();
			String sql = "select trust from members where m_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, m_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {			
				return rs.getInt("trust");
			}
			return 0;
		} catch (SQLException se) {
			se.printStackTrace();
			return -1;
		} finally {
			ConnectionPool.close(rs, pstmt, con);
		}
	}
}
