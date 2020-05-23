package semi.controller.shmainlist;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.controller.shdao.MainListDao;
import semi.controller.shvo.SHAuctionVo;
@WebServlet("/sh/recomList.do")
public class RecomListController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int endrow=4;
		int startrow=1;
		int price=0;
		MainListDao dao=new MainListDao();
		ArrayList<SHAuctionVo> CheckList=dao.CheckList(startrow, endrow);
		ArrayList<SHAuctionVo> JjimList=dao.JjimList(startrow, endrow);
		ArrayList<SHAuctionVo> EndList=dao.EndList(startrow, endrow);
		//추천목록 클릭시 (인기순(조회수)은 1번 , 추천순(찜 수)은 2번, 마감임박순은 3번)
		resp.setContentType("text/xml;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		pw.println("<result>");
		for(SHAuctionVo vo:CheckList) {
			pw.println("<CheckData>");
			pw.println("<a_num>" + vo.getA_num() + "</a_num>");
			if(dao.getPrice(vo.getA_num())==0) {
				price=dao.getStartBid(vo.getA_num());
			}else {
				price=dao.getPrice(vo.getA_num());
			}
			pw.println("<price>" + price + "</price>");
			pw.println("<id>"+dao.getId(vo.getSel_number())+"</id>");
			pw.println("<i_path>"+dao.getImgPath(vo.getA_num())+"</i_path>");
			System.out.println(dao.getImgPath(vo.getA_num()));
			pw.println("<bidcnt>"+dao.getBidCnt(vo.getA_num())+"</bidcnt>");
			pw.println("<a_title>" + vo.getA_title() + "</a_title>");
			pw.println("<a_content>" + vo.getA_content() + "</a_content>");
			pw.println("<a_condition>" + vo.getA_condition() + "</a_condition>");
			pw.println("<a_regdate>" + vo.getA_regdate() + "</a_regdate>");
			pw.println("<a_startdate>" + vo.getA_startdate() + "</a_startdate>");
			pw.println("<a_enddate>" + dao.getTime(vo.getA_num()) + "</a_enddate>");
			pw.println("<a_check>" + vo.getA_check() + "</a_check>");
			pw.println("<c_num>" + vo.getC_num() + "</c_num>");
			pw.println("<a_jjim>" + vo.getA_jjim() + "</a_jjim>");
			pw.println("<sel_number>" + vo.getSel_number() + "</sel_number>");
			pw.println("<bidstatus>" + vo.getBidstatus() + "</bidstatus>");
			pw.println("<a_startbid>" + vo.getA_startbid() + "</a_startbid>");
			pw.println("<a_bidunit>" + vo.getA_bidunit() + "</a_bidunit>");
			pw.println("</CheckData>");
		}
		for(SHAuctionVo vo:JjimList) {
			pw.println("<JjimData>");
			pw.println("<a_num>" + vo.getA_num() + "</a_num>");
			if(dao.getPrice(vo.getA_num())==0) {
				price=dao.getStartBid(vo.getA_num());
			}else {
				price=dao.getPrice(vo.getA_num());
			}
			pw.println("<price>" + price + "</price>");
			pw.println("<id>"+dao.getId(vo.getSel_number())+"</id>");
			pw.println("<i_path>"+dao.getImgPath(vo.getA_num())+"</i_path>");
			pw.println("<bidcnt>"+dao.getBidCnt(vo.getA_num())+"</bidcnt>");
			pw.println("<a_title>" + vo.getA_title() + "</a_title>");
			pw.println("<a_content>" + vo.getA_content() + "</a_content>");
			pw.println("<a_condition>" + vo.getA_condition() + "</a_condition>");
			pw.println("<a_regdate>" + vo.getA_regdate() + "</a_regdate>");
			pw.println("<a_startdate>" + vo.getA_startdate() + "</a_startdate>");
			pw.println("<a_enddate>" + dao.getTime(vo.getA_num()) + "</a_enddate>");
			pw.println("<a_check>" + vo.getA_check() + "</a_check>");
			pw.println("<c_num>" + vo.getC_num() + "</c_num>");
			pw.println("<a_jjim>" + vo.getA_jjim() + "</a_jjim>");
			pw.println("<sel_number>" + vo.getSel_number() + "</sel_number>");
			pw.println("<bidstatus>" + vo.getBidstatus() + "</bidstatus>");
			pw.println("<a_startbid>" + vo.getA_startbid() + "</a_startbid>");
			pw.println("<a_bidunit>" + vo.getA_bidunit() + "</a_bidunit>");
			pw.println("</JjimData>");
		}
		for(SHAuctionVo vo:EndList) {
			pw.println("<EndData>");
			pw.println("<a_num>" + vo.getA_num() + "</a_num>");
			if(dao.getPrice(vo.getA_num())==0) {
				price=dao.getStartBid(vo.getA_num());
			}else {
				price=dao.getPrice(vo.getA_num());
			}
			pw.println("<price>" + price + "</price>");
			pw.println("<id>"+dao.getId(vo.getSel_number())+"</id>");
			pw.println("<i_path>"+dao.getImgPath(vo.getA_num())+"</i_path>");
			pw.println("<bidcnt>"+dao.getBidCnt(vo.getA_num())+"</bidcnt>");
			pw.println("<a_title>" + vo.getA_title() + "</a_title>");
			pw.println("<a_content>" + vo.getA_content() + "</a_content>");
			pw.println("<a_condition>" + vo.getA_condition() + "</a_condition>");
			pw.println("<a_regdate>" + vo.getA_regdate() + "</a_regdate>");
			pw.println("<a_startdate>" + vo.getA_startdate() + "</a_startdate>");
			pw.println("<a_enddate>" + dao.getTime(vo.getA_num()) + "</a_enddate>");
			pw.println("<a_check>" + vo.getA_check() + "</a_check>");
			pw.println("<c_num>" + vo.getC_num() + "</c_num>");
			pw.println("<a_jjim>" + vo.getA_jjim() + "</a_jjim>");
			pw.println("<sel_number>" + vo.getSel_number() + "</sel_number>");
			pw.println("<bidstatus>" + vo.getBidstatus() + "</bidstatus>");
			pw.println("<a_startbid>" + vo.getA_startbid() + "</a_startbid>");
			pw.println("<a_bidunit>" + vo.getA_bidunit() + "</a_bidunit>");
			pw.println("</EndData>");
		}
		pw.println("</result>");
	}
}
