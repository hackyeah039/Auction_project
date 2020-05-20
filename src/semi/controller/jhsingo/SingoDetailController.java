package semi.controller.jhsingo;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.dao.jh.SingoDao;
import semi.vo.jh.SingoVo;
@WebServlet("/singo/detail.jh")
public class SingoDetailController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		int singoNum=Integer.parseInt(req.getParameter("singo_num"));
		String m_id=req.getParameter("id");
		SingoDao dao=SingoDao.getSingoDao();
		ArrayList<SingoVo> list=dao.singoDetailList(singoNum);
		req.setAttribute("list", list);
		req.setAttribute("id", m_id);//신고자 아이디도 보내주기
		req.getRequestDispatcher("/admin/adminIndex.jsp?file=singoDetail.jsp").forward(req, resp);
	}
}
