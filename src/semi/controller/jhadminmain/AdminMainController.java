package semi.controller.jhadminmain;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.dao.jh.AdminDao;
@WebServlet("/adminmain/main.jh")
public class AdminMainController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		req.setCharacterEncoding("utf-8");
		AdminDao dao=AdminDao.getAdminDao();
		int singoCount=dao.getSingoCount();//신고처리중개수
		int qnaCount=dao.getQnaCount();
		int membersOutCount=dao.getMembersOutCount();
		req.setAttribute("singoCount", singoCount);
		req.setAttribute("qnaCount", qnaCount);
		req.setAttribute("membersCount", membersOutCount);
		req.getRequestDispatcher("/admin/adminIndex.jsp?file=adminMain.jsp").forward(req, resp);
		
		
	}
}
