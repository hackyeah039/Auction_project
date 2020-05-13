package semi.dao.jh;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import semi.db.jh.ConnectionPool;
import semi.vo.jh.MembersVo;

public class MembersDao {
	private static MembersDao dao=new MembersDao();
	private MembersDao() {}
	public static MembersDao getMembersDao() {
		return dao;
	}
	
	public int idCk(String id) { //아이디 중복검사
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=ConnectionPool.getCon();
			String sql="select * from members where m_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			}catch(SQLException s) {
				System.out.println(s.getMessage());
			}
		}
	}
	public int emailCk(String email) { //이메일 중복검사
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=ConnectionPool.getCon();
			String sql="select * from members where m_email=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, email);
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			}catch(SQLException s) {
				System.out.println(s.getMessage());
			}
		}
	}
	//멤버회원가입
	public int insertMembers(MembersVo vo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=ConnectionPool.getCon();
			String sql="insert into members values(seq_members_m_num.nextval,"
					+ "?,?,?,?,?,?,5,0,sysdate);";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, vo.getM_name());
			pstmt.setString(2, vo.getM_email());
			pstmt.setInt(3, vo.getM_phone());
			pstmt.setString(4, vo.getM_addr());
			pstmt.setString(5, vo.getM_id());
			pstmt.setString(6, vo.getM_pwd());
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			}catch(SQLException s) {
				System.out.println(s.getMessage());
			}
		}
	}
}
