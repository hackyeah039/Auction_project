package semi.dao.yh;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.JdbcUtil;
import semi.vo.yh.AuctionVo;
import semi.vo.yh.SellerVo;
import semi.vo.yh.ShipVo;

public class AuctionDao {
	private static AuctionDao instance = new AuctionDao();
	
	private AuctionDao() {}
	
	public static AuctionDao getInstance() {
		return instance;
	}
	public AuctionVo searchAuction(int a_num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JdbcUtil.getConn();
			String sql = "select * from auction where a_num = ?";
			//0514 빼먹은 코드 수정완료!
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, a_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				AuctionVo vo = new AuctionVo(
						rs.getInt("a_num"),
						rs.getString("a_title"),
						rs.getString("a_content"),
						rs.getInt("a_condition"),
						rs.getDate("a_regdate"),
						rs.getString("a_startdate"),
						rs.getString("a_enddate"),
						rs.getInt("a_check"),
						rs.getInt("c_num"),
						rs.getInt("a_jjim"),
						rs.getInt("sel_number"),
						rs.getInt("bidstatus"),
						rs.getInt("a_startbid"),
						rs.getInt("a_bidunit")
						);
				return vo;
			}
			return null;
		} catch (SQLException se) {
			se.printStackTrace();
			return null;
		} finally {
			JdbcUtil.close(con, pstmt, rs);
		}
	}
	//테이블 인서트 메소드 추가 
	public int InsertAuction(AuctionVo vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int n = 0;
		try {
			con = JdbcUtil.getConn();
			String sql = "insert into auction values(seq_auction_a_num.nextval,?,?,?,sysdate,?,?,0,?,0,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getA_title());
			pstmt.setString(2, vo.getA_content());
			pstmt.setInt(3, vo.getA_condition());
			pstmt.setString(4, vo.getA_startdate());
			pstmt.setString(5, vo.getA_enddate());
			pstmt.setInt(6, vo.getC_num());
			pstmt.setInt(7, vo.getSel_number());
			pstmt.setInt(8, vo.getBidstatus());
			pstmt.setInt(9, vo.getA_startbid());
			pstmt.setInt(10, vo.getA_bidunit());
			return n = pstmt.executeUpdate();	
		} catch (SQLException se) {
			se.printStackTrace();
			return -1;
		} finally {
			JdbcUtil.close(con, pstmt, null);
		}
	}
	public int InsertTables(int sel_number, SellerVo sevo, AuctionVo avo, ShipVo shvo, ArrayList<String> fList) {
		Connection con = null;
		PreparedStatement pstmtSeller = null;
		PreparedStatement pstmtAuction = null;
		PreparedStatement pstmtShip = null;
		PreparedStatement pstmtImg = null;
		try {
			con = JdbcUtil.getConn();
			con.setAutoCommit(false);
			// 신규일때 -> seller, action
			if(sel_number == 0 ) {
				String sqlSeller = "insert into seller values(?,?,seq_seller_sel_number.nextval)";
				pstmtSeller = con.prepareStatement(sqlSeller);
				pstmtSeller.setLong(1, sevo.getAccount());
				pstmtSeller.setInt(2, sevo.getM_num());
				pstmtSeller.executeUpdate();
				
				
				// 문자열이라고 미리 작은 따옴표로 감쌀 필요 없음, MM과 mm은 같은 문자열로 인식 되므로 분단위 mm을 mi로 수정
				String sqlAuction = "insert into auction values(seq_auction_a_num.nextval,?,?,?,sysdate,to_date(?,'yyyy-MM-dd HH24:mi:ss'),to_date(?,'yyyy-MM-dd HH24:mi:ss'),0,?,0,seq_seller_sel_number.currval,?,?,?)";
				pstmtAuction = con.prepareStatement(sqlAuction);			
				pstmtAuction.setString(1, avo.getA_title());
				pstmtAuction.setString(2, avo.getA_content());
				pstmtAuction.setInt(3, avo.getA_condition());
				pstmtAuction.setString(4, avo.getA_startdate());
				pstmtAuction.setString(5, avo.getA_enddate());
				pstmtAuction.setInt(6, avo.getC_num());
				pstmtAuction.setInt(7, avo.getBidstatus());
				pstmtAuction.setInt(8, avo.getA_startbid());
				pstmtAuction.setInt(9, avo.getA_bidunit());
				pstmtAuction.executeUpdate();
			} else {
				// 기존 등록자일때 
				String sqlAuction = "insert into auction values(seq_auction_a_num.nextval,?,?,?,sysdate,to_date(?,'yyyy-MM-dd HH24:mi:ss'),to_date(?,'yyyy-MM-dd HH24:mi:ss'),0,?,0,?,?,?,?)";
				pstmtAuction = con.prepareStatement(sqlAuction);			
				pstmtAuction.setString(1, avo.getA_title());
				pstmtAuction.setString(2, avo.getA_content());
				pstmtAuction.setInt(3, avo.getA_condition());
				pstmtAuction.setString(4, avo.getA_startdate());
				pstmtAuction.setString(5, avo.getA_enddate());
				pstmtAuction.setInt(6, avo.getC_num());
				pstmtAuction.setInt(7, avo.getSel_number());
				pstmtAuction.setInt(8, avo.getBidstatus());
				pstmtAuction.setInt(9, avo.getA_startbid());
				pstmtAuction.setInt(10, avo.getA_bidunit());
				pstmtAuction.executeUpdate();
			}
			
			String sqlShip = "insert into Ship values(seq_ship_s_num.nextval, ?, ?, seq_auction_a_num.currval)";
			pstmtShip = con.prepareStatement(sqlShip);
			pstmtShip.setString(1, shvo.getS_way());
			pstmtShip.setInt(2, shvo.getS_price());
			pstmtShip.executeUpdate();
			
			System.out.println(fList + "     asdae");

			for(String path:fList) {
				String sqlImg = "insert into img values(seq_auction_a_num.currval,?)";
				System.out.println(path + "     111111");
				pstmtImg = con.prepareStatement(sqlImg);
				pstmtImg.setString(1, path);
				pstmtImg.executeUpdate();
			}
			con.commit();
			return 1;
		} catch (SQLException se) {
			se.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException se1) {
				se1.printStackTrace();
			}
			return -1;
		} finally {
			JdbcUtil.close(pstmtImg);
			JdbcUtil.close(pstmtShip);
			JdbcUtil.close(pstmtAuction);
			JdbcUtil.close(pstmtSeller);
			JdbcUtil.close(con);
		}
	}
}
