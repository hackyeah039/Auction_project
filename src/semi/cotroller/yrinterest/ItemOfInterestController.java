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
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

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
//		req.getServletContext().setAttribute("cp", req.getContextPath());

		
		//회원번호 가져오기
//		int mnum = 2;
		
		HttpSession session = req.getSession();
		int mnum = (Integer)session.getAttribute("m_num"); 
		
		InterproductDao dao = new InterproductDao();
		TranCompletedDao td = new TranCompletedDao();
		TransactDao trd = new TransactDao();
		
		ArrayList<AuctionVo> list = dao.getInterProduct(mnum);
		
//		for (AuctionVo auctionVo : list) {
//			System.out.println("anum : " + auctionVo.getA_num());			
//		}
		
		//입찰 정보 들고오기(현재가)
		HashMap<Integer, BidVo> bidInfo = new HashMap<Integer, BidVo>(); 
		HashMap<Integer, Integer> bidCount = new HashMap<Integer, Integer>(); 
		HashMap<Integer,String> sellerIdList = new HashMap<Integer, String>();
		
		for (AuctionVo auctionVo : list) {
			int count = td.getCountBid(auctionVo.getA_num());
			
			if(count == 0) {
				BidVo bv = new BidVo(mnum, auctionVo.getA_num(),  auctionVo.getStartbid(), null, 0);
				bidInfo.put(auctionVo.getA_num(), bv);
			}else {
				bidInfo.put(auctionVo.getA_num(),td.getBidVo(auctionVo.getA_num()));
			}
			
			bidCount.put(auctionVo.getA_num(), count);							
			sellerIdList.put(auctionVo.getA_num(), trd.getSellerId(auctionVo.getA_num()));
		}
		
//		for (int key : bidInfo.keySet()) {
//			System.out.println(String.format("bidinfo 키 : %s, 값 : %s", key, bidInfo.get(key)));
//		}
//		for (int key : bidCount.keySet()) {
//			System.out.println(String.format("bidcount 키 : %s, 값 : %s", key, bidCount.get(key)));
//		}
//		for (int key : sellerIdList.keySet()) {
//			System.out.println(String.format("sellerid 키 : %s, 값 : %s", key, sellerIdList.get(key)));
//		}
		
		
		req.setAttribute("getListSize", list.size());		
        req.setAttribute("interProductList", list);		
        req.setAttribute("bidInfoList", bidInfo);		
        req.setAttribute("bidCountList", bidCount);
        req.setAttribute("sellerIdList", sellerIdList);
//        req.setAttribute("header", "header.jsp");
        req.setAttribute("file", "/interest/itemOfInterest.jsp");
        req.getRequestDispatcher("/main_sh/layoutTest.jsp").forward(req, resp);
//	    req.getRequestDispatcher("/main_sh/layoutTest.jsp?file=/interest/itemOfInterest.jsp").forward(req, resp);

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//인코딩
		req.setCharacterEncoding("utf-8");
		
		String[] sanum = req.getParameterValues("checkbox");
		InterproductDao dao = new InterproductDao();
		int n = 0;
		
		for (String anum : sanum) {
			n = dao.deleteInterProduct(Integer.parseInt(anum));			
			n += n;
		}
		
		JSONObject data = new JSONObject();
		PrintWriter out = resp.getWriter();
		
		
		try {
			if(n>0) {
					data.put("message", "success");
			}else {
				data.put("message", "fail");
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		out.print(data);
		out.close();
	}
	
}
