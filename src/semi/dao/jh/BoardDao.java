package semi.dao.jh;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import semi.db.jh.ConnectionPool;
import semi.vo.jh.B_answerVo;
import semi.vo.jh.BoardVo;

public class BoardDao {
	private static BoardDao dao=new BoardDao();
	private BoardDao() {}
	public static BoardDao getBoardDao() {
		return dao;
	}
	
	//보드테이블 전체 글목록 담아오기
	public ArrayList<BoardVo> allBoard(int startRow,int endRow){
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
	
	//상태에 따른 전체 글개수 가져오기(검색조건 없을 때)
	public int getCount(int type) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getCon();
			String sql="select nvl(count(*),0) count from board where b_status=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, type);
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
	//상태에 따른 전체 글개수 가져오기(검색조건 있을 때)
	public int getCount(int type,String field, String keyword) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getCon();
			String sql="select nvl(count(*),0) count " + 
					"from(SELECT b.*, m.m_id id from board b,members m " + 
					"where b.m_num=m.m_num and b_status=?) " + 
					"where "+field+" like '%"+keyword+"%'";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, type);
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
	
	
	
		//보드테이블 검색조건 있을 때 전체글목록 가져오기(상태에 따라서)
		public ArrayList<BoardVo> allBoard(int startRow,int endRow, int type,
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
						"where "+field+" like '%"+keyword+"%' and b_status=?) " + 
						"where rnum>=? and rnum<=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, type);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
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
	
	//보드테이블 상세 페이지 내용 가져오기
	public ArrayList<BoardVo> boardDetail(int num){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getCon();
			String sql="select * from board where b_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			ArrayList<BoardVo> list=new ArrayList<BoardVo>();
			if(rs.next()) {
				int b_num=rs.getInt("b_num");
				String b_title=rs.getString("b_title");
				String b_content=rs.getString("b_content");
				int b_status=rs.getInt("b_status");
				int m_num=rs.getInt("m_num");
				Date b_regdate=rs.getDate("b_regdate");
				BoardVo vo=new BoardVo(b_num, b_title, b_content, b_status, m_num, b_regdate);
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
	//답글가져오기
	public ArrayList<B_answerVo> dapDetail(int num){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getCon();
			String sql="select * from b_answer where b_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			ArrayList<B_answerVo> list=new ArrayList<B_answerVo>();
			if(rs.next()) {
				String b_dap=rs.getString("b_dap");
				int b_num=rs.getInt("b_num");
				Date answerdate=rs.getDate("answerdate");
				B_answerVo vo=new B_answerVo(b_dap, b_num, answerdate);
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
	
	//관리자 보드테이블 답변달기, 상태변경
	public int boardAnswer(int b_num,String b_dap) {
		Connection con=null;
		PreparedStatement pstmt=null;
		PreparedStatement pstmt2=null;
		try {
			con=ConnectionPool.getCon();
			con.setAutoCommit(false);//자동커밋해제
			String sql="insert into b_answer values(?,?,sysdate)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, b_dap);
			pstmt.setInt(2, b_num);
			String sql2="update board set b_status=1 where b_num=?";
			pstmt2=con.prepareStatement(sql2);
			pstmt2.setInt(1, b_num);
			int i=pstmt.executeUpdate();
			int j=pstmt2.executeUpdate();
			con.commit();
			return i+j;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			try{
				if(pstmt!=null) pstmt.close();
				if(pstmt2!=null) pstmt2.close();
				if(con!=null) con.close();
			}catch(SQLException s) {
				System.out.println(s.getMessage());
			}
		}
	}
	//답변가져오기
	public ArrayList<B_answerVo> adminAnswer(int num){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getCon();
			String sql="select * from b_answer where b_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			ArrayList<B_answerVo> list=new ArrayList<B_answerVo>();
			if(rs.next()) {
				String b_dap=rs.getString("b_dap");
				int b_num=rs.getInt("b_num");
				Date answerdate=rs.getDate("answerdate");
				B_answerVo vo=new B_answerVo(b_dap, b_num, answerdate);
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
	
	//상태에 따른 qna(board테이블) 전체리스트 가져오기
	public ArrayList<BoardVo> allBoard(int startRow,int endRow,int type){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getCon();
			String sql="select * from(select bb.*, rownum rnum " + 
					"from (SELECT b.*, m.m_id id from board b,members m " + 
					"where b.m_num=m.m_num and b_status=?  order by b_num desc)bb) " + 
					"where rnum>=? and rnum<=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, type);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
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
	
	
	
	
	
}
