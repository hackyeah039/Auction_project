package semi.dao.yr;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import semi.db.yr.ConnectionPool;
import semi.vo.yr.BoardVo;

public class BoardDao {
	public int insertBoard(BoardVo vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
			
		try {
			con = ConnectionPool.getCon();
			String sql = "insert into board values (SEQ_BOARD_B_NUM.nextval,?,?,0,?,sysdate)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getB_title());
			pstmt.setString(2, vo.getB_content());
			pstmt.setInt(3, vo.getM_num());
			int n = pstmt.executeUpdate();
			return n;
		}catch (SQLException e) {
			System.out.println(e.getMessage());
			return -1;
		}finally {
			ConnectionPool.close(null, pstmt, con);
		}
	}
	
	public ArrayList<BoardVo> listBoard(int mnum) {
		
		ArrayList<BoardVo> list = new ArrayList<BoardVo>();
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		
		try {
			con = ConnectionPool.getCon();
			String sql = "select * from board where m_num = ? order by b_regdate desc";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mnum);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				do {
					 int b_num =rs.getInt("b_num");
					 String b_title = rs.getString("b_title");
					 String b_content =rs.getString("b_content");
					 int b_status = rs.getInt("b_status"); //답변여부
					 int m_num = rs.getInt("m_num");
					 Date b_regdate = rs.getDate("b_regdate");
					 Date answerDate = null;
					 if(b_status == 1) {
						 String sql2 = "select * from b_answer where b_num = ?";
						 pstmt2 = con.prepareStatement(sql2);
						 pstmt2.setInt(1,b_num);
						 rs2 = pstmt2.executeQuery();
						 if(rs2.next()) {
							 answerDate = rs2.getDate("answerdate"); 	
						 }
					 }
					 
					 list.add(new BoardVo(b_num, b_title, b_content, b_status, m_num, b_regdate,answerDate));
					
				}while(rs.next());
			}
			return list;
		}catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}finally {
			ConnectionPool.close(rs, pstmt, con);
		}
	}
	
	public String listAnswer(int bnum) {
			
			ArrayList<BoardVo> list = new ArrayList<BoardVo>();
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				con = ConnectionPool.getCon();
				String sql = "select * from b_answer where b_num = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, bnum);
				rs = pstmt.executeQuery();
				String dap = null;
				
				if(rs.next()) {
					dap = rs.getString("B_DAP");
				}
				return dap;
			}catch (SQLException e) {
				System.out.println(e.getMessage());
				return null;
			}finally {
				ConnectionPool.close(rs, pstmt, con);
			}
		}

	//content 질문
	public String listQuestion(int bnum) {
		
		ArrayList<BoardVo> list = new ArrayList<BoardVo>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ConnectionPool.getCon();
			String sql = "select * from board where b_num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bnum);
			rs = pstmt.executeQuery();
			String question = null;
			
			if(rs.next()) {
				question = rs.getString("b_content");
			}
			return question;
		}catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}finally {
			ConnectionPool.close(rs, pstmt, con);
		}
	}
	
	
}
