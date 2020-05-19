package semi.cotroller.yrinterest;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.dao.yr.InterproductDao;
import semi.dao.yr.TranCompletedDao;
import semi.dao.yr.TransactDao;
import semi.vo.yr.AuctionVo;
import semi.vo.yr.BidVo;

@WebServlet("/interest/interest.do")
public class ItemOfInterestController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//경로..나중에 지워야함..
		req.getServletContext().setAttribute("cp", req.getContextPath());

		
		//회원번호 가져오기
		int mnum = 2;
		
//		HttpSession session = req.getSession();
//		String id = (String)session.getAttribute("id"); 
		
		InterproductDao dao = new InterproductDao();
		TranCompletedDao td = new TranCompletedDao();
		TransactDao trd = new TransactDao();
		
		ArrayList<AuctionVo> list = dao.getInterProduct(mnum);
		
		for (AuctionVo auctionVo : list) {
			System.out.println("anum : " + auctionVo.getA_num());			
		}
		
		//입찰 정보 들고오기(현재가)
		HashMap<Integer, BidVo> bidInfo = new HashMap<Integer, BidVo>(); 
		HashMap<Integer, Integer> bidCount = new HashMap<Integer, Integer>(); 
		HashMap<Integer,String> sellerIdList = new HashMap<Integer, String>();
		
		for (AuctionVo auctionVo : list) {
			bidCount.put(auctionVo.getA_num(), auctionVo.getStartbid());								
			if(td.getCountBid(auctionVo.getA_num()) == 0) {
				bidInfo.put(auctionVo.getA_num(), auctionVo.getStartbid()));
			}else {
				bidInfo.put(auctionVo.getA_num(),td.getBidVo(auctionVo.getA_num()));
			}
			sellerIdList.put(auctionVo.getA_num(), trd.getSellerId(auctionVo.getA_num()));
		}
		
		for (int key : bidInfo.keySet()) {
			System.out.println(String.format("bidinfo 키 : %s, 값 : %s", key, bidInfo.get(key)));
		}
		for (int key : bidCount.keySet()) {
			System.out.println(String.format("bidcount 키 : %s, 값 : %s", key, bidCount.get(key)));
		}
		for (int key : sellerIdList.keySet()) {
			System.out.println(String.format("sellerid 키 : %s, 값 : %s", key, sellerIdList.get(key)));
		}
		
		
		
		
		
		req.setAttribute("getListSize", list.size());		
        req.setAttribute("interProductList", list);		
        req.setAttribute("bidInfoList", bidInfo);		
        req.setAttribute("bidCountList", bidCount);
        req.setAttribute("sellerIdList", sellerIdList);
        req.setAttribute("header", "header.jsp");
        req.setAttribute("content", "/interest/itemOfInterest.jsp");
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String[] sanum = req.getParameterValues("checkbox");
		InterproductDao dao = new InterproductDao();
		int n = 0;
		
		for (String anum : sanum) {
			n = dao.deleteInterProduct(Integer.parseInt(anum));			
			n += n;
		}
		
		if(n>0) {
			
		}
	
	}
	
}
