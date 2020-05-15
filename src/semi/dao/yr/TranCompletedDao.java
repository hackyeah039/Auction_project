package semi.dao.yr;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import semi.db.yr.ConnectionPool;
import semi.vo.yr.BidVo;

public class TranCompletedDao {
	
	//완료된 bid 번호 가져오기
	public ArrayList<Integer> getCompletedTran(int m_num){
		ArrayList<Integer> completedTranList = new ArrayList<Integer>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
						
		try {
			con = ConnectionPool.getCon();
			String sql = "select * from payment where m_num = ? and pay_status = 1";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, m_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				do {
					int bidnumber = rs.getInt("bid_number");
					completedTranList.add(bidnumber);
				}while(rs.next());
			}
			return completedTranList;			
		}catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return null;
		}finally {
			ConnectionPool.close(rs, pstmt, con);
		}
	}
	
	
	//bid가져오기
	public ArrayList<BidVo> getBidVo(ArrayList<Integer> bidnumlist){
		
		ArrayList<BidVo> bidVoList = new ArrayList<BidVo>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
				
			con = ConnectionPool.getCon();
			for (int bidnum : bidnumlist) {
				String sql = "select * from bid where bid_number = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, bidnum);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					do {
						int mnum = rs.getInt("m_num");
						int anum = rs.getInt("a_num");
						int bidPrice = rs.getInt("bid_price");
						Date bidDate = rs.getDate("bid_date");

						bidVoList.add(new BidVo(mnum, anum, bidPrice, bidDate, bidnum));
					}while(rs.next());
				}
			}
			return bidVoList;							
				
		}catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return null;
		}finally {
			ConnectionPool.close(rs, pstmt, con);
		}	
	}
	
}
