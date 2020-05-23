package semi.dao.yh;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.ConnectionPool;
import semi.vo.yh.SellerVo;

public class SellerDao {
		private static SellerDao instance = new SellerDao();
		
		private SellerDao() {}
		
		public static SellerDao getInstance() {
			return instance;
		}
		
		public SellerVo SearchSeller(int sel_number) {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				con = ConnectionPool.getConn();
				String sql = "select * from seller where sel_number = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, sel_number);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					SellerVo vo = new SellerVo(
					rs.getLong("account"),
					rs.getInt("m_num"),
					rs.getInt("sel_number")
					);
					return vo;
				}
				return null;
			} catch (SQLException se) {
				se.printStackTrace();
				return null;
			} finally {
				ConnectionPool.close(rs, pstmt, con);
			}
		}
		public ArrayList<SellerVo> listAccount(int m_num1){
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				con = ConnectionPool.getConn();
				String sql = "select * from seller where m_num=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, m_num1);
				rs = pstmt.executeQuery();
				ArrayList<SellerVo> list = new ArrayList<SellerVo>();
				while(rs.next()) {
					Long account = rs.getLong(1);
					int m_num = rs.getInt(2);
					int sel_number = rs.getInt(3);
					SellerVo vo=new SellerVo(account, m_num, sel_number);
					list.add(vo);
				}
				return list;
			} catch (SQLException se) {
				se.printStackTrace();
				return null;
			} finally {
				ConnectionPool.close(rs, pstmt, con);
			}
		}
		//계좌번호가 DB에 존재하는지 확인
		public int checkAccount(long a) {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				con = ConnectionPool.getConn();
				String sql = "select * from seller where sel_number = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setLong(1, a);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					return 1;
				}
				return 0;
			} catch (SQLException se) {
				se.printStackTrace();
				return -1;
			} finally {
				ConnectionPool.close(rs, pstmt, con);
			}
		}
		// seller 테이블 인서트 
		//public int InsertSeller(Long account, int sel_number, int m_num) {
		public int InsertSeller(SellerVo vo) {
			Connection con = null;
			PreparedStatement pstmt = null;
			int n = 0;
			try {
				if(vo.getSel_number() == 0) {
					con = ConnectionPool.getConn();
					String sql = "insert into seller values(?,?,seq_seller_sel_number.nextval)";
					pstmt = con.prepareStatement(sql);
					pstmt.setLong(1, vo.getAccount());
					pstmt.setInt(2, vo.getM_num());
					n = pstmt.executeUpdate();
					return n;
				}
				return 0; // 기존에 있던 값일 경우
			} catch (SQLException se) {
				se.printStackTrace();
				return -1;
			} finally {
				ConnectionPool.close(null, pstmt, con);
			}
		}
}
