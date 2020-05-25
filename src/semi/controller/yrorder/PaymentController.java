package semi.controller.yrorder;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.dao.yr.OrderDao;
import semi.vo.yr.PaymentVo;

@WebServlet("/order/payment.do")
public class PaymentController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		
		String[] spaynumList = req.getParameterValues("paynumList");
		String buyerName = req.getParameter("buyerName");
		String zipcode = req.getParameter("zip");
		String rodename = req.getParameter("addr1");
		String detailaddr = req.getParameter("addr2");
		String phone = req.getParameter("phone");
		String price = req.getParameter("price");
		String bank = req.getParameter("bank");
		String card = req.getParameter("card");
		
		ArrayList<Integer> paynumList = new ArrayList<Integer>();
		
		OrderDao dao = new OrderDao();
		
		//전달받은 paynum을 int형으로 바꿈
		for (String spaynum : spaynumList) {
			paynumList.add(Integer.parseInt(spaynum));
		}
		//해당 paynum에 입력받은 수령인, 주소, 전화번호로 update, pay_status 입금완료 1로 업데이트 dao 실행	
		PaymentVo vo = new PaymentVo(0, zipcode+":"+rodename+":"+detailaddr,
				buyerName, phone);
		int n = dao.updatePayInfo(vo, paynumList);
		
		if(bank != null) {
			//은행
			if(bank.equals("1")) {
				bank = "국민은행";
			}else if (bank.equals("2")) {
				bank = "신한은행";			
			}else if (bank.equals("3")) {
				bank = "우리은행";			
			}
		}
		
//		for (String string : paynumList) {
//			System.out.println("paynum : " + string);			
//		}
//		System.out.println("buyername : " + buyerName);
//		System.out.println("zipcode : " + zipcode);
//		System.out.println("rodename : " + rodename);
//		System.out.println("detailaddr : " + detailaddr);
//		System.out.println("phone : " + phone);
		
		System.out.println("bank"+bank);
		
		if(n>0) {
			
			req.setAttribute("buyerName", buyerName);
			req.setAttribute("price", price);
			req.setAttribute("bank", bank);
			req.setAttribute("result", "success");
			req.setAttribute("file", "/order/resultOrder.jsp");
			
	        req.getRequestDispatcher("/main_sh/layoutTest.jsp").forward(req, resp);
//			req.getRequestDispatcher("/order/resultOrder.jsp").forward(req, resp);
//		    req.getRequestDispatcher("/main_sh/layoutTest.jsp?/order/resultOrder.jsp").forward(req, resp);
		}else {
			req.setAttribute("result", "false");

			req.setAttribute("file", "/order/resultOrder.jsp");
	        req.getRequestDispatcher("/main_sh/layoutTest.jsp").forward(req, resp);
//			req.getRequestDispatcher("/main_sh/layoutTest.jsp?/order/resultOrder.jsp").forward(req, resp);
		}
	}
}
