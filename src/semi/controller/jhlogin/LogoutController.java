package semi.controller.jhlogin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/logout.jh")
public class LogoutController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		HttpSession session=req.getSession();
		if(session.getAttribute("id") != null || 
				session.getAttribute("adminId") !=null || 
				session.getAttribute("m_id") !=null) {
			session.invalidate();
			String contentPath=req.getContextPath();
			resp.sendRedirect(contentPath+"/sh/testMain.do");
		}
	}
}
