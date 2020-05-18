package semi.controller.shmainlist;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.controller.shdao.MainListDao;
@WebServlet("/sh/updateBid.do")
public class UpdateBidStatusController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MainListDao dao=new MainListDao();
		int n=dao.updateBidStatus();
		if(n>0) {
			resp.sendRedirect(req.getContextPath()+"/sh/main.do");
		}else {
			resp.sendRedirect(req.getContextPath()+"/main_sh/errorPage.jsp");
		}
	}
}
