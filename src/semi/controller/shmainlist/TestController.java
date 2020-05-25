package semi.controller.shmainlist;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.controller.shdao.MainListDao;
@WebServlet("/sh/testMain.do")
public class TestController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String contextPath=req.getContextPath();
		ServletContext application=req.getServletContext();
		application.setAttribute("cp", contextPath);
		MainListDao dao=new MainListDao();
		System.out.println("실행한다");
		dao.updateBidStatus();
		dao.getStartAuction();
		
		resp.sendRedirect(req.getContextPath()+"/main_sh/layoutTest.jsp");
	}
}
