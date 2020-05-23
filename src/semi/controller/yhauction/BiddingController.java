package semi.controller.yhauction;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.dao.yh.AuctionDao;
import semi.dao.yh.BiddingDao;
import semi.vo.yh.AuctionVo;
import semi.vo.yh.BiddingVo;

@WebServlet("/Bidding.do")
public class BiddingController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//		�쉶�썝踰덊샇 諛쏆븘�삤湲�
//		int m_num =  (int)req.getAttribute("m_num");
		int m_num = 1; // �뀒�뒪�듃 �슜

//		寃쎈ℓ踰덊샇 諛쏆븘�삤湲�(�뙆�씪誘명꽣濡� �꽆寃쇱쓬)
//		int a_num = Integer.parseInt(req.getParameter("a_num"));		
		int a_num = 3; // �뀒�뒪�듃 �슜

//		�엯李곕떒�쐞 媛��졇�삤湲� 
		AuctionDao adao = AuctionDao.getInstance();
		AuctionVo avo = adao.searchAuction(a_num);
		int bidunit = avo.getA_bidunit();
		int a_startbid = avo.getA_startbid();

//		�엯李� 理쒕� 媛� 援ы븯湲�
		int maxBid = 0;
		BiddingDao bdao = BiddingDao.getInstance();
		int check = bdao.showMaxBid(a_num);
		if (check == 0) {
//			DB�뿉 �엯李곕맂 媛믪씠 �뾾�쓣 寃쎌슦 珥덇린 �꽕�젙媛믪쑝濡� �엯李� �븷 �닔 �엳�룄濡�
			maxBid = bidunit + a_startbid;
		} else {
			maxBid = check + bidunit;
		}

//		�꽭�뀡�뿉 ���옣�븯湲�		
		req.setAttribute("m_num", m_num);
		req.setAttribute("a_num", a_num);
		req.setAttribute("maxBid", maxBid);

//		Bidding.jsp濡� �씠�룞
		req.getRequestDispatcher("/Bidding/bidding.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=utf-8");
//		�엯�젰 諛쏆� 媛� 蹂��닔�뿉 �꽔湲�
		int m_num = Integer.parseInt(req.getParameter("m_num"));
		int a_num = Integer.parseInt(req.getParameter("a_num"));
		int bid_price = Integer.parseInt(req.getParameter("bid_price"));
		
//		Vo�뿉 �꽔湲�
		BiddingVo bvo = new BiddingVo(m_num, a_num, bid_price, null, 0);
		
		BiddingDao bdao = BiddingDao.getInstance();
		
//		DB ���옣 寃곌낵 異쒕젰
		int n = bdao.insertBid(bvo);
		
//		DB���옣 寃곌낵 �럹�씠吏�濡� �씠�룞
		PrintWriter pw = resp.getWriter(); 
		if (n > 0) {
//			���옣 �꽦怨듭떆 alert李� �쓣�슦怨� �떕湲� �겢由��떆 �옄�룞�쑝濡� 李� �떕�쓬. 
			pw.print("<script>alert('�엯李곗꽦怨�');window.open('about:blank', '_self').close();</script>"); 
		} else {
			pw.print("<script>alert('�엯李곗떎�뙣');window.open('about:blank', '_self').close();</script>");
		}
	}
}
