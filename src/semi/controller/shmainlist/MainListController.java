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
@WebServlet("/mainlist.do")
public class MainListController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("리스트 서블릿 접근");
		MainListDao dao=new MainListDao();
		ArrayList<SHAuctionVo> list=dao.AllList();
		resp.setContentType("text/xml;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		pw.println("<result>");
		for(SHAuctionVo vo:list) {
			pw.println("<data>");
			pw.println("<a_num>" + vo.getA_num() + "</a_num>");
			pw.println("<price>"+dao.getPrice(vo.getA_num())+"</price>");
			pw.println("<id>"+dao.getId(vo.getSel_number())+"</id>");
			pw.println("<a_title>" + vo.getA_title() + "</a_title>");
			pw.println("<a_content>" + vo.getA_content() + "</a_content>");
			pw.println("<a_condition>" + vo.getA_condition() + "</a_condition>");
			pw.println("<a_regdate>" + vo.getA_regdate() + "</a_regdate>");
			pw.println("<a_startdate>" + vo.getA_startdate() + "</a_startdate>");
			pw.println("<a_enddate>" + vo.getA_enddate() + "</a_enddate>");
			System.out.println(vo.getA_enddate());
			pw.println("<a_check>" + vo.getA_check() + "</a_check>");
			pw.println("<c_num>" + vo.getC_num() + "</c_num>");
			pw.println("<a_jjim>" + vo.getA_jjim() + "</a_jjim>");
			pw.println("<sel_number>" + vo.getSel_number() + "</sel_number>");
			pw.println("<bidstatus>" + vo.getBidstatus() + "</bidstatus>");
			pw.println("<a_startbid>" + vo.getA_startbid() + "</a_startbid>");
			pw.println("<a_bidunit>" + vo.getA_bidunit() + "</a_bidunit>");
			pw.println("</data>");
		}
		pw.println("</result>");
	}
}
