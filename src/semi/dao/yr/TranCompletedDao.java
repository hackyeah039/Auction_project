package semi.dao.yr;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import semi.db.yr.ConnectionPool;
import semi.vo.yr.AuctionVo;
import semi.vo.yr.BidVo;

public class TranCompletedDao {

	// 판매자a num 가져오기
	public ArrayList<AuctionVo> getCompletedTranForSeller(ArrayList<Integer> selnumList) {
		ArrayList<AuctionVo> completedTranList = new ArrayList<AuctionVo>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ConnectionPool.getCon();

			for (int selnum : selnumList) {
				String sql = "select * from auction where bidstatus = 3 and sel_number = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, selnum);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					do {
						int aNum = rs.getInt("a_num"); // 물품번호
						String aTitle = rs.getString("a_title"); // 제목
						String acontent = rs.getString("a_content"); 
						int acondition = rs.getInt("a_condition"); // 물품번호
						Date regDate = rs.getDate("a_regdate"); 
						Date startDate = rs.getDate("a_startdate"); 
						Date endDate = rs.getDate("a_enddate"); // 마감일
						int aCheck = rs.getInt("a_check"); // 조회수
						int cnum = rs.getInt("c_num"); // 조회수
						int ajjim = rs.getInt("a_jjim");
						int selNum = rs.getInt("sel_number");
						int bidstatus = rs.getInt("bidstatus");
						int startbid = rs.getInt("a_startbid");
						int abidunit = rs.getInt("a_bidunit");
						
						completedTranList.add(new AuctionVo(aNum, aTitle, acontent, acondition, regDate, startDate, endDate, aCheck, selNum, ajjim, selNum, bidstatus, startbid, abidunit));

					} while (rs.next());
				}
			}
			return completedTranList;
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return null;
		} finally {
			ConnectionPool.close(rs, pstmt, con);
		}
	}

	// 구매자 anum 가져오기
	public ArrayList<AuctionVo> getCompletedTran(int m_num) {
		ArrayList<AuctionVo> completedTranList = new ArrayList<AuctionVo>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ConnectionPool.getCon();
			String sql = "select * from ("
					+ "select DISTINCT auction.a_num from auction, bid where bid.a_num = auction.a_num and bid.m_num = ? and bidstatus = 3"
					+ ")aa, auction where aa.a_num = auction.a_num";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, m_num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {
					int aNum = rs.getInt("a_num"); // 물품번호
					String aTitle = rs.getString("a_title"); // 제목
					String acontent = rs.getString("a_content"); 
					int acondition = rs.getInt("a_condition"); // 물품번호
					Date regDate = rs.getDate("a_regdate"); 
					Date startDate = rs.getDate("a_startdate"); 
					Date endDate = rs.getDate("a_enddate"); // 마감일
					int aCheck = rs.getInt("a_check"); // 조회수
					int cnum = rs.getInt("c_num"); // 조회수
					int ajjim = rs.getInt("a_jjim");
					int selNum = rs.getInt("sel_number");
					int bidstatus = rs.getInt("bidstatus");
					int startbid = rs.getInt("a_startbid");
					int abidunit = rs.getInt("a_bidunit");
					
					completedTranList.add(new AuctionVo(aNum, aTitle, acontent, acondition, regDate, startDate, endDate, aCheck, selNum, ajjim, selNum, bidstatus, startbid, abidunit));

				} while (rs.next());
			}
			return completedTranList;
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return null;
		} finally {
			ConnectionPool.close(rs, pstmt, con);
		}
	}

	// bid 가져오기
	public BidVo getBidVo(int anum) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ConnectionPool.getCon();
			String sql = "select * from (select max(bid_price) max,a_num from bid group by a_num having a_num = ?) aa, bid "
					+ "where aa.a_num = bid.a_num and bid_price = aa.max ";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, anum);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				do {
					int mnum = rs.getInt("m_num");
					int anum2 = rs.getInt("a_num");
					int bidPrice = rs.getInt("bid_price");
					Date bidDate = rs.getDate("bid_date");
					int bidnum = rs.getInt("bid_number");

					return new BidVo(mnum, anum2, bidPrice, bidDate, bidnum);
				} while (rs.next());
			}

			return null;
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return null;
		} finally {
			ConnectionPool.close(rs, pstmt, con);
		}
	}
	
	
	// 입찰 수 가져오기
	public int getCountBid(int anum) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ConnectionPool.getCon();
			String sql = "select count(a_num) count from bid where a_num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, anum);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				return rs.getInt("count");
					
			}else {
				return 0;				
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return 0;
		} finally {
			ConnectionPool.close(rs, pstmt, con);
		}
	}
	
	

}
