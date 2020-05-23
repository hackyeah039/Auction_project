package semi.dao.jh;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import semi.db.jh.ConnectionPool;
import semi.vo.jh.MembersVo;

public class MembersDao {
	private static MembersDao dao=new MembersDao();
	private MembersDao() {}
	public static MembersDao getMembersDao() {
		return dao;
	}
	//===============================회원가입관련===============================//
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
					+ "?,?,?,?,?,?,5,0,sysdate)";
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
	
	//===============================탈퇴처리관련===============================//
	
	//전체 회원리스트(검색조건 있을때 or 없을때)
	public ArrayList<MembersVo> allMembersList(int startRow,int endRow,
			String field,String keyword){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getCon();
			String sql=null;
			if(field==null || field.equals("") || keyword==null || keyword.equals("")) {
				sql="select * from (select aa.*, rownum rnum from "
						+ "(select * from members order by m_num desc)aa) "
						+ "where rnum>=? and rnum<=?";
			}else {
				sql="select * " + 
					"from(select aa.*, rownum rnum " + 
					"from (select * from members where "+field+" like '%"+keyword+"%' "
							+ "order by m_num desc)aa) " + 
					"where rnum>=? and rnum<=?";
			}
			
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs=pstmt.executeQuery();
			ArrayList<MembersVo> list=new ArrayList<MembersVo>();
			while(rs.next()) {
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
				String typeName=getType(m_type);
				MembersVo vo=new MembersVo(m_num, m_name, m_email, m_phone,
						m_addr, m_id, m_pwd, trust, m_type, m_regdate,typeName);
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
	//전체 회원 글번호 가져오기(검색조건 있을 때 || 없을 때)
	public int getCount(String field,String keyword){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getCon();
			String sql=null;
			if(field==null || field.equals("") || keyword==null || keyword.equals("")) {
				//검색조건 없을 때
				sql="select nvl(count(*),0) count from members";
			}else {
				sql="select nvl(count(*),0) count from members where "
						+field+" like '%"+keyword+"%'";
			}
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
	//회원상태 가져오는 메소드
	public String getType(int num) {
		String str=null;
		if(num==0) {
			str="정상";
		}else if(num==1) {
			str="탈퇴처리중";
		}else if(num==2) {
			str="탈퇴";
		}
		return str;
	}
	
	//회원번호 넣어서 리스트 가지고오기
	public ArrayList<MembersVo> membersDetail(int num){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getCon();
			String sql="select * from members where m_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
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
				String typeName=getType(m_type);
				MembersVo vo=new MembersVo(m_num, m_name, m_email, m_phone,
						m_addr, m_id, m_pwd, trust, m_type, m_regdate,typeName);
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
	
	//회원탈퇴처리 메소드
	public int membersOut(int num) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=ConnectionPool.getCon();
			String sql="update members set m_name='null', m_email=null, "
					+ "m_phone=0, m_addr='null', " + 
					"m_pwd='null', trust=0, m_type=2 where m_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
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
	
		//전체 회원리스트_상태에 따른(검색조건 있을때 or 없을때)
		public ArrayList<MembersVo> allMembersList(int startRow,int endRow,
				String field,String keyword,int type){
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			try {
				con=ConnectionPool.getCon();
				String sql=null;
				if(field==null || field.equals("") || keyword==null || keyword.equals("")) {
					sql="select * from (select aa.*, rownum rnum from " + 
						"(select * from members where m_type="+type+" order by m_num desc)aa) " + 
						"where rnum>=? and rnum<=?";
				}else {
					sql="select * " + 
						"from(select aa.*, rownum rnum " + 
						"from (select * from members where "+field+" like '%"+keyword+"%' and m_type="+type+ 
						" order by m_num desc)aa) " + 
						"where rnum>=? and rnum<=?";
				}
				
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
				rs=pstmt.executeQuery();
				ArrayList<MembersVo> list=new ArrayList<MembersVo>();
				while(rs.next()) {
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
					String typeName=getType(m_type);
					MembersVo vo=new MembersVo(m_num, m_name, m_email, m_phone,
							m_addr, m_id, m_pwd, trust, m_type, m_regdate,typeName);
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
		
		//상태에 따른 전체 회원 글번호 가져오기(검색조건 있을 때 || 없을 때)
		public int getCount(String field,String keyword,int type){
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			try {
				con=ConnectionPool.getCon();
				String sql=null;
				if(field==null || field.equals("") || keyword==null || keyword.equals("")) {
					//검색조건 없을 때
					sql="select nvl(count(*),0) count from members where m_type="+type;
				}else {
					sql="select nvl(count(*),0) count from members where "
							+field+" like '%"+keyword+"%' and m_type="+type;
				}
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
		
		//===========================내정보관련 메소드==================================
		
		//회원정보 가져오는 메소드(정보수정 관련)
		public ArrayList<MembersVo> getMyInfo(int num){
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			try {
				con=ConnectionPool.getCon();
				String sql="select * from members where m_num=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, num);
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
					MembersVo vo=new MembersVo(m_num, m_name, m_email, m_phone, m_addr, 
							m_id, m_pwd, trust, m_type, m_regdate);
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
		
		//내정보_비밀번호 변경(현재 비밀번호와 체크)
		public String getPwd(int num) {
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			try {
				con=ConnectionPool.getCon();
				String sql="select m_pwd from members where m_num=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, num);
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
				try {
					if(rs!=null) rs.close();
					if(pstmt!=null) pstmt.close();
					if(con!=null) con.close();
				}catch(SQLException s) {
					System.out.println(s.getMessage());
				}
			}
		}
		
		//새로운 비밀번호로 변경하기
		public int changePWd(String pwd, int num) {
			Connection con=null;
			PreparedStatement pstmt=null;
			try {
				con=ConnectionPool.getCon();
				String sql="update members set m_pwd=? where m_num=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, pwd);
				pstmt.setInt(2, num);
				int n=pstmt.executeUpdate();
				return n;
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
		
		//회원정보 변경하기
		public int updateInfo(String id,String name,String addr, String phone) {
			Connection con=null;
			PreparedStatement pstmt=null;
			try {
				con=ConnectionPool.getCon();
				String sql="update members set m_name=?, m_addr=?, m_phone=? where m_id=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, name);
				pstmt.setString(2, addr);
				pstmt.setString(3, phone);
				pstmt.setString(4, id);
				int n=pstmt.executeUpdate();
				return n;
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
		//========================내정보 회원탈퇴==============================//
		//회원번호로 입찰중인 글 있는지 확인
		public ArrayList<Integer> getMembersOut(int num){
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			try {
				con=ConnectionPool.getCon();
				String sql="select * from auction a, "
						+ "(select sel_number from seller where m_num=?) s "
						+ "where a.sel_number=s.sel_number and a.bidstatus BETWEEN 1 and 2";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, num);
				rs=pstmt.executeQuery();
				ArrayList<Integer> list=new ArrayList<Integer>();
				while(rs.next()) {
					int sel_number=rs.getInt("sel_number");
					list.add(sel_number);
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
		
		public int updateOut(int num) {
			Connection con=null;
			PreparedStatement pstmt=null;
			try {
				con=ConnectionPool.getCon();
				String sql="update members set m_type=1, m_pwd='null' where m_num=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, num);
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
