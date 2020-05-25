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
import semi.dao.yr.TransactDao;
import semi.vo.yr.BiddingVo;

@WebServlet("/mypage/sellerBidding.do")
public class MyPageSellerBiddingController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		String id = "test2";
		
//		회원 번호 session으로 가져오기
		String id = (String)req.getSession().getAttribute("id");
		int m_num= (int)req.getSession().getAttribute("m_num");
		int type = Integer.parseInt(req.getParameter("type"));
		
		
		try {
			
			BiddingDao auctiondao  = new BiddingDao();
			
			//판매자 번호
			ArrayList<Integer> selNumberList = auctiondao.getSelnum(id);
	
			
			//현재 판매자가 입찰중인 판매 리스트
			ArrayList<Integer> anumList = auctiondao.sellerBidinglist(selNumberList,type);
	
			//입찰 수
			HashMap<Integer, Integer> getBidCountList = auctiondao.getBidCount(anumList);
	        
	        //현재 입찰가격
	        HashMap<Integer, Integer> getCurrPrice = auctiondao.getCurrPrice(anumList);
	        
	        
	        //판매품 정보(물품명, 조회, 시작일,마감일)
	        HashMap<Integer, BiddingVo> BiddingInfoList = auctiondao.sellergetBiddingInfo(anumList);
			
	        TransactDao tddao = new TransactDao();
	        HashMap<Integer, Integer> noAnswerList = new HashMap<Integer, Integer>();

	        for (int anum : anumList) {
	        	noAnswerList.put(anum, tddao.getCountQues(anum));	
			}
	        
	        req.setAttribute("type", type);
	        req.setAttribute("getListSize", anumList.size());
	        req.setAttribute("anumList", anumList);		
	        req.setAttribute("BidCountList", getBidCountList);		
	        req.setAttribute("currPriceList", getCurrPrice);
	        req.setAttribute("BiddingInfoList", BiddingInfoList);
	        req.setAttribute("noAnswerList", noAnswerList);
		}catch (NullPointerException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			req.setAttribute("getListSize", 0);
		}

//        req.setAttribute("header", "header.jsp");
        req.setAttribute("file", "/mypage/mypageSellerBidding.jsp");
        req.getRequestDispatcher("/main_sh/layoutTest.jsp").forward(req, resp);
//        req.getRequestDispatcher("/main_sh/layoutTest.jsp?file=/mypage/mypageSellerBidding.jsp").forward(req, resp);
    	
	}	
}
