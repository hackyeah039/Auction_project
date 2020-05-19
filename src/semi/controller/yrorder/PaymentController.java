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
		
		String[] spaynumList = req.getParameterValues("paynumList");
		String buyerName = req.getParameter("buyerName");
		String zipcode = req.getParameter("zipcode");
		String rodename = req.getParameter("rodename");
		String detailaddr = req.getParameter("detailaddr");
		String phone = req.getParameter("phone");
		
		ArrayList<Integer> paynumList = new ArrayList<Integer>();
		
		OrderDao dao = new OrderDao();
		
		
		//전달받은 paynum을 int형으로 바꿈
		for (String spaynum : spaynumList) {
			paynumList.add(Integer.parseInt(spaynum));
		}
		
		//해당 paynum에 입력받은 수령인, 주소, 전화번호로 update dao 실행
		
		
		PaymentVo vo = new PaymentVo(0, zipcode+":"+rodename+":"+detailaddr,
				buyerName, phone);
		int n = dao.updatePayInfo(vo, paynumList);

//		for (String string : paynumList) {
//			System.out.println("paynum : " + string);			
//		}
//		System.out.println("buyername : " + buyerName);
//		System.out.println("zipcode : " + zipcode);
//		System.out.println("rodename : " + rodename);
//		System.out.println("detailaddr : " + detailaddr);
//		System.out.println("phone : " + phone);
		
		if(n>0) {
			
		}else {
			
		}
		
	}
}
