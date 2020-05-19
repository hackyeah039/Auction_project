package semi.controller.yrmypage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.dao.yr.BiddingDao;
import semi.vo.yr.BiddingVo;

@WebServlet("/mypage/buyerBidding.do")
public class MyPageBuyBiddingController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//아이디 가져오기
		String id = "gogo";
		
		//경로
		req.getServletContext().setAttribute("cp", req.getContextPath());

		
//		HttpSession session = req.getSession();
//		String id = (String)session.getAttribute("id"); 
		
		BiddingDao auctiondao = new BiddingDao();
		
		//입찰중경매리스트 얻어오기
		ArrayList<Integer> bidlist =  auctiondao.buyerBidinglist(id);
	
		//입찰한 경매리스트 현재가격 얻어오기
		HashMap<Integer, Integer> currPriceList = auctiondao.getCurrPrice(bidlist);
		
		//입찰 수
		HashMap<Integer, Integer> getBidCountList = auctiondao.getBidCount(bidlist);
		
		//입찰 순위
		HashMap<Integer, Integer> getBidRankList = auctiondao.getBidRank(bidlist, id);
	
		//경매상품 정보
		HashMap<Integer, BiddingVo> BiddingInfoList = auctiondao.getBiddingInfo(bidlist);
		

		req.setAttribute("getListSize", bidlist.size());		
        req.setAttribute("bidlist", bidlist);		
        req.setAttribute("currPriceList", currPriceList);		
        req.setAttribute("getBidCountList", getBidCountList);
        req.setAttribute("getBidRankList", getBidRankList);
        req.setAttribute("BiddingInfoList", BiddingInfoList);
        req.setAttribute("header", "header.jsp");
        req.setAttribute("content", "/mypage/mypageBuyBidding.jsp");
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
	}
}
