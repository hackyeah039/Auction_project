package semi.controller.yrorder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import semi.dao.yr.OrderDao;
import semi.vo.yr.MembersVo;
import semi.vo.yr.ShipVo;

/**
 * @author yurae
 * 
 *         입금하기 페이지 get으로 하면 선택한 입금 상품이 보여짐 post하면 입금이 완료됨 (payment테이블
 *         insert해야함)
 *
 */

@WebServlet("/order/order.do")
public class OrderController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, javax.servlet.http.HttpServletResponse resp)
			throws ServletException, IOException {
		
		//회원 번호 가져오기
		int mnum = 1;
		
		// 회원번호 가져오기
//		HttpSession session = req.getSession();
//		int mnum = (int)session.getAttribute("mnum");


		
		String[] spaynum = req.getParameterValues("checkbox");
		String message = req.getParameter("message");
		
		// 0 : num, 1:title, 2:price
		String[] messages = message.split(":");

		OrderDao dao = new OrderDao();
		
		// 배송 정보
		HashMap<Integer, ShipVo> shipinfoList = new HashMap<Integer, ShipVo>();
		
		// 상품 제목
		HashMap<Integer, String> titleList = new HashMap<Integer, String>();
		
		// 상품 가격
		HashMap<Integer, Integer> priceList = new HashMap<Integer, Integer>();
		
		//구매자 정보 가져오기
		MembersVo vo = dao.getMemberInfo(mnum);
		
		ArrayList<Integer> orderanumList = new ArrayList<Integer>();
		
		int anum = 0;

		for (int i = 0; i < messages.length; i++) {
			if (i % 3 == 0) {
				anum = Integer.parseInt(messages[i]);
				orderanumList.add(anum);
				ShipVo sv = dao.getShipInfo(anum);
				shipinfoList.put(anum, sv);
			} else if (i % 3 == 1) {
				titleList.put(anum, messages[i]);
			} else if (i % 3 == 2) {
				priceList.put(anum, Integer.parseInt(messages[i]));
			}
		}
		
		
		
		
		// test
//		for (Integer key : shipinfoList.keySet()) {
//			System.out.println(String.format("키 : %s, 값 : %s", key, shipinfoList.get(key)));
//		}
//		for (Integer key : titleList.keySet()) {
//			System.out.println(String.format("키 : %s, 값 : %s", key, titleList.get(key)));
//		}
//		for (Integer key : priceList.keySet()) {
//			System.out.println(String.format("키 : %s, 값 : %s", key, priceList.get(key)));
//		}
//
//		for (int anum2 : orderanumList) {
//			System.out.println(anum2);
//		}
		
		
		req.setAttribute("paynumList", spaynum);
		req.setAttribute("orderanumList", orderanumList);
		req.setAttribute("shipinfoList", shipinfoList);
		req.setAttribute("titleList", titleList);
		req.setAttribute("memberInfo", vo);
		req.setAttribute("priceList", priceList);
//		req.setAttribute("header", "header.jsp");
//		req.setAttribute("content", "/order/orderSheet.jsp");
//		req.getRequestDispatcher("/index.jsp").forward(req, resp);
	    req.getRequestDispatcher("/main_sh/layoutTest.jsp?file=/order/orderSheet.jsp").forward(req, resp);

	}
}
