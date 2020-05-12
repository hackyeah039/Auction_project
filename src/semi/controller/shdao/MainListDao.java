package semi.controller.shdao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.JDBCUtil;
import semi.controller.shvo.SHAuctionVo;

public class MainListDao {
	public ArrayList<SHAuctionVo> AllList(){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			String sql="select * from auction";
			con=JDBCUtil.getConn();
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			ArrayList<SHAuctionVo> list=new ArrayList<SHAuctionVo>();
			while(rs.next()) {
				int a_num=rs.getInt("a_num");
				String a_title=rs.getString("a_title");
				String a_content=rs.getString("a_content");
				int a_condition=rs.getInt("a_condition");
				Date a_regdate=rs.getDate("a_regdate");
				Date a_startdate=rs.getDate("a_startdate");
				Date a_enddate=rs.getDate("a_enddate");
				int a_check=rs.getInt("a_check");
				int c_num=rs.getInt("c_num");
				int a_jjim=rs.getInt("a_jjim");
				int s_num=rs.getInt("s_num");
				int sel_number=rs.getInt("sel_number");
				int bidstatus=rs.getInt("bidstatus");
				int a_startbid=rs.getInt("a_startbid");
				int a_bidunit=rs.getInt("a_bidunit");
				
				SHAuctionVo vo=new SHAuctionVo(a_num, a_title, a_content, a_condition, a_regdate,
						a_startdate, a_enddate, a_check, c_num, a_jjim, s_num, sel_number, bidstatus, a_startbid, a_bidunit);
				list.add(vo);
			}
			return list;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			JDBCUtil.close(rs, pstmt, con);
		}
	}
}
