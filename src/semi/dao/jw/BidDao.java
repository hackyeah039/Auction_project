package semi.dao.jw;

import java.sql.Connection;  
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import semi.db.jw.ConnectionPool;
import semi.vo.jw.BidVo;

public class BidDao {
	private static BidDao instance = new BidDao();
	private BidDao() {};
	public static BidDao getInstance() {
		return instance;
	}
//	public BidVo list(int num){
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			con=JDBCUtil.getConn();
//			String sql="select m_num,bid_price, to_char(systimestamp, 'YYYY/MM/DD HH24:MI:SS:ff') realdate from bid where a_num=? order by bid_price asc";
//			pstmt=con.prepareStatement(sql);
//			pstmt.setInt(1, num);
//			rs=pstmt.executeQuery();
//			BidVo vo= new BidVo();
//			if(rs.next()) {
//				vo.setBid_date(rs.getString("realdate"));
//				vo.setM_num(rs.getInt("m_num"));
//				vo.setBid_price(rs.getInt("bid_price"));
//			}
//			return vo;
//		}catch(SQLException se) {
//			System.out.println(se.getMessage());
//			return null;
//		}finally {
//			JDBCUtil.close(rs, pstmt, con);
//		}
//	}
//	public ArrayList<BidVo> list(int a_num,int startRow,int endRow){
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			System.out.println("dao list접근");
//			con=ConnectionPool.getConn();
//			System.out.println(con);
//			String sql=null;
//				 sql = 
//						 "select * from "
//						+ "	("
//						+ " select aa.*, rownum rnum from "
//						+ " ( "
//						+ "	select m_num,bid_price, to_char(systimestamp, 'YYYY/MM/DD HH24:MI:SS:ff') realdate from bid where a_num=? order by bid_price asc"
//						+ " )aa"
//						+ " )where rnum>=? and rnum<=?";
//				 		pstmt=con.prepareStatement(sql);
//			 			pstmt.setInt(1, a_num);
//			 			pstmt.setInt(2, startRow);
//			 			pstmt.setInt(3, endRow);
//			
//			 			rs= pstmt.executeQuery();
//						ArrayList<BidVo> list = new ArrayList<BidVo>();
//						while(rs.next()) {
//							BidVo vo = new BidVo();
//							vo.setBid_date(rs.getString("realdate"));
//							vo.setBid_price(rs.getInt("bid_price"));
//							vo.setM_num(rs.getInt("m_num"));
//							
//							list.add(vo);
//						}
//						return list;
//						}catch(SQLException se) {
//							System.out.println(se.getMessage());
//							return null;
//						}finally {
//							ConnectionPool.close(rs, pstmt, con);
//						}
//	}
	
	public Double getCount(int field,int a_num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con=ConnectionPool.getConn();
			String sql="select nvl(max(rownum),0) rnum from bid where a_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, a_num);
			rs=pstmt.executeQuery();
			
			Double result=0.0;
			
			if(rs.next()) {
				double newField =field;
				result=rs.getInt("rnum")/newField;
			}
			return result;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1.0;
		}finally {
			ConnectionPool.close(rs, pstmt, con);
		}
	}
	
	public ArrayList<BidVo> postlist(int startRow,
			int endRow,int a_num){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getConn();
			
				String sql="select * from \r\n" + 
						"                        (\r\n" + 
						"                        select aa.*, rownum rnum from \r\n" + 
						"						( \r\n" + 
						"							select m_num,bid_price, to_char(bid_date, 'YYYY/MM/DD HH24:MI:SS') realdate from bid where a_num=? order by bid_price asc\r\n" + 
						"						 )aa\r\n" + 
						"						 ) where rnum>=? and rnum<=?";
			
			    pstmt=con.prepareStatement(sql);
			    pstmt.setInt(1, a_num);
				pstmt.setInt(2,startRow);
				pstmt.setInt(3,endRow);
			
			rs=pstmt.executeQuery();
			ArrayList<BidVo> list=new ArrayList<BidVo>();
			while(rs.next()) {
				BidVo vo = new BidVo();
				vo.setBid_date(rs.getString("realdate"));
				vo.setBid_price(rs.getInt("bid_price"));
				vo.setM_num(rs.getInt("m_num"));
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
}
