package semi.dao.jh;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import semi.db.jh.ConnectionPool;
import semi.vo.jh.BoardVo;

public class BoardDao {
	private static BoardDao dao=new BoardDao();
	private BoardDao() {}
	public static BoardDao getBoardDao() {
		return dao;
	}
	
	//보드테이블 전체 글목록 담아오기
	public ArrayList<BoardVo> allBoard(int startRow,int endRow){
		System.out.println("dao실행");
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getCon();
			String sql="select * from(select bb.*, rownum rnum "
						+ "from (SELECT b.*, m.m_id id from board b,members m "
						+ "where b.m_num=m.m_num order by b_num desc)bb) "
						+ "where rnum>=? and rnum<=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs=pstmt.executeQuery();
			ArrayList<BoardVo> list=new ArrayList<BoardVo>();
			while(rs.next()) {
				int b_num=rs.getInt("b_num");
				String b_title=rs.getString("b_title");
				String b_content=rs.getString("b_content");
				int b_status=rs.getInt("b_status");
				int m_num=rs.getInt("m_num");
				Date b_regdate=rs.getDate("b_regdate");
				String m_id=rs.getString("id");
				String bName=BoardProcess(b_status);
				BoardVo vo=new BoardVo(b_num, b_title, b_content, b_status, m_num, 
						b_regdate,m_id,bName);
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
	
	//전체 글개수 가져오기
	public int getCount() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getCon();
			String sql="select nvl(count(*),0) count from board";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			int count=0;
			if(rs.next()) {
				count=rs.getInt("count");
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
	
	//보드테이블 검색조건 있을 때 전체글목록 가져오기
		public ArrayList<BoardVo> allBoard(int startRow,int endRow,
				String field,String keyword){
			System.out.println("dao실행");
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			try {
				con=ConnectionPool.getCon();
				String sql="select * from (select aa.*, rownum rnum " + 
						"from (select b.*, m.m_id id from board b, members m " + 
						"where b.m_num=m.m_num order by b_num desc)aa " + 
						"where "+field+" like '%"+keyword+"%') " + 
						"where rnum>=? and rnum<=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
				rs=pstmt.executeQuery();
				ArrayList<BoardVo> list=new ArrayList<BoardVo>();
				while(rs.next()) {
					int b_num=rs.getInt("b_num");
					String b_title=rs.getString("b_title");
					String b_content=rs.getString("b_content");
					int b_status=rs.getInt("b_status");
					int m_num=rs.getInt("m_num");
					Date b_regdate=rs.getDate("b_regdate");
					String m_id=rs.getString("id");
					String bName=BoardProcess(b_status);
					BoardVo vo=new BoardVo(b_num, b_title, b_content, b_status, m_num, 
							b_regdate,m_id,bName);
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
	
	
	
	
	
	//검색조건 있을 때 글의 개수구하기
	public int getCount(String field,String keyword) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			con=ConnectionPool.getCon();
			String sql="select nvl(count(*),0) count " + 
					"from(SELECT b.*, m.m_id id from board b,members m " + 
					"where b.m_num=m.m_num) " + 
					"where "+field+" like '%"+keyword+"%'";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			int count=0;
			if(rs.next()) {
				count=rs.getInt("count");
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
	
	
	
	
	
	//답글상태 가져오는 메소드
	public String BoardProcess(int num) {
		String str=null;//답변상태
		if(num==0) {
			str="미답변";
		}else if(num==1) {
			str="답변완료";
		}
		return str;
	}
	
	
	
	
	
	
	
	
	
	
}
