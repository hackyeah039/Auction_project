package semi.dao.jw;

import java.sql.Connection; 
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.jdt.internal.compiler.batch.Main;

import semi.db.jw.ConnectionPool;
import semi.vo.jw.MainVo;

public class MainDao {
	private static MainDao instance = new MainDao();
	private MainDao() {};
	public static MainDao getInstance() {
		return instance;
	}
	
	//이미지경로
	public ArrayList<MainVo> ipath(int a_num) {
		Connection con=null;	
		PreparedStatement pstmt2=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getConn();
			String sql="select i_path from img where a_num =?";
			pstmt2=con.prepareStatement(sql);
			pstmt2.setInt(1, a_num);
			rs=pstmt2.executeQuery();
			ArrayList<MainVo> path = new ArrayList<MainVo>();
			while(rs.next()) {
				MainVo vo = new MainVo(rs.getString(1));
				System.out.println(rs.getString(1));
				path.add(vo);
			}
			return path;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			ConnectionPool.close(rs, pstmt2, con);
		}
	}
	//이미지 경로
	public ArrayList<String> ipath2(int a_num) {
		Connection con=null;	
		PreparedStatement pstmt2=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getConn();
			String sql="select i_path from img where a_num =?";
			pstmt2=con.prepareStatement(sql);
			pstmt2.setInt(1, a_num);
			rs=pstmt2.executeQuery();
			ArrayList<String> path =new ArrayList<String>();
			while(rs.next()) {
				path.add(rs.getString(1));
			}
			return path;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			ConnectionPool.close(rs, pstmt2, con);
		}
	}
	
	//카테고리
	public String cate(int a_num) {
		Connection con=null;	
		PreparedStatement pstmt2=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getConn();
			String sql="select c_des from category,auction where a_num=? and auction.c_num =category.c_num";
			pstmt2=con.prepareStatement(sql);
			pstmt2.setInt(1, a_num);
			rs=pstmt2.executeQuery();
			String cate ="";
			if(rs.next()) {
				cate = rs.getString(1);
				System.out.println(cate);
			}
			return cate;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			ConnectionPool.close(rs, pstmt2, con);
		}
	}
	
	//경매물품 상세정보 //CONTNET다 스펠링조심
	public MainVo info(int a_num) {
		Connection con=null;	
		PreparedStatement pstmt2=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getConn();
			String sql="select * from auction where a_num =?";
			pstmt2=con.prepareStatement(sql);
			pstmt2.setInt(1, a_num);
			rs=pstmt2.executeQuery();
			MainVo vo = null;
			while(rs.next()) {
				//int a_num,Date a_startdate,Date a_enddate,int a_startbid,int a_bidunit,String a_content,String a_title
				vo = new MainVo(rs.getInt("A_NUM"),rs.getDate("A_STARTDATE"),rs.getDate("A_ENDDATE"),
						rs.getInt("A_STARTBID"),rs.getInt("A_BIDUNIT"),rs.getString("A_CONTENT"),rs.getString("A_TITLE")
						);
				System.out.println(rs.getInt("A_NUM"));
				System.out.println(rs.getDate("A_STARTDATE"));
				System.out.println(rs.getDate("A_ENDDATE"));
				System.out.println(rs.getInt("A_STARTBID"));
				System.out.println(rs.getInt("A_BIDUNIT"));
				System.out.println(rs.getString("A_CONTENT"));
				System.out.println(rs.getString("A_TITLE"));
			}
			System.out.println("info저장완료");
			return vo;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			ConnectionPool.close(rs, pstmt2, con);
		}
	} 
	
	
	//배송방법
	public MainVo ship(int a_num) {
		Connection con=null;	
		PreparedStatement pstmt2=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getConn();
			String sql="select * from ship where a_num=?";
			pstmt2=con.prepareStatement(sql);
			pstmt2.setInt(1, a_num);
			rs=pstmt2.executeQuery();
			MainVo vo = null;
			while(rs.next()) {
				vo = new MainVo(rs.getInt("s_num"),rs.getString("s_way"),rs.getInt("s_price"),rs.getInt("a_num"));
				System.out.println(rs.getInt("s_num"));
				System.out.println(rs.getString("s_way"));
				System.out.println(rs.getInt("s_price"));
				System.out.println(rs.getInt("a_num"));
			}
			return vo;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			ConnectionPool.close(rs, pstmt2, con);
		}
	}
	
	public int seller(int a_num) {
		Connection con=null;	
		PreparedStatement pstmt2=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getConn();
			String sql="select seller.sel_number hi from seller,auction where a_num=? and auction.sel_number =seller.sel_number";
			pstmt2=con.prepareStatement(sql);
			pstmt2.setInt(1, a_num);
			rs=pstmt2.executeQuery();
			int seller = 0;
			if(rs.next()) {
				seller=rs.getInt("hi");
				System.out.println(seller+"셀러의 인트값입니다.");
			}
			return seller;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			ConnectionPool.close(rs, pstmt2, con);
		}
	}
	
	public int bidnum(int a_num) {
		Connection con=null;	
		PreparedStatement pstmt2=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getConn();
			String sql="select nvl(max(rownum),0) hi from bid where a_num=?";
			pstmt2=con.prepareStatement(sql);
			pstmt2.setInt(1, a_num);
			rs=pstmt2.executeQuery();
			int seller = 0;
			if(rs.next()) {
				seller=rs.getInt("hi");
				System.out.println(seller+"셀러의 인트값입니다.");
			}
			return seller;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			ConnectionPool.close(rs, pstmt2, con);
		}
	}
	
	public void jjim(int a_num,int m_num) {
		Connection con=null;	
		PreparedStatement pstmt2=null;
		PreparedStatement pstmt3=null;
		ResultSet rs=null;
		ResultSet rs2=null;
		try {
			con=ConnectionPool.getConn();
			String sql="update auction set a_jjim = (select a_jjim from auction where a_num=?)+1 where a_num=?";
			String sql2="insert into interproduct values(?,?) ";
			pstmt2=con.prepareStatement(sql);
			pstmt3=con.prepareStatement(sql);
			pstmt2.setInt(1, a_num);
			pstmt2.setInt(2, a_num);
			pstmt3.setInt(1, m_num);
			pstmt3.setInt(1, a_num);
			rs=pstmt2.executeQuery();
			rs2=pstmt3.executeQuery();
		}catch(SQLException se) {
			System.out.println(se.getMessage());
		}finally {
			ConnectionPool.close(rs, pstmt2, con);
			ConnectionPool.close(rs2, pstmt3, null);
		}
	}
	
