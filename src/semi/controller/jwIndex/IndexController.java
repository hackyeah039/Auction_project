package semi.controller.jwIndex;

import java.io.IOException;

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
		AuctionDao dao = new AuctionDao();
		dao.enddate(num);
		
	}
}
