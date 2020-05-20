package semi.controller.jhlogin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/login/findId.jh")
public class FindIdController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String m_id=req.getParameter("m_id");
		int m_phone=Integer.parseInt(req.getParameter("m_phone"));
		System.out.println(m_id);
		System.out.println(m_phone);
		
		
		
		
	}
}
