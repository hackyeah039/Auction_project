package semi.controller.yrmypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/home.do")
public class HomeController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String id = "gogo";
//		
//		HttpSession session = req.getSession();
//		session.setAttribute("id", id);
//		
//		
//		//경로
//		req.getServletContext().setAttribute("cp", req.getContextPath());
//		
//		req.setAttribute("header",  "header.jsp");
//		req.setAttribute("content",  "/mypage/buyManagement.do");
//		
//		req.getRequestDispatcher("/index.jsp").forward(req, resp);
//		
	}
}
