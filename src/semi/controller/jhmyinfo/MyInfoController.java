package semi.controller.jhmyinfo;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.dao.jh.MembersDao;
import semi.vo.jh.MembersVo;
@WebServlet("/myinfo.jh")
public class MyInfoController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		int m_num=Integer.parseInt(req.getParameter("m_num"));
		System.out.println("세션으로 받아온 회원번호:"+m_num);
		MembersDao dao=MembersDao.getMembersDao();
		ArrayList<MembersVo> list=dao.getMyInfo(m_num);
		req.setAttribute("list", list);
		req.getRequestDispatcher("/myInfo/myinfoMain.jsp?file=myinfoUpdate.jsp").forward(req, resp);
		
		
	}
}
