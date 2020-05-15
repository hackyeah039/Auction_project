package semi.controller.shmainlist;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.controller.shdao.CheckUpDao;
@WebServlet("/checkup.do")
public class CheckUpController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("checkup.do 접속");
		int a_num=Integer.parseInt(req.getParameter("a_num")) ;
		CheckUpDao dao=new CheckUpDao();
		int n=dao.checkup(a_num);
		System.out.println("dao 접속 후");
		if(n>0) {
			resp.sendRedirect(req.getContextPath()+"/main.do");
		}
	}
}
