package semi.controller.yrorder;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**@author yurae
 * 
 * 입금하기 페이지
 * get으로 하면 선택한 입금 상품이 보여짐
 * post하면 입금이 완료됨 (payment테이블 insert해야함)
 *
 */

@WebServlet("/order/order.do")
public class OrderController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String[] orderList = req.getParameterValues("checkbox");
		String[] title = req.getParameterValues("title");
		String[] price = req.getParameterValues("price");
		
		for (String order : price) {
			
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
	
}
