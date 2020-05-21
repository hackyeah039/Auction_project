package semi.controller.yrmypage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import semi.dao.yr.ShipDao;
import semi.vo.yr.PaymentVo;

@WebServlet("/popup/reqShipPopup.do")
public class reqShipController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//주문자 정보
		String spaynum = req.getParameter("paynum");
		String sanum = req.getParameter("anum");
//		System.out.println("paynum"+spaynum);
		
		ShipDao dao = new ShipDao();
		
		int paynum = Integer.parseInt(spaynum);
		PaymentVo buyerInfo = dao.getBuyerInfo(paynum); 
		
		req.setAttribute("anum", sanum);
		req.setAttribute("buyerInfo", buyerInfo);
		req.getRequestDispatcher("/popup/reqShipPopup.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//인코딩
		req.setCharacterEncoding("utf-8");
		
		
		String courier = req.getParameter("courier");
		String invoicenum = req.getParameter("invoicenum");
		String sanum = req.getParameter("anum");
		int anum = Integer.parseInt(sanum);
		
		System.out.println(courier + ", "+invoicenum + ", "+ anum);
		
		ShipDao dao = new ShipDao();
		int n = dao.updateShipInfo(anum, courier, invoicenum);
		
		JSONObject data = new JSONObject();
		PrintWriter out = resp.getWriter();
		
		try {
			if(n>0) {
					int n2 = dao.updateBidStatus(anum);
					if(n2 > 0) {
						data.put("message", "success");						
					}else {
						data.put("message", "fail");						
					}
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
