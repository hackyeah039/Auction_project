package semi.controller.yrmypage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.dao.yr.TransactDao;
import semi.vo.yr.BidVo;
import semi.vo.yr.PaymentVo;

/**@
 * 
 * @author yurae
 * 판매자 거래중 Controller
 * 
 */

@WebServlet("/mypage/sellerTransact.do")
public class SellerTransact extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		

		//회원번호 가져오기
//		HttpSession session = req.getSession();
//		int mnum = (int)session.getAttribute("mnum");

		int mnum = 4;
		
		
		try {
			
		
			//판매자 번호리스트로 가져오기 -> 판매자가 팔고 있는 
			TransactDao dao = new TransactDao(); 
			ArrayList<Integer> selList = dao.getSelnum(mnum);
			
			for (int selnum : selList) {
				System.out.println("판매자 번호 리스트 : " + selnum);
			}
			
			
			//판매자가 올린 경매 중에서 거래중인 리스트 가져 오기
			ArrayList<Integer> forSellerTranList = dao.getForSellerTran(selList,0);
			
	//		for (int anum : forSellerTranList) {
	//			System.out.println(anum);
	//		}
			
			
			//판매한 입찰정보 (낙찰일, 구매가격)		
			ArrayList<BidVo> tranBidList = dao.getTranBidList(forSellerTranList, 0);
	
			//구매자아이디 가져오기
			HashMap<Integer,String> buyerId = new HashMap<Integer,String>();
			//거래상태, 입금기한
			HashMap<Integer, PaymentVo> paymentInfoList = new HashMap<Integer, PaymentVo>();
			
			for (BidVo bidList : tranBidList) {
				int mnum2 = bidList.getM_num();
				buyerId.put(bidList.getA_num(),dao.getMId(mnum2));
				paymentInfoList.put(bidList.getA_num(),dao.getPaymentInfo(bidList.getBid_num()));
	//			System.out.println(bidList);
			}
			
			
	//		for (String mid : buyerId) {
	//			System.out.println(mid);
	//		}
			
			//물품정보(물품제목)
			HashMap<Integer, String> auctiontitleList = dao.getAuctionTitle(tranBidList);
			
			req.setAttribute("getListSize", forSellerTranList.size() );
			req.setAttribute("forSellerTranList", forSellerTranList);
			req.setAttribute("tranBidList", tranBidList);
			req.setAttribute("buyerId", buyerId);
			req.setAttribute("paymentList", paymentInfoList);
			req.setAttribute("auctiontitleList", auctiontitleList);
		
		}catch (NullPointerException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			req.setAttribute("getListSize", 0);
		}
		req.setAttribute("header", "header.jsp");
		req.setAttribute("content", "/mypage/mypageSellerTransact.jsp");
		req.getRequestDispatcher("/index.jsp").forward(req, resp);
		
	}
}
