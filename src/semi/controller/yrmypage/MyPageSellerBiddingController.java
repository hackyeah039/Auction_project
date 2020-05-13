package semi.controller.yrmypage;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.dao.yr.AuctionDao;
import semi.vo.yr.AuctionVo;

@WebServlet("/mypage/sellerBidding.do")
public class MyPageSellerBiddingController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String id = "gogo";
		
		AuctionDao dao  = new AuctionDao();
		dao.getSelnum("gogo");
	}	
}
