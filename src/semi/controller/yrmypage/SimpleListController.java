package semi.controller.yrmypage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

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
		int mnum = 1;
		
		//경로
		req.getServletContext().setAttribute("cp", req.getContextPath());

		
		
//		HttpSession session = req.getSession();
//		int mnum = (Integer)session.getAttribute("m_num"); 
		
//		입찰중(입찰중인데, bid에 자신의 번호가 들어가있는것)
		BiddingDao dao = new BiddingDao();
		ArrayList<Integer>bidlist =  dao.buyerBidinglist(mnum);
		
//		입금요청(입찰 완료인데 자신의 회원번호가 일등인것) 
		TransactDao tddao = new TransactDao();
		ArrayList<Integer> anumlist = tddao.getTransactList(mnum);
		ArrayList<BidVo> tranBidList = tddao.getTranBidList(anumlist, mnum);
		int reqPayCount = 0;
		for (BidVo bidVo : tranBidList) {
			PaymentVo payvo = tddao.getPaymentInfo(bidVo.getBid_num());
			if(payvo.getPay_status() == 0) {
				reqPayCount++;
			}
		}

//		판매중(입찰하기 전이거나, 입찰 중인 auction 물품)
		ArrayList<Integer> selList = tddao.getSelnum(mnum);
		
		ArrayList<Integer> forSellerTranList = tddao.getForSellerTran(selList,1);
		
//		배송요청(입찰 완료)  paystatus = 1, auction bidstatus = 2;
		TranCompletedDao tcdao = new TranCompletedDao();
		int shipReqCount = 0;
		ArrayList<PaymentVo> pvlist = new ArrayList<PaymentVo>();
		ArrayList<BidVo> bidvolist = new ArrayList<BidVo>();
		for (Integer anum : forSellerTranList) {
			BidVo vo= tcdao.getBidVo(anum);
			if(vo != null) {
				bidvolist.add(vo);
				PaymentVo pvo= tddao.getPaymentInfo(vo.getBid_num());
				if(pvo != null) {
					if(pvo.getPay_status() == 1) {
						shipReqCount++;
					}			
				}
			}
		}
		
		
		req.setAttribute("bidCount", bidlist.size());		
        req.setAttribute("reqPayCount", reqPayCount);		
        req.setAttribute("saleCount", forSellerTranList.size());		
        req.setAttribute("shipReqCount", shipReqCount);
        req.setAttribute("header", "header.jsp");
        req.setAttribute("content", "/mypage/mypagefirst.jsp");
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
	
	}
}
