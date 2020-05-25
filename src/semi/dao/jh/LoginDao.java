package semi.dao.jh;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import semi.db.jh.ConnectionPool;
import semi.vo.jh.MembersVo;

public class LoginDao {
	private static final String String = null;
	private static LoginDao dao=new LoginDao();
	private LoginDao() {}
	public static LoginDao getLoginDao() {
		return dao;
	}
	//로그인체크
	public ArrayList<MembersVo> loginCk(String id,String pwd) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getCon();
			String sql="select * from members where m_id=? and m_pwd=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			rs=pstmt.executeQuery();
			ArrayList<MembersVo> list=new ArrayList<MembersVo>();
			if(rs.next()) {
				int m_num=rs.getInt("m_num");
				String m_name=rs.getString("m_name");
				String m_email=rs.getString("m_email");
				int m_phone=rs.getInt("m_phone");
				String m_addr=rs.getString("m_addr");
				String m_id=rs.getString("m_id");
				String m_pwd=rs.getString("m_pwd");
				int trust=rs.getInt("trust");
				int m_type=rs.getInt("m_type");
				Date m_regdate=rs.getDate("m_regdate");
				MembersVo vo=new MembersVo(m_num, m_name, m_email, 
						m_phone, m_addr, m_id, m_pwd, trust, m_type, 
						m_regdate);
				list.add(vo);
			}
			return list;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			try{
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			}catch(SQLException s) {
				System.out.println(s.getMessage());
			}
		}
	}
	//아이디찾기
	public String findId(String name, String email) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String m_id=null;
		try {
			con=ConnectionPool.getCon();
			String sql="select * from members where m_name=? and m_email=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				m_id=rs.getString("m_id");
			}
			return m_id;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			try{
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			}catch(SQLException s) {
				System.out.println(s.getMessage());
			}
		}
	}
	
	//비밀번호 찾기
		public String findPwd(String id,String name, String phone) {
			System.out.println("dao로 받아온 아이디"+id);
			System.out.println("dao로 받아온 네임"+name);
			System.out.println("dao로 받아온 폰"+phone);
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			try {
				con=ConnectionPool.getCon();
				String sql="select * from members where m_id=? and m_name=? and m_phone=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.setString(2, name);
				pstmt.setString(3, phone);
				rs=pstmt.executeQuery();
				String m_pwd=null;
				if(rs.next()) {
					m_pwd=rs.getString("m_pwd");
				}
				return m_pwd;
			}catch(SQLException se) {
				System.out.println(se.getMessage());
				return null;
			}finally {
				try{
					if(rs!=null) rs.close();
					if(pstmt!=null) pstmt.close();
					if(con!=null) con.close();
				}catch(SQLException s) {
					System.out.println(s.getMessage());
				}
			}
		}
	
	
}
