package semi.controller.jwIndex;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/home.do")
public class HomeController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String top=(String)req.getAttribute("top");
		String content=(String)req.getAttribute("content");
		String footer=(String)req.getAttribute("footer");
		if(top==null) {
			top="/header.jsp";
		}
		if(content==null) {
			content="/home.jsp";
		}
		if(footer==null) {
			footer="/footer.jsp";
		}
		req.setAttribute("top",top);
		req.setAttribute("content",content);
		req.setAttribute("footer", footer);
		getServletContext().setAttribute("cp",req.getContextPath());
		req.getRequestDispatcher("/index.jsp").forward(req, resp);
	}
}










