package semi.controller.jhmembers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.dao.jh.MembersDao;
@WebServlet("/members/out.jh")
public class MembersOutController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		int m_num=Integer.parseInt(req.getParameter("m_num"));
		MembersDao dao=MembersDao.getMembersDao();
		int n=dao.membersOut(m_num);
		String msg;
		if(n>0) {
			msg="ok";
		}else {
			msg="error";
		}
		req.setAttribute("msg", msg);
		req.getRequestDispatcher("/admin/adminIndex.jsp?file=membersOutOk.jsp").forward(req, resp);
	}
}
