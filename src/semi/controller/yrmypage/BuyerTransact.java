package semi.controller.yrmypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/mypage/buyerTransact.do")
public class BuyerTransact extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		int mnum = 1;
		
		//회원번호 가져오기
//		HttpSession session = req.getSession();
//		int mnum = (int)session.getAttribute("mnum");
		
		//거래중인 리스트 들고오기
		
		
		
	}
}
