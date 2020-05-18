package semi.controller.jwIndex;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.dao.jw.AuctionDao;
@WebServlet("/index.do")
public class IndexController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//detailView에서 경매상품에 대한 num를 req.getparameter()로 받아오기
		
		AuctionDao dao = AuctionDao.getInstance();
		Date enddate=dao.enddate(18);
		String years=enddate.toString().substring(0,4);
		String months=enddate.toString().substring(5,7);
		String day=enddate.toString().substring(8,10);
		
		req.setAttribute("years", years);
		req.setAttribute("months", months);
		req.setAttribute("day", day);
		//String a=dao.enddate(15);
		//System.out.println(a);
		//req.setAttribute("a", a);
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}
}
