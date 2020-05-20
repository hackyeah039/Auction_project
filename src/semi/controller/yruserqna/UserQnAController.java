package semi.controller.yruserqna;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.dao.yr.BoardDao;
import semi.vo.yr.BoardVo;

@WebServlet("/board/userQnA.do")
public class UserQnAController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//회원번호가져오기
		int mnum = 1;
		
		BoardDao dao = new BoardDao();
		ArrayList<BoardVo> list = dao.listBoard(mnum);
		for (BoardVo boardVo : list) {
			System.out.println(boardVo);
		}
		req.setAttribute("BoardListSize", list.size());
		req.setAttribute("BoardList", list);
		req.setAttribute("header", "header.jsp");
		req.setAttribute("content", "/board/userQnA.jsp");
		req.getRequestDispatcher("/index.jsp").forward(req, resp);
	}
}
