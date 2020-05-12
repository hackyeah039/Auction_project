package semi.dao.jw;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import semi.db.jw.JDBCUtil;
import semi.vo.jw.BidVo;

public class AuctionDao {
	
	public String enddate(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		String sql="select to_char(a_enddate,'MM DD, YYYY HH24:MI:SS' )from auction where a_num=?";
		//String sql="select to_char(hiredate,'YYYY-MM-DD HH24:MI:SS') date1 from emp where empno=?";
		try{
			con= JDBCUtil.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			String enddate =null;
			if(rs.next()) {
				enddate=rs.getString(1);
			}
			return enddate;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			JDBCUtil.close(rs, pstmt, con);
		}
	}
	public BidVo list(int num){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con=JDBCUtil.getConn();
			String sql="select m_num,bid_price, to_char(systimestamp, 'YYYY/MM/DD HH24:MI:SS:ff') realdate from bid where a_num=? order by bid_price asc";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			BidVo vo= new BidVo();
			if(rs.next()) {
				vo.setBid_date(rs.getString("realdate"));
				vo.setM_num(rs.getInt("m_num"));
				vo.setBid_price(rs.getInt("bid_price"));
			}
			return vo;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			JDBCUtil.close(rs, pstmt, con);
		}
	}
}
