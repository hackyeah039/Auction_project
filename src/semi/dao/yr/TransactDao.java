package semi.dao.yr;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import semi.db.yr.ConnectionPool;
import semi.vo.yr.BidVo;
import semi.vo.yr.PaymentVo;

public class TransactDao {

	
	//거래중인 리스트 가져오기
	public ArrayList<Integer> getTransactList(int mnum) {

		ArrayList<Integer> transactlist = new ArrayList<Integer>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ConnectionPool.getCon();
			String sql = "select distinct auction.a_num from bid, auction "
					+ "where bid.a_num=auction.a_num and bid.m_num = ? and bidstatus = 2";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mnum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {
					int a = rs.getInt("a_num");
					transactlist.add(a);
				} while (rs.next());
				return transactlist;
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

	// 낙찰된 입찰정보 들고오기 (mnum이 0이면 판매자)
	public ArrayList<BidVo> getTranBidList(ArrayList<Integer> anumList, int mnum) {
		ArrayList<BidVo> tranBidList = new ArrayList<BidVo>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			for (int anum : anumList) {
				con = ConnectionPool.getCon();
				String sql = "";
				if(mnum == 0) {
					sql = "select * from" + 
							"(select max(bid_price) max,a_num from bid group by a_num having a_num = ?) aa, bid" + 
							" where aa.a_num = bid.a_num and bid_price = aa.max";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, anum);				
				}else {
					sql = "select * from"
							+ "(select max(bid_price) max,a_num from bid group by a_num having a_num = ?) aa, bid"
							+ " where aa.a_num = bid.a_num and bid_price = aa.max and m_num = ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, anum);
					pstmt.setInt(2, mnum);
				}
				rs = pstmt.executeQuery();
				if (rs.next()) {
					do {
						int bidPrice = rs.getInt("bid_price");
						Date bidDate = rs.getDate("bid_date");
						int bidNum = rs.getInt("bid_number");
						int mnum2 = rs.getInt("m_num");

						tranBidList.add(new BidVo(mnum2, anum, bidPrice, bidDate, bidNum));
					} while (rs.next());
				}
			}

			return tranBidList;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			ConnectionPool.close(rs, pstmt, con);
		}
	}

	// 판매자 id
	public HashMap<Integer, String> getSellerId(ArrayList<BidVo> anumList) {
		HashMap<Integer, String> sellerIdList = new HashMap<Integer, String>();

		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;

		try {

			for (BidVo anum : anumList) {
				con = ConnectionPool.getCon();
				String sql = "select * from auction where a_num = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, anum.getA_num());
				rs = pstmt.executeQuery();
				if (rs.next()) {

					int selNumber = rs.getInt("sel_number");
					String sql1 = "select * from seller, members where seller.m_num = members.m_num and sel_number=?";
					pstmt2 = con.prepareStatement(sql1);
					pstmt2.setInt(1, selNumber);

					rs2 = pstmt2.executeQuery();
					if (rs2.next()) {
						String m_id = rs2.getString("m_id");
						sellerIdList.put(anum.getA_num(), m_id);
					}
				}

			}

			return sellerIdList;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			ConnectionPool.close(rs, pstmt, con);
		}
	}

	public String getSellerId(int anum) {

		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		String m_id = "";
		try {
				con = ConnectionPool.getCon();
				String sql = "select * from auction where a_num = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, anum);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					int selNumber = rs.getInt("sel_number");
					System.out.println("selnumber : " + selNumber);
					String sql1 = "select * from seller, members where seller.m_num = members.m_num and sel_number=?";
					pstmt2 = con.prepareStatement(sql1);
					pstmt2.setInt(1, selNumber);

					rs2 = pstmt2.executeQuery();
					if (rs2.next()) {
						 m_id = rs2.getString("m_id");
					}		
				}
			System.out.println("id"+m_id);
			return m_id;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			ConnectionPool.close(rs, pstmt, con);
		}
	}

	// 판매물품명
	public HashMap<Integer, String> getAuctionTitle(ArrayList<BidVo> anumList) {
		HashMap<Integer, String> auctionTitleList = new HashMap<Integer, String>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			for (BidVo anum : anumList) {
				con = ConnectionPool.getCon();
				String sql = "select * from auction where a_num = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, anum.getA_num());
				rs = pstmt.executeQuery();
				if (rs.next()) {

					String title = rs.getString("a_title");
					auctionTitleList.put(anum.getA_num(), title);

				}

			}

			return auctionTitleList;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			ConnectionPool.close(rs, pstmt, con);
		}
	}

	// 거래상태, 입금기한
	public PaymentVo getPaymentInfo(int bidnum) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ConnectionPool.getCon();
			String sql = "select * from payment where bid_number = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bidnum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int payNum = rs.getInt("pay_num");
				String payAddr = rs.getString("pay_addr");
				int payStatus = rs.getInt("pay_status");
				int bidNumber = rs.getInt("bid_number");
				Date payDeadline = rs.getDate("pay_deadline");
				return new PaymentVo(payNum, payAddr, payStatus, bidNumber, payDeadline);
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

	// 판매자 번호 가져오기
	public ArrayList<Integer> getSelnum(int mnum) {

		ArrayList<Integer> list = new ArrayList<Integer>();
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;

		try {
			con = ConnectionPool.getCon();
			String sql2 = "select SEL_NUMBER from seller where m_num = ? ";
			pstmt2 = con.prepareStatement(sql2);
			pstmt2.setInt(1, mnum);

			rs = pstmt2.executeQuery();
			if (rs.next()) {
				do {
					list.add(rs.getInt("SEL_NUMBER"));
				} while (rs.next());
				return list;
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

	// 판매자가 거래하고 있는 물품리스트 들고오기
	public ArrayList<Integer> getForSellerTran(ArrayList<Integer> selList) {
		ArrayList<Integer> forSellerTranList = new ArrayList<Integer>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			for (int selnum : selList) {
				con = ConnectionPool.getCon();
				String sql = "select * from auction where sel_number = ? and bidstatus = 2";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, selnum);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					do {
						forSellerTranList.add(rs.getInt("a_num"));
					} while (rs.next());
				}
			}
			return forSellerTranList;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			ConnectionPool.close(rs, pstmt, con);
		}
	}
	
	//구매자 아이디 가져오기
	public String getMId(int mnum) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ConnectionPool.getCon();
			String sql = "select m_id from members where m_num = ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mnum);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				// 회원번호 출력
				String mId = rs.getString("m_id");
				return mId;
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

}
