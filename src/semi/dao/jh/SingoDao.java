package semi.dao.jh;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import semi.db.jh.ConnectionPool;
import semi.vo.jh.SingoVo;

public class SingoDao {
	private static SingoDao dao=new SingoDao();
	private SingoDao() {}
	public static SingoDao getSingoDao() {
		return dao;
	}
	
	//신고테이블 전체리스트 불러오기
	public ArrayList<SingoVo> singoAll(int startRow,int endRow){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getCon();
			String sql="select *\r\n" + 
					"from(select aa.*,rownum rnum\r\n" + 
					"from (select * from singo order by singo_num desc)aa)bb\r\n" + 
					"where rnum>=? and rnum <=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs=pstmt.executeQuery();
			ArrayList<SingoVo> list=new ArrayList<SingoVo>();
			while(rs.next()) {
				int sel_number=rs.getInt("sel_number");
				int m_num=rs.getInt("m_num");
				int singo_num=rs.getInt("singo_num");
				String singo_content=rs.getString("singo_content");
				int singo_status=rs.getInt("singo_status");
				Date singo_date=rs.getDate("singo_date");
				String id = singoId(m_num); //신고자 아이디 가져오는 메소드 사용
				String singoName=SingoProcess(singo_status);//신고상태 가져오는 메소드 사용
				SingoVo vo=new SingoVo(sel_number, m_num, singo_num, singo_content, 
						singo_status, singo_date,id,singoName);
				list.add(vo);
			}
			return list;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			}catch(SQLException s) {
				System.out.println(s.getMessage());
			}
		}
	}
	//신고자 아이디 가져오기
	public String singoId(int num) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getCon();
			String sql="select m.m_id singoja from singo s, members m "
					+ "where s.m_num=m.m_num and m.m_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			String id=null;
			if(rs.next()) {
				 id=rs.getString("singoja");//쿼리문에 알리안스
			}
			return id;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			}catch(SQLException s) {
				System.out.println(s.getMessage());
			}
		}
	}
	//int 값 넣어서 처리상태 뽑아오기
	public String SingoProcess(int num) {
		String str=null;//신고상태
		if(num==0) {
			str="처리중";
		}else if(num==1) {
			str="처리완료";
		}else if(num==2) {
			str="처리반려";
		}
		return str;
	}
	//신고리스트 전체 글번호 개수 가져오기
	public int getCount() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getCon();
			String sql="select nvl(count(*),0) count from singo";
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
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			}catch(SQLException s) {
				System.out.println(s.getMessage());
			}
		}
	}
	
	//관리자_신고페이지 상세리스트 가져오기
	public ArrayList<SingoVo> singoDetailList(int snum){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getCon();
			String sql="select * from singo where singo_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, snum);
			rs=pstmt.executeQuery();
			ArrayList<SingoVo> list=new ArrayList<SingoVo>();
			if(rs.next()) {
				int sel_number=rs.getInt("sel_number");
				int m_num=rs.getInt("m_num");
				int singo_num=rs.getInt("singo_num");
				String singo_content=rs.getString("singo_content");
				int singo_status=rs.getInt("singo_status");
				Date singo_date=rs.getDate("singo_date");
				int singoM_num=singoMnum(sel_number);//신고대상자 회원번호 가져오기
				String singoReId=singoRe(singoM_num);//신고대상자 아이디 가져오기
				SingoVo vo=new SingoVo(sel_number, singoM_num, singo_num, singo_content, 
						singo_status, singo_date, singoReId);
				list.add(vo);
			}
			return list;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			}catch(SQLException s) {
				System.out.println(s.getMessage());
			}
		}
	}
	//sel_number로 신고 대상자 회원번호 가져오기
	public int singoMnum(int selNumber) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getCon();
			String sql="select m_num from seller where sel_number=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, selNumber);
			rs=pstmt.executeQuery();
			int m_num=0;
			if(rs.next()) {
				m_num=rs.getInt("m_num");
			}
			return m_num;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			}catch(SQLException s) {
				System.out.println(s.getMessage());
			}
		}
	}
	//위에서 구한 신고대상자 회원번호로 아이디 가져오기
	public String singoRe(int num) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getCon();
			String sql="select DISTINCT m.m_id id" + 
					"from seller s,members m" + 
					"where s.m_num=m.m_num and s.m_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			String id=null;
			if(rs.next()) {
				id=rs.getString("id");
			}
			return id;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			}catch(SQLException s) {
				System.out.println(s.getMessage());
			}
		}
	}
	
	
	
}











