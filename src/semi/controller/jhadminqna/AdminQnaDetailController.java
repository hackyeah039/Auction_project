package semi.controller.jhadminqna;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.dao.jh.BoardDao;
import semi.vo.jh.B_answerVo;
import semi.vo.jh.BoardVo;

@WebServlet("/board/detail.jh")
public class AdminQnaDetailController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		int b_num=Integer.parseInt(req.getParameter("b_num"));
		String m_id=req.getParameter("m_id");
		BoardDao dao=BoardDao.getBoardDao();
		ArrayList<BoardVo> list=dao.boardDetail(b_num);
		ArrayList<B_answerVo> list2=dao.dapDetail(b_num);
		req.setAttribute("list", list);
		req.setAttribute("list2", list2);
		req.setAttribute("id", m_id);
		req.getRequestDispatcher("/admin/adminIndex.jsp?file=adminQnaDetail.jsp").forward(req, resp);
	}
}
