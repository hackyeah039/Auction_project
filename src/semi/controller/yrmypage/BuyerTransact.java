package semi.controller.yrmypage;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import semi.vo.yr.BidVo;

@WebServlet("/mypage/buyerTransact.do")
public class BuyerTransact extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		int mnum = 1;
		
		//회원번호 가져오기
//		HttpSession session = req.getSession();
//		int mnum = (int)session.getAttribute("mnum");
		
		
		TransactDao dao = new TransactDao();
		
		//거래중인 리스트 들고오기
		ArrayList<Integer> anumlist = dao.getTransactList(mnum);
		
		for(int anum : anumlist) {
			System.out.println(anum);
		}
		
		//상품정보 가져오기
		ArrayList<BidVo> tranBidList = dao.getTranBidList(anumlist, mnum);
		for (BidVo bidVo : tranBidList) {
			System.out.println(bidVo);
		}
		
		
		req.setAttribute("anumlist", anumlist);
		req.setAttribute("tranBidList", tranBidList);
		req.setAttribute("header", "header.jsp");
        req.setAttribute("content", "/mypage/mypageBuyerTransact.jsp");
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
	}
}