//	public ArrayList<MainVo> queList(int a_num){
//		Connection con=null;	
//		PreparedStatement pstmt3=null;
//		ResultSet rs=null;
//		ArrayList<MainVo> list = new ArrayList<MainVo>();
//		try {
//			con=JDBCUtil.getConn();
//			String sql="select q.que_num a,que_title b,que_contnet c,m_num d, que_status e,que_regdate f,b_content g ,rownum h  from question q,answer x where x.que_num =q.que_num and q.a_num=? order by q.que_num asc";
//			pstmt3=con.prepareStatement(sql);
//			pstmt3.setInt(1, a_num);
//			rs=pstmt3.executeQuery();
//			while(rs.next()) {
//				MainVo vo = new MainVo(rs.getInt("a"),rs.getString("b"),rs.getString("c"),rs.getInt("d"),rs.getInt("e"),rs.getDate("f"),rs.getString("g"),rs.getInt("h"));
//				list.add(vo);
//			}
//		}catch(SQLException se) {
//			System.out.println(se.getMessage());
//		}finally {
//			JDBCUtil.close(rs, pstmt3, con);
//		}
//		return list;
//	}
	public ArrayList<MainVo> list(int startRow,int endRow,String keyword,int a_num){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getConn();
			String sql=null;
			System.out.println(keyword+"  dao안에서의 키워드");
			System.out.println(startRow +"   startRow입니다.");
			System.out.println(endRow +"   endRow입니다.");
			System.out.println(a_num +"   a_num입니다.");
			if(keyword==null || keyword.equals("")) {//검색조건이 없는경우
				sql="select * from" + 		
						"    (" + 
						"        select aa.*,rownum rnum from" + 
						"        (" + 
						"            select q.que_num a,que_title b,que_content c,m_num d, que_status e,que_regdate f,b_content g ,rownum h  from question q left join answer x on x.que_num =q.que_num and q.a_num=? order by q.que_num asc "+ 
						"        )aa" + 
						")where rnum>=? and  rnum<=?";
	
			}else {//검색조건이 있는 경우
				sql="select * from 		\r\n" + 
						"						(\r\n" + 
						"						select aa.*,rownum rnum from\r\n" + 
						"						(\r\n" + 
						" select q.que_num a,que_title b,que_content c,m_num d, que_status e,que_regdate f,b_content g ,rownum h  from question q,answer x where  que_title  like '%\"+keyword+\"%' and x.que_num =q.que_num and q.a_num=? order by q.que_num asc\r\n" + 
						"                     )aa \r\n" + 
						"						)where rnum>=? and  rnum<=?";
			}
		    pstmt=con.prepareStatement(sql);
		    pstmt.setInt(1,a_num);
			pstmt.setInt(2,startRow);
			pstmt.setInt(3,endRow);
			rs=pstmt.executeQuery();
			ArrayList<MainVo> list=new ArrayList<MainVo>();
			while(rs.next()) {
				System.out.println("바뀐값입니다!"+rs.getInt(1));
				System.out.println("바뀐값입니다!"+rs.getString(2));
				System.out.println("바뀐값입니다!"+rs.getString(3));
				System.out.println("바뀐값입니다!"+rs.getInt(4));
				System.out.println("바뀐값입니다!"+rs.getInt(5));
				System.out.println("바뀐값입니다!"+rs.getDate(6));
				System.out.println("바뀐값입니다!"+rs.getString(7));
				System.out.println("바뀐값입니다!"+rs.getInt(8));
				MainVo vo=new MainVo(rs.getInt("a"),rs.getString("b"),rs.getString("c"),rs.getInt("d"),rs.getInt("e"),rs.getDate("f"),rs.getString("g"),rs.getInt("h"));
				list.add(vo);
			}
			return list;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			ConnectionPool.close(rs, pstmt, con);
		}	
	}
	
	public int getCount(int a_num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="select nvl(count(*),0) a from  question where a_num=?";
		int a=0;
		try {
			con=ConnectionPool.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, a_num);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				a=rs.getInt(1);
			}
		}catch(SQLException se) {
			System.out.println(se.getMessage());
		}finally {
			ConnectionPool.close(rs, pstmt, con);
		}
		return a;
	}
	
	public void singo(int a_num,int mnum,String textarea) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="insert into singo values(?,?,SEQ_SINGO_SINGO_NUM.nextval,?,0,sysdate)";
		
		try {
			con=ConnectionPool.getConn();
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
		}catch(SQLException se) {
			System.out.println(se.getMessage());
		}finally {
			ConnectionPool.close(rs, pstmt, con);
		}
	}
}
