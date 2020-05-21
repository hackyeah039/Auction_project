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
			String sql="select * from (select aa.*, rownum rnum from (select * from singo order by singo_num desc)aa) bb " + 
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
	
	//신고테이블 전체리스트 불러오기(검색조건 있을 때)
		public ArrayList<SingoVo> singoAllSearch(int startRow,int endRow, String field,String keyword){
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			try {
				con=ConnectionPool.getCon();
				String sql="select * from (select sin.*, rownum rnum, sinid "
						+ "from singo_view sin, selid_view sel "
						+ "where sin.sel_number=sel.sel_number and "+field+" like '%"+keyword+"%') "
						+ "where rnum>=? and rnum <=?";
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
				//System.out.println("검색조건전제글개수:"+allRow);
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
	
	//신고테이블 상태에 따른 리스트 불러오기(검색조건x)
	public ArrayList<SingoVo> singoDoing(int startRow,int endRow,int status){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getCon();
			String sql="select * from (select aa.*, rownum rnum from "
					+ "(select * from singo order by singo_num desc)aa where singo_status=?) bb " + 
					"where rnum>=? and rnum <=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, status);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
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
	
	//신고테이블 상태에 따른 리스트 불러오기(검색조건o)
		public ArrayList<SingoVo> singoDoing(int startRow,int endRow,int status,
				String field,String keyword){
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			try {
				con=ConnectionPool.getCon();
				String sql="select * from (select sin.*, rownum rnum, sinid "
						+ "from singo_view sin, selid_view sel "
						+ "where sin.sel_number=sel.sel_number "
						+ "and "+field+" like '%"+keyword+"%' and singo_status=?) "
						+ "where rnum>=? and rnum <=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, status);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
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
	
	//신고리스트 검색조건 있을 때 전체 글번호 개수 가져오기
		public int getCountSearch(String field,String keyword) {
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			try {
				con=ConnectionPool.getCon();
				String sql="select nvl(count(*),0) count from(select bb.*, mm.m_id id from members mm, (select aa.*, se.m_num 대상자회원번호, rownum rnum from seller se, (select s.*, m_id singojaId from singo s, members m where s.m_num=m.m_num order by s.singo_num desc)aa where aa.sel_number=se.sel_number)bb where mm.m_num=bb.대상자회원번호 and "+field+" like '%"+keyword+"%')";
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
	
	//상태에 따른 신고리스트 전체 글번호 개수 가져오기(검색조건 없을 때)
		public int getCount(int singo_status) {
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			try {
				con=ConnectionPool.getCon();
				String sql="select nvl(count(*),0) count "
						+ "from singo where singo_status=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, singo_status);
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
	
	//상태에 따른 신고리스트 글번호개수 가져오기(검색조건 있을 때)
		public int getCount(int singo_status,String field,String keyword) {
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			try {
				con=ConnectionPool.getCon();
				String sql="select nvl(count(*),0) count from "
						+ "(select * from (select sin.*, rownum rnum, sinid "
						+ "from singo_view sin, selid_view sel "
						+ "where sin.sel_number=sel.sel_number "
						+ "and "+field+" like '%"+keyword+"%' and singo_status=?))";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, singo_status);
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
					" from seller s, members m " + 
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
	//신고자 아이디 받아서 신뢰도 가져오기
	public int getTrust(String id) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getCon();
			String sql="select * from members where m_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			int trust=0;
			if(rs.next()) {
				trust=rs.getInt("trust");
			}
			return trust;
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
	//신고자 아이디 받아서 신뢰도 깍기&&신고테이블 처리완료 상태로 변경--처리상태:1
	public int trustDown(String id,int singoNum) {
		Connection con=null;
		PreparedStatement pstmt=null;
		PreparedStatement pstmt2=null;
		try {
			con=ConnectionPool.getCon();
			con.setAutoCommit(false);//트랜잭션처리 자동커밋 해제
			String sql="update members set trust=(trust-1) where m_id=?";
			String sql2="update singo set singo_status=1 where singo_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt2=con.prepareStatement(sql2);
			pstmt.setString(1, id);
			pstmt2.setInt(1, singoNum);
			int n=pstmt.executeUpdate();
			int j=pstmt2.executeUpdate();
			con.commit();
			return n+j;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(pstmt2!=null) pstmt2.close();
				if(con!=null) con.close();
			}catch(SQLException s) {
				System.out.println(s.getMessage());
			}
		}
	}

	//신고테이블 처리반려 상태로 변경--처리상태:2
	public int trustStay(int singoNum) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=ConnectionPool.getCon();
			String sql="update singo set singo_status=2 where singo_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, singoNum);
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











