package semi.controller.jhmembers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.dao.jh.MembersDao;
import semi.vo.jh.MembersVo;
@WebServlet("/members/detail.jh")
public class MembersDetailController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		int m_num=Integer.parseInt(req.getParameter("m_num"));
		MembersDao dao=MembersDao.getMembersDao();
		ArrayList<MembersVo> list=dao.membersDetail(m_num);
		
		req.setAttribute("list", list);
		req.getRequestDispatcher("/admin/adminIndex.jsp?file=membersDetail.jsp").forward(req, resp);
	}
}
