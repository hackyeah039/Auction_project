package semi.dao.yr;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import semi.db.yr.ConnectionPool;
import semi.vo.yr.AuctionVo;
import semi.vo.yr.BiddingVo;

public class AuctionDao {

	// 회원번호 가져오기
	public int getMnum(String id) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ConnectionPool.getCon();
			String sql = "select m_num from members where m_id = ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				// 회원번호 출력
				int mnum = rs.getInt("m_num");
				return mnum;
			} else {
				return -1;
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return -1;
		}
	}

	// 입찰중 리스트
	// select distinct bid.a_num from bid, auction where bid.a_num = auction.a_num
	// and m_num = 1 and bidstatus = 1;
	public ArrayList<Integer> bidinglist(String id) {

		ArrayList<Integer> bidlist = new ArrayList<Integer>();
		int mnum = getMnum(id);

		// 회원번호 출력
		System.out.println("회원 번호" + mnum);

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ConnectionPool.getCon();
			String sql = "select distinct bid.a_num from bid, auction "
					+ "where bid.a_num = auction.a_num and m_num = ? and bidstatus = 1";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mnum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {
					bidlist.add(rs.getInt("a_num"));
				} while (rs.next());
				return bidlist;
			} else {
				return null;
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	// 현재가격
	// select max(bid_price) from bid where a_num = ?;
	public ArrayList<Integer> getCurrPrice(ArrayList<Integer> anumlist) {

		ArrayList<Integer> currPriceList = new ArrayList<Integer>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			for (int anum : anumlist) {
				con = ConnectionPool.getCon();
				String sql = "select max(bid_price) currprice from bid where a_num = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, anum);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					do {
						currPriceList.add(rs.getInt("currprice"));
					} while (rs.next());

				} else {
					return null;
				}

			}

			return currPriceList;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	// 입찰등록한 수
	// select count(bid.a_num) count, auction.a_num from bid, auction
	// where bid.a_num = auction.a_num and m_num = 1 and bidstatus = 1 group by
	// auction.a_num

	public ArrayList<Integer> getBidCount(String id) {

		ArrayList<Integer> bidCountList = new ArrayList<Integer>();
		int mnum = getMnum(id);

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ConnectionPool.getCon();
			String sql = "select count(bid.a_num) count, auction.a_num from bid, auction "
					+ " where bid.a_num = auction.a_num and m_num = ? and bidstatus = 1 group by auction.a_num";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mnum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {
					bidCountList.add(rs.getInt("count"));
				} while (rs.next());

			} else {
				return null;
			}

			return bidCountList;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	//입찰 순위
	public ArrayList<Integer> getBidRank(ArrayList<Integer> anumlist, String id){
		
		ArrayList<Integer> bidRankList = new ArrayList<Integer>();
		int mnum = getMnum(id);

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			for (int anum : anumlist) {
				con = ConnectionPool.getCon();
				String sql = "select * from" + 
						"(" + 
						"select rownum rnm, aa.* from" + 
						"(select * from bid where a_num = ? order by bid_price desc) aa)" + 
						"where m_num = ? and rownum = 1";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, anum);
				pstmt.setInt(2, mnum);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					do {
						bidRankList.add(rs.getInt("rnm"));
					} while (rs.next());

				} else {
					return null;
				}

			}

			return bidRankList;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			ConnectionPool.close(rs, pstmt, con);
		}
	}
	
	//물품명, 조회,마감일
	public ArrayList<BiddingVo> getBiddingInfo(ArrayList<Integer> anumlist){
		ArrayList<BiddingVo> biddingInfoList = new ArrayList<BiddingVo>();

		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;

		try {

			for (int anum : anumlist) {
				con = ConnectionPool.getCon();
				String sql = "select * from auction where a_num = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, anum);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					do {
						String title =rs.getString("a_title");
						int check = rs.getInt("a_check");
						Date endDate = rs.getDate("a_enddate");
						int selNumber = rs.getInt("sel_number");
						
						String sql2 = "select m_id from seller, members"
								+ " where seller.m_num = members.m_num "
								+ "and sel_number = ?";
						pstmt2 = con.prepareStatement(sql2);
						pstmt2.setInt(1, selNumber);
						rs = pstmt2.executeQuery();
						String mId ="";
						
						if(rs.next()) {
							mId = rs.getString("m_id");
						}else {
							return null;
						}
						
						biddingInfoList.add(new BiddingVo(title, check, endDate,mId));
						
					} while (rs.next());

				} else {
					return null;
				}

			}

			return biddingInfoList;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			ConnectionPool.close(pstmt2);
			ConnectionPool.close(rs, pstmt, con);
		}
	}
	
	

}
