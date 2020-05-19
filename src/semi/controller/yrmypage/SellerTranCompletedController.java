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
import semi.dao.yr.TranCompletedDao;
import semi.dao.yr.TransactDao;
import semi.vo.yr.AuctionVo;
import semi.vo.yr.BidVo;

@WebServlet("/mypage/sellerTranCompleted.do")
public class SellerTranCompletedController extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//경로..나중에 지워야함..
		req.getServletContext().setAttribute("cp", req.getContextPath());

		int mnum = 1;

		try {
			//판매자 num 가져오기
			TransactDao td = new TransactDao();
			ArrayList<Integer> selList = td.getSelnum(mnum);
			
			TranCompletedDao dao = new TranCompletedDao();
			ArrayList<AuctionVo> completedTranList = dao.getCompletedTranForSeller(selList);
//			for (AuctionVo auctionVo : completedTranList) {
//				System.out.println(auctionVo);
//			}
			
			
			ArrayList<BidVo> bidList = new ArrayList<BidVo>();
					
			// 입찰결과
			HashMap<Integer, String> bidResult = new HashMap<Integer, String>();
			
			
			//구매자ID
			HashMap<Integer, String> buyerList = new HashMap<Integer, String>();
			
			//입찰수
			HashMap<Integer, Integer> bidCountList = new HashMap<Integer, Integer>();
			
						
			for (AuctionVo auctionVo : completedTranList) {
				BidVo vo= dao.getBidVo(auctionVo.getA_num());
				
				if(vo == null) {
					bidResult.put(auctionVo.getA_num(),"경매 유찰");
					bidList.add(new BidVo(0, auctionVo.getA_num(), 0, null, 0));
					bidCountList.put(auctionVo.getA_num(), dao.getCountBid(auctionVo.getA_num()));
				}else {
					bidResult.put(auctionVo.getA_num(),"경매 낙찰");
					bidList.add(dao.getBidVo(auctionVo.getA_num()));
					bidCountList.put(auctionVo.getA_num(), dao.getCountBid(auctionVo.getA_num()));
				}

			}
			
//			for (BidVo bidVo : bidList) {
//				System.out.println("bidVo" + bidVo);
//			}
			
//			for (int key : bidResult.keySet()) {
//				System.out.println(String.format("키 : %s, 값 : %s", key, bidResult.get(key)));
//			}
			
			
			for (BidVo bidVo : bidList) {
				if(bidVo != null) {
					buyerList.put(bidVo.getA_num(), td.getMId(bidVo.getM_num()));								
				}
			}
			
			
//			for (int key : buyerList.keySet()) {
//				System.out.println(String.format("키 : %s, 값 : %s", key, buyerList.get(key)));
//			}

			//입찰수
			
			req.setAttribute("getListSize", completedTranList.size());
			req.setAttribute("bidCountList", bidCountList);
			req.setAttribute("completedTranList", completedTranList);
			req.setAttribute("bidList", bidList);
			req.setAttribute("bidResult", bidResult);
			req.setAttribute("buyerList", buyerList);
			
		}catch (NullPointerException e) {
			// TODO: handle exception
			req.setAttribute("getListSize", 0);			
		}

		req.setAttribute("header", "header.jsp");
		req.setAttribute("content", "/mypage/mypageSellerTranCompleted.jsp");
		req.getRequestDispatcher("/index.jsp").forward(req, resp);
		
	}
}
