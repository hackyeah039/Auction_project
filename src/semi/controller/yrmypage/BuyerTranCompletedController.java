package semi.controller.yrmypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/mypage/buyerTranCompleted.do")
public class BuyerTranCompletedController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// <구매자>구분, 물품번호, 이미지, 물품명, 낙찰가, 입찰, 조회, 마감일, 판매자, 입찰결과(낙찰,낙찰 받지 못한 물품 )
		
		int mnum = 1;
		
		//회원 번호가져오기
//		HttpSession session = req.getSession();
//		int id = (int)session.getAttribute("mnum"); 
		
		//
		

	}
}
