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
		
		req.setCharacterEncoding("utf-8");
		
		//판매자 정보
		String spaynum = req.getParameter("paynum");
		String sanum = req.getParameter("anum");
//		System.out.println("paynum : "+spaynum);
//		System.out.println("sanum : " + sanum);

		ShipDao dao = new ShipDao();
		
		int paynum = Integer.parseInt(spaynum);
		PaymentVo buyerInfo = dao.getBuyerInfo(paynum); 
		
		resp.setCharacterEncoding("utf-8");
		JSONObject data = new JSONObject();
		PrintWriter out = resp.getWriter();
		
		data.put("anum", sanum);
		data.put("addr", buyerInfo.getPay_addr());
		data.put("name", buyerInfo.getPay_name());
		data.put("phone",buyerInfo.getPay_phone());
		
		
		out.print(data);
		
//		req.setAttribute("anum", sanum);
//		req.setAttribute("buyerInfo", buyerInfo);
//		req.getRequestDispatcher("/popup/reqShipPopup.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//인코딩
		req.setCharacterEncoding("utf-8");
		
		
		String courier = req.getParameter("courier");
		String invoicenum = req.getParameter("invoicenum");
		String sanum = req.getParameter("anum");
		int anum = Integer.parseInt(sanum);
		String message = "";
//		System.out.println(courier + ", "+invoicenum + ", "+ anum);
		
		ShipDao dao = new ShipDao();
		System.out.println(anum);
		int n = dao.updateShipInfo(anum, courier, invoicenum);
		
		JSONObject data = new JSONObject();
		PrintWriter out = resp.getWriter();
		
		try {
			if(n>0) {
					int n2 = dao.updateBidStatus(anum);
					System.out.println(n2);
					if(n2 > 0) {
						data.put("message", "success");	
						message = "success";
					}else {
						data.put("message", "fail");						
						message = "fail1";
					}
			}else {
				data.put("message", "fail");
				message = "fail2";
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("message" + message);
		out.print(data);
		out.close();
	}
}
