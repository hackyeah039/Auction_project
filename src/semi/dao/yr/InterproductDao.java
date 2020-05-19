package semi.dao.yr;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import semi.db.yr.ConnectionPool;
import semi.vo.yr.AuctionVo;

public class InterproductDao {
	
	//관심물품 보기
	public ArrayList<AuctionVo> getInterProduct(int mnum){

		ArrayList<AuctionVo> interProductList = new ArrayList<AuctionVo>();
		Connection con = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;

		try {
			con = ConnectionPool.getCon();
			String sql = "select * from interproduct where m_num = ? ";
			pstmt1 = con.prepareStatement(sql);
			pstmt1.setInt(1, mnum);
			rs = pstmt1.executeQuery();
			
			if (rs.next()) {
				do {
					// 물품 출력
					int anum = rs.getInt("a_num");
					
					String sql2 = "select * from auction where a_num = ?";
					pstmt2 = con.prepareStatement(sql2);
					pstmt2.setInt(1, anum);
					rs2= pstmt2.executeQuery();
					
					if(rs2.next()) {
						int aNum = rs2.getInt("a_num"); // 물품번호
						String aTitle = rs2.getString("a_title"); // 제목
						String acontent = rs2.getString("a_content"); 
						int acondition = rs2.getInt("a_condition"); // 물품번호
						Date regDate = rs2.getDate("a_regdate"); 
						Date startDate = rs2.getDate("a_startdate"); 
						Date endDate = rs2.getDate("a_enddate"); // 마감일
						int aCheck = rs2.getInt("a_check"); // 조회수
						int cnum = rs2.getInt("c_num"); // 조회수
						int ajjim = rs2.getInt("a_jjim");
						int selNum = rs2.getInt("sel_number");
						int bidstatus = rs2.getInt("bidstatus");
						int startbid = rs2.getInt("a_startbid");
						int abidunit = rs2.getInt("a_bidunit");
						
						interProductList.add(new AuctionVo(aNum, aTitle, acontent, acondition, regDate, startDate, endDate, aCheck, selNum, ajjim, selNum, bidstatus, startbid, abidunit));

					}
				}while(rs.next());
			}
			
			return interProductList;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			ConnectionPool.close(pstmt2);
			ConnectionPool.close(rs, pstmt1, con);
		}
	}
	
	
	//관심물품 지우기
	public int deleteInterProduct(int anum) {
		Connection con = null;
		PreparedStatement pstmt1 = null;

		try {
			con = ConnectionPool.getCon();
			String sql = "delete from interproduct where a_num = ? ";
			pstmt1 = con.prepareStatement(sql);
			pstmt1.setInt(1, anum);
			int n = pstmt1.executeUpdate();
			return n;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return -1;
		} finally {
			ConnectionPool.close(null, pstmt1, con);
		}
	}
}
