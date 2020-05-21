package semi.controller.jhmaintest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/home.jiho")
public class IndexMainTest extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String contentPath=req.getContextPath();
		req.getServletContext().setAttribute("cp", contentPath);
		resp.sendRedirect(contentPath+"/indextest.jsp");
	}
}