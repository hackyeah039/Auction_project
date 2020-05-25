package semi.dao.yr;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import semi.db.yr.ConnectionPool;
import semi.vo.yr.AuctionVo;
import semi.vo.yr.BiddingVo;


public class BiddingDao {

	
	
	// 회원번호 가져오기
	public int getMnumTrust(int mnum) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ConnectionPool.getCon();
			String sql = "select * from members where m_num = ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mnum);
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				// 회원번호 출력
				int trust = rs.getInt("trust");
				return trust;
			} else {
				return -1;
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return -1;
		} finally {
			ConnectionPool.close(rs, pstmt, con);
		}
	}
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
		} finally {
			ConnectionPool.close(rs, pstmt, con);
		}
	}

	// 입찰중 리스트
	// select distinct bid.a_num from bid, auction where bid.a_num = auction.a_num
	// and m_num = 1 and bidstatus = 1;
	public ArrayList<Integer> buyerBidinglist(int mnum) {

		ArrayList<Integer> bidlist = new ArrayList<Integer>();

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
					int a = rs.getInt("a_num");
					bidlist.add(a);
				} while (rs.next());
				return bidlist;
			} else {
				return null;
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			ConnectionPool.close(rs, pstmt, con);
		}
	}

	// 현재가격
	// select max(bid_price) from bid where a_num = ?;
	public HashMap<Integer, Integer> getCurrPrice(ArrayList<Integer> anumlist) {

		HashMap<Integer, Integer> currPriceList = new HashMap<Integer, Integer>();

		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		

		try {

			for (int anum : anumlist) {
				con = ConnectionPool.getCon();
				String sql = "select nvl(max(bid_price),0) currprice from bid where a_num = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, anum);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					do {
						int currprice = rs.getInt("currprice");
						if(currprice == 0) {
							String sql2 = "select * from auction where a_num = ?";
							pstmt2 = con.prepareStatement(sql2);
							pstmt2.setInt(1, anum);
							rs2 = pstmt2.executeQuery();
							if(rs2.next()) {
								currprice = rs2.getInt("a_startbid");
								currPriceList.put(anum, currprice);								
							}
						}else {
							currPriceList.put(anum, currprice);							
						}
						
					} while (rs.next());

				} else {
					return null;
				}

			}

			return currPriceList;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			ConnectionPool.close(rs, pstmt, con);
		}
	}

	// 입찰등록한 수
	public HashMap<Integer, Integer> getBidCount(ArrayList<Integer> anumlist) {

		HashMap<Integer, Integer> bidCountList = new HashMap<Integer, Integer>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			for (int anum : anumlist) {
				con = ConnectionPool.getCon();
				String sql = "select count(bid.a_num) count, auction.a_num a_num from bid, auction where bid.a_num = auction.a_num and bidstatus = 1 "
						+ "and bid.a_num = ? group by auction.a_num";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, anum);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					do {
						int count = rs.getInt("count");
						bidCountList.put(anum, count);
					} while (rs.next());

				} else {
					return null;
				}
			}

			return bidCountList;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			ConnectionPool.close(rs, pstmt, con);
		}
	}

	// 입찰 순위
	public HashMap<Integer, Integer> getBidRank(ArrayList<Integer> anumlist, int mnum) {

		HashMap<Integer, Integer> bidRankList = new HashMap<Integer, Integer>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			for (int anum : anumlist) {
				con = ConnectionPool.getCon();
				String sql = "select * from" + "(" + "select rownum rnm, aa.* from"
						+ "(select * from bid where a_num = ? order by bid_price desc) aa)"
						+ "where m_num = ? and rownum = 1";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, anum);
				pstmt.setInt(2, mnum);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					do {
						bidRankList.put(anum, rs.getInt("rnm"));
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

	// 물품명, 조회, 마감일 BiddingVo
	public HashMap<Integer, BiddingVo> getBiddingInfo(ArrayList<Integer> anumlist) {
		HashMap<Integer, BiddingVo> biddingInfoList = new HashMap<Integer, BiddingVo>();

		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;

		try {

			for (int anum : anumlist) {
				con = ConnectionPool.getCon();
				String sql = "select * from auction where a_num = ?";

				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, anum);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					do {
						String title = rs.getString("a_title");
						int check = rs.getInt("a_check");
						Date endDate = rs.getDate("a_enddate");
						int selNumber = rs.getInt("sel_number");

						String sql2 = "select m_id from seller, members" + " where seller.m_num = members.m_num "
								+ "and sel_number = ?";
						pstmt2 = con.prepareStatement(sql2);
						pstmt2.setInt(1, selNumber);
						rs2 = pstmt2.executeQuery();
						String mId = "";

						if (rs2.next()) {
							mId = rs2.getString("m_id");
						} else {
							return null;
						}

						biddingInfoList.put(anum, new BiddingVo(title, check, endDate, mId));

					} while (rs2.next());

				} else {
					return null;
				}

			}

			return biddingInfoList;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			
			ConnectionPool.close(rs2);
			ConnectionPool.close(pstmt2);
			ConnectionPool.close(rs, pstmt, con);
		}
	}

	// 판매자 번호 가져오기
	public ArrayList<Integer> getSelnum(String id) {

		ArrayList<Integer> list = new ArrayList<Integer>();
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;

		try {
			con = ConnectionPool.getCon();
			String sql = "select m_num from members where m_id = ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				// 회원번호 출력
				int mnum = rs.getInt("m_num");

				String sql2 = "select SEL_NUMBER from seller where m_num = ? ";
				pstmt2 = con.prepareStatement(sql2);
				pstmt2.setInt(1, mnum);
				rs2 = pstmt2.executeQuery();
				if (rs2.next()) {

					do {
						list.add(rs2.getInt("SEL_NUMBER"));
					} while (rs2.next());
					return list;
				} else {
					return null;
				}
			} else {
				return null;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			ConnectionPool.close(rs2);
			ConnectionPool.close(pstmt2);
			ConnectionPool.close(rs, pstmt, con);
		}
	}

	// aution테이블에서 판매자가 팔고 있는 물품중 현재 입찰중인 (bidstatus = 1)인 것리스트 가져오기
	public ArrayList<Integer> sellerBidinglist(ArrayList<Integer> selNumlist, int type) {

		ArrayList<Integer> bidlist = new ArrayList<Integer>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			for (int selnum : selNumlist) {
				con = ConnectionPool.getCon();
				String sql = "";
				if(type == 1) {
					sql = "select a_num from auction where sel_number=? and bidstatus=0";					
				}else{
					sql = "select a_num from auction where sel_number=? and bidstatus=1";					
				}
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, selnum);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					do {
						int anum = rs.getInt("a_num");
//						System.out.println("anum : " + anum);
						bidlist.add(anum);
					}while(rs.next());
				} 
			}

			return bidlist;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			ConnectionPool.close(rs, pstmt, con);
		}
	}
	
	// 물품명, 조회,마감일 BiddingVo
	public HashMap<Integer, BiddingVo> sellergetBiddingInfo(ArrayList<Integer> anumlist) {
		HashMap<Integer, BiddingVo> biddingInfoList = new HashMap<Integer, BiddingVo>();

		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;

		try {

			for (int anum : anumlist) {
				con = ConnectionPool.getCon();
				String sql = "select a.*,  (TRUNC(a_enddate) - TRUNC(SYSDATE)) remainDate from" + 
						"(select * from auction where a_num = ?) a";

				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, anum);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					do {
						String title = rs.getString("a_title");
						int check = rs.getInt("a_check");
						Date startDate = rs.getDate("a_startdate");
						Date endDate = rs.getDate("a_enddate");
						String remainDate = rs.getString("remainDate");
						biddingInfoList.put(anum, new BiddingVo(title, check, startDate, endDate, remainDate));

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
