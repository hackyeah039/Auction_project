package semi.controller.yrmypage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.dao.yr.TranCompletedDao;
import semi.dao.yr.TransactDao;
import semi.vo.yr.AuctionVo;
import semi.vo.yr.BidVo;

@WebServlet("/mypage/buyerTranCompleted.do")
public class BuyerTranCompletedController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// <구매자>구분, 물품번호, 이미지, 물품명, 낙찰가, 입찰, 조회, 마감일, 판매자, 입찰결과(낙찰,낙찰 받지 못한 물품 )

		int mnum = 1;

		// 회원 번호가져오기
//		HttpSession session = req.getSession();
//		int id = (int)session.getAttribute("mnum"); 

		try {
			
			// 물품번호, 물품명, 조회, 마감일, 판매자 num
			TranCompletedDao dao = new TranCompletedDao();
			ArrayList<AuctionVo> completedTranList = dao.getCompletedTran(mnum);
			
			// 낙찰가격,  
			ArrayList<BidVo> bidList = new ArrayList<BidVo>();
			
			// 입찰결과
			HashMap<Integer, String> bidResult = new HashMap<Integer, String>();
			
			//입찰수
			HashMap<Integer, Integer> bidCountList = new HashMap<Integer, Integer>();
		
			
			for (AuctionVo auctionVo : completedTranList) {
				bidList.add(dao.getBidVo(auctionVo.getA_num()));
				bidCountList.put(auctionVo.getA_num(), dao.getCountBid(auctionVo.getA_num()));
			}
			
			
			
			for (BidVo bidVo : bidList) {
				if (bidVo.getM_num() == mnum) {
					bidResult.put(bidVo.getA_num(), "낙찰성공");
				} else {
					bidResult.put(bidVo.getA_num(), "낙찰실패");
				}
			}
			
//		for (int key : BidResult.keySet()) {
//			System.out.println(String.format("키 : %s, 값 : %s", key, BidResult.get(key)));
//		}
			
			//판매자 id
			TransactDao td = new TransactDao();
			HashMap<Integer, String> sellerList= td.getSellerId(bidList);
			
//		for (int key : sellerList.keySet()) {
//			System.out.println(String.format("키 : %s, 값 : %s", key, sellerList.get(key)));
//		}
			
			req.setAttribute("getListSize", completedTranList.size());
			req.setAttribute("completedTranList", completedTranList);
			req.setAttribute("bidCountList", bidCountList);
			req.setAttribute("bidList", bidList);
			req.setAttribute("bidResult", bidResult);
			req.setAttribute("sellerList", sellerList);

		}catch (NullPointerException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			req.setAttribute("getListSize", 0);
		}

		req.setAttribute("header", "header.jsp");
		req.setAttribute("content", "/mypage/mypageBuyerTranCompleted.jsp");
		req.getRequestDispatcher("/index.jsp").forward(req, resp);
		
	}
}
