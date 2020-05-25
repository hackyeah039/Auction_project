package semi.controller.shdao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.ConnectionPool;
import semi.controller.shvo.SHAuctionVo;

public class MainListDao {
	//전체글 불러오는 메소드
	public ArrayList<SHAuctionVo> AllList(int startrow,int  endrow){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			String sql="select * from(select aa.*,rownum rnum from(\r\n" + 
					"select * from auction order by a_num desc , rownum asc) aa)\r\n" + 
					"where rnum>=? and rnum<=?";
			con=ConnectionPool.getConn();
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
			ConnectionPool.close(rs, pstmt, con);
		}
	}
	//검색결과 불러오는 메소드
	public ArrayList<SHAuctionVo> SearchList(int startrow,int endrow,String field,String keyword){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql = null;
		try {
			con=ConnectionPool.getConn();
			if(keyword == "" || keyword.equals("")) {
				sql =  "select * from (select aa.*, rownum rnum from("
						+" select a.*, m.m_id from auction a, seller s, members m"
						+ " where a.sel_number = s.sel_number and s.m_num = m.m_num and (a_enddate - sysdate) > 0 ORDER BY a_num desc) aa)where rnum>=? and rnum <=?";								
			} else {
				sql =  "select * from (select aa.*, rownum rnum from("
						+" select a.*, m.m_id from auction a, seller s, members m"
						+ " where a.sel_number = s.sel_number and s.m_num = m.m_num and (a_enddate - sysdate) > 0 and "+field+" like '%" +keyword+ "%' ORDER BY a_num desc) aa)where rnum>=? and rnum <=?";				
			}

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
			ConnectionPool.close(rs, pstmt, con);
		}
	}
	//인기글 불러오는 메소드(조회수 높은 순)
	public ArrayList<SHAuctionVo> CheckList(int startrow,int  endrow){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			String sql="select * from(select aa.*,rownum rnum from(select * from auction where (a_enddate-sysdate)>0 order by a_check desc , rownum asc) aa)where rnum>=? and rnum<=?";
			con=ConnectionPool.getConn();
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
			ConnectionPool.close(rs, pstmt, con);
		}
	}
	//추천글 불러오는 메소드(찜 수 높은 순)
	public ArrayList<SHAuctionVo> JjimList(int startrow,int  endrow){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			String sql="select * from(select aa.*,rownum rnum from(select * from auction where (a_enddate-sysdate)>0 order by a_jjim desc , rownum asc) aa)where rnum>=? and rnum<=?";
			con=ConnectionPool.getConn();
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
			ConnectionPool.close(rs, pstmt, con);
		}
	}
	//마감임박글 불러오는 메소드(찜 수 높은 순)
	public ArrayList<SHAuctionVo> EndList(int startrow,int  endrow){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			String sql="select * from(select aa.*,rownum rnum from(select * from auction where (a_enddate-sysdate)>0 order by (a_enddate-sysdate) asc , rownum asc) aa)where rnum>=? and rnum<=?";
			con=ConnectionPool.getConn();
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
			ConnectionPool.close(rs, pstmt, con);
		}
	}
	//최고 입찰가격 가져오는 메소드
	public int getPrice(int num) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int price=0;
		try {
			String sql="select NVL(max(BID_PRICE),0) price FROM BID WHERE A_NUM=?";
			con=ConnectionPool.getConn();
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
			ConnectionPool.close(rs, pstmt, con);
		}
	}
	//시작가격 가져오는 메소드
	public int getStartBid(int num) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int price=0;
		try {
			String sql="select a_startbid FROM auction WHERE A_NUM=?";
			con=ConnectionPool.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				price=rs.getInt("a_startbid");
			}
			return price;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return 0;
		}finally {
			ConnectionPool.close(rs, pstmt, con);
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
			con=ConnectionPool.getConn();
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
			ConnectionPool.close(rs, pstmt, con);
		}
	}
	
	public int getSerchCnt(String keyword, String field) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int cnt=0;
		try {
			String sql="select NVL(count(a_num),0) cnt from( select a.*, m.m_id from auction a, seller s, members m where a.sel_number = s.sel_number and s.m_num = m.m_num and (a_enddate - sysdate) > 0 and " + field + " like '%" + keyword + "%' ORDER BY a_num desc)";	
			con=ConnectionPool.getConn();
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
			ConnectionPool.close(rs, pstmt, con);
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
			con=ConnectionPool.getConn();
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
			ConnectionPool.close(rs, pstmt, con);
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
			con=ConnectionPool.getConn();
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
			ConnectionPool.close(rs, pstmt, con);
		}
	}
	//날짜와 시간 가져오는 메소드
	public String getTime(int a_num) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String time="";
		try {
			String sql="select to_char(a_enddate,'YYYYMMDDHH24MISS') time from auction where a_num=?";
			con=ConnectionPool.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, a_num);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				time=rs.getString("time");
			}
			return time;
		}catch(SQLException se) {
			se.printStackTrace();
			return null;
		}finally {
			ConnectionPool.close(rs, pstmt, con);
		}
	}
	//이미지 패스 가져오는 메소드
	public String getImgPath(int a_num) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String path="";
		try {
			String sql="select i_path from img where a_num=?";
			con=ConnectionPool.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, a_num);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				path=rs.getString("i_path");
			}
			return path;
		}catch(SQLException se) {
			se.printStackTrace();
			return null;
		}finally {
			ConnectionPool.close(rs, pstmt, con);
		}
	}
	
	//마감시간이 되면 거래진행중 상태로 바꾸는 메소드
	public int updateBidStatus() {
		int n=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		PreparedStatement pstmt2=null;
		PreparedStatement pstmt3=null;
		PreparedStatement pstmt4=null;
		PreparedStatement pstmt5=null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		
		try {
			con=ConnectionPool.getConn();
			String sql = "select * from auction where (a_enddate-sysdate)<=0 and bidstatus = 1";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				do {
					int a_num = rs.getInt("a_num");
					String sql1 =  "select nvl(count(*),0) count from bid where a_num = ?";
					pstmt2 = con.prepareStatement(sql1);
					pstmt2.setInt(1, a_num);
					rs2 = pstmt2.executeQuery();
					if(rs2.next()) {
						int count = rs2.getInt("count");
						if(count == 0) {
							String sql3 =  "update auction set bidstatus = 3 where a_num =?";
							pstmt3 = con.prepareStatement(sql3);
							pstmt3.setInt(1, a_num);
							n = pstmt3.executeUpdate();
						}else {
							String sql3 =  "update auction set bidstatus = 2 where a_num =?";
							pstmt3 = con.prepareStatement(sql3);
							pstmt3.setInt(1, a_num);
							int m = pstmt3.executeUpdate();
							if(m>0) {
								String sql4 =  "select bid_number from (select max(bid_price) max,a_num from bid group by a_num having a_num = ?) aa, bid" + 
										"where aa.a_num = bid.a_num and bid_price = aa.max";
								pstmt4 = con.prepareStatement(sql4);
								pstmt4.setInt(1, a_num);
								rs3 = pstmt4.executeQuery();
								if(rs3.next()) {
									int bid_number = rs3.getInt("bid_number");
									String sql5 = "insert payment values(SEQ_PAYMENT_PAY_NUM.nextval,null,0,?,null,null,null)";
									pstmt5 = con.prepareStatement(sql5);
									pstmt5.setInt(1, bid_number);
									n = pstmt5.executeUpdate();
								}
							}else {
								return 0;
							}
						}
					}
				}while(rs.next());
			}
			
			return n;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return 0;
		}finally {
			ConnectionPool.close(null, pstmt, con);
		}
	}
	
	//입찰 할 시간이 되면 1이되도록
	public int getStartAuction() {
		int n=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		PreparedStatement pstmt2=null;
		ResultSet rs=null;
		try {
			String sql="select * from auction where a_startdate <= sysdate";
			con=ConnectionPool.getConn();
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				int anum= rs.getInt("a_num");
				String sql2 = "update auction set bidstatus = 1 where a_num = ? and bidstatus = 0";
				pstmt2 = con.prepareStatement(sql2);
				pstmt2.setInt(1, anum);
				n = pstmt2.executeUpdate();
				return n;
			}
			return -1;
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			ConnectionPool.close(rs, pstmt, con);
		}
	}
}
