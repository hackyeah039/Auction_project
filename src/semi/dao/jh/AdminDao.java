package semi.dao.jh;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import semi.db.jh.ConnectionPool;

public class AdminDao {
	private static AdminDao dao=new AdminDao();
	private AdminDao() {}
	public static AdminDao getAdminDao() {
		return dao;
	}
	//신고검토중인 리스트 개수 들고오기
	public int getSingoCount() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getCon();
			String sql="select nvl(count(*),0) count from singo where singo_status=0";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			int count=0;
			if(rs.next()) {
				count= rs.getInt("count");
			}
			return count;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
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
	//관리자 문의사항 답변중인 리스트 개수
	public int getQnaCount() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getCon();
			String sql="select nvl(count(*),0) count from board where b_status=0";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			int count=0;
			if(rs.next()) {
				count= rs.getInt("count");
			}
			return count;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
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
	
	//회원탈퇴 처리중 리스트 개수 가져오기
		public int getMembersOutCount() {
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			try {
				con=ConnectionPool.getCon();
				String sql="select nvl(count(*),0) count from members where m_type=1";
				pstmt=con.prepareStatement(sql);
				rs=pstmt.executeQuery();
				int count=0;
				if(rs.next()) {
					count= rs.getInt("count");
				}
				return count;
			}catch(SQLException se) {
				System.out.println(se.getMessage());
				return -1;
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
