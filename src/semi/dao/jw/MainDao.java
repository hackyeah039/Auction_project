package semi.dao.jw;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.jdt.internal.compiler.batch.Main;

import semi.db.jw.JDBCUtil;
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
			con=JDBCUtil.getConn();
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
			JDBCUtil.close(rs, pstmt2, con);
		}
	}
	//이미지 경로
	public ArrayList<String> ipath2(int a_num) {
		Connection con=null;	
		PreparedStatement pstmt2=null;
		ResultSet rs=null;
		try {
			con=JDBCUtil.getConn();
			String sql="select i_path from img where a_num =?";
			pstmt2=con.prepareStatement(sql);
			pstmt2.setInt(1, a_num);
			rs=pstmt2.executeQuery();
			ArrayList<String> path =new ArrayList<String>();
			while(rs.next()) {
				System.out.println("jpg이름입니다."+rs.getString(1).substring(59));
				path.add(rs.getString(1).substring(59));
			}
			return path;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			JDBCUtil.close(rs, pstmt2, con);
		}
	}
	
	//카테고리
	public String cate(int a_num) {
		Connection con=null;	
		PreparedStatement pstmt2=null;
		ResultSet rs=null;
		try {
			con=JDBCUtil.getConn();
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
			JDBCUtil.close(rs, pstmt2, con);
		}
	}
	
	//경매물품 상세정보 //CONTNET다 스펠링조심
	public MainVo info(int a_num) {
		Connection con=null;	
		PreparedStatement pstmt2=null;
		ResultSet rs=null;
		try {
			con=JDBCUtil.getConn();
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
			JDBCUtil.close(rs, pstmt2, con);
		}
	} 
	
	
	//배송방법
	public MainVo ship(int a_num) {
		Connection con=null;	
		PreparedStatement pstmt2=null;
		ResultSet rs=null;
		try {
			con=JDBCUtil.getConn();
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
			JDBCUtil.close(rs, pstmt2, con);
		}
	}
	
	public int seller(int a_num) {
		Connection con=null;	
		PreparedStatement pstmt2=null;
		ResultSet rs=null;
		try {
			con=JDBCUtil.getConn();
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
			JDBCUtil.close(rs, pstmt2, con);
		}
	}
}
