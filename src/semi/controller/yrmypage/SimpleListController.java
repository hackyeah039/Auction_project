package semi.controller.yrmypage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import semi.dao.yr.BiddingDao;
import semi.dao.yr.TranCompletedDao;
import semi.dao.yr.TransactDao;
import semi.vo.yr.BidVo;
import semi.vo.yr.PaymentVo;

@WebServlet("/mypage/simplelist.do")
public class SimpleListController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//회원 번호 가져오기
//		int mnum = 4;
		
		//경로
//		req.getServletContext().setAttribute("cp", req.getContextPath());

	
		HttpSession session = req.getSession();
		int mnum = (Integer)session.getAttribute("m_num"); 
		
//		System.out.println("mnum" + mnum);
		
		BiddingDao dao = new BiddingDao();

		//신뢰도 가져오기
		int trust = dao.getMnumTrust(mnum);
		
		
//		입찰중(입찰중인데, bid에 자신의 번호가 들어가있는것)
		int bidlistSize = 0;

		try {
			ArrayList<Integer>bidlist =  dao.buyerBidinglist(mnum);
			bidlistSize = bidlist.size(); 
		}catch (NullPointerException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			bidlistSize = 0;
		}
				
		
//		입금요청(입찰 완료인데 자신의 회원번호가 일등인것) 
		TransactDao tddao = new TransactDao();
		
		int reqPayCount=0;
		try {
			ArrayList<Integer> anumlist = tddao.getTransactList(mnum);
			ArrayList<BidVo> tranBidList = tddao.getTranBidList(anumlist, mnum);
			
			
			for (BidVo bidVo : tranBidList) {
				PaymentVo payvo = tddao.getPaymentInfo(bidVo.getBid_num());
				System.out.println(bidVo);
				if(payvo.getPay_status() == 0) {
					reqPayCount++;
				}
			}
		}catch (NullPointerException e) {
			// TODO: handle exception
			System.out.println("입금요청 : "+e.getMessage());
			reqPayCount = 0;
		}
		
		

//		판매중(입찰하기 전이거나, 입찰 중인 auction 물품)
		int saleCount = 0;
		int shipReqCount = 0;
//		미답변 갯수
		int quesCount = 0;

		TranCompletedDao tcdao = new TranCompletedDao();
		ArrayList<Integer> forSellerTranList  = new ArrayList<Integer>();
		ArrayList<BidVo> bidvolist = new ArrayList<BidVo>();
		
		try {
			ArrayList<Integer> selList = tddao.getSelnum(mnum);	//판매자 번호 가져오기	
			forSellerTranList = tddao.getForSellerTran(selList,1);	//anum
			saleCount = forSellerTranList.size();
			for (int anum : forSellerTranList) {
				quesCount += tddao.getCountQues(anum);
			}
			
			
		}catch (NullPointerException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			saleCount = 0;
			shipReqCount = 0;
			quesCount = 0;
		}
		
		System.out.println("sc1 "+ quesCount);
		
//		배송요청(입찰 완료)  paystatus = 1, auction bidstatus = 2;

		for (Integer anum : forSellerTranList) {
			BidVo vo= tcdao.getBidVo(anum);
			if(vo == null) {
				continue;
			}else {
				bidvolist.add(vo);
				PaymentVo pvo= tddao.getPaymentInfo(vo.getBid_num());
				if(pvo != null) {
					if(pvo.getPay_status() == 1) {
						shipReqCount++;
					}								
				}				
			}
		}
	
		

		
		req.setAttribute("trust", trust);		
		req.setAttribute("bidCount", bidlistSize);//입찰중		
        req.setAttribute("reqPayCount", reqPayCount);//입금요청	
        req.setAttribute("saleCount", saleCount); //판매중		
        req.setAttribute("shipReqCount", shipReqCount); //배송요청
        req.setAttribute("quesCount", quesCount); //미답변
//        req.setAttribute("header", "main_sh/layoutTest.jsp");
        req.setAttribute("file", "/mypage/mypagefirst.jsp");
        req.getRequestDispatcher("/main_sh/layoutTest.jsp").forward(req, resp);
//        req.getRequestDispatcher("/main_sh/layoutTest.jsp?file=/mypage/mypagefirst.jsp").forward(req, resp);
	
	}
}
