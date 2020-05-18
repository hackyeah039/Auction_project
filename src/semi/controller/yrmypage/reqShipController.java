package semi.controller.yrmypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.dao.yr.ReqShipDao;
import semi.vo.yr.PaymentVo;

@WebServlet("/popup/reqShipPopup.do")
public class reqShipController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//주문자 정보
		String sanum = req.getParameter("anum");
		
		ReqShipDao dao = new ReqShipDao();
		
		int anum = Integer.parseInt(sanum);
		PaymentVo buyerInfo = dao.getBuyerInfo(anum); 
			
		req.setAttribute("buyerInfo", buyerInfo);
		req.getRequestDispatcher("/popup/reqShipPopup.jsp").forward(req, resp);
	}
}
