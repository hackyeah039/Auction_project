package semi.controller.yrmypage;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import semi.db.yr.ConnectionPool;
import semi.vo.yr.BidVo;

public class TransactDao {
	
	public ArrayList<Integer> getTransactList(int mnum) {

		ArrayList<Integer> transactlist = new ArrayList<Integer>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ConnectionPool.getCon();
			String sql = "select distinct auction.a_num from bid, auction "
					+ "where bid.a_num=auction.a_num and bid.m_num = ? and " + 
					"bidstatus = 2";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mnum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {
					int a = rs.getInt("a_num");
					transactlist .add(a);
				} while (rs.next());
				return transactlist ;
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
	
	//낙찰된 경매물품 정보 들고오기
	public ArrayList<BidVo> getTranBidList(ArrayList<Integer> anumList, int mnum){
		ArrayList<BidVo> tranBidList = new ArrayList<BidVo>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			for (int anum : anumList) {
				con = ConnectionPool.getCon();
				String sql = "select * from (select aa.*, rownum rnm from ( select * from bid where a_num = ? order by bid_price desc) aa) where rnm = 1 and m_num = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, anum);
				pstmt.setInt(2, mnum);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					do {
						int bidPrice = rs.getInt("bid_price");
						Date bidDate = rs.getDate("bid_date");
						int bidNum = rs.getInt("bid_number");
						
						tranBidList.add(new BidVo(mnum, anum, bidPrice, bidDate, bidNum));
					} while (rs.next());

				} else {
					return null;
				}

			}

			return tranBidList ;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			ConnectionPool.close(rs, pstmt, con);
		}
	}
	
	
}
