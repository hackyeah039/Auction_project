package semi.controller.shmainlist;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.controller.shdao.CheckUpDao;
@WebServlet("/sh/checkup.do")
public class CheckUpController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int a_num=Integer.parseInt(req.getParameter("a_num")) ;
		CheckUpDao dao=new CheckUpDao();
		int n=dao.checkup(a_num);
			resp.sendRedirect(req.getContextPath()+"/sh/main.do");
	}
}
