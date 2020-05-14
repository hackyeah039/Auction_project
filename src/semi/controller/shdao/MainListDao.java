package semi.controller.shdao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.JDBCUtil;
import semi.controller.shvo.SHAuctionVo;

public class MainListDao {
	//전체글 불러오는 메소드
	public ArrayList<SHAuctionVo> AllList(int startrow,int  endrow){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			String sql="select * from(select aa.*,rownum rnum from(\r\n" + 
					"select * from auction order by a_num asc , rownum asc) aa)\r\n" + 
					"where rnum>=? and rnum<=?";
			con=JDBCUtil.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs=pstmt.executeQuery();
			ArrayList<SHAuctionVo> list=new ArrayList<SHAuctionVo>();
			while(rs.next()) {
				int a_num=rs.getInt("a_num");
				String a_title=rs.getString("a_title");
				String a_content=rs.getString("a_content");
				int a_condition=rs.getInt("a_condition");
				Date a_regdate=rs.getDate("a_regdate");
				Date a_startdate=rs.getDate("a_startdate");
				Date a_enddate=rs.getDate("a_enddate");
				int a_check=rs.getInt("a_check");
				int c_num=rs.getInt("c_num");
				int a_jjim=rs.getInt("a_jjim");
				int sel_number=rs.getInt("sel_number");
				int bidstatus=rs.getInt("bidstatus");
				int a_startbid=rs.getInt("a_startbid");
				int a_bidunit=rs.getInt("a_bidunit");
				SHAuctionVo vo=new SHAuctionVo(
						a_num, a_title, a_content,
						a_condition, a_regdate, a_startdate,
						a_enddate, a_check, c_num, a_jjim,
						sel_number, bidstatus, a_startbid, a_bidunit);
				list.add(vo);
			}
			return list;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			JDBCUtil.close(rs, pstmt, con);
		}
	}
	//가격 가져오는 메소드
	public int getPrice(int num) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int price=0;
		try {
			String sql="select NVL(max(BID_PRICE),0) price FROM BID WHERE A_NUM=?";
			con=JDBCUtil.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				price=rs.getInt("price");
			}
			return price;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return 0;
		}finally {
			JDBCUtil.close(rs, pstmt, con);
		}
	}
	//전체글 수 가져오는 메소드
	public int getAllCnt() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int cnt=0;
		try {
			String sql="select NVL(count(a_num),0) cnt FROM auction";
			con=JDBCUtil.getConn();
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				cnt=rs.getInt("cnt");
			}
			return cnt;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			JDBCUtil.close(rs, pstmt, con);
		}
	}
	//판매자 아이디 가져오는 메소드
	public String getId(int num) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String id="";
		try {
			String sql="select m_id from members where m_num=?";
			con=JDBCUtil.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				id=rs.getString("m_id");
			}
			return id;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			JDBCUtil.close(rs, pstmt, con);
		}
	}
	//각 판매글의 입찰횟수 가져오는 메소드
	public int getBidCnt(int a_num) {
		int cnt=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			String sql="select count(*) cnt from bid where a_num=?";
			con=JDBCUtil.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, a_num);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				cnt=rs.getInt("cnt");
			}
			return cnt;
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			JDBCUtil.close(rs, pstmt, con);
		}
	}
}
