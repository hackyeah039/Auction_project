
package semi.controller.yrmypage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import semi.dao.yr.TransactDao;
import semi.vo.yr.BidVo;
import semi.vo.yr.PaymentVo;

/**
 * @author yurae - 구매자 거래진행중 controller - TransactDao에서 거래중인경매리스트, 입찰정보, 물품이름,
 *         거래상태, 입금기한을 가져옴
 */

@WebServlet("/mypage/buyerTransact.do")
public class BuyerTransact extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//		int mnum = 1;

		try {

			// 회원번호 가져오기
			HttpSession session = req.getSession();
			int mnum = (int)session.getAttribute("m_num");

			TransactDao dao = new TransactDao();

			// 거래중인 리스트 들고오기
			ArrayList<Integer> anumlist = dao.getTransactList(mnum);

//			for(int anum : anumlist) {
//				System.out.println("거래중인 리스트 : " + anum);
//			}

			// 입찰정보 가져오기
			ArrayList<BidVo> tranBidList = dao.getTranBidList(anumlist, mnum);

//			for(BidVo vo : tranBidList) {
//				System.out.println("bid"+vo);
//			}

			// 물품이름
			HashMap<Integer, String> auctionTitleList = dao.getAuctionTitle(tranBidList);
			
//			for (Integer key : auctionTitleList.keySet()) {
//				System.out.println(String.format("키 : %s, 값 : %s", key, auctionTitleList.get(key)));
//			}
			
			
			// 거래상태, 입금기한
			HashMap<Integer, PaymentVo> paymentList = new HashMap<Integer, PaymentVo>();
			for (BidVo bidVo : tranBidList) {
				PaymentVo payvo = dao.getPaymentInfo(bidVo.getBid_num());
				paymentList.put(bidVo.getA_num(), payvo);
			}

			// 판매자
			HashMap<Integer, String> sellerIdList = dao.getSellerId(tranBidList);

//			for (Integer key : paymentList.keySet()) {
//				System.out.println(String.format("키 : %s, 값 : %s", key, paymentList.get(key)));
//			}

			req.setAttribute("getListSize", tranBidList.size());
			req.setAttribute("tranBidList", tranBidList);
			req.setAttribute("auctionTitleList", auctionTitleList);
			req.setAttribute("sellerIdList", sellerIdList);
			req.setAttribute("paymentList", paymentList);

		} catch (NullPointerException e) {
			// TODO: handle exception
			System.out.println("null...");
			req.setAttribute("getListSize", 0);
		}
		
//		req.setAttribute("header", "header.jsp");
		req.setAttribute("file", "/mypage/mypageBuyerTransact.jsp");
		req.getRequestDispatcher("/main_sh/layoutTest.jsp").forward(req, resp);
//	    req.getRequestDispatcher("/main_sh/layoutTest.jsp?file=/mypage/mypageBuyerTransact.jsp").forward(req, resp);

	}
}