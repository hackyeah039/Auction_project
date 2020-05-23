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
//		회원번호 받아오기
		int m_num =  Integer.parseInt(req.getParameter("m_num"));//회원번호

//		경매번호 받아오기(파라미터로 넘겼음)
		int a_num = Integer.parseInt(req.getParameter("a_num"));//경매번호		

//		입찰단위 가져오기 
		AuctionDao adao = AuctionDao.getInstance();
		AuctionVo avo = adao.searchAuction(a_num);
		int bidunit = avo.getA_bidunit();
		int a_startbid = avo.getA_startbid();

//		입찰 최대 값 구하기
		int maxBid = 0;
		BiddingDao bdao = BiddingDao.getInstance();
		int check = bdao.showMaxBid(a_num);
		if (check == 0) {
//			DB에 입찰된 값이 없을 경우 초기 설정값으로 입찰 할 수 있도록
			maxBid = bidunit + a_startbid;
		} else {
			maxBid = check + bidunit;
		}

//		세션에 저장하기		
		req.setAttribute("m_num", m_num);
		req.setAttribute("a_num", a_num);
		req.setAttribute("maxBid", maxBid);

//		Bidding.jsp로 이동
		req.getRequestDispatcher("/Bidding/bidding.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=utf-8");
//		입력 받은 값 변수에 넣기
		int m_num = Integer.parseInt(req.getParameter("m_num"));
		int a_num = Integer.parseInt(req.getParameter("a_num"));
		int bid_price = Integer.parseInt(req.getParameter("bid_price"));
		
//		Vo에 넣기
		BiddingVo bvo = new BiddingVo(m_num, a_num, bid_price, null, 0);
		
		BiddingDao bdao = BiddingDao.getInstance();
		
//		DB 저장 결과 출력
		int n = bdao.insertBid(bvo);
		
//		DB저장 결과 페이지로 이동
		PrintWriter pw = resp.getWriter(); 
		if (n > 0) {
//			저장 성공시 alert창 띄우고 닫기 클릭시 자동으로 창 닫음. 
			pw.print("<script>alert('입찰성공');window.open('about:blank', '_self').close();</script>"); 
		} else {
			pw.print("<script>alert('입찰실패');window.open('about:blank', '_self').close();</script>");
		}
	}
}
