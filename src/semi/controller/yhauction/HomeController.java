package semi.controller.yhauction;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/home.do")
public class HomeController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getServletContext().setAttribute("cp", req.getContextPath());
		
		req.setAttribute("header",  "");
		req.setAttribute("content",  "InsertAuction.jsp");
		
		req.getRequestDispatcher("/index.jsp").forward(req, resp);
//		
	}
}